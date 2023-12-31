package Control.Cia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Entity
@Data // Anotación de Lombok para no tener que escribir los métodos getter, setter, equals, hashCode y toString
 @NoArgsConstructor// Crea un constructor sin argumentos
@AllArgsConstructor // Crea un constructor con todos los argumentos
@ToString // Genera automáticamente el método toString()

@Table(name = "Gasto")
public class Gasto extends PadreGastoyIngreso {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idGasto; // Identificador único del Gasto

}
