package servlets;

import java.io.IOException;
import java.time.ZoneOffset;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import delegado.BusinessDelegate;
import dto.JugadorDTO;
import dto.PartidaDTO;
import enumeraciones.EstadoPartida;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;



@WebServlet("/Partidas")
public class ListadoPartidas  extends HttpServlet {
	private static final long serialVersionUID = -1434281182121847875L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = null;
		String action = request.getParameter("action");
		if ((action == null) || (action.length() < 1))
		{
			action = "default";
		}
		try {
			BusinessDelegate bd = BusinessDelegate.getInstance();
			if ("listarMisPartidas".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				if (jDTO != null) {
					ArrayList<PartidaDTO> partidas = bd.listarMisPartidas(jDTO);
					JsonObjectBuilder json = Json.createObjectBuilder();
					JsonArrayBuilder partidasEnCursoJson = Json.createArrayBuilder();
					JsonArrayBuilder partidasPendientesJson = Json.createArrayBuilder();
					JsonObjectBuilder partidaJson;
					for (PartidaDTO p : partidas) {
						if (p.getEstado() == EstadoPartida.Pendiente) {
							partidaJson = Json.createObjectBuilder()
									.add("idPartida", p.getId())
									.add("lastUpdate", p.getFechaActualizacion().toEpochSecond(ZoneOffset.ofHours(0)));
							partidasPendientesJson.add(partidaJson);
						}
						else if (p.getEstado() == EstadoPartida.EnCurso) {
							partidaJson = Json.createObjectBuilder()
									.add("idPartida", p.getId())
									.add("lastUpdate", p.getFechaActualizacion().toEpochSecond(ZoneOffset.ofHours(0)))
									.add("miTurno", (p.getTurnoJugador().getApodo().equals(jDTO.getApodo())));
							 partidasEnCursoJson.add(partidaJson);
						}
					}
					json.add("Pendientes", partidasPendientesJson);
					json.add("EnCurso", partidasEnCursoJson);
					response.setContentType("application/json");
					response.getWriter().write(json.build().toString());
				}
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher(jspPage);
				rd.forward(request, response);
			}

		} catch (ComunicacionException e) {
			jspPage = "mensaje.jsp";
			request.setAttribute("mensaje", e.getMessage());
			response.setStatus(599);
			RequestDispatcher rd = request.getRequestDispatcher(jspPage);
			rd.forward(request, response);
		} catch (LoggedInException e) {
			jspPage = "login.jsp";
			request.setAttribute("error", e.getMessage());
			response.setStatus(401);
			RequestDispatcher rd = request.getRequestDispatcher(jspPage);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
