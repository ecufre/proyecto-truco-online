package controladores;

import java.util.ArrayList;

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
	
	public void crearPartida(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {}
	
	public Partida crearPartidaCerrada(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {}
	
	public void jugadorListo(int idPartida, Jugador j) {}
	
	public int estadoNumericoPartida(int idPartida) {}
	
	public void jugarCarta(int idPartida, String apodo, int valorCarta) {}
	
	public void cantarEnvite(int idPartida, String apodo, TipoCanto tipoCanto) {}
	
	public void responderEnvite(int idPartida, String apodo, boolean respuesta) {}
}
