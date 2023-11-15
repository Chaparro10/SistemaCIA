package Control.Cia.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data // Anotación de Lombok para no tener que escribir los métodos getter, setter, equals, hashCode y toString
@NoArgsConstructor // Crea un constructor sin argumentos
@AllArgsConstructor // Crea un constructor con todos los argumentos
@ToString // Genera automáticamente el método toString()

@MappedSuperclass
public abstract  class PadreGastoyIngreso {

    LocalDate fecha; // Fecha del gasto
    String beneficiario; // Beneficiario del gasto
    String descripcion; // Descripción del gasto
    BigDecimal monto; // Monto del gasto, utilizando BigDecimal para precisión en valores monetarios
}
