package excepciones;

public class LoggedInException extends Exception {

	private static final long serialVersionUID = 7496062858060490609L;

	public LoggedInException(String mensaje){
		super(mensaje);
	}

}
