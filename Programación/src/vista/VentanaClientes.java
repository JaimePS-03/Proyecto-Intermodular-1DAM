package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import dao.ClienteDAO;
import modelo.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
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
        setTitle("Gestión de clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 550);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE CLIENTES");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(255, 10, 280, 30);
        contentPane.add(lblTitulo);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(30, 70, 80, 20);
        contentPane.add(lblDni);

        txtDni = new JTextField();
        txtDni.setBounds(120, 70, 180, 25);
        contentPane.add(txtDni);
        txtDni.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 110, 80, 20);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 110, 180, 25);
        contentPane.add(txtNombre);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(30, 150, 80, 20);
        contentPane.add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(120, 150, 180, 25);
        contentPane.add(txtApellidos);

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

        JButton btnListar = new JButton("Listar");
        btnListar.setBounds(350, 70, 130, 30);
        contentPane.add(btnListar);

        JButton btnBuscar = new JButton("Buscar por ID");
        btnBuscar.setBounds(500, 70, 130, 30);
        contentPane.add(btnBuscar);

        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.setBounds(350, 120, 130, 30);
        contentPane.add(btnInsertar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(500, 120, 130, 30);
        contentPane.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(350, 170, 130, 30);
        contentPane.add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(500, 170, 130, 30);
        contentPane.add(btnLimpiar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 290, 720, 180);
        contentPane.add(scrollPane);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("DNI");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Email");

        tablaClientes = new JTable(modeloTabla);
        scrollPane.setViewportView(tablaClientes);

        btnListar.addActionListener(e -> {
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
        });

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
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                String id = txtDni.getText().trim();
                Cliente cliente = cDao.buscarPorId(id);

                if (cliente != null) {
                    cliente.setNombre(txtNombre.getText().trim());
                    cliente.setApellidos(txtApellidos.getText().trim());
                    cliente.setTelefono(txtTelefono.getText().trim());
                    cliente.setEmail(txtEmail.getText().trim());

                    cDao.actualizar(id, cliente);
                    JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, "No existe el cliente");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                String id = txtDni.getText().trim();
                boolean eliminado = cDao.eliminar(id);
                JOptionPane.showMessageDialog(this, eliminado ? "Cliente eliminado correctamente" : "No se encontró el cliente");
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void limpiarCampos() {
        txtDni.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        modeloTabla.setRowCount(0);
    }

    private void mostrarError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}