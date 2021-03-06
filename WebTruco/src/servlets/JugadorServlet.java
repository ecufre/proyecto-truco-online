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
import dto.InvitacionDTO;
import dto.JugadorDTO;
import enumeraciones.TipoCanto;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;

@WebServlet("/Jugador")
public class JugadorServlet  extends HttpServlet {
	private static final long serialVersionUID = 8193537298020076840L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = "index.jsp";
		try {
			String action = request.getParameter("action");
			BusinessDelegate bd = BusinessDelegate.getInstance();

			if ((action == null) || (action.length() < 1))
			{
				action = "default";
			}

			if ("login".equals(action)) {
				HttpSession session = request.getSession();
				String sessionId = session.getId();
				String apodo = request.getParameter("apodo");
				String password = request.getParameter("password");
				if (apodo != null && password != null) {
					JugadorDTO jDTO = new JugadorDTO(apodo, "", password, sessionId);
					try {
						bd.login(jDTO);
						jDTO.setPassword(null);
						session.setAttribute("jugador", jDTO);
					}
					catch (ComunicacionException e) {
						jspPage = "login.jsp";
						request.setAttribute("error", e.getMessage());
					}					
				}
			}
			else if ("signup".equals(action)) {
				HttpSession session = request.getSession();
				String apodo = request.getParameter("apodo");
				String password = request.getParameter("password");
				String mail = request.getParameter("mail");
				String sessionId = session.getId();
				if (apodo != null && password != null && mail != null) {
					JugadorDTO jDTO = new JugadorDTO(apodo, mail, password, sessionId);
					try {
						bd.crearJugador(jDTO);
					}
					catch (ComunicacionException e) {
						jspPage = "signup.jsp";
						request.setAttribute("error", e.getMessage());
					}
					session.setAttribute("jugador", jDTO);
				}
			}
			else if ("logout".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				if (jDTO != null) {
					bd.logout(jDTO);
					session.invalidate();
					jspPage = "login.jsp";
					request.setAttribute("error", "Usuario desconectado correctamente");
				}
			}
			else if ("jugarSolo".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				jspPage = "mensaje.jsp";
				if (jDTO != null) {
					try {
						bd.jugarLibreIndividual(jDTO);
						request.setAttribute("mensaje", "Fuiste agregado a la lista de espera");
						request.setAttribute("ok", "Fuiste agregado a la lista de espera");
					}
					catch (ComunicacionException e) {
						request.setAttribute("mensaje", "Ya estabas en la lista de espera");
					}
				}
			}
			else if ("jugarDuo".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				if (jDTO != null) {
					if (request.getParameter("invitar").equals("false")) {
						JugadorDTO jugador = bd.buscarJugadorDTO(request.getParameter("apodo"));
						jspPage = "invite.jsp";
						request.setAttribute("invitado", jugador);
						request.setAttribute("buscado", true);
					}
					else {
						bd.jugarLibrePareja(jDTO, request.getParameter("apodo"));
						jspPage = "mensaje.jsp";
						request.setAttribute("mensaje", "Invitacion enviada");
						request.setAttribute("ok", "Invitacion enviada");
					}
				}
			}
			else if ("listInvites".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				if (jDTO != null) {
					request.setAttribute("invitacionesPendientes", bd.listarInvitacionesPendientes(jDTO));
					jspPage = "invites.jsp";
				}
			}
			else if ("aceptarInvitacion".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				if (jDTO != null) {
					JugadorDTO rtte = new JugadorDTO(request.getParameter("rtte"));
					InvitacionDTO iDTO = new InvitacionDTO(rtte, Integer.valueOf(request.getParameter("idInvitacion")));
					bd.aceptarInvitacion(jDTO, iDTO);
					request.setAttribute("invitacionesPendientes", bd.listarInvitacionesPendientes(jDTO));
					jspPage = "invites.jsp";
				}
			}
			else if ("rechazarInvitacion".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				if (jDTO != null) {
					JugadorDTO rtte = new JugadorDTO(request.getParameter("rtte"));
					InvitacionDTO iDTO = new InvitacionDTO(rtte, Integer.valueOf(request.getParameter("idInvitacion")));
					bd.rechazarInvitacion(jDTO, iDTO);
					request.setAttribute("invitacionesPendientes", bd.listarInvitacionesPendientes(jDTO));
					jspPage = "invites.jsp";
				}
			}
			else if ("listarRanking".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				if (jDTO != null) {
					request.setAttribute("ranking", bd.listarRanking());
					jspPage = "ranking.jsp";
				}
			}
			else if ("listGroups".equals(action)) {
				HttpSession session = request.getSession();
				JugadorDTO jDTO = (JugadorDTO)session.getAttribute("jugador");
				if (jDTO != null && bd.isLoggedIn(jDTO)) {
					request.setAttribute("jugadorDTO", bd.buscarJugadorDTO(jDTO.getApodo()));
					jspPage = "groups.jsp";
				}
			}
			else {
				response.setStatus(404);
			}
		} catch (ComunicacionException e) {
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
