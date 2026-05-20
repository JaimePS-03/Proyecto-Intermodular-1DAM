package controlador;

import modelo.Mesa;
import util.EntradaTexto;

import java.util.ArrayList;

public class MesaDAO {

    // ArrayList con las Mesas. Nos permite manejas las Mesas de forma dinámica
    private static ArrayList<Mesa> mesas = new ArrayList<>();

    // Controlador de las ID para que no se repitan
    private static int nextId = 1;

    /**
     * Constructor del Controlador de Mesas
     */
    public MesaDAO(){
    }

    /**
     * Nos permite generar ID diferente y tener un control para no repetir ID.
     * @return Devuelve el ID a asignar
     */
    public int generarId(){
        int id = nextId;
        nextId++;
        return id;
    }

    /**
     * Nos permite insertar en el ArrayList las mesas para tener un control
     * @param m Mesa a añadir
     */
    public void insertar(Mesa m){
        mesas.add(m);
    }

    /**
     * Nos permite mostrar todas las mesas junto a la información de estas
     */
    public void listar(){
        for(Mesa m: mesas){
            System.out.println(m);
        }
    }

    /**
     * Nos permite buscar una mesa por su ID
     * @param id ID de la mesa a buscar
     * @return Si existe la mesa, nos la devolverá. Si no, nos devuelve null
     */
    public Mesa buscarPorId(int id){
        for(Mesa m : mesas){
            if(m.getnMesa() == id){
                return m;
            }
        }
        return null;
    }

    /**
     * Nos permite eliminar una mesa del ArrayList de mesas
     * @param id Id de la mesas a eliminar
     * @return Si existe y la elimina, nos devuelve true, si no, devolverá falso
     */
    public boolean eliminar(int id){
        if(buscarPorId(id) == null){
            return false;
        }else{
            mesas.remove(buscarPorId(id));
            return true;
        }
    }

    /**
     * Nos permite actualizar la información de una mesa
     * @param id Mesa a actualizar información
     * @param m Información con la mesa actualizada
     */
    public void actualizar(int id, Mesa m){
        Mesa existente = buscarPorId(id);
        if(existente != null){
            int index = mesas.indexOf(existente);
            mesas.set(index, m);
        }
    }

    /**
     * Nos permite crear una mesa y devolverlo
     * @return Mesa creada
     */
    public Mesa generarMesa(){
        int nPersonas = EntradaTexto.pedirIntRango("Capacidad de la mesa", 1, 12);

        return new Mesa(generarId(), nPersonas);
    }

    /**
     * Imprimir el submenú para poder controlar las Mesas
     */
    public void menu(){
        System.out.println("----- MESAS -----\n" +
                "1) Listar mesas\n" +
                "2) Nueva mesa\n" +
                "3) Buscar mesa\n" +
                "4) Eliminar mesa\n" +
                "5) Actualizar mesa\n" +
                "0) Volver");
    }
}
