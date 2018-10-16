package dto;

import java.io.Serializable;
import java.util.ArrayList;

public class PartidaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -542658652968920332L;
	private Integer partidaID;
	private Integer JuegosPar;
	private Integer JuegosImpar;
	private Integer PuntosJuegoPar;
	private Integer PuntosJuegoImpar;
	private String valorEnvido1;
	private String valorEnvido2;
	private String valorEnvido3;
	private String valorEnvido4;
	
	private ArrayList<CartaDTO> cartasJugador;
	private ArrayList<CartaDTO> cartasMesa;
	
	private Integer vista; 
	private JugadorDTO jugador1;
	private JugadorDTO jugador2;
	private JugadorDTO jugador3;
	private JugadorDTO jugador4;
	
	private String chat;
	private String seniasPar;
	private String SeniasImpar;
	
	
	public PartidaDTO(Integer partidaId) {
		super();
		partidaID = partidaId;
		JuegosPar = 0;
		JuegosImpar = 0;
		PuntosJuegoPar = 0;
		PuntosJuegoImpar = 0;
		this.valorEnvido1 = "-";
		this.valorEnvido2 = "-";
		this.valorEnvido3 = "-";
		this.valorEnvido4 = "-";
		this.cartasJugador = new ArrayList<CartaDTO>() ;
		this.cartasMesa = new ArrayList<CartaDTO>() ;
		this.vista = 0;
		this.jugador1 = null;
		this.jugador2 = null;
		this.jugador3 = null;
		this.jugador4 = null;
		this.chat = "";
		this.seniasPar = "";
		this.SeniasImpar = "";
	}


	public Integer getJuegosPar() {
		return JuegosPar;
	}


	public void setJuegosPar(Integer juegosPar) {
		JuegosPar = juegosPar;
	}


	public Integer getJuegosImpar() {
		return JuegosImpar;
	}


	public void setJuegosImpar(Integer juegosImpar) {
		JuegosImpar = juegosImpar;
	}


	public Integer getPuntosJuegoPar() {
		return PuntosJuegoPar;
	}


	public void setPuntosJuegoPar(Integer puntosJuegoPar) {
		PuntosJuegoPar = puntosJuegoPar;
	}


	public Integer getPuntosJuegoImpar() {
		return PuntosJuegoImpar;
	}


	public void setPuntosJuegoImpar(Integer puntosJuegoImpar) {
		PuntosJuegoImpar = puntosJuegoImpar;
	}


	public String getValorEnvido1() {
		return valorEnvido1;
	}


	public void setValorEnvido1(String valorEnvido1) {
		this.valorEnvido1 = valorEnvido1;
	}


	public String getValorEnvido2() {
		return valorEnvido2;
	}


	public void setValorEnvido2(String valorEnvido2) {
		this.valorEnvido2 = valorEnvido2;
	}


	public String getValorEnvido3() {
		return valorEnvido3;
	}


	public void setValorEnvido3(String valorEnvido3) {
		this.valorEnvido3 = valorEnvido3;
	}


	public String getValorEnvido4() {
		return valorEnvido4;
	}


	public void setValorEnvido4(String valorEnvido4) {
		this.valorEnvido4 = valorEnvido4;
	}


	public ArrayList<CartaDTO> getCartasJugador() {
		return cartasJugador;
	}


	public void setCartasJugador(ArrayList<CartaDTO> cartasJugador) {
		this.cartasJugador = cartasJugador;
	}


	public ArrayList<CartaDTO> getCartasMesa() {
		return cartasMesa;
	}


	public void setCartasMesa(ArrayList<CartaDTO> cartasMesa) {
		this.cartasMesa = cartasMesa;
	}


	public Integer getVista() {
		return vista;
	}


	public void setVista(Integer vista) {
		this.vista = vista;
	}


	public JugadorDTO getJugador1() {
		return jugador1;
	}


	public void setJugador1(JugadorDTO jugador1) {
		this.jugador1 = jugador1;
	}


	public JugadorDTO getJugador2() {
		return jugador2;
	}


	public void setJugador2(JugadorDTO jugador2) {
		this.jugador2 = jugador2;
	}


	public JugadorDTO getJugador3() {
		return jugador3;
	}


	public void setJugador3(JugadorDTO jugador3) {
		this.jugador3 = jugador3;
	}


	public JugadorDTO getJugador4() {
		return jugador4;
	}


	public void setJugador4(JugadorDTO jugador4) {
		this.jugador4 = jugador4;
	}


	public String getChat() {
		return chat;
	}


	public void setChat(String chat) {
		this.chat = chat;
	}


	public String getSeniasPar() {
		return seniasPar;
	}


	public void setSeniasPar(String seniasPar) {
		this.seniasPar = seniasPar;
	}


	public String getSeniasImpar() {
		return SeniasImpar;
	}


	public void setSeniasImpar(String seniasImpar) {
		SeniasImpar = seniasImpar;
	}
	
	
	
	
	
	
	
}