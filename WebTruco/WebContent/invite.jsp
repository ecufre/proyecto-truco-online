<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dto.JugadorDTO"%>
<% JugadorDTO invitado = (JugadorDTO)request.getAttribute("invitado"); %>    
<!DOCTYPE html>
<html>
<body>
</br>
Buscar Jugador:
<input <% if (invitado != null) out.print("disabled value=" + invitado.getApodo() + " "); %>type=text id=apodo><input type="button" <% if (invitado != null) out.print("disabled "); %>value="Buscar" onclick='loadDiv("principal", "Jugador?action=jugarDuo", "apodo=" + getElementById("apodo").value + "&invitar=false")'>
<% if (request.getAttribute("invitado") != null) { %>
<table>
<tr><th>Apodo</th><th>Partidas Jugadas</th><th>Puntos</th><th>Promedio</th><th></th></tr>
<tr><td><%=invitado.getApodo() %></td><td><%=invitado.getCategoria().getPartidasJugadas() %></td><td><%=invitado.getCategoria().getPuntosTotales() %></td><td><%=invitado.getCategoria().getPromedio() %></td><td><input type=button value="Confirmar invitacion" onclick='loadDiv("mensajes", "Jugador?action=jugarDuo", "apodo=" + getElementById("apodo").value + "&invitar=true")'>
</table>
<% } 
else if (request.getAttribute("buscado") != null) { %>
<script> loadDiv("mensajes", "mensaje.jsp", 'mensaje=Jugador%20No%20Encontrado')</script>
<% } %>
</body>
</html>