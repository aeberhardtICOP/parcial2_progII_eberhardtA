package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class AcercaDeMi extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public AcercaDeMi() {
		setTitle("Acerca de..");
		setModal(true);
		setBounds(100, 100, 538, 248);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Alejandro Eberhardt");
			lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
			lblNewLabel.setForeground(new Color(0, 0, 0));
			lblNewLabel.setBounds(38, 24, 172, 26);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("Parcial 2");
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
			lblNewLabel.setBounds(38, 52, 172, 26);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("14/10/2023");
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
			lblNewLabel.setBounds(38, 78, 172, 26);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("Inicio parte 2: 07:30hs ");
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
			lblNewLabel.setBounds(38, 104, 217, 26);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("Fin parte 2: 21:04hs");
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
			lblNewLabel.setBounds(38, 128, 217, 26);
			contentPanel.add(lblNewLabel);
		}
		setResizable(false);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
