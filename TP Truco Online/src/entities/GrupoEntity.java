package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="grupos")
public class GrupoEntity {
	private String nombre;
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private JugadorEntity administrador;
	@ManyToMany
	private List<JugadorEntity> miembros;
	@OneToMany
	private List<ParejaEntity> parejas;
	@OneToMany
	private List<PartidaEntity> partidas;
	
	public GrupoEntity() {}

	public GrupoEntity(String nombre, int id) {
		this.nombre = nombre;
		this.id = id;
	}

	public GrupoEntity(String nombre) {
		this.nombre = nombre;
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

	public JugadorEntity getAdministrador() {
		return administrador;
	}

	public void setAdministrador(JugadorEntity administrador) {
		this.administrador = administrador;
	}

	public List<JugadorEntity> getMiembros() {
		return miembros;
	}

	public void setMiembros(ArrayList<JugadorEntity> miembros) {
		this.miembros = miembros;
	}

	public List<ParejaEntity> getParejas() {
		return parejas;
	}

	public void setParejas(ArrayList<ParejaEntity> parejas) {
		this.parejas = parejas;
	}

	public List<PartidaEntity> getPartidas() {
		return partidas;
	}

	public void setPartidas(ArrayList<PartidaEntity> partidas) {
		this.partidas = partidas;
	}
}
