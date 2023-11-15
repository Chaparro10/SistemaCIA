package Control.Cia.repository;

import Control.Cia.models.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Indica que esta interfaz es un componente de repositorio gestionado por Spring.
@Repository
// Extiende JpaRepository, proporcionando operaciones básicas de CRUD para la entidad Gasto.
public interface IGastoRepositorio extends JpaRepository<Gasto, Long> {
}

