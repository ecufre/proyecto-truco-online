<!-- 
Si soy admin
Tengo que poder:
Agregar gente
Remover gente
Crear parejas
Crear partidas

Todos deben poder:
Ver el ranking del grupo cerrado
 -->

<!-- Si es admin -->

<%@ page import="dto.JugadorDTO"%>
<%@ page import="dto.ParejaDTO"%>
<%@ page import="dto.GrupoDTO"%>
<%@ page import="java.util.ArrayList"%>
<%
	JugadorDTO jug = (JugadorDTO) session.getAttribute("jugador");
	GrupoDTO g = (GrupoDTO) request.getAttribute("grupoDTO");
	if (jug.getApodo().equals(g.getAdministrador().getApodo())) {
%>
<input type="hidden" id="idGrupo" value="<%=g.getId()%>">
<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">Administracion del grupo</div>
		<div class="panel-body">
			<div class="col-md-6">
				<div class="row">
					<div class="input-group">
						<input class="form-control" placeholder="Agergar jugador al grupo"
							id="apodo"> <span class="form-group input-group-btn">
							<button class="btn btn-default" type="button"
								onclick="loadDiv('principal', 'Grupo?action=agregarMiembro', 'idGrupo=<%=g.getId()%>&apodo=' + getElementById('apodo').value)">Agregar</button>
						</span>
					</div>
				</div>
				<div class="row">
					<div class=" table-wrapper-scroll-y">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>Apodo</th>
										<th>Clasificacion</th>
										<th>Accion</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (JugadorDTO j : g.getMiembros()) {
									%>
									<tr>
										<td style="vertical-align: middle">
											<%
												out.print(j.getApodo());
														if (j.getApodo().equals(g.getAdministrador().getApodo()))
															out.print(" (Admin)");
											%>
										</td>
										<td style="vertical-align: middle"><%=j.getCategoria().getCategoria().getNombre()%></td>
										<td style="vertical-align: middle">
											<%
												if (!j.getApodo().equals(g.getAdministrador().getApodo())) {
											%><button
												class="btn btn-default" type="button" onclick="loadDiv('principal', 'Grupo?action=eliminarMiembro', 'idGrupo=<%=g.getId()%>&apodo=<%=j.getApodo() %>')">Remover</button>
											<%
												}
											%>
										</td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="row">
					<label>Crear Pareja</label>
					<form action="" class="form-inline">
						<div class="form-group">
							<select class="form-control" id="jug1">
								<%
									for (JugadorDTO j : g.getMiembros()) {
								%>
								<option value="<%=j.getApodo()%>"><%=j.getApodo()%></option>
								<%
									}
								%>
							</select> <select class="form-control" id="jug2">
								<%
									for (JugadorDTO j : g.getMiembros()) {
								%>
								<option value="<%=j.getApodo()%>"><%=j.getApodo()%></option>
								<%
									}
								%>
							</select>
							<button class="btn btn-default" type="button" onclick="loadDiv('principal', 'Grupo?action=crearPareja', 'idGrupo=<%=g.getId()%>&jug1=' + getElementById('jug1').options[getElementById('jug1').selectedIndex].value + '&jug2=' + getElementById('jug1').options[getElementById('jug2').selectedIndex].value)">Crear
								Pareja</button>
						</div>
					</form>
				</div>
				<hr>
				<div class="row">
					<label>Crear Partida</label>
					<form action="" class="form">
						<div class="form-group">
							<select class="form-control">
								<%
									for (ParejaDTO p : g.getParejas()) {
								%>
								<option><%=p.getJugador1().getApodo()%> y
									<%=p.getJugador2().getApodo()%></option>
								<%
									}
								%>
							</select> contra <select class="form-control">
								<%
									for (ParejaDTO p : g.getParejas()) {
								%>
								<option><%=p.getJugador1().getApodo()%> y
									<%=p.getJugador2().getApodo()%></option>
								<%
									}
								%>
							</select>
							<button class="btn btn-default" type="button">Crear
								Partida</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- fin del menu de admin -->
<%
	}
%>
<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">Ranking del grupo</div>
		<div class="panel-body">
			<%
				if (request.getAttribute("ranking") != null) {
			%>
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover"
					id="dataTable">
					<thead>
						<tr>
							<th>Apodo</th>
							<th>Partidas Jugadas</th>
							<th>Puntos</th>
							<th>Promedio</th>
							<th>Categoria</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (JugadorDTO j : (ArrayList<JugadorDTO>) request.getAttribute("ranking")) {
						%>
						<tr class="odd gradeX">
							<td><%=j.getApodo()%></td>
							<td><%=j.getCategoria().getPartidasJugadas()%></td>
							<td><%=j.getCategoria().getPuntosTotales()%></td>
							<td><%=j.getCategoria().getPromedio()%></td>
							<td><%=j.getCategoria().getCategoria().getNombre()%></td>
						</tr>
						<%
										}
									%>
					</tbody>
				</table>
			</div>
			<% } %>
		</div>
	</div>
</div>