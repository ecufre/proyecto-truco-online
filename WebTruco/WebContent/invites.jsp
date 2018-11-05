<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dto.JugadorDTO"%>
<%@ page import="dto.InvitacionDTO"%>
<%@ page import="java.util.ArrayList"%>


<% ArrayList<InvitacionDTO> invitaciones = (ArrayList<InvitacionDTO>)request.getAttribute("invitacionesPendientes"); %>
<!DOCTYPE html>
<html>
<body>
<% if (invitaciones.size() > 0) { %>
<table>
<tr><th>Apodo</th><th>Partidas Jugadas</th><th>Puntos</th><th>Promedio</th><th colspan=2></th></tr>
<% }
else { %>
<b>No tenes invitaciones pendientes.</b>
<% }
for (InvitacionDTO i : invitaciones) { %>
<tr>
	<td><%=i.getRemitente().getApodo() %></td>
	<td><%=i.getRemitente().getCategoria().getPartidasJugadas() %></td>
	<td><%=i.getRemitente().getCategoria().getPuntosTotales() %></td>
	<td><%=i.getRemitente().getCategoria().getPromedio() %></td>
	<td><input type=button onclick='loadDiv("principal", "Jugador?action=aceptarInvitacion", "idInvitacion=<%=i.getId() %>&rtte=<%=i.getRemitente().getApodo() %>")' value="Aceptar"></td>
	<td><input type=button onclick='loadDiv("principal", "Jugador?action=rechazarInvitacion", "idInvitacion=<%=i.getId() %>&rtte=<%=i.getRemitente().getApodo() %>")' value="Rechazar"></td>
</tr>
<% }
if (invitaciones.size() == 0) {
%>
</table>
<% } %>
</body>
</html>