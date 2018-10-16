package negocio;

import java.util.List;
import java.util.Vector;

public class Partida {
	private int id;
	private List<Jugador> jugadores;
	private List<Boolean> jugadoresListos;
	private boolean esAbierta;
	private int estado;
	private int ganador;
	private List<Juego> juegos;
	
	
	public void jugadorListo(Jugador j) {}
	
	public void jugarCarta(Jugador j, int valorCarta) {}
	
	public void cantarEnvite(Jugador j, TipoCanto tipoCanto) {}
	
	public void responderEnvite(Jugador j, boolean respuesta) {}
}
