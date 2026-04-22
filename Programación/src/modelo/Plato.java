package modelo;

public class Plato {

    // Atributos de la clase Plato
    private String id; // Identificador del Plato (ÚNICO)
    private String nombre; // Nombre del Plato
    private double precio; // Precio del Plato
    private String tipo; // Tipo de Plato (Usado para los diferentes filtros (arroces, carnes..)

    /**
     * Constructor vacío de Plato
     */
    public Plato() {
    }

    /**
     * Constructor con los atributos
     * @param nombrePlato Identificador del plato
     * @param nombre Nombre del plato
     * @param precio Precio del plato
     * @param tipo Tipo de plato
     */
    public Plato(String nombrePlato, String nombre, double precio, String tipo) {
        this.id = nombrePlato;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    // Getters y Setters de los atributos
    public String getId() {
        return id;
    }

    public void setId(String nombrePlato) {
        this.id = nombrePlato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // ToString reescrito para coincidir con los parámetros de la clase
    @Override
    public String toString() {
        return "Plato{" +
                "nombrePlato='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
