package Control.Cia.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ingreso extends PadreGastoyIngreso {
    @Id
    Long idIngreso; // Identificador único del Ingreso

    @Override
    public void setTipoPartida(char tipoPartida) {
        super.setTipoPartida('I');
    }
}
