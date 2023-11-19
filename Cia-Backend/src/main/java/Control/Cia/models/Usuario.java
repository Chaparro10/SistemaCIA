package Control.Cia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



/**
 * Clase que representa la entidad Usuario en la base de datos.
 */
@Data // Anotación de Lombok para no tener que escribir los métodos getter, setter, equals, hashCode y toString
@NoArgsConstructor // Crea un constructor sin argumentos
@AllArgsConstructor // Crea un constructor con todos los argumentos
@ToString // Genera automáticamente el método toString()
@Entity // Anotación que indica que esta clase es una entidad JPA
@Table(name="usuario") // Especifica el nombre de la tabla en la base de datos
public class Usuario {

    @Id // Anotación que marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática de valores de clave primaria usando identidad de base de datos
    private Long idusuario;

    private String email;

    @Column(name = "password") // Especifica el nombre de la columna en la base de datos (opción 1)
    private String password;

    private String Nombre; // Nombre de usuario (opción 2)
    private String role; // Rol del usuario en el sistema
}
