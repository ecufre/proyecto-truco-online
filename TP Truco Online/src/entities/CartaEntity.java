package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cartas")
public class CartaEntity {
	
	private int valor;
	private String palo;
	private int jugada;
	
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private ManoEntity mano;
	//TODO  AGREGAR JUGADOR
	
	public CartaEntity() {}

	public CartaEntity(int valor, String palo, int jugada, ManoEntity mano) {
		super();
		this.valor = valor;
		this.palo = palo;
		this.jugada = jugada;
		this.mano = mano;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ManoEntity getMano() {
		return mano;
	}

	public void setMano(ManoEntity mano) {
		this.mano = mano;
	}
}
