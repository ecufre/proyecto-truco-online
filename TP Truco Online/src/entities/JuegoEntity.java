package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="juegos")
public class JuegoEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer puntajePar;
	private Integer puntajeImpar;
	private Boolean finalizado;
	@OneToMany
	private List<ManoEntity> manos;
	
	public JuegoEntity() {}

	public JuegoEntity(Integer puntajePar, Integer puntajeImpar, Boolean finalizado) {
		this.puntajePar = puntajePar;
		this.puntajeImpar = puntajeImpar;
		this.finalizado = finalizado;
	}

	public JuegoEntity(Integer id, Integer puntajePar, Integer puntajeImpar, Boolean finalizado) {
		this.id = id;
		this.puntajePar = puntajePar;
		this.puntajeImpar = puntajeImpar;
		this.finalizado = finalizado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPuntajePar() {
		return puntajePar;
	}

	public void setPuntajePar(Integer puntajePar) {
		this.puntajePar = puntajePar;
	}

	public Integer getPuntajeImpar() {
		return puntajeImpar;
	}

	public void setPuntajeImpar(Integer puntajeImpar) {
		this.puntajeImpar = puntajeImpar;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public List<ManoEntity> getManos() {
		return manos;
	}

	public void setManos(List<ManoEntity> manos) {
		this.manos = manos;
	}

}
