package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bazaas")
public class BazaEntity {
	@Id
	private int turno;
	@OneToMany
	private List<CartaEntity> cartas;
	@ManyToOne
	private JugadorEntity ganadorBaza;
	
	public BazaEntity() {}

	public BazaEntity(int turno, JugadorEntity ganadorBaza) {
		this.turno = turno;
		this.ganadorBaza = ganadorBaza;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public List<CartaEntity> getCartas() {
		return cartas;
	}

	public void setCartas(List<CartaEntity> cartas) {
		this.cartas = cartas;
	}

	public JugadorEntity getGanadorBaza() {
		return ganadorBaza;
	}

	public void setGanadorBaza(JugadorEntity ganadorBaza) {
		this.ganadorBaza = ganadorBaza;
	}
}
