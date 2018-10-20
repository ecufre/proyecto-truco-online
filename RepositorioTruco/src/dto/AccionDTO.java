package dto;

import java.io.Serializable;

public class AccionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1825347752050944183L;
	private PartidaDTO partida;
	private JugadorDTO jugador;
	private CartaDTO carta;
	private EnviteDTO envite;
	private String mensaje ;
	
	public AccionDTO(PartidaDTO partida ,JugadorDTO jugador, Integer valor, Boolean bool, String mensaje) {
		this.partida = partida;
		this.jugador = jugador;
		this.mensaje = jugador.getApodo() + ": " + mensaje + "\n";
	}

	public PartidaDTO getPartida() {
		return partida;
	}

	public void setPartida(PartidaDTO partida) {
		this.partida = partida;
	}

	public JugadorDTO getJugador() {
		return jugador;
	}

	public void setJugador(JugadorDTO jugador) {
		this.jugador = jugador;
	}

	public CartaDTO getCarta() {
		return carta;
	}

	public void setCarta(CartaDTO carta) {
		this.carta = carta;
	}

	public EnviteDTO getEnvite() {
		return envite;
	}

	public void setEnvite(EnviteDTO envite) {
		this.envite = envite;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
