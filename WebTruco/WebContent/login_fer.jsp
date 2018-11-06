<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Logueate al truco</title>
<script src="include.js"></script>
</head>
<body>
	<div align=center>
	<form action=Jugador?action=login method="POST" target=_self>
		<table>
			<%
						String error = (String) request.getAttribute("error");
						if (error != null && ! error.equals("null")) {
					%>
			<tr>
				<td colspan=2 style="text-align: center; color: red"><b><%=error%></b></td>
			</tr>
			<%
						}
					%>
			<tr>
				<td align=right>Nombre de usuario</td>
				<td><input type=text name=apodo id=apodo></td>
			</tr>
			<tr>
				<td align=right>Constraseña</td>
				<td><input type=password name=password id=password></td>
			</tr>
			<tr>
				<td colspan=2 align=center><input type=submit value="Ingresar"></td>
			</tr>
			<tr>
				<td colspan=2 align=center><a href="signup.jsp">Crear cuenta</a></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>