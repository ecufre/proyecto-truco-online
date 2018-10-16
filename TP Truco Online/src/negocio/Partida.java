package negocio;

import java.util.ArrayList;
import java.util.Vector;

import dto.JugadorDTO;
import dto.PartidaDTO;

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
		this.estado = EstadoPartida.PENDIENTE;
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
			this.estado=EstadoPartida.ENCURSO;
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
					this.estado = EstadoPartida.FINALIZADA;
					this.setGanador(1);
				}
				if(juegosPar==2){
					this.estado = EstadoPartida.FINALIZADA;
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
		Jugador j = new Jugador();
		j.setApodo(j1.getApodo());
		j.setUbicacion(i);
		this.jugadores.add(j);
		
	}







	public void senia(String apodoJugador, Integer carta) {
		Jugador j= this.buscarJugadorApodo(apodoJugador);
		if(j!=null){
			
		juegoActual.senia(j.getUbicacion(),j.getApodo(),carta);
		}
	}







	public PartidaDTO toDTO(int partida, String Jugador , Boolean ptosEnvido) {
		PartidaDTO pd = new PartidaDTO(partida);
		int juegosImpar = 0,juegosPar = 0;
		for(Juego j : juegos){
			if(j.getPuntajePar()<j.getPuntajeImpar()){
				
				juegosImpar++;
			}else{
			
				juegosPar++;
			}
		}
		pd.setJuegosPar(juegosPar);
		pd.setJuegosImpar(juegosImpar);
		
		pd.setPuntosJuegoImpar(this.juegoActual.getPuntajeImpar());
		pd.setPuntosJuegoPar(this.juegoActual.getPuntajePar());
		pd.setChat(this.getCharla());
		pd.setSeniasPar(this.juegoActual.getManoActual().getSeniasPar());
		pd.setSeniasImpar(this.juegoActual.getManoActual().getSeniasImpar());
		
		for(Jugador j:jugadores){
			JugadorDTO jd = new JugadorDTO(j.getApodo(), null);
			
			switch (j.getUbicacion()){
			case 1:
				pd.setJugador1(jd);
				pd.setValorEnvido1(j.getApodo()+": "+this.juegoActual.mostrarPuntosEnvido(j.getUbicacion()));
				
			break;
			case 2:
				pd.setJugador2(jd);
				pd.setValorEnvido2(j.getApodo()+": "+this.juegoActual.mostrarPuntosEnvido(j.getUbicacion()));
			break;
			case 3:
				pd.setJugador3(jd);
				pd.setValorEnvido3(j.getApodo()+": "+this.juegoActual.mostrarPuntosEnvido(j.getUbicacion()));
			break;
			case 4:
				pd.setJugador4(jd);
				pd.setValorEnvido1(j.getApodo()+": "+this.juegoActual.mostrarPuntosEnvido(j.getUbicacion()));
			break;
			}
				
		}
		
		pd.setCartasJugador(this.juegoActual.mostrarCartasJugador(Jugador));
		pd.setCartasMesa(this.juegoActual.mostrarCartasMesa());
		return pd;
	}







	
	
	
	
	
}
