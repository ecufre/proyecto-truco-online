package negocio;

import dto.CategoriaDTO;

public class Categoria {
	private int partidasJugadas;
	private int puntosTotales;
	
	public Categoria(int partidasJugadas, int puntosTotales) {
		this.partidasJugadas = partidasJugadas;
		this.puntosTotales = puntosTotales;
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
	
	public void sumarPuntos(int puntos) {
		this.partidasJugadas++;
		this.puntosTotales = this.puntosTotales + puntos;
	}
	public int calcularCategoria() {
		//TODO Categorias definidas como int entre 1 y 4.
		float promedio = this.calcularPromedio();
		if (this.partidasJugadas > 1000 && this.puntosTotales > 8000 && promedio > 8) {
			return 4;
		} else if (this.partidasJugadas > 500 && this.puntosTotales > 3000 && promedio > 6) {
			return 3;
		} else if (this.partidasJugadas > 100 && this.puntosTotales > 500 && promedio > 5) {
			return 2;
		} else {
			return 1;
		}
	}
	
	private float calcularPromedio() {
		return (this.partidasJugadas / this.puntosTotales);
	}
	
	public CategoriaDTO toDTO() {
		return new CategoriaDTO(this.partidasJugadas, this.puntosTotales, this.calcularPromedio());
	}
}
