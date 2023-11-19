package Control.Cia.service;
import org.springframework.http.ResponseEntity;

import java.util.Map;


public interface IUsuarioServicio {

  ResponseEntity<String> signUp(Map<String,String> requestMap);
}
