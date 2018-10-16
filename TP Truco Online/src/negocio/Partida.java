package negocio;

import java.util.ArrayList;
import java.util.Vector;

public class Partida {
	private int id;
	private ArrayList<Jugador> jugadores;
	private boolean[] jugadoresListos;
	private boolean esAbierta;
	private EstadoPartida estado;
	private Integer ganador;
	private ArrayList<Juego> juegos;
	private Juego juegoActual;
	
	
	
	
	
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







	public void agregarJugador(Jugador j1, int i) {
		Jugador j = new Jugador();
		j.setApodo(j1.getApodo());
		j.setUbicacion(i);
		this.jugadores.add(j);
		
	}
	
	
	
	
	
	
}
