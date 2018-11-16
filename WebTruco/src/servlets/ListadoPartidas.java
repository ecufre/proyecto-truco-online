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
import dto.AccionDTO;
import dto.CartaDTO;
import dto.EnviteDTO;
import dto.JugadorDTO;
import dto.PartidaDTO;
import dto.PartidaPantallaDTO;
import enumeraciones.EstadoPartida;
import enumeraciones.TipoCanto;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;



@WebServlet("/Partidas")
public class ListadoPartidas  extends HttpServlet {
	private static final long serialVersionUID = -1434281182121847875L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = "index.jsp";
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
				if("mostrarPartida".equals(action)) {
					HttpSession session = request.getSession();
					JugadorDTO jugAct = (JugadorDTO)session.getAttribute("jugador");
					Integer idPartida = Integer.valueOf(request.getParameter("partidaId"));
					if (jugAct != null && idPartida != null) {
						PartidaDTO pd = new PartidaDTO();
						pd.setId(idPartida);
						AccionDTO ad = new AccionDTO(pd, jugAct,1,true,null);
						request.setAttribute("partidaActual", bd.mostrarPartida(ad));
						jspPage = "partida.jsp";
					}
				}
				else if ("listarPartidasFinalizadas".equals(action)) {
					HttpSession session = request.getSession();
					JugadorDTO jugAct = (JugadorDTO)session.getAttribute("jugador");
					if (jugAct != null) {
						request.setAttribute("listaPartidas", bd.listarPartidasFinalizadasJugador(jugAct));
						request.setAttribute("miApodo", jugAct.getApodo());
						jspPage = "listaPartidas.jsp";
					}
				}
				else if ("jugarCarta".equals(action)) {
					HttpSession session = request.getSession();
					JugadorDTO jugAct = (JugadorDTO)session.getAttribute("jugador");
					Integer cartaId = Integer.valueOf(request.getParameter("cartaId"));
					Integer partidaId = Integer.valueOf(request.getParameter("partidaId"));
					if (jugAct != null && cartaId != null && partidaId != null) {
						PartidaDTO p = new PartidaDTO();
						p.setId(partidaId);
						AccionDTO ad = new AccionDTO(p, jugAct, 0, null, null);
						CartaDTO c = new CartaDTO();
						c.setId(cartaId);
						ad.setCarta(c);
						bd.JugarCarta(ad);
						jspPage = "partida.jsp";
						PartidaPantallaDTO pdto = bd.mostrarPartida(ad);
						request.setAttribute("partidaActual", pdto);
					}
				}
				else if ("jugadorListo".equals(action)) {
					HttpSession session = request.getSession();
					JugadorDTO jugAct = (JugadorDTO)session.getAttribute("jugador");
					Integer partidaId = Integer.valueOf(request.getParameter("partidaId"));
					jspPage = "mensaje.jsp";
					if (jugAct != null && partidaId != null) {
						PartidaDTO p = new PartidaDTO();
						p.setId(partidaId);
						AccionDTO ad = new AccionDTO(p, jugAct, 0, null, null);
						bd.jugadorListos(ad);
						request.setAttribute("mensaje", "Estas listo para jugar la partida");
						request.setAttribute("ok", "Estas listo para jugar la partida");
					}
					else {
						request.setAttribute("mensaje", "Ocurrio un error desconocido y misterioso");
					}
				}
				else if ("cantarEnvite".equals(action) || "responderEnvite".equals(action)) {
					HttpSession session = request.getSession();
					JugadorDTO jugAct = (JugadorDTO)session.getAttribute("jugador");
					Integer partidaId = Integer.valueOf(request.getParameter("partidaId"));
					String envite = request.getParameter("envite");
					String respuesta = request.getParameter("respuesta");
					jspPage = "mensaje.jsp";
					if (jugAct != null && envite != null && partidaId != null) {
						PartidaDTO p = new PartidaDTO();
						p.setId(partidaId);
						AccionDTO ad = new AccionDTO(p, jugAct, 0, null, null);
						TipoCanto tipoCanto = null;
						if (envite.equals("Envido")) {
							tipoCanto = TipoCanto.Envido;
						}
						else if (envite.equals("Envido envido")) {
							tipoCanto = TipoCanto.EnvidoEnvido;
						}
						else if (envite.equals("Real envido")) {
							tipoCanto = TipoCanto.RealEnvido;
						}
						else if (envite.equals("Falta envido")) {
							tipoCanto = TipoCanto.FaltaEnvido;
						}
						else if (envite.equals("Truco")) {
							tipoCanto = TipoCanto.Truco;
						}
						else if (envite.equals("Re truco")) {
							tipoCanto = TipoCanto.ReTruco;
						}
						else if (envite.equals("Vale cuatro")) {
							tipoCanto = TipoCanto.ValeCuatro;
						}
						if (tipoCanto != null) {
							if (respuesta == null) {
								EnviteDTO enviteDTO = new EnviteDTO(tipoCanto, false, false);
								ad.setEnvite(enviteDTO);
								bd.cantarEnvite(ad);
								request.setAttribute("mensaje", "Cantaste un envite");
								request.setAttribute("ok", "Cantaste un envite");
							}
							else {
								EnviteDTO enviteDTO;
								if (respuesta.equals("si") && tipoCanto.getValor() < 5) {
									enviteDTO = new EnviteDTO(tipoCanto, true, true);
								}
								else if (respuesta.equals("si") && tipoCanto.getValor() > 4) {
									enviteDTO = new EnviteDTO(tipoCanto, true, false);									
								}
								else {
									enviteDTO = new EnviteDTO(tipoCanto, false, false);
								}
								ad.setEnvite(enviteDTO);
								bd.responderEnvite(ad);
								request.setAttribute("mensaje", "Respondiste un envite");
								request.setAttribute("ok", "Respondiste un envite");
							}	
						}
					}
					else {
						request.setAttribute("mensaje", "Ocurrio un error desconocido y misterioso");
					}
				}
				else if ("retirarse".equals(action)) {
					HttpSession session = request.getSession();
					JugadorDTO jugAct = (JugadorDTO)session.getAttribute("jugador");
					Integer partidaId = Integer.valueOf(request.getParameter("partidaId"));
					if (jugAct != null && partidaId != null) {
						PartidaDTO p = new PartidaDTO();
						p.setId(partidaId);
						AccionDTO ad = new AccionDTO(p, jugAct, 0, null, null);
						bd.Retirarse(ad);
						jspPage = "partida.jsp";
						PartidaPantallaDTO pdto = bd.mostrarPartida(ad);
						request.setAttribute("partidaActual", pdto);
					}
				}
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
