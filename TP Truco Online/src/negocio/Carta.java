package negocio;

import dto.CartaDTO;

public class Carta {
	private int id;
	private int valor;
	private int valorEnvite;
	private int numero;
	private String palo;
	private int jugada;
	private Jugador jugador;
	
	
	
	
	public Carta(int id, int valor,int valorEnvite, int numero, String palo) {
		super();
		this.id = id;
		this.valor = valor;
		this.valorEnvite=valorEnvite;
		this.numero = numero;
		this.palo = palo;
		this.jugada = 0;
		this.jugador = null;
	}


	public void jugarCarta(int nroBaza) {}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getValor() {
		return valor;
	}


	public void setValor(int valor) {
		this.valor = valor;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getPalo() {
		return palo;
	}


	public void setPalo(String palo) {
		this.palo = palo;
	}


	public int getJugada() {
		return jugada;
	}


	public void setJugada(int jugada) {
		this.jugada = jugada;
	}


	public Jugador getJugador() {
		return jugador;
	}


	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}


	

	public int getValorEnvite() {
		return valorEnvite;
	}


	public void setValorEnvite(int valorEnvite) {
		this.valorEnvite = valorEnvite;
	}


	@Override
	public String toString() {
		return "Carta [id=" + id + ", valor=" + valor + ", valorEnvite="
				+ valorEnvite + ", numero=" + numero + ", palo=" + palo
				+ ", jugada=" + jugada + ", jugador=" + jugador + "]";
	}


	public String toString2() {
		return "Carta [id=" + id+ ", valor=" + valor + " , jugador= "+this.getJugador().getApodo()+ " , ubicacion= "+jugador.getUbicacion()
	;
	}


	public CartaDTO toDTO() {
		return (new CartaDTO(this.getId(),this.getNumero(),this.getPalo(),this.getJugador().getUbicacion()));
		
	}


	public CartaDTO toDTOHistoria() {
		CartaDTO c = this.toDTO();
		c.setApodoJugador(c.getApodoJugador());
		return c;
	}
	
	
	
	
}
