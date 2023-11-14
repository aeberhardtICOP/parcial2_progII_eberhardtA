package model;

import java.util.Date;

public class Reserva {
	private Long id;
	private Date fecha;
	private String nombre;
	private String apellido;
	private int cantidadComensales;
	private Mesa mesa;
	
	public Reserva(Long id, String nombre, String apellido, int cantidadComensales, Mesa mesa) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cantidadComensales = cantidadComensales;
		this.mesa = mesa;
	}

	public Reserva() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getCantidadComensales() {
		return cantidadComensales;
	}

	public void setCantidadComensales(int cantidadComensales) {
		this.cantidadComensales = cantidadComensales;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
}
