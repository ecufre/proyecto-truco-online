<%@ page import="dto.JugadorDTO"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- TABLE STYLES-->
<link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
</head>
<body>
	<div align=center>
		<%
			if (request.getAttribute("ranking") != null) {
		%>
		<div class="row">
			<div class="col-md-12">
				</br>
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-heading"> Ranking	</div>
					<div class="panel-body">
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
										<td><%=j.getCategoria().getCategoria().getNombre() %></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!--End Advanced Tables -->
			</div>
		</div>
		<%
			}
		%>
	</div>
</body>
</html>