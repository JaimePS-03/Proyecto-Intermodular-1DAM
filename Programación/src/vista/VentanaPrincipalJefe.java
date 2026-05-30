package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

public class VentanaPrincipalJefe extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public VentanaPrincipalJefe() {

        Color AZUL = new Color(0x0f4c81);
        Color NARANJA = new Color(0xfa6c07);
        Color FONDO = new Color(0xf4f6fb);
        Color BLANCO = Color.WHITE;
        Color TEXTO = new Color(0x1f2937);
        Color GRIS = new Color(0xe5e7eb);

        setTitle("Administración de empleados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 520, 360);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(FONDO);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("ADMINISTRACIÓN DE EMPLEADOS");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setForeground(AZUL);
        lblTitulo.setBounds(75, 25, 370, 30);
        contentPane.add(lblTitulo);

        JLabel lblSub = new JLabel("Selecciona el tipo de empleado que quieres gestionar");
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSub.setForeground(TEXTO);
        lblSub.setBounds(85, 60, 340, 20);
        contentPane.add(lblSub);

        JPanel panelBotones = new JPanel();
        panelBotones.setBounds(55, 105, 390, 165);
        panelBotones.setLayout(new GridLayout(2, 2, 15, 15));
        panelBotones.setBackground(FONDO);
        panelBotones.setBorder(BorderFactory.createLineBorder(GRIS));
        contentPane.add(panelBotones);

        JButton btnJefes = new JButton("Jefes");
        btnJefes.setBackground(AZUL);
        btnJefes.setForeground(BLANCO);
        btnJefes.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnJefes.setFocusPainted(false);
        btnJefes.setBorderPainted(false);
        btnJefes.setContentAreaFilled(true);
        btnJefes.setOpaque(true);
        btnJefes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBotones.add(btnJefes);

        JButton btnBartender = new JButton("Bartenders");
        btnBartender.setBackground(AZUL);
        btnBartender.setForeground(BLANCO);
        btnBartender.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnBartender.setFocusPainted(false);
        btnBartender.setBorderPainted(false);
        btnBartender.setContentAreaFilled(true);
        btnBartender.setOpaque(true);
        btnBartender.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBotones.add(btnBartender);

        JButton btnCamareros = new JButton("Camareros");
        btnCamareros.setBackground(AZUL);
        btnCamareros.setForeground(BLANCO);
        btnCamareros.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnCamareros.setFocusPainted(false);
        btnCamareros.setBorderPainted(false);
        btnCamareros.setContentAreaFilled(true);
        btnCamareros.setOpaque(true);
        btnCamareros.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBotones.add(btnCamareros);

        JButton btnCocineros = new JButton("Cocineros");
        btnCocineros.setBackground(AZUL);
        btnCocineros.setForeground(BLANCO);
        btnCocineros.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnCocineros.setFocusPainted(false);
        btnCocineros.setBorderPainted(false);
        btnCocineros.setContentAreaFilled(true);
        btnCocineros.setOpaque(true);
        btnCocineros.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBotones.add(btnCocineros);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(190, 285, 120, 30);
        btnCerrar.setBackground(NARANJA);
        btnCerrar.setForeground(BLANCO);
        btnCerrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(true);
        btnCerrar.setOpaque(true);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnCerrar);

        btnJefes.addActionListener(e -> {
            VentanaGJefes vGJ = new VentanaGJefes();
            vGJ.setVisible(true);
        });

        btnBartender.addActionListener(e -> {
            VentanaBartender vB = new VentanaBartender();
            vB.setVisible(true);
        });

        btnCamareros.addActionListener(e -> {
            VentanaCamareros vC = new VentanaCamareros();
            vC.setVisible(true);
        });

        btnCocineros.addActionListener(e -> {
            VentanaCocineros vCoc = new VentanaCocineros();
            vCoc.setVisible(true);
        });

        btnCerrar.addActionListener(e -> dispose());
    }
}