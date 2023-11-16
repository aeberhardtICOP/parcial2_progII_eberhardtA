package model;

import javax.swing.JOptionPane;

import excepciones.EstadoInvalidoException;

public class Reservada implements Estado{

	@Override
	public void liberar(Mesa m) throws EstadoInvalidoException {
		System.out.println("Esta mesa esta reservada, no se puede liberar!");
		JOptionPane.showMessageDialog(null, "Esta mesa esta reservada, no se puede liberar", "Error", JOptionPane.ERROR_MESSAGE);
		throw new EstadoInvalidoException("Estado invalido");
	}

	@Override
	public void reservar(Mesa m) throws EstadoInvalidoException {
		System.out.println("Esta mesa ya esta reservada!");
		JOptionPane.showMessageDialog(null, "Esta mesa ya esta reservada!", "Error", JOptionPane.ERROR_MESSAGE);
		throw new EstadoInvalidoException("Estado invalido");
	}

	@Override
	public void ocupar(Mesa m) {
		System.out.println("Mesa ocupada correctamente!!");
		JOptionPane.showMessageDialog(null, "Mesa ocupada correctamente!!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		m.setEstado(new Ocupada());
	}

}
