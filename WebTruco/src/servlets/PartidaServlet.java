package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import delegado.BusinessDelegate;
import dto.AccionDTO;
import dto.CartaDTO;
import dto.GrupoDTO;
import dto.JugadorDTO;
import dto.PartidaDTO;
import dto.PartidaPantallaDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;

@WebServlet("/Partida")
public class PartidaServlet  extends HttpServlet {
	private static final long serialVersionUID = -1434281182121847875L;
	JugadorDTO[] jugadores = new JugadorDTO[4];  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(401);
		RequestDispatcher rd = request.getRequestDispatcher("asd");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	doGet(request, response);
		String div = request.getParameter("div");
		if(div.equals("iniciar")){
			PartidaPantallaDTO pdto = iniciarPartida();
		try {
			mostrarPartida(pdto);
		} catch (ComunicacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoggedInException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else{
		System.out.println("llegue");
		try {
		PartidaDTO pd = new PartidaDTO();
		pd.setId(1);
		AccionDTO ad;
		PartidaPantallaDTO pdto;
		JugadorDTO jugAct = jugadores[0]; //(JugadorDTO) request.getSession().getAttribute("jugador");
		ad = new AccionDTO(pd, jugAct,1,true,null);
		
		pdto = BusinessDelegate.getInstance().mostrarPartida(ad);
		
		//System.out.println(nombre);
		//System.out.println(nombre);
		if(div.equals("contador")){
		response.setContentType( "text/html; charset=iso-8859-1" );
		PrintWriter out = response.getWriter();
		out.println("<p>Juegos<br> Nosotros:"+ pdto.getJuegosNosotros()  +"Ellos:"+ pdto.getJuegosEllos()  +"<br>"
				+ 	"Puntos<br> Nosotros:"+ pdto.getPuntosJuegoNosotros()  +"Ellos:"+ pdto.getPuntosJuegoEllos() +"</p>");

		}
		if(div.equals("puntosEnvido")){
		response.setContentType( "text/html; charset=iso-8859-1" );
		PrintWriter out = response.getWriter();
		out.println("<p>Envido:<br>"
				+pdto.getJugador().getApodo()+ ": "+ pdto.getValorEnvidoJugador() 
				+pdto.getJugadorDerecha().getApodo()+": "+ pdto.getValorEnvidoJugadorDerecha() +"<br>"
				+pdto.getJugadorFrente().getApodo()+": "+ pdto.getValorEnvidoJugadorFrente() 
				+pdto.getJugadorIzquierda().getApodo()+": "+ pdto.getValorEnvidoJugadorIquierda()+"</p>");
				
		}
		
		if(div.equals("frente")){
			response.setContentType( "text/html; charset=iso-8859-1" );
			PrintWriter out = response.getWriter();
			int cc=1;
			for(CartaDTO c :pdto.getCartasMesaJugadorFrente()){
			out.println("<img src=\"assets/img/cartas/"+c.getNumero()+".PNG\" class=\"carta\" onClick=\"alert(1)\" style=\"transform: rotate(180deg);\">");
			cc++;
			}
			while(cc<=3){
				out.println("<img src=\"assets/img/cartas/0.PNG\" class=\"carta\" onClick=\"alert(1)\" style=\"transform: rotate(180deg);\">");
				cc++;
			}
		}

		if(div.equals("propias")){
			response.setContentType( "text/html; charset=iso-8859-1" );
			PrintWriter out = response.getWriter();
			int cc=1;
			for(CartaDTO c :pdto.getCartasMesaJugador()){
			out.println("<img src=\"assets/img/cartas/"+c.getNumero()+".PNG\" class=\"carta\" onClick=\"alert(1)\">");
			cc++;
			}
			while(cc<=3){
				out.println("<img src=\"assets/img/cartas/0.PNG\" class=\"carta\" onClick=\"alert(1)\">");
				cc++;
			}
		}		

		if(div.equals("izquierda")){
			response.setContentType( "text/html; charset=iso-8859-1" );
			PrintWriter out = response.getWriter();
			int cc=1;
			for(CartaDTO c :pdto.getCartasMesajugadorIzquierda()){
			out.println("<img src=\"assets/img/cartas/"+c.getNumero()+".PNG\" class=\"carta\" onClick=\"alert(1)\" style=\"transform: rotate(90deg);\">");
			out.println("<br>");
			cc++;
			}
			while(cc<=3){
				out.println("<img src=\"assets/img/cartas/0.PNG\" class=\"carta\" onClick=\"alert(1)\" style=\"transform: rotate(90deg);\">");
				out.println("<br>");
				cc++;
			}
			}	
		if(div.equals("derecha")){
			response.setContentType( "text/html; charset=iso-8859-1" );
			PrintWriter out = response.getWriter();
			int cc=1;
			for(CartaDTO c :pdto.getCartasMesaJugadorDerecha()){
			out.println("<img src=\"assets/img/cartas/"+c.getNumero()+".PNG\" class=\"carta\" onClick=\"alert(1)\" style=\"transform: rotate(270deg);\">");
			out.println("<br>");
			cc++;
			}
			while(cc<=3){
				out.println("<img src=\"assets/img/cartas/0.PNG\" class=\"carta\" onClick=\"alert(1)\" style=\"transform: rotate(270deg);\">");
				out.println("<br>");
				cc++;
			}
			}		
		
		
		if(div.equals("cartasJugador")){
			response.setContentType( "text/html; charset=iso-8859-1" );
			PrintWriter out = response.getWriter();
		
			for(CartaDTO c :pdto.getCartasMesaJugador()){
			out.println("<img src=\"assets/img/cartas/"+c.getNumero()+".PNG\" class=\"carta\" name=\"" +c.getNumero()+ "\" onClick=\"alert(1)\">");
			
			}
			
		}		

		if(div.equals("jugarCarta")){
			AccionDTO add = new AccionDTO(pd, jugAct, null, null, div);
			CartaDTO cc = new CartaDTO();
			String carta = request.getParameter("carta");
			cc.setId(Integer.parseInt(carta));
			add.setCarta(new CartaDTO());
			BusinessDelegate.getInstance().JugarCarta(add);
			response.setContentType( "text/html; charset=iso-8859-1" );
			PrintWriter out = response.getWriter();
		
			for(CartaDTO c :pdto.getCartasMesaJugador()){
			out.println("<img src=\"assets/img/cartas/"+c.getNumero()+".PNG\" class=\"carta\" name=\"" +c.getNumero()+ "\" onClick=\"alert(1)\">");
			
			}
			
		}			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}

	private PartidaPantallaDTO iniciarPartida() {
		try {
			BusinessDelegate bd = BusinessDelegate.getInstance();
			JugadorDTO admin = new JugadorDTO("Ced", "fer@fer", "Pass", "SessionID");
			bd.crearJugador(admin);
			JugadorDTO jugador1 = new JugadorDTO("San", "eze@eze", "Pass", "SessionID");
			bd.crearJugador(jugador1);			
			JugadorDTO jugador2 = new JugadorDTO("Bru", "kike@kike", "Pass", "SessionID");
			bd.crearJugador(jugador2);			
			JugadorDTO jugador3 = new JugadorDTO("Ale", "fer2@fer", "Pass", "SessionID");
			bd.crearJugador(jugador3);			
			JugadorDTO jugador4 = new JugadorDTO("Ger", "eze2@eze", "Pass", "SessionID");
			bd.crearJugador(jugador4);			
			JugadorDTO jugador = new JugadorDTO("Fer", "kike2@kike", "Pass", "SessionID");
			bd.crearJugador(jugador);	
/*

			bd.login(admin);
			bd.crearGrupo(admin, "La Cumbancha");
			GrupoDTO g = new GrupoDTO("La Cumbancha", 1);
			bd.agregarJugadorAGrupo(admin, g, "Ced");
			bd.agregarJugadorAGrupo(admin, g, "Fer");
			bd.agregarJugadorAGrupo(admin, g, "San");
			bd.agregarJugadorAGrupo(admin, g, "Ger");
			bd.crearPareja(admin, g, admin, jugador);
			bd.crearPareja(admin, g, jugador1, jugador4);
*/
			bd.jugarLibreIndividual(jugador1);
			bd.jugarLibreIndividual(jugador2);
			bd.jugarLibreIndividual(jugador3);
			bd.jugarLibreIndividual(jugador4);

			PartidaDTO pd = new PartidaDTO();
			System.out.println("Partida:");
			Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
			int valp = Integer.parseInt(entradaEscaner.nextLine());
			pd.setId(valp);
			AccionDTO ad1 = new AccionDTO(pd, jugador1, 1, false, "");
			bd.jugadorListos(ad1);
			ad1.setJugador(jugador2);
			bd.jugadorListos(ad1);
			ad1.setJugador(jugador3);
			bd.jugadorListos(ad1);
			ad1.setJugador(jugador4);
			bd.jugadorListos(ad1);


		} catch (ComunicacionException | LoggedInException e) {
			System.err.println(e.getMessage());
		}

		int val=0;
		System.out.println("Empezo la partida...");
		JugadorDTO jugador1 = new JugadorDTO("San", "mail1@mail", "Pass", "SessionID");
		JugadorDTO jugador2 = new JugadorDTO("Bru", "mail2@mail", "Pass", "SessionID");
		JugadorDTO jugador3 = new JugadorDTO("Ale", "mail3@mail", "Pass", "SessionID");
		JugadorDTO jugador4 = new JugadorDTO("Ger", "mail4@mail", "Pass", "SessionID");

		PartidaDTO pd = new PartidaDTO();
		pd.setId(1);
		//jugadores = new JugadorDTO[4];  
		jugadores[0] = jugador1;
		jugadores[1] = jugador2;
		jugadores[2] = jugador3;
		jugadores[3] = jugador4;

		AccionDTO ad;
		PartidaPantallaDTO pdto;
		JugadorDTO jugAct = jugadores[val];
		ad = new AccionDTO(pd, jugAct,1,true,null);
		try {
			pdto = BusinessDelegate.getInstance().mostrarPartida(ad);
			return pdto;
		} catch (ComunicacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoggedInException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	return null;
	}
	
	public static void mostrarPartida(PartidaPantallaDTO pd) throws ComunicacionException, LoggedInException{

		//		System.out.println(c3.mostrarPartida(ad));
		for (int i = 0; i < 2; i++) System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
		System.out.println("Juegos\nNosotros:"+pd.getJuegosNosotros()+" Ellos: "+pd.getJuegosEllos() );
		System.out.println("Puntos\nNosotros: "+pd.getPuntosJuegoNosotros()+" Ellos: "+pd.getPuntosJuegoEllos() );
		//		System.out.println("jugador:"			+pd.getJugador().getApodo()+" suma Envido: "+pd.getValorEnvidoJugador());
		//		System.out.println("jugador frente:"	+pd.getJugadorFrente().getApodo()+" suma Envido: "+pd.getValorEnvidoJugadorFrente());
		//		System.out.println("jugador IZQ:"		+pd.getJugadorIzquierda().getApodo()+" suma Envido: "+pd.getValorEnvidoJugadorIquierda());
		//		System.out.println("jugador DER:"		+pd.getJugadorDerecha().getApodo()+" suma Envido: "+pd.getValorEnvidoJugadorDerecha());
		for (int i = 0; i < 2; i++) System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.print("Ultimo canto: ");
		if (pd.getUltimoCanto() == null) System.out.println("No se canto nada todavía");
		else {
			System.out.print(pd.getUltimoCanto().getDescTipoCanto());
			if (pd.getUltimoCanto().isQuerido() == null) System.out.println(" - No fue respondido aun");
			else if (pd.getUltimoCanto().isQuerido()) System.out.println(" - Querido");
			else System.out.println(" - No querido");
		}
		for (int i = 0; i < 2; i++) System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Cartas Jugador Mesa: "+pd.getJugador().getApodo());
		for(CartaDTO c :pd.getCartasMesaJugador()) System.out.println(c.getNumero() + " de " + c.getPalo() + " (" + c.getId() + ")");
		for (int i = 0; i < 2; i++) System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Cartas Jugador Mesa Frente: "+pd.getJugadorFrente().getApodo());
		for(CartaDTO c :pd.getCartasMesaJugadorFrente()) System.out.println(c.getNumero() + " de " + c.getPalo() + " (" + c.getId() + ")");
		for (int i = 0; i < 2; i++) System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Cartas Jugador Mesa Izquieda: "+pd.getJugadorIzquierda().getApodo());
		for(CartaDTO c :pd.getCartasMesajugadorIzquierda()) System.out.println(c.getNumero() + " de " + c.getPalo() + " (" + c.getId() + ")");
		for (int i = 0; i < 2; i++) System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Cartas Jugador Mesa Derecha: "+pd.getJugadorDerecha().getApodo());
		for(CartaDTO c :pd.getCartasMesaJugadorDerecha()) System.out.println(c.getNumero() + " de " + c.getPalo() + " (" + c.getId() + ")");
		for (int i = 0; i < 2; i++) System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Cartas Jugador: " + pd.getJugador().getApodo());
		for(CartaDTO c :pd.getCartasJugador()) System.out.println(c.getNumero() + " de " + c.getPalo() + " (" + c.getId() + ")");
		for (int i = 0; i < 2; i++) System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Turno de: " + pd.getTurnoJugador().getApodo());
		System.out.println("Jugando: " + pd.getJugador().getApodo());
	}		
	
}
