package Control.Cia.repository;

import Control.Cia.models.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Indica que esta interfaz es un componente de repositorio gestionado por Spring.
@Repository
// Extiende JpaRepository, proporcionando operaciones básicas de CRUD para la entidad Ingreso.
public interface IIngresoRepository extends JpaRepository<Ingreso,Long> {
}
