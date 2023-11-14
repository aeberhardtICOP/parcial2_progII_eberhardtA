package model;

public class Ocupada implements Estado{

	@Override
	public void liberar(Mesa m) {
		m.setEstado(new Liberada());
		System.out.println("Mesa liberada correctamente!");
		
	}

	@Override
	public void reservar(Mesa m) {
		System.out.println("Esta mesa esta ocupada, no se puede reservar!");
		
	}

	@Override
	public void ocupar(Mesa m) {
		System.out.println("Esta mesa ya esta ocupada!");
		
	}

}
