package controlador;

import modelo.Alergenos;
import util.EntradaTexto;

import java.util.ArrayList;

public class AlergenoDAO {

    // ArrayList con los Alergenos. Nos permite manejar los Alergenos de forma dinámica
    private static ArrayList<Alergenos> alergenos = new ArrayList<>();

    // Controlador de las ID para que no se repitan
    private static int nextId = 1;

    /**
     * Constructor del Controlador de Alergenos
     */
    public AlergenoDAO() {
    }

    /**
     * Generador de ID. Este nos permite generar un ID con un formato
     * @return Nos devuelve el ID para poder usarlo con los diferentes alergenos
     */
    public int generarId() {
        return nextId++;
    }

    /**
     * Nos permite insertar los Alergenos al ArrayList de alergenos, ya que es privada
     * @param a Alergeno que añadimos al ArrayList
     */
    public void insertar(Alergenos a){alergenos.add(a);
    }

    /**
     * Nos permite listar todos los Alergenos que están en el ArrayList
     */
    public String listar() {
        String texto = "";

        for (Alergenos a : alergenos) {
            texto = texto + a + "\n";
        }

        return texto;
    }


    /**
     * Nos permite buscar los Alergenos por su ID
     * @param id El ID del alergeno al buscarlo
     * @return Si encuentra el alergeno nos devuelve el Alergeno seleccionado. Si no, nos devuelve null (no nos devuelve nada)
     */
    public Alergenos buscarPorId(int id){
        for(Alergenos a : alergenos){
            if(a.getId() == id){
                return a;
            }
        }
        return null;
    }

    /**
     * Nos permite borrar un Alergeno del ArrayList
     * @param id Id del alergeno que vamos a buscar
     * @return Si no encuentra al Alergeno, nos devuelve False. Si lo encuentra, lo elimina y devuelve True
     */
    public boolean eliminar(int id){
        if(buscarPorId(id) == null){
            return false;
        }else{
            alergenos.remove(buscarPorId(id));
            return true;
        }
    }

    /**
     * Nos permite actualizar la información de un alergeno. Si encuentra el alergeno, guarda el índice del alergeno para sobreescribir los datos luego.
     * @param idBuscado ID del Alergeno que vamos a actualizar los datos
     * @param aActualizado Información del alergeno (Pasada por un objeto alergeno) para actualizar
     */
    public void actualizar(int idBuscado, Alergenos aActualizado) {
        Alergenos existente = buscarPorId(idBuscado);
        if (existente != null) {
            int index = alergenos.indexOf(existente);
            alergenos.set(index, aActualizado);
        }
    }

    /**
     * Nos permite imprimir el submenú para controlar Alergenos
     */
    public void menu(){
        System.out.println("--- ALERGENOS ---");
        System.out.println("1) Listar alergenos");
        System.out.println("2) Nuevo alergeno");
        System.out.println("3) Buscar alergenos");
        System.out.println("4) Actualizar alergenos");
        System.out.println("5) Eliminar alergenos");
        System.out.println("0) Volver");
    }

    /**
     * Nos permite crear un Alergeno y devolverlo
     * @return El Alergeno que hemos creado
     */
    public Alergenos crearAlergeno(){
        String grupo = EntradaTexto.pedirString("Dame el grupo del alérgeno");

        return new Alergenos(generarId(),grupo);
    }

    /**
     * Nos permite devolver el ArrayList para las ventanas
     * @return ArrayList para las ventanas
     */
    public ArrayList<Alergenos> getCAlergeno() {
        return alergenos;
    }

}
