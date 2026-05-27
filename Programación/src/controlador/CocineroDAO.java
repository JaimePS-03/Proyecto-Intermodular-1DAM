package controlador;

import modelo.Cocinero;
import java.util.ArrayList;

/**
 * Controlador específico para la gestión de cocineros.
 */
public class CocineroDAO extends EmpleadoDAO<Cocinero> {

    /**
     * Constructor del controlador de cocineros.
     */
    public CocineroDAO() {
        super();
    }

    /**
     * Busca cocineros por especialidad.
     *
     * @param especialidad Especialidad del cocinero
     * @return Lista de cocineros con esa especialidad
     */
    public ArrayList<Cocinero> buscarPorEspecialidad(String especialidad) {
        ArrayList<Cocinero> resultado = new ArrayList<>();

        for (Cocinero cocinero : listaUsuarios) {
            if (cocinero.getEspecialidad().equalsIgnoreCase(especialidad)) {
                resultado.add(cocinero);
            }
        }

        return resultado;
    }

    /**
     * Muestra el menú de gestión de cocineros.
     */
    @Override
    public void menu() {
        System.out.println("--- Gestión de Cocineros ---");
        System.out.println("1) Listar cocineros");
        System.out.println("2) Insertar cocinero");
        System.out.println("3) Buscar cocinero por DNI");
        System.out.println("4) Buscar cocinero por NUSS");
        System.out.println("5) Buscar cocinero por especialidad");
        System.out.println("6) Actualizar cocinero");
        System.out.println("7) Eliminar cocinero");
        System.out.println("0) Volver");
    }
}