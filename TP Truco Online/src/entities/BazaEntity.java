package entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import negocio.Carta;

@Entity
@Table(name="bazaas")
public class BazaEntity {
	@Id
	@GeneratedValue
	private Integer id;
	@OneToMany
	private ArrayList<CartaEntity> cartasbaza;
	private Integer ganadorBaza;
	private Integer turno;
	private Integer mano;
	private Boolean parda;
	
	public BazaEntity() {}

	public BazaEntity(Integer ganadorBaza, Integer turno, Integer mano, Boolean parda) {
		this.ganadorBaza = ganadorBaza;
		this.turno = turno;
		this.mano = mano;
		this.parda = parda;
	}

	public BazaEntity(Integer id, Integer ganadorBaza, Integer turno, Integer mano, Boolean parda) {
		this.id = id;
		this.ganadorBaza = ganadorBaza;
		this.turno = turno;
		this.mano = mano;
		this.parda = parda;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<CartaEntity> getCartasbaza() {
		return cartasbaza;
	}

	public void setCartasbaza(ArrayList<CartaEntity> cartasbaza) {
		this.cartasbaza = cartasbaza;
	}

	public Integer getGanadorBaza() {
		return ganadorBaza;
	}

	public void setGanadorBaza(Integer ganadorBaza) {
		this.ganadorBaza = ganadorBaza;
	}

	public Integer getTurno() {
		return turno;
	}

	public void setTurno(Integer turno) {
		this.turno = turno;
	}

	public Integer getMano() {
		return mano;
	}

	public void setMano(Integer mano) {
		this.mano = mano;
	}

	public Boolean getParda() {
		return parda;
	}

	public void setParda(Boolean parda) {
		this.parda = parda;
	}


}
