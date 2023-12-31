package med.Vol.api.dominio.pacientes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.Vol.api.dominio.direccion.Direccion;

@Table(name = "pacientes")    //con eso estoy diciendo que en el banco de datos hay una tabela llamada "medicos"
@Entity(name = "Paciente")

@Getter                     //forma de generar automaticamente los getters de la clase
@NoArgsConstructor          //forma de generar los consstructores sin argumento(default) que o JPA exige, de la clase
@AllArgsConstructor         //forma de generar los consstructores que reciben todos los campos de la clase
@EqualsAndHashCode(of="id") // para generar los HasCode encima del id, no encima de todos los atributos
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String nombre;
    private String correo;
    private String telefono;
    private String enfermedad;
    @Embedded
    private Direccion direccion;
    private boolean activo;

    public Paciente(DatosRegistroPacientes datos) {
        this.activo = true;
        this.nombre = datos.nombre();
        this.correo = datos.correo();
        this.enfermedad = datos.enfermedad();
        this.telefono = datos.telefono();
        this.direccion = new Direccion(datos.direccion());
    }

    public void atualizarInformaciones(DatosActualizaPacientes datos) {
        if (datos.nombre() != null) { // si el recurso nombre fuera actualiado se Renombrara
            this.nombre = datos.nombre();
        }
        if (datos.telefono() != null) {
            this.telefono = datos.telefono();
        }
        // por el hecho de Direccion ser una clase y no un recurso, tenemos que crear
        // un metodo que altere el recurso de esa clase
        if (datos.direccion() != null) {
            this.direccion.actualizacionInformacion(datos.direccion());
        }

}
public void borrar() {
        this.activo = false;
    }
}