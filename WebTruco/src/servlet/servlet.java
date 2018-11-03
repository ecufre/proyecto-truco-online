package servlet;

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

@WebServlet("/servlet")
public class servlet  extends HttpServlet {
	private static final long serialVersionUID = 8193537298020076840L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = "/index.jsp";
		try {
			String action = request.getParameter("action");
			BusinessDelegate bd = BusinessDelegate.getInstance();

			if ((action == null) || (action.length() < 1))
			{
				action = "default";
			}

			if ("default".equals(action))
			{
				jspPage = "/index.jsp";
			}
			else if ("login".equals(action)) {
				HttpSession session = request.getSession();
				String sessionId = session.getId();
				String apodo = request.getParameter("apodo");
				String password = request.getParameter("password");
				JugadorDTO jDTO = new JugadorDTO(apodo, "", password, sessionId);
				bd.login(jDTO);
				session.setAttribute("jugador", jDTO);
			}

		} catch (ComunicacionException e) {
			request.setAttribute("error", e.getMessage());
		} catch (LoggedInException e) {
			request.setAttribute("error", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher(jspPage);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
