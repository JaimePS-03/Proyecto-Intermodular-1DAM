package modelo;

public class Cliente {

    // Atributos de la clase Cliente

    private String id; // Identificador del Cliente (ÚNICO)
    private String nombre; // Nombre del Cliente
    private String apellidos; // Apellidos del Cliente
    private String telefono; // Teléfono del Cliente
    private String email; // Email del Cliente

    /**
     * Constructor vacío de Cliente
     */
    public Cliente() {
    }

    /**
     * Constructor con los parámetros de Cliente
     * @param id Identificador Único de Cliente
     * @param nombre Nombre del Cliente
     * @param apellidos Apellidos del Cliente
     * @param telefono Telefono del Cliente
     * @param email Email del Cliente
     */
    public Cliente(String id, String nombre, String apellidos, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y Setters de los parámetros de Cliente
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ToString reescrito para coincidir con los parámetros de la clase
    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
