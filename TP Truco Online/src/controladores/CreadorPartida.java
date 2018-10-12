package controladores;

import java.util.ArrayList;
import java.util.Vector;

import negocio.Jugador;
import negocio.Pareja;

public class CreadorPartida {
	private Vector<Jugador> jugadoresDisponibles;
	private Vector<Pareja> parejasDisponibles;
	private static CreadorPartida instancia;
	
	private CreadorPartida() {}
	
	public static CreadorPartida getInstancia() {
		if (instancia == null) {
			instancia = new CreadorPartida();
		}
		return instancia;
	}
	
	public void agregarJugadorIndividual(Jugador j) {
		this.jugadoresDisponibles.add(j);
		this.crearPartidasPosibles();
	}
	
	public void agregarPareja(Jugador j1, Jugador j2) {
		Pareja p = new Pareja(j1, j2);
		this.parejasDisponibles.add(p);
		this.crearPartidasPosibles();
	}
	
	private void crearPartidasPosibles() {
		//JugadoresIndividuales
		ArrayList<Jugador> Cat1 = new ArrayList<Jugador>();
		ArrayList<Jugador> Cat2 = new ArrayList<Jugador>();
		ArrayList<Jugador> Cat3 = new ArrayList<Jugador>();
		ArrayList<Jugador> Cat4 = new ArrayList<Jugador>();
		for (Jugador j : this.jugadoresDisponibles) {
			switch (j.getCategoria().calcularCategoria()) {
			case 1:
				Cat1.add(j);
				break;
			case 2:
				Cat2.add(j);
				break;
			case 3:
				Cat3.add(j);
				break;
			case 4:
				Cat4.add(j);
				break;
			}
			while (Cat1.size() >= 4) {
				
			}
		}
		//TODO Analiza los jugadores disponibles e intenta crear una partida segun el algoritmo. Idem con parejas.
	}
	
	private void removerDeEspera(Jugador j) {
		
		//TODO Remueve al jugador de la lista individual.
	}
	
	private void removerDeEspera(Pareja p) {
		//TODO Remueve la pareja de la lista de parejas.
	}
}
