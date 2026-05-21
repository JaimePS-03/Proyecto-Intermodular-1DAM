package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import controlador.PlatoDAO;
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

        JButton btnBuscar = new JButton("Buscar por ID");
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
        scrollPane.setBounds(30, 260, 720, 180);
        contentPane.add(scrollPane);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Tipo");

        tablaPlatos = new JTable(modeloTabla);
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

    // Este método vacía la tabla y la vuelve a rellenar con los datos actuales
    // que vienen del DAO, para que siempre se vea en pantalla el estado real.
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