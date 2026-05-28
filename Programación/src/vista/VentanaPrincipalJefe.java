package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Font;

public class VentanaPrincipalJefe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public VentanaPrincipalJefe() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 60, 307, 171);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnJefes = new JButton("Jefes");
		panel.add(btnJefes);
		
		JButton btnBartender = new JButton("Bartenders");
		panel.add(btnBartender);
		
		JButton btnCamareros = new JButton("Camareros");
		panel.add(btnCamareros);
		
		JButton btnCocineros = new JButton("Cocineros");
		panel.add(btnCocineros);
		
		JLabel lblNewLabel = new JLabel("ADMINISTACIÓN DE EMPLEADOS");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 17));
		lblNewLabel.setBounds(41, 23, 307, 27);
		contentPane.add(lblNewLabel);
	}
}