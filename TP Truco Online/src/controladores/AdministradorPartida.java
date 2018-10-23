package controladores;

import java.util.ArrayList;

import dao.PartidaDAO;
import dto.AccionDTO;
import dto.PartidaDTO;
import dto.PartidaPantallaDTO;
import enumeraciones.EstadoPartida;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import negocio.Jugador;
import negocio.Partida;

public class AdministradorPartida {
	//private ArrayList<Partida> partidas;
	private static AdministradorPartida instancia;

	private AdministradorPartida() {
		//this.partidas = new ArrayList<Partida>();
	}

	public static AdministradorPartida getInstancia() {
		if (instancia == null) {
			instancia = new AdministradorPartida();
		}
		return instancia;
	}

	public void crearPartida(Jugador j1, Jugador j2, Jugador j3, Jugador j4) throws ComunicacionException {
		System.out.println("Creando una nueva partida con los jugadores: " + j1.getApodo() + " y " + j3.getApodo() + " vs " + j2.getApodo() + " y " + j4.getApodo());
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(j1);
		jugadores.add(j2);
		jugadores.add(j3);
		jugadores.add(j4);
		Partida p = new Partida(true);
		p.setJugadores(jugadores);
		p.crear();
		//this.partidas.add(p); //Solo para pruebas sin persistencia
	}

	public Partida crearPartidaCerrada(Jugador j1, Jugador j2, Jugador j3, Jugador j4) throws ComunicacionException {
		System.out.println("Creando una nueva partida con los jugadores: " + j1.getApodo() + " y " + j3.getApodo() + " vs " + j2.getApodo() + " y " + j4.getApodo());
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(j1);
		jugadores.add(j2);
		jugadores.add(j3);
		jugadores.add(j4);
		Partida p = new Partida(false);
		p.setJugadores(jugadores);
		p.crear();
		//this.partidas.add(p); //Solo para pruebas sin persistencia
		return p;
	}

	public void jugadorListo(AccionDTO ad) throws LoggedInException, ComunicacionException {
		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
			Jugador j = AdministradorJugador.getInstancia().buscarJugador(ad.getJugador().getApodo());
			Partida p = this.buscarPartida(ad.getPartida().getId());
			System.out.println("El siguiente jugador esta listo para jugar: " + ad.getJugador().getApodo());
			p.jugadorListo(j);
			p.grabar();
		}
	}

	public void JugarCarta(AccionDTO ad) throws LoggedInException, ComunicacionException{
		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
			Jugador j = AdministradorJugador.getInstancia().buscarJugador(ad.getJugador().getApodo());
			Partida p = this.buscarPartida(ad.getPartida().getId());
			if (p.getEstado() == EstadoPartida.EnCurso) {
				p.jugarCarta(j, ad.getCarta().getId());
				p.grabar(); 
			}
		}
	}

	public void Retirarse(AccionDTO ad) throws LoggedInException, ComunicacionException{
		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
			Jugador j = AdministradorJugador.getInstancia().buscarJugador(ad.getJugador().getApodo());
			Partida p = this.buscarPartida(ad.getPartida().getId());
			if (p.getEstado() == EstadoPartida.EnCurso) {
				p.retiraseMano(j);
				p.grabar();
			}
		}
	}

	public void cantarEnvite(AccionDTO ad) throws LoggedInException, ComunicacionException{
		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
			Jugador j = AdministradorJugador.getInstancia().buscarJugador(ad.getJugador().getApodo());
			Partida p = this.buscarPartida(ad.getPartida().getId());
			if (p.getEstado() == EstadoPartida.EnCurso) {
				p.cantarEnvite(j, ad.getEnvite().getTipoCanto()); //Agregar actualizarPartida
				p.grabar();
			}
		}
	}

	public void responderEnvite(AccionDTO ad) throws LoggedInException, ComunicacionException{
		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
			Jugador j = AdministradorJugador.getInstancia().buscarJugador(ad.getJugador().getApodo());
			Partida p = this.buscarPartida(ad.getPartida().getId());
			if (p.getEstado() == EstadoPartida.EnCurso) {
				p.responderEnvite(j,ad.getEnvite().getTipoCanto(), ad.getEnvite().isRespuesta()); //Agregar actualizarPartida
				p.grabar();
			}
		}
	}

	public PartidaPantallaDTO mostrarPartida(AccionDTO ad) throws ComunicacionException, LoggedInException{

		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
			Jugador j = AdministradorJugador.getInstancia().buscarJugador(ad.getJugador().getApodo());
			Partida p = this.buscarPartida(ad.getPartida().getId());
			if (p.getEstado() == EstadoPartida.EnCurso) {
				return 	p.toPantallaDTO(ad.getPartida().getId(),j,true);
			}
		}
		return null;
	}
	
	public PartidaDTO mostarPartidaCompleta(AccionDTO ad) throws ComunicacionException, LoggedInException{
		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
				Partida p = this.buscarPartida(ad.getPartida().getId());
			if (p.getEstado() == EstadoPartida.EnCurso) {
				return 	p.toDTO();
			}
		}
		return null;
	}

	
	private Partida buscarPartida(int partida) throws ComunicacionException {
		return PartidaDAO.getInstancia().getPartidaById(partida);
		/* Para cuando no hay persistencia
		for (Partida p:this.partidas) if(p.getId()==partida) return p;
		throw new ComunicacionException("Partida no encontrada");
		*/
	}
}
