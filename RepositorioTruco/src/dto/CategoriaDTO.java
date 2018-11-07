package dto;

import java.io.Serializable;

import enumeraciones.TipoCategoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1747311364662476520L;
	private Integer partidasJugadas;
	private Integer puntosTotales;
	private Float promedio;
	private TipoCategoria categoria;
	
	public CategoriaDTO(Integer partidasJugadas, Integer puntosTotales, Float promedio, TipoCategoria categoria) {
		this.partidasJugadas = partidasJugadas;
		this.puntosTotales = puntosTotales;
		this.promedio = promedio;
		this.categoria = categoria;
	}

	public Integer getPartidasJugadas() {
		return partidasJugadas;
	}

	public void setPartidasJugadas(Integer partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}

	public Integer getPuntosTotales() {
		return puntosTotales;
	}

	public void setPuntosTotales(Integer puntosTotales) {
		this.puntosTotales = puntosTotales;
	}

	public Float getPromedio() {
		return promedio;
	}

	public void setPromedio(Float promedio) {
		this.promedio = promedio;
	}

	public TipoCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(TipoCategoria categoria) {
		this.categoria = categoria;
	}
}
