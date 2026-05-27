package modelo;

public class Ingredientes {
    /**
     * Atributos de la clase. Nombre, tipo y existencias
     */
    private String nombre;
    private String tipo;
    private int existencias;

    /**
     * Constructor con los parámetros
     * @param nombre Nombre del plato
     * @param tipo Tipo de plato
     * @param existencias Cuanto del plato nos queda
     */
    public Ingredientes(String nombre, String tipo, int existencias) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.existencias = existencias;
    }

    // Getters y Setters de la clase Ingredientes
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    // ToString sobreescrito para conincidir con los atributos de la clase
    @Override
    public String toString() {
        return "Ingredientes{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", existencias=" + existencias +
                '}';
    }
}
