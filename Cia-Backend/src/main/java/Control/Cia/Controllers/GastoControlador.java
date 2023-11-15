package Control.Cia.Controllers;

import Control.Cia.models.Gasto;
import Control.Cia.service.IGastoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// La anotación RestController indica que esta clase es un controlador de Spring que maneja solicitudes REST.
// En otras palabras, se utilizará para manejar peticiones HTTP y devolverá objetos como respuesta.
@RequestMapping("cia-app")
// La anotación RequestMapping se utiliza para asignar solicitudes web a métodos específicos de controlador o a clases de controlador.
// En este caso, todas las solicitudes que comienzan con "/cia-app" serán manejadas por este controlador.
@CrossOrigin(value = "http://localhost:3000")
// La anotación CrossOrigin se utiliza para permitir solicitudes desde un dominio diferente al dominio del servidor.
// En este caso, se permite la conexión desde "http://localhost:3000" (puerto típico de desarrollo de aplicaciones front-end).
public class GastoControlador {
   
    private static final Logger logger = LoggerFactory.getLogger(GastoControlador.class);//Para mandar informacion a consola

    @Autowired
    // La anotación Autowired se utiliza para realizar la inyección de dependencias.
    // En este caso, se inyecta una instancia de la interfaz IGastoServicio en esta clase.
    private IGastoServicio gastoServicio;

    //http://localhost:8080/cia-app/Gastos
    @GetMapping("/Gastos")
    public List<Gasto> obtenerGastos(){
        var gastos=gastoServicio.ListarGastos();
        gastos.forEach(gasto ->logger.info(gasto.toString()));//Para mandar la informacion a la consola
        return gastos;
    }

}
