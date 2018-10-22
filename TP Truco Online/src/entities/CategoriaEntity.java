package entities;

import javax.persistence.Embeddable;

@Embeddable
public class CategoriaEntity {
	private Integer partidasJugadas;
	private Integer puntosTotales;

	public CategoriaEntity(Integer partidasJugadas, Integer puntosTotales) {
		this.partidasJugadas = partidasJugadas;
		this.puntosTotales = puntosTotales;
	}
	public CategoriaEntity() {
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
}
