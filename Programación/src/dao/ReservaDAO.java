package dao;

import modelo.Reserva;
import util.EntradaTexto;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ReservaDAO {

    // ArrayList con las Reservas. Nos permite manejar las Reservas de forma dinámica
    private static ArrayList<Reserva> reservas = new ArrayList<>();

    // Controlador de las ID para que no se repitan
    private static int nextId = 4;

    /**
     * Constructor del Controlador de Reservas
     */
    public ReservaDAO() {
    }

    /**
     * Nos permite insertar las Reservas al ArrayList de reservas, ya que es privada
     * @param r Reserva que añadimos al ArrayList
     */
    public void insertar(Reserva r){
        reservas.add(r);
    }

    /**
     * Nos permite listar las Reservas que están en el ArrayList
     */
    public String listar() {
        String texto = "";

        for (Reserva r : reservas) {
            texto = texto + r + "\n";
        }

        return texto;
    }

    /**
     * Nos permite buscar las Reservas por su ID
     * @param id El Id de la reserva para buscarlo
     * @return Si encuentra la reserva, nos devuelve la reserva. Si no, nos devuelve null
     */
    public Reserva buscarPorId(int id){
        for(Reserva r : reservas){
            if(r.getId() == id){
                return r;
            }
        }
        return null;
    }

    /**
     * Nos permite borrar una Reserva del ArrayList
     * @param id Id de la Reserva que vamos a buscar
     * @return Si no lo encuentra, nos devuelve false. Si lo encuentra, lo borra del ArrayList y devuelve true
     */
    public boolean eliminar(int id){
        if(buscarPorId(id) == null){
            return false;
        }else{
            reservas.remove(buscarPorId(id));
            return true;
        }
    }

    /**
     * Nos permite actualizar la información de una Reserva. Si encuentra la reserva, guarda el índice de la reserva para sobreescribir los datos luego.
     * @param idBuscado ID de la Reserva que vamos a Actualizar
     * @param rActualizada Información actualizada de la Reserva (pasada por objeto)
     */
    public void actualizar(int idBuscado, Reserva rActualizada){
        Reserva existente = buscarPorId(idBuscado);
        if (existente != null){
            int index = reservas.indexOf(existente);
            reservas.set(index, rActualizada);
        }
    }

    /**
     * Nos permite generar un id para la reserva
     * @return El id de la reserva
     */
    public int generarId(){
        return nextId++;
    }

    /**
     * Nos devuelve un ArrayList con las Reservas que haya hecho un cliente
     * @param idCliente Id del cliente a buscar las reservas
     * @return Si tiene, devuelve un ArrayList con las reservas, si no encuentra, devuelve null
     */
    public ArrayList<Reserva> listarPorCliente(String idCliente) {
        ArrayList<Reserva> reservaPorCliente = new ArrayList<>();

        for (Reserva r : reservas) {
            if (r.getIdCliente().equals(idCliente)) {
                reservaPorCliente.add(r);
            }
        }

        if (reservaPorCliente.isEmpty()) {
            return null;
        } else {
            return reservaPorCliente;
        }
    }

    static{
        reservas.add(new Reserva(1, 4, "Cena familiar", new Timestamp(System.currentTimeMillis()), "C001"));
        reservas.add(new Reserva(2, 4, "Cumpleaños", new Timestamp(System.currentTimeMillis()), "C001"));
        reservas.add(new Reserva(3, 4, "Cena familiar", new Timestamp(System.currentTimeMillis()), "C002"));
    }

    /**
     * Nos permite crear una reserva para devolverla
     * @param idCliente ID del cliente que realiza la reserva
     * @return Reserva creada
     */
    public Reserva generarReserva(String idCliente){
        int personas = EntradaTexto.pedirIntRango("Personas [1-12]", 1, 12);
        String tipo = EntradaTexto.pedirString("Tipo de reserva");

        return new Reserva(generarId(), personas, tipo, new Timestamp(System.currentTimeMillis()), idCliente);
    }

    /**
     * Nos permite mostrar el submenú para manejar las reservas
     */
    public void menu(){
        System.out.println("---- RESERVAS ----\n" +
                "1) Listar reservas\n" +
                "2) Nueva reserva\n" +
                "3) Buscar reservas por cliente\n" +
                "4) Eliminar reserva\n" +
                "5) Actualizar platos\n" +
                "0) Volver");
    }
    
 // Método para devolver el Array para la tabla de la Ventana de Reservas
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
}
