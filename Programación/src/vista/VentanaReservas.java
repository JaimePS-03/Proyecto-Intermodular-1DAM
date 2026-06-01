package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import dao.ClienteDAO;
import dao.ReservasDAO;
import modelo.Cliente;
import modelo.Reserva;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Cursor;
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

    private ReservasDAO rDao = new ReservasDAO();
    private ClienteDAO cDao = new ClienteDAO();

    public VentanaReservas() {

        Color AZUL = new Color(0x0f4c81);
        Color NARANJA = new Color(0xfa6c07);
        Color FONDO = new Color(0xf4f6fb);
        Color BLANCO = Color.WHITE;
        Color TEXTO = new Color(0x1f2937);
        Color GRIS = new Color(0xe5e7eb);

        setTitle("Gestión de reservas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 560);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(FONDO);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE RESERVAS");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setForeground(AZUL);
        lblTitulo.setBounds(290, 20, 320, 30);
        contentPane.add(lblTitulo);

        JLabel lblId = new JLabel("ID reserva:");
        lblId.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblId.setForeground(TEXTO);
        lblId.setBounds(40, 80, 100, 20);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(140, 80, 180, 30);
        txtId.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtId.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtId);

        JLabel lblPersonas = new JLabel("Nº personas:");
        lblPersonas.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblPersonas.setForeground(TEXTO);
        lblPersonas.setBounds(40, 125, 100, 20);
        contentPane.add(lblPersonas);

        txtPersonas = new JTextField();
        txtPersonas.setBounds(140, 125, 180, 30);
        txtPersonas.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtPersonas.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtPersonas);

        JLabel lblTipo = new JLabel("Tipo reserva:");
        lblTipo.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTipo.setForeground(TEXTO);
        lblTipo.setBounds(40, 170, 100, 20);
        contentPane.add(lblTipo);

        txtTipo = new JTextField();
        txtTipo.setBounds(140, 170, 180, 30);
        txtTipo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtTipo.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtTipo);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblFecha.setForeground(TEXTO);
        lblFecha.setBounds(40, 215, 100, 20);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(140, 215, 180, 30);
        txtFecha.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtFecha.setBorder(BorderFactory.createLineBorder(GRIS));
        txtFecha.setText("2026-05-14 14:00:00");
        contentPane.add(txtFecha);

        JLabel lblIdCliente = new JLabel("ID cliente:");
        lblIdCliente.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblIdCliente.setForeground(TEXTO);
        lblIdCliente.setBounds(40, 260, 100, 20);
        contentPane.add(lblIdCliente);

        txtIdCliente = new JTextField();
        txtIdCliente.setBounds(140, 260, 180, 30);
        txtIdCliente.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtIdCliente.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtIdCliente);

        JButton btnBuscar = new JButton("Buscar por ID");
        btnBuscar.setBounds(380, 80, 150, 35);
        btnBuscar.setBackground(AZUL);
        btnBuscar.setForeground(BLANCO);
        btnBuscar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(true);
        btnBuscar.setOpaque(true);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnBuscar);

        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.setBounds(560, 80, 130, 35);
        btnInsertar.setBackground(AZUL);
        btnInsertar.setForeground(BLANCO);
        btnInsertar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnInsertar.setFocusPainted(false);
        btnInsertar.setBorderPainted(false);
        btnInsertar.setContentAreaFilled(true);
        btnInsertar.setOpaque(true);
        btnInsertar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnInsertar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(380, 135, 150, 35);
        btnActualizar.setBackground(AZUL);
        btnActualizar.setForeground(BLANCO);
        btnActualizar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setBorderPainted(false);
        btnActualizar.setContentAreaFilled(true);
        btnActualizar.setOpaque(true);
        btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnActualizar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(560, 135, 130, 35);
        btnLimpiar.setBackground(AZUL);
        btnLimpiar.setForeground(BLANCO);
        btnLimpiar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setContentAreaFilled(true);
        btnLimpiar.setOpaque(true);
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnLimpiar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(380, 190, 150, 35);
        btnEliminar.setBackground(NARANJA);
        btnEliminar.setForeground(BLANCO);
        btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setContentAreaFilled(true);
        btnEliminar.setOpaque(true);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JButton btnListarCliente = new JButton("Reservas por cliente");
        btnListarCliente.setBounds(560, 190, 180, 35);
        btnListarCliente.setBackground(AZUL);
        btnListarCliente.setForeground(BLANCO);
        btnListarCliente.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnListarCliente.setFocusPainted(false);
        btnListarCliente.setBorderPainted(false);
        btnListarCliente.setContentAreaFilled(true);
        btnListarCliente.setOpaque(true);
        btnListarCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnListarCliente);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(40, 325, 810, 160);
        scrollPane.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(scrollPane);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nº personas");
        modeloTabla.addColumn("Tipo reserva");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("ID cliente");

        tablaReservas = new JTable(modeloTabla);
        tablaReservas.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaReservas.setRowHeight(28);
        tablaReservas.setBackground(BLANCO);
        tablaReservas.setForeground(TEXTO);
        tablaReservas.setSelectionBackground(new Color(220, 235, 255));
        tablaReservas.setSelectionForeground(TEXTO);
        tablaReservas.setGridColor(GRIS);
        tablaReservas.setShowVerticalLines(false);

        JTableHeader header = tablaReservas.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 13));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(AZUL);
        headerRenderer.setForeground(BLANCO);
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setOpaque(true);

        tablaReservas.getColumnModel().getColumn(0).setHeaderRenderer(headerRenderer);
        tablaReservas.getColumnModel().getColumn(1).setHeaderRenderer(headerRenderer);
        tablaReservas.getColumnModel().getColumn(2).setHeaderRenderer(headerRenderer);
        tablaReservas.getColumnModel().getColumn(3).setHeaderRenderer(headerRenderer);
        tablaReservas.getColumnModel().getColumn(4).setHeaderRenderer(headerRenderer);

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