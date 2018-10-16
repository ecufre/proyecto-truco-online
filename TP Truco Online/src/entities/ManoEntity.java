package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="manos")
public class ManoEntity {

	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne()
	@JoinColumn(name="id_juego")
	private JuegoEntity juego;
	@OneToMany
	@JoinColumn(name="id_mano")
	private List<BazaEntity> bazas;
	@OneToMany
	@JoinColumn(name="id_mano")
	private List<CantoEntity> cantos;
	@OneToMany
	@JoinColumn(name="id_mano")
	private List<CartaEntity> cartas;
	
	public ManoEntity() {}

	

	public ManoEntity(JuegoEntity juego, List<BazaEntity> bazas, List<CantoEntity> cantos, List<CartaEntity> cartas) {
		super();
		this.juego = juego;
		this.bazas = bazas;
		this.cantos = cantos;
		this.cartas = cartas;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public JuegoEntity getJuego() {
		return juego;
	}

	public void setJuego(JuegoEntity juego) {
		this.juego = juego;
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
