package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private final Color AZUL = new Color(0x0f4c81);
    private final Color NARANJA = new Color(0xfa6c07);
    private final Color FONDO = new Color(0xf4f6fb);
    private final Color BLANCO = Color.WHITE;
    private final Color TEXTO = new Color(0x1f2937);
    private final Color GRIS_SUAVE = new Color(0xe5e7eb);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                VentanaPrincipal frame = new VentanaPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VentanaPrincipal() {
        setTitle("Menú principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 980, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(FONDO);
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBackground(AZUL);
        sidebar.setBounds(0, 0, 250, 600);
        contentPane.add(sidebar);

        JLabel lblApp = new JLabel("RESTAURANTE");
        lblApp.setForeground(BLANCO);
        lblApp.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblApp.setHorizontalAlignment(SwingConstants.CENTER);
        lblApp.setBounds(20, 45, 210, 35);
        sidebar.add(lblApp);

        JLabel lblSub = new JLabel("Panel de gestión");
        lblSub.setForeground(new Color(220, 228, 255));
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 15));
        lblSub.setHorizontalAlignment(SwingConstants.CENTER);
        lblSub.setBounds(20, 82, 210, 22);
        sidebar.add(lblSub);

        JPanel accentBar = new JPanel();
        accentBar.setBackground(NARANJA);
        accentBar.setBounds(55, 120, 140, 5);
        sidebar.add(accentBar);

        JLabel lblMenu = new JLabel("Acceso rápido");
        lblMenu.setForeground(BLANCO);
        lblMenu.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblMenu.setBounds(35, 170, 150, 25);
        sidebar.add(lblMenu);

        JLabel lblInfo = new JLabel("<html>Gestiona clientes, mesas, reservas, empleados y proveedores desde un único panel.</html>");
        lblInfo.setForeground(new Color(228, 234, 255));
        lblInfo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblInfo.setBounds(35, 210, 180, 85);
        sidebar.add(lblInfo);

        JButton btnSalir = new JButton("Cerrar aplicación");
        btnSalir.setBounds(35, 485, 180, 42);
        btnSalir.setBackground(NARANJA);
        btnSalir.setForeground(BLANCO);
        btnSalir.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnSalir.setFocusPainted(false);
        btnSalir.setBorderPainted(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebar.add(btnSalir);

        JPanel panelTop = new JPanel();
        panelTop.setLayout(null);
        panelTop.setBackground(FONDO);
        panelTop.setBounds(250, 0, 730, 120);
        contentPane.add(panelTop);

        JLabel lblTitulo = new JLabel("Menú principal");
        lblTitulo.setForeground(TEXTO);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        lblTitulo.setBounds(35, 28, 260, 35);
        panelTop.add(lblTitulo);

        JLabel lblDesc = new JLabel("Selecciona el módulo que quieres abrir");
        lblDesc.setForeground(new Color(107, 114, 128));
        lblDesc.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblDesc.setBounds(37, 65, 320, 22);
        panelTop.add(lblDesc);

        JPanel panelMain = new JPanel();
        panelMain.setLayout(null);
        panelMain.setBackground(BLANCO);
        panelMain.setBounds(285, 120, 645, 390);
        panelMain.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GRIS_SUAVE, 1),
                new EmptyBorder(20, 20, 20, 20)
        ));
        contentPane.add(panelMain);

        JLabel lblModulos = new JLabel("Módulos");
        lblModulos.setForeground(TEXTO);
        lblModulos.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblModulos.setBounds(25, 20, 140, 30);
        panelMain.add(lblModulos);

        JLabel lblTexto = new JLabel("Haz clic en una tarjeta para abrir la gestión correspondiente");
        lblTexto.setForeground(new Color(107, 114, 128));
        lblTexto.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblTexto.setBounds(25, 50, 360, 20);
        panelMain.add(lblTexto);

        JButton btnClientes = crearTarjeta("Clientes", "Gestión de clientes registrados", AZUL, AZUL);
        btnClientes.setBounds(25, 95, 180, 105);
        panelMain.add(btnClientes);

        JButton btnPlatos = crearTarjeta("Platos", "Catálogo y administración", BLANCO, AZUL);
        btnPlatos.setBounds(230, 95, 180, 105);
        panelMain.add(btnPlatos);

        JButton btnMesas = crearTarjeta("Mesas", "Control de mesas disponibles", BLANCO, AZUL);
        btnMesas.setBounds(435, 95, 180, 105);
        panelMain.add(btnMesas);

        JButton btnEmpleados = crearTarjeta("Empleados", "Acceso y gestión interna", NARANJA, NARANJA);
        btnEmpleados.setBounds(25, 230, 180, 105);
        panelMain.add(btnEmpleados);

        JButton btnReservas = crearTarjeta("Reservas", "Seguimiento de reservas", BLANCO, AZUL);
        btnReservas.setBounds(230, 230, 180, 105);
        panelMain.add(btnReservas);

        JButton btnProveedores = crearTarjeta("Proveedores", "Relación con proveedores", BLANCO, AZUL);
        btnProveedores.setBounds(435, 230, 180, 105);
        panelMain.add(btnProveedores);

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

        btnEmpleados.addActionListener(e -> {
            VentanaLoginEmpleados vj = new VentanaLoginEmpleados();
            vj.setVisible(true);
        });

        btnProveedores.addActionListener(e -> {
            VentanaProveedores vp = new VentanaProveedores();
            vp.setVisible(true);
        });

        btnSalir.addActionListener(e -> System.exit(0));
    }

    private JButton crearTarjeta(String titulo, String subtitulo, Color fondo, Color texto) {
        JButton boton = new JButton(
                "<html><div style='text-align:center;'>" +
                "<span style='font-size:18px; font-weight:bold;'>" + titulo + "</span><br>" +
                "<span style='font-size:11px;'>" + subtitulo + "</span>" +
                "</div></html>"
        );

        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setFont(new Font("SansSerif", Font.BOLD, 16));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(fondo.equals(BLANCO) ? AZUL : fondo, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        return boton;
    }
}