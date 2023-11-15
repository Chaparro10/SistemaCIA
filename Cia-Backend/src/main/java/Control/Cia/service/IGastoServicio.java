package Control.Cia.service;

import Control.Cia.models.Gasto;

import java.util.List;

public interface IGastoServicio {
    public List<Gasto> ListarGastos();

    public Gasto buscarGastoPorId(Long idGasto);

    public Gasto guardarGasto(Gasto gasto);

    public void eliminarGasto(Gasto gasto);
}
