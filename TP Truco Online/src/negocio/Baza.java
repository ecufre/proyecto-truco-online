package negocio;

import java.util.ArrayList;

public class Baza {
	private ArrayList<Carta> cartasbaza;
	private Jugador ganadorBaza;
	private Integer turno;
	
	
	public Baza(Integer turno) {
		super();
		this.cartasbaza = new ArrayList<Carta>();
		this.ganadorBaza = null;
		this.turno = turno;
	}


	public void agregarCarta(Carta c){
		cartasbaza.add(c);
		if(c.getJugador().getUbicacion()==4){
			turno=1;
		}else{
		turno = c.getJugador().getUbicacion()+1;
		}
	}
	
	public ArrayList<Carta> getCartasbaza() {
		return cartasbaza;
	}


	public void setCartasbaza(ArrayList<Carta> cartasbaza) {
		this.cartasbaza = cartasbaza;
	}


	public Jugador getGanadorBaza() {
		return ganadorBaza;
	}


	public void setGanadorBaza(Jugador ganadorBaza) {
		this.ganadorBaza = ganadorBaza;
	}


	public Integer getTurno() {
		return turno;
	}


	public void setTurno(Integer turno) {
		this.turno = turno;
	}


	public boolean completa() {
		
		return (4==this.cartasbaza.size());
	}


	public void determinarGanador() {
		Carta MejorCarta =null;
		for(Carta c: this.cartasbaza){
			if(MejorCarta==null){
				MejorCarta = c;
			}else{
				
				if(c.getValor()>MejorCarta.getValor()){
					MejorCarta = c;	
					
				}
				if(c.getValor()==MejorCarta.getValor()){
					if(c.getJugador().getUbicacion()<MejorCarta.getJugador().getUbicacion()){
						MejorCarta = c;	
					}
				}
				
			}
			
		}
		
		this.ganadorBaza = MejorCarta.getJugador();
	}
	
	
	
	
	
	
	
}
