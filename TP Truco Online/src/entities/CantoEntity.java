package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import negocio.TipoCanto;

@Entity
@Table(name="cantos")
public class CantoEntity {
	
	private boolean querido;
	private TipoCanto tipoCanto;
	private int valor;
	
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private ManoEntity mano;
	
	public CantoEntity() {}

	public CantoEntity(boolean querido, TipoCanto tipoCanto, int valor, ManoEntity mano) {
		super();
		this.querido = querido;
		this.tipoCanto = tipoCanto;
		this.valor = valor;
		this.mano = mano;
	}

	public boolean isQuerido() {
		return querido;
	}

	public void setQuerido(boolean querido) {
		this.querido = querido;
	}

	public TipoCanto getTipoCanto() {
		return tipoCanto;
	}

	public void setTipoCanto(TipoCanto tipoCanto) {
		this.tipoCanto = tipoCanto;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ManoEntity getMano() {
		return mano;
	}

	public void setMano(ManoEntity mano) {
		this.mano = mano;
	}
	
	

}
