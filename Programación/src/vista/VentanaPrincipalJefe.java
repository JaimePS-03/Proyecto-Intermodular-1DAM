package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

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

		JLabel lbl = new JLabel("Login correcto");
		lbl.setBounds(140, 100, 120, 20);
		contentPane.add(lbl);
	}
}