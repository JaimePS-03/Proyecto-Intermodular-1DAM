package modelo;
import java.sql.Timestamp;

public class Reserva {

    // Atributos de la clase Reserva
    private int id; // Identificador de la Reserva
    private int nPersonas; // Cantidad de personas para la Reserva
    private String tipoReserva; // Tipo de Reserva (cumpleaños, cena...)
    private Timestamp fecha; // Fecha en la que cae la Reserva
    private String idCliente; // ID del Cliente que realiza la Reserva

    /**
     * Constructor vacío de Reserva
     */
    public Reserva() {
    }

    /**
     * Constructor con los parámetros de Reserva
     * @param id Identificador único de Reserva
     * @param nPersonas Cantidad de Personas
     * @param tipoReserva Tipo de reserva (Cena, Comida, cumpleaños, comunion...)
     * @param fecha Fecha de la reserva
     * @param idCliente ID del Cliente que realiza la reserva
     */
    public Reserva(int id, int nPersonas, String tipoReserva, Timestamp fecha, String idCliente) {
        this.id = id;
        this.nPersonas = nPersonas;
        this.tipoReserva = tipoReserva;
        this.fecha = fecha;
        this.idCliente = idCliente;
    }

    // Getters y Setters de los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getnPersonas() {
        return nPersonas;
    }

    public void setnPersonas(int nPersonas) {
        this.nPersonas = nPersonas;
    }

    public String getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    // ToString reescrito para coincidir con los parámetros de la clase
    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", nPersonas=" + nPersonas +
                ", tipoReserva='" + tipoReserva + '\'' +
                ", fecha=" + fecha +
                ", idCliente='" + idCliente + '\'' +
                '}';
    }
}
