package controlador;

import modelo.Usuario;
import java.util.ArrayList;

/**
 * Controlador genérico para gestionar objetos de tipo Usuario y sus subclases.
 *
 * @param <T> Tipo de objeto que hereda de Usuario
 */
public class UsuarioDAO<T extends Usuario> {

    /**
     * Lista dinámica donde se almacenan los usuarios.
     */
    protected ArrayList<T> listaUsuarios;

    /**
     * Constructor del controlador genérico de usuarios.
     * Inicializa la lista vacía.
     */
    public UsuarioDAO() {
        listaUsuarios = new ArrayList<>();
    }

    /**
     * Inserta un nuevo usuario en la lista.
     *
     * @param usuario Objeto usuario que se desea añadir
     */
    public void insertar(T usuario) {
        listaUsuarios.add(usuario);
    }

    /**
     * Devuelve un texto con todos los usuarios almacenados.
     *
     * @return Cadena con el listado de usuarios
     */
    public String listar() {
        StringBuilder sb = new StringBuilder();

        for (T usuario : listaUsuarios) {
            sb.append(usuario).append("\n");
        }

        return sb.toString();
    }

    /**
     * Busca un usuario por su DNI.
     *
     * @param dni DNI del usuario a buscar
     * @return El usuario encontrado o null si no existe
     */
    public T buscarPorDni(String dni) {
        for (T usuario : listaUsuarios) {
            if (usuario.getDni().equalsIgnoreCase(dni)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Elimina un usuario de la lista a partir de su DNI.
     *
     * @param dni DNI del usuario a eliminar
     * @return true si se eliminó correctamente, false si no existe
     */
    public boolean eliminar(String dni) {
        T usuarioEncontrado = buscarPorDni(dni);

        if (usuarioEncontrado != null) {
            listaUsuarios.remove(usuarioEncontrado);
            return true;
        }

        return false;
    }

    /**
     * Actualiza los datos de un usuario existente buscando por su DNI.
     *
     * @param dniBuscado DNI del usuario que se quiere actualizar
     * @param usuarioActualizado Nuevo objeto con los datos actualizados
     * @return true si se actualizó, false si no se encontró el usuario
     */
    public boolean actualizar(String dniBuscado, T usuarioActualizado) {
        T usuarioExistente = buscarPorDni(dniBuscado);

        if (usuarioExistente != null) {
            int indice = listaUsuarios.indexOf(usuarioExistente);
            listaUsuarios.set(indice, usuarioActualizado);
            return true;
        }

        return false;
    }

    /**
     * Devuelve la lista completa de usuarios.
     *
     * @return ArrayList con los usuarios almacenados
     */
    public ArrayList<T> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * Muestra por pantalla el menú genérico de usuarios.
     */
    public void menu() {
        System.out.println("--- Gestión de Usuarios ---");
        System.out.println("1) Listar");
        System.out.println("2) Insertar");
        System.out.println("3) Buscar por DNI");
        System.out.println("4) Actualizar");
        System.out.println("5) Eliminar");
        System.out.println("0) Volver");
    }
}