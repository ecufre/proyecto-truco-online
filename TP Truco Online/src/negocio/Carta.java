package negocio;

import dto.CartaDTO;

public class Carta {
	private static int siguienteId = 1; //TODO esto se reemplaza por la persistencia
	private int id;
	private int cartaId;
	private int valor;
	private int valorEnvite;
	private int numero;
	private String palo;
	private int ubicacionJugador;
	private boolean jugada;
	
	//Metodo a eliminar con persistencia
	private static int getSiguienteId() {
		return siguienteId++;
	}

	public Carta(int cartaId, int valor,int valorEnvite, int numero, String palo) {
		this.id = Carta.getSiguienteId();
		this.cartaId = cartaId;
		this.valor = valor;
		this.valorEnvite=valorEnvite;
		this.numero = numero;
		this.palo = palo;
		this.jugada = false;
	}
	
	public void grabar() {
		//TODO Grabar
	}

	public int getJugador() {
		return ubicacionJugador;
	}

	public int getValor() {
		return valor;
	}

	public int getId() {
		return cartaId;
	}

	public void setJugador(int ubicacionJugador) {
		this.ubicacionJugador = ubicacionJugador;
	}

	public boolean isJugada() {
		return jugada;
	}

	public void setJugada(boolean jugada) {
		this.jugada = jugada;
	}

	public int getValorEnvite() {
		return valorEnvite;
	}

	public String getPalo() {
		return palo;
	}
	
	

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public CartaDTO toDTO() {
		return (new CartaDTO(this.getId(),this.getNumero(),this.getPalo(),this.getJugador()));
		
	}

	@Override
	public String toString() {
		return "Carta [id=" + id + ", cartaId=" + cartaId + ", valor=" + valor + ", valorEnvite=" + valorEnvite
				+ ", numero=" + numero + ", palo=" + palo + ", ubicacionJugador=" + ubicacionJugador + ", jugada="
				+ jugada + "]";
	}
	
}
