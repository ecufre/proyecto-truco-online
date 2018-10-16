package dto;



public class CartaDTO {

	private Integer id;
	private Integer numero;
	private String palo;
	private Integer idJugador;
	
		
	public CartaDTO(Integer id, Integer numero, String palo, Integer idJugador) {
		super();
		this.id = id;
		this.numero = numero;
		this.palo = palo;
		this.idJugador = idJugador;
	}



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
	

	
	
	
	
}
