package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import dao.CocineroDAO;
import modelo.Cocinero;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

public class VentanaCocineros extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtDNI;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtNUSS;
    private JTextField txtColectivo;
    private JTextField txtEspecialidad;
    private JTable tablaCocineros;
    private DefaultTableModel modeloTabla;

    private CocineroDAO cDao = new CocineroDAO();

    public VentanaCocineros() {

        Color AZUL = new Color(0x0f4c81);
        Color NARANJA = new Color(0xfa6c07);
        Color FONDO = new Color(0xf4f6fb);
        Color BLANCO = Color.WHITE;
        Color TEXTO = new Color(0x1f2937);
        Color GRIS = new Color(0xe5e7eb);

        setTitle("Gestión de cocineros");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 980, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(FONDO);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE COCINEROS");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setForeground(AZUL);
        lblTitulo.setBounds(330, 20, 320, 30);
        contentPane.add(lblTitulo);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblDni.setForeground(TEXTO);
        lblDni.setBounds(40, 80, 100, 20);
        contentPane.add(lblDni);

        txtDNI = new JTextField();
        txtDNI.setBounds(150, 80, 190, 30);
        txtDNI.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtDNI.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtDNI);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblNombre.setForeground(TEXTO);
        lblNombre.setBounds(40, 125, 100, 20);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 125, 190, 30);
        txtNombre.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtNombre.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtNombre);

        JLabel lblApellido = new JLabel("Apellidos:");
        lblApellido.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblApellido.setForeground(TEXTO);
        lblApellido.setBounds(40, 170, 100, 20);
        contentPane.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(150, 170, 190, 30);
        txtApellido.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtApellido.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtApellido);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTelefono.setForeground(TEXTO);
        lblTelefono.setBounds(40, 215, 100, 20);
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(150, 215, 190, 30);
        txtTelefono.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtTelefono.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtTelefono);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblEmail.setForeground(TEXTO);
        lblEmail.setBounds(40, 260, 100, 20);
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 260, 190, 30);
        txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtEmail.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtEmail);

        JLabel lblNUSS = new JLabel("NUSS:");
        lblNUSS.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblNUSS.setForeground(TEXTO);
        lblNUSS.setBounds(390, 80, 100, 20);
        contentPane.add(lblNUSS);

        txtNUSS = new JTextField();
        txtNUSS.setBounds(500, 80, 190, 30);
        txtNUSS.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtNUSS.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtNUSS);

        JLabel lblColectivo = new JLabel("Colectivo:");
        lblColectivo.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblColectivo.setForeground(TEXTO);
        lblColectivo.setBounds(390, 125, 100, 20);
        contentPane.add(lblColectivo);

        txtColectivo = new JTextField();
        txtColectivo.setBounds(500, 125, 190, 30);
        txtColectivo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtColectivo.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtColectivo);

        JLabel lblEspecialidad = new JLabel("Especialidad:");
        lblEspecialidad.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblEspecialidad.setForeground(TEXTO);
        lblEspecialidad.setBounds(390, 170, 100, 20);
        contentPane.add(lblEspecialidad);

        txtEspecialidad = new JTextField();
        txtEspecialidad.setBounds(500, 170, 190, 30);
        txtEspecialidad.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtEspecialidad.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtEspecialidad);

        JButton btnBuscar = new JButton("Buscar por DNI");
        btnBuscar.setBounds(740, 80, 180, 35);
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
        btnInsertar.setBounds(740, 130, 180, 35);
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
        btnActualizar.setBounds(740, 180, 180, 35);
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
        btnLimpiar.setBounds(740, 230, 180, 35);
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
        btnEliminar.setBounds(740, 280, 180, 35);
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
        scrollPane.setBounds(40, 350, 880, 170);
        scrollPane.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(scrollPane);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("DNI");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("NUSS");
        modeloTabla.addColumn("Colectivo");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Especialidad");

        tablaCocineros = new JTable(modeloTabla);
        tablaCocineros.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaCocineros.setRowHeight(28);
        tablaCocineros.setBackground(BLANCO);
        tablaCocineros.setForeground(TEXTO);
        tablaCocineros.setSelectionBackground(new Color(220, 235, 255));
        tablaCocineros.setSelectionForeground(TEXTO);
        tablaCocineros.setGridColor(GRIS);
        tablaCocineros.setShowVerticalLines(false);

        JTableHeader header = tablaCocineros.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 13));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(AZUL);
        headerRenderer.setForeground(BLANCO);
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setOpaque(true);

        for (int i = 0; i < tablaCocineros.getColumnModel().getColumnCount(); i++) {
            tablaCocineros.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        scrollPane.setViewportView(tablaCocineros);

        btnBuscar.addActionListener(e -> {
            try {
                String dni = txtDNI.getText().trim();
                Cocinero cocinero = cDao.buscarPorDni(dni);

                if (cocinero != null) {
                    txtNombre.setText(cocinero.getNombre());
                    txtApellido.setText(cocinero.getApellidos());
                    txtTelefono.setText(cocinero.getTelefono());
                    txtEmail.setText(cocinero.getEmail());
                    txtNUSS.setText(cocinero.getNUSS());
                    txtColectivo.setText(cocinero.getColectivo());
                    txtEspecialidad.setText(cocinero.getEspecialidad());
                } else {
                    JOptionPane.showMessageDialog(this, "Cocinero no encontrado");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnInsertar.addActionListener(e -> {
            try {
                Cocinero nuevo = new Cocinero(
                        txtDNI.getText().trim(),
                        txtNombre.getText().trim(),
                        txtApellido.getText().trim(),
                        txtTelefono.getText().trim(),
                        txtEmail.getText().trim(),
                        txtNUSS.getText().trim(),
                        txtColectivo.getText().trim(),
                        txtEspecialidad.getText().trim()
                );

                cDao.insertar(nuevo);
                JOptionPane.showMessageDialog(this, "Cocinero insertado correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                int fila = tablaCocineros.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                String dni = tablaCocineros.getValueAt(fila, 0).toString();

                Cocinero cocineroActualizado = new Cocinero(
                        dni,
                        txtNombre.getText().trim(),
                        txtApellido.getText().trim(),
                        txtTelefono.getText().trim(),
                        txtEmail.getText().trim(),
                        txtNUSS.getText().trim(),
                        txtColectivo.getText().trim(),
                        txtEspecialidad.getText().trim()
                );

                cDao.actualizar(dni, cocineroActualizado);
                JOptionPane.showMessageDialog(this, "Cocinero actualizado correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int fila = tablaCocineros.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                String dni = tablaCocineros.getValueAt(fila, 0).toString();
                boolean eliminado = cDao.eliminar(dni);

                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Cocinero eliminado correctamente");
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el cocinero");
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
        txtEspecialidad.setText("");
    }

    private void mostrarError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void cargarTabla() {
        try {
            modeloTabla.setRowCount(0);

            for (Cocinero cocinero : cDao.getCocineros()) {
                Object[] fila = {
                        cocinero.getDni(),
                        cocinero.getNombre(),
                        cocinero.getApellidos(),
                        cocinero.getTelefono(),
                        cocinero.getNUSS(),
                        cocinero.getColectivo(),
                        cocinero.getEmail(),
                        cocinero.getEspecialidad()
                };
                modeloTabla.addRow(fila);
            }

        } catch (Exception ex) {
            mostrarError(ex);
        }
    }
}