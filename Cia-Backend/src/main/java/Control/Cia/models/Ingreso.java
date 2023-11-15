package Control.Cia.models;


import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idIngreso; // Identificador único del Ingreso


}
