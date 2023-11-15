package Control.Cia.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Ingreso")
public class Ingreso extends PadreGastoyIngreso {
    @Id
    Long idIngreso; // Identificador único del Ingreso


}
