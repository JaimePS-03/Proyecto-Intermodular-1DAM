package controlador;

import modelo.Plato;
import util.EntradaTexto;

import java.util.ArrayList;

public class PlatoDAO {

    // ArrayList con los Platos. Nos permite manejar los Platos de forma dinámica
    private static ArrayList<Plato> platos = new ArrayList<>();

    // Controlador de las ID para que no se repitan
    private static int nextId = 1; // USADO PARA LOS ARRAYLIST, SE ACTUALIZA AUTOMÁTICAMENTE

    /**
     * Constructor del Controlador de Platos
     */
    public PlatoDAO() {
    }

    /**
     * Nos permite insertar los Platos al ArrayList de Platos
     * @param p Plato que añadimos al ArrayList
     */
    public void insertar(Plato p){
        platos.add(p);
    }

    /**
     * Nos permite generar los ID de los platos, pasarlos a String y aumentar el número automáticamente
     * @return Nos devuelve el ID para asignar al Plato
     */
    public String generarId(){
        String devolverId = "" + nextId;
        nextId++;
        return devolverId;
    }

    /**
     * Nos permite listar todos los Platos del ArrayList
     */
    public void listar(){
        for(Plato p: platos) {
            System.out.println(p);
        }
    }

    /**
     * Nos permite buscar un Plato según el ID
     * @param id Id del Plato que vamos a buscar
     * @return Nos devuelve el Plato si coincide con el ID, si no nos devuelve null
     */
    public Plato buscarPorId(String id){
        for(Plato p: platos){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    /**
     * Nos permite borrar un Plato del ArrayList
     * @param id Id del plato que vamos a buscar
     * @return Si no encuentra al Plato, nos devuelve False. Si lo encuentra, lo elimina y devuelve True
     */
    public boolean eliminar(String id){
        if(buscarPorId(id) == null){
            return false;
        }else{
            platos.remove(buscarPorId(id));
            return true;
        }
    }

    /**
     * Nos permite actualizar la información de un plato. Si encuentra el plato, guarda el índice del plato para sobreescribir los datos luego.
     * @param id ID del Plato que vamos a actualizar los datos
     * @param p Información del plato (Pasada por un objeto plato) para actualizar
     */
    public void actualizar(String id, Plato p){
        Plato existente = buscarPorId(id);
        if(existente != null){
            int index = platos.indexOf(existente);
            platos.set(index, p);
        }
    }

    static{
        platos.add(new Plato("1","Paella", 15.45, "Arroces"));
        platos.add(new Plato("2","Paella", 15.45, "Arroces"));
        platos.add(new Plato("3","Paella", 15.45, "Arroces"));
        platos.add(new Plato("4","Paella", 15.45, "Arroces"));
        nextId = 5;
    }

    /**
     * Nos permite crear un Plato y devolverlo
     * @return El plato que hemos creado
     */
    public Plato generarPlato(){
        String nombre = EntradaTexto.pedirString("Nombre del plato");
        double precio = EntradaTexto.pedirDouble("Precio del plato");
        String tipo = EntradaTexto.pedirString("Tipo de plato");

        return new Plato(generarId(), nombre, precio, tipo);
    }

    /**
     * Imprime el submenú para controlar las Reservas
     */
    public void menu(){
        System.out.println("---- PLATOS ----\n" +
                "1) Listar platos\n" +
                "2) Nueva plato\n" +
                "3) Buscar plato\n" +
                "4) Eliminar platos\n" +
                "5) Actualizar platos\n" +
                "0) Volver");
    }
}
