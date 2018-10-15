package negocio;

import java.util.ArrayList;


public class Juego {
	private int id;
	private int puntajePar;
	private int puntajeImpar;
	private boolean finalizado;
	private ArrayList<Mano> manos;
	private Mano manoActual;
	
	
	
	
	

	public Juego() {
		super();
		this.id = 1;
		this.puntajePar = 0;
		this.puntajeImpar = 0;
		this.finalizado = false;
		this.manos = new ArrayList<Mano>();
		this.manoActual = null;
		
	}

	public void getGanador() {}
	
	public void crearMano(ArrayList<Jugador> jugadores) {
		
		Mazo mazo = new Mazo();
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		
		for(Jugador j : jugadores){
			
			for(int i= 0;i<3;i++){
				Carta c=mazo.darCarta();
				c.setJugador(j);
				cartas.add(c);
				
			}
		}
		
		Mano mano = new Mano();
		mano.setCartas(cartas);
		if(manoActual!=null){
			manos.add(manoActual);
		}
		mano.calcularEnvidos();
		this.manoActual = mano;
		
	}
	
	public void jugarCarta(int jugador, int carta) {
		
		manoActual.jugarCarta(jugador, carta);
		
		
	}
	
	
	
	
	public void retirarseMano(Jugador ganador){
				this.getManoActual().administrarRetiro(ganador);
				
		
	};
	
	public void cantarEnvite(int jugadorUbicacion,int canto) {
		Canto c=new Canto();
		switch (canto){
		
		case 1:
			
			c.setTipoCanto(TipoCanto.ENVIDO);
			break;
		case 2:
			c.setTipoCanto(TipoCanto.ENVIDOENVIDO);
			break;
		case 3:
			c.setTipoCanto(TipoCanto.REALENVIDO);
			break;
		case 4:
			c.setTipoCanto(TipoCanto.FALTAENVIDO);
			break;
		case 5:
			c.setTipoCanto(TipoCanto.TRUCO);
			break;
		case 6:
			
			c.setTipoCanto(TipoCanto.RETRUCO);
			break;
		case 7:
			c.setTipoCanto(TipoCanto.VALE4);
			break;
		
		}
		
		
		
		this.manoActual.cantarEnvite(jugadorUbicacion, c);
		
		
	}
	
	public boolean responderEnvite(int jugadorUbicacion, boolean respuesta) {
		return this.manoActual.responderEnvite(jugadorUbicacion, respuesta);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPuntajePar() {
		return puntajePar;
	}

	public void setPuntajePar(int puntajePar) {
		this.puntajePar = puntajePar;
	}

	public int getPuntajeImpar() {
		return puntajeImpar;
	}

	public void setPuntajeImpar(int puntajeImpar) {
		this.puntajeImpar = puntajeImpar;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public ArrayList<Mano> getManos() {
		return manos;
	}

	public void setManos(ArrayList<Mano> manos) {
		this.manos = manos;
	}

	public Mano getManoActual() {
		return manoActual;
	}

	public void setManoActual(Mano manoActual) {
		this.manoActual = manoActual;
	}

	public int getTurno() {
		
		return manoActual.getBazaActual().getTurno();
	}

	public void calcularPuntos() {
		
		this.setPuntajeImpar(this.getPuntajeImpar()+manoActual.calcularPuntaje(1));
		this.setPuntajePar(this.getPuntajePar()+manoActual.calcularPuntaje(2));
		this.setFinalizado(((this.getPuntajeImpar()>=30)|(this.getPuntajePar()>=30)));
	}

	public void sumarPuntosEnvido() {
		int a = (this.getPuntajeImpar());
		if(a>=15){
			a=a-15;
		}
		int b =(this.getPuntajePar());
		if(b>=15){
			b=b-15;
		}
		int dif =0;
		if(a>b){
			dif=a;
		}else{
			dif=b;
		}
		int puntos =	this.manoActual.sumarPuntosEnvido(dif);
		int turno = this.getManoActual().getBazaActual().getTurno();
		int ptoGanador=0;
		int ganador=turno;
		int ultimo= 0;
		if(turno==1){
			ultimo = 4;
		}else{
			ultimo = turno -1;
		}
		do{
			if(this.getManoActual().darEnvidoValor(turno)>ptoGanador){
				ganador=turno;
			}else{
				this.getManoActual().setEnvidoValor(turno);
			}
			turno = siguinte(turno);
		}	while(turno!=ultimo);
		
		if(this.manoActual.esPar(ganador)){
			this.puntajePar = this.puntajePar+puntos;
		}else{
			this.puntajePar = this.puntajeImpar+puntos;
		}
		
	}

	private int siguinte(int turno) {
		
		if(turno==4){
			return 1;
		}else{
			return ++turno;
		}
	
	}

	public void mostrarPuntosEnvido() {
			this.getManoActual().mostrarPuntosEnvido();
		
	}

	public void mostrarCartasJugador(int jugador) {
		this.getManoActual().mostrarCartasJugador(jugador);
		
	}

	
	
	
	
	
	
	
}
