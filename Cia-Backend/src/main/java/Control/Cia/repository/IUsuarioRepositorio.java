package Control.Cia.repository;

import Control.Cia.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Interfaz de repositorio para la entidad Usuario. Extiende JpaRepository para operaciones CRUD básicas.
 */
@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {

    /**
     * Método de consulta para buscar un usuario por su dirección de correo electrónico.
     * @param email Dirección de correo electrónico del usuario a buscar.
     * @return Objeto Usuario que coincide con la dirección de correo electrónico proporcionada.
     */
    Usuario findByEmail(String email);
}

