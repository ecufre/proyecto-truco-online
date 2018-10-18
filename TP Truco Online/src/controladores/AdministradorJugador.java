package controladores;

import java.util.ArrayList;

import dao.JugadorDAO;
import dto.InvitacionDTO;
import dto.JugadorDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import negocio.Invitacion;
import negocio.Jugador;

public class AdministradorJugador {
	private static AdministradorJugador instancia;
	private ArrayList<Jugador> jugadores;
	
	private AdministradorJugador() {
		jugadores = new ArrayList<Jugador>();
	}
	
	public static AdministradorJugador getInstancia() {
		if (instancia == null) {
			instancia = new AdministradorJugador();
		}
		return instancia;
	}
	
	public void crearJugador(JugadorDTO jugador) throws ComunicacionException {
		if (! JugadorDAO.getInstancia().existeJugadorByApodo(jugador.getApodo())) {
			Jugador j = new Jugador(jugador.getApodo(), jugador.getEmail(), jugador.getPassword(), jugador.getLoggedSession());
			j.grabar();
			System.out.println("Se creo el jugador: " + jugador.getApodo());
		} else throw new ComunicacionException("El jugador ya existe");
	}
	
	public void login(JugadorDTO jugador) throws ComunicacionException {
		Jugador j = this.buscarJugador(jugador.getApodo());
		if (j != null) {
			if (j.passwordCorrecta(jugador.getPassword())) {
				j.setLoggedSession(jugador.getLoggedSession());
				j.grabar();
				System.out.println("Autenticacion correcta de parte del jugador: " + jugador.getApodo());
				return;
			}
		}
		System.out.println("Autenticacion incorrecta de parte del jugador: " + jugador.getApodo());
		throw new ComunicacionException ("No pudo autenticarse el usuario");
	}
	
	public void logout(JugadorDTO jugador) throws LoggedInException, ComunicacionException {
		if (this.isLoggedIn(jugador)) {
			Jugador j = this.buscarJugador(jugador.getApodo());
			jugadores.remove(j);
			j.setLoggedSession(null);
			j.grabar();
			System.out.println("Cierre de sesion correcto de parte del jugador: " + jugador.getApodo());
		}
	}
	
	public boolean isLoggedIn (JugadorDTO jugador) throws LoggedInException, ComunicacionException {
		Jugador j = this.buscarJugador(jugador.getApodo());
		if (j != null) {
			if (j.getLoggedSession().equals(jugador.getLoggedSession())) return true;
		}
		throw new LoggedInException("El usuario no esta conectado.");
	}
	
	public Jugador buscarJugador(String apodo) throws ComunicacionException {
		for (Jugador j : jugadores) {
			if (j.getApodo().equals(apodo)) {
				j.actualizar();
				return j;
			}
		}
		Jugador j = JugadorDAO.getInstancia().toNegocio(JugadorDAO.getInstancia().getJugadorByApodo(apodo));
		this.jugadores.add(j);
		return j;
	}
	
	public JugadorDTO buscarJugadorDTO(String apodo) throws ComunicacionException {
		return this.buscarJugador(apodo).toDTO();
	}
	
	public void jugarLibreIndividual(JugadorDTO jugador) throws LoggedInException, ComunicacionException {
		if (this.isLoggedIn(jugador)) {
			CreadorPartida.getInstancia().agregarJugadorIndividual(this.buscarJugador(jugador.getApodo()));
			System.out.println("El siguiente jugador fue agregado a lista de espera individual: " + jugador.getApodo());
		}
	}
	
	public void jugarLibrePareja(JugadorDTO remitente, String apodoInvitado) throws LoggedInException, ComunicacionException {
		if (this.isLoggedIn(remitente)) {
			Jugador j = this.buscarJugador(apodoInvitado);
			if (j != null) {
				j.crearInvitacion(this.buscarJugador(remitente.getApodo()));
				System.out.println("Invitacion para jugar enviada a: " + apodoInvitado);
			}
		}
	}
	
	public ArrayList<InvitacionDTO> listarInvitacionesPendientes(JugadorDTO jugador) throws LoggedInException, ComunicacionException {
		ArrayList<InvitacionDTO> invitaciones = new ArrayList<InvitacionDTO>();
		if (this.isLoggedIn(jugador)) {
			for (Invitacion i : this.buscarJugador(jugador.getApodo()).getInvitacionesPendientes()) {
				invitaciones.add(i.toDTO());
			}
			System.out.println("Listando invitaciones pendientes del jugador: " + jugador.getApodo());
		}
		return invitaciones;
	}
	
	public void aceptarInvitacion (JugadorDTO jugador, InvitacionDTO invitacion) throws LoggedInException, ComunicacionException {
		if (this.isLoggedIn(jugador)) {
			Jugador j = this.buscarJugador(jugador.getApodo());
			j.aceptarInvitacion(invitacion.getId());
			System.out.println("Aceptando invitacion enviada por: " + invitacion.getRemitente().getApodo());
		}
	}
	
	public void rechazarInvitacion (JugadorDTO jugador, InvitacionDTO invitacion) throws LoggedInException, ComunicacionException {
		if (this.isLoggedIn(jugador)) {
			Jugador j = this.buscarJugador(jugador.getApodo());
			j.rechazarInvitacion(invitacion.getId());
			System.out.println("Rechazando invitacion enviada por: " + invitacion.getRemitente().getApodo());
		}
	}
	
	public ArrayList<JugadorDTO> listarTopTen(Integer categoria) throws ComunicacionException {
		System.out.println("Listando Top 10 de jugadores de la categoria: " + String.valueOf(categoria));
		return Jugador.buscarTop10(categoria);
	}
}
