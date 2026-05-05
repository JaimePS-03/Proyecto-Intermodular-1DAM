package modelo;

public class Descuentos {
    // Atributos de la clase
    private String idCliente;
    private int reserva;
    private String codigoDescuento;

    // Constructor de la clase
    public Descuentos(String idCliente, int reserva, String codigoDescuento) {
        this.idCliente = idCliente;
        this.reserva = reserva;
        this.codigoDescuento = codigoDescuento;
    }

    // Getters y Setters de la clase
    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public int getReserva() {
        return reserva;
    }

    public void setReserva(int reserva) {
        this.reserva = reserva;
    }

    public String getCodigoDescuento() {
        return codigoDescuento;
    }

    public void setCodigoDescuento(String codigoDescuento) {
        this.codigoDescuento = codigoDescuento;
    }

    // ToString adaptado a la clase
    @Override
    public String toString() {
        return "Descuentos{" +
                "idCliente='" + idCliente + '\'' +
                ", reserva=" + reserva +
                ", codigoDescuento='" + codigoDescuento + '\'' +
                '}';
    }
}
