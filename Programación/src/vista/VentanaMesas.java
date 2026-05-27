package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import controlador.MesaDAO;
import modelo.Mesa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
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
        setTitle("Gestión de mesas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 780, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE MESAS");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(270, 10, 250, 30);
        contentPane.add(lblTitulo);

        JLabel lblIdMesa = new JLabel("Nº mesa:");
        lblIdMesa.setBounds(30, 70, 100, 20);
        contentPane.add(lblIdMesa);

        txtIdMesa = new JTextField();
        txtIdMesa.setBounds(120, 70, 180, 25);
        contentPane.add(txtIdMesa);

        JLabel lblPersonas = new JLabel("Capacidad:");
        lblPersonas.setBounds(30, 110, 100, 20);
        contentPane.add(lblPersonas);

        txtPersonas = new JTextField();
        txtPersonas.setBounds(120, 110, 180, 25);
        contentPane.add(txtPersonas);

        JButton btnBuscar = new JButton("Buscar por ID");
        btnBuscar.setBounds(360, 70, 130, 30);
        contentPane.add(btnBuscar);

        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.setBounds(510, 70, 130, 30);
        contentPane.add(btnInsertar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(360, 120, 130, 30);
        contentPane.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(510, 120, 130, 30);
        contentPane.add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(435, 170, 130, 30);
        contentPane.add(btnLimpiar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 250, 700, 170);
        contentPane.add(scrollPane);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nº mesa");
        modeloTabla.addColumn("Capacidad");

        tablaMesas = new JTable(modeloTabla);
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
                int personas = Integer.parseInt(txtPersonas.getText().trim());

                Mesa m = new Mesa(mDao.generarId(), personas);
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

    // Este método borra las filas actuales del modelo y vuelve a cargar
    // todas las mesas desde el DAO para que la tabla siempre esté al día.
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