package Control.Cia.service;

import Control.Cia.models.Gasto;
import Control.Cia.models.Ingreso;
import Control.Cia.repository.IGastoRepositorio;
import Control.Cia.repository.IIngresoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IngresoServicio implements IIngresoServicio{

    // Inyección de dependencia del repositorio de Gastos.
    @Autowired
    private IIngresoRepository ingresoRepository;
    @Override
    public List<Ingreso> ListarIngresos() {
        return ingresoRepository.findAll();
    }

    @Override
    public Ingreso buscarIngresoPorId(Long idIngreso) {
        Ingreso ingreso = ingresoRepository.findById(idIngreso).orElse(null);
        return ingreso;
    }

    @Override
    public Ingreso guardarIngreso(Ingreso ingreso) {
        return ingresoRepository.save(ingreso);
    }

    @Override
    public void eliminarIngreso(Ingreso ingreso) {
            ingresoRepository.delete(ingreso);
    }
}
