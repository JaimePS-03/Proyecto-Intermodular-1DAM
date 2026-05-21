package controlador;

import modelo.Camareros;

import java.util.ArrayList;

/**
 * Controlador específico para la gestión de camareros.
 */
public class CamareroDAO extends EmpleadoDAO<Camareros> {

    /**
     * Constructor del controlador de camareros.
     */
    public CamareroDAO() {
        super();
    }

    /**
     * Busca camareros por zona de trabajo.
     *
     * @param zona Zona que se desea buscar
     * @return Lista de camareros que trabajan en esa zona
     */
    public ArrayList<Camareros> buscarPorZona(String zona) {
        ArrayList<Camareros> resultado = new ArrayList<>();

        for (Camareros camarero : listaUsuarios) {
            if (camarero.getnZona().equalsIgnoreCase(zona)) {
                resultado.add(camarero);
            }
        }

        return resultado;
    }

    /**
     * Busca camareros por nombre del jefe de sala.
     *
     * @param jefeSala Nombre del jefe de sala
     * @return Lista de camareros asociados a ese jefe de sala
     */
    public ArrayList<Camareros> buscarPorJefeSala(String jefeSala) {
        ArrayList<Camareros> resultado = new ArrayList<>();

        for (Camareros camarero : listaUsuarios) {
            if (camarero.getCamareroJefe().equalsIgnoreCase(jefeSala)) {
                resultado.add(camarero);
            }
        }

        return resultado;
    }

    /**
     * Muestra el menú de gestión de camareros.
     */
    @Override
    public void menu() {
        System.out.println("--- Gestión de Camareros ---");
        System.out.println("1) Listar camareros");
        System.out.println("2) Insertar camarero");
        System.out.println("3) Buscar camarero por DNI");
        System.out.println("4) Buscar camarero por NUSS");
        System.out.println("5) Buscar camarero por zona");
        System.out.println("6) Buscar camarero por jefe de sala");
        System.out.println("7) Actualizar camarero");
        System.out.println("8) Eliminar camarero");
        System.out.println("0) Volver");
    }
}
