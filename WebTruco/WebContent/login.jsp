<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logueate al truco</title>
</head>
<body>
	<form action=servlet?action=login method="POST">
		<table>
			<%
						String error = (String) request.getAttribute("error");
						if (error != null) {
					%>
			<tr>
				<td colspan=2 style="text-align: center; color: red"><b><%=error%></b></td>
			</tr>
			<%
						}
					%>
			<tr>
				<td align=right>Nombre de usuario</td>
				<td><input type=text name=apodo></td>
			</tr>
			<tr>
				<td align=right>Constraseña</td>
				<td><input type=password name=password></td>
			</tr>
			<tr>
				<td colspan=2 align=center><input type=submit value="Ingresar"></td>
			</tr>
			<tr>
				<td colspan=2 align=center><a href="signup.jsp">Crear
						cuenta</a></td>
			</tr>
		</table>
	</form>
</body>
</html>