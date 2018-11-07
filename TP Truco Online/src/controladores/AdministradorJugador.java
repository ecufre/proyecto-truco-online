package controladores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			System.out.println(dtf.format(now) + " - Se creo el jugador: " + jugador.getApodo());
		} else throw new ComunicacionException("El jugador ya existe");
	}
	
	public void login(JugadorDTO jugador) throws LoggedInException {
		Jugador j;
		try {
			j = this.buscarJugador(jugador.getApodo());
			if (j.passwordCorrecta(jugador.getPassword())) {
				j.setLoggedSession(jugador.getLoggedSession());
				j.grabar();
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
				System.out.println(dtf.format(now) + " - Autenticacion correcta de parte del jugador: " + jugador.getApodo());
				return;
			}
			throw new LoggedInException("No pudo autenticarse el usuario");
		} catch (ComunicacionException e) {
			throw new LoggedInException("No pudo autenticarse el usuario");
		}
	}
	
	public void logout(JugadorDTO jugador) throws LoggedInException, ComunicacionException {
		if (this.isLoggedIn(jugador)) {
			Jugador j = this.buscarJugador(jugador.getApodo());
			jugadores.remove(j);
			j.setLoggedSession(null);
			j.grabar();
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			System.out.println(dtf.format(now) + " - Cierre de sesion correcto de parte del jugador: " + jugador.getApodo());
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
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			System.out.println(dtf.format(now) + " - El siguiente jugador se agrego lista de espera individual: " + jugador.getApodo());
			CreadorPartida.getInstancia().agregarJugadorIndividual(this.buscarJugador(jugador.getApodo()));
		}
	}
	
	public void jugarLibrePareja(JugadorDTO remitente, String apodoInvitado) throws LoggedInException, ComunicacionException {
		if (this.isLoggedIn(remitente)) {
			Jugador j = this.buscarJugador(apodoInvitado);
			if (j != null) {
				j.crearInvitacion(this.buscarJugador(remitente.getApodo()));
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
				System.out.println(dtf.format(now) + " - Invitacion para jugar enviada a: " + apodoInvitado);
			}
		}
	}
	
	public ArrayList<InvitacionDTO> listarInvitacionesPendientes(JugadorDTO jugador) throws LoggedInException, ComunicacionException {
		ArrayList<InvitacionDTO> invitaciones = new ArrayList<InvitacionDTO>();
		if (this.isLoggedIn(jugador)) {
			for (Invitacion i : this.buscarJugador(jugador.getApodo()).getInvitacionesPendientes()) {
				invitaciones.add(i.toDTO());
			}
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			System.out.println(dtf.format(now) + " - Listando invitaciones pendientes del jugador: " + jugador.getApodo());
		}
		return invitaciones;
	}
	
	public void aceptarInvitacion (JugadorDTO jugador, InvitacionDTO invitacion) throws LoggedInException, ComunicacionException {
		if (this.isLoggedIn(jugador)) {
			Jugador j = this.buscarJugador(jugador.getApodo());
			j.aceptarInvitacion(invitacion.getId());
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			System.out.println(dtf.format(now) + " - Aceptando invitacion enviada por: " + invitacion.getRemitente().getApodo());
		}
	}
	
	public void rechazarInvitacion (JugadorDTO jugador, InvitacionDTO invitacion) throws LoggedInException, ComunicacionException {
		if (this.isLoggedIn(jugador)) {
			Jugador j = this.buscarJugador(jugador.getApodo());
			j.rechazarInvitacion(invitacion.getId());
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			System.out.println(dtf.format(now) + " - Rechazando invitacion enviada por: " + invitacion.getRemitente().getApodo());
		}
	}
	
	public ArrayList<JugadorDTO> listarRanking() throws ComunicacionException {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		System.out.println(dtf.format(now) + " - Listando ranking de jugadores");
		return Jugador.listarRanking();
	}
}
