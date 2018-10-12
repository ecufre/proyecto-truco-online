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
	
	public void crearJugador(String apodo, String email, String password) {
		if (this.buscarJugador(apodo) == null) {
			Jugador j = new Jugador(apodo, email, password);
			Integer id = j.crear();
			if (id != null) {
				jugadores.add(j);
				j.setId(id);
			}
			//TODO Error?
		}
		//TODO Error?
	}
	
	public boolean login(String apodo, String password) {
		Jugador j = this.buscarJugador(apodo);
		return j.passwordCorrecta(password);
	}
	
	private Jugador buscarJugador(String apodo) {
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
	
	public void jugarLibreIndividual(String apodo) {
		CreadorPartida.getInstancia().agregarJugadorIndividual(this.buscarJugador(apodo));
	}
	
	public void jugarLibrePareja(String apodo1, String apodo2) {
		this.buscarJugador(apodo2).crearInvitacion(this.buscarJugador(apodo1));
	}
	
	public ArrayList<InvitacionDTO> listarInvitacionesPendientes(String apodo) {
		ArrayList<InvitacionDTO> invitaciones = new ArrayList<InvitacionDTO>();
		for (Invitacion i : this.buscarJugador(apodo).getInvitacionesPendientes()) {
			invitaciones.add(i.toDTO());
		}
		return invitaciones;
	}
	
	// Innecesario, dado que la informacion ya esta en el JugadorDTO
	//public void calcularRankingAbierto() {}
}
