package entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="parejas")
public class ParejaEntity {
	@ManyToMany
	private ArrayList<JugadorEntity> jugadores;
	@Id
	@GeneratedValue
	private Integer id;
	
	public ParejaEntity() {}

	public ParejaEntity(Integer id) {
		this.id = id;
	}

	public ArrayList<JugadorEntity> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<JugadorEntity> jugadores) {
		this.jugadores = jugadores;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
