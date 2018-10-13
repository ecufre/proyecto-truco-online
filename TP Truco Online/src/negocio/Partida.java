package negocio;

import java.util.ArrayList;
import java.util.Vector;

public class Partida {
	private int id;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Boolean> jugadoresListos;
	private boolean esAbierta;
	private int estado;
	private int ganador;
	private ArrayList<Juego> juegos;
	
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void jugadorListo(Jugador j) {}
	
	public void jugarCarta(Jugador j, int valorCarta) {}
	
	public void cantarEnvite(Jugador j, TipoCanto tipoCanto) {}
	
	public void responderEnvite(Jugador j, boolean respuesta) {}
}
