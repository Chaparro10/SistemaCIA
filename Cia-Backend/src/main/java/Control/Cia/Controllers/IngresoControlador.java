package Control.Cia.Controllers;

import Control.Cia.models.Ingreso;
import Control.Cia.service.IIngresoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Ingreso> obtenerIngresos(){
        var ingresos=ingresoServicio.ListarIngresos();
        return ingresos;
    }
}
