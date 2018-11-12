
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
import javax.servlet.http.HttpSession;

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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(401);
		RequestDispatcher rd = request.getRequestDispatcher("asd");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	doGet(request, response);
		String div = request.getParameter("div");
		try {
			BusinessDelegate bd = BusinessDelegate.getInstance();

			PartidaDTO pd = new PartidaDTO();
			pd.setId(1);
			AccionDTO ad;
			PartidaPantallaDTO pdto;
			HttpSession session = request.getSession();
			JugadorDTO jugAct = (JugadorDTO)session.getAttribute("jugador");
			ad = new AccionDTO(pd, jugAct,1,true,null);

			pdto = bd.mostrarPartida(ad);

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
