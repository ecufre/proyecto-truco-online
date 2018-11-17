package negocio;


import java.util.ArrayList;

import org.joda.time.LocalDateTime;

import dao.PartidaDAO;
import dto.JuegoDTO;
import dto.JugadorDTO;
import dto.PartidaDTO;
import dto.PartidaPantallaDTO;
import enumeraciones.EstadoPartida;
import enumeraciones.TipoCanto;
import excepciones.ComunicacionException;

public class Partida {
	private int id;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Jugador> jugadoresListos;
	private boolean esAbierta;
	private EstadoPartida estado;
	private Integer ganador;
	private ArrayList<Juego> juegos;
	private String charla;
	private LocalDateTime fechaCreacion; //TODO
	private LocalDateTime fechaActualizacion; //TODO


	// Creacion y preparacion de partida
	public Partida(boolean esAbierta) throws ComunicacionException {
		this.jugadores = new ArrayList<Jugador>();
		this.jugadoresListos = new ArrayList<Jugador>();
		this.esAbierta = esAbierta;
		this.estado = EstadoPartida.Pendiente;
		this.ganador = null;
		this.juegos = new ArrayList<Juego>();
		Juego juegoActual = new Juego();
		this.juegos.add(juegoActual);
		juegoActual.crearMano();
		juegoActual.crear();
		this.charla = null;
		this.fechaCreacion = LocalDateTime.now();
		this.fechaActualizacion = LocalDateTime.now();
	}

	public Partida(int id) {
		this.id = id;
		this.jugadores = new ArrayList<Jugador>();
		this.jugadoresListos = new ArrayList<Jugador>();
		this.juegos = new ArrayList<Juego>();
	}

	public int getId() {
		return id;
	}
	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	public ArrayList<Jugador> getJugadoresListos() {
		return jugadoresListos;
	}
	public void setJugadoresListos(ArrayList<Jugador> jugadoresListos) {
		this.jugadoresListos = jugadoresListos;
	}
	public boolean isEsAbierta() {
		return esAbierta;
	}
	public void setEsAbierta(boolean esAbierta) {
		this.esAbierta = esAbierta;
	}
	public ArrayList<Juego> getJuegos() {
		return juegos;
	}
	public void setJuegos(ArrayList<Juego> juegos) {
		this.juegos = juegos;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEstado(EstadoPartida estado) {
		this.estado = estado;
	}
	public void setGanador(Integer ganador) {
		this.ganador = ganador;
	}

	public void grabar() {
		PartidaDAO.getInstancia().grabar(this);
	}

	public void crear() throws ComunicacionException {
		Integer id = PartidaDAO.getInstancia().crear(this);
		if (id != null) this.id = id;
		else throw new ComunicacionException("Hubo un error al generar una nueva partida");
	}

	public void jugadorListo(Jugador j) throws ComunicacionException {
		for (int i = 0; i < this.jugadoresListos.size(); i++) {
			if (this.jugadoresListos.get(i).getApodo().equals(j.getApodo())) throw new ComunicacionException("El jugador ya estaba listo para jugar esta partida");
		}
		this.jugadoresListos.add(j);
		if (this.jugadoresListos.size() == 4) this.estado = EstadoPartida.EnCurso;
	}

	//Metodos privados ya revisados

	private int ubicacionJugador(Jugador j) throws ComunicacionException {
		for (int i = 0; i < this.jugadores.size(); i++) 
			if (this.jugadores.get(i).getApodo().equals(j.getApodo())) return (i + 1);
		throw new ComunicacionException("Jugador no pertenece a la partida");
	}

	private int calcularGanadorMano(int ubicacion) {
		if (ubicacion == 4) return 3;
		else return (ubicacion + 1);
	}

	private Juego getJuegoActual() {
		return this.juegos.get(this.juegos.size() - 1);
	}

	//----------------Metodos del Juego-----------------------------------------

	public void jugarCarta(Jugador j, int idCarta) throws ComunicacionException {
		this.getJuegoActual().jugarCarta(this.ubicacionJugador(j), idCarta);
		this.actualizarEsatdoPartida();
	}

	public void retiraseMano(Jugador j) throws ComunicacionException{
		int ganadorMano = this.calcularGanadorMano(this.ubicacionJugador(j));
		this.getJuegoActual().retirarseMano(ganadorMano);
		this.actualizarEsatdoPartida();
	}

	public void actualizarEsatdoPartida() throws ComunicacionException{
		this.getJuegoActual().actualizarJuego();
		this.getJuegoActual().grabar();

		//Analizo que hacer si se termino el juego
		if (this.getJuegoActual().isFinalizado()) {
			//Tres juegos completos
			if (this.juegos.size() == 3) {
				this.estado = EstadoPartida.Finalizada;
				if (this.juegos.get(2).getPuntajeImpar() > this.juegos.get(2).getPuntajePar()) this.ganador = 1;
				else this.ganador = 2;
			}
			//Dos juegos completos
			else if (this.juegos.size() == 2) {
				//Ambos ganados por el mismo equipo, finalizo la partida
				if (this.juegos.get(0).getPuntajeImpar() > this.juegos.get(0).getPuntajePar() == this.juegos.get(1).getPuntajeImpar() > this.juegos.get(1).getPuntajePar()) {
					this.estado = EstadoPartida.Finalizada;
					if (this.juegos.get(0).getPuntajeImpar() > this.juegos.get(0).getPuntajePar()) this.ganador = 1;
					else this.ganador = 2;
				}
				//Ganados por equipos distintos, creo un nuevo juego.
				else {
					Juego juegoActual = new Juego();
					this.juegos.add(juegoActual);
					juegoActual.crearMano();
					juegoActual.crear();
				}	
			}
			//Un juego completo creo un juevo nuevo
			else {
				Juego juegoActual = new Juego();
				this.juegos.add(juegoActual);
				juegoActual.crearMano();
				juegoActual.crear();
			}
		}
		//Si se termino la mano, creo una nueva mano.
		else if (this.getJuegoActual().manoCompleta()) {
			this.getJuegoActual().crearMano();
			this.getJuegoActual().grabar();
		}
	}

	public void cantarEnvite(Jugador jugador, TipoCanto canto) throws ComunicacionException{
		this.getJuegoActual().cantarEnvite(this.ubicacionJugador(jugador), canto);
	}

	public void responderEnvite(Jugador jugador, TipoCanto tipoCanto, Boolean respuesta, Boolean mostrarPuntos) throws ComunicacionException {
		this.getJuegoActual().responderEnvite(this.ubicacionJugador(jugador), tipoCanto, respuesta, mostrarPuntos);
		if (tipoCanto.getId() > 4 && ! respuesta) this.actualizarEsatdoPartida();
	}

	public EstadoPartida getEstado() {
		return estado;
	}

	public Integer getGanador() {
		return ganador;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public PartidaDTO toDTO() {
		ArrayList<JugadorDTO> jugadores = new ArrayList<JugadorDTO>();
		ArrayList<JuegoDTO> juegos  =new ArrayList<JuegoDTO>();
		for(Jugador j : this.getJugadores()){
			jugadores.add(j.toDTO_reducido());
		}
		for(Juego jj : this.getJuegos()){
			juegos.add(jj.toDTO());
		}
		PartidaDTO  pdto= new PartidaDTO(this.getId(),jugadores,this.isEsAbierta(),this.getEstado(), this.getGanador(),juegos,charla);
		org.joda.time.LocalDateTime jodaTime = this.fechaActualizacion;
		java.time.LocalDateTime javaTime = java.time.LocalDateTime.of(jodaTime.getYear(), jodaTime.getMonthOfYear(), jodaTime.getDayOfMonth(), jodaTime.getHourOfDay(), jodaTime.getMinuteOfHour(), jodaTime.getSecondOfMinute());
		pdto.setFechaActualizacion(javaTime);
		jodaTime = this.fechaCreacion;
		javaTime = java.time.LocalDateTime.of(jodaTime.getYear(), jodaTime.getMonthOfYear(), jodaTime.getDayOfMonth(), jodaTime.getHourOfDay(), jodaTime.getMinuteOfHour(), jodaTime.getSecondOfMinute());
		pdto.setFechaCreacion(javaTime);
		return pdto;
	}
	
	public PartidaDTO toDTOReducido() {
		PartidaDTO pdto = new PartidaDTO();
		pdto.setId(this.id);
		pdto.setEstado(this.estado);
		org.joda.time.LocalDateTime jodaTime = this.fechaActualizacion;
		java.time.LocalDateTime javaTime = java.time.LocalDateTime.of(jodaTime.getYear(), jodaTime.getMonthOfYear(), jodaTime.getDayOfMonth(), jodaTime.getHourOfDay(), jodaTime.getMinuteOfHour(), jodaTime.getSecondOfMinute());
		pdto.setFechaActualizacion(javaTime);
		jodaTime = this.fechaCreacion;
		javaTime = java.time.LocalDateTime.of(jodaTime.getYear(), jodaTime.getMonthOfYear(), jodaTime.getDayOfMonth(), jodaTime.getHourOfDay(), jodaTime.getMinuteOfHour(), jodaTime.getSecondOfMinute());
		pdto.setFechaCreacion(javaTime);
		if (this.estado == EstadoPartida.EnCurso) pdto.setTurnoJugador(this.jugadores.get(this.getJuegoActual().getManoActual().getBazaActual().getTurno() - 1).toDTO_reducido());
		return 	pdto;
	}


	public PartidaPantallaDTO toPantallaDTO(int partida, Jugador j2 , Boolean ptosEnvido) throws ComunicacionException {
		if (this.estado == EstadoPartida.Pendiente) throw new ComunicacionException("La partida aun no ha comenzado");
		if (this.estado == EstadoPartida.Finalizada) throw new ComunicacionException("La partida ha finalizado");
		boolean par=true;


		if(j2!=null){
			PartidaPantallaDTO pd = new PartidaPantallaDTO(partida);
			org.joda.time.LocalDateTime jodaTime = this.fechaActualizacion;
			java.time.LocalDateTime javaTime = java.time.LocalDateTime.of(jodaTime.getYear(), jodaTime.getMonthOfYear(), jodaTime.getDayOfMonth(), jodaTime.getHourOfDay(), jodaTime.getMinuteOfHour(), jodaTime.getSecondOfMinute());
			pd.setUltimaActualizacion(javaTime);
			pd.setJugador(j2.toDTO());

			pd.setTurnoJugador(this.jugadores.get(this.getJuegoActual().getManoActual().getBazaActual().getTurno() - 1).toDTO_reducido());
			if (this.getJuegoActual().getManoActual().getUltimoCanto() != null) {
				pd.setUltimoCanto(this.getJuegoActual().getManoActual().getUltimoCanto().toDTO());
				pd.getUltimoCanto().setApodoCantante(this.jugadores.get(pd.getUltimoCanto().getCantante() - 1).getApodo());
			}
			//pd.setChat(this.getCharla()); queda para la proxima entrega
			pd.setCartasJugador(this.getJuegoActual().mostrarCartasJugador(this.ubicacionJugador(j2)));
			if(ptosEnvido){
				pd.setValorEnvidoJugador(this.getJuegoActual().mostrarPuntosEnvido(this.ubicacionJugador(j2)));	
			}
			pd.setCartasMesaJugador(this.getJuegoActual().mostrarCartasMesa(this.ubicacionJugador(j2)));
			pd.setTurno(this.ubicacionJugador(j2)==this.getJuegoActual().getTurno());
			if(this.ubicacionJugador(j2)==1||this.ubicacionJugador(j2)==3){
				par=false;
			}else{
				par=true;
			}


			int juegosImpar = 0,juegosPar = 0;
			for(Juego j : juegos){
				if(j.getPuntajePar()<j.getPuntajeImpar() && j.isFinalizado()){

					juegosImpar++;
				}
				if(j.getPuntajePar()>j.getPuntajeImpar() && j.isFinalizado()){

					juegosPar++;
				}
			}

			if(par){
				pd.setJuegosNosotros(juegosPar);
				pd.setJuegosEllos(juegosImpar);
				pd.setPuntosJuegoNosotros(this.getJuegoActual().getPuntajePar());
				pd.setPuntosJuegoEllos(this.getJuegoActual().getPuntajeImpar());
				//pd.setSenias(this.getJuegoActual().mostrarSeniasPar());
			}else{
				pd.setJuegosNosotros(juegosImpar);
				pd.setJuegosEllos(juegosPar);
				pd.setPuntosJuegoNosotros(this.getJuegoActual().getPuntajeImpar());
				pd.setPuntosJuegoEllos(this.getJuegoActual().getPuntajePar());
				//pd.setSenias(this.getJuegoActual().mostrarSeniasImpar());

			}



			for(Jugador j:jugadores){

				switch (this.ubicacionJugador(j2)){
				case 1:
					pd.setJugadorFrente(this.jugadores.get(2).toDTO_reducido());
					pd.setJugadorIzquierda(this.jugadores.get(1).toDTO_reducido());
					pd.setJugadorDerecha(this.jugadores.get(3).toDTO_reducido());
					pd.setCartasMesaJugadorFrente(this.getJuegoActual().mostrarCartasMesa(3));
					pd.setCartasMesajugadorIzquierda(this.getJuegoActual().mostrarCartasMesa(2));
					pd.setCartasMesaJugadorDerecha(this.getJuegoActual().mostrarCartasMesa(4));
					if(ptosEnvido){
						pd.setValorEnvidoJugadorFrente(this.getJuegoActual().mostrarPuntosEnvido(3));
						pd.setValorEnvidoJugadorIquierda(this.getJuegoActual().mostrarPuntosEnvido(2));
						pd.setValorEnvidoJugadorDerecha(this.getJuegoActual().mostrarPuntosEnvido(4));
					}

					break;
				case 2:
					pd.setJugadorFrente(this.jugadores.get(3).toDTO_reducido());
					pd.setJugadorIzquierda(this.jugadores.get(2).toDTO_reducido());
					pd.setJugadorDerecha(this.jugadores.get(0).toDTO_reducido());
					pd.setCartasMesaJugadorFrente(this.getJuegoActual().mostrarCartasMesa(4));
					pd.setCartasMesajugadorIzquierda(this.getJuegoActual().mostrarCartasMesa(3));
					pd.setCartasMesaJugadorDerecha(this.getJuegoActual().mostrarCartasMesa(1));
					if(ptosEnvido){
						pd.setValorEnvidoJugadorFrente(this.getJuegoActual().mostrarPuntosEnvido(4));
						pd.setValorEnvidoJugadorIquierda(this.getJuegoActual().mostrarPuntosEnvido(3));
						pd.setValorEnvidoJugadorDerecha(this.getJuegoActual().mostrarPuntosEnvido(1));
					}
					break;
				case 3:
					pd.setJugadorFrente(this.jugadores.get(0).toDTO_reducido());
					pd.setJugadorIzquierda(this.jugadores.get(3).toDTO_reducido());
					pd.setJugadorDerecha(this.jugadores.get(1).toDTO_reducido());
					pd.setCartasMesaJugadorFrente(this.getJuegoActual().mostrarCartasMesa(1));
					pd.setCartasMesajugadorIzquierda(this.getJuegoActual().mostrarCartasMesa(4));
					pd.setCartasMesaJugadorDerecha(this.getJuegoActual().mostrarCartasMesa(2));
					if(ptosEnvido){
						pd.setValorEnvidoJugadorFrente(this.getJuegoActual().mostrarPuntosEnvido(1));
						pd.setValorEnvidoJugadorIquierda(this.getJuegoActual().mostrarPuntosEnvido(4));
						pd.setValorEnvidoJugadorDerecha(this.getJuegoActual().mostrarPuntosEnvido(2));
					}
					break;
				case 4:
					pd.setJugadorFrente(this.jugadores.get(1).toDTO_reducido());
					pd.setJugadorIzquierda(this.jugadores.get(0).toDTO_reducido());
					pd.setJugadorDerecha(this.jugadores.get(2).toDTO_reducido());
					pd.setCartasMesaJugadorFrente(this.getJuegoActual().mostrarCartasMesa(2));
					pd.setCartasMesajugadorIzquierda(this.getJuegoActual().mostrarCartasMesa(1));
					pd.setCartasMesaJugadorDerecha(this.getJuegoActual().mostrarCartasMesa(3));
					if(ptosEnvido){
						pd.setValorEnvidoJugadorFrente(this.getJuegoActual().mostrarPuntosEnvido(2));
						pd.setValorEnvidoJugadorIquierda(this.getJuegoActual().mostrarPuntosEnvido(1));
						pd.setValorEnvidoJugadorDerecha(this.getJuegoActual().mostrarPuntosEnvido(3));
					}
					break;
				}

			}



			return pd;
		}else{
			return null;
		}
	}
}
