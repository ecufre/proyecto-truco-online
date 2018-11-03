package dto;

import java.io.Serializable;
import org.joda.time.LocalDateTime;
import java.util.ArrayList;

import enumeraciones.EstadoPartida;

public class PartidaDTO implements Serializable {

	private static final long serialVersionUID = 6680808569403596388L;
	private int id;
	private ArrayList<JugadorDTO> jugadores;
	private JugadorDTO turnoJugador;
	private boolean esAbierta;
	private EstadoPartida estado;
	private Integer ganador;
	private ArrayList<JuegoDTO> juegos;
	private String charla;
	private LocalDateTime fechaCreacion; //TODO
	private LocalDateTime fechaActualizacion; //TODO


	public PartidaDTO() { }

	public PartidaDTO(int id, ArrayList<JugadorDTO> jugadores,
			boolean esAbierta, EstadoPartida estado, Integer ganador,
			ArrayList<JuegoDTO> juegos, String charla) {
		super();
		this.id = id;
		this.jugadores = jugadores;
		this.esAbierta = esAbierta;
		this.estado = estado;
		this.ganador = ganador;
		this.juegos = juegos;
		this.charla = charla;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<JugadorDTO> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<JugadorDTO> jugadores) {
		this.jugadores = jugadores;
	}

	public boolean isEsAbierta() {
		return esAbierta;
	}

	public void setEsAbierta(boolean esAbierta) {
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

	public ArrayList<JuegoDTO> getJuegos() {
		return juegos;
	}

	public void setJuegos(ArrayList<JuegoDTO> juegos) {
		this.juegos = juegos;
	}

	public String getCharla() {
		return charla;
	}

	public void setCharla(String charla) {
		this.charla = charla;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion2) {
		this.fechaCreacion = fechaCreacion2;
	}

	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public JugadorDTO getTurnoJugador() {
		return turnoJugador;
	}

	public void setTurnoJugador(JugadorDTO turnoJugador) {
		this.turnoJugador = turnoJugador;
	}
}

