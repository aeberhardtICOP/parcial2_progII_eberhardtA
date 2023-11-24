package igu;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.Controlador;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador contr;

	public MenuPrincipal(Controlador contr) {
		this.contr=contr;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 654);
		
		JMenuBar mnu = new JMenuBar();
		setJMenuBar(mnu);
		
		JMenu mnReserva = new JMenu("Reserva");
		mnu.add(mnReserva);
		
		JMenuItem mnitmRegistrarRes = new JMenuItem("Registrar reserva");
		mnReserva.add(mnitmRegistrarRes);
		mnitmRegistrarRes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GenerarReserva gr = new GenerarReserva(contr);
				gr.setVisible(true);
				dispose();
				
			}
		});
		
		JMenu mnuGestion = new JMenu("Gestion");
		mnu.add(mnuGestion);
		
		JMenuItem mnitmAlta = new JMenuItem("Alta mesa");
		mnuGestion.add(mnitmAlta);
		mnitmAlta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AltaMEsa altaMesa = new AltaMEsa(contr);
				altaMesa.setVisible(true);
				dispose();
				
			}
		});
		
		
		JMenuItem mnitmBaja = new JMenuItem("Baja mesa");
		mnuGestion.add(mnitmBaja);
		mnitmBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BajaMesa bajaMesa = new BajaMesa(contr);
				bajaMesa.setVisible(true);
				dispose();
				
			}
		});
		
		JMenu mnuInfo = new JMenu("Info");
		mnu.add(mnuInfo);
		
		JMenuItem mnuItm = new JMenuItem("Vista actual");
		mnuInfo.add(mnuItm);
		mnuItm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaActual vi = new VistaActual(contr);
				vi.setVisible(true);
				dispose();
				
			}
		});
		
		JMenuItem mnitmVistaxfecha = new JMenuItem("Vista por fecha");
		mnuInfo.add(mnitmVistaxfecha);
		mnitmVistaxfecha.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaPorFecha v = new VistaPorFecha(contr);
				v.setVisible(true);
				dispose();
				
			}
		});
		
		JMenuItem mnitmConsumos = new JMenuItem("Consumos");
		mnuInfo.add(mnitmConsumos);
		mnitmConsumos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ConsumoMesas c = new ConsumoMesas(contr);
				c.setVisible(true);
				dispose();
				
			}
		});
		
		JMenuItem mnitmAcercaDe = new JMenuItem("Acerca de..");
		mnuInfo.add(mnitmAcercaDe);
		mnitmAcercaDe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AcercaDeMi a = new AcercaDeMi();
				a.setVisible(true);
				a.setModal(true);
				
			}
		});
		
		JMenuItem mnitmSalir = new JMenuItem("Salir");
		mnu.add(mnitmSalir);
		mnitmSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		JMenuBar menuBar_1 = new JMenuBar();
		mnu.add(menuBar_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
