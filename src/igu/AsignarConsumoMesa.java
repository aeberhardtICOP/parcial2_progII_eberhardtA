package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import excepciones.EstadoInvalidoException;
import service.Controlador;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AsignarConsumoMesa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Controlador control;
	private String nroMesa;
	private JTextField txtConsumo;


	public AsignarConsumoMesa(String nroMesa, Controlador control) {
		setTitle("Liberar mesa NRO: "+nroMesa);
		setResizable(false);
		setModal(true);
		setAlwaysOnTop(true);
		this.control=control;
		setBounds(100, 100, 528, 184);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtConsumo = new JTextField();
		txtConsumo.setBounds(34, 53, 203, 33);
		contentPanel.add(txtConsumo);
		txtConsumo.setColumns(10);
		
		JLabel lblConsumo = new JLabel("Ingrese consumo:");
		lblConsumo.setBounds(34, 28, 134, 14);
		contentPanel.add(lblConsumo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnLiberar = new JButton("Liberar");
				btnLiberar.setActionCommand("OK");
				buttonPane.add(btnLiberar);
				getRootPane().setDefaultButton(btnLiberar);
				btnLiberar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						control.sumarConsumoMesa(nroMesa, txtConsumo.getText());
						dispose();
					}
					
				});
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}
