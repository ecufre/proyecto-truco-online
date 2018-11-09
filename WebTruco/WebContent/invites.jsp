<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dto.JugadorDTO"%>
<%@ page import="dto.InvitacionDTO"%>
<%@ page import="java.util.ArrayList"%>


<%
	ArrayList<InvitacionDTO> invitaciones = (ArrayList<InvitacionDTO>) request
			.getAttribute("invitacionesPendientes");
	JugadorDTO invitado = (JugadorDTO) request.getAttribute("invitado");
%>
<!DOCTYPE html>
<html>
<body>
	<br>
	<div class="col-md-12 center-block" style="float: none;">
		<div class="row">
			<!--   Basic Table  -->
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>Apodo</th>
									<th>Clasificacion</th>
									<th style="width:180px;"></th>
								</tr>
							</thead>
							<tbody>

								<%
									if (invitaciones == null || invitaciones.size() == 0) {
								%>
								<td colspan=3 align=center>No tenes invitaciones
									pendientes.</td>
								<%
									} else {
										for (InvitacionDTO i : invitaciones) {
								%>
								<tr>
									<td><%=i.getRemitente().getApodo()%></td>
									<td><%=i.getRemitente().getCategoria().getCategoria().getNombre()%></td>
									<td><button
											onclick='loadDiv("principal", "Jugador?action=aceptarInvitacion", "idInvitacion=<%=i.getId()%>&rtte=<%=i.getRemitente().getApodo()%>")' style="width:80px;">Aceptar
										</button>
										<button
											onclick='loadDiv("principal", "Jugador?action=rechazarInvitacion", "idInvitacion=<%=i.getId()%>&rtte=<%=i.getRemitente().getApodo()%>")' style="width:80px;">Rechazar</button></td>
								</tr>
								<%
									}
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="panel-body">
					<div class="input-group">
						<input class="form-control" placeholder="Invitar jugador"
							id="apodo"> <span class="form-group input-group-btn">
							<button class="btn btn-default" type="button"
								onclick='loadDiv("inviteForm", "Jugador?action=jugarDuo", "apodo=" + getElementById("apodo").value + "&invitar=false")' style="width:100px;">Buscar</button>
						</span>
					</div>
					<div class="input-group" id="inviteForm"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>