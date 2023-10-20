package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.AplicacionUsuarios;

public class VentanaInicioSesion extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textoUsuario;
	private JTextField textoContraseña;
	private JButton btnIniciarSesion;
	private JButton btnCrearNuevoUsuario;
	private AplicacionUsuarios app;

	public VentanaInicioSesion(AplicacionUsuarios app) {
		this.app = app;

		setTitle("Aplicación usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		JLabel etiquetaInicioSesion = new JLabel("Inicio de sesión");
		etiquetaInicioSesion.setFont(new Font("Tahoma", Font.BOLD, 18));
		etiquetaInicioSesion.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaInicioSesion.setBounds(97, 26, 270, 44);
		contentPane.add(etiquetaInicioSesion);

		JLabel etiquetaUsuario = new JLabel("Usuario:");
		etiquetaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		etiquetaUsuario.setBounds(160, 90, 46, 14);
		contentPane.add(etiquetaUsuario);

		textoUsuario = new JTextField();
		textoUsuario.setBounds(160, 115, 149, 20);
		contentPane.add(textoUsuario);
		textoUsuario.setColumns(10);

		JLabel etiquetaContraseña = new JLabel("Contraseña:");
		etiquetaContraseña.setFont(new Font("Tahoma", Font.PLAIN, 12));
		etiquetaContraseña.setBounds(160, 146, 149, 14);
		contentPane.add(etiquetaContraseña);

		textoContraseña = new JTextField();
		textoContraseña.setColumns(10);
		textoContraseña.setBounds(160, 171, 149, 20);
		contentPane.add(textoContraseña);

		btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.setBounds(176, 215, 118, 23);
		btnIniciarSesion.addActionListener(this);
		contentPane.add(btnIniciarSesion);

		btnCrearNuevoUsuario = new JButton("Crear nuevo usuario");
		btnCrearNuevoUsuario.setBounds(10, 303, 149, 23);
		btnCrearNuevoUsuario.addActionListener(this);
		contentPane.add(btnCrearNuevoUsuario);
	}


	public void setTextoUsuario(String s) {
		 textoUsuario.setText(s);
	}

	public void setTextoContraseña(String s) {
		textoContraseña.setText(s);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnCrearNuevoUsuario)){
			app.mostrarVentanaCrearUsuario();

		}
		if(e.getSource().equals(btnIniciarSesion)){
			app.iniciarSesion(textoUsuario.getText(),textoContraseña.getText());

		}
	}
}
