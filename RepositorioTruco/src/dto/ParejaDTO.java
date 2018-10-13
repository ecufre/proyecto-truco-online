package dto;

import java.util.ArrayList;

public class ParejaDTO {
	private ArrayList<JugadorDTO> jugadores;
	
	private Integer id;

	public ParejaDTO(ArrayList<JugadorDTO> jugadores, Integer id) {
		this.jugadores = jugadores;
		this.id = id;
	}

	public ArrayList<JugadorDTO> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<JugadorDTO> jugadores) {
		this.jugadores = jugadores;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
