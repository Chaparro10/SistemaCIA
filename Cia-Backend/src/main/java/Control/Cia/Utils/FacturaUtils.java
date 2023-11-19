package Control.Cia.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FacturaUtils {

    // Método estático que devuelve un ResponseEntity<String>
    public static ResponseEntity<String> getResponseEntity(String mensaje, HttpStatus httpStatus){
        // Crea y devuelve un nuevo ResponseEntity con el mensaje proporcionado y el HttpStatus dado
        return new ResponseEntity<String>("mensaje:" + mensaje, httpStatus);
    }
}
