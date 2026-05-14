package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaPrincipal frame = new VentanaPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaPrincipal() {
        setTitle("Menú principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 450);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("MENÚ PRINCIPAL");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(0, 64, 128));
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitulo.setBounds(170, 30, 280, 40);
        contentPane.add(lblTitulo);

        JButton btnClientes = new JButton("Clientes");
        btnClientes.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnClientes.setBounds(80, 120, 200, 50);
        contentPane.add(btnClientes);

        JButton btnPlatos = new JButton("Platos");
        btnPlatos.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnPlatos.setBounds(340, 120, 200, 50);
        contentPane.add(btnPlatos);

        JButton btnReservas = new JButton("Reservas");
        btnReservas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnReservas.setBounds(80, 220, 200, 50);
        contentPane.add(btnReservas);

        JButton btnMesas = new JButton("Mesas");
        btnMesas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnMesas.setBounds(340, 220, 200, 50);
        contentPane.add(btnMesas);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSalir.setBounds(240, 320, 140, 40);
        contentPane.add(btnSalir);

        btnClientes.addActionListener(e -> {
            VentanaClientes vc = new VentanaClientes();
            vc.setVisible(true);
        });

        btnPlatos.addActionListener(e -> {
            VentanaPlatos vp = new VentanaPlatos();
            vp.setVisible(true);
        });

        btnReservas.addActionListener(e -> {
            VentanaReservas vr = new VentanaReservas();
            vr.setVisible(true);
        });

        btnMesas.addActionListener(e -> {
            VentanaMesas vm = new VentanaMesas();
            vm.setVisible(true);
        });

        btnSalir.addActionListener(e -> System.exit(0));
    }
}