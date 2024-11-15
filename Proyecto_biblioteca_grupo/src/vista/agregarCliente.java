package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class AgregarCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_id;
	private JTextField txt_nombre;
	private JTextField txt_apellidos;
	private JTextField txt_dni;
	private JTextField txt_telefono;
	private JTextField txt_email;
	private JTextField txt_provincia;
	private JTextField txt_ciudad;
	private JTextField txt_calle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarCliente frame = new AgregarCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AgregarCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_registro = new JLabel("REGISTRO DE CLIENTES");
		lbl_registro.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbl_registro.setBounds(40, 32, 313, 38);
		contentPane.add(lbl_registro);
		
		JLabel lbl_id = new JLabel("ID");
		lbl_id.setBounds(40, 97, 26, 14);
		contentPane.add(lbl_id);
		
		txt_id = new JTextField();
		txt_id.setBounds(76, 94, 335, 20);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
		
		JLabel lbl_biblioteca = new JLabel("Biblioteca");
		lbl_biblioteca.setBounds(40, 405, 61, 14);
		contentPane.add(lbl_biblioteca);
		
		JComboBox cb_biblioteca = new JComboBox();
		cb_biblioteca.setBounds(111, 401, 300, 22);
		contentPane.add(cb_biblioteca);
		
		JLabel lbl_nombre = new JLabel("Nombre");
		lbl_nombre.setBounds(40, 142, 46, 14);
		contentPane.add(lbl_nombre);
		
		txt_nombre = new JTextField();
		txt_nombre.setBounds(96, 139, 315, 20);
		contentPane.add(txt_nombre);
		txt_nombre.setColumns(10);
		
		JLabel lbl_apellidos = new JLabel("Apellidos");
		lbl_apellidos.setBounds(40, 170, 46, 14);
		contentPane.add(lbl_apellidos);
		
		txt_apellidos = new JTextField();
		txt_apellidos.setBounds(96, 167, 315, 20);
		contentPane.add(txt_apellidos);
		txt_apellidos.setColumns(10);
		
		JLabel lbl_dni = new JLabel("DNI");
		lbl_dni.setBounds(40, 198, 46, 14);
		contentPane.add(lbl_dni);
		
		txt_dni = new JTextField();
		txt_dni.setBounds(96, 195, 315, 20);
		contentPane.add(txt_dni);
		txt_dni.setColumns(10);
		
		JLabel lbl_telefono = new JLabel("Teléfono");
		lbl_telefono.setBounds(40, 226, 46, 14);
		contentPane.add(lbl_telefono);
		
		txt_telefono = new JTextField();
		txt_telefono.setBounds(96, 223, 315, 20);
		contentPane.add(txt_telefono);
		txt_telefono.setColumns(10);
		
		JLabel lbl_email = new JLabel("Email");
		lbl_email.setBounds(40, 254, 46, 14);
		contentPane.add(lbl_email);
		
		txt_email = new JTextField();
		txt_email.setBounds(96, 251, 315, 20);
		contentPane.add(txt_email);
		txt_email.setColumns(10);
		
		JButton btn_registrar = new JButton("Registrar");
		btn_registrar.setBounds(40, 445, 89, 23);
		contentPane.add(btn_registrar);
		
		JLabel lbl_provincia = new JLabel("Provincia");
		lbl_provincia.setBounds(40, 303, 46, 14);
		contentPane.add(lbl_provincia);
		
		txt_provincia = new JTextField();
		txt_provincia.setBounds(96, 300, 315, 20);
		contentPane.add(txt_provincia);
		txt_provincia.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(40, 282, 377, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(40, 125, 371, 2);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel = new JLabel("Ciudad");
		lblNewLabel.setBounds(40, 331, 46, 14);
		contentPane.add(lblNewLabel);
		
		txt_ciudad = new JTextField();
		txt_ciudad.setBounds(96, 328, 315, 20);
		contentPane.add(txt_ciudad);
		txt_ciudad.setColumns(10);
		
		JLabel lbl_calle = new JLabel("Calle");
		lbl_calle.setBounds(40, 356, 46, 14);
		contentPane.add(lbl_calle);
		
		txt_calle = new JTextField();
		txt_calle.setBounds(96, 353, 315, 20);
		contentPane.add(txt_calle);
		txt_calle.setColumns(10);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(40, 388, 371, 2);
		contentPane.add(separator_2);
	}

	
	// Getters & Setters
	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTextField getTxt_id() {
		return txt_id;
	}

	public void setTxt_id(JTextField txt_id) {
		this.txt_id = txt_id;
	}

	public JTextField getTxt_nombre() {
		return txt_nombre;
	}

	public void setTxt_nombre(JTextField txt_nombre) {
		this.txt_nombre = txt_nombre;
	}

	public JTextField getTxt_apellidos() {
		return txt_apellidos;
	}

	public void setTxt_apellidos(JTextField txt_apellidos) {
		this.txt_apellidos = txt_apellidos;
	}

	public JTextField getTxt_dni() {
		return txt_dni;
	}

	public void setTxt_dni(JTextField txt_dni) {
		this.txt_dni = txt_dni;
	}

	public JTextField getTxt_telefono() {
		return txt_telefono;
	}

	public void setTxt_telefono(JTextField txt_telefono) {
		this.txt_telefono = txt_telefono;
	}

	public JTextField getTxt_email() {
		return txt_email;
	}

	public void setTxt_email(JTextField txt_email) {
		this.txt_email = txt_email;
	}

	public JTextField getTxt_provincia() {
		return txt_provincia;
	}

	public void setTxt_provincia(JTextField txt_provincia) {
		this.txt_provincia = txt_provincia;
	}

	public JTextField getTxt_ciudad() {
		return txt_ciudad;
	}

	public void setTxt_ciudad(JTextField txt_ciudad) {
		this.txt_ciudad = txt_ciudad;
	}

	public JTextField getTxt_calle() {
		return txt_calle;
	}

	public void setTxt_calle(JTextField txt_calle) {
		this.txt_calle = txt_calle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
