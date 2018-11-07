<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="delegado.BusinessDelegate"%>
<%@ page import="dto.JugadorDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Jugate un truco</title>
<script src="include.js"></script>
</head>
<body>
<div id=main name=main align=center style="min-width: 950px;">
<%  try {
		JugadorDTO jugador = (JugadorDTO) request.getSession().getAttribute("jugador");
		BusinessDelegate bd = BusinessDelegate.getInstance();
		bd.isLoggedIn(jugador);
	} catch (Exception e) {response.sendRedirect("login.jsp");} %>
	<div id=main>
		<div id=left style="width: 300px; height: 100%; float: left"></div>
		<div id=center align=center
			style="min-width: 400px; width: calc(100% - 550px); height: 100%; float: left">
			<div id=mensajes align=center style="width: 100%"></div>
			<div id=principal align=center style="width: 100%"></div>
		</div>
		<div id=right style="width: 250px; height: 100%; float: left"></div>
	</div>
	<script type='text/javascript'>
		loadDiv("left", "leftMenu.jsp", null);
		loadDiv("principal", "tmp.jsp", null);
		loadDiv("right", "rightMenu.jsp", null);
	</script>
</div>
</body>
</html>