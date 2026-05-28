package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import dao.BartenderDAO;
import modelo.Bartender;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
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
        setTitle("Gestión de Bartenders");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 550);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE BARTENDERS");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(255, 10, 280, 30);
        contentPane.add(lblTitulo);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(30, 70, 80, 20);
        contentPane.add(lblDni);

        txtDNI = new JTextField();
        txtDNI.setBounds(120, 70, 180, 25);
        contentPane.add(txtDNI);
        txtDNI.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 110, 80, 20);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 110, 180, 25);
        contentPane.add(txtNombre);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(30, 150, 80, 20);
        contentPane.add(lblApellidos);

        txtApellido = new JTextField();
        txtApellido.setBounds(120, 150, 180, 25);
        contentPane.add(txtApellido);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(30, 192, 80, 20);
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(120, 190, 180, 25);
        contentPane.add(txtTelefono);

        JLabel lblNUSS = new JLabel("NUSS:");
        lblNUSS.setBounds(310, 192, 80, 20);
        contentPane.add(lblNUSS);

        txtNUSS = new JTextField();
        txtNUSS.setBounds(365, 190, 180, 25);
        contentPane.add(txtNUSS);

        JLabel lblColectivo = new JLabel("Colectivo:");
        lblColectivo.setBounds(310, 230, 80, 20);
        contentPane.add(lblColectivo);

        txtColectivo = new JTextField();
        txtColectivo.setBounds(365, 228, 180, 25);
        contentPane.add(txtColectivo);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 230, 80, 20);
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 230, 180, 25);
        contentPane.add(txtEmail);

        JButton btnBuscar = new JButton("Buscar por DNI");
        btnBuscar.setBounds(465, 65, 130, 30);
        contentPane.add(btnBuscar);

        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.setBounds(632, 65, 130, 30);
        contentPane.add(btnInsertar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(465, 120, 130, 30);
        contentPane.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(632, 120, 130, 30);
        contentPane.add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(632, 172, 130, 30);
        contentPane.add(btnLimpiar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 290, 720, 180);
        contentPane.add(scrollPane);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("DNI");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("NUSS");
        modeloTabla.addColumn("Colectivo");
        modeloTabla.addColumn("Email");

        tablaBartenders = new JTable(modeloTabla);
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

    // Este método vuelve a cargar todos los bartenders en la tabla para que
    // siempre se vea la información actual después de insertar, actualizar o borrar.
    private void cargarTabla() {
        try {
            modeloTabla.setRowCount(0);

            for (Bartender bartender : jDao.getBartenders()) {
                Object[] fila = {
                        bartender.getDni(),
                        bartender.getNombre(),
                        bartender.getApellidos(),
                        bartender.getTelefono(),
                        bartender.getNUSS(),
                        bartender.getColectivo(),
                        bartender.getEmail()
                };
                modeloTabla.addRow(fila);
            }

        } catch (Exception ex) {
            mostrarError(ex);
        }
    }
}