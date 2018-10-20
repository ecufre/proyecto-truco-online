package controladores;

import java.util.ArrayList;
import dto.AccionDTO;
import dto.HistoriaPartidaDTO;
import dto.PartidaDTO;
import enumeraciones.EstadoPartida;
import enumeraciones.TipoCanto;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import negocio.Grupo;
import negocio.Jugador;
import negocio.Partida;

public class AdministradorPartida {
	private ArrayList<Partida> partidas;
	private static AdministradorPartida instancia;
	
	private AdministradorPartida() {}
	
  	public static AdministradorPartida getInstancia() {
		if (instancia == null) {
			instancia = new AdministradorPartida();
		}
		return instancia;
	}

	public void crearPartida(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {
			ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
			jugadores.add(j1);
			jugadores.add(j2);
			jugadores.add(j3);
			jugadores.add(j4);
			Partida p = new Partida(true);
			p.setJugadores(jugadores);
			this.partidas.add(p); //TODO Sacar cuando se persista
	}
	
	public Partida crearPartidaCerrada(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(j1);
		jugadores.add(j2);
		jugadores.add(j3);
		jugadores.add(j4);
		Partida p = new Partida(false);
		p.setJugadores(jugadores);
		this.partidas.add(p); //TODO Sacar cuando se persista
		p.grabar();
		return p;
	}
	
	public void jugadorListo(AccionDTO ad) throws LoggedInException, ComunicacionException {
		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
			Jugador j = AdministradorJugador.getInstancia().buscarJugador(ad.getJugador().getApodo());
			Partida p = this.buscarPartida(ad.getPartida().getPartidaID());
			p.jugadorListo(j);
			p.grabar();
		}
	}
	
	public void JugarCarta(AccionDTO ad) throws LoggedInException, ComunicacionException{
		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
			Jugador j = AdministradorJugador.getInstancia().buscarJugador(ad.getJugador().getApodo());
			Partida p = this.buscarPartida(ad.getPartida().getPartidaID());
			if (p.getEstado() == EstadoPartida.EnCurso) {
				p.jugarCarta(j, ad.getCarta().getId());
				p.grabar(); 
			}
		}
	}
	
	public void Retirarse(AccionDTO ad) throws LoggedInException, ComunicacionException{
		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
			Jugador j = AdministradorJugador.getInstancia().buscarJugador(ad.getJugador().getApodo());
			Partida p = this.buscarPartida(ad.getPartida().getPartidaID());
			if (p.getEstado() == EstadoPartida.EnCurso) {
				p.retiraseMano(j);
				p.grabar();
			}
		}
	}
	
	public void cantarEnvite(AccionDTO ad) throws LoggedInException, ComunicacionException{
		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
			Jugador j = AdministradorJugador.getInstancia().buscarJugador(ad.getJugador().getApodo());
			Partida p = this.buscarPartida(ad.getPartida().getPartidaID());
			if (p.getEstado() == EstadoPartida.EnCurso) {
				p.cantarEnvite(j, ad.getEnvite().getTipoCanto()); //Agregar actualizarPartida
				p.grabar();
			}
		}
	}

	public void responderEnvite(AccionDTO ad) throws LoggedInException, ComunicacionException{
		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
			Jugador j = AdministradorJugador.getInstancia().buscarJugador(ad.getJugador().getApodo());
			Partida p = this.buscarPartida(ad.getPartida().getPartidaID());
			if (p.getEstado() == EstadoPartida.EnCurso) {
				p.responderEnvite(j, ad.getEnvite().isRespuesta()); //Agregar actualizarPartida
				p.grabar();
			}
		}
	}
	
	public PartidaDTO mostrarPartida(AccionDTO ad) throws ComunicacionException, LoggedInException{
		
		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
			Jugador j = AdministradorJugador.getInstancia().buscarJugador(ad.getJugador().getApodo());
			Partida p = this.buscarPartida(ad.getPartida().getPartidaID());
			if (p.getEstado() == EstadoPartida.EnCurso) {
			
        return 	p.toDTO(ad.getPartida().getPartidaID(), j , true);
        	}
			}
			
		
		
		return null;
		
	}

	private Partida buscarPartida(int partida) throws ComunicacionException {
		//TODO Pedirsela al PartidaDAO
		for (Partida p:this.partidas) if(p.getId()==partida) return p;
		throw new ComunicacionException("Partida no encontrada");
	}
}
