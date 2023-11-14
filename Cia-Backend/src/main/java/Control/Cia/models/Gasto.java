package Control.Cia.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate; // Importa la clase LocalDate para representar fechas
import java.math.BigDecimal; // Importa la clase BigDecimal para trabajar con valores monetarios

@Entity
@Data // Anotación de Lombok para no tener que escribir los métodos getter, setter, equals, hashCode y toString
@NoArgsConstructor // Crea un constructor sin argumentos
@AllArgsConstructor // Crea un constructor con todos los argumentos
@ToString // Genera automáticamente el método toString()
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idGasto; // Identificador único del gasto
    LocalDate fecha; // Fecha del gasto
    String beneficiario; // Beneficiario del gasto
    String descripcion; // Descripción del gasto
    BigDecimal monto; // Monto del gasto, utilizando BigDecimal para precisión en valores monetarios
}
