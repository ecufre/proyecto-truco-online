package negocio;

import java.util.ArrayList;

import dto.PartidaDTO;
import enumeraciones.EstadoPartida;
import enumeraciones.TipoCanto;
import excepciones.ComunicacionException;

public class Partida {
	private int id;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Jugador> jugadoresListos;
	private boolean esAbierta;
	private EstadoPartida estado;
	private Integer ganador;
	private ArrayList<Juego> juegos;
	private String charla;
	
	
	// Creacion y preparacion de partida
	public Partida(boolean esAbierta) {
		this.id = 1; // terminar
		this.jugadores = new ArrayList<Jugador>();
		this.jugadoresListos = new ArrayList<Jugador>();
		this.esAbierta = esAbierta;
		this.estado = EstadoPartida.Pendiente;
		this.ganador = null;
		this.juegos = new ArrayList<Juego>();
		Juego juegoActual = new Juego();
		this.juegos.add(juegoActual);
		juegoActual.crearMano();
		juegoActual.grabar();
		this.charla = null;
	}
	
	public void grabar() {
		//TODO Grabar
	}

	public void jugadorListo(Jugador j) {
		for (int i = 0; i < this.jugadores.size(); i++) {
			if (this.jugadores.get(i).getApodo().equals(j.getApodo())) return;
			this.jugadoresListos.add(j);
		}
		if (this.jugadoresListos.size() == 4) this.estado = EstadoPartida.EnCurso;
	}
	
	//Metodos privados ya revisados
	
	private int ubicacionJugador(Jugador j) throws ComunicacionException {
		for (int i = 0; i < this.jugadores.size(); i++) 
			if (this.jugadores.get(i).getApodo().equals(j.getApodo())) return (i + 1);
		throw new ComunicacionException("Jugador no pertenece a la partida");
	}
	
	private int calcularGanadorMano(int ubicacion) {
		 if (ubicacion == 4) return 3;
		 else return (ubicacion + 1);
	}
	
	private Juego getJuegoActual() {
		return this.juegos.get(this.juegos.size() - 1);
	}
	
	//----------------Metodos del Juego-----------------------------------------
	
	public void jugarCarta(Jugador j, int idCarta) throws ComunicacionException {
		this.getJuegoActual().jugarCarta(this.ubicacionJugador(j), idCarta);
		this.actualizarEsatdoPartida();
	}
	
	public void retiraseMano(Jugador j) throws ComunicacionException{
		int ganadorMano = this.calcularGanadorMano(this.ubicacionJugador(j));
		this.getJuegoActual().retirarseMano(ganadorMano);
		this.actualizarEsatdoPartida();
	}
	
	public void actualizarEsatdoPartida(){
		this.getJuegoActual().actualizarJuego(); //TODO Revisar cuando este el conteo de envites
		this.getJuegoActual().grabar();
		
		//Analizo que hacer si se termino el juego
		if (this.getJuegoActual().isFinalizado()) {
			//Tres juegos completos
			if (this.juegos.size() == 3) {
				this.estado = EstadoPartida.Finalizada;
				if (this.juegos.get(2).getPuntajeImpar() > this.juegos.get(2).getPuntajePar()) this.ganador = 1;
				else this.ganador = 2;
			}
			//Dos juegos completos
			else if (this.juegos.size() == 2) {
				//Ambos ganados por el mismo equipo, finalizo la partida
				if (this.juegos.get(0).getPuntajeImpar() > this.juegos.get(0).getPuntajePar() == this.juegos.get(1).getPuntajeImpar() > this.juegos.get(1).getPuntajePar()) {
					this.estado = EstadoPartida.Finalizada;
					if (this.juegos.get(0).getPuntajeImpar() > this.juegos.get(0).getPuntajePar()) this.ganador = 1;
					else this.ganador = 2;
				}
				//Ganados por equipos distintos, creo un nuevo juego.
				else {
					Juego juegoActual = new Juego();
					this.juegos.add(juegoActual);
					juegoActual.grabar();
				}	
			}
			//Un juego completo creo un juevo nuevo
			else {
				Juego juegoActual = new Juego();
				this.juegos.add(juegoActual);
				juegoActual.grabar();
			}
		}
		//Si se termino la mano, creo una nueva mano.
		else if (this.getJuegoActual().manoCompleta()) this.getJuegoActual().crearMano();
	}
	
	public void cantarEnvite(Jugador jugador, TipoCanto canto) throws ComunicacionException{
		this.getJuegoActual().cantarEnvite(this.ubicacionJugador(jugador), canto);
	}
	
	public void responderEnvite(Jugador jugador, Boolean respuesta) throws ComunicacionException {
		this.getJuegoActual().responderEnvite(this.ubicacionJugador(jugador), respuesta);
	}

	public EstadoPartida getEstado() {
		return estado;
	}

	public int getId() {
		return id;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public PartidaDTO toDTO_reducido() {
		return null;//TODO Grabar
	}

	public Integer getGanador() {
		return ganador;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
}
