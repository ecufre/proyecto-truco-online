package entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import negocio.Jugador;

@Entity
@Table(name="grupos")
public class GrupoEntity {
	private String nombre;
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private Jugador administrador;
	@ManyToMany(mappedBy="grupos")
	private ArrayList<JugadorEntity> miembros;
	@OneToMany
	private ArrayList<ParejaEntity> parejas;
	@OneToMany
	private ArrayList<PartidaEntity> partidas;
	
	public GrupoEntity() {}

	public GrupoEntity(String nombre, int id) {
		this.nombre = nombre;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Jugador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Jugador administrador) {
		this.administrador = administrador;
	}

	public ArrayList<JugadorEntity> getMiembros() {
		return miembros;
	}

	public void setMiembros(ArrayList<JugadorEntity> miembros) {
		this.miembros = miembros;
	}

	public ArrayList<ParejaEntity> getParejas() {
		return parejas;
	}

	public void setParejas(ArrayList<ParejaEntity> parejas) {
		this.parejas = parejas;
	}

	public ArrayList<PartidaEntity> getPartidas() {
		return partidas;
	}

	public void setPartidas(ArrayList<PartidaEntity> partidas) {
		this.partidas = partidas;
	}
}
