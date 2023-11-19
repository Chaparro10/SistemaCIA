package Control.Cia.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DataInvalida extends RuntimeException{
    public DataInvalida(String mensaje){
        super(mensaje);
    }
}
