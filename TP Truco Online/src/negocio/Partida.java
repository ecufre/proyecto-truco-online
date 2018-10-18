package negocio;

import java.util.ArrayList;

import dto.CartaDTO;
import dto.HistoriaPartidaDTO;
import dto.JugadorDTO;
import dto.PartidaDTO;
import enumeraciones.EstadoPartida;
import enumeraciones.TipoCanto;

public class Partida {
	private int id;
	private ArrayList<Jugador> jugadores;
	private boolean[] jugadoresListos;
	private boolean esAbierta;
	private EstadoPartida estado;
	private Integer ganador;
	private ArrayList<Juego> juegos;
	private Juego juegoActual;
	private String charla;
	
	
	
	public Partida(boolean esAbierta ) {
		super();
		this.id = 1; // terminar
		this.jugadores = new ArrayList<Jugador>();
		this.jugadoresListos = new boolean[4];
		
		jugadoresListos[0]=false;
		jugadoresListos[1]=false;
		jugadoresListos[2]=false;
		jugadoresListos[3]=false;
		
		this.esAbierta = esAbierta;
		this.estado = EstadoPartida.Pendiente;
		this.ganador = null;
		this.juegos = new ArrayList<Juego>();
		this.juegoActual = new Juego();
		this.charla = null;
	
		
	}







	public boolean jugadorListos(String jug) {
		boolean respuesta=false;
		Jugador j= this.buscarJugadorApodo(jug);
		if(j!=null){
			this.jugadoresListos[j.getUbicacion()-1]=true;
		}
		for(int i =0;i<4;i++){
			respuesta  = this.jugadoresListos[i];
		}
		
		if(respuesta==true){
			this.estado=EstadoPartida.EnCurso;
		}
		
		return respuesta;
	}
	
	
	//----------------Metodos del Juego-----------------------------------------
	public void nuevoJuego(){
			
		juegoActual = new Juego();
	}
	
	public void nuevaMano(){
		juegoActual.crearMano(jugadores);
	}
	
	public void jugarCarta(String jugador, int carta){
		juegoActual.jugarCarta(jugador, carta);
	}
	
	public void retiraseMano(String jugador){
		Jugador j= this.buscarJugadorApodo(jugador);
		if(j!=null){
				int sig = this.siguiente(j.getUbicacion());
				j=this.buscarJugadorPosicion(sig);
				if(j!=null){
					juegoActual.retirarseMano(j);
				}
		}
	}


	public void cantarEnvite(String jugador, int canto){
			Jugador j = this.buscarJugadorApodo(jugador);
			if(j!=null){
			juegoActual.cantarEnvite(j.getUbicacion(), canto);
			}
		}
	

	public void responderEnvite(String apodoJugador, Boolean respuesta) {
		Jugador j = this.buscarJugadorApodo(apodoJugador);
		if(j!=null){
			juegoActual.responderEnvite(j.getUbicacion(), respuesta);
				
		}
		}
	
	
	public void actualizarPartida(){
			this.getJuegoActual().actualizarJuego();
			
			if(this.juegoActual.isFinalizado()){
				this.juegos.add(this.juegoActual);
				this.juegoActual= new Juego();
				int juegosImpar =0, juegosPar=0;
				for(Juego j : juegos){
					if(j.getPuntajePar()<j.getPuntajeImpar()){
						juegosImpar++;
					}else{
						juegosPar++;
					}
				}
				if(juegosImpar ==2 ){
					this.estado = EstadoPartida.Finalizada;
					this.setGanador(1);
				}
				if(juegosPar==2){
					this.estado = EstadoPartida.Finalizada;
					this.setGanador(2);
				}
			
			}else{
				if(this.juegoActual.manoCompleta()){
					this.juegoActual.crearMano(jugadores);
				}
				
			}
			
		}
	
		
	private int siguiente(Integer ubicacion) {
		 if(ubicacion ==4){
          	ubicacion--;
          }else{
          	ubicacion++;
          }
		return ubicacion;
	}
	
	private Jugador buscarJugadorApodo(String jugador) {
		  for(Jugador jj:this.jugadores){	
	             if(jj.getApodo().equals(jugador)){
	             return jj;
	             }}
		return null;
	
	}
	
	
	
	private Jugador buscarJugadorPosicion(int ubicacion) {
		  for(Jugador jj:this.jugadores){	
	             if(jj.getUbicacion()==ubicacion){
	             return jj;
	             }}
		return null;
	
	}




	//----------------getters and setters----------------------------------------


	public int getId() {
		return id;
	}







	public void setId(int id) {
		this.id = id;
	}







	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}







	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}



	public boolean isEsAbierta() {
		return esAbierta;
	}







	public void setEsAbierta(boolean esAbierta) {
		this.esAbierta = esAbierta;
	}







	public EstadoPartida getEstado() {
		return estado;
	}







	public void setEstado(EstadoPartida estado) {
		this.estado = estado;
	}







	public int getGanador() {
		return ganador;
	}







	public void setGanador(int ganador) {
		this.ganador = ganador;
	}







	public ArrayList<Juego> getJuegos() {
		return juegos;
	}







	public void setJuegos(ArrayList<Juego> juegos) {
		this.juegos = juegos;
	}







	public Juego getJuegoActual() {
		return juegoActual;
	}







	public void setJuegoActual(Juego juegoActual) {
		this.juegoActual = juegoActual;
	}


	




	public boolean[] getJugadoresListos() {
		return jugadoresListos;
	}







	public void setJugadoresListos(boolean[] jugadoresListos) {
		this.jugadoresListos = jugadoresListos;
	}







	public String getCharla() {
		return charla;
	}







	public void setCharla(String charla) {
		this.charla = charla;
	}


	public void setGanador(Integer ganador) {
		this.ganador = ganador;
	}



	public void nuevoMesnaje(String mensaje){
		this.charla= charla + mensaje;
	}



	public void agregarJugador(Jugador j1, int i) {
		Jugador j = new Jugador(j1.getApodo(), j1.getEmail(), j1.getPassword(), j1.getLoggedSession());
		j.setUbicacion(i);
		this.jugadores.add(j);
	}

	public void senia(String apodoJugador, Integer carta) {
		Jugador j= this.buscarJugadorApodo(apodoJugador);
		if(j!=null){
			
		juegoActual.senia(j.getUbicacion(),j.getApodo(),carta);
		}
	}







	public PartidaDTO toDTO(int partida, String jugador , Boolean ptosEnvido) {
		Jugador jj=this.buscarJugadorApodo(jugador);
		boolean par=true;
		
		
		if(jj!=null){
			PartidaDTO pd = new PartidaDTO(partida);
			pd.setChat(this.getCharla());
			pd.setCartasJugador(this.juegoActual.mostrarCartasJugador(jj.getApodo()));
			if(ptosEnvido){
			pd.setValorEnvidoJugador(this.juegoActual.mostrarPuntosEnvido(jj.getUbicacion()));	
			}
			pd.setCartasMesaJugador(this.juegoActual.mostrarCartasMesa(jj.getUbicacion()));
			pd.setTurno((jj.getUbicacion()==this.juegoActual.getTurno()));
		if(jj.getUbicacion()==1||jj.getUbicacion()==3){
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
			pd.setPuntosJuegoNosotros(this.juegoActual.getPuntajePar());
			pd.setPuntosJuegoEllos(this.juegoActual.getPuntajeImpar());
			pd.setSenias(this.juegoActual.mostrarSeniasPar());
		}else{
			pd.setJuegosNosotros(juegosImpar);
			pd.setJuegosEllos(juegosPar);
			pd.setPuntosJuegoNosotros(this.juegoActual.getPuntajeImpar());
			pd.setPuntosJuegoEllos(this.juegoActual.getPuntajePar());
			pd.setSenias(this.juegoActual.mostrarSeniasImpar());
			
		}
		
		
		
		for(Jugador j:jugadores){
			JugadorDTO jd = new JugadorDTO(j.getApodo(), null);
			
			switch (j.getUbicacion()){
			case 1:
				pd.setCartasMesaJugadorFrente(this.juegoActual.mostrarCartasMesa(3));
				pd.setCartasMesajugadorIzquierda(this.juegoActual.mostrarCartasMesa(2));
				pd.setCartasMesaJugadorDerecha(this.juegoActual.mostrarCartasMesa(4));
				if(ptosEnvido){
					pd.setValorEnvidoJugadorFrente(3);
					pd.setValorEnvidoJugadorIquierda(2);
					pd.setValorEnvidoJugadorDerecha(4);
				}
				
			break;
			case 2:
				pd.setCartasMesaJugadorFrente(this.juegoActual.mostrarCartasMesa(4));
				pd.setCartasMesajugadorIzquierda(this.juegoActual.mostrarCartasMesa(3));
				pd.setCartasMesaJugadorDerecha(this.juegoActual.mostrarCartasMesa(1));
				if(ptosEnvido){
					pd.setValorEnvidoJugadorFrente(4);
					pd.setValorEnvidoJugadorIquierda(3);
					pd.setValorEnvidoJugadorDerecha(1);
				}
			break;
			case 3:
				pd.setCartasMesaJugadorFrente(this.juegoActual.mostrarCartasMesa(1));
				pd.setCartasMesajugadorIzquierda(this.juegoActual.mostrarCartasMesa(4));
				pd.setCartasMesaJugadorDerecha(this.juegoActual.mostrarCartasMesa(2));
				if(ptosEnvido){
					pd.setValorEnvidoJugadorFrente(1);
					pd.setValorEnvidoJugadorIquierda(4);
					pd.setValorEnvidoJugadorDerecha(2);
				}
			break;
			case 4:
				pd.setCartasMesaJugadorFrente(this.juegoActual.mostrarCartasMesa(2));
				pd.setCartasMesajugadorIzquierda(this.juegoActual.mostrarCartasMesa(1));
				pd.setCartasMesaJugadorDerecha(this.juegoActual.mostrarCartasMesa(3));
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







	public HistoriaPartidaDTO mostrarHistoria() {
		HistoriaPartidaDTO hp=new HistoriaPartidaDTO();
		ArrayList<CartaDTO> cds=new ArrayList<CartaDTO>();
		
		for(Juego j:this.getJuegos()){
			for(Mano m:j.getManos()){
				for(Baza b : m.getBazas()){
					for(Carta c : b.getCartasbaza()){
						cds.add(c.toDTOHistoria());
					}
				}
			}
		}
		hp.setCartas(cds);
		return hp;
	}



	
	public PartidaDTO toDTO() {
		//TODO Devuelve todo el DTO completo
		return null;
	}
	public PartidaDTO toDTO_reducido() {
		//TODO Arma un dto reducido, solo con las primitivas y quizas los participantes.
		return null;
	}
}
