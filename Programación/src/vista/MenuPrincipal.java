package vista;

import controlador.*;
import modelo.*;
import util.EntradaTexto;

import java.util.ArrayList;

/**
 * Clase principal de la aplicación.
 * Controla la navegación entre los distintos submenús
 * y coordina las operaciones CRUD de cada entidad.
 */
public class MenuPrincipal {

    /**
     * Instancias de los controladores.
     */
    private static final ClienteDAO cDao = new ClienteDAO();
    private static final ReservaDAO rDao = new ReservaDAO();
    private static final PlatoDAO pDao = new PlatoDAO();
    private static final MesaDAO mDao = new MesaDAO();
    private static final DescuentoDAO dDao = new DescuentoDAO();
    private static final ProveedorDAO prDao = new ProveedorDAO();

    /**
     * Método principal de arranque del programa.
     *
     * @param args Argumentos de entrada del programa
     */
    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenuPrincipal();
            opcion = EntradaTexto.pedirInt("Dame una opción");

            switch (opcion) {
                case 1:
                    menuClientes();
                    break;
                case 2:
                    menuPlatos();
                    break;
                case 3:
                    menuReservas();
                    break;
                case 4:
                    menuMesas();
                    break;
                case 5:
                    menuDescuentos();
                    break;
                case 6:
                    menuProveedores();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }

        } while (opcion != 0);
    }

    /**
     * Muestra el menú principal de la aplicación.
     */
    public static void mostrarMenuPrincipal() {
        System.out.println("----- MENÚ PRINCIPAL -----");
        System.out.println("1) Clientes");
        System.out.println("2) Platos");
        System.out.println("3) Reservas");
        System.out.println("4) Mesas");
        System.out.println("5) Descuentos");
        System.out.println("6) Proveedores");
        System.out.println("0) Salir");
    }

    /**
     * Gestiona el submenú de clientes.
     */
    public static void menuClientes() {
        int opCli;

        do {
            cDao.menu();
            opCli = EntradaTexto.pedirInt("Opción: ");

            switch (opCli) {
                case 1:
                    System.out.println(cDao.listar());
                    break;

                case 2:
                    cDao.insertar(cDao.crearCliente());
                    System.out.println("Cliente insertado correctamente");
                    break;

                case 3:
                    String idBuscar = EntradaTexto.pedirString("ID del cliente");
                    Cliente buscarCli = cDao.buscarPorId(idBuscar);
                    System.out.println(buscarCli != null ? buscarCli : "No encontrado");
                    break;

                case 4:
                    String idActualizar = EntradaTexto.pedirString("ID del cliente a actualizar");
                    Cliente clienteActualizar = cDao.buscarPorId(idActualizar);

                    if (clienteActualizar != null) {
                        clienteActualizar.setNombre(EntradaTexto.pedirString("Nuevo nombre (" + clienteActualizar.getNombre() + "): "));
                        clienteActualizar.setApellidos(EntradaTexto.pedirString("Nuevo apellido (" + clienteActualizar.getApellidos() + "): "));
                        clienteActualizar.setTelefono(EntradaTexto.pedirString("Nuevo teléfono (" + clienteActualizar.getTelefono() + "): "));
                        clienteActualizar.setEmail(EntradaTexto.pedirString("Nuevo email (" + clienteActualizar.getEmail() + "): "));

                        cDao.actualizar(idActualizar, clienteActualizar);
                        System.out.println("Cliente actualizado correctamente");
                    } else {
                        System.out.println("Cliente no encontrado");
                    }
                    break;

                case 5:
                    String idEliminar = EntradaTexto.pedirString("ID del cliente a eliminar");
                    System.out.println(cDao.eliminar(idEliminar) ? "Cliente eliminado" : "No encontrado");
                    break;

                case 0:
                    System.out.println("Saliendo del menú de clientes...");
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }

        } while (opCli != 0);
    }

    /**
     * Gestiona el submenú de platos.
     */
    public static void menuPlatos() {
        int opPlato;

        do {
            pDao.menu();
            opPlato = EntradaTexto.pedirInt("Opción: ");

            switch (opPlato) {
                case 1:
                    System.out.println(pDao.listar());
                    break;

                case 2:
                    pDao.insertar(pDao.generarPlato());
                    System.out.println("Plato insertado correctamente");
                    break;

                case 3:
                    String idBuscar = EntradaTexto.pedirString("ID plato: ");
                    Plato buscarPlato = pDao.buscarPorId(idBuscar);
                    System.out.println(buscarPlato != null ? buscarPlato : "No existe ese plato");
                    break;

                case 4:
                    String idEliminar = EntradaTexto.pedirString("ID del plato a eliminar: ");
                    System.out.println(pDao.eliminar(idEliminar) ? "Plato eliminado con éxito" : "No existe el plato");
                    break;

                case 5:
                    String idActualizar = EntradaTexto.pedirString("ID del plato a actualizar: ");
                    Plato platoActualizar = pDao.buscarPorId(idActualizar);

                    if (platoActualizar != null) {
                        platoActualizar.setNombre(EntradaTexto.pedirString("Nuevo nombre del plato (" + platoActualizar.getNombre() + "): "));
                        platoActualizar.setPrecio(EntradaTexto.pedirDouble("Nuevo precio (" + platoActualizar.getPrecio() + "): "));
                        platoActualizar.setTipo(EntradaTexto.pedirString("Nuevo tipo de plato (" + platoActualizar.getTipo() + "): "));
                        pDao.actualizar(idActualizar, platoActualizar);
                        System.out.println("Plato actualizado correctamente");
                    } else {
                        System.out.println("Plato no encontrado");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del menú de platos...");
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }

        } while (opPlato != 0);
    }

    /**
     * Gestiona el submenú de reservas.
     */
    public static void menuReservas() {
        int opReserva;

        do {
            rDao.menu();
            opReserva = EntradaTexto.pedirInt("Opción: ");

            switch (opReserva) {
                case 1:
                    System.out.println(rDao.listar());
                    break;

                case 2:
                    String idClienteReserva = EntradaTexto.pedirString("ID cliente: ");
                    Cliente clienteReserva = cDao.buscarPorId(idClienteReserva);

                    if (clienteReserva != null) {
                        rDao.insertar(rDao.generarReserva(idClienteReserva));
                        System.out.println("Reserva insertada correctamente");
                    } else {
                        System.out.println("No hay clientes con ese ID");
                    }
                    break;

                case 3:
                    String idCli = EntradaTexto.pedirString("ID cliente: ");
                    ArrayList<Reserva> resCli = rDao.listarPorCliente(idCli);

                    if (resCli != null && !resCli.isEmpty()) {
                        for (Reserva reserva : resCli) {
                            System.out.println(reserva);
                        }
                    } else {
                        System.out.println("Sin reservas");
                    }
                    break;

                case 4:
                    int idEliminar = EntradaTexto.pedirInt("ID de la reserva a eliminar: ");
                    System.out.println(rDao.eliminar(idEliminar) ? "Reserva eliminada" : "Reserva no encontrada");
                    break;

                case 5:
                    int idActualizar = EntradaTexto.pedirInt("ID de la reserva a actualizar: ");
                    Reserva reservaActualizar = rDao.buscarPorId(idActualizar);

                    if (reservaActualizar != null) {
                        reservaActualizar.setnPersonas(EntradaTexto.pedirInt("Nueva cantidad de comensales (" + reservaActualizar.getnPersonas() + "): "));
                        reservaActualizar.setTipoReserva(EntradaTexto.pedirString("Nuevo tipo de reserva (" + reservaActualizar.getTipoReserva() + "): "));

                        String nuevoIdCliente = EntradaTexto.pedirString("Nuevo ID cliente (" + reservaActualizar.getIdCliente() + "): ");
                        Cliente clienteNuevo = cDao.buscarPorId(nuevoIdCliente);

                        if (clienteNuevo != null) {
                            reservaActualizar.setIdCliente(nuevoIdCliente);
                            rDao.actualizar(idActualizar, reservaActualizar);
                            System.out.println("Reserva actualizada correctamente");
                        } else {
                            System.out.println("No existe un cliente con ese ID");
                        }
                    } else {
                        System.out.println("Reserva no encontrada");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del menú de reservas...");
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }

        } while (opReserva != 0);
    }

    /**
     * Gestiona el submenú de mesas.
     */
    public static void menuMesas() {
        int opMesa;

        do {
            mDao.menu();
            opMesa = EntradaTexto.pedirInt("Opción: ");

            switch (opMesa) {
                case 1:
                    System.out.println(mDao.listar());
                    break;

                case 2:
                    mDao.insertar(mDao.generarMesa());
                    System.out.println("Mesa insertada correctamente");
                    break;

                case 3:
                    int idBuscar = EntradaTexto.pedirInt("ID de la mesa buscada");
                    Mesa mesa = mDao.buscarPorId(idBuscar);
                    System.out.println(mesa != null ? mesa : "No existe una mesa con ese ID");
                    break;

                case 4:
                    int idEliminar = EntradaTexto.pedirInt("ID de la mesa a eliminar");
                    System.out.println(mDao.eliminar(idEliminar) ? "Mesa eliminada" : "Mesa no encontrada");
                    break;

                case 5:
                    int idActualizar = EntradaTexto.pedirInt("ID de la mesa a actualizar: ");
                    Mesa mesaActualizar = mDao.buscarPorId(idActualizar);

                    if (mesaActualizar != null) {
                        mesaActualizar.setnPersonas(EntradaTexto.pedirInt("Nueva capacidad (" + mesaActualizar.getnPersonas() + "): "));
                        mDao.actualizar(idActualizar, mesaActualizar);
                        System.out.println("Mesa actualizada correctamente");
                    } else {
                        System.out.println("Mesa no encontrada");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del menú de mesas...");
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }

        } while (opMesa != 0);
    }

    /**
     * Gestiona el submenú de descuentos.
     */
    public static void menuDescuentos() {
        int opcionDesc;

        do {
            dDao.menu();
            opcionDesc = EntradaTexto.pedirInt("Qué opción quieres escoger");

            switch (opcionDesc) {
                case 1:
                    System.out.println(dDao.listar());
                    break;

                case 2:
                    dDao.insertar(dDao.crearDescuento());
                    System.out.println("Descuento insertado correctamente");
                    break;

                case 3:
                    String codigoBuscar = EntradaTexto.pedirString("Código de descuento a buscar");
                    Descuentos descuento = dDao.buscarPorId(codigoBuscar);
                    System.out.println(descuento != null ? descuento : "No existe el descuento");
                    break;

                case 4:
                    String codigoActualizar = EntradaTexto.pedirString("Código del descuento a actualizar");
                    Descuentos descuentoActualizar = dDao.buscarPorId(codigoActualizar);

                    if (descuentoActualizar != null) {
                        descuentoActualizar.setCodigoDescuento(EntradaTexto.pedirString("Nuevo código de descuento: "));
                        dDao.actualizar(codigoActualizar, descuentoActualizar);
                        System.out.println("Descuento actualizado correctamente");
                    } else {
                        System.out.println("Descuento no encontrado");
                    }
                    break;

                case 5:
                    String codigoEliminar = EntradaTexto.pedirString("Código de descuento a eliminar");
                    System.out.println(dDao.eliminar(codigoEliminar)
                            ? "Código de descuento eliminado"
                            : "No se ha encontrado ningún código de descuento");
                    break;

                case 0:
                    System.out.println("Saliendo del menú de descuentos...");
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }

        } while (opcionDesc != 0);
    }

    /**
     * Gestiona el submenú de proveedores.
     */
    public static void menuProveedores() {
        int opProv;

        do {
            prDao.menu();
            opProv = EntradaTexto.pedirInt("Opción: ");

            switch (opProv) {
                case 1:
                    System.out.println(prDao.listar());
                    break;

                case 2:
                    prDao.insertar(prDao.crearProveedor());
                    System.out.println("Proveedor insertado correctamente");
                    break;

                case 3:
                    String cifBuscar = EntradaTexto.pedirString("CIF del proveedor a buscar: ");
                    Proveedores proveedorBuscar = prDao.buscarPorId(cifBuscar);
                    System.out.println(proveedorBuscar != null ? proveedorBuscar : "No existe ese proveedor");
                    break;

                case 4:
                    String cifActualizar = EntradaTexto.pedirString("CIF del proveedor a actualizar: ");
                    Proveedores proveedorActualizar = prDao.buscarPorId(cifActualizar);

                    if (proveedorActualizar != null) {
                        proveedorActualizar.setCif(EntradaTexto.pedirString("Nuevo CIF (" + proveedorActualizar.getCif() + "): "));
                        proveedorActualizar.setNombre(EntradaTexto.pedirString("Nuevo nombre (" + proveedorActualizar.getNombre() + "): "));
                        proveedorActualizar.setDireccion(EntradaTexto.pedirString("Nueva dirección (" + proveedorActualizar.getDireccion() + "): "));
                        proveedorActualizar.setTelefono(EntradaTexto.pedirString("Nuevo teléfono (" + proveedorActualizar.getTelefono() + "): "));
                        proveedorActualizar.setEmail(EntradaTexto.pedirString("Nuevo email (" + proveedorActualizar.getEmail() + "): "));

                        prDao.actualizar(cifActualizar, proveedorActualizar);
                        System.out.println("Proveedor actualizado correctamente");
                    } else {
                        System.out.println("Proveedor no encontrado");
                    }
                    break;

                case 5:
                    String cifEliminar = EntradaTexto.pedirString("CIF del proveedor a eliminar: ");
                    System.out.println(prDao.eliminar(cifEliminar) ? "Proveedor eliminado correctamente" : "No existe ese proveedor");
                    break;

                case 0:
                    System.out.println("Saliendo del menú de proveedores...");
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }

        } while (opProv != 0);
    }
}