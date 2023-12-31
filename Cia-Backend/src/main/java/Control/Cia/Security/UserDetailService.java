package Control.Cia.Security;

import Control.Cia.models.Usuario;
import Control.Cia.repository.IUsuarioRepositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    private Usuario userDetail;
    /**
     * Carga los detalles del usuario utilizando el nombre de usuario (en este caso, el correo electrónico).
     *
     * @param username El nombre de usuario (correo electrónico) para buscar en el repositorio de usuarios.
     * @return Detalles del usuario encapsulados en un objeto UserDetails.
     * @throws UsernameNotFoundException Excepción lanzada si el usuario no es encontrado en el repositorio.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Dentro de loadUserByUsername{} ", username);

        // Buscar los detalles del usuario en el repositorio utilizando el correo electrónico como nombre de usuario
        userDetail = usuarioRepositorio.findByEmail(username);

        // Imprimir la contraseña del usuario en la consola (se puede omitir en un entorno de producción)
        System.out.println(userDetail.getPassword());

        if (!Objects.isNull(userDetail)) {
            // Devolver un objeto UserDetails con el correo electrónico, la contraseña y una lista vacía de autoridades
            return new org.springframework.security.core.userdetails.User(
                    userDetail.getEmail(),
                    userDetail.getPassword(),
                    new ArrayList<>()
            );
        } else {
            // Lanzar una excepción si el usuario no es encontrado en el repositorio
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }


    public Usuario getUserDetail(){
        return  userDetail;
    }
}
