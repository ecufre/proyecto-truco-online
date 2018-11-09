<%@ page import="dto.JugadorDTO"%>
<%@ page import="dto.GrupoDTO"%>
<%
	JugadorDTO j = (JugadorDTO) request.getAttribute("jugadorDTO");
%>

<br>
<div class="row">
	<div class="col-md-12">
		<!-- Form Elements -->
		<div class="panel panel-default">
			<div class="panel-heading">Crear un nuevo grupo</div>
			<div class="panel-body">
				<div class="input-group">
					<input type="text" class="form-control" id="nombreGrupo"> <span
						class="form-group input-group-btn">
						<button class="btn btn-default" type="button" onclick="loadDiv('principal', 'Grupo?action=crearGrupo', 'nombreGrupo=' + getElementById('nombreGrupo').value);">Crear</button>
					</span>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">Mis grupos</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>Nombre del Grupo</th>
								<th>Administrador</th>
							</tr>
						</thead>
						<tbody>
							<%
								int i = 1;
								for (GrupoDTO g : j.getGrupos()) {
							%>
							<tr>
								<td><%=g.getId()%></td>
								<td><%=g.getNombre()%></td>
								<td><%=g.getAdministrador().getApodo()%></td>
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
</div>
