package dto;

import java.io.Serializable;
import java.util.ArrayList;

public class GrupoDTO implements Serializable {

	private static final long serialVersionUID = -1885940853815496900L;
	private String nombre;
	private int id;
	private JugadorDTO administrador;
	private ArrayList<JugadorDTO> miembros;
	private ArrayList<ParejaDTO> parejas;
	private ArrayList<PartidaDTO> partidas;
	
	public GrupoDTO(String nombre, int id) {
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

	public JugadorDTO getAdministrador() {
		return administrador;
	}

	public void setAdministrador(JugadorDTO administrador) {
		this.administrador = administrador;
	}

	public ArrayList<JugadorDTO> getMiembros() {
		return miembros;
	}

	public void setMiembros(ArrayList<JugadorDTO> miembros) {
		this.miembros = miembros;
	}

	public ArrayList<ParejaDTO> getParejas() {
		return parejas;
	}

	public void setParejas(ArrayList<ParejaDTO> parejas) {
		this.parejas = parejas;
	}

	public ArrayList<PartidaDTO> getPartidas() {
		return partidas;
	}

	public void setPartidas(ArrayList<PartidaDTO> partidas) {
		this.partidas = partidas;
	}
	
}