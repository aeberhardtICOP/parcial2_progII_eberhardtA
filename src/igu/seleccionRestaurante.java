package igu;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import service.Controlador;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class seleccionRestaurante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador contr;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					seleccionRestaurante frame = new seleccionRestaurante();
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
	public seleccionRestaurante() {
		contr=new Controlador();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Seleccione Restaurante:", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> cmbxRestaurante = new JComboBox(contr.listaNombresResto().toArray(new String[0]));
		cmbxRestaurante.setBounds(120, 84, 225, 53);
		contentPane.add(cmbxRestaurante);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(383, 227, 89, 23);
		contentPane.add(btnIngresar);
		btnIngresar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contr.seleccionarRestaurante(cmbxRestaurante.getSelectedItem().toString());
				MenuPrincipal men = new MenuPrincipal(contr);
				men.setVisible(true);
				dispose();
				
			}
		});
	}
}
