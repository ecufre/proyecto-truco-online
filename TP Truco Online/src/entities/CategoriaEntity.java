package entities;

import javax.persistence.Embeddable;

@Embeddable
public class CategoriaEntity {
	private int partidasJugadas;
	private int puntosTotales;
	public CategoriaEntity(int partidasJugadas, int puntosTotales) {
		this.partidasJugadas = partidasJugadas;
		this.puntosTotales = puntosTotales;
	}
	public CategoriaEntity() {
	}
	public int getPartidasJugadas() {
		return partidasJugadas;
	}
	public void setPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}
	public int getPuntosTotales() {
		return puntosTotales;
	}
	public void setPuntosTotales(int puntosTotales) {
		this.puntosTotales = puntosTotales;
	}
}
