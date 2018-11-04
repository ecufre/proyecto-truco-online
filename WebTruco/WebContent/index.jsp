<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="delegado.BusinessDelegate"%>
<%@ page import="dto.JugadorDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Jugate un truco</title>
<script type='text/javascript'>
function loadDiv(divName, url, postInfo) {
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {document.getElementById(divName).innerHTML = this.responseText;}};
    xmlhttp.open("POST", url, true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send(postInfo);
}
</script>
</head>
<body>
	<div id=top align=center style="min-width: 950px;">
		<div id=left style="width: 300px; height: 100%; float: left"></div>
		<div id=center align=center style="min-width: 400px; width: calc(100% - 550px); height: 100%; float: left"></div>
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
loadDiv("center", "main.jsp", null);
loadDiv("right", "rightMenu.jsp", null);
</script>
<%
	}
	} catch (Exception e) {
%>
<script type='text/javascript'>
		loadDiv("center", "login.jsp", "error=<%=request.getAttribute("error")%>");
		</script>
<%
	}
%>
</body>
</html>