package model;

public class Mesa {
	private static Long nro=1L;
	private Long nroMesa;
	private Estado estado;
	private int capacidad;
	private double consumo;
	
	public Mesa(int capacidad, double consumo) {
		super();
		this.nroMesa = nro++;;
		this.estado = new Liberada();
		this.capacidad = capacidad;
		this.consumo = consumo;
	}

	public Mesa() {
		super();
		this.nroMesa=nro++;
		this.estado=new Liberada();
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

	public double getConsumo() {
		return consumo;
	}

	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}
	
	public void liberar() {
		this.estado.liberar(this);
	}
	
	public void reservar() {
		this.estado.reservar(this);
	}
	
	public void ocupar() {
		this.estado.ocupar(this);
	}
	
	public String enQueEstadoEstoy() {
		String estado="";
		if(this.estado instanceof Reservada) {
			estado="Reservada";
		}else if(this.estado instanceof Ocupada) {
			estado="Ocupada";
		}else if(this.estado instanceof Liberada){
			estado="Liberada";
		}
		return estado;
	}
}
