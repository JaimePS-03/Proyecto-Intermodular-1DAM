package modelo;

public class Camarero extends Empleado{
    // Atributos propios de la clase
    private int anoServicio;
    private String dniEmpleado;
    private String nZona;
    private String camareroJefe;

    // Constructor de la clase.
    public Camarero(String dni, String nombre, String apellidos, String telefono, String email, String NUSS, String colectivo, int anoServicio, String nZona, String camareroJefe) {
        super(dni, nombre, apellidos, telefono, email, NUSS, colectivo);
        this.anoServicio = anoServicio;
        this.dniEmpleado = dni;
        this.nZona = nZona;
        this.camareroJefe = camareroJefe;
    }

    // Getters y Setters de los atributos
    public int getAnoServicio() {
        return anoServicio;
    }

    public void setAnoServicio(int anoServicio) {
        this.anoServicio = anoServicio;
    }

    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    public String getnZona() {
        return nZona;
    }

    public void setnZona(String nZona) {
        this.nZona = nZona;
    }

    public String getCamareroJefe() {
        return camareroJefe;
    }

    public void setCamareroJefe(String camareroJefe) {
        this.camareroJefe = camareroJefe;
    }

    // ToString modificado para que coincida con los atributos
    @Override
    public String toString() {
        return super.toString() + "Camareros{" +
                "anoServicio=" + anoServicio +
                ", dniEmpleado='" + dniEmpleado + '\'' +
                ", nZona='" + nZona + '\'' +
                ", camareroJefe='" + camareroJefe + '\'' +
                '}';
    }
}
