package controladores;

import java.util.ArrayList;
import java.util.Vector;

import dao.JugadorDAO;
import dto.InvitacionDTO;
import dto.JugadorDTO;
import negocio.Invitacion;
import negocio.Jugador;

public class AdministradorJugador {
	private static AdministradorJugador instancia;
	private ArrayList<Jugador> jugadores;
	
	private AdministradorJugador() {}
	
	public static AdministradorJugador getInstancia() {
		if (instancia == null) {
			instancia = new AdministradorJugador();
		}
		return instancia;
	}
	
	public void crearJugador(JugadorDTO jugador) {
		if (this.buscarJugador(jugador.getApodo()) == null) {
			Jugador j = new Jugador(jugador.getApodo(), jugador.getEmail(), jugador.getPassword(), jugador.getLoggedSession());
			j.grabar();
		}
		//TODO Error?
	}
	
	public boolean login(JugadorDTO jugador) {
		Jugador j = this.buscarJugador(jugador.getApodo());
		if (j != null) {
			if (j.passwordCorrecta(jugador.getPassword())) {
				j.setLoggedSession(jugador.getLoggedSession());
				j.grabar();
				return true;
			}
		}
		return false;
	}
	
	public void logout(JugadorDTO jugador) {
		if (this.isLoggedIn(jugador)) {
			Jugador j = this.buscarJugador(jugador.getApodo());
			jugadores.remove(j);
			j.setLoggedSession(null);
			j.grabar();
		}
	}
	
	public boolean isLoggedIn (JugadorDTO jugador) {
		Jugador j = this.buscarJugador(jugador.getApodo());
		if (j != null) {
			if (j.getLoggedSession().equals(jugador.getLoggedSession())) return true;
		}
		return false;
	}
	
	public Jugador buscarJugador(String apodo) {
		for (Jugador j : jugadores) {
			if (j.getApodo().equals(apodo)) {
				j.actualizar();
				return j;
			}
		}
		return JugadorDAO.getInstancia().toNegocio(JugadorDAO.getInstancia().getJugadorByApodo(apodo));
	}
	
	public JugadorDTO buscarJugadorDTO(String apodo) {
		return this.buscarJugador(apodo).toDTO();
	}
	
	public void jugarLibreIndividual(JugadorDTO jugador) {
		if (this.isLoggedIn(jugador)) {
			CreadorPartida.getInstancia().agregarJugadorIndividual(this.buscarJugador(jugador.getApodo()));
		}
	}
	
	public void jugarLibrePareja(JugadorDTO remitente, String apodoInvitado) {
		if (this.isLoggedIn(remitente)) {
			Jugador j = this.buscarJugador(apodoInvitado);
			if (j != null) {
				j.crearInvitacion(this.buscarJugador(remitente.getApodo()));
			}
		}
		//TODO Error?
	}
	
	public ArrayList<InvitacionDTO> listarInvitacionesPendientes(JugadorDTO jugador) {
		ArrayList<InvitacionDTO> invitaciones = new ArrayList<InvitacionDTO>();
		if (this.isLoggedIn(jugador)) {
			for (Invitacion i : this.buscarJugador(jugador.getApodo()).getInvitacionesPendientes()) {
				invitaciones.add(i.toDTO());
			}
		}
		return invitaciones;
	}
	
	public void aceptarInvitacion (JugadorDTO jugador, InvitacionDTO invitacion) {
		if (this.isLoggedIn(jugador)) {
			Jugador j = this.buscarJugador(jugador.getApodo());
			j.aceptarInvitacion(invitacion.getId());
		}
	}
	
	public void rechazarInvitacion (JugadorDTO jugador, InvitacionDTO invitacion) {
		if (this.isLoggedIn(jugador)) {
			Jugador j = this.buscarJugador(jugador.getApodo());
			j.rechazarInvitacion(invitacion.getId());
		}
	}
	// Innecesario, dado que la informacion ya esta en el JugadorDTO
	//public void calcularRankingAbierto() {}
}
