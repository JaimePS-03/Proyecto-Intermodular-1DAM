package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import dao.ClienteDAO;
import modelo.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

public class VentanaClientes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    private ClienteDAO cDao = new ClienteDAO();

    public VentanaClientes() {

        Color AZUL = new Color(0x0f4c81);
        Color NARANJA = new Color(0xfa6c07);
        Color FONDO = new Color(0xf4f6fb);
        Color BLANCO = Color.WHITE;
        Color TEXTO = new Color(0x1f2937);
        Color GRIS = new Color(0xe5e7eb);

        setTitle("Gestión de clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 560);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(FONDO);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE CLIENTES");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setForeground(AZUL);
        lblTitulo.setBounds(300, 20, 320, 30);
        contentPane.add(lblTitulo);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblDni.setForeground(TEXTO);
        lblDni.setBounds(40, 80, 80, 20);
        contentPane.add(lblDni);

        txtDni = new JTextField();
        txtDni.setBounds(130, 80, 190, 30);
        txtDni.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtDni.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtDni);
        txtDni.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblNombre.setForeground(TEXTO);
        lblNombre.setBounds(40, 125, 80, 20);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130, 125, 190, 30);
        txtNombre.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtNombre.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtNombre);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblApellidos.setForeground(TEXTO);
        lblApellidos.setBounds(40, 170, 80, 20);
        contentPane.add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(130, 170, 190, 30);
        txtApellidos.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtApellidos.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtApellidos);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTelefono.setForeground(TEXTO);
        lblTelefono.setBounds(40, 215, 80, 20);
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(130, 215, 190, 30);
        txtTelefono.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtTelefono.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtTelefono);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblEmail.setForeground(TEXTO);
        lblEmail.setBounds(40, 260, 80, 20);
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(130, 260, 190, 30);
        txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtEmail.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtEmail);

        JButton btnBuscar = new JButton("Buscar por DNI");
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
        btnEliminar.setBounds(470, 190, 130, 35);
        btnEliminar.setBackground(NARANJA);
        btnEliminar.setForeground(BLANCO);
        btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setContentAreaFilled(true);
        btnEliminar.setOpaque(true);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(40, 325, 810, 160);
        scrollPane.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(scrollPane);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("DNI");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Email");

        tablaClientes = new JTable(modeloTabla);
        tablaClientes.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaClientes.setRowHeight(28);
        tablaClientes.setBackground(BLANCO);
        tablaClientes.setForeground(TEXTO);
        tablaClientes.setSelectionBackground(new Color(220, 235, 255));
        tablaClientes.setSelectionForeground(TEXTO);
        tablaClientes.setGridColor(GRIS);
        tablaClientes.setShowVerticalLines(false);

        JTableHeader header = tablaClientes.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 13));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(AZUL);
        headerRenderer.setForeground(BLANCO);
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setOpaque(true);

        tablaClientes.getColumnModel().getColumn(0).setHeaderRenderer(headerRenderer);
        tablaClientes.getColumnModel().getColumn(1).setHeaderRenderer(headerRenderer);
        tablaClientes.getColumnModel().getColumn(2).setHeaderRenderer(headerRenderer);
        tablaClientes.getColumnModel().getColumn(3).setHeaderRenderer(headerRenderer);
        tablaClientes.getColumnModel().getColumn(4).setHeaderRenderer(headerRenderer);

        scrollPane.setViewportView(tablaClientes);

        btnBuscar.addActionListener(e -> {
            try {
                String id = txtDni.getText().trim();
                Cliente c = cDao.buscarPorId(id);

                if (c != null) {
                    txtNombre.setText(c.getNombre());
                    txtApellidos.setText(c.getApellidos());
                    txtTelefono.setText(c.getTelefono());
                    txtEmail.setText(c.getEmail());
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente no encontrado");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnInsertar.addActionListener(e -> {
            try {
                Cliente nuevo = new Cliente(
                    txtDni.getText().trim(),
                    txtNombre.getText().trim(),
                    txtApellidos.getText().trim(),
                    txtTelefono.getText().trim(),
                    txtEmail.getText().trim()
                );

                cDao.insertar(nuevo);
                JOptionPane.showMessageDialog(this, "Cliente insertado correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                int fila = tablaClientes.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                String id = tablaClientes.getValueAt(fila, 0).toString();

                Cliente cliente = new Cliente(
                    id,
                    txtNombre.getText().trim(),
                    txtApellidos.getText().trim(),
                    txtTelefono.getText().trim(),
                    txtEmail.getText().trim()
                );

                cDao.actualizar(id, cliente);
                JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int fila = tablaClientes.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                String id = tablaClientes.getValueAt(fila, 0).toString();
                boolean eliminado = cDao.eliminar(id);

                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el cliente");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());

        cargarTabla();
    }

    private void limpiarCampos() {
        txtDni.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }

    private void mostrarError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void cargarTabla() {
        try {
            modeloTabla.setRowCount(0);

            for (Cliente c : cDao.getClientes()) {
                Object[] fila = {
                    c.getId(),
                    c.getNombre(),
                    c.getApellidos(),
                    c.getTelefono(),
                    c.getEmail()
                };
                modeloTabla.addRow(fila);
            }

        } catch (Exception ex) {
            mostrarError(ex);
        }
    }
}