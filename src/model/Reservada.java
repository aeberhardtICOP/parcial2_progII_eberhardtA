package model;

public class Reservada implements Estado{

	@Override
	public void liberar(Mesa m) {
		System.out.println("Esta mesa esta reservada, no se puede liberar!");
	}

	@Override
	public void reservar(Mesa m) {
		System.out.println("Esta mesa ya esta reservada!");
		
	}

	@Override
	public void ocupar(Mesa m) {
		System.out.println("Mesa ocupada correctamente!!");
		m.setEstado(new Ocupada());
	}

}
