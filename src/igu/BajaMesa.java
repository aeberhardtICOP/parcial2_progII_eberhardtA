package igu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.Controlador;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import excepciones.MesaConReservasException;
import excepciones.MesaOcupadaException;
import model.Estado;
import model.Liberada;
import model.Mesa;
import model.Reservada;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

public class BajaMesa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador control;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JLabel lblEstado;
	private JLabel lblComensales;
	private JLabel lblMesaNro;
	private String nroMesa;
	private JButton btnEliminar;

	public BajaMesa(Controlador control) {
		setTitle("BajaMesa");
		setResizable(false);
		this.control=control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 656);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTabla = new JPanel();
		panelTabla.setBorder(new TitledBorder(null, "Mesas:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTabla.setBounds(409, 11, 490, 595);
		contentPane.add(panelTabla);
		panelTabla.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 470, 573);
		panelTabla.add(scrollPane);
		modeloTabla = new DefaultTableModel(new Object[]{"Nro MESA", "ESTADO", "CAPACIDAD"}, 0) {
			 public boolean isCellEditable(int row, int column) {
	             return false;
			 }
			};
			
			for (Mesa mesa : control.listaMesasLambda()) {
	            String estadoMesa = mesa.enQueEstadoEstoy();  
	            String numeroMesa = String.valueOf(mesa.getNroMesa());
	            String capacidad = String.valueOf(mesa.getCapacidad());

	            modeloTabla.addRow(new Object[]{numeroMesa, estadoMesa, capacidad});
	        }
			
			
		table = new JTable(modeloTabla);
		table.getColumnModel().getColumn(0).setCellRenderer(new EstadoCellRenderer());
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		table.addMouseListener(new MouseAdapter() {
	           @Override
	           public void mouseClicked(MouseEvent e) {
	                if (e.getClickCount() == 2) {
	                   
	                    int selectedRow = table.getSelectedRow();
	                    if (selectedRow != -1) {
	                    	nroMesa= table.getValueAt(selectedRow, 0).toString();
	                    	lblMesaNro.setText("NRO MESA: "+nroMesa);
	                    	lblEstado.setText(table.getValueAt(selectedRow, 1).toString());
	                    	lblComensales.setText(table.getValueAt(selectedRow, 2).toString());
	                    }
	                }
	            }
	        });
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Baja", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 390, 595);
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
		
		lblMesaNro = new JLabel("MESA NRO: ");
		lblMesaNro.setForeground(new Color(0, 128, 128));
		lblMesaNro.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lblMesaNro.setBounds(97, 61, 202, 41);
		panel.add(lblMesaNro);
		
		lblComensales = new JLabel("");
		lblComensales.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblComensales.setBounds(54, 113, 216, 32);
		panel.add(lblComensales);
		
		lblEstado = new JLabel("");
		lblEstado.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEstado.setBounds(54, 156, 216, 32);
		panel.add(lblEstado);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(145, 199, 107, 48);
		panel.add(btnEliminar);
		
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					control.bajaMesa(nroMesa);
					 DefaultTableModel modelo = (DefaultTableModel) table.getModel();
					  int filaSeleccionada = table.getSelectedRow();
					  if(filaSeleccionada >= 0) {
					     nroMesa = (String) table.getValueAt(filaSeleccionada, 0);
					     abrirDialog();
					    
					  } else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar una mesa!!!", "Error", JOptionPane.ERROR_MESSAGE);
					  }
				} catch (MesaConReservasException e1) {
					JOptionPane.showMessageDialog(null, "No se puede eliminar la mesa por que tiene reservas asociadas!!!", "Error", JOptionPane.ERROR_MESSAGE);
				} catch(NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una mesa!!!", "Error", JOptionPane.ERROR_MESSAGE);
				} catch(MesaOcupadaException e3) {
					JOptionPane.showMessageDialog(null, "No se puede eliminar una mesa ocupada!!", "Error", JOptionPane.ERROR_MESSAGE);
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
        
        ConfirmacionBaja dialog = new ConfirmacionBaja(nroMesa, control);
        dialog.setDefaultCloseOperation(ReservaMesa.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                borrarRegistros();
                control.listaMesasLambda().forEach(mesa -> {
				    String estadoMesa = estadoAString(mesa.getEstado());
				    String numeroMesa = String.valueOf(mesa.getNroMesa());
				    String capacidad = String.valueOf(mesa.getCapacidad());

				    modeloTabla.addRow(new Object[]{numeroMesa, estadoMesa, capacidad});
				});
                lblMesaNro.setText("NRO MESA: ");
            	lblEstado.setText("");
            	lblComensales.setText("");
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
