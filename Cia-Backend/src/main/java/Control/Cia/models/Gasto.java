package Control.Cia.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data // Anotación de Lombok para no tener que escribir los métodos getter, setter, equals, hashCode y toString
@NoArgsConstructor // Crea un constructor sin argumentos
@AllArgsConstructor // Crea un constructor con todos los argumentos
@ToString // Genera automáticamente el método toString()

public class Gasto extends PadreGastoyIngreso {
    @Id
    Long idGasto; // Identificador único del Gasto

    @Override
    public void setTipoPartida(char tipoPartida) {
        super.setTipoPartida('E');
    }
}
