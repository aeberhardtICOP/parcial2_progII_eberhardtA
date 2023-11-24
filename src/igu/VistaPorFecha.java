package igu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.Controlador;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import model.Estado;
import model.Liberada;
import model.Mesa;
import model.Reservada;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VistaPorFecha extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador control;
	private JTable table;
	private JFormattedTextField ftxtFecha;
	private MaskFormatter maskFormatter;
	private String nroMesa;
	private String capacidadMesa;
	private DefaultTableModel modeloTabla;
	private JButton btnReservar;
	private JLabel lblEstado;

	public VistaPorFecha(Controlador control) {
		setTitle("Vista por fecha");
		this.control=control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Vista:", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 908, 271);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblVolver = new JLabel("<--Volver");
		lblVolver.setForeground(new Color(0, 0, 255));
		lblVolver.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblVolver.setBounds(10, 14, 82, 14);
		panel.add(lblVolver);
		lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuPrincipal men = new MenuPrincipal(control);
                men.setVisible(true);
                dispose();
            }
        });
		
		ftxtFecha = new JFormattedTextField();
		ftxtFecha.setBounds(36, 71, 241, 39);
		try {
			maskFormatter = new MaskFormatter("##/##/####");
			ftxtFecha = new JFormattedTextField(maskFormatter);
			ftxtFecha.setBounds(36, 67, 287, 38);
			panel.add(ftxtFecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(36, 53, 46, 14);
		panel.add(lblFecha);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(199, 139, 121, 39);
		panel.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				borrarRegistros();
				control.estadoMesasFecha(ftxtFecha.getText()).forEach(mesa -> {
				    String estadoMesa = estadoAString(mesa.getEstado());
				    String numeroMesa = String.valueOf(mesa.getNroMesa());
				    String capacidad = String.valueOf(mesa.getCapacidad());

				    modeloTabla.addRow(new Object[]{numeroMesa, estadoMesa, capacidad});
				});
			}
		});
		
		JLabel lblMesaNro = new JLabel("MESA NRO: ");
		lblMesaNro.setForeground(new Color(0, 128, 128));
		lblMesaNro.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lblMesaNro.setBounds(604, 53, 202, 41);
		panel.add(lblMesaNro);
		
		JLabel lblComensales = new JLabel("");
		lblComensales.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblComensales.setBounds(564, 105, 216, 32);
		panel.add(lblComensales);
		
		lblEstado = new JLabel("");
		lblEstado.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEstado.setBounds(564, 139, 216, 32);
		panel.add(lblEstado);
		
		btnReservar = new JButton("Reservar");
		btnReservar.setBounds(604, 197, 121, 39);
		panel.add(btnReservar);
		btnReservar.setEnabled(false);
		btnReservar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirDialog();
				
			}
		});
		
		JPanel panelMesas = new JPanel();
		panelMesas.setBorder(new TitledBorder(null, "Mesas:", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelMesas.setBounds(10, 287, 908, 275);
		contentPane.add(panelMesas);
		panelMesas.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 888, 253);
		panelMesas.add(scrollPane);
		
		modeloTabla = new DefaultTableModel(new Object[]{"Nro MESA", "ESTADO", "CAPACIDAD"}, 0) {
			 public boolean isCellEditable(int row, int column) {
	             return false;
			 }
			};
			

		table = new JTable(modeloTabla);
		table.getColumnModel().getColumn(0).setCellRenderer(new EstadoCellRenderer());
		table.getColumnModel().getColumn(1).setCellRenderer(new EstadoCellRenderer());
		table.getColumnModel().getColumn(2).setCellRenderer(new EstadoCellRenderer());
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		
		table.addMouseListener(new MouseAdapter() {
	           @Override
	           public void mouseClicked(MouseEvent e) {
	                if (e.getClickCount() == 2) {
	                   
	                    int selectedRow = table.getSelectedRow();
	                    if (selectedRow != -1) {
	                    	capacidadMesa=table.getValueAt(selectedRow, 2).toString();
	                    	nroMesa= table.getValueAt(selectedRow, 0).toString();
	                    	lblMesaNro.setText("NRO MESA: "+nroMesa);
	                    	lblEstado.setText(table.getValueAt(selectedRow, 1).toString());
	                    	lblComensales.setText("CAPACIDAD: "+table.getValueAt(selectedRow, 2).toString());
	                    	if(table.getValueAt(selectedRow, 1).equals("Liberada")) {
	                    		btnReservar.setEnabled(true);
	                    	}else {
	                    		btnReservar.setEnabled(false);
	                    	}
	                  
	                    }
	                }
	            }
	        });
	}
	 private void borrarRegistros() {
	        int rowCount = modeloTabla.getRowCount();

	        for (int i = rowCount - 1; i >= 0; i--) {
	        	modeloTabla.removeRow(i);
	        }
	    }
	 
	 private void abrirDialog() {
	        
	        ReservaMesa dialog = new ReservaMesa(control, nroMesa, ftxtFecha.getText(),this, capacidadMesa);
	        dialog.setDefaultCloseOperation(ReservaMesa.DISPOSE_ON_CLOSE);
	        dialog.setVisible(true);
	        dialog.addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosed(WindowEvent e) {
	                borrarRegistros();
	                control.estadoMesasFecha(ftxtFecha.getText()).forEach(mesa -> {
					    String estadoMesa = estadoAString(mesa.getEstado());
					    String numeroMesa = String.valueOf(mesa.getNroMesa());
					    String capacidad = String.valueOf(mesa.getCapacidad());

					    modeloTabla.addRow(new Object[]{numeroMesa, estadoMesa, capacidad});
					});
	            }
	        });
	       
	    }
	 
	 private String estadoAString(Estado est) {
		 if(est instanceof Reservada) {
			 return "Reservada";
		 }else if(est instanceof Liberada){
			 return "Liberada";
		 }else {
			 return "Ocupada";
		 }
	 }
}
