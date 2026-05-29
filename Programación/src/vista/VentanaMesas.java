package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import dao.MesaDAO;
import modelo.Mesa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

public class VentanaMesas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdMesa;
    private JTextField txtPersonas;
    private JTable tablaMesas;
    private DefaultTableModel modeloTabla;

    private MesaDAO mDao = new MesaDAO();

    public VentanaMesas() {

        Color AZUL = new Color(0x0f4c81);
        Color NARANJA = new Color(0xfa6c07);
        Color FONDO = new Color(0xf4f6fb);
        Color BLANCO = Color.WHITE;
        Color TEXTO = new Color(0x1f2937);
        Color GRIS = new Color(0xe5e7eb);

        setTitle("Gestión de mesas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 560);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(FONDO);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE MESAS");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setForeground(AZUL);
        lblTitulo.setBounds(315, 20, 300, 30);
        contentPane.add(lblTitulo);

        JLabel lblIdMesa = new JLabel("Nº mesa:");
        lblIdMesa.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblIdMesa.setForeground(TEXTO);
        lblIdMesa.setBounds(40, 80, 100, 20);
        contentPane.add(lblIdMesa);

        txtIdMesa = new JTextField();
        txtIdMesa.setBounds(130, 80, 190, 30);
        txtIdMesa.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtIdMesa.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtIdMesa);

        JLabel lblPersonas = new JLabel("Capacidad:");
        lblPersonas.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblPersonas.setForeground(TEXTO);
        lblPersonas.setBounds(40, 125, 100, 20);
        contentPane.add(lblPersonas);

        txtPersonas = new JTextField();
        txtPersonas.setBounds(130, 125, 190, 30);
        txtPersonas.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtPersonas.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtPersonas);

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
        modeloTabla.addColumn("Nº mesa");
        modeloTabla.addColumn("Capacidad");

        tablaMesas = new JTable(modeloTabla);
        tablaMesas.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaMesas.setRowHeight(28);
        tablaMesas.setBackground(BLANCO);
        tablaMesas.setForeground(TEXTO);
        tablaMesas.setSelectionBackground(new Color(220, 235, 255));
        tablaMesas.setSelectionForeground(TEXTO);
        tablaMesas.setGridColor(GRIS);
        tablaMesas.setShowVerticalLines(false);

        JTableHeader header = tablaMesas.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 13));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(AZUL);
        headerRenderer.setForeground(BLANCO);
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setOpaque(true);

        tablaMesas.getColumnModel().getColumn(0).setHeaderRenderer(headerRenderer);
        tablaMesas.getColumnModel().getColumn(1).setHeaderRenderer(headerRenderer);

        scrollPane.setViewportView(tablaMesas);

        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdMesa.getText().trim());
                Mesa m = mDao.buscarPorId(id);

                if (m != null) {
                    txtPersonas.setText(String.valueOf(m.getnPersonas()));
                } else {
                    JOptionPane.showMessageDialog(this, "No existe una mesa con ese ID");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnInsertar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdMesa.getText().trim());
                int personas = Integer.parseInt(txtPersonas.getText().trim());

                Mesa m = new Mesa(id, personas);
                mDao.insertar(m);

                txtIdMesa.setText(String.valueOf(m.getnMesa()));
                JOptionPane.showMessageDialog(this, "Mesa insertada correctamente");
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                int fila = tablaMesas.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                int id = Integer.parseInt(tablaMesas.getValueAt(fila, 0).toString());
                Mesa m = new Mesa(id, Integer.parseInt(txtPersonas.getText().trim()));

                mDao.actualizar(id, m);
                JOptionPane.showMessageDialog(this, "Mesa actualizada correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int fila = tablaMesas.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                int id = Integer.parseInt(tablaMesas.getValueAt(fila, 0).toString());
                boolean eliminado = mDao.eliminar(id);

                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Mesa eliminada correctamente");
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No existe la mesa");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());

        cargarTabla();
    }

    private void limpiarCampos() {
        txtIdMesa.setText("");
        txtPersonas.setText("");
    }

    private void mostrarError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void cargarTabla() {
        try {
            modeloTabla.setRowCount(0);

            for (Mesa m : mDao.getMesas()) {
                Object[] fila = {
                    m.getnMesa(),
                    m.getnPersonas()
                };
                modeloTabla.addRow(fila);
            }
        } catch (Exception ex) {
            mostrarError(ex);
        }
    }
}