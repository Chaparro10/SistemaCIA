package Control.Cia.service;

import Control.Cia.models.Ingreso;

import java.util.List;

public interface IIngresoServicio {
    public List<Ingreso> ListarIngresos();
    public Ingreso buscarIngresoPorId(Long idIngreso);

    public Ingreso guardarIngreso(Ingreso ingreso);

    public void eliminarIngreso(Ingreso ingreso);

}
