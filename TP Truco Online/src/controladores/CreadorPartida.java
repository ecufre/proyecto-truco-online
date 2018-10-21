package controladores;

import java.util.ArrayList;
import java.util.Vector;

import excepciones.ComunicacionException;
import negocio.Jugador;
import negocio.Pareja;

public class CreadorPartida {
	private ArrayList<Jugador> jugadoresDisponibles;
	private ArrayList<Pareja> parejasDisponibles;
	private static CreadorPartida instancia;
	
	private CreadorPartida() {
		this.jugadoresDisponibles = new ArrayList<Jugador>();
		this.parejasDisponibles = new ArrayList<Pareja>();
	}
	
	public static CreadorPartida getInstancia() {
		if (instancia == null) {
			instancia = new CreadorPartida();
		}
		return instancia;
	}
	
	public void agregarJugadorIndividual(Jugador j) throws ComunicacionException {
		this.jugadoresDisponibles.add(j);
		this.crearPartidasIndividuales();
	}
	
	public void agregarPareja(Jugador j1, Jugador j2) throws ComunicacionException {
		Pareja p = new Pareja(j1, j2);
		this.parejasDisponibles.add(p);
		this.crearPartidasPareja();
	}
	
	private void crearPartidasPareja() throws ComunicacionException {
		//JugadoresPareja
		ArrayList<Pareja> Cat1 = new ArrayList<Pareja>();
		ArrayList<Pareja> Cat2 = new ArrayList<Pareja>();
		ArrayList<Pareja> Cat3 = new ArrayList<Pareja>();
		ArrayList<Pareja> Cat4 = new ArrayList<Pareja>();
		for (Pareja p : this.parejasDisponibles) {
			switch (p.calcularCategoria()) {
			case 1:
				Cat1.add(p);
				break;
			case 2:
				Cat2.add(p);
				break;
			case 3:
				Cat3.add(p);
				break;
			case 4:
				Cat4.add(p);
				break;
			}
		}
		while (Cat1.size() > 1) {
			Pareja p1 = Cat1.get(0);
			Pareja p2 = Cat1.get(0);
			AdministradorPartida.getInstancia().crearPartida(p1.getJugador1(), p2.getJugador1(), p1.getJugador2(), p2.getJugador2());
			this.parejasDisponibles.remove(p1);
			this.parejasDisponibles.remove(p2);
			Cat1.remove(p1);
			Cat1.remove(p2);
		}
		while (Cat2.size() > 1) {
			Pareja p1 = Cat2.get(0);
			Pareja p2 = Cat2.get(0);
			AdministradorPartida.getInstancia().crearPartida(p1.getJugador1(), p2.getJugador1(), p1.getJugador2(), p2.getJugador2());
			this.parejasDisponibles.remove(p1);
			this.parejasDisponibles.remove(p2);
			Cat2.remove(p1);
			Cat2.remove(p2);
		}
		while (Cat3.size() > 1) {
			Pareja p1 = Cat3.get(0);
			Pareja p2 = Cat3.get(0);
			AdministradorPartida.getInstancia().crearPartida(p1.getJugador1(), p2.getJugador1(), p1.getJugador2(), p2.getJugador2());
			this.parejasDisponibles.remove(p1);
			this.parejasDisponibles.remove(p2);
			Cat3.remove(p1);
			Cat3.remove(p2);
		}
		while (Cat4.size() > 1) {
			Pareja p1 = Cat4.get(0);
			Pareja p2 = Cat4.get(0);
			AdministradorPartida.getInstancia().crearPartida(p1.getJugador1(), p2.getJugador1(), p1.getJugador2(), p2.getJugador2());
			this.parejasDisponibles.remove(p1);
			this.parejasDisponibles.remove(p2);
			Cat4.remove(p1);
			Cat4.remove(p2);
		}
	}

	private void crearPartidasIndividuales() throws ComunicacionException {
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
		}
		
		//Partidas con jugador mas bajo Categoria 1
		while (Cat1.size() >= 4) {
			Jugador Jug1 = Cat1.get(0);
			Jugador Jug2 = Cat1.get(1);
			Jugador Jug3 = Cat1.get(2);
			Jugador Jug4 = Cat1.get(3);
			AdministradorPartida.getInstancia().crearPartida(Jug1, Jug2, Jug3, Jug4);
			this.jugadoresDisponibles.remove(Jug1);
			this.jugadoresDisponibles.remove(Jug2);
			this.jugadoresDisponibles.remove(Jug3);
			this.jugadoresDisponibles.remove(Jug4);
			Cat1.remove(Jug1);
			Cat1.remove(Jug2);
			Cat1.remove(Jug3);
			Cat1.remove(Jug4);
		}
		if (Cat1.size() == 3 && Cat2.size() >= 1) {
			Jugador Jug1 = Cat1.get(0);
			Jugador Jug2 = Cat1.get(1);
			Jugador Jug3 = Cat1.get(2);
			Jugador Jug4 = Cat2.get(0);
			AdministradorPartida.getInstancia().crearPartida(Jug1, Jug2, Jug3, Jug4);
			this.jugadoresDisponibles.remove(Jug1);
			this.jugadoresDisponibles.remove(Jug2);
			this.jugadoresDisponibles.remove(Jug3);
			this.jugadoresDisponibles.remove(Jug4);
			Cat1.remove(Jug1);
			Cat1.remove(Jug2);
			Cat1.remove(Jug3);
			Cat2.remove(Jug4);
		} else if (Cat1.size() == 2 && Cat2.size() >= 2) {
			Jugador Jug1 = Cat1.get(0);
			Jugador Jug2 = Cat1.get(1);
			Jugador Jug3 = Cat2.get(0);
			Jugador Jug4 = Cat2.get(1);
			AdministradorPartida.getInstancia().crearPartida(Jug1, Jug2, Jug3, Jug4);
			this.jugadoresDisponibles.remove(Jug1);
			this.jugadoresDisponibles.remove(Jug2);
			this.jugadoresDisponibles.remove(Jug3);
			this.jugadoresDisponibles.remove(Jug4);
			Cat1.remove(Jug1);
			Cat1.remove(Jug2);
			Cat2.remove(Jug3);
			Cat2.remove(Jug4);
		}
		
		//Partidas con jugador mas bajo Categoria 2
		while (Cat2.size() >= 4) {
			Jugador Jug1 = Cat2.get(0);
			Jugador Jug2 = Cat2.get(1);
			Jugador Jug3 = Cat2.get(2);
			Jugador Jug4 = Cat2.get(3);
			AdministradorPartida.getInstancia().crearPartida(Jug1, Jug2, Jug3, Jug4);
			this.jugadoresDisponibles.remove(Jug1);
			this.jugadoresDisponibles.remove(Jug2);
			this.jugadoresDisponibles.remove(Jug3);
			this.jugadoresDisponibles.remove(Jug4);
			Cat2.remove(Jug1);
			Cat2.remove(Jug2);
			Cat2.remove(Jug3);
			Cat2.remove(Jug4);
		}
		if (Cat2.size() == 3 && Cat3.size() >= 1) {
			Jugador Jug1 = Cat2.get(0);
			Jugador Jug2 = Cat2.get(1);
			Jugador Jug3 = Cat2.get(2);
			Jugador Jug4 = Cat3.get(0);
			AdministradorPartida.getInstancia().crearPartida(Jug1, Jug2, Jug3, Jug4);
			this.jugadoresDisponibles.remove(Jug1);
			this.jugadoresDisponibles.remove(Jug2);
			this.jugadoresDisponibles.remove(Jug3);
			this.jugadoresDisponibles.remove(Jug4);
			Cat2.remove(Jug1);
			Cat2.remove(Jug2);
			Cat2.remove(Jug3);
			Cat3.remove(Jug4);
		} else if (Cat2.size() == 2 && Cat3.size() >= 2) {
			Jugador Jug1 = Cat2.get(0);
			Jugador Jug2 = Cat2.get(1);
			Jugador Jug3 = Cat3.get(0);
			Jugador Jug4 = Cat3.get(1);
			AdministradorPartida.getInstancia().crearPartida(Jug1, Jug2, Jug3, Jug4);
			this.jugadoresDisponibles.remove(Jug1);
			this.jugadoresDisponibles.remove(Jug2);
			this.jugadoresDisponibles.remove(Jug3);
			this.jugadoresDisponibles.remove(Jug4);
			Cat2.remove(Jug1);
			Cat2.remove(Jug2);
			Cat3.remove(Jug3);
			Cat3.remove(Jug4);
		}
		//Partidas con jugador mas bajo Categoria 3
		while (Cat3.size() >= 4) {
			Jugador Jug1 = Cat3.get(0);
			Jugador Jug2 = Cat3.get(1);
			Jugador Jug3 = Cat3.get(2);
			Jugador Jug4 = Cat3.get(3);
			AdministradorPartida.getInstancia().crearPartida(Jug1, Jug2, Jug3, Jug4);
			this.jugadoresDisponibles.remove(Jug1);
			this.jugadoresDisponibles.remove(Jug2);
			this.jugadoresDisponibles.remove(Jug3);
			this.jugadoresDisponibles.remove(Jug4);
			Cat3.remove(Jug1);
			Cat3.remove(Jug2);
			Cat3.remove(Jug3);
			Cat3.remove(Jug4);
		}
		if (Cat1.size() == 3 && Cat2.size() >= 1) {
			Jugador Jug1 = Cat3.get(0);
			Jugador Jug2 = Cat3.get(1);
			Jugador Jug3 = Cat3.get(2);
			Jugador Jug4 = Cat4.get(0);
			AdministradorPartida.getInstancia().crearPartida(Jug1, Jug2, Jug3, Jug4);
			this.jugadoresDisponibles.remove(Jug1);
			this.jugadoresDisponibles.remove(Jug2);
			this.jugadoresDisponibles.remove(Jug3);
			this.jugadoresDisponibles.remove(Jug4);
			Cat3.remove(Jug1);
			Cat3.remove(Jug2);
			Cat3.remove(Jug3);
			Cat4.remove(Jug4);
		} else if (Cat3.size() == 2 && Cat4.size() >= 2) {
			Jugador Jug1 = Cat3.get(0);
			Jugador Jug2 = Cat3.get(1);
			Jugador Jug3 = Cat4.get(0);
			Jugador Jug4 = Cat4.get(1);
			AdministradorPartida.getInstancia().crearPartida(Jug1, Jug2, Jug3, Jug4);
			this.jugadoresDisponibles.remove(Jug1);
			this.jugadoresDisponibles.remove(Jug2);
			this.jugadoresDisponibles.remove(Jug3);
			this.jugadoresDisponibles.remove(Jug4);
			Cat3.remove(Jug1);
			Cat3.remove(Jug2);
			Cat4.remove(Jug3);
			Cat4.remove(Jug4);
		}
		//Partidas categoria 4
		while (Cat4.size() >= 4) {
			Jugador Jug1 = Cat4.get(0);
			Jugador Jug2 = Cat4.get(1);
			Jugador Jug3 = Cat4.get(2);
			Jugador Jug4 = Cat4.get(3);
			AdministradorPartida.getInstancia().crearPartida(Jug1, Jug2, Jug3, Jug4);
			this.jugadoresDisponibles.remove(Jug1);
			this.jugadoresDisponibles.remove(Jug2);
			this.jugadoresDisponibles.remove(Jug3);
			this.jugadoresDisponibles.remove(Jug4);
			Cat4.remove(Jug1);
			Cat4.remove(Jug2);
			Cat4.remove(Jug3);
			Cat4.remove(Jug4);
		}
	}
}
