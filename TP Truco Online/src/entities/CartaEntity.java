package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cartas")
public class CartaEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	private Integer cartaId;
	private Integer valor;
	private Integer valorEnvite;
	private Integer numero;
	private String palo;
	private Integer ubicacionJugador;
	private Boolean jugada;
	
	public CartaEntity() {}

	public CartaEntity(Integer cartaId, Integer valor, Integer valorEnvite, Integer numero, String palo,
			Integer ubicacionJugador, Boolean jugada) {
		this.cartaId = cartaId;
		this.valor = valor;
		this.valorEnvite = valorEnvite;
		this.numero = numero;
		this.palo = palo;
		this.ubicacionJugador = ubicacionJugador;
		this.jugada = jugada;
	}

	public CartaEntity(Integer id, Integer cartaId, Integer valor, Integer valorEnvite, Integer numero, String palo,
			Integer ubicacionJugador, Boolean jugada) {
		this.id = id;
		this.cartaId = cartaId;
		this.valor = valor;
		this.valorEnvite = valorEnvite;
		this.numero = numero;
		this.palo = palo;
		this.ubicacionJugador = ubicacionJugador;
		this.jugada = jugada;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCartaId() {
		return cartaId;
	}

	public void setCartaId(Integer cartaId) {
		this.cartaId = cartaId;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Integer getValorEnvite() {
		return valorEnvite;
	}

	public void setValorEnvite(Integer valorEnvite) {
		this.valorEnvite = valorEnvite;
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

	public Integer getUbicacionJugador() {
		return ubicacionJugador;
	}

	public void setUbicacionJugador(Integer ubicacionJugador) {
		this.ubicacionJugador = ubicacionJugador;
	}

	public Boolean getJugada() {
		return jugada;
	}

	public void setJugada(Boolean jugada) {
		this.jugada = jugada;
	}
}
