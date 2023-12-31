package Control.Cia.service;
import Control.Cia.models.Ingreso;
import Control.Cia.models.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface IUsuarioServicio {

  /**
   * Método para registrar un usuario a partir de un mapa de datos y devolver una ResponseEntity.
   *
   * @param requestMap Mapa que contiene los datos del usuario (nombre, email, password, etc.).
   * @return ResponseEntity<String> que representa la respuesta del registro del usuario.
   */

  public List<Usuario> ListarUsuarios();
  ResponseEntity<String> signUp(Map<String, String> requestMap);

  ResponseEntity<String> Login(Map<String,String> requestMap);
}
