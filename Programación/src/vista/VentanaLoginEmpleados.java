package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.JefeDAO;
import modelo.Jefe;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class VentanaLoginEmpleados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textPass;
	private JLabel lblMensaje;

	public VentanaLoginEmpleados() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Login Empleados");
		lblTitulo.setBounds(98, 28, 96, 12);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 91, 61, 12);
		contentPane.add(lblNombre);
		
		JLabel lblPass = new JLabel("Contraseña:");
		lblPass.setBounds(20, 138, 74, 12);
		contentPane.add(lblPass);
		
		textNombre = new JTextField();
		textNombre.setBounds(167, 88, 96, 18);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textPass = new JTextField();
		textPass.setBounds(167, 135, 96, 18);
		contentPane.add(textPass);
		textPass.setColumns(10);
		
		lblMensaje = new JLabel("");
		lblMensaje.setForeground(Color.RED);
		lblMensaje.setBounds(20, 191, 243, 20);
		contentPane.add(lblMensaje);
		
		JButton btnLogin = new JButton("Acceder");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			}
		});
		btnLogin.setBounds(33, 237, 84, 20);
		contentPane.add(btnLogin);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNombre.setText("");
				textPass.setText("");
				lblMensaje.setText("");
			}
		});
		btnLimpiar.setBounds(179, 237, 84, 20);
		contentPane.add(btnLimpiar);
	}
}