package negocio;

import java.util.ArrayList;

import dto.CartaDTO;
import enumeraciones.TipoCanto;
import excepciones.ComunicacionException;


public class Juego {
	private static int siguienteId = 1; //TODO esto se reemplaza por la persistencia
	private int id;
	private int puntajePar;
	private int puntajeImpar;
	private boolean finalizado;
	private ArrayList<Mano> manos;

	//Metodo a eliminar con persistencia
	private static int getSiguienteId() {
		return siguienteId++;
	}

	public Juego() {
		this.id = Juego.getSiguienteId();
		this.puntajePar = 0;
		this.puntajeImpar = 0;
		this.finalizado = false;
		this.manos = new ArrayList<Mano>();
	}

	public void grabar() {
		//TODO Grabar
	}

	private Mano getManoActual() {
		return this.manos.get(this.manos.size() - 1);
	}

	public void crearMano() {
		Mazo mazo = new Mazo();
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		for(int j= 1; j <= 4 ; j++){
			for (int i = 0; i < 3; i ++) {
				Carta c = mazo.darCarta();
				c.setJugador(j);
				c.grabar();
				cartas.add(c);
			}
		}
		Mano mano = new Mano(this.manos.size() + 1);
		
		mano.setCartas(cartas);
		mano.calcularEnvidos();
		manos.add(mano);
		mano.grabar();
	}

	public void jugarCarta(int ubicacionJugador, int carta) throws ComunicacionException {
		this.getManoActual().jugarCarta(ubicacionJugador, carta);
	}

	public void retirarseMano(int ganador){
		this.getManoActual().administrarRetiro(ganador);
	}

	public void actualizarJuego() {
		if(this.getManoActual().manoCompleta()) this.calcularPuntos();
	}

	public boolean manoCompleta() {
		return this.getManoActual().manoCompleta();
	}

	public void cantarEnvite(int jugadorUbicacion, TipoCanto canto) throws ComunicacionException {
		this.getManoActual().cantarEnvite(jugadorUbicacion, canto);
		this.getManoActual().grabar();
	}
	
	public void responderEnvite(int jugadorUbicacion, boolean respuesta) throws ComunicacionException {
		this.getManoActual().responderEnvite(jugadorUbicacion, respuesta);
	}
	

	private void calcularPuntos() {
		this.puntajeImpar = this.puntajeImpar + this.getManoActual().calcularPuntaje(1, this.puntajeImpar, this.puntajePar);
		this.puntajePar = this.puntajePar + this.getManoActual().calcularPuntaje(2, this.puntajePar, this.puntajeImpar);
		this.finalizado = (this.puntajeImpar >= 30 || this.puntajePar >= 30);
	}

	public int getPuntajePar() {
		return puntajePar;
	}

	public int getPuntajeImpar() {
		return puntajeImpar;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	
	
	public ArrayList<CartaDTO> mostrarCartasJugador(int i) {
		return this.getManoActual().mostrarCartasJugador(i);

}

	public Integer mostrarPuntosEnvido(Integer pos) {
		return this.getManoActual().mostrarPuntosEnvido(pos);

	}

	
		public  ArrayList<CartaDTO> mostrarCartasMesa(Integer ubicacion){
			return this.getManoActual().mostarCartasMesa(ubicacion);
			
	
	}

		public int getTurno() {
		
		return this.getManoActual().getBazaActual().getTurno();
}
}
