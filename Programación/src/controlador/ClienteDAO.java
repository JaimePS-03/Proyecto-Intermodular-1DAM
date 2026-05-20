package controlador;

import modelo.Cliente;
import util.EntradaTexto;

import java.util.ArrayList;

public class ClienteDAO {

    // ArrayList con los Clientes. Nos permite manejar los Clientes de forma dinámica
    private static ArrayList<Cliente> clientes = new ArrayList<>();

    // Controlador de las ID para que no se repitan
    private static int nextId = 1;

    /**
     * Constructor del Controlador de Clientes
     */
    public ClienteDAO() {
    }

    /**
     * Generador de ID. Este nos permite generar un ID con un formato
     * @return Nos devuelve el ID para poder usarlo con los diferentes clientes
     */
    public String generarId() {
        return "C" + String.format("%03d", nextId++);
    }

    /**
     * Nos permite insertar los Clientes al ArrayList de clientes, ya que es privada
     * @param c Cliente que añadimos al ArrayList
     */
    public void insertar(Cliente c){
        clientes.add(c);
    }

    /**
     * Nos permite listar todos los Clientes que están en el ArrayList
     */
    public void listar(){
        for(Cliente c : clientes){
            System.out.println(c);
        }
    }

    /**
     * Nos permite buscar los Clientes por su ID
     * @param id El ID del cliente al buscarlo
     * @return Si encuentra el cliente nos devuelve el Cliente seleccionado. Si no, nos devuelve null (no nos devuelve nada)
     */
    public Cliente buscarPorId(String id){
        for(Cliente c : clientes){
            if(c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

    /**
     * Nos permite borrar un Cliente del ArrayList
     * @param id Id del cliente que vamos a buscar
     * @return Si no encuentra al Cliente, nos devuelve False. Si lo encuentra, lo elimina y devuelve True
     */
    public boolean eliminar(String id){
        if(buscarPorId(id) == null){
            return false;
        }else{
            clientes.remove(buscarPorId(id));
            return true;
        }
    }

    /**
     * Nos permite actualizar la información de un cliente. Si encuentra el cliente, guarda el índice del cliente para sobreescribir los datos luego.
     * @param idBuscado ID del Cliente que vamos a actualizar los datos
     * @param cActualizado Información del cliente (Pasada por un objeto cliente) para actualizar
     */
    public void actualizar(String idBuscado, Cliente cActualizado) {
        Cliente existente = buscarPorId(idBuscado);
        if (existente != null) {
            int index = clientes.indexOf(existente);
            clientes.set(index, cActualizado);
        }
    }

    static {
        clientes.add(new Cliente("C000", "cliente", null, null, null));
        clientes.add(new Cliente("C001", "Juan", "Pérez", "612345678901", "juan@arroceria.es"));
        clientes.add(new Cliente("C002", "Ana", "García", "698765432109", "ana@arroceria.es"));
        nextId = 3;
    }

    /**
     * Nos permite imprimir el submenú para controlar Clientes
     */
    public void menu(){
        System.out.println("--- CLIENTES ---");
        System.out.println("1) Listar clientes");
        System.out.println("2) Nuevo cliente");
        System.out.println("3) Buscar cliente");
        System.out.println("4) Actualizar Cliente");
        System.out.println("5) Eliminar clientes");
        System.out.println("0) Volver");
    }

    /**
     * Nos permite crear un Cliente y devolverlo
     * @return El cliente que hemos creado
     */
    public Cliente crearCliente(){
        String nombre = EntradaTexto.pedirString("Dame el nombre del cliente");
        String apellidos = EntradaTexto.pedirString("Dame el apellido del cliente");
        String telefono = EntradaTexto.pedirString("Dame el teléfono del cliente");
        String email = EntradaTexto.pedirString("Dame el email del cliente");

        return new Cliente(generarId(),nombre, apellidos, telefono, email);
    }

}
