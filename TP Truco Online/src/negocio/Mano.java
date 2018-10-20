package negocio;

import java.util.ArrayList;

import enumeraciones.TipoCanto;
import excepciones.ComunicacionException;


public class Mano {
	private static int siguienteId = 1; //TODO esto se reemplaza por la persistencia
	private Integer id;
	private Integer numeroMano;
	private ArrayList<Canto> cantos;
	private ArrayList<Carta> cartas;
	private ArrayList<Baza> bazas;

	//Metodo a eliminar con persistencia
	private static int getSiguienteId() {
		return siguienteId++;
	}

	public Mano(Integer numeroMano) {
		this.id = Mano.getSiguienteId();
		this.numeroMano = numeroMano;
		this.cantos = new ArrayList<Canto>();
		this.cartas = new ArrayList<Carta>();
		this.bazas = new ArrayList<Baza>();
		bazas.add(new Baza(this.calcularJugManoBaza()));
	}

	public void grabar() {
		//TODO Grabar
	}

	public Baza getBazaActual() {
		return this.bazas.get(this.bazas.size() - 1);
	}

	private Canto getUltimoCanto() {
		if (cantos.size() > 0) return this.cantos.get(this.cantos.size() - 1);
		else return null;
	}

	public void jugarCarta(int ubicacionJugador, int carta) throws ComunicacionException {
		Carta c = this.buscarCarta(carta);
		if(this.esJugadaValida(ubicacionJugador, c)){
			this.getBazaActual().agregarCarta(c);
			//this.cartas.remove(c);
			c.setJugada(true);
			c.grabar();
			if(this.getBazaActual().isCompleta()){
				this.getBazaActual().determinarGanador();
				int mano = this.getBazaActual().getGanadorBaza();
				bazas.add(new Baza(mano));
			}
		}
		else throw new ComunicacionException("Jugada invalida");

	}

	public boolean esJugadaValida(int ubicacionJugador, Carta c) {
		if (ubicacionJugador == this.getBazaActual().getTurno()) return (ubicacionJugador == c.getJugador() && ! c.isJugada());
		else return false;
	}

	private Carta buscarCarta(int carta) throws ComunicacionException {
		for(Carta c : this.cartas) if(c.getId() == carta) return c;
		throw new ComunicacionException("La carta buscada no existe");
	}

	private int calcularJugManoBaza(){
		if (this.numeroMano % 4 == 0) return 4;
		else return this.numeroMano % 4;
	}
	
	//Calcula la posicion relativa de un jugador respecto del mano de la primer baza
	private int posicionRelativa(int ubicacion) {
		if (ubicacion >= this.calcularJugManoBaza()) return (this.calcularJugManoBaza() - ubicacion);
		else return (4 + this.calcularJugManoBaza() - ubicacion);
	}

	public void administrarRetiro(int ganador) {
		while(this.bazas.size()<3) this.bazas.add(new Baza(1));
		for(Baza b : this.bazas) b.setGanadorBaza(ganador);
	}

	public boolean manoCompleta() {
		//Tres bazas completas -> Mano completa
		if (this.bazas.size() == 3) return this.bazas.get(2).isCompleta();
		//Dos bazas completas
		if(this.bazas.size() == 2 && this.bazas.get(1).isCompleta()) {
			//Ambas terminaron empatadas -> Mano incompleta
			if (this.bazas.get(0).isParda() && this.bazas.get(1).isParda()) return false;
			//Solo una de las dos termino empatada -> Mano completa
			else if (this.bazas.get(0).isParda() || this.bazas.get(1).isParda()) return true;
			//El ganador de ambas bazas es el mismo equipo -> Mano completa
			else if (this.bazas.get(0).getGanadorBaza() % 2 == this.bazas.get(1).getGanadorBaza() % 2) return true;
			//Dos bazas con ganadores distintos -> Mano incompleta
			else return false;
		}
		//No se termino de jugar la 2da Baza -> Mano incompleta
		return false;
	}
	
	public int calcularGanadorMano() {
		int ganador;
		//Ultima baza es empate -> Ganador de la primer baza gano la mano
		if (this.bazas.get(this.bazas.size() - 1).isParda()) ganador = this.bazas.get(0).getGanadorBaza() % 2;
		//Sino el ganador de la ultima baza es el ganador de la mano
		else ganador = this.bazas.get(this.bazas.size() - 1).getGanadorBaza() % 2;
		
		if (ganador == 0) return 2;
		else return 1;
	}

	public boolean esCantoValido(int jugadorUbicacion, TipoCanto canto) {
		//No es el turno del jugador
		if (jugadorUbicacion != this.getBazaActual().getTurno()) return false;

		//Canta envido despues de la primer baza
		if (this.bazas.size() > 1 && canto.getId() <= 4) return false;

		//Si ya se realizo el canto
		for (Canto c : this.cantos) if (c.getTipoCanto().getId() == canto.getId()) return false;

		//Si es un retruco o valecuatro buscar que este el canto anterior
		if (canto.getId() > 5) {
			for (Canto c : this.cantos) if (c.getTipoCanto().getId() == canto.getPredecesor()) return true;
			return false;
		}

		//Si es un envido buscar que no este el posterior.
		if (canto.getId() < 4) for (Canto c : this.cantos) if (c.getTipoCanto().getId() < 5 && c.getTipoCanto().getId() > canto.getId()) return false;

		//Si es envido envido, buscar que este el envido
		if (canto.getId() == 2) for (Canto c : this.cantos) if (c.getTipoCanto().getId() == 1) return true;

		//Todas las demas opciones son validas
		return true;
	}

	public void cantarEnvite(int jugadorUbicacion, TipoCanto canto) throws ComunicacionException {
		if (this.esCantoValido(jugadorUbicacion, canto)) {
			if (this.getUltimoCanto() != null) {
				//Si canto el envido despues de que se canto el truco, el envido sobreescribe al truco.
				if (this.getUltimoCanto().getTipoCanto().getId() == 5 && canto.getId() < 5) this.cantos.remove(this.getUltimoCanto());
				//Si el canto anterior era del mismo tipo, acepto el canto anterior.
				else if ((this.getUltimoCanto().getTipoCanto().getId() > 4 && canto.getId() > 4) || (this.getUltimoCanto().getTipoCanto().getId() < 5 && canto.getId() < 5)) this.getUltimoCanto().setQuerido(true);	
			}
			Canto c = new Canto(jugadorUbicacion);
			c.setTipoCanto(canto);
			c.grabar();
			this.cantos.add(c);
		}
		else throw new ComunicacionException("El canto es invalido");
	}

	public boolean esRespuestaValida(int jugador) {
		return (jugador % 2  != this.getBazaActual().getTurno() % 2);
	}

	public void responderEnvite(int jugador, boolean respuesta) throws ComunicacionException {
		if (this.esRespuestaValida(jugador)) {
			this.getUltimoCanto().setQuerido(respuesta);
			this.getUltimoCanto().grabar();
		}
		else throw new ComunicacionException("La respuesta es invalida");
	}
	
	public int calcularPuntaje(int equipo, int puntosEquipo, int puntosRival) {
		int puntosTruco = 0;
		//Si equipo gano el truco se suman los puntos del truco
		if (equipo % 2 == this.calcularGanadorMano() % 2) {
			puntosTruco = 1;
			for (Canto c : cantos) {
				if (c.getTipoCanto().getId() > 4 && c.isQuerido()) puntosTruco++;
			}
		}
		
		int puntosEnvido = 0;
		//Calculo los puntos del envido
		int maximoCanto = 0;
		boolean maximoCantoQuerido = false;
		int jugadorMaximoCanto = 0;
		for (Canto c : cantos) {
			if (c.getTipoCanto().getId() < 5) {
				//Busco el maximo canto de envido, y guardo si fue querido o no.
				if (c.getTipoCanto().getId() > maximoCanto) {
					maximoCanto = c.getTipoCanto().getId();
					maximoCantoQuerido = c.isQuerido();
					jugadorMaximoCanto = c.getCantante();
				}
				//Si el canto fue querido, sumo los puntos.
				if (c.isQuerido()) {
					//Si no fue FaltaEnvido (Y no fue cantada), acumulo los puntos
					if (c.getTipoCanto().getId() < 4 && maximoCanto < 4) puntosEnvido = puntosEnvido + c.getTipoCanto().getValor();
					//Si fue falta envido reviso si estaban en malas y gana el partido, o calculo cuanto vale.
					else {
						//Los dos equipos en malas
						if (puntosEquipo < 15 && puntosRival < 15) puntosEnvido = 30 - puntosEquipo;
						//El equipo calculado va ganando la partida
						else if (puntosEquipo > puntosRival) puntosEnvido = 30 - puntosEquipo;
						//El equipo calculado va perdiendo la partida
						else puntosEnvido = 30 - puntosRival;
					}
				}
			}
		}
		//Si todos empatan en 0, gana el mano de la primer baza
		int jugadorGanador = this.calcularJugManoBaza();
		if (! maximoCantoQuerido && maximoCanto > 0) {
			if (maximoCanto == 1) puntosEnvido = 1;
			jugadorGanador = jugadorMaximoCanto;
		}
		else {
			//Verifico que equipo gano el envido
			int puntosGanador = 0;
			int comb1, comb2, comb3;
			for (int i = 0; i < 4; i++) {
				//Valor de Envido de cartas 1 y 2 del jugador
				comb1 = this.cartas.get(0 + 3 * i).getValorEnvite() + this.cartas.get(1 + 3 * i).getValorEnvite();
				if (this.cartas.get(0 + 3 * i).getPalo().equals(this.cartas.get(1 + 3 * i).getPalo())) comb1 = comb1 + 20;
				//Valor de Envido de cartas 1 y 3 del jugador
				comb2 = this.cartas.get(0 + 3 * i).getValorEnvite() + this.cartas.get(2 + 3 * i).getValorEnvite();
				if (this.cartas.get(0 + 3 * i).getPalo().equals(this.cartas.get(2 + 3 * i).getPalo())) comb2 = comb2 + 20;
				//Valor de Envido de cartas 2 y 3 del jugador
				comb3 = this.cartas.get(1 + 3 * i).getValorEnvite() + this.cartas.get(2 + 3 * i).getValorEnvite();
				if (this.cartas.get(1 + 3 * i).getPalo().equals(this.cartas.get(2 + 3 * i).getPalo())) comb3 = comb3 + 20;
				
				//Agrego el mayor valor al array
				if (comb1 >= comb2 && comb1 >= comb3 && comb1 > puntosGanador && this.posicionRelativa(i + 1) < this.posicionRelativa(jugadorGanador)) {
					puntosGanador = comb1;
					jugadorGanador = i + 1;
				}
				else if (comb2 >= comb3 && comb2 > puntosGanador && this.posicionRelativa(i + 1) < this.posicionRelativa(jugadorGanador)) {
					puntosGanador = comb2;
					jugadorGanador = i + 1;
				}
				else if (comb3 > puntosGanador && this.posicionRelativa(i + 1) < this.posicionRelativa(jugadorGanador)) {
					puntosGanador = comb3;
					jugadorGanador = i + 1;
				}
			}
		}
		//Si el ganador es del equipo, sumar los puntos
		if (jugadorGanador % 2 == equipo % 2) return puntosTruco + puntosEnvido;
		else return puntosTruco;
	}

	public void setCartas(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}
	
}
