<%@ page import="dto.JugadorDTO"%>
<% JugadorDTO invitado = (JugadorDTO)request.getAttribute("invitado"); %>

<% if (invitado != null) { %>
<input type="hidden" name="apodo" value="<%=invitado.getApodo() %>">
<input type="text" class="form-control"
	value="Categoria: <%=invitado.getCategoria().getCategoria().getNombre() %>"
	disabled>
<span class="form-group input-group-btn">
	<button class="btn btn-default" type="button" style="width:100px;" onclick='loadDiv("mensajes", "Jugador?action=jugarDuo", "apodo=" + getElementById("apodo").value + "&invitar=true")'>¡Invitar!</button>
</span>
<% } %>