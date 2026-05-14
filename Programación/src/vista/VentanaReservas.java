package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dao.ClienteDAO;
import dao.ReservaDAO;
import modelo.Cliente;
import modelo.Reserva;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.sql.Timestamp;

public class VentanaReservas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtPersonas;
    private JTextField txtTipo;
    private JTextField txtFecha;
    private JTextField txtIdCliente;
    private JTextArea txtResultado;

    private ReservaDAO rDao = new ReservaDAO();
    private ClienteDAO cDao = new ClienteDAO();

    public VentanaReservas() {
        setTitle("Gestión de reservas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 850, 560);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE RESERVAS");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(280, 10, 280, 30);
        contentPane.add(lblTitulo);

        JLabel lblId = new JLabel("ID reserva:");
        lblId.setBounds(30, 70, 100, 20);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(140, 70, 180, 25);
        contentPane.add(txtId);

        JLabel lblPersonas = new JLabel("Nº personas:");
        lblPersonas.setBounds(30, 110, 100, 20);
        contentPane.add(lblPersonas);

        txtPersonas = new JTextField();
        txtPersonas.setBounds(140, 110, 180, 25);
        contentPane.add(txtPersonas);

        JLabel lblTipo = new JLabel("Tipo reserva:");
        lblTipo.setBounds(30, 150, 100, 20);
        contentPane.add(lblTipo);

        txtTipo = new JTextField();
        txtTipo.setBounds(140, 150, 180, 25);
        contentPane.add(txtTipo);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(30, 190, 100, 20);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(140, 190, 180, 25);
        txtFecha.setText("2026-05-14 14:00:00");
        contentPane.add(txtFecha);

        JLabel lblIdCliente = new JLabel("ID cliente:");
        lblIdCliente.setBounds(30, 230, 100, 20);
        contentPane.add(lblIdCliente);

        txtIdCliente = new JTextField();
        txtIdCliente.setBounds(140, 230, 180, 25);
        contentPane.add(txtIdCliente);

        JButton btnListar = new JButton("Listar");
        btnListar.setBounds(380, 70, 130, 30);
        contentPane.add(btnListar);

        JButton btnBuscar = new JButton("Buscar por ID");
        btnBuscar.setBounds(530, 70, 130, 30);
        contentPane.add(btnBuscar);

        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.setBounds(380, 120, 130, 30);
        contentPane.add(btnInsertar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(530, 120, 130, 30);
        contentPane.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(380, 170, 130, 30);
        contentPane.add(btnEliminar);

        JButton btnListarCliente = new JButton("Reservas por cliente");
        btnListarCliente.setBounds(530, 170, 160, 30);
        contentPane.add(btnListarCliente);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(380, 220, 130, 30);
        contentPane.add(btnLimpiar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 300, 770, 180);
        contentPane.add(scrollPane);

        txtResultado = new JTextArea();
        scrollPane.setViewportView(txtResultado);

        btnListar.addActionListener(e -> {
            try {
                txtResultado.setText("");
                rDao.listar();
                txtResultado.setText("Se ha ejecutado listar(). Si el DAO imprime por consola, tendrás que adaptarlo más tarde.");
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                Reserva r = rDao.buscarPorId(id);

                if (r != null) {
                    txtPersonas.setText(String.valueOf(r.getnPersonas()));
                    txtTipo.setText(r.getTipoReserva());
                    txtFecha.setText(String.valueOf(r.getFecha()));
                    txtIdCliente.setText(r.getIdCliente());
                    txtResultado.setText(r.toString());
                } else {
                    txtResultado.setText("No existe la reserva");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnInsertar.addActionListener(e -> {
            try {
                String idCliente = txtIdCliente.getText().trim();
                Cliente c = cDao.buscarPorId(idCliente);

                if (c == null) {
                    txtResultado.setText("No hay cliente con ese ID");
                    return;
                }

                Reserva r = new Reserva();
                r.setId(Integer.parseInt(txtId.getText().trim()));
                r.setnPersonas(Integer.parseInt(txtPersonas.getText().trim()));
                r.setTipoReserva(txtTipo.getText().trim());
                r.setFecha(Timestamp.valueOf(txtFecha.getText().trim()));
                r.setIdCliente(idCliente);

                rDao.insertar(r);
                txtResultado.setText("Reserva insertada correctamente");
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                Reserva r = rDao.buscarPorId(id);

                if (r != null) {
                    String idCliente = txtIdCliente.getText().trim();
                    Cliente c = cDao.buscarPorId(idCliente);

                    if (c == null) {
                        txtResultado.setText("No hay cliente con ese ID");
                        return;
                    }

                    r.setnPersonas(Integer.parseInt(txtPersonas.getText().trim()));
                    r.setTipoReserva(txtTipo.getText().trim());
                    r.setFecha(Timestamp.valueOf(txtFecha.getText().trim()));
                    r.setIdCliente(idCliente);

                    rDao.actualizar(id, r);
                    txtResultado.setText("Reserva actualizada correctamente");
                } else {
                    txtResultado.setText("No existe la reserva");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                boolean eliminado = rDao.eliminar(id);
                txtResultado.setText(eliminado ? "Reserva eliminada correctamente" : "No existe la reserva");
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnListarCliente.addActionListener(e -> {
            try {
                String idCliente = txtIdCliente.getText().trim();
                var lista = rDao.listarPorCliente(idCliente);

                if (lista != null && !lista.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (Reserva r : lista) {
                        sb.append(r).append("\n");
                    }
                    txtResultado.setText(sb.toString());
                } else {
                    txtResultado.setText("Sin reservas para ese cliente");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtPersonas.setText("");
        txtTipo.setText("");
        txtFecha.setText("");
        txtIdCliente.setText("");
        txtResultado.setText("");
    }

    private void mostrarError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}