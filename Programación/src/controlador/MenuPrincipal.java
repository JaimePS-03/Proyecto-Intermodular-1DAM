package controlador;

import dao.ClienteDAO;
import dao.PlatoDAO;
import dao.ReservaDAO;
import modelo.Cliente;
import modelo.Plato;
import modelo.Reserva;
import util.EntradaTexto;

import java.sql.Timestamp;

public class MenuPrincipal {

    // Creación de los controladores de los diferentes modelos.
    private static final ClienteDAO cDao = new ClienteDAO();
    private static final ReservaDAO rDao = new ReservaDAO();
    private static final PlatoDAO pDao = new PlatoDAO();

    public static void main(String[] args){
        int opcion = 0;
        do{
            mostrarMenuPrincipal();
            opcion = EntradaTexto.pedirInt("Dame una opción");
            switch(opcion){
                case 1:
                    int opCli;
                    do{
                        cDao.menu();
                        opCli = EntradaTexto.pedirInt("Opción: ");

                        switch(opCli) {
                            case 1:
                                cDao.listar();
                                break;
                            case 2:
                                cDao.insertar(cDao.crearCliente());
                                break;
                            case 3:
                                Cliente buscarCli = cDao.buscarPorId(EntradaTexto.pedirString("ID del cliente"));
                                System.out.println(buscarCli != null ? buscarCli : "No encontrado");
                                break;
                            case 4:
                                String id = EntradaTexto.pedirString("ID del cliente a actualizar");
                                Cliente buscarActuCli = cDao.buscarPorId(id);
                                if(buscarActuCli != null){
                                    buscarActuCli.setNombre(EntradaTexto.pedirString("Nuevo nombre (" + buscarActuCli.getNombre() + "): "));
                                    buscarActuCli.setApellidos(EntradaTexto.pedirString("Nuevo apellido (" + buscarActuCli.getApellidos() + "): "));
                                    buscarActuCli.setTelefono(EntradaTexto.pedirString("Nuevo telefono (" + buscarActuCli.getTelefono() + "): "));
                                    buscarActuCli.setEmail(EntradaTexto.pedirString("Nuevo email (" + buscarActuCli.getEmail() + "): "));
                                    cDao.actualizar(id, buscarActuCli);
                                }
                                break;
                            case 5:
                                System.out.println(cDao.eliminar(EntradaTexto.pedirString("Id del cliente ha eliminar")) ? "Eliminado" : "No encontrado");
                                break;
                            case 0:
                                System.out.println("Saliendo...");
                                break;
                        }
                    }while(opCli != 0);
                    break;
                case 2:
                    int opPlato;
                    do{
                        pDao.menu();
                        opPlato = EntradaTexto.pedirInt("Opción: ");
                        switch(opPlato) {
                            case 1:
                                pDao.listar();
                                break;
                            case 2:
                                pDao.insertar(pDao.generarPlato());
                                break;
                            case 3:
                                Plato buscarPlato = pDao.buscarPorId(EntradaTexto.pedirString("ID plato: "));
                                System.out.println(buscarPlato != null ? buscarPlato : "No existe ese plato");
                                break;
                            case 4:
                                String eliPlato = EntradaTexto.pedirString("Id de la reserva");
                                System.out.println((pDao.eliminar(eliPlato) ? "Plato eliminado con éxito" : "No existe el plato"));
                                break;
                            case 5:
                                String id = EntradaTexto.pedirString("ID de la reserva a actualizar: ");
                                Plato platoActualizar = pDao.buscarPorId(id);
                                if (platoActualizar != null) {
                                    platoActualizar.setNombre(EntradaTexto.pedirString("Nuevo nombre del plato (" + platoActualizar.getNombre() + "): "));
                                    platoActualizar.setPrecio(EntradaTexto.pedirDouble("Nuevo precio (" + platoActualizar.getPrecio() + "): "));
                                    platoActualizar.setTipo(EntradaTexto.pedirString("Nuevo tipo de plato (" + platoActualizar.getTipo() + "): "));
                                    pDao.actualizar(id, platoActualizar);
                                }
                                break;
                            case 0:
                                System.out.println("Saliendo de las platos...");
                                break;
                            default:
                                System.out.println("Opción no válida");
                                break;
                        }
                    } while (opPlato != 0);
                    break;
                case 3:
                    int opReserva;
                    do {
                        rDao.menu();
                        opReserva = EntradaTexto.pedirInt("Opción: ");

                        switch(opReserva) {
                            case 1:
                                rDao.listar();
                                break;
                            case 2:
                                String idClienteReserva = EntradaTexto.pedirString("ID cliente: ");
                                Cliente c = cDao.buscarPorId(idClienteReserva);
                                if(c != null){
                                    rDao.insertar(rDao.generarReserva(idClienteReserva));
                                }else{
                                    System.out.println("No hay clientes con esa id");
                                }
                                break;
                            case 3:
                                String idCli = EntradaTexto.pedirString("ID cliente: ");
                                var resCli = rDao.listarPorCliente(idCli);
                                if (resCli != null) resCli.forEach(System.out::println);
                                else System.out.println("Sin reservas");
                                break;
                            case 4:
                                int eliReserva = EntradaTexto.pedirInt("Id de la reserva");
                                rDao.eliminar(eliReserva);
                                break;
                            case 5:
                                int id = EntradaTexto.pedirInt("ID de la reserva a actualizar: ");
                                Reserva r = rDao.buscarPorId(id);
                                if (r != null) {
                                    r.setnPersonas(EntradaTexto.pedirInt("Nueva cantidad de comensales (" + r.getnPersonas() + "): "));
                                    r.setTipoReserva(EntradaTexto.pedirString("Nuevo tipo de reserva (" + r.getTipoReserva() + "): "));
                                    do {
                                        String idCliReserva = EntradaTexto.pedirString("ID cliente: ");
                                        Cliente clienteReserva = cDao.buscarPorId(idCliReserva);
                                        if (clienteReserva != null) {
                                            r.setIdCliente(idCliReserva);
                                            break;
                                        } else {
                                            System.out.println("No hay cliente con ese ID");
                                            break;
                                        }
                                    } while (true);
                                    rDao.actualizar(id, r);
                                }
                                break;
                            case 0:
                                System.out.println("Saliendo de las reservas...");
                                break;
                            default:
                                System.out.println("Opción no válida");
                                break;
                        }
                    } while (opReserva != 0);
                    break;
                default:
                    System.out.println("Opcion no válida");
                    break;
            }
        }while(opcion != 0);
    }

    /**
     * Imprimir el menú principal
     */
    public static void mostrarMenuPrincipal(){
        System.out.println("----- MENÚ PRINCIPAL -----\n" +
                "1) Clientes\n" +
                "2) Platos\n" +
                "3) Reservas\n" +
                "4) WIP\n" +
                "5) WIP\n" +
                "6) WIP\n" +
                "7) WIP\n" +
                "0) Salir");
    }




}