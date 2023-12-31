package Control.Cia.service;


import Control.Cia.Security.SecurityConfig;
import Control.Cia.Security.UserDetailService;
import Control.Cia.Security.jwt.JwtUtil;
import Control.Cia.Utils.FacturaUtils;
import Control.Cia.excepcion.DataInvalida;
import Control.Cia.excepcion.ServerError;
import Control.Cia.models.Ingreso;
import Control.Cia.models.Usuario;
import Control.Cia.repository.IUsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    /**
     * Inyección de dependencia del repositorio de usuarios.
     * La anotación @Autowired se utiliza para automáticamente cablear (wire) la dependencia
     * con el componente que la necesita, en este caso, el repositorio de usuarios.
     */
    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private  UserDetailService userDetailService;
    @Autowired
    private SecurityConfig securityConfig;


    @Override
    public List<Usuario> ListarUsuarios() {
        return usuarioRepositorio.findAll();
    }


    /**
     * Registra a un nuevo usuario utilizando la información proporcionada en el mapa de solicitud.
     *
     * @param requestMap Un mapa que contiene los datos del usuario a ser registrado.
     * @return ResponseEntity<String> que indica el resultado del registro.
     */
    // En tu método signUp de UsuarioServicio
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            // Verifica si los datos de registro son válidos
            if (validateSignUp(requestMap)) {
                // Verifica si el usuario ya existe en la base de datos
                Usuario user = usuarioRepositorio.findByEmail(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    // Hashea la contraseña antes de guardarla
                    String hashedPassword = securityConfig.passwordEncoder().encode(requestMap.get("password"));

                    // Crea el objeto Usuario con la contraseña hasheada
                    Usuario newUser = getUserFromMap(requestMap);
                    newUser.setPassword(hashedPassword);

                    // Guarda el usuario en la base de datos
                    usuarioRepositorio.save(newUser);

                    return FacturaUtils.getResponseEntity("Usuario Registrado con éxito", HttpStatus.CREATED);
                } else {
                    // Si el usuario ya existe, devuelve un error
                    return FacturaUtils.getResponseEntity("El usuario con este email ya existe", HttpStatus.BAD_REQUEST);
                }
            } else {
                // Si los datos no son válidos, lanza una excepción de datos inválidos
                throw new DataInvalida("Error: Datos Inválidos");
            }
        } catch (DataInvalida dataInvalida) {
            // Captura y maneja la excepción de datos inválidos
            return FacturaUtils.getResponseEntity("Error: " + dataInvalida.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            // Captura y maneja cualquier otra excepción
            exception.printStackTrace();
            throw new ServerError("Error Interno del Servidor");
        }
    }


    /**
     * Procesa la autenticación del usuario y genera un token JWT si las credenciales son válidas.
     *
     * @param requestMap Un mapa que contiene las credenciales del usuario (correo electrónico y contraseña).
     * @return ResponseEntity con un mensaje o token JWT, y el código de estado HTTP correspondiente.
     */
    @Override
    public ResponseEntity<String> Login(Map<String, String> requestMap) {
        System.out.println("Dentro de login");
        try {
            // Intentar autenticar al usuario con las credenciales proporcionadas
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );

            System.out.println(authentication);

            if (authentication.isAuthenticated()) {
                // Verificar el estado del usuario antes de generar el token
                if (userDetailService.getUserDetail().isStatus()) {
                    // Generar un token JWT y devolverlo en una ResponseEntity exitosa


                    return new ResponseEntity<String>("{\"token\":\"" + jwtUtil.generateToken(userDetailService.getUserDetail().getEmail(), userDetailService.getUserDetail().getRole()) + "\"}", HttpStatus.OK);

                } else {
                    // Devolver un mensaje de error si el usuario no está aprobado
                    return new ResponseEntity<String>("{\"mensaje\":\"" + " Espere la Aprobacion del Admin " + "\"}", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception exception) {
            // Imprimir la traza de la excepción (puede ser útil para depuración)
            exception.printStackTrace();
        }

        // Devolver un mensaje de error si las credenciales son incorrectas
        return new ResponseEntity<String>("{\"mensaje\":\"" + " Credenciales Incorrectas " + "\"}", HttpStatus.BAD_REQUEST);
    }


    /**
     * Método privado para validar los datos de registro.
     * Comprueba si el mapa de solicitudes contiene las claves "nombre", "email" y "password".
     * @param requestMap Mapa que contiene los datos de registro.
     * @return true si todas las claves necesarias están presentes, false de lo contrario.
     */
    private boolean validateSignUp(Map<String, String> requestMap) {
        return requestMap.containsKey("nombre") && requestMap.containsKey("email") && requestMap.containsKey("password");
    }

    /**
     * Método privado para crear un objeto Usuario a partir del mapa de datos de la solicitud.
     * Asigna los valores del mapa al objeto Usuario y establece el rol como "user".
     * @param requestMap Mapa que contiene los datos de registro.
     * @return Objeto Usuario creado a partir de los datos del mapa.
     */
    private Usuario getUserFromMap(Map<String, String> requestMap) {
        Usuario user = new Usuario();
        // Asigna los valores del Map al objeto Usuario
        user.setNombre(requestMap.get("nombre"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setRole("user");
        user.setStatus(false);
        return user;
    }

}

