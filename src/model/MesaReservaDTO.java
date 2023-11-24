package model;

public class MesaReservaDTO {
	private Long nroMesa;
	private Estado estado;
	private int capacidad;
	
	public MesaReservaDTO(Long nroMesa, Estado estado, int capacidad) {
		super();
		this.nroMesa = nroMesa;
		this.estado = estado;
		this.capacidad = capacidad;
	}

	public MesaReservaDTO() {
		super();
	}

	public Long getNroMesa() {
		return nroMesa;
	}

	public void setNroMesa(Long nroMesa) {
		this.nroMesa = nroMesa;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	
	
}
