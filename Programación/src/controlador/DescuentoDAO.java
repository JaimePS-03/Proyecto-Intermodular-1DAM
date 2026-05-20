package controlador;

import modelo.Descuentos;
import util.EntradaTexto;

import java.sql.Timestamp;
import java.util.ArrayList;

public class DescuentoDAO {

    // ArrayList con los Descuentos. Nos permite manejar los Descuentos de forma dinámica
    private static ArrayList<Descuentos> descuentos = new ArrayList<>();

    // Controlador de las ID para que no se repitan
    private static int nextId = 1;

    /**
     * Constructor del Controlador de Descuentos
     */
    public DescuentoDAO() {
    }

    /**
     * Generador de ID. Este nos permite generar un ID con un formato
     * @return Nos devuelve el ID para poder usarlo con los diferentes descuentos
     */
    public String generarId() {
        return EntradaTexto.pedirString("Codigo del descuento: ") + nextId++;
    }

    /**
     * Nos permite insertar los Descuentos al ArrayList de Descuentos, ya que es privada
     * @param d Descuento que añadimos al ArrayList
     */
    public void insertar(Descuentos d){descuentos.add(d);
    }

    /**
     * Nos permite listar todos los Descuentos que están en el ArrayList
     */
    public String listar() {
        String texto = "";

        for (Descuentos a : descuentos) {
            texto = texto + a + "\n";
        }

        return texto;
    }


    /**
     * Nos permite buscar los Descuentos por su ID
     * @param id El ID del descuento al buscarlo
     * @return Si encuentra el descuento nos devuelve el Descuento seleccionado. Si no, nos devuelve null (no nos devuelve nada)
     */
    public Descuentos buscarPorId(String id){
        for(Descuentos d : descuentos){
            if(d.getCodigoDescuento().equals(id)){
                return d;
            }
        }
        return null;
    }

    /**
     * Nos permite borrar un Descuento del ArrayList
     * @param id Id del Descuento que vamos a buscar
     * @return Si no encuentra al Descuento, nos devuelve False. Si lo encuentra, lo elimina y devuelve True
     */
    public boolean eliminar(String id){
        if(buscarPorId(id) == null){
            return false;
        }else{
            descuentos.remove(buscarPorId(id));
            return true;
        }
    }

    /**
     * Nos permite actualizar la información de un descuento. Si encuentra el descuento, guarda el índice del descuento para sobreescribir los datos luego.
     * @param idBuscado ID del Descuento que vamos a actualizar los datos
     * @param aActualizado Información del descuento (Pasada por un objeto descuento) para actualizar
     */
    public void actualizar(String idBuscado, Descuentos aActualizado) {
        Descuentos existente = buscarPorId(idBuscado);
        if (existente != null) {
            int index = descuentos.indexOf(existente);
            descuentos.set(index, aActualizado);
        }
    }

    /**
     * Nos permite imprimir el submenú para controlar Descuentos
     */
    public void menu(){
        System.out.println("--- Descuentos ---");
        System.out.println("1) Listar Descuentos");
        System.out.println("2) Nuevo Descuentos");
        System.out.println("3) Buscar Descuentos");
        System.out.println("4) Actualizar Descuentos");
        System.out.println("5) Eliminar Descuentos");
        System.out.println("0) Volver");
    }

    /**
     * Nos permite crear un Descuento y devolverlo
     * @return El Descuento que hemos creado
     */
    public Descuentos crearAlergeno(){
        String cliente = EntradaTexto.pedirString("Cliente");
        int reserva = EntradaTexto.pedirInt("Numero de la reserva");
        String descripcion = EntradaTexto.pedirString("Descripcion del descuento");

        return new Descuentos(generarId(), cliente, reserva, descripcion);
    }

    /**
     * Nos permite devolver el ArrayList para las ventanas
     * @return ArrayList para las ventanas
     */
    public ArrayList<Descuentos> getDescuentos() {
        return descuentos;
    }

}
