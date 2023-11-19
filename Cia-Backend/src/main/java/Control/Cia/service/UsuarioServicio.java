package Control.Cia.service;


import Control.Cia.Utils.FacturaUtils;
import Control.Cia.excepcion.DataInvalida;
import Control.Cia.excepcion.ServerError;
import Control.Cia.models.Usuario;
import Control.Cia.repository.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

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


    /**
     * Registra a un nuevo usuario utilizando la información proporcionada en el mapa de solicitud.
     *
     * @param requestMap Un mapa que contiene los datos del usuario a ser registrado.
     * @return ResponseEntity<String> que indica el resultado del registro.
     */
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            // Verifica si los datos de registro son válidos
            if (validateSignUp(requestMap)) {
                // Verifica si el usuario ya existe en la base de datos
                Usuario user = usuarioRepositorio.findByEmail(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    // Si el usuario no existe, realiza el registro
                    usuarioRepositorio.save(getUserFromMap(requestMap));
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
        return user;
    }

}

