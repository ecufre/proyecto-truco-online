package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import enumeraciones.TipoCanto;


@Entity
@Table(name="cantos")
public class CantoEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	private Boolean querido;
	private TipoCanto tipoCanto;
	private Integer cantante;
	
	public CantoEntity() {}

	public CantoEntity(Boolean querido, TipoCanto tipoCanto, Integer cantante) {
		this.querido = querido;
		this.tipoCanto = tipoCanto;
		this.cantante = cantante;
	}

	public CantoEntity(Integer id, Boolean querido, TipoCanto tipoCanto, Integer cantante) {
		this.id = id;
		this.querido = querido;
		this.tipoCanto = tipoCanto;
		this.cantante = cantante;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getQuerido() {
		return querido;
	}

	public void setQuerido(Boolean querido) {
		this.querido = querido;
	}

	public TipoCanto getTipoCanto() {
		return tipoCanto;
	}

	public void setTipoCanto(TipoCanto tipoCanto) {
		this.tipoCanto = tipoCanto;
	}

	public Integer getCantante() {
		return cantante;
	}

	public void setCantante(Integer cantante) {
		this.cantante = cantante;
	}
	
	
}
