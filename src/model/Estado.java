package model;

import excepciones.EstadoInvalidoException;

public interface Estado {
	public void liberar(Mesa m) throws EstadoInvalidoException;
	public void reservar(Mesa m) throws EstadoInvalidoException;
	public void ocupar(Mesa m) throws EstadoInvalidoException;
	
	
}
