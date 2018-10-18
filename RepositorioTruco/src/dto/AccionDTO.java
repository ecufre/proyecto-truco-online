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
	private Boolean respuesta;
	private Boolean mostrarValoresEnvido;
	private String mensaje ;
	public AccionDTO(int partida ,String apodoJugador, Integer valor,Boolean bool,String mensaje) {
		super();
		this.partida = partida;
		this.apodoJugador = apodoJugador;
		this.valor = valor;
		this.respuesta=bool;
		this.mostrarValoresEnvido=false;
		this.mensaje = apodoJugador+": "+mensaje+"\n";
		
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
	public Boolean getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Boolean respuesta) {
		this.respuesta = respuesta;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Boolean getMostrarValoresEnvido() {
		return mostrarValoresEnvido;
	}
	public void setMostrarValoresEnvido(Boolean mostrarValoresEnvido) {
		this.mostrarValoresEnvido = mostrarValoresEnvido;
	}
	
	
	
}
