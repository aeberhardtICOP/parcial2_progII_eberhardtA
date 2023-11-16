package service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import excepciones.EstadoInvalidoException;
import excepciones.MesaConReservasException;
import model.Mesa;
import model.Reserva;
import model.Resto;
import model.Liberada;
import repository.RestóRepository;
import repository.MesaRepository;
import repository.ReservaRepository;
public class Controlador {
	private HashMap<Long, Resto>restaurantes;
	private MesaRepository mrep;
	private ReservaRepository rerep;
	private RestóRepository restorep;
	private Resto restaurante;	
	
	public Controlador() {
		this.restorep=new RestóRepository();
		this.restaurantes=restorep.traerAMemoria();
		this.mrep=new MesaRepository();
		this.rerep=new ReservaRepository(mrep);
	}
	
	public void seleccionarRestaurante(String nombre) {
		for (Entry<Long, Resto> entry : restaurantes.entrySet()) {
            if(entry.getValue().getNombre().equals(nombre)) {
            	System.out.println("Set - "+nombre+" to "+entry.getValue().getNombre());
            	this.restaurante=entry.getValue();
            	this.restaurante.setMesas(mrep.traerAMemoria(entry.getKey()));
            	this.restaurante.setReservas(rerep.traerAMemoria(entry.getKey()));
            }
        }
	}
	
	public List<String> listaNombresResto() {
		List<String> nombresRestos = new ArrayList<>();
        for (Map.Entry<Long, Resto> entry : this.restaurantes.entrySet()) {
            Resto resto = entry.getValue();
            nombresRestos.add(resto.getNombre());
        }
        return nombresRestos;
	}
	
	public List<Mesa> listaMesas(){
		List<Mesa>mesas = new ArrayList<Mesa>();
		for (Map.Entry<Long, Mesa> entry : this.restaurante.getMesas().entrySet()) {
            Mesa m = entry.getValue();
            System.out.println(m.enQueEstadoEstoy()+" "+m.getNroMesa());
            mesas.add(m);
        }
		return mesas;
	}
	
	public List<Mesa>mesasDisponiblesPara(String fecha, String comensales){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate;
		try {
			fechaDate = format.parse(fecha);
			HashMap<Long, Mesa>mesasDisp=restaurante.mesasDisponibles(fechaDate, Integer.parseInt(comensales));
			List<Mesa> mesas = new ArrayList<>(mesasDisp.values());
			return mesas; 
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Mesa>mesasDisponiblesPara(String fecha){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate;
		try {
			fechaDate = format.parse(fecha);
			HashMap<Long, Mesa>mesasDisp=restaurante.mesasDisponibles(fechaDate);
			List<Mesa> mesas = new ArrayList<>(mesasDisp.values());
			return mesas; 
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Mesa>mesasNoDisponiblesPara(String fecha){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate;
		try {
			fechaDate = format.parse(fecha);
			HashMap<Long, Mesa>mesasDisp=restaurante.mesasNoDisponibles(fechaDate);
			List<Mesa> mesas = new ArrayList<>(mesasDisp.values());
			return mesas; 
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void generarReserva(String nroMesa, String apellido, String nombre, String fecha, String comensales) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Long nro_mesa=Long.parseLong(nroMesa);
			int nroComensales = Integer.parseInt(comensales);
			Date fecha_date = format.parse(fecha);
			Mesa m=restaurante.getMesa(nro_mesa);
			Reserva res = new Reserva(rerep.ultimoIdReserva()+1, fecha_date, nombre, apellido, nroComensales, m);
			rerep.guardarReserva(res);
			restaurante.agregarReserva(res);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void altaMesa(String capacidad) {
		Mesa m = new Mesa(mrep.ultimoIdMesa(), new Liberada(), Integer.parseInt(capacidad), 0);
		restaurante.agregarMesa(m);
		mrep.guardarMesa(m, restaurante.getId());
	}
	
	public void bajaMesa(String nroMesa) throws MesaConReservasException {
		Long nro_mesa = Long.parseLong(nroMesa);
		mrep.eliminarMesa(nro_mesa);
	}
	
	public Mesa traerMesa(Long nroMesa) {
		return restaurante.getMesa(nroMesa);
	}
	
	public Long UltimoIdMesa() {
		return mrep.ultimoIdMesa();
	}
	
	public boolean liberarMesa(String nroMesa)  {
		Long nroM=Long.parseLong(nroMesa);
		Mesa m = restaurante.getMesa(nroM);
		try {
			m.liberar();
			mrep.editarEstadoMesa(nroM, "Liberada");
			return true;
		} catch (EstadoInvalidoException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean ocuparMesa(String nroMesa)  {
		try {
			Long nroM=Long.parseLong(nroMesa);
			Mesa m = restaurante.getMesa(nroM);
			m.ocupar();
			mrep.editarEstadoMesa(nroM, "Ocupada");
			return true;
		} catch (EstadoInvalidoException e) {
			e.printStackTrace();
			return false;
		}
	
	}
	public boolean reservarMesa(String nroMesa)  {
		try {
			Long nroM=Long.parseLong(nroMesa);
			Mesa m = restaurante.getMesa(nroM);
			m.reservar();
			mrep.editarEstadoMesa(nroM, "Reservada");
			return true;
		} catch (EstadoInvalidoException e) {
			System.out.println("Entro a la excepcion de reserva");
			return false;
		}
		
	}
		
}
