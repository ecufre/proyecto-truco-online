<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "delegado.BusinessDelegate" %>
<%@ page import= "dto.JugadorDTO" %>
<% 
	try {
	JugadorDTO jugador = (JugadorDTO)request.getSession().getAttribute("jugador");
	BusinessDelegate bd = BusinessDelegate.getInstance();
	if (bd.isLoggedIn(jugador)) {%>
		<script type='text/javascript'>
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {document.getElementById('main').innerHTML = this.responseText;}};
	    xmlhttp.open("POST", 'main.jsp', true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    xmlhttp.send(null);
	    </script>
	<%}}
	catch (Exception e) {}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Jugate un truco</title>
</head>
<body>
<div align=center id="main">
<form action=servlet?action=login method="POST">
<table>
<%
	String error = (String)request.getAttribute("error");
	if (error != null) {%>
		<tr><td colspan=2><%=error %></td></tr>
	<%}

%>
<!-- Si viene de otro lado, traer en "error" un codigo y mostrar un mensaje en base al error -->
<tr><td align=right>Nombre de usuario</td><td><input type=text name=apodo></td></tr>
<tr><td align=right>Constraseña</td><td><input type=password name=password></td></tr>
<tr><td colspan=2 align=center><input type=submit value="Ingresar"></td></tr>
<tr><td colspan=2 align=center><a href="signup.jsp">Crear cuenta</a></td></tr>
</table>
</form>
</div>
</body>
</html>