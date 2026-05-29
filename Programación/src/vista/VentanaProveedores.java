package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import dao.ProveedoresDAO;
import modelo.Proveedores;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

public class VentanaProveedores extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCif;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTable tablaProveedores;
    private DefaultTableModel modeloTabla;

    private ProveedoresDAO pDao = new ProveedoresDAO();

    public VentanaProveedores() {

        Color AZUL = new Color(0x0f4c81);
        Color NARANJA = new Color(0xfa6c07);
        Color FONDO = new Color(0xf4f6fb);
        Color BLANCO = Color.WHITE;
        Color TEXTO = new Color(0x1f2937);
        Color GRIS = new Color(0xe5e7eb);

        setTitle("Gestión de proveedores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 560);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(FONDO);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE PROVEEDORES");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setForeground(AZUL);
        lblTitulo.setBounds(260, 20, 380, 30);
        contentPane.add(lblTitulo);

        JLabel lblCif = new JLabel("CIF:");
        lblCif.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblCif.setForeground(TEXTO);
        lblCif.setBounds(40, 80, 80, 20);
        contentPane.add(lblCif);

        txtCif = new JTextField();
        txtCif.setBounds(130, 80, 190, 30);
        txtCif.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtCif.setBorder(BorderFactory.createLineBorder(GRIS));
        txtCif.setColumns(10);
        contentPane.add(txtCif);

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

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblDireccion.setForeground(TEXTO);
        lblDireccion.setBounds(40, 170, 80, 20);
        contentPane.add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(130, 170, 190, 30);
        txtDireccion.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtDireccion.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtDireccion);

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

        JButton btnBuscar = new JButton("Buscar por CIF");
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
        modeloTabla.addColumn("CIF");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Email");

        tablaProveedores = new JTable(modeloTabla);
        tablaProveedores.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaProveedores.setRowHeight(28);
        tablaProveedores.setBackground(BLANCO);
        tablaProveedores.setForeground(TEXTO);
        tablaProveedores.setSelectionBackground(new Color(220, 235, 255));
        tablaProveedores.setSelectionForeground(TEXTO);
        tablaProveedores.setGridColor(GRIS);
        tablaProveedores.setShowVerticalLines(false);

        JTableHeader header = tablaProveedores.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 13));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(AZUL);
        headerRenderer.setForeground(BLANCO);
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setOpaque(true);

        tablaProveedores.getColumnModel().getColumn(0).setHeaderRenderer(headerRenderer);
        tablaProveedores.getColumnModel().getColumn(1).setHeaderRenderer(headerRenderer);
        tablaProveedores.getColumnModel().getColumn(2).setHeaderRenderer(headerRenderer);
        tablaProveedores.getColumnModel().getColumn(3).setHeaderRenderer(headerRenderer);
        tablaProveedores.getColumnModel().getColumn(4).setHeaderRenderer(headerRenderer);

        scrollPane.setViewportView(tablaProveedores);

        btnBuscar.addActionListener(e -> {
            try {
                String cif = txtCif.getText().trim();
                Proveedores p = pDao.buscarPorId(cif);

                if (p != null) {
                    txtNombre.setText(p.getNombre());
                    txtDireccion.setText(p.getDireccion());
                    txtTelefono.setText(p.getTelefono());
                    txtEmail.setText(p.getEmail());
                } else {
                    JOptionPane.showMessageDialog(this, "Proveedor no encontrado");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnInsertar.addActionListener(e -> {
            try {
                Proveedores nuevo = new Proveedores(
                        txtCif.getText().trim(),
                        txtNombre.getText().trim(),
                        txtDireccion.getText().trim(),
                        txtTelefono.getText().trim(),
                        txtEmail.getText().trim()
                );

                pDao.insertar(nuevo);
                JOptionPane.showMessageDialog(this, "Proveedor insertado correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                int fila = tablaProveedores.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                String cif = tablaProveedores.getValueAt(fila, 0).toString();

                Proveedores proveedor = new Proveedores(
                        cif,
                        txtNombre.getText().trim(),
                        txtDireccion.getText().trim(),
                        txtTelefono.getText().trim(),
                        txtEmail.getText().trim()
                );

                pDao.actualizar(cif, proveedor);
                JOptionPane.showMessageDialog(this, "Proveedor actualizado correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int fila = tablaProveedores.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                String cif = tablaProveedores.getValueAt(fila, 0).toString();
                boolean eliminado = pDao.eliminar(cif);

                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Proveedor eliminado correctamente");
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el proveedor");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());

        cargarTabla();
    }

    private void limpiarCampos() {
        txtCif.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }

    private void mostrarError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void cargarTabla() {
        try {
            modeloTabla.setRowCount(0);

            for (Proveedores p : pDao.getProveedores()) {
                Object[] fila = {
                        p.getCif(),
                        p.getNombre(),
                        p.getDireccion(),
                        p.getTelefono(),
                        p.getEmail()
                };
                modeloTabla.addRow(fila);
            }

        } catch (Exception ex) {
            mostrarError(ex);
        }
    }
}