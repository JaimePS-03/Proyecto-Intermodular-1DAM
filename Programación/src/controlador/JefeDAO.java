package controlador;

import modelo.Jefe;

/**
 * Controlador específico para la gestión de jefes.
 */
public class JefeDAO extends UsuarioDAO<Jefe> {

    /**
     * Constructor del controlador de jefes.
     */
    public JefeDAO() {
        super();
    }


    /**
     * Muestra el menú de gestión de jefes.
     */
    @Override
    public void menu() {
        System.out.println("--- Gestión de Jefes ---");
        System.out.println("1) Listar jefes");
        System.out.println("2) Insertar jefe");
        System.out.println("3) Buscar jefe por DNI");
        System.out.println("5) Actualizar jefe");
        System.out.println("6) Eliminar jefe");
        System.out.println("0) Volver");
    }
}