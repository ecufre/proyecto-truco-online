package negocio;

import java.util.ArrayList;

import dao.CantoDAO;
import dao.ManoDAO;
import dto.BazaDTO;
import dto.CantoDTO;
import dto.CartaDTO;
import dto.ManoDTO;
import enumeraciones.TipoCanto;
import excepciones.ComunicacionException;


public class Mano {
	private Integer id;
	private Integer numeroMano;
	private ArrayList<Canto> cantos;
	private ArrayList<Carta> cartas;
	private ArrayList<Baza> bazas;
	private Boolean mostrarPuntos;
	private Integer[] envidoValor;

	public Mano(Integer numeroMano) throws ComunicacionException {
		this.numeroMano = numeroMano;
		this.cantos = new ArrayList<Canto>();
		this.cartas = new ArrayList<Carta>();
		this.bazas = new ArrayList<Baza>();
		this.mostrarPuntos = false;
		Baza b = new Baza(this.calcularJugManoBaza());
		b.crear();
		bazas.add(b);
		this.envidoValor = new Integer[4];
	}

	public Mano(Integer id, Integer numeroMano, Boolean mostrarPuntos) {
		this.id = id;
		this.numeroMano = numeroMano;
		this.cantos = new ArrayList<Canto>();
		this.cartas = new ArrayList<Carta>();
		this.bazas = new ArrayList<Baza>();
		this.envidoValor = new Integer[4];
		this.mostrarPuntos = mostrarPuntos;
	}

	public ArrayList<Canto> getCantos() {
		return cantos;
	}
	public void setCantos(ArrayList<Canto> cantos) {
		this.cantos = cantos;
	}
	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	public Integer getNumeroMano() {
		return numeroMano;
	}
	public void setNumeroMano(Integer numeroMano) {
		this.numeroMano = numeroMano;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ArrayList<Baza> getBazas() {
		return bazas;
	}
	public void setBazas(ArrayList<Baza> bazas) {
		this.bazas = bazas;
	}

	public Boolean getMostrarPuntos() {
		return mostrarPuntos;
	}

	public void setMostrarPuntos(Boolean mostrarPuntos) {
		this.mostrarPuntos = mostrarPuntos;
	}

	public void grabar() {
		ManoDAO.getInstancia().grabar(this);
	}

	public void crear() throws ComunicacionException {
		Integer id = ManoDAO.getInstancia().crear(this);
		if (id != null) this.id = id;
		else throw new ComunicacionException("Hubo un error al generar una nueva mano");
	}

	public Baza getBazaActual() {
		return this.bazas.get(this.bazas.size() - 1);
	}

	public Canto getUltimoCanto() {
		if (cantos.size() > 0) return this.cantos.get(this.cantos.size() - 1);
		else return null;
	}

	public void jugarCarta(int ubicacionJugador, int carta) throws ComunicacionException {
		Carta c = this.buscarCarta(carta);
		if(!this.cantoPendiente() && this.esJugadaValida(ubicacionJugador, c)){
			this.getBazaActual().agregarCarta(c);
			this.getBazaActual().grabar();
			c.setJugada(true);
			c.grabar();
			if(this.getBazaActual().isCompleta()){
				this.getBazaActual().determinarGanador();
				this.getBazaActual().grabar();
				if  (! this.manoCompleta()) {
					int mano = this.getBazaActual().getGanadorBaza();
					Baza b = new Baza(mano);
					b.crear();
					bazas.add(b);
				}
			}
			this.grabar();
		}
		else throw new ComunicacionException("Jugada invalida");

	}

	public boolean esJugadaValida(int ubicacionJugador, Carta c) {
		if (ubicacionJugador == this.getBazaActual().getTurno()) return (ubicacionJugador == c.getJugador() && ! c.isJugada());
		else return false;
	}

	private Carta buscarCarta(int carta) throws ComunicacionException {
		for(Carta c : this.cartas) if(c.getCartaId() == carta) return c;
		throw new ComunicacionException("La carta buscada no existe");
	}

	private int calcularJugManoBaza(){
		if (this.numeroMano % 4 == 0) return 4;
		else return this.numeroMano % 4;
	}

	//Calcula la posicion relativa de un jugador respecto del mano de la primer baza
	private int posicionRelativa(int ubicacion) {
		if (ubicacion >= this.calcularJugManoBaza()) return (1 + ubicacion - this.calcularJugManoBaza());
		else return (5 + ubicacion - this.calcularJugManoBaza());
	}

	public void administrarRetiro(int ganador) throws ComunicacionException {
		while(this.bazas.size()<3) {
			Baza b = new Baza(1);
			b.crear();
			bazas.add(b);
		}
		for(Baza b : this.bazas) {
			b.setGanadorBaza(ganador);
			b.grabar();
		}
		this.grabar();
	}

	public boolean manoCompleta() {
		//Tres bazas completas -> Mano completa
		if (this.bazas.size() == 3) return this.bazas.get(2).isCompleta();
		//Dos bazas completas
		if(this.bazas.size() == 2 && this.bazas.get(1).isCompleta()) {
			//Ambas terminaron empatadas -> Mano incompleta
			if (this.bazas.get(0).isParda() && this.bazas.get(1).isParda()) return false;
			//Solo una de las dos termino empatada -> Mano completa
			else if (this.bazas.get(0).isParda() || this.bazas.get(1).isParda()) return true;
			//El ganador de ambas bazas es el mismo equipo -> Mano completa
			else if (this.bazas.get(0).getGanadorBaza() % 2 == this.bazas.get(1).getGanadorBaza() % 2) return true;
			//Dos bazas con ganadores distintos -> Mano incompleta
			else return false;
		}
		//No se termino de jugar la 2da Baza -> Mano incompleta
		return false;
	}

	public int calcularGanadorMano() {
		int ganador;
		//Ultima baza es empate -> Ganador de la primer baza gano la mano
		if (this.bazas.get(this.bazas.size() - 1).isParda()) ganador = this.bazas.get(0).getGanadorBaza() % 2;
		//Sino el ganador de la ultima baza es el ganador de la mano
		else ganador = this.bazas.get(this.bazas.size() - 1).getGanadorBaza() % 2;

		if (ganador == 0) return 2;
		else return 1;
	}

	public boolean esCantoValido(int jugadorUbicacion, TipoCanto canto) {
		//No es el turno del jugador y no hay un canto pendiente
		if (! this.cantoPendiente() && jugadorUbicacion != this.getBazaActual().getTurno()) return false;

		//Si ya se realizo el mismo canto
		for (Canto c : this.cantos) if (c.getTipoCanto().getId() == canto.getId()) return false;

		//Para el envido
		if (canto.getId() < 5) {
			//Canta envido despues de la primer baza
			if (this.bazas.size() > 1 && canto.getId() <= 4) return false;
			
			//Si no es el pie
			if (this.getUltimoCanto() == null && this.posicionRelativa(jugadorUbicacion) < 3) return false;
			
			//Si el ultimo canto ya fue respondido
			if (this.getUltimoCanto() != null && this.getUltimoCanto().isQuerido() != null) return false;
			
			int cantidadDeCantos = 0;
			//Cuanto la cantidad de cantos ya queridos
			for (Canto c : this.cantos) {
				if (c.getTipoCanto().getId() < 5 && c.isQuerido() != null) cantidadDeCantos++;
			}
			
			//Si se canto truco, en la primer baza, y no fue respondido, puede cantar el equipo rival al que canto truco, siempre que no haya un envido anterior
			if (this.getUltimoCanto() != null && this.getUltimoCanto().getTipoCanto().getId() == 5 && this.getUltimoCanto().isQuerido() == null && cantidadDeCantos == 0 && this.getUltimoCanto().getCantante() % 2 != jugadorUbicacion % 2) return true;
			
			//Si hubo un canto anterior, verifico que no haya sido del mismo equipo
			if (this.getUltimoCanto() != null && this.getUltimoCanto().getCantante() % 2 == jugadorUbicacion % 2) return false;
			
			/* Reemplazada por la anterior
			//Si los cantos son pares puede cantar el equipo de quien es turno, si son impares, puede cantar el equipo rival
			if (jugadorUbicacion % 2  != (this.getBazaActual().getTurno() + cantidadDeCantos) % 2) return false;
			*/
		}
		
		//Si es un truco, no tiene que haber un canto pendiente
		if (canto.getId() == 5 && this.cantoPendiente()) return false;

		//Si es un retruco o valecuatro buscar que este el canto anterior
		if (canto.getId() > 5) {
			for (Canto c : this.cantos) {
				//Si existe el anterior y fue cantado por el equipo rival al que esta cantando
				if (c.getTipoCanto().getId() == canto.getPredecesor() && (c.getCantante() % 2 != jugadorUbicacion % 2)) {
					//Si el canto anterior ya fue respondido, tiene que ser turno del que canta
					if (c.isQuerido() != null) return (jugadorUbicacion == this.getBazaActual().getTurno());
					//Si el canto anterior no fue respondido, puede subirse el canto
					else return true;
				}
			}
			return false;
		}

		//Si es un envido buscar que no este el posterior.
		if (canto.getId() < 4) for (Canto c : this.cantos) if (c.getTipoCanto().getId() < 5 && c.getTipoCanto().getId() > canto.getId()) return false;

		//Si es envido envido, buscar que este el envido
		if (canto.getId() == 2) for (Canto c : this.cantos) if (c.getTipoCanto().getId() == 1) return true;

		//Todas las demas opciones son validas
		return true;
	}

	public void cantarEnvite(int jugadorUbicacion, TipoCanto canto) throws ComunicacionException {
		if (this.esCantoValido(jugadorUbicacion, canto)) {
			if (this.getUltimoCanto() != null) {
				//Si canto el envido despues de que se canto el truco, el envido sobreescribe al truco.
				if (this.cantoPendiente() && this.getUltimoCanto().getTipoCanto().getId() > 4 && canto.getId() < 5) {
					//CantoDAO.getInstancia().borrar(this.getUltimoCanto());
					this.cantos.remove(this.getUltimoCanto());
					this.grabar();
				}
				//Si el canto anterior era del mismo tipo, acepto el canto anterior.
				else if (((this.getUltimoCanto().getTipoCanto().getId() > 4 && canto.getId() > 4) || (this.getUltimoCanto().getTipoCanto().getId() < 5 && canto.getId() < 5)) && this.cantoPendiente()) {
					this.getUltimoCanto().setQuerido(true);	
					this.getUltimoCanto().grabar();
				}
			}
			if (! this.cantoPendiente()) {
				Canto c = new Canto(jugadorUbicacion);
				c.setTipoCanto(canto);
				c.crear();
				this.cantos.add(c);
				this.grabar();
			}
			else throw new ComunicacionException("Ya hay un canto pendiente de respuesta");
		}
		else throw new ComunicacionException("El canto es invalido");
	}

	public boolean esRespuestaValida(int jugador, TipoCanto tipoCanto) {
		//Si no hay un canto pendiente de respuesta, la respuesta no es valida
		if (! this.cantoPendiente()) return false;
		//Sino, reviso que el ultimo canto sea del equipo rival
		else return (this.getUltimoCanto().getCantante() % 2 != jugador % 2);
		
		//TODO ya no es necesario el TipoCanto en la respuesta. :D
		
		
		/* Todo lo siguiente fue eliminado por una sola linea de codigo :'(
		int cantidadDeCantos = 0;
		int cantoTrucoMaximo = 0;
		int cantanteUltimoTruco = 0;
		
		//Cuanto la cantidad de cantos
		for (Canto c : this.cantos) {
			if (c.getTipoCanto().getId() < 5) cantidadDeCantos++;
			if (c.getTipoCanto().getId() > cantoTrucoMaximo) {
				cantoTrucoMaximo = c.getTipoCanto().getId();
				cantanteUltimoTruco = c.getCantante();
			}
		}
		if (tipoCanto.getId() < 5) return (jugador % 2  == (this.getBazaActual().getTurno() + cantidadDeCantos) % 2);
		else return (cantanteUltimoTruco % 2 != jugador % 2);
		*/
	}

	public void responderEnvite(int jugador, TipoCanto tipoCanto, Boolean respuesta, Boolean mostrarPuntos) throws ComunicacionException {
		if (this.esRespuestaValida(jugador, tipoCanto)) {
			this.getUltimoCanto().setQuerido(respuesta);
			this.getUltimoCanto().grabar();
			if (tipoCanto.getId() > 4 && ! respuesta) this.administrarRetiro(jugador % 2 + 1);
			if (mostrarPuntos != null) this.mostrarPuntos = mostrarPuntos;
			this.grabar();
		}
		else throw new ComunicacionException("La respuesta es invalida");
	}

	public int calcularPuntaje(int equipo, int puntosEquipo, int puntosRival) {
		int puntosTruco = 0;
		//Si equipo gano el truco se suman los puntos del truco
		if (equipo % 2 == this.calcularGanadorMano() % 2) {
			puntosTruco = 1;
			for (Canto c : cantos) {
				if (c.getTipoCanto().getId() > 4 && c.isQuerido()) puntosTruco++;
			}
		}

		int puntosEnvido = 0;
		//Calculo los puntos del envido
		int maximoCanto = 0;
		boolean maximoCantoQuerido = false;
		int jugadorMaximoCanto = 0;
		int cantidadEnvidosCantados = 0;
		for (Canto c : cantos) {
			if (c.getTipoCanto().getId() < 5) {
				cantidadEnvidosCantados++;
				//Busco el maximo canto de envido, y guardo si fue querido o no.
				if (c.getTipoCanto().getId() > maximoCanto) {
					maximoCanto = c.getTipoCanto().getId();
					maximoCantoQuerido = c.isQuerido();
					jugadorMaximoCanto = c.getCantante();
				}
				//Si el canto fue querido, sumo los puntos.
				if (c.isQuerido()) {
					//Si no fue FaltaEnvido (Y no fue cantada), acumulo los puntos
					if (c.getTipoCanto().getId() < 4 && maximoCanto < 4) puntosEnvido = puntosEnvido + c.getTipoCanto().getValor();
					//Si fue falta envido reviso si estaban en malas y gana el partido, o calculo cuanto vale.
					else {
						//Los dos equipos en malas
						if (puntosEquipo < 15 && puntosRival < 15) puntosEnvido = 30 - puntosEquipo;
						//El equipo calculado va ganando la partida
						else if (puntosEquipo > puntosRival) puntosEnvido = 30 - puntosEquipo;
						//El equipo calculado va perdiendo la partida
						else puntosEnvido = 30 - puntosRival;
					}
				}
			}
		}
		//Si todos empatan en 0, gana el mano de la primer baza
		int jugadorGanador = this.calcularJugManoBaza();
		if (! maximoCantoQuerido && maximoCanto > 0) {
			if (cantidadEnvidosCantados == 1 || ( cantidadEnvidosCantados == 2 && this.cantos.get(0).getTipoCanto().getId() == this.cantos.get(1).getTipoCanto().getId())) puntosEnvido = 1;
			jugadorGanador = jugadorMaximoCanto;
		}
		else {
			//Verifico que equipo gano el envido
			int puntosGanador = 0;
			for (int i = 0; i < 4; i++) {
				if (this.envidoValor[i] > puntosGanador || (this.envidoValor[i] == puntosGanador && this.posicionRelativa(i + 1) < this.posicionRelativa(jugadorGanador))) {
					puntosGanador = this.envidoValor[i];
					jugadorGanador = i + 1;
					//System.out.println(jugadorGanador + ": " + puntosGanador);
				}
			}
		}
		//Si el ganador es del equipo, sumar los puntos
		if (jugadorGanador % 2 == equipo % 2) return puntosTruco + puntosEnvido;
		else return puntosTruco;
	}

	public void setCartas(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}

	public ArrayList<CartaDTO> mostrarCartasJugador(int i) {
		ArrayList<CartaDTO> cd = new ArrayList<CartaDTO>();
		for(Carta c: cartas){
			if(c.getJugador()==i && !c.isJugada()){
				cd.add(c.toDTO());
			}
		}
		return cd;
	}

	
	public void limpiarArrayEnvido(){
		if(true){
		int mano = this.getNumeroMano()%4;
		
		int a, b,c,d;
		boolean borrarA, borrarB , borrarC, borrarD;
		
		borrarA= borrarB = borrarC= borrarD= true;
		// busco el mano y las posciones relativas subsiguientes
		a= mano; 
		if(mano==3){b=0;}else{b=mano++;}
		if(b==3){c=0;}else{c=b++;}	
		if(c==3){d=0;}else{d=c++;}
		
		
		//por defecto digo que hay que borrar todos y luego me fijo cuales tengo que dejar
		borrarA = false;
	
		if(envidoValor[b]>envidoValor[a]){
			borrarB=false;
		}
		
		if((envidoValor[a]<envidoValor[b] & 
			envidoValor[b]<envidoValor[c])
			||
			((envidoValor[d]>envidoValor[a]) & 
			 (envidoValor[c]>envidoValor[d]))){
			borrarC=false;
		}
		
		if((envidoValor[a]<envidoValor[b] & 
			envidoValor[b]<envidoValor[c]  & 
			envidoValor[c]<envidoValor[d] ) 
			||
			(envidoValor[a]>envidoValor[b])){
			borrarD=false;
		}
		
		
		//borro los que corresponden 
		if(borrarA){
			envidoValor[a]=null;
		}
		if(borrarB){
			envidoValor[b]=null;
		}
		if(borrarC){
			envidoValor[c]=null;
		}
		if(borrarD){
			envidoValor[d]=null;
		}
		}
	}
	
	
	
	public Integer mostrarPuntosEnvido(Integer pos) {
		limpiarArrayEnvido();
		return this.envidoValor[pos-1];
	}

	public void calcularEnvidos() {
		for(int i=1;i<5;i++){
			Carta[] manoj = new Carta[3];
			int a=0;
			int mejor=0;
			for(Carta c:cartas){
				if(c.getJugador()==i){
					manoj[a]=c;
					a++;
				}
			}
			for(int b=0;b<2;b++){
				for(int cc=b+1;cc<3;cc++){
					int s= manoj[cc].getValorEnvite();
					int p=(manoj[b].getValorEnvite());
					if (manoj[b].getPalo().equals(manoj[cc].getPalo())){
						p=p+s+20;
					}
					if(mejor<(p)){
						mejor=p;
					}
					if(mejor<(s)){
						mejor=s;
					}
				}
			}
			this.envidoValor[i-1]=mejor;
		}
		//for (int i = 0; i < 4; i++) System.out.println(i + ": " + this.envidoValor[i]);

	}

	public ArrayList<CartaDTO> mostarCartasMesa(Integer ubicacion) {
		ArrayList<CartaDTO> cm = new  ArrayList<CartaDTO>();
		for(Baza b : this.bazas){
			for(Carta c :b.getCartasbaza()){
				if(c.getJugador()==ubicacion){
					cm.add(c.toDTO());
				}
			}
		}
		return cm;
	}

	public Integer[] getEnvidoValor() {
		return envidoValor;
	}

	public void setEnvidoValor(Integer[] envidoValor) {
		this.envidoValor = envidoValor;
	}

	public ManoDTO toDTO() {
		ArrayList<CantoDTO> cantos = new ArrayList<CantoDTO>();
		ArrayList<CartaDTO> cartas = new ArrayList<CartaDTO>();
		ArrayList<BazaDTO> bazas =  new ArrayList<BazaDTO>();

		for(Canto c : this.getCantos()){
			cantos.add(c.toDTO());
		}
		for(Carta c : this.getCartas()){
			cartas.add(c.toDTO());
		}
		for(Baza b : this.getBazas()){
			bazas.add(b.toDTO());
		}
		ManoDTO mdto = new ManoDTO(this.getId(),this.getNumeroMano(),cantos,cartas,bazas,this.getEnvidoValor());

		return mdto;
	}
	
	private boolean cantoPendiente() {
		//for (Canto c : this.cantos) if (c.isQuerido() == null) return true;
		if (this.getUltimoCanto() != null && this.getUltimoCanto().isQuerido() == null) return true;
		return false;
	}
}
