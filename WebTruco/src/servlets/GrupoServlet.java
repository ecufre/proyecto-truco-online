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
import dto.JugadorDTO;
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

			if ("default".equals(action))
			{
				jspPage = "index.jsp";
			}
			else if ("crearGrupo".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				String nombreGrupo = (String)request.getParameter("nombreGrupo");
				if (jDTO != null && nombreGrupo != null && ! nombreGrupo.isEmpty()) {
					bd.crearGrupo(jDTO, nombreGrupo);
					request.setAttribute("jugadorDTO", bd.buscarJugadorDTO(jDTO.getApodo()));
					jspPage = "groups.jsp";
				}
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
