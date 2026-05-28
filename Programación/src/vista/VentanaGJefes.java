package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import dao.JefeDAO;
import modelo.Jefe;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;

public class VentanaGJefes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtDNI;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTable tablaJefes;
    private DefaultTableModel modeloTabla;

    private JefeDAO jDao = new JefeDAO();

    public VentanaGJefes() {
        setTitle("Gestión de Jefes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 550);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE JEFES");
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
        lblTelefono.setBounds(30, 190, 80, 20);
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(120, 190, 180, 25);
        contentPane.add(txtTelefono);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 230, 80, 20);
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 230, 180, 25);
        contentPane.add(txtEmail);

        JButton btnBuscar = new JButton("Buscar por DNI");
        btnBuscar.setBounds(350, 70, 130, 30);
        contentPane.add(btnBuscar);

        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.setBounds(500, 70, 130, 30);
        contentPane.add(btnInsertar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(350, 120, 130, 30);
        contentPane.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(500, 120, 130, 30);
        contentPane.add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(425, 170, 130, 30);
        contentPane.add(btnLimpiar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 290, 720, 180);
        contentPane.add(scrollPane);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("DNI");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Email");

        tablaJefes = new JTable(modeloTabla);
        scrollPane.setViewportView(tablaJefes);

        btnBuscar.addActionListener(e -> {
            try {
                String dni = txtDNI.getText().trim();
                Jefe jefe = jDao.buscarPorDni(dni);

                if (jefe != null) {
                    txtNombre.setText(jefe.getNombre());
                    txtApellido.setText(jefe.getApellidos());
                    txtTelefono.setText(jefe.getTelefono());
                    txtEmail.setText(jefe.getEmail());
                } else {
                    JOptionPane.showMessageDialog(this, "Jefe no encontrado");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnInsertar.addActionListener(e -> {
            try {
                Jefe nuevo = new Jefe(
                        txtDNI.getText().trim(),
                        txtNombre.getText().trim(),
                        txtApellido.getText().trim(),
                        txtTelefono.getText().trim(),
                        txtEmail.getText().trim()
                );

                jDao.insertar(nuevo);
                JOptionPane.showMessageDialog(this, "Jefe insertado correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                int fila = tablaJefes.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                String dni = tablaJefes.getValueAt(fila, 0).toString();

                Jefe jefeActualizado = new Jefe(
                        dni,
                        txtNombre.getText().trim(),
                        txtApellido.getText().trim(),
                        txtTelefono.getText().trim(),
                        txtEmail.getText().trim()
                );

                jDao.actualizar(dni, jefeActualizado);
                JOptionPane.showMessageDialog(this, "Jefe actualizado correctamente");
                limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int fila = tablaJefes.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla");
                    return;
                }

                String dni = tablaJefes.getValueAt(fila, 0).toString();
                boolean eliminado = jDao.eliminar(dni);

                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Jefe eliminado correctamente");
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el jefe");
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
    }

    private void mostrarError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Este método vuelve a cargar todos los jefes en la tabla para que
    // siempre se vea la información actual después de insertar, actualizar o borrar.
    private void cargarTabla() {
        try {
            modeloTabla.setRowCount(0);

            for (Jefe jefe : jDao.getJefes()) {
                Object[] fila = {
                        jefe.getDni(),
                        jefe.getNombre(),
                        jefe.getApellidos(),
                        jefe.getTelefono(),
                        jefe.getEmail()
                };
                modeloTabla.addRow(fila);
            }

        } catch (Exception ex) {
            mostrarError(ex);
        }
    }
}