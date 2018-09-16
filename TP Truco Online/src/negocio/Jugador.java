package negocio;

import java.util.Vector;

public class Jugador {
	private String apodo;
	private String email;
	private String password;
	private int id;
	private Vector<Invitacion> invitacionesPendientes;
	
	
	public boolean passwordCorrecta(String password) {}
	
	public void actualizarPuntaje(int puntosASumar) {}
	
	public void crearInvitacion(Jugador remitente) {}
	
	public void listarInvitacionesPendientes() {}
	
	public void aceptarInvitacion(int idInvitacion) {}

}
