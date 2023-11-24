package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import excepciones.MesaConReservasException;
import excepciones.MesaOcupadaException;
import service.Controlador;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmacionBaja extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private String nroMesa;
	private Controlador control;

	
	public ConfirmacionBaja(String nroMesa, Controlador control) {
		this.control=control;
		this.nroMesa=nroMesa;
		setTitle("Confirmacion");
		setResizable(false);
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 525, 234);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCuidado = new JLabel("Baja Mesa NRO: "+nroMesa);
		lblCuidado.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCuidado.setBounds(20, 11, 166, 34);
		contentPanel.add(lblCuidado);
		
		JLabel lblEstaSeguro = new JLabel("¿Esta seguro de eliminar esta mesa?");
		lblEstaSeguro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEstaSeguro.setBounds(20, 56, 239, 14);
		contentPanel.add(lblEstaSeguro);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSi = new JButton("Si");
				btnSi.setActionCommand("OK");
				buttonPane.add(btnSi);
				getRootPane().setDefaultButton(btnSi);
				btnSi.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							control.bajaMesa(nroMesa);
							dispose();
						} catch (MesaConReservasException | MesaOcupadaException e1) {
							System.out.println("Excepcion modal??????????????????????????");
						}
						
					}
				});
			}
			{
				JButton btnNo = new JButton("No");
				btnNo.setActionCommand("Cancel");
				buttonPane.add(btnNo);
			}
		}
	}
}
