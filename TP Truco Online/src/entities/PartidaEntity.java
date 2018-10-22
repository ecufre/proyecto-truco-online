package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enumeraciones.EstadoPartida;

@Entity
@Table(name="partidas")
public class PartidaEntity {
	@Id
	@GeneratedValue
	private Integer id;
	@OneToMany
	private List<JuegoEntity> juegos;
	@OneToMany
	private List<JugadorEntity> jugadores;
	@OneToMany
	@JoinTable(name="partida_jugadoes_listos")
	private List<JugadorEntity> jugadoresListos;
	private Boolean esAbierta;
	private EstadoPartida estado;
	private Integer ganador;
	
	public PartidaEntity() {}

	public PartidaEntity(Boolean esAbierta, EstadoPartida estado, Integer ganador) {
		this.esAbierta = esAbierta;
		this.estado = estado;
		this.ganador = ganador;
	}

	public PartidaEntity(Integer id, Boolean esAbierta, EstadoPartida estado, Integer ganador) {
		this.id = id;
		this.esAbierta = esAbierta;
		this.estado = estado;
		this.ganador = ganador;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<JuegoEntity> getJuegos() {
		return juegos;
	}

	public void setJuegos(List<JuegoEntity> juegos) {
		this.juegos = juegos;
	}

	public List<JugadorEntity> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<JugadorEntity> jugadores) {
		this.jugadores = jugadores;
	}

	public List<JugadorEntity> getJugadoresListos() {
		return jugadoresListos;
	}

	public void setJugadoresListos(List<JugadorEntity> jugadoresListos) {
		this.jugadoresListos = jugadoresListos;
	}

	public Boolean getEsAbierta() {
		return esAbierta;
	}

	public void setEsAbierta(Boolean esAbierta) {
		this.esAbierta = esAbierta;
	}

	public EstadoPartida getEstado() {
		return estado;
	}

	public void setEstado(EstadoPartida estado) {
		this.estado = estado;
	}

	public Integer getGanador() {
		return ganador;
	}

	public void setGanador(Integer ganador) {
		this.ganador = ganador;
	}
}
