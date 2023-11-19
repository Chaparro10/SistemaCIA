package Control.Cia.Controllers;

import Control.Cia.Utils.FacturaUtils;
import Control.Cia.excepcion.ServerError;
import Control.Cia.models.Usuario;
import Control.Cia.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("cia-app")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    //http://localhost:8080/cia-app/save
    @PostMapping("save")
    public ResponseEntity<String> registrarUsuario(@RequestBody(required = true) Map<String,String> requestMap){
        try{
              return usuarioServicio.signUp(requestMap);
        }catch (Exception exception){
                exception.printStackTrace();
        }
        return FacturaUtils.getResponseEntity( "Error:" + new ServerError("Error Interno Servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
