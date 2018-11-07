package negocio;

import dto.CategoriaDTO;
import enumeraciones.TipoCategoria;

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
	public TipoCategoria calcularCategoria() {
		//TODO Categorias definidas como int entre 1 y 4. Tendria que armar un Enum
		float promedio = this.calcularPromedio();
		if (this.partidasJugadas > 1000 && this.puntosTotales > 8000 && promedio > 8) {
			return TipoCategoria.Master;
		} else if (this.partidasJugadas > 500 && this.puntosTotales > 3000 && promedio > 6) {
			return TipoCategoria.Experto;
		} else if (this.partidasJugadas > 100 && this.puntosTotales > 500 && promedio > 5) {
			return TipoCategoria.Calificado;
		} else {
			return TipoCategoria.Novato;
		}
	}
	
	private float calcularPromedio() {
		if (this.partidasJugadas > 0) return (this.puntosTotales / this.partidasJugadas);
		else return 0;
	}
	
	public CategoriaDTO toDTO() {
		return new CategoriaDTO(this.partidasJugadas, this.puntosTotales, this.calcularPromedio(), this.calcularCategoria());
	}
}
