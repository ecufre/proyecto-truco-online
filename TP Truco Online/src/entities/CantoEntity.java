package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import negocio.TipoCanto;

@Entity
@Table(name="cantos")
public class CantoEntity {
	
	private boolean querido;
	@Id
	private TipoCanto tipoCanto;
	
	public CantoEntity() {}

	public CantoEntity(boolean querido, TipoCanto tipoCanto) {
		this.querido = querido;
		this.tipoCanto = tipoCanto;
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
}
