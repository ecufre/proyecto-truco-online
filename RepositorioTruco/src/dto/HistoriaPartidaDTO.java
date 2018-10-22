package dto;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoriaPartidaDTO implements Serializable{
			
	/**
	 * 
	 */
	private static final long serialVersionUID = -1614348147473656949L;
	private ArrayList<CartaDTO> cartas;
	
	
	
	public HistoriaPartidaDTO() {
		super();
	
	}
	public ArrayList<CartaDTO> getCartas() {
		return cartas;
	}
	public void setCartas(ArrayList<CartaDTO> cartas) {
		this.cartas = cartas;
	}
	
	
	
}
