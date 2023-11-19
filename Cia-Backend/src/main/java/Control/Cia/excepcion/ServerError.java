package Control.Cia.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada que representa un error interno del servidor.
 * Devuelve un código de estado HTTP 500 (INTERNAL_SERVER_ERROR) cuando se produce.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerError extends RuntimeException {

    /**
     * Constructor de la clase que recibe un mensaje de error.
     *
     * @param mensaje Mensaje descriptivo del error.
     */
    public ServerError(String mensaje) {
        super(mensaje);
    }
}
