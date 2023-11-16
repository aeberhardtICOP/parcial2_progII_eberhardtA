package model;

import javax.swing.JOptionPane;

import excepciones.EstadoInvalidoException;

public class Ocupada implements Estado{

	@Override
	public void liberar(Mesa m) {
		m.setEstado(new Liberada());
		System.out.println("Mesa liberada correctamente!");
		JOptionPane.showMessageDialog(null, "Mesa liberada correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	public void reservar(Mesa m) throws EstadoInvalidoException{
		System.out.println("Esta mesa esta ocupada, no se puede reservar!");
		JOptionPane.showMessageDialog(null, "Esta mesa esta ocupada, no se puede reservar!", "Error", JOptionPane.ERROR_MESSAGE);
		throw new EstadoInvalidoException("Estado invalido");
		
	}

	@Override
	public void ocupar(Mesa m) throws EstadoInvalidoException{
		System.out.println("Esta mesa ya esta ocupada!");
		JOptionPane.showMessageDialog(null, "Esta mesa ya esta ocupada!", "Error", JOptionPane.ERROR_MESSAGE);
		throw new EstadoInvalidoException("Estado invalido");
		
	}

}
