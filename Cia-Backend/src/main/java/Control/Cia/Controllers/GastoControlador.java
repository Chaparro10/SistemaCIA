package Control.Cia.Controllers;

import Control.Cia.excepcion.RecursoNoEncontradoExcepcion;
import Control.Cia.models.Gasto;
import Control.Cia.service.GastoServicio;
import Control.Cia.service.IGastoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //METODO PARA AGREGAR GASTOS
    //http://localhost:8080/cia-app/Gastos
    @PostMapping("/Gastos")
    public Gasto agregarGasto(@RequestBody Gasto gasto){
        logger.info("Gasto a agregar: "+ gasto);
        return gastoServicio.guardarGasto(gasto);
    }


    //METODO PARA ACTUALIZAR
    //http://localhost:8080/cia-app/Gastos/id
    @PutMapping("/Gastos/{id}")
    public ResponseEntity<Gasto> actualizarGasto(@PathVariable Long id, @RequestBody Gasto gastoRecibido) {
        Gasto gasto = gastoServicio.buscarGastoPorId(id);
        if (gasto == null) {
            throw new RecursoNoEncontradoExcepcion("El id no existe: " + id);
        }
        // Actualizar los campos de los Gastos con la información recibida en el cuerpo de la solicitud.
        gasto.setBeneficiario(gastoRecibido.getBeneficiario());
        gasto.setDescripcion(gastoRecibido.getDescripcion());
        gasto.setMonto(gastoRecibido.getMonto());
        gasto.setFecha(gastoRecibido.getFecha());

        gasto = gastoServicio.guardarGasto(gasto);

        return ResponseEntity.ok(gasto);
    }

    //BUSQUEDASSSSSS
    //METODO BUSCAR GASTOS POR ID
    @GetMapping("/Gastos/{id}")
// La anotación @GetMapping indica que este método manejará solicitudes HTTP GET en la ruta "/Gastos/{id}".
    public ResponseEntity<Gasto> obtenerGastoPorId(@PathVariable Long id) {
        // El método recibe el valor de la variable de ruta {id} desde la URL y lo almacena en la variable "id".
        Gasto gasto = gastoServicio.buscarGastoPorId(id);
        // Llama al método "buscarGastoPorId" del servicio "gastoServicio" para buscar un gasto por su ID y almacena el resultado en la variable "gasto".
        if (gasto == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el gasto id" + id);

        return ResponseEntity.ok(gasto);
        // Si se encontró el gasto, se devuelve una respuesta HTTP exitosa (código 200) con el objeto "gasto" como contenido de la respuesta.
    }


    //METODO PARA ELIMINAR
    @DeleteMapping("/Gastos/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarGasto(@PathVariable Long id){
        Gasto gasto =gastoServicio.buscarGastoPorId(id);
        if(gasto==null) throw new RecursoNoEncontradoExcepcion("EL id no existe "+id);

        gastoServicio.eliminarGasto(gasto);

        //Json {"Eliminado ": True}
        Map<String,Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }


}
