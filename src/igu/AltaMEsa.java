package igu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.Controlador;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.Mesa;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AltaMEsa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador control;
	private JTextField textField;
	private DefaultTableModel modeloTabla;
	private JTextField txtCantMesas;
	private JComboBox cmbx;
	private JLabel lblCantMesas;
	private JLabel lblCantidadTotalMesas;
	private JLabel lblCantidadDos;
	private JLabel lblCantidadCuatro;
	private JLabel lblCantidadSeis;
	private JLabel lblCantidadOcho;

	public AltaMEsa(Controlador control) {
		setTitle("Alta mesa");
		this.control=control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 941, 666);
		contentPane = new JPanel();
		setResizable(false);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelCarga = new JPanel();
		panelCarga.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Alta mesa:", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCarga.setBounds(10, 11, 905, 292);
		contentPane.add(panelCarga);
		panelCarga.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(428, 55, 273, 38);
		panelCarga.add(textField);
		textField.setColumns(10);
		
		JLabel lblCapacidadMesa = new JLabel("Capacidad mesa:");
		lblCapacidadMesa.setBounds(428, 39, 153, 14);
		panelCarga.add(lblCapacidadMesa);
		
		JLabel lblVolver = new JLabel("<--Volver");
		lblVolver.setForeground(new Color(0, 0, 255));
		lblVolver.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblVolver.setBounds(10, 14, 82, 14);
		panelCarga.add(lblVolver);
		lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuPrincipal men = new MenuPrincipal(control);
                men.setVisible(true);
                dispose();
            }
        });
		
		JButton btnGuadrar = new JButton("Guardar");
		btnGuadrar.setBounds(428, 195, 153, 52);
		panelCarga.add(btnGuadrar);
		btnGuadrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cmbx.getSelectedItem().toString().equals("Paquete")) {
					control.altaPaqueteMesas(txtCantMesas.getText(), textField.getText());
					conteo();
					textField.setText("");
					txtCantMesas.setText("");
				}else {
					control.altaMesa(textField.getText());
					conteo();
					textField.setText("");
					txtCantMesas.setText("");
				}
				
				
			}
		});
		
		txtCantMesas = new JTextField();
		txtCantMesas.setEnabled(false);
		txtCantMesas.setColumns(10);
		txtCantMesas.setBounds(428, 121, 273, 38);
		panelCarga.add(txtCantMesas);
		
		lblCantMesas = new JLabel("Cantidad:");
		lblCantMesas.setEnabled(false);
		lblCantMesas.setBounds(426, 104, 98, 14);
		panelCarga.add(lblCantMesas);
		
		cmbx = new JComboBox();
		cmbx.setModel(new DefaultComboBoxModel(new String[] {"Mesa indiviudal", "Paquete"}));
		cmbx.setSelectedIndex(0);
		cmbx.setBounds(65, 104, 153, 38);
		panelCarga.add(cmbx);
		cmbx.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cmbx.getSelectedItem().toString().equals("Paquete")) {
					txtCantMesas.setEnabled(true);
					lblCantMesas.setEnabled(true);
				}else {
					txtCantMesas.setEnabled(false);
					txtCantMesas.setText("");
					lblCantMesas.setEnabled(false);
				}
				
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de alta:");
		lblNewLabel_1.setBounds(65, 88, 98, 14);
		panelCarga.add(lblNewLabel_1);

		JPanel panelTabla = new JPanel();
		panelTabla.setBorder(new TitledBorder(null, "Mesas:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTabla.setBounds(10, 314, 905, 302);
		contentPane.add(panelTabla);
		panelTabla.setLayout(null);
		
		lblCantidadTotalMesas = new JLabel("Total mesas:");
		lblCantidadTotalMesas.setForeground(new Color(0, 128, 128));
		lblCantidadTotalMesas.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lblCantidadTotalMesas.setBounds(144, 43, 301, 41);
		panelTabla.add(lblCantidadTotalMesas);
		
		lblCantidadDos = new JLabel("Dos comensales:");
		lblCantidadDos.setForeground(new Color(0, 0, 0));
		lblCantidadDos.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		lblCantidadDos.setBounds(302, 96, 143, 41);
		panelTabla.add(lblCantidadDos);
		
		lblCantidadCuatro = new JLabel("Cuatro comensales:");
		lblCantidadCuatro.setForeground(Color.BLACK);
		lblCantidadCuatro.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		lblCantidadCuatro.setBounds(302, 148, 143, 41);
		panelTabla.add(lblCantidadCuatro);
		
		lblCantidadSeis = new JLabel("Seis comensales:");
		lblCantidadSeis.setForeground(Color.BLACK);
		lblCantidadSeis.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		lblCantidadSeis.setBounds(540, 96, 143, 41);
		panelTabla.add(lblCantidadSeis);
		
		lblCantidadOcho = new JLabel("Ocho comensales:");
		lblCantidadOcho.setForeground(Color.BLACK);
		lblCantidadOcho.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		lblCantidadOcho.setBounds(540, 148, 143, 41);
		panelTabla.add(lblCantidadOcho);
		{
			conteo();
		}

        
	}
	
	 public static boolean esNumero(String str) {
	        try {
	            Integer.parseInt(str);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	 private void conteo() {
		 lblCantidadTotalMesas.setText("Total mesas: "+control.cantidadMesasTotal());
		 lblCantidadDos.setText("Dos comensales: "+control.cantidadMesas(2));
		 lblCantidadCuatro.setText("Cuatro comensales: "+control.cantidadMesas(4));
		 lblCantidadSeis.setText("Seis comensales: "+control.cantidadMesas(6));
		 lblCantidadOcho.setText("Ocho comensales: "+control.cantidadMesas(8));
	 }
}

