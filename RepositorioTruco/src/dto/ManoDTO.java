package dto;

import java.io.Serializable;
import java.util.ArrayList;



public class ManoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 343210665498402004L;
	private Integer id;
	private Integer numeroMano;
	private ArrayList<CantoDTO> cantos;
	private ArrayList<CartaDTO> cartas;
	private ArrayList<BazaDTO> bazas;
	private Integer[] envidoValor;
	
	
	
	
	
	public ManoDTO() { }
	
	
	public ManoDTO(Integer id, Integer numeroMano, ArrayList<CantoDTO> cantos,
			ArrayList<CartaDTO> cartas, ArrayList<BazaDTO> bazas,
			Integer[] envidoValor) {
		super();
		this.id = id;
		this.numeroMano = numeroMano;
		this.cantos = cantos;
		this.cartas = cartas;
		this.bazas = bazas;
		this.envidoValor = envidoValor;
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
	public ArrayList<CantoDTO> getCantos() {
		return cantos;
	}
	public void setCantos(ArrayList<CantoDTO> cantos) {
		this.cantos = cantos;
	}
	public ArrayList<CartaDTO> getCartas() {
		return cartas;
	}
	public void setCartas(ArrayList<CartaDTO> cartas) {
		this.cartas = cartas;
	}
	public ArrayList<BazaDTO> getBazas() {
		return bazas;
	}
	public void setBazas(ArrayList<BazaDTO> bazas) {
		this.bazas = bazas;
	}
	public Integer[] getEnvidoValor() {
		return envidoValor;
	}
	public void setEnvidoValor(Integer[] envidoValor) {
		this.envidoValor = envidoValor;
	}
	
	
	
}
