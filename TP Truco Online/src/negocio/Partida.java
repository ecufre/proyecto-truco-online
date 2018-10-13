package negocio;

import java.util.ArrayList;
import java.util.Vector;

import dto.PartidaDTO;

public class Partida {
	private int id;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Boolean> jugadoresListos;
	private boolean esAbierta;
	private int estado;
	private int ganador;
	private ArrayList<Juego> juegos;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Boolean> getJugadoresListos() {
		return jugadoresListos;
	}

	public void setJugadoresListos(ArrayList<Boolean> jugadoresListos) {
		this.jugadoresListos = jugadoresListos;
	}

	public boolean isEsAbierta() {
		return esAbierta;
	}

	public void setEsAbierta(boolean esAbierta) {
		this.esAbierta = esAbierta;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getGanador() {
		return ganador;
	}

	public void setGanador(int ganador) {
		this.ganador = ganador;
	}

	public ArrayList<Juego> getJuegos() {
		return juegos;
	}

	public void setJuegos(ArrayList<Juego> juegos) {
		this.juegos = juegos;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void jugadorListo(Jugador j) {}
	
	public void jugarCarta(Jugador j, int valorCarta) {}
	
	public void cantarEnvite(Jugador j, TipoCanto tipoCanto) {}
	
	public void responderEnvite(Jugador j, boolean respuesta) {}
	
	public PartidaDTO toDTO() {
		//TODO Devuelve todo el DTO completo
		return null;
	}
	public PartidaDTO toDTO_reducido() {
		//TODO Arma un dto reducido, solo con las primitivas y quizas los participantes.
		return null;
	}

}
