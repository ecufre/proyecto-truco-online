package dto;

import java.io.Serializable;
import java.util.ArrayList;



public class BazaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6761437354769500725L;

	private int id;
	private ArrayList<CartaDTO> cartasbaza;
	private Integer ganadorBaza;
	private int turno;
	private int mano;
	private boolean parda;
	
	public BazaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BazaDTO(int id, ArrayList<CartaDTO> cartasbaza, Integer ganadorBaza,
			int turno, int mano, boolean parda) {
		super();
		this.id = id;
		this.cartasbaza = cartasbaza;
		this.ganadorBaza = ganadorBaza;
		this.turno = turno;
		this.mano = mano;
		this.parda = parda;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<CartaDTO> getCartasbaza() {
		return cartasbaza;
	}

	public void setCartasbaza(ArrayList<CartaDTO> cartasbaza) {
		this.cartasbaza = cartasbaza;
	}

	public Integer getGanadorBaza() {
		return ganadorBaza;
	}

	public void setGanadorBaza(Integer ganadorBaza) {
		this.ganadorBaza = ganadorBaza;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public int getMano() {
		return mano;
	}

	public void setMano(int mano) {
		this.mano = mano;
	}

	public boolean isParda() {
		return parda;
	}

	public void setParda(boolean parda) {
		this.parda = parda;
	}
	
	
	
	
	
}
