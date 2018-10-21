package entities;

import java.util.ArrayList;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mazo")
public class MazoEntity {
	@Id
	@GeneratedValue
	private Integer cartaId;
	private Integer valor;
	private Integer valorEnvite;
	private Integer numero;
	private String palo;
	
	public MazoEntity() {}
	
	public MazoEntity(Integer cartaId, Integer valor, Integer valorEnvite, Integer numero, String palo) {
		this.cartaId = cartaId;
		this.valor = valor;
		this.valorEnvite = valorEnvite;
		this.numero = numero;
		this.palo = palo;
	}

	public Integer getCartaId() {
		return cartaId;
	}

	public Integer getValor() {
		return valor;
	}

	public Integer getValorEnvite() {
		return valorEnvite;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getPalo() {
		return palo;
	}
}
