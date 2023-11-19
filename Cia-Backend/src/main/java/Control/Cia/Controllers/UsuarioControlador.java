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

/**
 * Controlador que maneja las operaciones relacionadas con los usuarios.
 */
@RestController // Anotación que indica que esta clase es un controlador de Spring que manejará las solicitudes HTTP y devolverá respuestas JSON.
@CrossOrigin(value = "http://localhost:3000") // Habilita el acceso cruzado desde el origen http://localhost:3000
@RequestMapping("cia-app") // Mapeo de la ruta base para todas las operaciones en este controlador
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio; // Inyección de dependencia del servicio de usuario


    /**
     * Maneja la solicitud POST para registrar un nuevo usuario.
     *
     * @param requestMap Mapa que contiene los datos del usuario a registrar.
     * @return ResponseEntity<String> que contiene la respuesta de la operación.
     */

    /*@RequestBody(required = true) Map<String, String> requestMap: Anotación que indica que el cuerpo de la solicitud HTTP se debe convertir en un objeto Map y pasar como parámetro al método.
    La anotación @RequestBody se utiliza para deserializar automáticamente el cuerpo de la solicitud en un objeto Java.*/
    @PostMapping("save")//http://localhost:8080/cia-app/save
    public ResponseEntity<String> registrarUsuario(@RequestBody(required = true) Map<String, String> requestMap) {
        try {
            // Invoca el servicio para registrar un usuario y devuelve la respuesta
            return usuarioServicio.signUp(requestMap);
        } catch (Exception exception) {
            // Captura y maneja cualquier excepción no controlada
            exception.printStackTrace();
        }

        // Devuelve una respuesta de error genérica en caso de excepción
        return FacturaUtils.getResponseEntity("Error: " + new ServerError("Error Interno del Servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
