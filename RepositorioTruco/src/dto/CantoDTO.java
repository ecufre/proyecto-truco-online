package dto;

import java.io.Serializable;

import enumeraciones.TipoCanto;

public class CantoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1788421376143417115L;
	private int id;
	private Boolean querido;
	private Integer idTipoCanto;
	private String DescTipoCanto;
	private Integer ValorTipoCanto;
	private int cantante;
	private String apodoCantante;
	
	
	
	
	
	
	public CantoDTO() { }
	
	
	
	
	public CantoDTO(int id, Boolean querido, Integer idTipoCanto,
			String descTipoCanto, Integer valorTipoCanto, int cantante) {
		super();
		this.id = id;
		this.querido = querido;
		this.idTipoCanto = idTipoCanto;
		DescTipoCanto = descTipoCanto;
		ValorTipoCanto = valorTipoCanto;
		this.cantante = cantante;
	}
	
	public CantoDTO(int id, Integer idTipoCanto,
			String descTipoCanto, Integer valorTipoCanto, int cantante) {
		super();
		this.id = id;
		this.querido = null;
		this.idTipoCanto = idTipoCanto;
		DescTipoCanto = descTipoCanto;
		ValorTipoCanto = valorTipoCanto;
		this.cantante = cantante;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean isQuerido() {
		return querido;
	}
	public void setQuerido(Boolean querido) {
		this.querido = querido;
	}
	public Integer getIdTipoCanto() {
		return idTipoCanto;
	}
	public void setIdTipoCanto(Integer idTipoCanto) {
		this.idTipoCanto = idTipoCanto;
	}
	public String getDescTipoCanto() {
		return DescTipoCanto;
	}
	public void setDescTipoCanto(String descTipoCanto) {
		DescTipoCanto = descTipoCanto;
	}
	public Integer getValorTipoCanto() {
		return ValorTipoCanto;
	}
	public void setValorTipoCanto(Integer valorTipoCanto) {
		ValorTipoCanto = valorTipoCanto;
	}
	public int getCantante() {
		return cantante;
	}
	public void setCantante(int cantante) {
		this.cantante = cantante;
	}




	public String getApodoCantante() {
		return apodoCantante;
	}




	public void setApodoCantante(String apodoCantante) {
		this.apodoCantante = apodoCantante;
	}




	public Boolean getQuerido() {
		return querido;
	}
	
	
	
}
