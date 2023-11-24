package excepciones;

public class MesaOcupadaException extends Exception{

	private static final long serialVersionUID = 1L;
	

	public MesaOcupadaException() {
	    super();
	  }

	  public MesaOcupadaException(String message) {
	    super(message);
	  }

}
