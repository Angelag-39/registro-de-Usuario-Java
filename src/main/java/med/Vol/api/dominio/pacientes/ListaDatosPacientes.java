package med.Vol.api.dominio.pacientes;

public record ListaDatosPacientes(Long id,String nombre, String telefono, String enfermedad) {
    public ListaDatosPacientes(Paciente paciente){
        this( paciente.getId(), paciente.getNombre(), paciente.getTelefono(), paciente.getEnfermedad());
    }
}
