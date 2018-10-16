package dto;

import java.io.Serializable;

public class AccionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1825347752050944183L;
	private int partida;
	private String apodoJugador;
	private Integer valor;
	public AccionDTO(int partida ,String apodoJugador, Integer valor) {
		super();
		this.partida = partida;
		this.apodoJugador = apodoJugador;
		this.valor = valor;
	}
	public String getApodoJugador() {
		return apodoJugador;
	}
	public void setApodoJugador(String apodoJugador) {
		this.apodoJugador = apodoJugador;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public int getPartida() {
		return partida;
	}
	public void setPartida(int partida) {
		this.partida = partida;
	}
	
	
	
}
