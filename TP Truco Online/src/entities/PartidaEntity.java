package entities;

//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.LocalDateTime;

import enumeraciones.EstadoPartida;

@Entity
@Table(name="partidas")
public class PartidaEntity {
	@Id
	@GeneratedValue
	private Integer id;
	@OneToMany
	private List<JuegoEntity> juegos;
	@ManyToOne
	private JugadorEntity jugador1;
	@ManyToOne
	private JugadorEntity jugador2;
	@ManyToOne
	private JugadorEntity jugador3;
	@ManyToOne
	private JugadorEntity jugador4;	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaCreacion;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaActualizacion;

	
	@ManyToMany
	@JoinTable(name="partidas_jugadores_listos")
	private List<JugadorEntity> jugadoresListos;
	private Boolean esAbierta;
	private EstadoPartida estado;
	private Integer ganador;
	
	public PartidaEntity() {}

	public PartidaEntity(Boolean esAbierta, EstadoPartida estado, Integer ganador, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
		this.esAbierta = esAbierta;
		this.estado = estado;
		this.ganador = ganador;
		this.fechaCreacion = fechaCreacion.toDateTime().toCalendar(Locale.getDefault()); //(fechaCreacion == null ? null : Timestamp.valueOf(fechaCreacion));
		this.fechaActualizacion = fechaActualizacion.toDateTime().toCalendar(Locale.getDefault()); //(fechaActualizacion == null ? null : Timestamp.valueOf(fechaActualizacion));
	}

	public PartidaEntity(Integer id, Boolean esAbierta, EstadoPartida estado, Integer ganador, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
		this.id = id;
		this.esAbierta = esAbierta;
		this.estado = estado;
		this.ganador = ganador;
		this.fechaCreacion = fechaCreacion.toDateTime().toCalendar(Locale.getDefault()); //(fechaCreacion == null ? null : Timestamp.valueOf(fechaCreacion));
		this.fechaActualizacion = fechaActualizacion.toDateTime().toCalendar(Locale.getDefault()); //(fechaActualizacion == null ? null : Timestamp.valueOf(fechaActualizacion));
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
		List<JugadorEntity> jugadores = new ArrayList<JugadorEntity>();
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		jugadores.add(jugador3);
		jugadores.add(jugador4);
		return jugadores;
	}

	public void setJugadores(List<JugadorEntity> jugadores) {
		if (jugadores.size() > 0) this.jugador1 = jugadores.get(0);
		if (jugadores.size() > 1) this.jugador2 = jugadores.get(1);
		if (jugadores.size() > 2) this.jugador3 = jugadores.get(2);
		if (jugadores.size() > 3) this.jugador4 = jugadores.get(3);
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

	public LocalDateTime getFechaCreacion() {
		return (fechaCreacion == null ? null : LocalDateTime.fromCalendarFields(fechaCreacion));
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion.toDateTime().toCalendar(Locale.getDefault());
	}

	public LocalDateTime getFechaActualizacion() {
		return (fechaActualizacion == null ? null : LocalDateTime.fromCalendarFields(fechaActualizacion));
	}

	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion.toDateTime().toCalendar(Locale.getDefault());
	}
	
	
}
