package negocio;

import java.util.Vector;

public class Grupo {
	private String nombre;
	private int id;
	private Jugador administrador;
	private Vector<Jugador> miembros;
	private Vector<Pareja> parejas;
	private Vector<Partida> partidas;
	
	public void agregarJugador(Jugador j) {
		
	}
	
	public void eliminarJugador(Jugador j) {}
	
	public void listarJugadores() {}
	
	public void crearPareja(Jugador j1, Jugador j2) {}
	
	public void listarParejas() {}
	
	public void crearPartida(Pareja p1, Pareja p2) {}
	
	public void listarPartidas() {}
	
	public Vector<JugadorView> calcularRankingCerrado() {}
	
	
}
