package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import controlador.ClienteDAO;
import controlador.ReservaDAO;
import modelo.Cliente;
import modelo.Reserva;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.sql.Timestamp;
import java.util.ArrayList;

public class VentanaReservas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtPersonas;
    private JTextField txtTipo;
    private JTextField txtFecha;
    private JTextField txtIdCliente;
    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;

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

        JButton btnBuscar = new JButton("Buscar por ID");
        btnBuscar.setBounds(380, 70, 130, 30);
        contentPane.add(btnBuscar);

        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.setBounds(530, 70, 130, 30);
        contentPane.add(btnInsertar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(380, 120, 130, 30);
        contentPane.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(530, 120, 130, 30);
        contentPane.add(btnEliminar);

        JButton btnListarCliente = new JButton("Reservas por cliente");
        btnListarCliente.setBounds(455, 170, 180, 30);
        contentPane.add(btnListarCliente);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(455, 220, 180, 30);
        contentPane.add(btnLimpiar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 300, 770, 180);
        contentPane.add(scrollPane);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nº personas");
        modeloTabla.addColumn("Tipo reserva");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("ID cliente");

        tablaReservas = new JTable(modeloTabla);
        scrollPane.setViewportView(tablaReservas);

        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                Reserva r = rDao.buscarPorId(id);

                if (r != null) {
                    txtPersonas.setText(String.valueOf(r.getnPersonas()));
                    txtTipo.setText(r.getTipoReserva());
                    txtFecha.setText(String.valueOf(r.getFecha()));
                    txtIdCliente.setText(r.getIdCliente());
                } else {
                    JOptionPane.showMessageDialog(this, "No existe la reserva");
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
                    JOptionPane.showMessageDialog(this, "No hay cliente con ese ID");
                    return;
                }

                Reserva r = new Reserva();
                r.setId(Integer.parseInt(txtId.getText().trim()));
                r.setnPersonas(Integer.parseInt(txtPersonas.getText().trim()));
                r.setTipoReserva(txtTipo.getText().trim());
                r.setFecha(Timestamp.valueOf(txtFecha.getText().trim()));
                r.setIdCliente(idCliente);

                rDao.insertar(r);
                JOptionPane.showMessageDialog(this, "Reserva insertada correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                int fila = tablaReservas.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                int id = Integer.parseInt(tablaReservas.getValueAt(fila, 0).toString());
                String idCliente = txtIdCliente.getText().trim();
                Cliente c = cDao.buscarPorId(idCliente);

                if (c == null) {
                    JOptionPane.showMessageDialog(this, "No hay cliente con ese ID");
                    return;
                }

                Reserva r = new Reserva();
                r.setId(id);
                r.setnPersonas(Integer.parseInt(txtPersonas.getText().trim()));
                r.setTipoReserva(txtTipo.getText().trim());
                r.setFecha(Timestamp.valueOf(txtFecha.getText().trim()));
                r.setIdCliente(idCliente);

                rDao.actualizar(id, r);
                JOptionPane.showMessageDialog(this, "Reserva actualizada correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int fila = tablaReservas.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                int id = Integer.parseInt(tablaReservas.getValueAt(fila, 0).toString());
                boolean eliminado = rDao.eliminar(id);

                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Reserva eliminada correctamente");
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No existe la reserva");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnListarCliente.addActionListener(e -> {
            try {
                String idCliente = txtIdCliente.getText().trim();
                ArrayList<Reserva> lista = rDao.listarPorCliente(idCliente);

                modeloTabla.setRowCount(0);

                if (lista != null && !lista.isEmpty()) {
                    for (Reserva r : lista) {
                        Object[] fila = {
                            r.getId(),
                            r.getnPersonas(),
                            r.getTipoReserva(),
                            r.getFecha(),
                            r.getIdCliente()
                        };
                        modeloTabla.addRow(fila);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Sin reservas para ese cliente");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());

        cargarTabla();
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtPersonas.setText("");
        txtTipo.setText("");
        txtFecha.setText("");
        txtIdCliente.setText("");
    }

    private void mostrarError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Este método recarga todas las reservas en la tabla para que siempre se
    // vea la información actual después de insertar, actualizar o eliminar.
    private void cargarTabla() {
        try {
            modeloTabla.setRowCount(0);

            for (Reserva r : rDao.getReservas()) {
                Object[] fila = {
                    r.getId(),
                    r.getnPersonas(),
                    r.getTipoReserva(),
                    r.getFecha(),
                    r.getIdCliente()
                };
                modeloTabla.addRow(fila);
            }
        } catch (Exception ex) {
            mostrarError(ex);
        }
    }
}