package negocio;

import java.util.Vector;

public class Mano {
		private int id;
		private Vector<Canto> cantos;
		private Vector<Carta> cartas;
		
		
		public void cantarEnvite(int jugador, TipoCanto tipoCanto) {}
		
		public boolean esCantoValido(int jugador, TipoCanto tipoCanto) {}
		
		public void responderEnvite(int jugador, boolean respuesta) {}
		
		public void esRespuestaValida(int jugador) {}
		
		public int calcularPuntos(String equipo) {}
		
		public int calcularBaza() {}
		
		public void jugarCarta(int jugador, int valorCarta) {}
		
		public void esJugadaValida(int jugador, int valorCarta) {}
		
		public int calcularPuntaje(int equipo) {}

}
