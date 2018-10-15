package tests;



import java.util.ArrayList;
import java.util.List;

import negocio.Carta;



public class CartaDAOv {

	
	private static CartaDAOv instancia;
	
	private CartaDAOv() {}
	
	public static CartaDAOv getInstance() {
		if(instancia == null)
			instancia = new CartaDAOv();
		return instancia;
	}
	
	
	public ArrayList<Carta> getCartas(){
			ArrayList<Carta> cartas = new ArrayList<Carta>();
		


		Carta  C1= new Carta(1,14,1,1,"espada");	 cartas.add(C1);
		Carta  C2= new Carta(2,9,2,2,"espada");	 cartas.add(C2);
		Carta  C3= new Carta(3,10,3,3,"espada");	 cartas.add(C3);
		Carta  C4= new Carta(4,1,4,4,"espada");	 cartas.add(C4);
		Carta  C5= new Carta(5,2,5,5,"espada");	 cartas.add(C5);
		Carta  C6= new Carta(6,3,6,6,"espada");	 cartas.add(C6);
		Carta  C7= new Carta(7,12,7,7,"espada");	 cartas.add(C7);
		Carta  C8= new Carta(8,5,0,10,"espada");	 cartas.add(C8);
		Carta  C9= new Carta(9,6,0,11,"espada");	 cartas.add(C9);
		Carta  C10= new Carta(10,7,0,12,"espada");	 cartas.add(C10);
		Carta  C11= new Carta(11,13,1,1,"basto");	 cartas.add(C11);
		Carta  C12= new Carta(12,9,2,2,"basto");	 cartas.add(C12);
		Carta  C13= new Carta(13,10,3,3,"basto");	 cartas.add(C13);
		Carta  C14= new Carta(14,1,4,4,"basto");	 cartas.add(C14);
		Carta  C15= new Carta(15,2,5,5,"basto");	 cartas.add(C15);
		Carta  C16= new Carta(16,3,6,6,"basto");	 cartas.add(C16);
		Carta  C17= new Carta(17,4,7,7,"basto");	 cartas.add(C17);
		Carta  C18= new Carta(18,5,0,10,"basto");	 cartas.add(C18);
		Carta  C19= new Carta(19,6,0,11,"basto");	 cartas.add(C19);
		Carta  C20= new Carta(20,7,0,12,"basto");	 cartas.add(C20);
		Carta  C21= new Carta(21,8,1,1,"copa");	 cartas.add(C21);
		Carta  C22= new Carta(22,9,2,2,"copa");	 cartas.add(C22);
		Carta  C23= new Carta(23,10,3,3,"copa");	 cartas.add(C23);
		Carta  C24= new Carta(24,1,4,4,"copa");	 cartas.add(C24);
		Carta  C25= new Carta(25,2,5,5,"copa");	 cartas.add(C25);
		Carta  C26= new Carta(26,3,6,6,"copa");	 cartas.add(C26);
		Carta  C27= new Carta(27,4,7,7,"copa");	 cartas.add(C27);
		Carta  C28= new Carta(28,5,0,10,"copa");	 cartas.add(C28);
		Carta  C29= new Carta(29,6,0,11,"copa");	 cartas.add(C29);
		Carta  C30= new Carta(30,7,0,12,"copa");	 cartas.add(C30);
		Carta  C31= new Carta(31,8,1,1,"oro");	 cartas.add(C31);
		Carta  C32= new Carta(32,9,2,2,"oro");	 cartas.add(C32);
		Carta  C33= new Carta(33,10,3,3,"oro");	 cartas.add(C33);
		Carta  C34= new Carta(34,1,4,4,"oro");	 cartas.add(C34);
		Carta  C35= new Carta(35,2,5,5,"oro");	 cartas.add(C35);
		Carta  C36= new Carta(36,3,6,6,"oro");	 cartas.add(C36);
		Carta  C37= new Carta(37,11,7,7,"oro");	 cartas.add(C37);
		Carta  C38= new Carta(38,5,0,10,"oro");	 cartas.add(C38);
		Carta  C39= new Carta(39,6,0,11,"oro");	 cartas.add(C39);
		Carta  C40= new Carta(40,7,0,12,"oro");	 cartas.add(C40);
		
		
		return cartas;
		
		
	}
	
	
}
