package modelo;

public class Jefe extends Usuario{

    // Constructor de la clase
    public Jefe(String dni, String nombre, String apellidos, String telefono, String email) {
        super(dni, nombre, apellidos, telefono, email);
    }

    // ToString modificado para que coincida con los atributos
    @Override
    public String toString() {
        return super.toString() + "Jefe{}";
    }
}
