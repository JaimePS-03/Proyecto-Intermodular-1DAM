package modelo;

public class Cocinero extends Empleado{
    // Atributos propios de la clase
    private String especialidad;
    private String dni_empleado;

    // Constructor de la clase.
    public Cocinero(String dni, String nombre, String apellidos, String telefono, String email, String NUSS, String colectivo, String especialidad) {
        super(dni, nombre, apellidos, telefono, email, NUSS, colectivo);
        this.especialidad = especialidad;
        this.dni_empleado = dni;
    }

    // Getters y Setters de los atributos
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDni_empleado() {
        return dni_empleado;
    }

    public void setDni_empleado(String dni_empleado) {
        this.dni_empleado = dni_empleado;
    }

    // ToString modificado para que coincida con los atributos
    @Override
    public String toString() {
        return super.toString() + "Cocinero{" +
                "especialidad='" + especialidad + '\'' +
                ", dni_empleado='" + dni_empleado + '\'' +
                '}';
    }
}
