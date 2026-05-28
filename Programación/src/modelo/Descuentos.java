package modelo;

public class Descuentos {
    // Atributos de la clase
    private String codigo;
    private String idCliente;
    private int reserva;
    private String codigoDescuento;

    // Constructor de la clase


    public Descuentos(String codigo, String idCliente, int reserva, String codigoDescuento) {
        this.codigo = codigo;
        this.idCliente = idCliente;
        this.reserva = reserva;
        this.codigoDescuento = codigoDescuento;
    }

    // Getters y Setters de la clase


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

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

    @Override
    public String toString() {
        return "Descuentos{" +
                "codigo='" + codigo + '\'' +
                ", idCliente='" + idCliente + '\'' +
                ", reserva=" + reserva +
                ", codigoDescuento='" + codigoDescuento + '\'' +
                '}';
    }
}
