package model;

import javax.swing.JOptionPane;

import excepciones.EstadoInvalidoException;

public class Liberada implements Estado{

	@Override
	public void liberar(Mesa m) throws EstadoInvalidoException{
		System.out.println("Esta mesa ya esta liberada!");
		JOptionPane.showMessageDialog(null, "Esta mesa ya esta liberada!", "Error", JOptionPane.ERROR_MESSAGE);
		throw new EstadoInvalidoException("Estado invalido");
		
	}

	@Override
	public void reservar(Mesa m) {
		m.setEstado(new Reservada());
		System.out.println("Mesa reservada correctamente!");
		JOptionPane.showMessageDialog(null, "Mesa reservada correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	public void ocupar(Mesa m) {
		m.setEstado(new Ocupada());
		System.out.println("Mesa ocupada correctamente!");
		JOptionPane.showMessageDialog(null, "Mesa ocupada correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}

}
