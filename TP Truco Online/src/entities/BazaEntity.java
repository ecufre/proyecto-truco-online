package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import negocio.Carta;
import negocio.Jugador;

@Entity
@Table(name="bazaas")
public class BazaEntity {
	
	private int turno;
	
	@Id
	@GeneratedValue
	private Integer id;
	@OneToMany
	private List<Carta> cartasbaza;
	@ManyToOne
	private ManoEntity mano;

	@ManyToOne
	private JugadorEntity ganadorBaza;
	
	public BazaEntity() {}

	public BazaEntity(int turno, List<Carta> cartasbaza, ManoEntity mano, JugadorEntity ganadorBaza) {
		super();
		this.turno = turno;
		this.cartasbaza = cartasbaza;
		this.mano = mano;
		this.ganadorBaza = ganadorBaza;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Carta> getCartasbaza() {
		return cartasbaza;
	}

	public void setCartasbaza(List<Carta> cartasbaza) {
		this.cartasbaza = cartasbaza;
	}

	public ManoEntity getMano() {
		return mano;
	}

	public void setMano(ManoEntity mano) {
		this.mano = mano;
	}

	public JugadorEntity getGanadorBaza() {
		return ganadorBaza;
	}

	public void setGanadorBaza(JugadorEntity ganadorBaza) {
		this.ganadorBaza = ganadorBaza;
	}
	
	
	
	

}
