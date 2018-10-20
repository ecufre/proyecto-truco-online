package negocio;

import java.util.ArrayList;

public class Baza {
	private static int siguienteId = 1; //TODO esto se reemplaza por la persistencia
	private int id;
	private ArrayList<Carta> cartasbaza;
	private int ganadorBaza;
	private int turno;
	private int mano;
	private boolean parda;
	
	//Metodo a eliminar con persistencia
	private static int getSiguienteId() {
		return siguienteId++;
	}

	public Baza(int turno) {
		this.id = Baza.getSiguienteId();
		this.cartasbaza = new ArrayList<Carta>();
		this.turno = turno;
		this.mano = turno;
	}
	
	public void grabar() {
		//TODO Grabar
	}
		
	//Calcula la posicion relativa de un jugador respecto del mano de la baza
	private int posicionRelativa(int ubicacion) {
		if (ubicacion >= this.mano) return (this.mano - ubicacion);
		else return (4 + this.mano - ubicacion);
	}

	public void agregarCarta(Carta c){
		cartasbaza.add(c);
		if(c.getJugador() == 4) turno=1;
		else turno = c.getJugador() + 1;
	}
	
	public void determinarGanador() {
		Carta MejorCarta = null;
		
		for (Carta c : this.cartasbaza) {
			//La primer carta siempre va ganando
			if (MejorCarta == null) {
				MejorCarta = c;
				this.parda = false;
			}
			
			else{
				//Si la carta es mejor
				if(c.getValor() > MejorCarta.getValor()) {
					MejorCarta = c;
					this.parda = false;
				}
				
				//Si las cartas son iguales gana el mano
				if(c.getValor() == MejorCarta.getValor()) {
					if(this.posicionRelativa(c.getJugador()) < this.posicionRelativa(MejorCarta.getJugador())) MejorCarta = c;
					this.parda = true;
				}
					
			}
		}		
		this.ganadorBaza = MejorCarta.getJugador();
	}
	
	public boolean isCompleta() {
		return (4 == this.cartasbaza.size());
	}

	public int getGanadorBaza() {
		return ganadorBaza;
	}

	public int getTurno() {
		return turno;
	}

	public void setGanadorBaza(int ganadorBaza) {
		this.ganadorBaza = ganadorBaza;
	}

	public boolean isParda() {
		return parda;
	}
}
