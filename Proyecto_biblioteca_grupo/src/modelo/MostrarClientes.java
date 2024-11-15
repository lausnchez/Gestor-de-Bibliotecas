package modelo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MostrarClientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbl_clientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarClientes frame = new MostrarClientes();
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
	public MostrarClientes() {
		setBounds(100, 100, 808, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tbl_clientes = new JTable();
		tbl_clientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		tbl_clientes.setColumnSelectionAllowed(true);
		tbl_clientes.setCellSelectionEnabled(true);
		tbl_clientes.setBounds(35, 66, 720, 400);
		contentPane.add(tbl_clientes);
	}
}
