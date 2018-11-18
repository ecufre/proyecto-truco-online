package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import delegado.BusinessDelegate;
import dto.GrupoDTO;
import dto.JugadorDTO;
import dto.ParejaDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;

@WebServlet("/Grupo")
public class GrupoServlet extends HttpServlet {
	private static final long serialVersionUID = 1316867336439164322L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = "index.jsp";
		try {
			String action = request.getParameter("action");
			BusinessDelegate bd = BusinessDelegate.getInstance();
			if ((action == null) || (action.length() < 1))
			{
				action = "default";
			}
			if ("crearGrupo".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				String nombreGrupo = (String)request.getParameter("nombreGrupo");
				if (jDTO != null && nombreGrupo != null && ! nombreGrupo.isEmpty()) {
					bd.crearGrupo(jDTO, nombreGrupo);
					request.setAttribute("jugadorDTO", bd.buscarJugadorDTO(jDTO.getApodo()));
					jspPage = "groups.jsp";
				}
			}
			else if ("mostrarGrupo".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				Integer idGrupo = Integer.valueOf(request.getParameter("idGrupo"));
				if (jDTO != null && idGrupo != null) {
					GrupoDTO g = bd.buscarGrupoDTO(idGrupo);
					request.setAttribute("grupoDTO", g);
					request.setAttribute("ranking", bd.calcularRankingCerrado(g));
					jspPage = "group.jsp";
				}
			}
			else if ("agregarMiembro".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				Integer idGrupo = Integer.valueOf(request.getParameter("idGrupo"));
				if (jDTO != null && idGrupo != null) {
					GrupoDTO g = bd.buscarGrupoDTO(idGrupo);
					bd.agregarJugadorAGrupo(jDTO, g, request.getParameter("apodo"));
					g = bd.buscarGrupoDTO(idGrupo);
					request.setAttribute("grupoDTO", g);
					request.setAttribute("ranking", bd.calcularRankingCerrado(g));
					jspPage = "group.jsp";
				}
			}
			else if ("eliminarMiembro".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				Integer idGrupo = Integer.valueOf(request.getParameter("idGrupo"));
				if (jDTO != null && idGrupo != null) {
					GrupoDTO g = bd.buscarGrupoDTO(idGrupo);
					JugadorDTO j = new JugadorDTO(request.getParameter("apodo"));
					bd.eliminarJugadorDeGrupo(jDTO, g, j);
					g = bd.buscarGrupoDTO(idGrupo);
					request.setAttribute("grupoDTO", g);
					request.setAttribute("ranking", bd.calcularRankingCerrado(g));
					jspPage = "group.jsp";
				}
			}
			else if ("crearPareja".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				Integer idGrupo = Integer.valueOf(request.getParameter("idGrupo"));
				if (jDTO != null && idGrupo != null) {
					GrupoDTO g = bd.buscarGrupoDTO(idGrupo);
					JugadorDTO j1 = new JugadorDTO(request.getParameter("jug1"));
					JugadorDTO j2 = new JugadorDTO(request.getParameter("jug2"));
					bd.crearPareja(jDTO, g, j1, j2);
					g = bd.buscarGrupoDTO(idGrupo);
					request.setAttribute("grupoDTO", g);
					request.setAttribute("ranking", bd.calcularRankingCerrado(g));
					jspPage = "group.jsp";
				}
			}
			else if ("crearPartida".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				Integer idGrupo = Integer.valueOf(request.getParameter("idGrupo"));
				if (jDTO != null && idGrupo != null) {
					GrupoDTO g = bd.buscarGrupoDTO(idGrupo);
					ParejaDTO p1 = new ParejaDTO(Integer.valueOf(request.getParameter("pareja1")));
					ParejaDTO p2 = new ParejaDTO(Integer.valueOf(request.getParameter("pareja2")));
					bd.crearPartida(jDTO, g, p1, p2);
					g = bd.buscarGrupoDTO(idGrupo);
					request.setAttribute("grupoDTO", g);
					request.setAttribute("ranking", bd.calcularRankingCerrado(g));
					jspPage = "group.jsp";
				}
			}
			else {
				response.setStatus(404);
			}
		}
		catch (ComunicacionException e) {
			jspPage = "mensaje.jsp";
			request.setAttribute("mensaje", e.getMessage());
			response.setStatus(599);
		} catch (LoggedInException e) {
			jspPage = "login.jsp";
			request.setAttribute("error", e.getMessage());
			response.setStatus(401);
		}
		RequestDispatcher rd = request.getRequestDispatcher(jspPage);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
