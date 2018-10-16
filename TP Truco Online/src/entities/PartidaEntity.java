package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import negocio.Juego;
import negocio.Jugador;

@Entity
@Table(name="partida")
public class PartidaEntity {
	private boolean esAbierta;
	private int estado;
	private int ganador;
	@Id
	@GeneratedValue
	private Integer id;
	@OneToMany
	@JoinColumn(name="id_partida")
	private List<JuegoEntity> juegos;
	@OneToMany
	@JoinColumn(name="id_partida")
	private List<JugadorEntity> jugadores;
	private List<Boolean> jugadoresListos;
	
	public PartidaEntity() {}

	public PartidaEntity(boolean esAbierta, int estado, int ganador, Integer id, List<JuegoEntity> juegos,
			List<JugadorEntity> jugadores, List<Boolean> jugadoresListos) {
		super();
		this.esAbierta = esAbierta;
		this.estado = estado;
		this.ganador = ganador;
		this.id = id;
		this.juegos = juegos;
		this.jugadores = jugadores;
		this.jugadoresListos = jugadoresListos;
	}

	public boolean isEsAbierta() {
		return esAbierta;
	}

	public void setEsAbierta(boolean esAbierta) {
		this.esAbierta = esAbierta;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getGanador() {
		return ganador;
	}

	public void setGanador(int ganador) {
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

	public List<Boolean> getJugadoresListos() {
		return jugadoresListos;
	}

	public void setJugadoresListos(List<Boolean> jugadoresListos) {
		this.jugadoresListos = jugadoresListos;
	}
}
