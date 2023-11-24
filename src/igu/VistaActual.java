package igu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.Controlador;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import excepciones.EstadoInvalidoException;
import model.Estado;
import model.Liberada;
import model.Mesa;
import model.Reservada;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VistaActual extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador control;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private String nroMesa;
	private JButton btnLiberar;
	private JButton btnOcupar;
	private JPanel panel_1;
	private JButton btnReservar;
	private JComboBox cmbxFiltro;

	public VistaActual(Controlador control) {
		setResizable(false);
		setTitle("Vista Actual");
		this.control=control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 943, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Acciones", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 907, 185);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMesaNro = new JLabel("MESA NRO: ");
		lblMesaNro.setForeground(new Color(0, 128, 128));
		lblMesaNro.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lblMesaNro.setBounds(477, 27, 202, 41);
		panel.add(lblMesaNro);
		
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
		
		JLabel lblComensales = new JLabel("");
		lblComensales.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblComensales.setBounds(437, 79, 216, 32);
		panel.add(lblComensales);
		
		JLabel lblEstado = new JLabel("");
		lblEstado.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEstado.setBounds(437, 113, 216, 32);
		panel.add(lblEstado);
		
		btnReservar = new JButton("Reservar");
		btnReservar.setBounds(756, 34, 89, 23);
		panel.add(btnReservar);
		btnReservar.setEnabled(false);
		btnReservar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				boolean flag=control.reservarMesa(nroMesa);
				System.out.println(flag);
				if(flag==true) {
					String estadoActual=table.getValueAt(fila, 1).toString();
					modeloTabla.setValueAt("Reservada", fila, 1);
					((AbstractTableModel) table.getModel()).fireTableCellUpdated(fila, 1);
					((AbstractTableModel) table.getModel()).fireTableCellUpdated(fila, 2);
					((AbstractTableModel) table.getModel()).fireTableCellUpdated(fila, 0);
				}else {
					System.out.println("ENTRO EN ELSE-RESERVAR");
				}
			}

		});
		
		btnLiberar = new JButton("Liberar");
		btnLiberar.setBounds(756, 79, 89, 23);
		panel.add(btnLiberar);
		btnLiberar.setEnabled(false);
		btnLiberar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
					boolean flag = control.liberarMesa(nroMesa);
				if(flag==true) {
						abrirDialog();
						modeloTabla.setValueAt("Liberada", fila, 1);
						((AbstractTableModel) table.getModel()).fireTableCellUpdated(fila, 1);
						((AbstractTableModel) table.getModel()).fireTableCellUpdated(fila, 2);
						((AbstractTableModel) table.getModel()).fireTableCellUpdated(fila, 0);
					}else {
					System.out.println("ENTRO EN ELSE-LIBERAR");
				}
				
			}
		});
		
		btnOcupar = new JButton("Ocupar");
		btnOcupar.setBounds(756, 122, 89, 23);
		panel.add(btnOcupar);
		btnOcupar.setEnabled(false);
		
		cmbxFiltro = new JComboBox();
		cmbxFiltro.setModel(new DefaultComboBoxModel(new String[] {"Ninguno", "Reservadas", "Ocupadas", "Liberadas"}));
		cmbxFiltro.setSelectedIndex(0);
		cmbxFiltro.setBounds(54, 74, 171, 32);
		panel.add(cmbxFiltro);
		cmbxFiltro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cmbxFiltro.getSelectedItem().equals("Ninguno")) {
					borrarRegistros();
					control.listaMesasLambda().forEach(m->{
			            String estadoMesa = m.enQueEstadoEstoy();  
			            String numeroMesa = String.valueOf(m.getNroMesa());
			            String capacidad = String.valueOf(m.getCapacidad());

			            modeloTabla.addRow(new Object[]{numeroMesa, estadoMesa, capacidad});
					});

				}else {
					borrarRegistros();
					control.listaMesasLambda(cmbxFiltro.getSelectedItem().toString()).forEach(m->{
			            String estadoMesa = m.enQueEstadoEstoy();  
			            String numeroMesa = String.valueOf(m.getNroMesa());
			            String capacidad = String.valueOf(m.getCapacidad());

			            modeloTabla.addRow(new Object[]{numeroMesa, estadoMesa, capacidad});
					});
				}
				
			}
		});
		
		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setBounds(54, 54, 46, 14);
		panel.add(lblFiltro);
		btnOcupar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				boolean flag=control.ocuparMesa(nroMesa);
				if(flag==true) {
					modeloTabla.setValueAt("Ocupada", fila, 1);
					((AbstractTableModel) table.getModel()).fireTableCellUpdated(fila, 1);
					((AbstractTableModel) table.getModel()).fireTableCellUpdated(fila, 2);
					((AbstractTableModel) table.getModel()).fireTableCellUpdated(fila, 0);
					
				}else {
					System.out.println("ENTRO EN ELSE-OCUPAR");
				}
			}
		});
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Mesas:", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 200, 907, 362);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 887, 340);
		panel_1.add(scrollPane);
		
		modeloTabla = new DefaultTableModel(new Object[]{"Nro MESA", "ESTADO", "CAPACIDAD"}, 0) {
			 public boolean isCellEditable(int row, int column) {
	             return false;
			 }
			};
			
			 control.listaMesasLambda().forEach(m->{
	            String estadoMesa = m.enQueEstadoEstoy();  
	            String numeroMesa = String.valueOf(m.getNroMesa());
	            String capacidad = String.valueOf(m.getCapacidad());

	            modeloTabla.addRow(new Object[]{numeroMesa, estadoMesa, capacidad});
	        });

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
	                    	nroMesa= table.getValueAt(selectedRow, 0).toString();
	                    	lblMesaNro.setText("NRO MESA: "+nroMesa);
	                    	lblEstado.setText(table.getValueAt(selectedRow, 1).toString());
	                    	lblComensales.setText("CAPACIDAD: "+table.getValueAt(selectedRow, 2).toString());
	                    	btnReservar.setEnabled(true);
	                    	btnLiberar.setEnabled(true);
	                    	btnOcupar.setEnabled(true);
	                    }else {
	                    	btnReservar.setEnabled(false);
	                    	btnLiberar.setEnabled(false);
	                    	btnOcupar.setEnabled(false);
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
    
    AsignarConsumoMesa dialog = new AsignarConsumoMesa(nroMesa, control);
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
