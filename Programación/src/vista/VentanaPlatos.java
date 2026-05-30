package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import dao.PlatosDAO;
import modelo.Plato;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

public class VentanaPlatos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtTipo;
    private JTable tablaPlatos;
    private DefaultTableModel modeloTabla;

    private PlatosDAO pDao = new PlatosDAO();

    public VentanaPlatos() {

        Color AZUL = new Color(0x0f4c81);
        Color NARANJA = new Color(0xfa6c07);
        Color FONDO = new Color(0xf4f6fb);
        Color BLANCO = Color.WHITE;
        Color TEXTO = new Color(0x1f2937);
        Color GRIS = new Color(0xe5e7eb);

        setTitle("Gestión de platos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 560);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(FONDO);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE PLATOS");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setForeground(AZUL);
        lblTitulo.setBounds(310, 20, 300, 30);
        contentPane.add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblId.setForeground(TEXTO);
        lblId.setBounds(40, 80, 80, 20);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(130, 80, 190, 30);
        txtId.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtId.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtId);

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

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblPrecio.setForeground(TEXTO);
        lblPrecio.setBounds(40, 170, 80, 20);
        contentPane.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(130, 170, 190, 30);
        txtPrecio.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtPrecio.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtPrecio);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTipo.setForeground(TEXTO);
        lblTipo.setBounds(40, 215, 80, 20);
        contentPane.add(lblTipo);

        txtTipo = new JTextField();
        txtTipo.setBounds(130, 215, 190, 30);
        txtTipo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtTipo.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtTipo);

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
        scrollPane.setBounds(40, 310, 810, 160);
        scrollPane.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(scrollPane);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Tipo");

        tablaPlatos = new JTable(modeloTabla);
        tablaPlatos.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaPlatos.setRowHeight(28);
        tablaPlatos.setBackground(BLANCO);
        tablaPlatos.setForeground(TEXTO);
        tablaPlatos.setSelectionBackground(new Color(220, 235, 255));
        tablaPlatos.setSelectionForeground(TEXTO);
        tablaPlatos.setGridColor(GRIS);
        tablaPlatos.setShowVerticalLines(false);

        JTableHeader header = tablaPlatos.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 13));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(AZUL);
        headerRenderer.setForeground(BLANCO);
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setOpaque(true);

        tablaPlatos.getColumnModel().getColumn(0).setHeaderRenderer(headerRenderer);
        tablaPlatos.getColumnModel().getColumn(1).setHeaderRenderer(headerRenderer);
        tablaPlatos.getColumnModel().getColumn(2).setHeaderRenderer(headerRenderer);
        tablaPlatos.getColumnModel().getColumn(3).setHeaderRenderer(headerRenderer);

        scrollPane.setViewportView(tablaPlatos);

        btnBuscar.addActionListener(e -> {
            try {
                Plato p = pDao.buscarPorId(txtId.getText().trim());

                if (p != null) {
                    txtNombre.setText(p.getNombre());
                    txtPrecio.setText(String.valueOf(p.getPrecio()));
                    txtTipo.setText(p.getTipo());
                } else {
                    JOptionPane.showMessageDialog(this, "No existe ese plato");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnInsertar.addActionListener(e -> {
            try {
                String id = txtId.getText().trim();
                String nombre = txtNombre.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                String tipo = txtTipo.getText().trim();

                Plato p = new Plato(id, nombre, precio, tipo);
                pDao.insertar(p);

                JOptionPane.showMessageDialog(this, "Plato insertado correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                int fila = tablaPlatos.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                String id = tablaPlatos.getValueAt(fila, 0).toString();

                Plato p = new Plato(
                    id,
                    txtNombre.getText().trim(),
                    Double.parseDouble(txtPrecio.getText().trim()),
                    txtTipo.getText().trim()
                );

                pDao.actualizar(id, p);
                JOptionPane.showMessageDialog(this, "Plato actualizado correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int fila = tablaPlatos.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                String id = tablaPlatos.getValueAt(fila, 0).toString();
                boolean eliminado = pDao.eliminar(id);

                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Plato eliminado correctamente");
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No existe el plato");
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
        txtNombre.setText("");
        txtPrecio.setText("");
        txtTipo.setText("");
    }

    private void mostrarError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void cargarTabla() {
        try {
            modeloTabla.setRowCount(0);

            for (Plato p : pDao.getPlatos()) {
                Object[] fila = {
                    p.getId(),
                    p.getNombre(),
                    p.getPrecio(),
                    p.getTipo()
                };
                modeloTabla.addRow(fila);
            }
        } catch (Exception ex) {
            mostrarError(ex);
        }
    }
}