package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import dao.CamareroDAO;
import modelo.Camarero;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

public class VentanaCamareros extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtDNI;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtNUSS;
    private JTextField txtColectivo;
    private JTextField txtAnoServicio;
    private JTextField txtZona;
    private JTextField txtCamareroJefe;
    private JTable tablaCamareros;
    private DefaultTableModel modeloTabla;

    private CamareroDAO cDao = new CamareroDAO();

    public VentanaCamareros() {

        Color AZUL = new Color(0x0f4c81);
        Color NARANJA = new Color(0xfa6c07);
        Color FONDO = new Color(0xf4f6fb);
        Color BLANCO = Color.WHITE;
        Color TEXTO = new Color(0x1f2937);
        Color GRIS = new Color(0xe5e7eb);

        setTitle("Gestión de camareros");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1100, 650);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(FONDO);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE CAMAREROS");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setForeground(AZUL);
        lblTitulo.setBounds(365, 20, 360, 30);
        contentPane.add(lblTitulo);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblDni.setForeground(TEXTO);
        lblDni.setBounds(40, 80, 100, 20);
        contentPane.add(lblDni);

        txtDNI = new JTextField();
        txtDNI.setBounds(150, 80, 180, 30);
        txtDNI.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtDNI.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtDNI);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblNombre.setForeground(TEXTO);
        lblNombre.setBounds(40, 125, 100, 20);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 125, 180, 30);
        txtNombre.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtNombre.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtNombre);

        JLabel lblApellido = new JLabel("Apellidos:");
        lblApellido.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblApellido.setForeground(TEXTO);
        lblApellido.setBounds(40, 170, 100, 20);
        contentPane.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(150, 170, 180, 30);
        txtApellido.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtApellido.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtApellido);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTelefono.setForeground(TEXTO);
        lblTelefono.setBounds(40, 215, 100, 20);
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(150, 215, 180, 30);
        txtTelefono.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtTelefono.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtTelefono);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblEmail.setForeground(TEXTO);
        lblEmail.setBounds(40, 260, 100, 20);
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 260, 180, 30);
        txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtEmail.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtEmail);

        JLabel lblNUSS = new JLabel("NUSS:");
        lblNUSS.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblNUSS.setForeground(TEXTO);
        lblNUSS.setBounds(380, 80, 100, 20);
        contentPane.add(lblNUSS);

        txtNUSS = new JTextField();
        txtNUSS.setBounds(500, 80, 180, 30);
        txtNUSS.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtNUSS.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtNUSS);

        JLabel lblColectivo = new JLabel("Colectivo:");
        lblColectivo.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblColectivo.setForeground(TEXTO);
        lblColectivo.setBounds(380, 125, 100, 20);
        contentPane.add(lblColectivo);

        txtColectivo = new JTextField();
        txtColectivo.setBounds(500, 125, 180, 30);
        txtColectivo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtColectivo.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtColectivo);

        JLabel lblAnoServicio = new JLabel("Años servicio:");
        lblAnoServicio.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblAnoServicio.setForeground(TEXTO);
        lblAnoServicio.setBounds(380, 170, 100, 20);
        contentPane.add(lblAnoServicio);

        txtAnoServicio = new JTextField();
        txtAnoServicio.setBounds(500, 170, 180, 30);
        txtAnoServicio.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtAnoServicio.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtAnoServicio);

        JLabel lblZona = new JLabel("Zona:");
        lblZona.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblZona.setForeground(TEXTO);
        lblZona.setBounds(380, 215, 100, 20);
        contentPane.add(lblZona);

        txtZona = new JTextField();
        txtZona.setBounds(500, 215, 180, 30);
        txtZona.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtZona.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtZona);

        JLabel lblCamareroJefe = new JLabel("Camarero jefe:");
        lblCamareroJefe.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblCamareroJefe.setForeground(TEXTO);
        lblCamareroJefe.setBounds(380, 260, 110, 20);
        contentPane.add(lblCamareroJefe);

        txtCamareroJefe = new JTextField();
        txtCamareroJefe.setBounds(500, 260, 180, 30);
        txtCamareroJefe.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtCamareroJefe.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(txtCamareroJefe);

        JButton btnBuscar = new JButton("Buscar por DNI");
        btnBuscar.setBounds(760, 80, 180, 35);
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
        btnInsertar.setBounds(760, 130, 180, 35);
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
        btnActualizar.setBounds(760, 180, 180, 35);
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
        btnLimpiar.setBounds(760, 230, 180, 35);
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
        btnEliminar.setBounds(760, 280, 180, 35);
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
        scrollPane.setBounds(40, 360, 1000, 210);
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
        modeloTabla.addColumn("Años servicio");
        modeloTabla.addColumn("Zona");
        modeloTabla.addColumn("Camarero jefe");

        tablaCamareros = new JTable(modeloTabla);
        tablaCamareros.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaCamareros.setRowHeight(28);
        tablaCamareros.setBackground(BLANCO);
        tablaCamareros.setForeground(TEXTO);
        tablaCamareros.setSelectionBackground(new Color(220, 235, 255));
        tablaCamareros.setSelectionForeground(TEXTO);
        tablaCamareros.setGridColor(GRIS);
        tablaCamareros.setShowVerticalLines(false);

        JTableHeader header = tablaCamareros.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 13));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(AZUL);
        headerRenderer.setForeground(BLANCO);
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setOpaque(true);

        for (int i = 0; i < tablaCamareros.getColumnModel().getColumnCount(); i++) {
            tablaCamareros.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        scrollPane.setViewportView(tablaCamareros);

        btnBuscar.addActionListener(e -> {
            try {
                String dni = txtDNI.getText().trim();
                Camarero camarero = cDao.buscarPorDni(dni);

                if (camarero != null) {
                    txtNombre.setText(camarero.getNombre());
                    txtApellido.setText(camarero.getApellidos());
                    txtTelefono.setText(camarero.getTelefono());
                    txtEmail.setText(camarero.getEmail());
                    txtNUSS.setText(camarero.getNUSS());
                    txtColectivo.setText(camarero.getColectivo());
                    txtAnoServicio.setText(String.valueOf(camarero.getAnoServicio()));
                    txtZona.setText(camarero.getnZona());
                    txtCamareroJefe.setText(camarero.getCamareroJefe());
                } else {
                    JOptionPane.showMessageDialog(this, "Camarero no encontrado");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnInsertar.addActionListener(e -> {
            try {
                Camarero nuevo = new Camarero(
                        txtDNI.getText().trim(),
                        txtNombre.getText().trim(),
                        txtApellido.getText().trim(),
                        txtTelefono.getText().trim(),
                        txtEmail.getText().trim(),
                        txtNUSS.getText().trim(),
                        txtColectivo.getText().trim(),
                        Integer.parseInt(txtAnoServicio.getText().trim()),
                        txtZona.getText().trim(),
                        txtCamareroJefe.getText().trim()
                );

                cDao.insertar(nuevo);
                JOptionPane.showMessageDialog(this, "Camarero insertado correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                int fila = tablaCamareros.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                String dni = tablaCamareros.getValueAt(fila, 0).toString();

                Camarero camareroActualizado = new Camarero(
                        dni,
                        txtNombre.getText().trim(),
                        txtApellido.getText().trim(),
                        txtTelefono.getText().trim(),
                        txtEmail.getText().trim(),
                        txtNUSS.getText().trim(),
                        txtColectivo.getText().trim(),
                        Integer.parseInt(txtAnoServicio.getText().trim()),
                        txtZona.getText().trim(),
                        txtCamareroJefe.getText().trim()
                );

                cDao.actualizar(dni, camareroActualizado);
                JOptionPane.showMessageDialog(this, "Camarero actualizado correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int fila = tablaCamareros.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                String dni = tablaCamareros.getValueAt(fila, 0).toString();
                boolean eliminado = cDao.eliminar(dni);

                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Camarero eliminado correctamente");
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el camarero");
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
        txtAnoServicio.setText("");
        txtZona.setText("");
        txtCamareroJefe.setText("");
    }

    private void mostrarError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void cargarTabla() {
        try {
            modeloTabla.setRowCount(0);

            for (Camarero camarero : cDao.getCamareros()) {
                Object[] fila = {
                        camarero.getDni(),
                        camarero.getNombre(),
                        camarero.getApellidos(),
                        camarero.getTelefono(),
                        camarero.getNUSS(),
                        camarero.getColectivo(),
                        camarero.getEmail(),
                        camarero.getAnoServicio(),
                        camarero.getnZona(),
                        camarero.getCamareroJefe()
                };
                modeloTabla.addRow(fila);
            }

        } catch (Exception ex) {
            mostrarError(ex);
        }
    }
}