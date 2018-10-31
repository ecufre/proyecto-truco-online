package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="manos")
public class ManoEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer numeroMano;
	@OneToMany
	private List<BazaEntity> bazas;
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<CantoEntity> cantos;
	@OneToMany
	private List<CartaEntity> cartas;
	
	public ManoEntity() {}

	public ManoEntity(Integer numeroMano) {
		this.numeroMano = numeroMano;
	}

	public ManoEntity(Integer id, Integer numeroMano) {
		this.id = id;
		this.numeroMano = numeroMano;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroMano() {
		return numeroMano;
	}

	public void setNumeroMano(Integer numeroMano) {
		this.numeroMano = numeroMano;
	}

	public List<BazaEntity> getBazas() {
		return bazas;
	}

	public void setBazas(List<BazaEntity> bazas) {
		this.bazas = bazas;
	}

	public List<CantoEntity> getCantos() {
		return cantos;
	}

	public void setCantos(List<CantoEntity> cantos) {
		this.cantos = cantos;
	}

	public List<CartaEntity> getCartas() {
		return cartas;
	}

	public void setCartas(List<CartaEntity> cartas) {
		this.cartas = cartas;
	}
}
