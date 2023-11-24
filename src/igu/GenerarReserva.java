package igu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import model.Mesa;
import service.Controlador;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.Font;

public class GenerarReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador contr;
	private JTable table;
	private JTextField txtComensales;
	private List<Mesa>mesas;
	private DefaultTableModel modeloTabla;
	private JFormattedTextField ftxtFecha;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private String nroMesa;
	private int iElementoTabla;
	private JButton btnBuscarMesas;
	
		
	public GenerarReserva(Controlador contr) {
		setTitle("Generar Reserva");
		this.contr=contr;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 659);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlAltaReserva = new JPanel();
		pnlAltaReserva.setBounds(5, 5, 913, 300);
		pnlAltaReserva.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos reserva:", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlAltaReserva.setLayout(null);
		contentPane.add(pnlAltaReserva);
		

        MaskFormatter maskFormatter;
		try {
			maskFormatter = new MaskFormatter("##/##/####");
			ftxtFecha = new JFormattedTextField(maskFormatter);
			ftxtFecha.setBounds(81, 56, 287, 38);
			pnlAltaReserva.add(ftxtFecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
		
		txtComensales = new JTextField();
		txtComensales.setBounds(81, 141, 287, 38);
		pnlAltaReserva.add(txtComensales);
		txtComensales.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(81, 39, 46, 14);
		pnlAltaReserva.add(lblFecha);
		
		JLabel lblCantidadComensales = new JLabel("Comensales:");
		lblCantidadComensales.setBounds(81, 123, 147, 14);
		pnlAltaReserva.add(lblCantidadComensales);
		
		btnBuscarMesas = new JButton("Buscar disponibles");
		btnBuscarMesas.setBounds(320, 218, 136, 46);
		pnlAltaReserva.add(btnBuscarMesas);
		btnBuscarMesas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				borrarRegistros();
				for (Mesa mesa : contr.mesasDisponiblesPara(ftxtFecha.getText(), txtComensales.getText())) {
		            String estadoMesa = "Liberada";  
		            String numeroMesa = String.valueOf(mesa.getNroMesa());
		            String capacidad = String.valueOf(mesa.getCapacidad());

		            modeloTabla.addRow(new Object[]{numeroMesa, estadoMesa, capacidad});
		        }
				
			}
		});
		
		JPanel panelNombreAp = new JPanel();
		panelNombreAp.setBorder(new TitledBorder(null, "Datos:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNombreAp.setBounds(553, 24, 334, 246);
		pnlAltaReserva.add(panelNombreAp);
		panelNombreAp.setLayout(null);
		
		JLabel lblMesaNro = new JLabel("MESA NRO: ");
		lblMesaNro.setForeground(new Color(0, 128, 128));
		lblMesaNro.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lblMesaNro.setBounds(75, 24, 202, 41);
		panelNombreAp.add(lblMesaNro);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(85, 76, 163, 31);
		panelNombreAp.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setEnabled(false);
   	  
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(85, 137, 163, 31);
		panelNombreAp.add(txtApellido);
		txtApellido.setEnabled(false);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(31, 84, 46, 14);
		panelNombreAp.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(29, 145, 46, 14);
		panelNombreAp.add(lblApellido);
		
		JButton btnGenerarReserva = new JButton("Generar");
		btnGenerarReserva.setBounds(188, 191, 122, 31);
		panelNombreAp.add(btnGenerarReserva);
		btnGenerarReserva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contr.generarReserva(nroMesa, txtApellido.getText(), txtNombre.getText(), ftxtFecha.getText(), txtComensales.getText());
				modeloTabla.removeRow(table.getSelectedRow());
				JOptionPane.showMessageDialog(null, "Reserva cargada con exito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				ftxtFecha.setText("");
				txtApellido.setText("");
				txtNombre.setText("");
			}
			
		});
		JLabel lblVolver = new JLabel("<--Volver");
		lblVolver.setForeground(new Color(0, 0, 255));
		lblVolver.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblVolver.setBounds(10, 14, 82, 14);
		pnlAltaReserva.add(lblVolver);
		lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuPrincipal men = new MenuPrincipal(contr);
                men.setVisible(true);
                dispose();
            }
        });
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Mesas disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 316, 909, 293);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 889, 271);
		panel.add(scrollPane);
		
		 modeloTabla = new DefaultTableModel(new Object[]{"Nro MESA", "ESTADO", "CAPACIDAD"}, 0) {
		 public boolean isCellEditable(int row, int column) {
             return false;
		 }
		};
        
		
		table = new JTable(modeloTabla);
		table.getColumnModel().getColumn(0).setCellRenderer(new EstadoCellRenderer());
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                   
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                    	  nroMesa = table.getValueAt(selectedRow, 0).toString();
                    	  iElementoTabla=table.getSelectedColumn();
                    	  lblMesaNro.setText("MESA NRO: "+nroMesa);
                    	  txtNombre.setEnabled(true);
                    	  txtApellido.setEnabled(true);
                    	  
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
}
