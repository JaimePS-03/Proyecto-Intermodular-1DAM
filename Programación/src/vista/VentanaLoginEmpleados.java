package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.JefeDAO;
import modelo.Jefe;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

public class VentanaLoginEmpleados extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textNombre;
    private JTextField textPass;
    private JLabel lblMensaje;

    public VentanaLoginEmpleados() {

        Color AZUL = new Color(0x0f4c81);
        Color NARANJA = new Color(0xfa6c07);
        Color FONDO = new Color(0xf4f6fb);
        Color BLANCO = Color.WHITE;
        Color TEXTO = new Color(0x1f2937);
        Color GRIS = new Color(0xe5e7eb);

        setTitle("Login empleados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 420, 360);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(FONDO);
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("ACCESO EMPLEADOS");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setForeground(AZUL);
        lblTitulo.setBounds(85, 25, 260, 30);
        contentPane.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Introduce tus datos para continuar");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSubtitulo.setForeground(new Color(107, 114, 128));
        lblSubtitulo.setBounds(90, 60, 230, 20);
        contentPane.add(lblSubtitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblNombre.setForeground(TEXTO);
        lblNombre.setBounds(45, 110, 100, 20);
        contentPane.add(lblNombre);

        textNombre = new JTextField();
        textNombre.setBounds(145, 108, 210, 30);
        textNombre.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textNombre.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(textNombre);
        textNombre.setColumns(10);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblPass.setForeground(TEXTO);
        lblPass.setBounds(45, 160, 100, 20);
        contentPane.add(lblPass);

        textPass = new JTextField();
        textPass.setBounds(145, 158, 210, 30);
        textPass.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textPass.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(textPass);
        textPass.setColumns(10);

        lblMensaje = new JLabel("");
        lblMensaje.setForeground(Color.RED);
        lblMensaje.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblMensaje.setBounds(45, 205, 310, 20);
        contentPane.add(lblMensaje);

        JButton btnLogin = new JButton("Acceder");
        btnLogin.setBounds(65, 255, 120, 35);
        btnLogin.setBackground(AZUL);
        btnLogin.setForeground(BLANCO);
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setContentAreaFilled(true);
        btnLogin.setOpaque(true);
        contentPane.add(btnLogin);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(220, 255, 120, 35);
        btnLimpiar.setBackground(NARANJA);
        btnLimpiar.setForeground(BLANCO);
        btnLimpiar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setContentAreaFilled(true);
        btnLimpiar.setOpaque(true);
        contentPane.add(btnLimpiar);

        btnLogin.addActionListener(e -> {
            String nombre = textNombre.getText().trim();
            String dni = textPass.getText().trim();

            if (nombre.isEmpty() || dni.isEmpty()) {
                lblMensaje.setText("Debes introducir nombre y contraseña");
                return;
            }

            JefeDAO jDAO = new JefeDAO();

            try {
                Jefe jefe = jDAO.buscarPorDni(dni);

                if (jefe == null) {
                    lblMensaje.setText("No existen datos con ese DNI");
                    return;
                }

                if (!jefe.getNombre().equalsIgnoreCase(nombre)) {
                    lblMensaje.setText("Nombre o contraseña incorrectos");
                    return;
                }

                lblMensaje.setText("");
                textNombre.setText("");
                textPass.setText("");

                VentanaPrincipalJefe v = new VentanaPrincipalJefe();
                v.setVisible(true);

                dispose();

            } catch (SQLException ex) {
                lblMensaje.setText("Error al acceder a la base de datos");
                ex.printStackTrace();
            }
        });

        btnLimpiar.addActionListener(e -> {
            textNombre.setText("");
            textPass.setText("");
            lblMensaje.setText("");
        });
    }
}