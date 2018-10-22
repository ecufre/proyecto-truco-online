package negocio;

import java.util.ArrayList;

import dao.BazaDAO;
import excepciones.ComunicacionException;

public class Baza {
	private int id;
	private ArrayList<Carta> cartasbaza;
	private Integer ganadorBaza;
	private int turno;
	private int mano;
	private boolean parda;

	public Baza(int turno) {
		this.cartasbaza = new ArrayList<Carta>();
		this.ganadorBaza = null;
		this.turno = turno;
		this.mano = turno;
	}
	
	public Baza(int id, int turno, int mano, boolean parda, Integer ganadorBaza) {
		this.id = id;
		this.cartasbaza = new ArrayList<Carta>();
		this.ganadorBaza = ganadorBaza;
		this.turno = turno;
		this.mano = mano;
	}
	
	public void grabar() {
		BazaDAO.getInstancia().grabar(this);
	}
	
	public void crear() throws ComunicacionException {
		Integer id = BazaDAO.getInstancia().crear(this);
		if (id != null) this.id = id;
		else throw new ComunicacionException("Hubo un error al generar una nueva baza");
	}
		
	//Calcula la posicion relativa de un jugador respecto del mano de la baza
	private int posicionRelativa(int ubicacion) {
		if (ubicacion >= this.mano) return (this.mano - ubicacion);
		else return (4 + this.mano - ubicacion);
	}

	public void agregarCarta(Carta c){
		cartasbaza.add(c);
		System.out.println("Se jugo la carta:" + c.toString());
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
				else if(c.getValor() == MejorCarta.getValor()) {
					if(this.posicionRelativa(c.getJugador()) < this.posicionRelativa(MejorCarta.getJugador())) MejorCarta = c;
					this.parda = true;
				}
					
			}
		}		
		this.ganadorBaza = MejorCarta.getJugador();
	}
	
	public boolean isCompleta() {
		return (this.ganadorBaza != null);
	}

	public Integer getGanadorBaza() {
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

	public ArrayList<Carta> getCartasbaza() {
		return cartasbaza;
	}

	public int getMano() {
		return mano;
	}

	public int getId() {
		return id;
	}

	public void setCartasbaza(ArrayList<Carta> cartasbaza) {
		this.cartasbaza = cartasbaza;
	}
}
