package controlador;

import modelo.Proveedores;
import util.EntradaTexto;

import java.util.ArrayList;

/**
 * Controlador DAO para gestionar objetos de tipo Proveedores.
 * Permite realizar operaciones CRUD sobre una lista dinámica.
 */
public class ProveedorDAO {

    /**
     * Lista dinámica que almacena los proveedores.
     */
    private static ArrayList<Proveedores> proveedores = new ArrayList<>();

    /**
     * Constructor vacío del DAO de proveedores.
     */
    public ProveedorDAO() {
    }

    /**
     * Crea un nuevo proveedor pidiendo los datos por teclado.
     *
     * @return Objeto Proveedores con los datos introducidos
     */
    public Proveedores crearProveedor() {
        String cif = EntradaTexto.pedirString("Introduce el CIF del proveedor: ");
        String nombre = EntradaTexto.pedirString("Introduce el nombre del proveedor: ");
        String direccion = EntradaTexto.pedirString("Introduce la dirección del proveedor: ");
        String telefono = EntradaTexto.pedirString("Introduce el teléfono del proveedor: ");
        String email = EntradaTexto.pedirString("Introduce el email del proveedor: ");

        return new Proveedores(cif, nombre, direccion, telefono, email);
    }

    /**
     * Inserta un proveedor en la lista.
     *
     * @param proveedor Proveedor que se desea añadir
     */
    public void insertar(Proveedores proveedor) {
        proveedores.add(proveedor);
    }

    /**
     * Lista todos los proveedores almacenados.
     *
     * @return Cadena con todos los proveedores
     */
    public String listar() {
        String texto = "";

        for (Proveedores proveedor : proveedores) {
            texto += proveedor + "\n";
        }

        return texto;
    }

    /**
     * Busca un proveedor por su CIF.
     *
     * @param cif CIF del proveedor a buscar
     * @return Proveedor encontrado o null si no existe
     */
    public Proveedores buscarPorId(String cif) {
        for (Proveedores proveedor : proveedores) {
            if (proveedor.getCif().equalsIgnoreCase(cif)) {
                return proveedor;
            }
        }
        return null;
    }

    /**
     * Elimina un proveedor de la lista por su CIF.
     *
     * @param cif CIF del proveedor a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    public boolean eliminar(String cif) {
        Proveedores proveedor = buscarPorId(cif);

        if (proveedor != null) {
            proveedores.remove(proveedor);
            return true;
        }

        return false;
    }

    /**
     * Actualiza la información de un proveedor existente.
     *
     * @param cifBuscado CIF del proveedor que se quiere actualizar
     * @param proveedorActualizado Objeto con los nuevos datos
     */
    public void actualizar(String cifBuscado, Proveedores proveedorActualizado) {
        Proveedores proveedorExistente = buscarPorId(cifBuscado);

        if (proveedorExistente != null) {
            int index = proveedores.indexOf(proveedorExistente);
            proveedores.set(index, proveedorActualizado);
        }
    }

    /**
     * Muestra el menú de gestión de proveedores.
     */
    public void menu() {
        System.out.println("--- Proveedores ---");
        System.out.println("1) Listar proveedores");
        System.out.println("2) Nuevo proveedor");
        System.out.println("3) Buscar proveedor");
        System.out.println("4) Actualizar proveedor");
        System.out.println("5) Eliminar proveedor");
        System.out.println("0) Volver");
    }

    /**
     * Devuelve la lista completa de proveedores.
     *
     * @return ArrayList de proveedores
     */
    public ArrayList<Proveedores> getProveedores() {
        return proveedores;
    }
}