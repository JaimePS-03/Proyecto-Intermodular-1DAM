package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import dao.PlatoDAO;
import modelo.Plato;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
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

    private PlatoDAO pDao = new PlatoDAO();

    public VentanaPlatos() {
        setTitle("Gestión de platos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 520);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE PLATOS");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(280, 10, 240, 30);
        contentPane.add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(30, 70, 80, 20);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(120, 70, 180, 25);
        contentPane.add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 110, 80, 20);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 110, 180, 25);
        contentPane.add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(30, 150, 80, 20);
        contentPane.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(120, 150, 180, 25);
        contentPane.add(txtPrecio);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(30, 190, 80, 20);
        contentPane.add(lblTipo);

        txtTipo = new JTextField();
        txtTipo.setBounds(120, 190, 180, 25);
        contentPane.add(txtTipo);

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
        scrollPane.setBounds(30, 260, 720, 180);
        contentPane.add(scrollPane);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Tipo");

        tablaPlatos = new JTable(modeloTabla);
        scrollPane.setViewportView(tablaPlatos);

        btnListar.addActionListener(e -> {
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
        });

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
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                String id = txtId.getText().trim();
                Plato p = pDao.buscarPorId(id);

                if (p != null) {
                    p.setNombre(txtNombre.getText().trim());
                    p.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
                    p.setTipo(txtTipo.getText().trim());

                    pDao.actualizar(id, p);
                    JOptionPane.showMessageDialog(this, "Plato actualizado correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, "No existe ese plato");
                }
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                boolean eliminado = pDao.eliminar(txtId.getText().trim());
                JOptionPane.showMessageDialog(this, eliminado ? "Plato eliminado correctamente" : "No existe el plato");
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtTipo.setText("");
        modeloTabla.setRowCount(0);
    }

    private void mostrarError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}