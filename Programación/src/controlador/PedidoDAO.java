package controlador;

import modelo.Pedido;
import util.EntradaTexto;

import java.sql.Timestamp;
import java.util.ArrayList;

public class PedidoDAO {

    //ArrayList con los Pedidos.
    private static ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

    private static int nextId = 1;

    /**
     * Constructor del Controlador de Pedidos
     */
    public PedidoDAO(){
    }

    /**
     * Nos permite controlar los ID únicos del ArrayList
     * @return Devuelve el ID a asignar
     */
    public int generarId(){
        int id = nextId;
        nextId++;
        return id;
    }

    /**
     * Nos permite insertar en el ArrayList pedidos
     * @param p Pedido a añadir
     */
    public void insertar(Pedido p){ pedidos.add(p); }

    /**
     * Nos permite mostrar todos los pedidos
     */
    public String listar() {
        String texto = "";

        for (Pedido p : pedidos) {
            texto = texto + p + "\n";
        }

        return texto;
    }

    /**
     * Nos permite buscar un pedido por su ID
     * @param id ID del pedido a buscar
     * @return Si existe el pedido, la devuelve. Si no, devuelve false
     */
    public Pedido buscarPorId(int id){
        for(Pedido p: pedidos){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    /**
     * Nos permite eliminar un pedido
     * @param id Pedido a eliminar
     * @return Nos devuelve true si lo elimina o false si no se encuentra
     */
    public boolean eliminar(int id){
        if(buscarPorId(id) == null){
            return false;
        }else{
            pedidos.remove(buscarPorId(id));
            return true;
        }
    }

    /**
     * Nos permite actualizar la información de un pedido
     * @param id ID del pedido a actualizar
     * @param p Información del pedido actualizado
     */
    public void actualizar(int id, Pedido p){
        Pedido existente = buscarPorId(id);
        if(existente != null){
            int index = pedidos.indexOf(existente);
            pedidos.set(index, p);
        }
    }

    /**
     * Nos permite crear pedidos
     * @return Nos devuelve un pedido creado
     */
    public Pedido generarPedido(){
        int nMesa = EntradaTexto.pedirIntRango("Dame el numero de mesa del pedido", 1, 12);
        return new Pedido(generarId(), new Timestamp(System.currentTimeMillis()), nMesa);
    }

    public void menu(){
        System.out.println("----- PEDIDOS -----\n" +
                "1) Listar pedidos\n" +
                "2) Nuevo pedido\n" +
                "3) Buscar pedido\n" +
                "4) Eliminar pedido\n" +
                "5) Actualizar pedido\n" +
                "0) Volver");
    }




}
