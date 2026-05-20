package controlador;

import util.EntradaTexto;

import java.util.ArrayList;
import modelo.Usuario;

public class UsuarioDAO {

    // ArrayList con los Usuarios. Nos permite manejar los Usuarios de forma dinámica usando Casting
    private static ArrayList<Usuario> empleados = new ArrayList<>();

    // Controlador de las ID para que no se repitan
    private static int nextId = 1;

    /**
     * Constructor del Controlador de Usuarios
     */
    public UsuarioDAO() {
    }

    /**
     * Generador de ID. Este nos permite generar un ID con un formato
     * @return Nos devuelve el ID para poder usarlo con los diferentes Usuarios
     */
    public int generarId() {
        return nextId++;
    }

    /**
     * Nos permite insertar los Usuarios al ArrayList de empleados, ya que es privada
     * @param a Empleado que añadimos al ArrayList
     */
    public void insertar(Usuario a){empleados.add(a);
    }

    /**
     * Nos permite listar todos los Alergenos que están en el ArrayList
     */
    public String listar() {
        String texto = "";

        for (Usuario a : empleados) {
            texto = texto + a + "\n";
        }

        return texto;
    }


    /**
     * Nos permite buscar los Usuarios por su ID
     * @param id El ID del Usuario al buscarlo
     * @return Si encuentra el Usuario nos devuelve el Alergeno seleccionado. Si no, nos devuelve null (no nos devuelve nada)
     */
    public Usuario buscarPorId(String id){
        for(Usuario a : empleados){
            if(a.getDni().equals(id)){
                return a;
            }
        }
        return null;
    }

    /**
     * Nos permite borrar un Usuario del ArrayList
     * @param id Id del Usuario que vamos a buscar
     * @return Si no encuentra al Usuario, nos devuelve False. Si lo encuentra, lo elimina y devuelve True
     */
    public boolean eliminar(String id){
        if(buscarPorId(id) == null){
            return false;
        }else{
            empleados.remove(buscarPorId(id));
            return true;
        }
    }

    /**
     * Nos permite actualizar la información de un usuario. Si encuentra el usuario, guarda el índice del usuario para sobreescribir los datos luego.
     * @param idBuscado ID del Usuario que vamos a actualizar los datos
     * @param aActualizado Información del usuario (Pasada por un objeto usuario) para actualizar
     */
    public void actualizar(String idBuscado, Usuario aActualizado) {
        Usuario existente = buscarPorId(idBuscado);
        if (existente != null) {
            int index = empleados.indexOf(existente);
            empleados.set(index, aActualizado);
        }
    }

    /**
     * Nos permite imprimir el submenú para controlar Usuarios
     */
    public void menu(){
        System.out.println("--- Usuarios ---");
        System.out.println("1) Listar Usuarios");
        System.out.println("2) Nuevo Usuario");
        System.out.println("3) Buscar Usuarios");
        System.out.println("4) Actualizar Usuarios");
        System.out.println("5) Eliminar Usuarios");
        System.out.println("0) Volver");
    }

    /**
     * Nos permite devolver el ArrayList para las ventanas
     * @return ArrayList para las ventanas
     */
    public ArrayList<Usuario> getCAlergeno() {
        return empleados;
    }

}
