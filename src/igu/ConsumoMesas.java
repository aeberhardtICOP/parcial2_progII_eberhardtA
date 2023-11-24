package igu;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.Controlador;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.Mesa;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ConsumoMesas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador control;
	private JTable table;
	private DefaultTableModel modeloTabla;
	
	public ConsumoMesas(Controlador control) {
		setResizable(false);
		setTitle("Consumos");
		this.control=control;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1196, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Consumos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVolver = new JLabel("<--Volver");
		lblVolver.setForeground(new Color(0, 0, 255));
		lblVolver.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblVolver.setBounds(10, 14, 82, 14);
		contentPane.add(lblVolver);
		lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuPrincipal men = new MenuPrincipal(control);
                men.setVisible(true);
                dispose();
            }
        });
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 1160, 601);
		contentPane.add(scrollPane);
		
		modeloTabla = new DefaultTableModel(new Object[]{"Nro MESA", "Consumo acumulado"}, 0) {
			 public boolean isCellEditable(int row, int column) {
	             return false;
			 }
			};
			
			for (Mesa mesa : control.listaMesasPorConsumo()) {
	            String numeroMesa = String.valueOf(mesa.getNroMesa());
	            String consumo = String.valueOf(mesa.getConsumo());

	            modeloTabla.addRow(new Object[]{numeroMesa, consumo});
	        }
			
			
		table = new JTable(modeloTabla);
		table.getColumnModel().getColumn(0).setCellRenderer(new EstadoCellRenderer());
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		
	}
}
