package negocio;

import java.util.ArrayList;

import dao.JuegoDAO;
import dto.CartaDTO;
import dto.JuegoDTO;
import dto.ManoDTO;
import enumeraciones.TipoCanto;
import excepciones.ComunicacionException;


public class Juego {

	private int id;
	private int puntajePar;
	private int puntajeImpar;
	private boolean finalizado;
	private ArrayList<Mano> manos;

	public Juego() {
		this.puntajePar = 0;
		this.puntajeImpar = 0;
		this.finalizado = false;
		this.manos = new ArrayList<Mano>();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Mano> getManos() {
		return manos;
	}
	public void setManos(ArrayList<Mano> manos) {
		this.manos = manos;
	}
	public void setPuntajePar(int puntajePar) {
		this.puntajePar = puntajePar;
	}
	public void setPuntajeImpar(int puntajeImpar) {
		this.puntajeImpar = puntajeImpar;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
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

	public void crear() throws ComunicacionException {
		Integer id = JuegoDAO.getInstancia().crear(this);
		if (id != null) this.id = id;
		else throw new ComunicacionException("Hubo un error al generar un nuevo juego");
	}

	public void grabar() {
		JuegoDAO.getInstancia().grabar(this);
	}

	private Mano getManoActual() {
		return this.manos.get(this.manos.size() - 1);
	}

	public void crearMano() throws ComunicacionException {
		Mazo mazo = new Mazo(); //TODO Pedirselo al MazoDAO
		ArrayList<Carta> cartas = new ArrayList<Carta>();

		/*
		for(int j= 1; j <= 4 ; j++){
			for (int i = 0; i < 3; i ++) {
				Carta c = mazo.darCarta();
				c.setJugador(j);
				c.grabar();
				cartas.add(c);
			}
		}
		 */
		//Metodo no random para pruebas TODO: Sacarlo
		Carta c = mazo.darCarta(1);
		c.setJugador(1);
		c.crear();
		System.out.println(c.toString());
		cartas.add(c);
		c = mazo.darCarta(1);
		c.setJugador(1);
		c.crear();
		System.out.println(c.toString());
		cartas.add(c);
		c = mazo.darCarta(24);
		c.setJugador(1);
		c.crear();
		System.out.println(c.toString());
		cartas.add(c);
		c = mazo.darCarta(33);
		c.setJugador(2);
		c.crear();
		System.out.println(c.toString());
		cartas.add(c);
		c = mazo.darCarta(33);
		c.setJugador(2);
		c.crear();
		System.out.println(c.toString());
		cartas.add(c);
		c = mazo.darCarta(33);
		c.setJugador(2);
		c.crear();
		System.out.println(c.toString());
		cartas.add(c);
		c = mazo.darCarta(1);
		c.setJugador(3);
		c.crear();
		System.out.println(c.toString());
		cartas.add(c);
		c = mazo.darCarta(10);
		c.setJugador(3);
		c.crear();
		System.out.println(c.toString());
		cartas.add(c);
		c = mazo.darCarta(21);
		c.setJugador(3);
		c.crear();
		System.out.println(c.toString());
		cartas.add(c);
		c = mazo.darCarta(1);
		c.setJugador(4);
		c.crear();
		System.out.println(c.toString());
		cartas.add(c);
		c = mazo.darCarta(10);
		c.setJugador(4);
		c.crear();
		System.out.println(c.toString());
		cartas.add(c);
		c = mazo.darCarta(21);
		c.setJugador(4);
		c.crear();
		System.out.println(c.toString());
		cartas.add(c);
		// Aca termina la seccion de prueba

		Mano mano = new Mano(this.manos.size() + 1);
		mano.setCartas(cartas);
		mano.calcularEnvidos();
		manos.add(mano);
		mano.crear();
	}

	public void jugarCarta(int ubicacionJugador, int carta) throws ComunicacionException {
		this.getManoActual().jugarCarta(ubicacionJugador, carta);
		this.grabar();
	}

	public void retirarseMano(int ganador) throws ComunicacionException{
		this.getManoActual().administrarRetiro(ganador);
		this.grabar();
	}

	public void actualizarJuego() {
		if(this.getManoActual().manoCompleta()) this.calcularPuntos();
		this.grabar();
	}

	public boolean manoCompleta() {
		return this.getManoActual().manoCompleta();
	}

	public void cantarEnvite(int jugadorUbicacion, TipoCanto canto) throws ComunicacionException {
		this.getManoActual().cantarEnvite(jugadorUbicacion, canto);
		this.getManoActual().grabar();
	}

	public void responderEnvite(int jugadorUbicacion, TipoCanto tipoCanto, boolean respuesta) throws ComunicacionException {
		this.getManoActual().responderEnvite(jugadorUbicacion, tipoCanto, respuesta);
		this.grabar();
	}


	private void calcularPuntos() {
		this.puntajeImpar = this.puntajeImpar + this.getManoActual().calcularPuntaje(1, this.puntajeImpar, this.puntajePar);
		this.puntajePar = this.puntajePar + this.getManoActual().calcularPuntaje(2, this.puntajePar, this.puntajeImpar);
		this.finalizado = (this.puntajeImpar >= 30 || this.puntajePar >= 30);
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

	public JuegoDTO toDTO() {
		ArrayList<ManoDTO> manos = new ArrayList<ManoDTO> ();
		for(Mano m : this.getManos()){
			manos.add(m.toDTO());
		}
		JuegoDTO jdto  = new JuegoDTO(this.getId(), this.getPuntajePar(), this.getPuntajeImpar(),
				this.isFinalizado(),  manos);

		return jdto;
	}
}
