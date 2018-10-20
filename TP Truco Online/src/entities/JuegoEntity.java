package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import negocio.Mano;

@Entity
@Table(name="juegos")
public class JuegoEntity {
	@Id
	@GeneratedValue
	private int id;
	private int puntajePar;
	private int puntajeImpar;
	private boolean finalizado;
	@OneToMany
	private List<ManoEntity> manos;
	@OneToOne
	private ManoEntity manoActual;
	
	public JuegoEntity() {}

	public JuegoEntity(int id, int puntajePar, int puntajeImpar, boolean finalizado, List<Mano> manos,
			Mano manoActual) {
		this.id = id;
		this.puntajePar = puntajePar;
		this.puntajeImpar = puntajeImpar;
		this.finalizado = finalizado;
		//this.manos = manos;
		//this.manoActual = manoActual;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPuntajePar() {
		return puntajePar;
	}

	public void setPuntajePar(int puntajePar) {
		this.puntajePar = puntajePar;
	}

	public int getPuntajeImpar() {
		return puntajeImpar;
	}

	public void setPuntajeImpar(int puntajeImpar) {
		this.puntajeImpar = puntajeImpar;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	/*
	public List<Mano> getManos() {
		return manos;
	}

	public void setManos(List<Mano> manos) {
		this.manos = manos;
	}

	public Mano getManoActual() {
		return manoActual;
	}

	public void setManoActual(Mano manoActual) {
		this.manoActual = manoActual;
	}
*/
}
