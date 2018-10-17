package negocio;

import java.util.ArrayList;
import java.util.Random;

import dto.CartaDTO;


public class Mano {
		private Integer id;
		private ArrayList<Canto> cantos;
		private ArrayList<Carta> cartas;
		private ArrayList<Baza> bazas;
		private Baza bazaActual;
		private Canto ultimoCanto;
		private static int turno;
		private Integer[] envidoValor;
		private String seniasPar;
		private String seniasImpar;
		
		
		public Mano() {
			super();
			this.id =  null;
			this.cantos = new ArrayList<Canto>();
			this.cartas = new ArrayList<Carta>();
			this.bazas = new ArrayList<Baza>();
			this.ultimoCanto =null;
			this.bazaActual = new Baza(this.asignarMano());
			envidoValor = new Integer[4];
			this.seniasPar=null;
			this.seniasImpar=null;
			
		}

		private static int asignarMano(){
			if(turno ==4)
				turno=0;
				turno++;
				return turno;
		
		}
			
		public void cantarEnvite(int jugadorUbicacion, Canto canto) {
			
			if(this.esCantoValido(jugadorUbicacion, canto)){
				
				
				if(ultimoCanto!=null){
					if(ultimoCanto.getTipoCanto().getId()>4 && canto.getTipoCanto().getId()<5 ){
						ultimoCanto = canto;
					}else{
					ultimoCanto.setQuerido(true);
					this.cantos.add(ultimoCanto);
					ultimoCanto = canto;}
					
				}else{
					ultimoCanto = canto;
					System.out.println(ultimoCanto);
				}
			}
			
			
		}
		
		public boolean esCantoValido(int jugadorUbicacion, Canto canto) {
			boolean eval=true;
			
			if((jugadorUbicacion!=this.getBazaActual().getTurno())){
				return false;
			}
			if(this.getBazas().size()>0 && this.getBazas()==null){
				if(canto.getTipoCanto().getId()<=4){
					return false;
				}
			}
			for(Canto c:this.cantos){
				if(c.getTipoCanto().getId()==canto.getTipoCanto().getId()){
					return false;
				}
			}
			if(canto.getTipoCanto().getId()>5){
				for(Canto c:this.cantos){
					if(c.getTipoCanto().getId()==canto.getTipoCanto().getPredecesor()){
						eval=true;
					}
			}
			
			}
			return eval;
		}
		
		public boolean responderEnvite(int jugador, boolean respuesta) {
			boolean band=false;
			if(this.esRespuestaValida(jugador)){
				this.ultimoCanto.setQuerido(respuesta);
				cantos.add(ultimoCanto);
			}
			
			if(ultimoCanto.getTipoCanto().getId()<=4){
				return true;
			}
			ultimoCanto=null;
			return band;
		}
		
		public boolean esRespuestaValida(int jugador) {
			return (this.esPar(jugador) && (!this.esPar(this.bazaActual.getTurno())));
		}
		
		
		public void jugarCarta(String jugador, int carta) {
			Carta c = this.buscarCarta(carta);
			if(this.esJugadaValida(jugador, c )){
				
				this.bazaActual.agregarCarta(c);
				this.cartas.remove(c);
				if(this.bazaActual.completa()){
					this.bazaActual.determinarGanador();
					this.bazas.add(bazaActual);
					int mano=bazaActual.getGanadorBaza().getUbicacion();
					this.bazaActual = new Baza(mano);
				}
			}
			
		}
		
		public boolean esJugadaValida(String jugador, Carta c) {
			
			
			if(c!=null){
			
				if(c.getJugador().getUbicacion()==this.bazaActual.getTurno()){
				
				return (c.getJugador().getApodo().equals(jugador));
				}
			}
			
			return false;
		}
		
		private Carta buscarCarta(int carta) {
			for(Carta c :cartas){
				
				if(c.getId()==carta){
					
					return c;
				}
			}
			return null;
		}

		public int calcularPuntaje(int equipo) {
			int puntosImpar =0;
			int puntosPar=0;
			int ptoAd=0;
			for(Canto c : cantos){
				if(c.getTipoCanto().getId()>4){
					if(c.isQuerido()){
						ptoAd=ptoAd+c.getTipoCanto().getValor();
								
					}
				}
			}
			for(Baza b:this.getBazas()){
				if(b.getGanadorBaza().getUbicacion()==1 |b.getGanadorBaza().getUbicacion()==3 ){
					puntosImpar++; 
					puntosImpar=puntosImpar;
					}
				if(b.getGanadorBaza().getUbicacion()==2 |b.getGanadorBaza().getUbicacion()==4 ){
					puntosPar++;
					puntosPar=puntosPar;
					}
				
			}
			
			if(equipo==1){
				if(puntosImpar>puntosPar){
					return 1+ptoAd;
				}else{
					return 0;
				}
			}
			
			if(equipo==2){
				if(puntosImpar<puntosPar){
					return 1+ptoAd;
				}else{
					return 0;
				}
			}
			return 0;
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
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

		public void setCartas(ArrayList<Carta> cartas2) {
			this.cartas = cartas2;
		}

		
		
		
		public ArrayList<Baza> getBazas() {
			return bazas;
		}

		public void setBazas(ArrayList<Baza> bazas) {
			this.bazas = bazas;
		}

		
		
		public String getSeniasPar() {
			return seniasPar;
		}

		public void setSeniasPar(String seniasPar) {
			this.seniasPar = seniasPar;
		}

		public String getSeniasImpar() {
			return seniasImpar;
		}

		public void setSeniasImpar(String seniasImpar) {
			this.seniasImpar = seniasImpar;
		}

		public Baza getBazaActual() {
			return bazaActual;
		}

		public void setBazaActual(Baza bazaActual) {
			this.bazaActual = bazaActual;
		}

		public static int getTurno() {
			return turno;
		}

		public static void setTurno(int turno) {
			Mano.turno = turno;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public boolean manoCompleta() {
			if(3==this.bazas.size()){
				return true;
			}
			if(2==this.bazas.size()){
			int par=0;
			int impar=0;
			for(Baza b: this.bazas){
				if(b.getGanadorBaza().getUbicacion() ==1 | b.getGanadorBaza().getUbicacion() ==3){
					impar ++;
				}
				if(b.getGanadorBaza().getUbicacion() ==2 | b.getGanadorBaza().getUbicacion() ==4){
					par ++;
				}
				
			}
			boolean respuesta =(par==2 | impar==2);
			
			return (par==2 | impar==2);
		}
		return false;
		}

		

		public void administrarRetiro(Jugador ganador) {
			
			this.bazas.add(this.getBazaActual());
			while(this.bazas.size()<3){
				Baza b= new Baza(1);
				this.bazas.add(b);
			}
			for(Baza b:this.getBazas()){
				b.setGanadorBaza(ganador);
			}
			
		}
		
		public boolean esPar(int nro){
			
			if(nro==2) return true;
			if(nro==4) return true;
			return false;
		}

		public void calcularEnvidos() {
			for(int i=1;i<5;i++){
				Carta[] manoj = new Carta[3];
				int a=0;
				int mejor=0;
				for(Carta c:cartas){
					if(c.getJugador().getUbicacion()==i){
						manoj[a]=c;
						a++;
					}
				}
					for(int b=0;b<2;b++){
						for(int cc=b+1;cc<3;cc++){
							
							int p=(manoj[b].getValorEnvite()+manoj[cc].getValorEnvite());
							if (manoj[b].getPalo().equals(manoj[cc].getPalo())){
								p=p+20;
							}
							if(mejor<(p)){
								mejor=p;
							}
						}
					}
					this.envidoValor[i-1]=mejor;
				}
				
			}

		public int darEnvidoValor(int de){
			return this.envidoValor[de];
		}
		
		public Integer[] getEnvidoValor() {
			return envidoValor;
		}

		public void setEnvidoValor(int pos) {
			this.envidoValor[pos] = 0;
		}

		public int sumarPuntosEnvido(int dif) {
			int puntos = 1;
			int cant = 1;
			for(Canto c: cantos){
				if(c.isQuerido()){
					if(c.getTipoCanto().getId()!=4){
						
					puntos = c.getTipoCanto().getValor();
					}else{
						puntos = c.getTipoCanto().getValor()-dif;	
					}
				}else{
					if(cant==1){
						puntos=1;
					}
				}
				cant++;
			}
			return puntos;
		}

		public Integer mostrarPuntosEnvido(Integer pos) {
			return this.envidoValor[pos-1];
		}

		public ArrayList<CartaDTO> mostrarCartasJugador(String jugador) {
			ArrayList<CartaDTO> cd = new ArrayList<CartaDTO>();
			for(Carta c: cartas){
				if(c.getJugador().getApodo().equals(jugador)){
					cd.add(c.toDTO());
				}
			}
			return cd;
		}

	
		
		
		public void senia(Integer ubicacion, String apodoJugador, Integer carta) {
			Carta c = this.buscarCarta(carta);
			Random aleatorio = new Random();
			int suerte= aleatorio.nextInt((10));
			String cartaS= null;
			if(c!=null){
				cartaS=apodoJugador+": "+Integer.toString(c.getNumero())+" "+c.getPalo()+"\n";
			}
			if(this.esPar(ubicacion)){
				this.seniasPar=this.seniasPar+cartaS;
				if(suerte==5){
					this.seniasImpar=this.seniasImpar+cartaS;
				}
				
			}else{
				this.seniasImpar=this.seniasImpar+cartaS;
				if(suerte==5){
					this.seniasPar=this.seniasPar+cartaS;
				}
			}
			
		}

		public ArrayList<CartaDTO> mostarCartasMesa(Integer ubicacion) {
			 ArrayList<CartaDTO> cm = new  ArrayList<CartaDTO>();
			for(Baza b : this.bazas){
				for(Carta c :b.getCartasbaza()){
					if(c.getJugador().getUbicacion()==ubicacion){
					cm.add(c.toDTO());
					}
				}
			}
			for(Carta c :this.bazaActual.getCartasbaza()){
				if(c.getJugador().getUbicacion()==ubicacion){
					cm.add(c.toDTO());
					}
			}
			return cm;
		}
			
		
}
