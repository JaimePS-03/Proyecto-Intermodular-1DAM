package modelo;

public class Empleado extends Usuario{
    // Atributos propios de la clase
    private String NUSS;
    private String colectivo;

    // Constructor de la clase.
    public Empleado(String dni, String nombre, String apellidos, String telefono, String email, String NUSS, String colectivo) {
        super(dni, nombre, apellidos, telefono, email);
        this.NUSS = NUSS;
        this.colectivo = colectivo;
    }

    // Getters y Setters de los atributos
    public String getNUSS() {
        return NUSS;
    }

    public void setNUSS(String NUSS) {
        this.NUSS = NUSS;
    }

    public String getColectivo() {
        return colectivo;
    }

    public void setColectivo(String colectivo) {
        this.colectivo = colectivo;
    }

    // ToString modificado para que coincida con los atributos
    @Override
    public String toString() {
        return super.toString() + "Empleado{" +
                "NUSS='" + NUSS + '\'' +
                ", colectivo='" + colectivo + '\'' +
                '}';
    }
}
