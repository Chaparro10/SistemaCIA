package Control.Cia.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FacturaUtils {

    /**
     * Método estático que construye y devuelve un ResponseEntity<String>.
     *
     * @param mensaje     El mensaje que se incluirá en la respuesta.
     * @param httpStatus  El código de estado HTTP que se utilizará en la respuesta.
     * @return ResponseEntity<String> que encapsula el mensaje y el código de estado proporcionados.
     */
    public static ResponseEntity<String> getResponseEntity(String mensaje, HttpStatus httpStatus) {
        // Crea un nuevo ResponseEntity con el mensaje y el HttpStatus dados, y lo devuelve
        return new ResponseEntity<String>("mensaje:" + mensaje, httpStatus);
    }

}
