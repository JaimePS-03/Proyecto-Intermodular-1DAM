package modelo;

public class Bartender extends Empleado{
    // Atributos propios de la clase
    private String dniEmpleado;

    // Constructor de la clase.
    public Bartender(String dni, String nombre, String apellidos, String telefono, String email, String NUSS, String colectivo) {
        super(dni, nombre, apellidos, telefono, email, NUSS, colectivo);
        this.dniEmpleado = dni;
    }

    // Getters y Setters de los atributos
    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    // ToString modificado para que coincida con los atributos
    @Override
    public String toString() {
        return super.toString() + "Bartenders{" +
                "dniEmpleado='" + dniEmpleado + '\'' +
                '}';
    }
}
