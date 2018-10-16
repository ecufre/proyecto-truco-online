package tests;

import java.util.ArrayList;
import java.util.Scanner;

import negocio.Baza;
import negocio.Carta;
import negocio.Juego;
import negocio.Jugador;
import negocio.Mazo;

public class TestJuego {

	static Mazo m = new Mazo();
	static String[] jugadorTurno = {"","jugador 1","jugador 2","jugador 3","jugador 4"};
	static ArrayList<Carta> cartas = new ArrayList<Carta>();
	static ArrayList<Jugador> js= new ArrayList<Jugador>();
	
	public static void main(String[] args) {
		
		
	cargar();
	Juego j = new Juego();
	j.crearMano(js);
	
	//j.cantarEnvite(1, 1);
	//if(j.responderEnvite(2, true)){
		//j.sumarPuntosEnvido();
	//	j.mostrarPuntosEnvido();
	//};
	
	
	System.out.println("Cartas Mano------------------------------------");
	for(Carta c:j.getManoActual().getCartas()){
		System.out.println(c.toString2());
	}
	
	
	while(!j.isFinalizado()){ 
	while(!j.getManoActual().manoCompleta()){
		int carta=0;
		int jugador = j.getTurno();
		
		String def = jugadorTurno[jugador];
        
        for(Carta c:j.getManoActual().getCartas()){
    		if(c.getJugador().getApodo().equals(def)){
    			carta = c.getId();
    			
    		}
    	}
        System.out.println("turno: "+jugador+jugadorTurno[jugador]);
        j.mostrarCartasJugador(jugadorTurno[jugador]);
        System.out.println("20) jugar 21)Retirarse 1) envido 2) envido envido 3) real 4) falta 5) turco 6) re 7) vale 8) quiero 9) no queiro  \n ------------------------------------\n opcion: ");
    	String entradaTeclado = "";
        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        entradaTeclado = entradaEscaner.nextLine ();
        int val = Integer.parseInt(entradaTeclado);
        switch (val){
        	
        case 20:
        	System.out.println("carta: ");
        	  entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
             entradaTeclado = entradaEscaner.nextLine ();
              carta = Integer.parseInt(entradaTeclado);
        	 j.jugarCarta(jugadorTurno[jugador], carta);
        	break;
        
        	
        case 1:
        	
        	j.cantarEnvite(jugador, val);
        	break;
        case 2:
        	j.cantarEnvite(jugador, val);
        	break;
        case 3:
        	j.cantarEnvite(jugador, val);
        	break;
        case 4:
        	j.cantarEnvite(jugador, val);
        	break;
        case 5:
        	j.cantarEnvite(jugador, val);
        	break;
        case 6:
        	j.cantarEnvite(jugador, val);
        	break;
        case 7:
        	j.cantarEnvite(jugador, val);
        	break;
        case 8:
        	System.out.println("reponde: ");
      	  entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
           entradaTeclado = entradaEscaner.nextLine ();
          int  jug = Integer.parseInt(entradaTeclado);
        
        	if(j.responderEnvite(jug, true)){
    		j.sumarPuntosEnvido();
    	j.mostrarPuntosEnvido();
    	};
        	break;	
        case 9:
        	System.out.println("reponde: ");
        	  entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
             entradaTeclado = entradaEscaner.nextLine ();
              jug = Integer.parseInt(entradaTeclado);
            
          	if(j.responderEnvite(jug, false)){
      		j.sumarPuntosEnvido();
      	j.mostrarPuntosEnvido();
      	};
        	break;
        case 21:
        	 int ubicacion =0;
             for(Jugador jj:js){	
             if(jj.getApodo().equals(jugadorTurno[jugador])){
             	ubicacion = jj.getUbicacion();
             }
             }
             if(ubicacion ==4){
             	ubicacion--;
             }else{
             	ubicacion++;
             }
             
             for(Jugador jj:js){
             if(jj.getUbicacion()==ubicacion){
                     j.retirarseMano(jj);
             }
             }
          break;
          
        	
        }
       
        
		
	}
	int i =1;
	for(Baza b :j.getManoActual().getBazas()){
		System.out.println("baza:"+i);
		for(Carta c: b.getCartasbaza()){
			System.out.println(c.toString2());
		}
		i++;
		
	}
	j.actualizarJuego();
	System.out.println("impar:  "+j.getPuntajeImpar());
	System.out.println("par:  "+j.getPuntajePar());
	
	
	System.out.println("proxima mano------------------------------------");
	String entradaTeclado = "";
    Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
    entradaTeclado = entradaEscaner.nextLine ();
    j.crearMano(js);
	
	}
	
	}

	
	public static void cargar(){
	/*	Carta  C1= new Carta(1,14,1,"espada");	 cartas.add(C1);
		Carta  C2= new Carta(2,9,2,"espada");	 cartas.add(C2);
		Carta  C3= new Carta(3,10,3,"espada");	 cartas.add(C3);
		Carta  C4= new Carta(4,1,4,"espada");	 cartas.add(C4);
		Carta  C5= new Carta(5,2,5,"espada");	 cartas.add(C5);
		Carta  C6= new Carta(6,3,6,"espada");	 cartas.add(C6);
		Carta  C7= new Carta(7,12,7,"espada");	 cartas.add(C7);
		Carta  C8= new Carta(8,5,10,"espada");	 cartas.add(C8);
		Carta  C9= new Carta(9,6,11,"espada");	 cartas.add(C9);
		Carta  C10= new Carta(10,7,12,"espada");	 cartas.add(C10);
		Carta  C11= new Carta(11,13,1,"basto");	 cartas.add(C11);
		Carta  C12= new Carta(12,9,2,"basto");	 cartas.add(C12);
		Carta  C13= new Carta(13,10,3,"basto");	 cartas.add(C13);
		Carta  C14= new Carta(14,1,4,"basto");	 cartas.add(C14);
		Carta  C15= new Carta(15,2,5,"basto");	 cartas.add(C15);
		Carta  C16= new Carta(16,3,6,"basto");	 cartas.add(C16);
		Carta  C17= new Carta(17,4,7,"basto");	 cartas.add(C17);
		Carta  C18= new Carta(18,5,10,"basto");	 cartas.add(C18);
		Carta  C19= new Carta(19,6,11,"basto");	 cartas.add(C19);
		Carta  C20= new Carta(20,7,12,"basto");	 cartas.add(C20);
		Carta  C21= new Carta(21,8,1,"copa");	 cartas.add(C21);
		Carta  C22= new Carta(22,9,2,"copa");	 cartas.add(C22);
		Carta  C23= new Carta(23,10,3,"copa");	 cartas.add(C23);
		Carta  C24= new Carta(24,1,4,"copa");	 cartas.add(C24);
		Carta  C25= new Carta(25,2,5,"copa");	 cartas.add(C25);
		Carta  C26= new Carta(26,3,6,"copa");	 cartas.add(C26);
		Carta  C27= new Carta(27,4,7,"copa");	 cartas.add(C27);
		Carta  C28= new Carta(28,5,10,"copa");	 cartas.add(C28);
		Carta  C29= new Carta(29,6,11,"copa");	 cartas.add(C29);
		Carta  C30= new Carta(30,7,12,"copa");	 cartas.add(C30);
		Carta  C31= new Carta(31,8,1,"oro");	 cartas.add(C31);
		Carta  C32= new Carta(32,9,2,"oro");	 cartas.add(C32);
		Carta  C33= new Carta(33,10,3,"oro");	 cartas.add(C33);
		Carta  C34= new Carta(34,1,4,"oro");	 cartas.add(C34);
		Carta  C35= new Carta(35,2,5,"oro");	 cartas.add(C35);
		Carta  C36= new Carta(36,3,6,"oro");	 cartas.add(C36);
		Carta  C37= new Carta(37,11,7,"oro");	 cartas.add(C37);
		Carta  C38= new Carta(38,5,10,"oro");	 cartas.add(C38);
		Carta  C39= new Carta(39,6,11,"oro");	 cartas.add(C39);
		Carta  C40= new Carta(40,7,12,"oro");	 cartas.add(C40);
*/
		
		System.out.println("Cartas Mazo------------------------------------");
		for (Carta c: m.getCartas()){
			System.out.println(c.toString());
			}
	
	
	
	
	Jugador j1  = new Jugador();

	j1.setUbicacion(1);
	j1.setApodo("jugador 1");
	
	js.add(j1);
	
	Jugador j2 = new Jugador();
	
	j2.setUbicacion(2);
	j2.setApodo("jugador 2");
	
	js.add(j2);
	
	Jugador j3  = new Jugador();
	
	j3.setUbicacion(3);
	j3.setApodo("jugador 3");
	
	js.add(j3);
	
	Jugador j4  = new Jugador();
	
	j4.setUbicacion(4);
	j4.setApodo("jugador 4");
	
	js.add(j4);
	
	}

}
	
