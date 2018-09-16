package controladores;

import java.util.Vector;

import negocio.Jugador;

public class AdministradorJugador {
	private Vector<Jugador> jugadores;
	
	public void crearJugador(String apodo, String email, String password) {}
	
	public void login(String apodo, String password) {}
	
	public Jugador buscarJugador(String apodo) {}
	
	public void jugarLibreIndividual(String apodo) {}
	
	public void jugarLibrePareja(String apodo1, String apodo2) {}
	
	public void listarInvitacionesPendientes(String apodo) {}
	
	public void calcularRankingAbierto() {}
}
