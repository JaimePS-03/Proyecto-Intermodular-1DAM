package modelo;

public class Mesa {

    // Atributos de la clase Mesa
    private int nMesa; // Identificador de la Mesa (ÚNICO)
    private int nPersonas; // Cantidad de personas que pueden ocupar la mesa

    /**
     * Constructor vacío de Mesa
     */
    public Mesa() {
    }

    /**
     * Constructor con los parámetros de Mesa
     * @param nMesa Identificador Único de Mesa
     * @param nPersonas Cantidad de Personas en la mesa
     */
    public Mesa(int nMesa, int nPersonas) {
        this.nMesa = nMesa;
        this.nPersonas = nPersonas;
    }

    // Getters y Setters de los parámetros de Mesa
    public int getnMesa() {
        return nMesa;
    }

    public void setnMesa(int nMesa) {
        this.nMesa = nMesa;
    }

    public int getnPersonas() {
        return nPersonas;
    }

    public void setnPersonas(int nPersonas) {
        this.nPersonas = nPersonas;
    }

    // ToString reescrito para coincidir con los parámetros de la clase
    @Override
    public String toString() {
        return "Mesa{" +
                "nMesa=" + nMesa +
                ", nPersonas=" + nPersonas +
                '}';
    }
}
