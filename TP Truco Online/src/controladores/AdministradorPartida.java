package controladores;

import java.util.Vector;

import negocio.Jugador;
import negocio.Partida;

public class AdministradorPartida {
	private Vector<Partida> partidas;
	
	
	public void crearPartida(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {}
	
	public Partida crearPartidaCerrada(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {}
	
	public void jugadorListo(int idPartida, Jugador j) {}
	
	public int estadoNumericoPartida(int idPartida) {}
	
	public void jugarCarta(int idPartida, String apodo, int valorCarta) {}
	
	public void cantarEnvite(int idPartida, String apodo, TipoCanto tipoCanto) {}
	
	public void responderEnvite(int idPartida, String apodo, boolean respuesta) {}
}
