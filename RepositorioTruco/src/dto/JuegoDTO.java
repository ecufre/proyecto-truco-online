package dto;

import java.io.Serializable;
import java.util.ArrayList;



public class JuegoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6148926054697760069L;
	private int id;
	private int puntajePar;
	private int puntajeImpar;
	private boolean finalizado;
	private ArrayList<ManoDTO> manos;
	
	public JuegoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public JuegoDTO(int id, int puntajePar, int puntajeImpar,
			boolean finalizado, ArrayList<ManoDTO> manos) {
		super();
		this.id = id;
		this.puntajePar = puntajePar;
		this.puntajeImpar = puntajeImpar;
		this.finalizado = finalizado;
		this.manos = manos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ArrayList<ManoDTO> getManos() {
		return manos;
	}

	public void setManos(ArrayList<ManoDTO> manos) {
		this.manos = manos;
	}
	
	
	
	
}
