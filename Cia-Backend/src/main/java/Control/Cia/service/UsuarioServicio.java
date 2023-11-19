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

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            if (validateSignUp(requestMap)) {
                Usuario user = usuarioRepositorio.findByEmail(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    usuarioRepositorio.save(getUserFromMap(requestMap));
                    return FacturaUtils.getResponseEntity("Usuario Registrado con éxito", HttpStatus.CREATED);
                } else {
                    return FacturaUtils.getResponseEntity("El usuario con este email ya existe", HttpStatus.BAD_REQUEST);
                }
            } else {
                throw new DataInvalida("Error: Datos Inválidos");
            }
        } catch (DataInvalida dataInvalida) {
            return FacturaUtils.getResponseEntity("Error: " + dataInvalida.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new ServerError("Error Interno del Servidor");
        }
    }



    //Validacion
    private boolean validateSignUp(Map<String, String> requestMap){
        if(requestMap.containsKey("nombre") && requestMap.containsKey("email") && requestMap.containsKey("password")){
            return  true;
        }
        return false;
    }
    private Usuario getUserFromMap(Map<String,String>requesMap){
        Usuario user = new Usuario();
        user.setNombre(requesMap.get("nombre"));
        user.setEmail(requesMap.get("email"));
        user.setPassword(requesMap.get("password"));
        user.setRole("user");

        return user;
    }
}

