package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.Controlador;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ReservaMesa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Controlador control;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtComensales;
	
	public ReservaMesa(Controlador control, String nroMesa, String fecha, VistaPorFecha vpf, String cantidadComensales) {
		setTitle("Reserva mesa nro:" +nroMesa);
		setAlwaysOnTop(true);
		setResizable(false);
		setModal(true);
		this.control=control;
		setBounds(100, 100, 602, 263);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Reserva:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(53, 38, 153, 28);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(53, 101, 153, 28);
		contentPanel.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtComensales = new JTextField();
		txtComensales.setColumns(10);
		txtComensales.setBounds(297, 42, 153, 28);
		contentPanel.add(txtComensales);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(53, 23, 100, 14);
		contentPanel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(53, 86, 153, 14);
		contentPanel.add(lblApellido);
		
		JLabel lblComensales = new JLabel("Comensales:");
		lblComensales.setBounds(297, 23, 114, 14);
		contentPanel.add(lblComensales);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
				btnGuardar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(Integer.parseInt(txtComensales.getText())<=Integer.parseInt(cantidadComensales)){
							control.generarReserva(nroMesa, txtApellido.getText(), txtNombre.getText(), fecha, txtComensales.getText());
							dispose();
						}else {
							 JOptionPane.showMessageDialog(null, "La cantidad de comensales es mayor a la capacidad de la mesa ("+cantidadComensales+")", "Error", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
				btnCancelar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
			}
		}
	}
}
