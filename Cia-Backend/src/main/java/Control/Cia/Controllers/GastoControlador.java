package Control.Cia.Controllers;

import Control.Cia.service.GastoServicio;
import Control.Cia.service.IGastoServicio;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

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
    // LoggerFactory es una utilidad para obtener instancias de logger.
    private static final Logger logger = (Logger) LoggerFactory.getLogger(GastoControlador.class);

    @Autowired
    // La anotación Autowired se utiliza para realizar la inyección de dependencias.
    // En este caso, se inyecta una instancia de la interfaz IGastoServicio en esta clase.
    private IGastoServicio gastoServicio;


}
