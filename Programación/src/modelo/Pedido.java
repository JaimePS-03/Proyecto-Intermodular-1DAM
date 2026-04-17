package modelo;
import java.sql.Timestamp;

public class Pedido {

    // Atributos de la clase Pedido
    private int id; // Identificador del Pedido (ÚNICO)
    private Timestamp hora; // Hora en la que se hace el pedido
    private int nMesa; // Mesa asociada a los Pedidos

    /**
     * Constructor vacío de Pedido
     */
    public Pedido() {
    }


    /**
     * Constructor con los parámetros de Pedido
     * @param id Identificador único de Pedido
     * @param hora Hora del Pedido
     * @param nMesa Mesa en la que estan los pedidos
     */
    public Pedido(int id, Timestamp hora, int nMesa) {
        this.id = id;
        this.hora = hora;
        this.nMesa = nMesa;
    }

    // Getters y Setters de los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }

    public int getnMesa() {
        return nMesa;
    }

    public void setnMesa(int nMesa) {
        this.nMesa = nMesa;
    }

    // ToString reescrito para coincidir con los parámetros de la clase
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", hora=" + hora +
                ", nMesa=" + nMesa +
                '}';
    }
}
