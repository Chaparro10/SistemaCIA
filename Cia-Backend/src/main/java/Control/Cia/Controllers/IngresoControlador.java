package Control.Cia.Controllers;

import Control.Cia.models.Ingreso;
import Control.Cia.service.IIngresoServicio;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cia-app")
@CrossOrigin(value = "http://localhost:3000")//Comunicacion con el front

public class IngresoControlador {
    private static final Logger logger = LoggerFactory.getLogger(GastoControlador.class);//Para mandar informacion a consola

    @Autowired//Inyeccion de Depencias
    private IIngresoServicio ingresoServicio;


    //METODO PARA MOSTRAR INGRESOS
    //http://localhost:8080/cia-app/Ingreso
    @GetMapping("/Ingreso")
    @Secured("ROLE_USER")
    public List<Ingreso> obtenerIngresos(){
        var ingresos=ingresoServicio.ListarIngresos();
        return ingresos;
    }


}
