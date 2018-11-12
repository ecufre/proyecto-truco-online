package controladores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.PartidaDAO;
import dto.AccionDTO;
import dto.JugadorDTO;
import dto.PartidaDTO;
import dto.PartidaPantallaDTO;
import enumeraciones.EstadoPartida;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import negocio.Jugador;
import negocio.Partida;

public class AdministradorPartida {
	private static AdministradorPartida instancia;

	private AdministradorPartida() { }

	public static AdministradorPartida getInstancia() {
		if (instancia == null) {
			instancia = new AdministradorPartida();
		}
		return instancia;
	}

	public void crearPartida(Jugador j1, Jugador j2, Jugador j3, Jugador j4) throws ComunicacionException {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		System.out.println(dtf.format(now) + " - Creando una nueva partida con los jugadores: " + j1.getApodo() + " y " + j3.getApodo() + " vs " + j2.getApodo() + " y " + j4.getApodo());
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
		if (j1.getApodo().equals(j2.getApodo()) || j1.getApodo().equals(j3.getApodo()) || j1.getApodo().equals(j4.getApodo()) || j2.getApodo().equals(j3.getApodo()) || j2.getApodo().equals(j4.getApodo()) || j3.getApodo().equals(j4.getApodo())) throw new ComunicacionException("La partida debe tener 4 jugadores distintos");
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		System.out.println(dtf.format(now) + " - Creando una nueva partida con los jugadores: " + j1.getApodo() + " y " + j3.getApodo() + " vs " + j2.getApodo() + " y " + j4.getApodo());
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
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			System.out.println(dtf.format(now) + " - El siguiente jugador esta listo para jugar: " + ad.getJugador().getApodo());
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
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
				System.out.println(dtf.format(now) + " - El jugador " + ad.getJugador().getApodo() + " jugo la carta " + ad.getCarta().getNumero() + " de " + ad.getCarta().getPalo() + " (Partida numero: " + ad.getPartida().getId() + ")");				
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
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
				System.out.println(dtf.format(now) + " - El jugador " + ad.getJugador().getApodo() + " se retiro de la mano (Partida numero: " + ad.getPartida().getId() + ")");				
				p.grabar();
			}
		}
	}

	public void cantarEnvite(AccionDTO ad) throws LoggedInException, ComunicacionException{
		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
			Jugador j = AdministradorJugador.getInstancia().buscarJugador(ad.getJugador().getApodo());
			Partida p = this.buscarPartida(ad.getPartida().getId());
			if (p.getEstado() == EstadoPartida.EnCurso) {
				p.cantarEnvite(j, ad.getEnvite().getTipoCanto());
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
				System.out.println(dtf.format(now) + " - El jugador " + ad.getJugador().getApodo() + " cantó " + ad.getEnvite().getTipoCanto().toString() + " (Partida numero: " + ad.getPartida().getId() + ")");				
				p.grabar();
			}
		}
	}

	public void responderEnvite(AccionDTO ad) throws LoggedInException, ComunicacionException{
		if (AdministradorJugador.getInstancia().isLoggedIn(ad.getJugador())) {
			Jugador j = AdministradorJugador.getInstancia().buscarJugador(ad.getJugador().getApodo());
			Partida p = this.buscarPartida(ad.getPartida().getId());
			if (p.getEstado() == EstadoPartida.EnCurso) {
				p.responderEnvite(j,ad.getEnvite().getTipoCanto(), ad.getEnvite().isRespuesta());
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
				String rta;
				if (ad.getEnvite().isRespuesta()) rta = "quiero";
				else rta = "no quiero";
				System.out.println(dtf.format(now) + " - El jugador " + ad.getJugador().getApodo() + " respondio al " + ad.getEnvite().getTipoCanto().toString() + " con un " + rta + " (Partida numero: " + ad.getPartida().getId() + ")");				
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
	
	public ArrayList<PartidaDTO> buscarPartidasJugador(String apodo) throws ComunicacionException {
		ArrayList<PartidaDTO> psDTO = new ArrayList<PartidaDTO>();
		for (Partida p : PartidaDAO.getInstancia().getPartidasByApodo(apodo)) psDTO.add(p.toDTOReducido());
		return psDTO;
	}
	
	public ArrayList<PartidaDTO> listarMisPartidas(JugadorDTO jugador) throws ComunicacionException, LoggedInException {
		if (AdministradorJugador.getInstancia().isLoggedIn(jugador)) {
			ArrayList<PartidaDTO> psDTO = new ArrayList<PartidaDTO>();
			for (Partida p : PartidaDAO.getInstancia().getListadoPartidasByApodo(jugador.getApodo())) psDTO.add(p.toDTOReducido());
			return psDTO;
		}
		return null;
	}
}
