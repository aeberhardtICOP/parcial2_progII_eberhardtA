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

public class AltaMEsa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador control;
	private JTable table;
	private JTextField textField;
	private DefaultTableModel modeloTabla;

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
		panelCarga.setBounds(10, 11, 905, 353);
		contentPane.add(panelCarga);
		panelCarga.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(54, 80, 273, 38);
		panelCarga.add(textField);
		textField.setColumns(10);
		
		JLabel lblCapacidadMesa = new JLabel("Capacidad mesa:");
		lblCapacidadMesa.setBounds(54, 55, 153, 14);
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
		btnGuadrar.setBounds(714, 276, 153, 52);
		panelCarga.add(btnGuadrar);
		btnGuadrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(esNumero(textField.getText())==true) {
					control.altaMesa(textField.getText());
					String capacidad = textField.getText();
					Long nroMesa=control.UltimoIdMesa();
					textField.setText("");
					Object[] fila = new Object[3];
					fila[0] = nroMesa;
					fila[1] = "Liberada";
					fila[2] = capacidad;

					modeloTabla.addRow(fila);
					modeloTabla.fireTableDataChanged();
					table.setModel(modeloTabla);
				}else {
					  JOptionPane.showMessageDialog(null, "Error de capacidad: Ingrese un número.");
				}
			}
			
			
		});
		
		JPanel panelTabla = new JPanel();
		panelTabla.setBorder(new TitledBorder(null, "Mesas:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTabla.setBounds(10, 375, 905, 241);
		contentPane.add(panelTabla);
		panelTabla.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 885, 219);
		panelTabla.add(scrollPane);
		modeloTabla = new DefaultTableModel(new Object[]{"Nro MESA", "ESTADO", "CAPACIDAD"}, 0) {
			 public boolean isCellEditable(int row, int column) {
	             return false;
			 }
			};
			
		for (Mesa mesa : control.listaMesas()) {
            String estadoMesa = mesa.enQueEstadoEstoy();  
            String numeroMesa = String.valueOf(mesa.getNroMesa());
            String capacidad = String.valueOf(mesa.getCapacidad());

            modeloTabla.addRow(new Object[]{numeroMesa, estadoMesa, capacidad});
        }
		
		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
	}
	
	 public static boolean esNumero(String str) {
	        try {
	            Integer.parseInt(str);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	
}

