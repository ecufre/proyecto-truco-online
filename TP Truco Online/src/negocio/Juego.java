package negocio;

import java.util.Vector;

public class Juego {
	private int id;
	private int puntajePar;
	private int puntajeImpar;
	private boolean finalizado;
	private Vector<Mano> manos;
	
	
	public void getGanador() {}
	
	public void crearMano() {}
	
	public void jugarCarta(int jugador, int valorCarta) {}
	
	public void cartaEnvite(int jugador,TipoCanto tipoCanto) {}
	
	public void responderEnvite(int jugador, boolean respuesta) {}
	
}
