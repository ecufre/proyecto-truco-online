package dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PartidaPantallaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -542658652968920332L;
	private Integer partidaID;
	private Boolean turno;
	private Integer JuegosNosotros;
	private Integer JuegosEllos;
	private Integer PuntosJuegoNosotros;
	private Integer PuntosJuegoEllos;
	private Integer valorEnvidoJugador;
	private Integer valorEnvidoJugadorFrente;
	private Integer valorEnvidoJugadorIquierda;
	private Integer valorEnvidoJugadorDerecha;

	private ArrayList<CartaDTO> cartasJugador;
	private ArrayList<CartaDTO> cartasMesaJugador;
	private ArrayList<CartaDTO> cartasMesaJugadorFrente;
	private ArrayList<CartaDTO> cartasMesajugadorIzquierda;
	private ArrayList<CartaDTO> cartasMesaJugadorDerecha;

	private Integer vista; 
	private JugadorDTO jugador;
	private JugadorDTO jugadorFrente;
	private JugadorDTO jugadorIzquierda;
	private JugadorDTO jugadorDerecha;
	private JugadorDTO turnoJugador;
	private LocalDateTime ultimaActualizacion;

	private CantoDTO ultimoCanto;

	private String chat;
	private String senias;



	public PartidaPantallaDTO(Integer partidaId) {
		super();
		partidaID = partidaId;
		JuegosNosotros= 0;
		JuegosEllos = 0;
		PuntosJuegoNosotros = 0;
		PuntosJuegoEllos = 0;
		this.turno=false;
		this.valorEnvidoJugador = null;
		this.valorEnvidoJugadorFrente = null;
		this.valorEnvidoJugadorIquierda= null;
		this.valorEnvidoJugadorDerecha = null;
		this.cartasJugador = new ArrayList<CartaDTO>() ;
		this.cartasMesaJugador = new ArrayList<CartaDTO>() ;
		this.cartasMesaJugadorFrente = new ArrayList<CartaDTO>() ;
		this.cartasMesajugadorIzquierda = new ArrayList<CartaDTO>() ;
		this.cartasMesaJugadorDerecha = new ArrayList<CartaDTO>() ;

		this.turnoJugador = null;
		this.ultimoCanto = null;

		this.vista = 0;
		this.jugador = null;
		this.jugadorFrente = null;
		this.jugadorDerecha = null;
		this.jugadorIzquierda = null;
		this.chat = "";
		this.senias = "";

	}


	public Integer getPartidaID() {
		return partidaID;
	}


	public void setPartidaID(Integer partidaID) {
		this.partidaID = partidaID;
	}


	public Boolean getTurno() {
		return turno;
	}


	public void setTurno(Boolean turno) {
		this.turno = turno;
	}


	public Integer getJuegosNosotros() {
		return JuegosNosotros;
	}


	public void setJuegosNosotros(Integer juegosNosotros) {
		JuegosNosotros = juegosNosotros;
	}


	public Integer getJuegosEllos() {
		return JuegosEllos;
	}


	public void setJuegosEllos(Integer juegosEllos) {
		JuegosEllos = juegosEllos;
	}


	public Integer getPuntosJuegoNosotros() {
		return PuntosJuegoNosotros;
	}


	public void setPuntosJuegoNosotros(Integer puntosJuegoNosotros) {
		PuntosJuegoNosotros = puntosJuegoNosotros;
	}


	public Integer getPuntosJuegoEllos() {
		return PuntosJuegoEllos;
	}


	public void setPuntosJuegoEllos(Integer puntosJuegoEllos) {
		PuntosJuegoEllos = puntosJuegoEllos;
	}


	public Integer getValorEnvidoJugador() {
		return valorEnvidoJugador;
	}


	public void setValorEnvidoJugador(Integer valorEnvidoJugador) {
		this.valorEnvidoJugador = valorEnvidoJugador;
	}


	public Integer getValorEnvidoJugadorFrente() {
		return valorEnvidoJugadorFrente;
	}


	public void setValorEnvidoJugadorFrente(Integer valorEnvidoJugadorFrente) {
		this.valorEnvidoJugadorFrente = valorEnvidoJugadorFrente;
	}


	public Integer getValorEnvidoJugadorIquierda() {
		return valorEnvidoJugadorIquierda;
	}


	public void setValorEnvidoJugadorIquierda(Integer valorEnvidoJugadorIquierda) {
		this.valorEnvidoJugadorIquierda = valorEnvidoJugadorIquierda;
	}


	public Integer getValorEnvidoJugadorDerecha() {
		return valorEnvidoJugadorDerecha;
	}


	public void setValorEnvidoJugadorDerecha(Integer valorEnvidoJugadorDerecha) {
		this.valorEnvidoJugadorDerecha = valorEnvidoJugadorDerecha;
	}


	public ArrayList<CartaDTO> getCartasJugador() {
		return cartasJugador;
	}


	public void setCartasJugador(ArrayList<CartaDTO> cartasJugador) {
		this.cartasJugador = cartasJugador;
	}


	public ArrayList<CartaDTO> getCartasMesaJugador() {
		return cartasMesaJugador;
	}


	public void setCartasMesaJugador(ArrayList<CartaDTO> cartasMesaJugador) {
		this.cartasMesaJugador = cartasMesaJugador;
	}


	public ArrayList<CartaDTO> getCartasMesaJugadorFrente() {
		return cartasMesaJugadorFrente;
	}


	public void setCartasMesaJugadorFrente(
			ArrayList<CartaDTO> cartasMesaJugadorFrente) {
		this.cartasMesaJugadorFrente = cartasMesaJugadorFrente;
	}


	public ArrayList<CartaDTO> getCartasMesajugadorIzquierda() {
		return cartasMesajugadorIzquierda;
	}


	public void setCartasMesajugadorIzquierda(
			ArrayList<CartaDTO> cartasMesajugadorIzquierda) {
		this.cartasMesajugadorIzquierda = cartasMesajugadorIzquierda;
	}


	public ArrayList<CartaDTO> getCartasMesaJugadorDerecha() {
		return cartasMesaJugadorDerecha;
	}


	public void setCartasMesaJugadorDerecha(
			ArrayList<CartaDTO> cartasMesaJugadorDerecha) {
		this.cartasMesaJugadorDerecha = cartasMesaJugadorDerecha;
	}


	public Integer getVista() {
		return vista;
	}


	public void setVista(Integer vista) {
		this.vista = vista;
	}


	public JugadorDTO getJugador() {
		return jugador;
	}


	public void setJugador(JugadorDTO jugador) {
		this.jugador = jugador;
	}


	public JugadorDTO getJugadorFrente() {
		return jugadorFrente;
	}


	public void setJugadorFrente(JugadorDTO jugadorFrente) {
		this.jugadorFrente = jugadorFrente;
	}


	public JugadorDTO getJugadorIzquierda() {
		return jugadorIzquierda;
	}


	public void setJugadorIzquierda(JugadorDTO jugadorIzquierda) {
		this.jugadorIzquierda = jugadorIzquierda;
	}


	public JugadorDTO getJugadorDerecha() {
		return jugadorDerecha;
	}


	public void setJugadorDerecha(JugadorDTO jugadorDerecha) {
		this.jugadorDerecha = jugadorDerecha;
	}


	public String getChat() {
		return chat;
	}


	public void setChat(String chat) {
		this.chat = chat;
	}


	public String getSenias() {
		return senias;
	}


	public void setSenias(String senias) {
		this.senias = senias;
	}


	public JugadorDTO getTurnoJugador() {
		return turnoJugador;
	}


	public void setTurnoJugador(JugadorDTO turnoJugador) {
		this.turnoJugador = turnoJugador;
	}


	public CantoDTO getUltimoCanto() {
		return ultimoCanto;
	}


	public void setUltimoCanto(CantoDTO ultimoCanto) {
		this.ultimoCanto = ultimoCanto;
	}


	public LocalDateTime getUltimaActualizacion() {
		return ultimaActualizacion;
	}


	public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}
	
}