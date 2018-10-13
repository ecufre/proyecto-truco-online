package negocio;

import java.util.ArrayList;

import dto.JugadorDTO;
import dto.ParejaDTO;

public class Pareja {
	private ArrayList<Jugador> jugadores;
	private int id; //Solo necesario cuando se persiste la pareja.
	
	public Integer calcularCategoria() {
		Integer categoria = null;
		for (Jugador j : jugadores) {
			if (categoria == null) {
				categoria = j.getCategoria().calcularCategoria();
			} else {
				if (j.getCategoria().calcularCategoria() < categoria) {
					categoria = j.getCategoria().calcularCategoria();
				}
			}
		}
		//TODO Error si es null?
		return categoria;
	}

	public Pareja(ArrayList<Jugador> jugadores, int id) {
		this.jugadores = jugadores;
		this.id = id;
	}

	public Pareja(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ParejaDTO toDTO() {
		ArrayList<JugadorDTO> jugadoresDTO = new ArrayList<JugadorDTO>();
		for (Jugador j : this.jugadores) {
			jugadoresDTO.add(j.toDTO());
		}
		return new ParejaDTO(jugadoresDTO, this.id);
	}
}
