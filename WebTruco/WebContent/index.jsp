<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="delegado.BusinessDelegate"%>
<%@ page import="dto.JugadorDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Jugate un truco</title>
</head>
<body>
<script type='text/javascript'>
function loadDiv(divName, url, postInfo) {
	document.getElementById("mensajes").innerHTML = "";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {document.getElementById(divName).innerHTML = this.responseText;}
	else if (this.readyState == 4 && this.status == 599) {document.getElementById("mensajes").innerHTML = this.responseText;}
	else if (this.readyState == 4 && this.status == 598) {document.getElementById("main").innerHTML = this.responseText;}};
    xmlhttp.open("POST", url, true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send(postInfo);
}
</script>
	<div id=main align=center style="min-width: 950px;">
		<div id=left style="width: 300px; height: 100%; float: left"></div>
		<div id=center align=center style="min-width: 400px; width: calc(100% - 550px); height: 100%; float: left">
			<div id=mensajes align=center style="width: 100%"></div>
			<div id=principal align=center style="width: 100%"></div>
		</div>
		<div id=right style="width: 250px; height: 100%; float: left"></div>
	</div>
	
<%
	try {
		JugadorDTO jugador = (JugadorDTO) request.getSession().getAttribute("jugador");
		BusinessDelegate bd = BusinessDelegate.getInstance();
		if (bd.isLoggedIn(jugador)) {
%>
<script type='text/javascript'>
loadDiv("left", "leftMenu.jsp", null);
loadDiv("principal", "main.jsp", null);
loadDiv("right", "rightMenu.jsp", null);
</script>
<%
	}
	} catch (Exception e) {
%>
<script type='text/javascript'>
		loadDiv("main", "login.jsp", 'error=<%=request.getAttribute("error")%>');
		</script>
<%
	}
%>
</body>
</html>