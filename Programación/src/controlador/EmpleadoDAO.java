package controlador;

import modelo.Empleado;
import java.util.ArrayList;

/**
 * Controlador genérico para gestionar empleados y sus subclases.
 *
 * @param <T> Tipo de objeto que hereda de Empleado
 */
public class EmpleadoDAO<T extends Empleado> extends UsuarioDAO<T> {

    /**
     * Constructor del controlador de empleados.
     */
    public EmpleadoDAO() {
        super();
    }

    /**
     * Busca empleados por colectivo.
     *
     * @param colectivo Colectivo que se desea buscar
     * @return Lista de empleados que pertenecen al colectivo indicado
     */
    public ArrayList<T> buscarPorColectivo(String colectivo) {
        ArrayList<T> resultado = new ArrayList<>();

        for (T empleado : listaUsuarios) {
            if (empleado.getColectivo().equalsIgnoreCase(colectivo)) {
                resultado.add(empleado);
            }
        }

        return resultado;
    }

    /**
     * Busca un empleado por su NUSS.
     *
     * @param nuss Número de la Seguridad Social del empleado
     * @return Empleado encontrado o null si no existe
     */
    public T buscarPorNUSS(String nuss) {
        for (T empleado : listaUsuarios) {
            if (empleado.getNUSS().equalsIgnoreCase(nuss)) {
                return empleado;
            }
        }

        return null;
    }

    /**
     * Muestra por pantalla el menú de gestión de empleados.
     */
    @Override
    public void menu() {
        System.out.println("--- Gestión de Empleados ---");
        System.out.println("1) Listar");
        System.out.println("2) Insertar");
        System.out.println("3) Buscar por DNI");
        System.out.println("4) Buscar por NUSS");
        System.out.println("5) Buscar por colectivo");
        System.out.println("6) Actualizar");
        System.out.println("7) Eliminar");
        System.out.println("0) Volver");
    }
}