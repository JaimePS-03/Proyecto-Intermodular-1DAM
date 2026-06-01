package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import dao.BartenderDAO;
import modelo.Bartender;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

public class VentanaBartender extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtDNI;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtNUSS;
    private JTextField txtColectivo;
    private JTable tablaBartenders;
    private DefaultTableModel modeloTabla;

    private BartenderDAO jDao = new BartenderDAO();

    public VentanaBartender() {

        Color AZUL = new Color(0x0f4c81);
        Color NARANJA = new Color(0xfa6c07);
        Color FONDO = new Color(0xf4f6fb);
        Color BLANCO = Color.WHITE;
        Color TEXTO = new Color(0x1f2937);
        Color GRIS = new Color(0xe5e7eb);

        setTitle("Gestión de bartenders");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 950, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(FONDO);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE BARTENDERS");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setForeground(AZUL);
        lblTitulo.setBounds(280, 20, 360, 30);
        contentPane.add(lblTitulo);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblDni.setForeground(TEXTO);
        lblDni.setBounds(40, 80, 80, 20);
        contentPane.add(lblDni);

        txtDNI = new JTextField();
        txtDNI.setBounds(130, 80, 190, 30);
        txtDNI.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtDNI.setBorder(BorderFactory.createLineBorder(GRIS));
        txtDNI.setColumns(10);
        contentPane.add(txtDNI);

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

        JLabel lblApellido = new JLabel("Apellidos:");
        lblApellido.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblApellido.setForeground(TEXTO);
        lblApellido.setBounds(40, 170, 80, 20);
        contentPane.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(130, 170, 190, 30);
        txtApellido.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtApellido.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtApellido);

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

        JLabel lblNUSS = new JLabel("NUSS:");
        lblNUSS.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblNUSS.setForeground(TEXTO);
        lblNUSS.setBounds(350, 215, 80, 20);
        contentPane.add(lblNUSS);

        txtNUSS = new JTextField();
        txtNUSS.setBounds(440, 215, 190, 30);
        txtNUSS.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtNUSS.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtNUSS);

        JLabel lblColectivo = new JLabel("Colectivo:");
        lblColectivo.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblColectivo.setForeground(TEXTO);
        lblColectivo.setBounds(350, 260, 80, 20);
        contentPane.add(lblColectivo);

        txtColectivo = new JTextField();
        txtColectivo.setBounds(440, 260, 190, 30);
        txtColectivo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtColectivo.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtColectivo);

        JButton btnBuscar = new JButton("Buscar por DNI");
        btnBuscar.setBounds(690, 80, 170, 35);
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
        btnInsertar.setBounds(690, 130, 170, 35);
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
        btnActualizar.setBounds(690, 180, 170, 35);
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
        btnLimpiar.setBounds(690, 230, 170, 35);
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
        btnEliminar.setBounds(690, 280, 170, 35);
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
        scrollPane.setBounds(40, 350, 850, 170);
        scrollPane.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(scrollPane);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("DNI");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Colectivo");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("NUSS");

        tablaBartenders = new JTable(modeloTabla);
        tablaBartenders.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaBartenders.setRowHeight(28);
        tablaBartenders.setBackground(BLANCO);
        tablaBartenders.setForeground(TEXTO);
        tablaBartenders.setSelectionBackground(new Color(220, 235, 255));
        tablaBartenders.setSelectionForeground(TEXTO);
        tablaBartenders.setGridColor(GRIS);
        tablaBartenders.setShowVerticalLines(false);

        JTableHeader header = tablaBartenders.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 13));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(AZUL);
        headerRenderer.setForeground(BLANCO);
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setOpaque(true);

        tablaBartenders.getColumnModel().getColumn(0).setHeaderRenderer(headerRenderer);
        tablaBartenders.getColumnModel().getColumn(1).setHeaderRenderer(headerRenderer);
        tablaBartenders.getColumnModel().getColumn(2).setHeaderRenderer(headerRenderer);
        tablaBartenders.getColumnModel().getColumn(3).setHeaderRenderer(headerRenderer);
        tablaBartenders.getColumnModel().getColumn(4).setHeaderRenderer(headerRenderer);
        tablaBartenders.getColumnModel().getColumn(5).setHeaderRenderer(headerRenderer);
        tablaBartenders.getColumnModel().getColumn(6).setHeaderRenderer(headerRenderer);

        scrollPane.setViewportView(tablaBartenders);

        btnBuscar.addActionListener(e -> {
            try {
                String dni = txtDNI.getText().trim();
                Bartender bartender = jDao.buscarPorDni(dni);

                if (bartender != null) {
                    txtNombre.setText(bartender.getNombre());
                    txtApellido.setText(bartender.getApellidos());
                    txtTelefono.setText(bartender.getTelefono());
                    txtNUSS.setText(bartender.getNUSS());
                    txtColectivo.setText(bartender.getColectivo());
                    txtEmail.setText(bartender.getEmail());
                } else {
                    JOptionPane.showMessageDialog(this, "Bartender no encontrado");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnInsertar.addActionListener(e -> {
            try {
                Bartender nuevo = new Bartender(
                        txtDNI.getText().trim(),
                        txtNombre.getText().trim(),
                        txtApellido.getText().trim(),
                        txtTelefono.getText().trim(),
                        txtEmail.getText().trim(),
                        txtNUSS.getText().trim(),
                        txtColectivo.getText().trim()
                );

                jDao.insertar(nuevo);
                JOptionPane.showMessageDialog(this, "Bartender insertado correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                int fila = tablaBartenders.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                String dni = tablaBartenders.getValueAt(fila, 0).toString();

                Bartender bartenderActualizado = new Bartender(
                        dni,
                        txtNombre.getText().trim(),
                        txtApellido.getText().trim(),
                        txtTelefono.getText().trim(),
                        txtEmail.getText().trim(),
                        txtNUSS.getText().trim(),
                        txtColectivo.getText().trim()
                );

                jDao.actualizar(dni, bartenderActualizado);
                JOptionPane.showMessageDialog(this, "Bartender actualizado correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int fila = tablaBartenders.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                String dni = tablaBartenders.getValueAt(fila, 0).toString();
                boolean eliminado = jDao.eliminar(dni);

                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Bartender eliminado correctamente");
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el bartender");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());

        cargarTabla();
    }

    private void limpiarCampos() {
        txtDNI.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtNUSS.setText("");
        txtColectivo.setText("");
    }

    private void mostrarError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void cargarTabla() {
        try {
            modeloTabla.setRowCount(0);

            for (Bartender bartender : jDao.getBartenders()) {
                Object[] fila = {
                        bartender.getDni(),
                        bartender.getNombre(),
                        bartender.getApellidos(),
                        bartender.getTelefono(),
                        bartender.getColectivo(),
                        bartender.getEmail(),
                        bartender.getNUSS()
                };
                modeloTabla.addRow(fila);
            }

        } catch (Exception ex) {
            mostrarError(ex);
        }
    }
}