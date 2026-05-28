package controlador;

import modelo.Bartender;

/**
 * Controlador específico para la gestión de bartenders.
 */
public class BartenderDAO extends EmpleadoDAO<Bartender> {

    /**
     * Constructor del controlador de bartenders.
     */
    public BartenderDAO() {
        super();
    }

    /**
     * Muestra el menú de gestión de bartenders.
     */
    @Override
    public void menu() {
        System.out.println("--- Gestión de Bartenders ---");
        System.out.println("1) Listar bartenders");
        System.out.println("2) Insertar bartender");
        System.out.println("3) Buscar bartender por DNI");
        System.out.println("4) Buscar bartender por NUSS");
        System.out.println("5) Actualizar bartender");
        System.out.println("6) Eliminar bartender");
        System.out.println("0) Volver");
    }
}