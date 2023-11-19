package Control.Cia.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada que representa un error en los datos proporcionados.
 * Devuelve un código de estado HTTP 400 (BAD_REQUEST) cuando se produce.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DataInvalida extends RuntimeException {

    /**
     * Constructor de la clase que recibe un mensaje de error.
     *
     * @param mensaje Mensaje descriptivo del error.
     */
    public DataInvalida(String mensaje) {
        super(mensaje);
    }
}

