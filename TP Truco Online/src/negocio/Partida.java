package negocio;

import java.util.ArrayList;

import dto.JugadorDTO;
import dto.PartidaDTO;
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
	
	
	// Creacion y preparacion de partida
	public Partida(boolean esAbierta) {
		this.id = 1; // terminar
		this.jugadores = new ArrayList<Jugador>();
		this.jugadoresListos = new ArrayList<Jugador>();
		this.esAbierta = esAbierta;
		this.estado = EstadoPartida.Pendiente;
		this.ganador = null;
		this.juegos = new ArrayList<Juego>();
		Juego juegoActual = new Juego();
		this.juegos.add(juegoActual);
		juegoActual.crearMano();
		juegoActual.grabar();
		this.charla = null;
		
	}
	
	public void grabar() {
		//TODO Grabar
	}

	public void jugadorListo(Jugador j) {
		for (int i = 0; i < this.jugadoresListos.size(); i++) {
			if (this.jugadoresListos.get(i).getApodo().equals(j.getApodo())) return;
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
	
	public void actualizarEsatdoPartida(){
		this.getJuegoActual().actualizarJuego(); //TODO Revisar cuando este el conteo de envites
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
					juegoActual.grabar();
				}	
			}
			//Un juego completo creo un juevo nuevo
			else {
				Juego juegoActual = new Juego();
				this.juegos.add(juegoActual);
				juegoActual.grabar();
			}
		}
		//Si se termino la mano, creo una nueva mano.
		else if (this.getJuegoActual().manoCompleta()) this.getJuegoActual().crearMano();
	}
	
	public void cantarEnvite(Jugador jugador, TipoCanto canto) throws ComunicacionException{
		this.getJuegoActual().cantarEnvite(this.ubicacionJugador(jugador), canto);
	}
	
	public void responderEnvite(Jugador jugador, Boolean respuesta) throws ComunicacionException {
		this.getJuegoActual().responderEnvite(this.ubicacionJugador(jugador), respuesta);
	}

	public EstadoPartida getEstado() {
		return estado;
	}

	public int getId() {
		return id;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public PartidaDTO toDTO_reducido() {
		return null;//TODO Grabar
	}

	public Integer getGanador() {
		return ganador;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public PartidaDTO toDTO(int partida, Jugador j2 , Boolean ptosEnvido) throws ComunicacionException {
	
		boolean par=true;
		
		
		if(j2!=null){
			PartidaDTO pd = new PartidaDTO(partida);
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
			if(j.getPuntajePar()<j.getPuntajeImpar()){
				
				juegosImpar++;
			}else{
			
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
			JugadorDTO jd = new JugadorDTO(j.getApodo(), null);
			
			switch (this.ubicacionJugador(j2)){
			case 1:
				pd.setCartasMesaJugadorFrente(this.getJuegoActual().mostrarCartasMesa(3));
				pd.setCartasMesajugadorIzquierda(this.getJuegoActual().mostrarCartasMesa(2));
				pd.setCartasMesaJugadorDerecha(this.getJuegoActual().mostrarCartasMesa(4));
				if(ptosEnvido){
					pd.setValorEnvidoJugadorFrente(3);
					pd.setValorEnvidoJugadorIquierda(2);
					pd.setValorEnvidoJugadorDerecha(4);
				}
				
			break;
			case 2:
				pd.setCartasMesaJugadorFrente(this.getJuegoActual().mostrarCartasMesa(4));
				pd.setCartasMesajugadorIzquierda(this.getJuegoActual().mostrarCartasMesa(3));
				pd.setCartasMesaJugadorDerecha(this.getJuegoActual().mostrarCartasMesa(1));
				if(ptosEnvido){
					pd.setValorEnvidoJugadorFrente(4);
					pd.setValorEnvidoJugadorIquierda(3);
					pd.setValorEnvidoJugadorDerecha(1);
				}
			break;
			case 3:
				pd.setCartasMesaJugadorFrente(this.getJuegoActual().mostrarCartasMesa(1));
				pd.setCartasMesajugadorIzquierda(this.getJuegoActual().mostrarCartasMesa(4));
				pd.setCartasMesaJugadorDerecha(this.getJuegoActual().mostrarCartasMesa(2));
				if(ptosEnvido){
					pd.setValorEnvidoJugadorFrente(1);
					pd.setValorEnvidoJugadorIquierda(4);
					pd.setValorEnvidoJugadorDerecha(2);
				}
			break;
			case 4:
				pd.setCartasMesaJugadorFrente(this.getJuegoActual().mostrarCartasMesa(2));
				pd.setCartasMesajugadorIzquierda(this.getJuegoActual().mostrarCartasMesa(1));
				pd.setCartasMesaJugadorDerecha(this.getJuegoActual().mostrarCartasMesa(3));
				if(ptosEnvido){
					pd.setValorEnvidoJugadorFrente(2);
					pd.setValorEnvidoJugadorIquierda(1);
					pd.setValorEnvidoJugadorDerecha(3);
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
