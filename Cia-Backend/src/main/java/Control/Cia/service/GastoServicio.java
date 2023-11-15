package Control.Cia.service;

import Control.Cia.models.Gasto;
import Control.Cia.repository.IGastoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// Indica que esta clase es un componente de servicio gestionado por Spring.
@Service
public class GastoServicio implements IGastoServicio {

    // Inyección de dependencia del repositorio de Gastos.
    @Autowired
    private IGastoRepositorio gastoRepositorio;

    // Implementación del método para listar todos los gastos.
    @Override
    public List<Gasto> ListarGastos() {
        return gastoRepositorio.findAll(); // Utiliza el método findAll() del repositorio para obtener todos los gastos.
    }

    // Implementación del método para buscar un gasto por su ID.
    @Override
    public Gasto buscarGastoPorId(Long idGasto) {
        // Utiliza el método findById() del repositorio para buscar un gasto por su ID.
        // Si no se encuentra, devuelve null.
        Gasto gasto = gastoRepositorio.findById(idGasto).orElse(null);
        return gasto;
    }

    // Implementación del método para guardar un gasto.
    @Override
    public Gasto guardarGasto(Gasto gasto) {
        // Utiliza el método save() del repositorio para guardar un nuevo gasto o actualizar uno existente.
        return gastoRepositorio.save(gasto);
    }

    // Implementación del método para eliminar un gasto.
    @Override
    public void eliminarGasto(Gasto gasto) {
        // Utiliza el método delete() del repositorio para eliminar un gasto.
        gastoRepositorio.delete(gasto);
    }
}

