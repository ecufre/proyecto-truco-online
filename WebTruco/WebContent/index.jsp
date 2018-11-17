<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="delegado.BusinessDelegate"%>
<%@ page import="dto.JugadorDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="assets/img/icono.ico">
<title>Truco ATR</title>
<!-- BOOTSTRAP STYLES-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="assets/css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />

<script src=assets/js/include.js></script>
</head>
<body>
	<%
		JugadorDTO jugador = null;
		try {
			jugador = (JugadorDTO) request.getSession().getAttribute("jugador");
			BusinessDelegate bd = BusinessDelegate.getInstance();
			bd.isLoggedIn(jugador);
		} catch (Exception e) {
			response.sendRedirect("/WebTruco/login.jsp");
		}
	%>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-cls-top " role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/WebTruco/">Truco ATR</a>
			</div>
			<div
				style="color: white; padding: 15px 50px 5px 50px; float: right; font-size: 16px;">
				Jugador:
				<%
				if (jugador != null)
					out.print(jugador.getApodo());
			%>
				<a href="Jugador?action=logout"
					class="btn btn-danger square-btn-adjust">Logout</a>
			</div>
		</nav>
		<!-- /. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="main-menu">
					<li><a
						onClick='loadDiv("mensajes", "Jugador?action=jugarSolo", null)'
						data-toggle="collapse" data-target=".sidebar-collapse.in"><i
							class="fa fa-user fa-2x"></i>Jugar Solo</a></li>
					<li><a
						onClick='loadDiv("principal", "Jugador?action=listInvites", null)'
						data-toggle="collapse" data-target=".sidebar-collapse.in"><i
							class="fa fa-user-plus fa-2x"></i>Jugar en Pareja</a></li>
					<li><a
						onclick='loadDiv("principal", "Jugador?action=listGroups", null)'
						data-toggle="collapse" data-target=".sidebar-collapse.in"><i
							class="fa fa-users fa-2x"></i>Grupos</a></li>
					<li><a
						onclick='loadDiv("principal", "Jugador?action=listarRanking", null)'
						data-toggle="collapse" data-target=".sidebar-collapse.in"><i
							class="fa fa-trophy fa-2x"></i>Ver Ranking</a></li>
					<li><a
						onclick='loadDiv("principal", "Partidas?action=listarPartidasFinalizadas", null)'
						data-toggle="collapse" data-target=".sidebar-collapse.in"><i
							class="fa fa-trash-o fa-2x"></i>Partidas Finalizadas</a></li>

				</ul>

			</div>

		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<div align=center id=mensajes></div>
					</div>
				</div>
				<!-- /. ROW  -->
				<div align=center id="principal" class="col-lg-10"></div>
				<!-- background:#009933; -->
				<div id=rightMenu class="col-lg-2">
					<div class="panel panel-default">
						<div class="panel-heading">Partidas</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-hover"
									id="tablaPendientes">
									<thead>
										<tr>
											<th>Partidas Pendientes</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
								<table class="table table-bordered table-hover"
									id="tablaEnCurso">
									<thead>
										<tr>
											<th>Partidas En Curso</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
	<!-- /. WRAPPER  -->
	<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
	<!-- JQUERY SCRIPTS -->
	<script src="assets/js/jquery-1.10.2.js"></script>
	<!-- BOOTSTRAP SCRIPTS -->
	<script src="assets/js/bootstrap.min.js"></script>
	<!-- METISMENU SCRIPTS -->
	<script src="assets/js/jquery.metisMenu.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script src="assets/js/custom.js"></script>
	<script src="assets/js/misJs/accion.js"></script>
	<!-- DATA TABLE SCRIPTS -->
	<script src="assets/js/dataTables/jquery.dataTables.js"></script>
	<script src="assets/js/dataTables/dataTables.bootstrap.js"></script>
</body>
</html>