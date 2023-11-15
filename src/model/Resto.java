package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Resto {
	private static Long nro=1L;
	private Scanner scanner;
	private Long id;
	private String nombre;
	private String direccion;
	private String localidad;
	private HashMap<Long, Mesa>mesas;
	private HashMap<Long, Reserva>reservas;
	
	
	public Resto(String nombre, String direccion, String localidad) {
		super();
		this.id=nro++;
		this.nombre = nombre;
		this.direccion = direccion;
		this.localidad = localidad;
		this.mesas=new HashMap<Long, Mesa>();
		this.reservas=new HashMap<Long, Reserva>();
		this.scanner=new Scanner(System.in);
	}

	

	public Resto() {
		super();
		this.id=nro++;
		this.scanner=new Scanner(System.in);
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getLocalidad() {
		return localidad;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}



	public HashMap<Long, Mesa> getMesas() {
		return mesas;
	}



	public void setMesas(HashMap<Long, Mesa> mesas) {
		this.mesas = mesas;
	}
	
	public void agregarMesa(Mesa m) {
		this.mesas.put(m.getNroMesa(), m);
	}
	
	public void eliminarMesa(Long nroMesa) {
		this.mesas.remove(nroMesa);
	}
	
	public void mostrarMesas() {
		for (Entry<Long, Mesa> entry : this.mesas.entrySet()) {
		
			System.out.println("Nro MESA: "+entry.getKey()+" | ESTADO: " +entry.getValue().enQueEstadoEstoy()+" | Para "+entry.getValue().getCapacidad()+" personas");
		}
	}
	
	public void cargarMesas(int cantidad, int comensales) {
		for(int i =0; i<cantidad;i++) {
			Mesa m = new Mesa(comensales, 0);
			mesas.put(m.getNroMesa(), m);
		}
		
	}
	
	public void mostrarInformacion() {
		int mReservadas=0;
		int mLiberadas=0;
		int mOcupadas=0;
		for (Entry<Long, Mesa> entry : this.mesas.entrySet()) {
			if(entry.getValue().enQueEstadoEstoy().equals("Reservada")) {
				mReservadas++;
			}else if(entry.getValue().enQueEstadoEstoy().equals("Liberada")) {
				mLiberadas++;
			}else if(entry.getValue().enQueEstadoEstoy().equals("Ocupada")) {
				mOcupadas++;
			}
		}
		System.out.println("------------------");
		System.out.println("-"+this.nombre+"-"
				+ "\n"+this.direccion+" - "+this.localidad);
		System.out.println("------------------");
		System.out.println("Mesas:"
				+ "\nOcupadas: "+mOcupadas
				+"\nReservadas: "+mReservadas
				+"\nLiberadas: "+mLiberadas);
			
		
	}
	public void mostrarInformacion(String fecha) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		try {
			int mReservadas=0;
			int mLiberadas=0;
			Date fechaDate = formatoFecha.parse(fecha);
			for (Entry<Long, Reserva> entry : this.reservas.entrySet()) {
				if(fechaDate.equals(entry.getValue().getFecha())) {
					mReservadas++;
				}
			}
			mLiberadas=this.mesas.size()-mReservadas;
			System.out.println("------------------");
			System.out.println("-"+this.nombre+"-"
					+ "\n"+this.direccion+" - "+this.localidad);
			System.out.println("------------------");
			System.out.println("Mesas:"
					+"\nReservadas: "+mReservadas
					+"\nLiberadas: "+mLiberadas);
		} catch (ParseException e) {
			System.out.println("NO INGRESO FORMATO VALIDO!");
		}
	}
	
	public void cambiarEstadoMesa() {
		mostrarMesas();
		System.out.println("Ingrese NRO de mesa:");
		Long nro = scanner.nextLong();
		try {
			Mesa mes = this.mesas.get(nro);
			System.out.println("Ingrese que estado desea asignar: "
					+ "\nActual: "+mes.enQueEstadoEstoy()
					+"\n1)Reservada"
					+ "\n2)Liberada"
					+ "\n3)Ocupada");
			int o = scanner.nextInt();
			switch(o) {
			case 1:
				mes.reservar();
				break;
			case 2:
				mes.liberar();
				break;
			case 3:
				mes.ocupar();
				break;
				default:
					System.out.println("No ingreso opcion valida!");
			}
			
		}catch(NullPointerException e) {
			System.out.println("No existe esa mesa..");
		}
	}
	public void listarMesas(String estado) {
		for (Entry<Long, Mesa> entry : this.mesas.entrySet()) {
			if(entry.getValue().enQueEstadoEstoy().equals(estado)) {
				System.out.println("Nro MESA: "+entry.getKey()+" | ESTADO: " +entry.getValue().enQueEstadoEstoy()+" | Para "+entry.getValue().getCapacidad()+" personas");
			}
		}
	}
	public void listarMesas(String estado, String fecha) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		HashMap<Long, String>mesasParaFecha=new HashMap<Long, String>();
		try {
			Date fechaDate=formatoFecha.parse(fecha);
			for (Mesa mesa : mesas.values()) {
                mesasParaFecha.put(mesa.getNroMesa(), "Libre");
            }
            for (Reserva reserva : reservas.values()) {
                if (reserva.getFecha().equals(fechaDate)) {
                    mesasParaFecha.put(reserva.getMesa().getNroMesa(), "Reservada");
                }
            }
            
            for (Entry<Long, String> entry : mesasParaFecha.entrySet()) {
            	if(this.mesas.get(entry.getKey()).enQueEstadoEstoy().equals(estado)) {
            		System.out.println("Mesa NRO "+entry.getKey()+" -- "+entry.getValue());
            	}
            	else if(estado.equals("")){
                	System.out.println("Mesa NRO "+entry.getKey()+" -- "+entry.getValue());
            	}
            }
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void generarReserva() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		scanner.nextLine();
		System.out.println("Ingrese nombre:");
		String nom=scanner.nextLine();
		System.out.println("Ingrese apellido: ");
		String ape = scanner.nextLine();
		System.out.println("Ingrese fecha: (FORMATO dd/mm/aaaa)");
		String fecha = scanner.nextLine();
		try {
			Date fechaDate =formatoFecha.parse(fecha);
			System.out.println("Ingrese cantidad de comensales:");
			int comensales = scanner.nextInt();
			System.out.println("Buscando mesas que se adapten....");
			System.out.println("Las mesas disponinles para la fecha "+fechaDate+" y para "+comensales+" personas son:");
			HashMap<Long, Mesa> mesasDisponibles = new HashMap<Long, Mesa>();
			 for (Mesa mesa : mesas.values()) {
		            if (mesa.getCapacidad() >= comensales) {
		                if (esMesaDisponibleEnFecha(mesa, fechaDate)) {
		                	System.out.println("Nro MESA: "+mesa.getNroMesa()+" | ESTADO: " +mesa.enQueEstadoEstoy()+" | Para "+mesa.getCapacidad()+" personas");
		                    mesasDisponibles.put(mesa.getNroMesa(), mesa);
		                }
		            }
		        }
			 System.out.println("Ingrese NRO de mesa que desea reservar para la fecha "+fechaDate);
			 Long nroMesa = scanner.nextLong();
			 Reserva res = new Reserva(fechaDate, nom, ape, comensales, this.mesas.get(nroMesa));
			 this.reservas.put(res.getId(), res);
			 System.out.println("RESERVA GUARDADA CON EXITO!");
			 
		} catch (ParseException e) {
			System.out.println("No ingreso un formato valido...");
		}
	}
	 private boolean esMesaDisponibleEnFecha(Mesa mesa, Date fecha) {
	        for (Reserva reserva : reservas.values()) {
	            if (reserva.getMesa().equals(mesa) && reserva.getFecha().equals(fecha)) {
	                return false; 
	            }
	        }
	        return true; 
	    } 
	 
	 public void mostrarCapacidad() {
		 int cantidadTotal=0;
		 int cantidadLiberada=0;
		 int cantidadOcupada=0;
		 int cantidadReservada=0;
		 for (Entry<Long, Mesa> entry : this.mesas.entrySet()) {
			 cantidadTotal+=entry.getValue().getCapacidad();
			 if(entry.getValue().enQueEstadoEstoy().equals("Reservada")){
				 cantidadReservada+=entry.getValue().getCapacidad();
			 }else if(entry.getValue().enQueEstadoEstoy().equals("Liberada")) {
				 cantidadLiberada+=entry.getValue().getCapacidad();
			 }else if(entry.getValue().enQueEstadoEstoy().equals("Ocupada")) {
				 cantidadOcupada+=entry.getValue().getCapacidad();
			 }
		 }
		 System.out.println("----------------");
		 System.out.println("CAPACIDAD: ");
		 System.out.println("----------------");
		 System.out.println("TOTAL: "+cantidadTotal
				 +"\nLIBRES: "+cantidadLiberada
				 +"\nRESERVADAS: "+cantidadReservada
				 +"\nOCUPADAS: "+cantidadOcupada);
		 
		
	 }
	 public void mostrarCapacidad(String fecha) {
		 SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		 Date fechaDate;
		try {
			fechaDate = formatoFecha.parse(fecha);
		
			 int cantidadTotal=0;
			 int cantidadReservada=0;
			 
			 for (Entry<Long, Mesa> entry : this.mesas.entrySet()) {
				 cantidadTotal+=entry.getValue().getCapacidad();
			 }
			 for(Entry<Long, Reserva> entry : this.reservas.entrySet()) {
				 if(entry.getValue().getFecha().equals(fechaDate)) {
					 cantidadReservada+=entry.getValue().getMesa().getCapacidad();
				 }
			 }
			 int cantidadLiberada=cantidadTotal-cantidadReservada;
			 System.out.println("----------------");
			 System.out.println("CAPACIDAD: ");
			 System.out.println("----------------");
			 System.out.println("TOTAL: "+cantidadTotal
					 +"\nLIBRES: "+cantidadLiberada
					 +"\nRESERVADAS: "+cantidadReservada);

		 
		} catch (ParseException e) {
			System.out.println("Formato incorrecto..");
		}
	 }
	
	 public void altaMesa() {
		 System.out.println("------------");
		 System.out.println("ALTA DE MESA");
		 System.out.println("------------");
		 System.out.println("Ingrese capacidad:");
		 int capacidad = scanner.nextInt();
		 System.out.println("Ingrese consumo: ");
		 int consumo = scanner.nextInt();
		 
		 Mesa mes = new Mesa(capacidad, consumo);
		 mesas.put(mes.getNroMesa(), mes);
		 
		 
	 }
	 
	 public void bajaMesa() {
		 System.out.println("------------");
		 System.out.println("BAJA DE MESA");
		 System.out.println("------------");
		 mostrarMesas();
		 System.out.println("Ingrese nro de la mesa que desea dar de baja..");
		 try {
			 Long nro = scanner.nextLong();
			 boolean teniaReserva=false;
			 for(Entry<Long, Reserva> entry : this.reservas.entrySet()) {
				 if(entry.getValue().getMesa().getNroMesa()==nro) {
					 reservas.remove(entry.getValue().getId());
					 teniaReserva = true;
				 }
			 }
			 
			 if(teniaReserva=true) {
				 System.out.println("Se eliminaron todas las reservas vinculadas a la mesa "+nro);
			 }
			 
			 mesas.remove(nro);
			 System.out.println("mesa eliminada");
		 }catch(NullPointerException e) {
			 System.out.println("no existe esa mesa...");
		 }
	 }
}

