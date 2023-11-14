package model;

public class Liberada implements Estado{

	@Override
	public void liberar(Mesa m) {
		System.out.println("Esta mesa ya esta liberada!");
		
	}

	@Override
	public void reservar(Mesa m) {
		m.setEstado(new Reservada());
		System.out.println("Mesa reservada correctamente!");
		
	}

	@Override
	public void ocupar(Mesa m) {
		m.setEstado(new Ocupada());
		System.out.println("Mesa ocupada correctamente!");
	}

}
