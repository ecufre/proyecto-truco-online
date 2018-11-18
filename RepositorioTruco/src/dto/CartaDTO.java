package dto;

import java.io.Serializable;



public class CartaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2897638576412881813L;
	private Integer id;
	private Integer numero;
	private String palo;
	private Integer idJugador;
	private String apodoJugador;
		
	public CartaDTO(Integer id, Integer numero, String palo, Integer idJugador) {
		super();
		this.id = id;
		this.numero = numero;
		this.palo = palo;
		this.idJugador = idJugador;
		this.apodoJugador=null;
	}



	public CartaDTO() { }



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getNumero() {
		return numero;
	}



	public void setNumero(Integer numero) {
		this.numero = numero;
	}



	public String getPalo() {
		return palo;
	}



	public void setPalo(String palo) {
		this.palo = palo;
	}



	public Integer getIdJugador() {
		return idJugador;
	}



	public void setIdJugador(Integer idJugador) {
		this.idJugador = idJugador;
	}



	@Override
	public String toString() {
		return "CartaDTO [id=" + id + ", numero=" + numero + ", palo=" + palo
				+ ", idJugador=" + idJugador + "]";
	}



	public String getApodoJugador() {
		return apodoJugador;
	}



	public void setApodoJugador(String apodoJugador) {
		this.apodoJugador = apodoJugador;
	}


	
	
	
	
}
