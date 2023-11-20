package Control.Cia.Jwt;

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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Dentro de loadUserByUsername{}",username);

        userDetail=usuarioRepositorio.findByEmail(username);

        if(!Objects.isNull((userDetail))){
            return new org.springframework.security.core.userdetails.User(userDetail.getEmail(),userDetail.getPassword(),new ArrayList<>())
        }else{
            throw  new UsernameNotFoundException("Usuario no encontrado")
        }

    }

    public Usuario getUserDetail(){
        return  userDetail;
    }
}
