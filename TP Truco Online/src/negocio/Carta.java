package negocio;

import dao.CartaDAO;
import dto.CartaDTO;
import excepciones.ComunicacionException;

public class Carta {
	private int id;
	private int cartaId;
	private int valor;
	private int valorEnvite;
	private int numero;
	private String palo;
	private int ubicacionJugador;
	private boolean jugada;
	
	public Carta(int cartaId, int valor,int valorEnvite, int numero, String palo) {
		this.cartaId = cartaId;
		this.valor = valor;
		this.valorEnvite=valorEnvite;
		this.numero = numero;
		this.palo = palo;
		this.jugada = false;
	}
	
	public Carta(int id, int cartaId, int valor, int valorEnvite, int numero, String palo, int ubicacionJugador,
			boolean jugada) {
		this.id = id;
		this.cartaId = cartaId;
		this.valor = valor;
		this.valorEnvite = valorEnvite;
		this.numero = numero;
		this.palo = palo;
		this.ubicacionJugador = ubicacionJugador;
		this.jugada = jugada;
	}
	
	public void crear() throws ComunicacionException {
		Integer id = CartaDAO.getInstancia().crear(this);
		if (id != null) this.id = id;
		else throw new ComunicacionException("Hubo un error al generar la nueva carta");
	}
	public void grabar() {
		CartaDAO.getInstancia().grabar(this);
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
	
	public int getCartaId() {
		return cartaId;
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
