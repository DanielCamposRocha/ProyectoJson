package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.AplicacionUsuarios;

public class VentanaBorrarUsuario extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel etiquetaBorrarUsuario;
	private JButton btnBorrar;
	private JButton btnCancelar;
	private AplicacionUsuarios app;
	private String nombreUsuario;

	public VentanaBorrarUsuario(AplicacionUsuarios app, String nombreUsuario) {
		this.app = app;
		this.nombreUsuario = nombreUsuario;
		setTitle("Aplicación usuarios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 316, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		etiquetaBorrarUsuario = new JLabel("¿Está seguro de que quiere borrar su usuario?");
		etiquetaBorrarUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		etiquetaBorrarUsuario.setBounds(10, 11, 296, 34);
		contentPane.add(etiquetaBorrarUsuario);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(170, 56, 89, 23);
		btnBorrar.addActionListener(this);
		contentPane.add(btnBorrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(35, 56, 89, 23);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
