package Control.Cia.service;
import org.springframework.http.ResponseEntity;

import java.util.Map;


public interface IUsuarioServicio {

  /**
   * Método para registrar un usuario a partir de un mapa de datos y devolver una ResponseEntity.
   *
   * @param requestMap Mapa que contiene los datos del usuario (nombre, email, password, etc.).
   * @return ResponseEntity<String> que representa la respuesta del registro del usuario.
   */
  ResponseEntity<String> signUp(Map<String, String> requestMap);

}
