package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="parejas")
public class ParejaEntity {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private JugadorEntity jugador1;
	@ManyToOne
	private JugadorEntity jugador2;
	
	public ParejaEntity() {}

	public ParejaEntity(Integer id, JugadorEntity jugador1, JugadorEntity jugador2) {
		this.id = id;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public JugadorEntity getJugador1() {
		return jugador1;
	}

	public void setJugador1(JugadorEntity jugador1) {
		this.jugador1 = jugador1;
	}

	public JugadorEntity getJugador2() {
		return jugador2;
	}

	public void setJugador2(JugadorEntity jugador2) {
		this.jugador2 = jugador2;
	}
}
