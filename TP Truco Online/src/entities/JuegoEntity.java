package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import negocio.Mano;

@Entity
@Table(name="juegos")
public class JuegoEntity {
	private int puntajePar;
	private int puntajeImpar;
	private boolean finalizado;
	
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private PartidaEntity partida;
	
	@OneToMany
	private List<ManoEntity> manos;
	
	public JuegoEntity() {}

	public JuegoEntity(int puntajePar, int puntajeImpar, boolean finalizado, Integer id, PartidaEntity partida,
			List<ManoEntity> manos) {
		super();
		this.puntajePar = puntajePar;
		this.puntajeImpar = puntajeImpar;
		this.finalizado = finalizado;
		this.id = id;
		this.partida = partida;
		this.manos = manos;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PartidaEntity getPartida() {
		return partida;
	}

	public void setPartida(PartidaEntity partida) {
		this.partida = partida;
	}

	public List<ManoEntity> getManos() {
		return manos;
	}

	public void setManos(List<ManoEntity> manos) {
		this.manos = manos;
	}
	
	
	

}
