<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="delegado.BusinessDelegate"%>
<%@ page import="dto.JugadorDTO"%>
<%@ page import="dto.PartidaPantallaDTO"%>
<%@ page import="dto.PartidaDTO"%>
<%@ page import="dto.AccionDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="assets/js/misJs/accion.js"></script>

</head>
<body>
	<%
		JugadorDTO jugador = null;
		try {
			jugador = (JugadorDTO) request.getSession().getAttribute("jugador");
			BusinessDelegate bd = BusinessDelegate.getInstance();
			bd.isLoggedIn(jugador);
		
			
			
			
		} catch (Exception e) {
			//response.sendRedirect("login.jsp");
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
				<a class="navbar-brand" href="index.html">Truco ATR</a>
			</div>
			<div
				style="color: white; padding: 15px 50px 5px 50px; float: right; font-size: 16px;">
				Jugador: <% if (jugador != null) out.print(jugador.getApodo()); %>
				<a href="Jugador?action=logout"
					class="btn btn-danger square-btn-adjust">Logout</a>
			</div>
		</nav>
		<!-- /. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="main-menu">
					<li><a onClick='loadDiv("mensajes", "Jugador?action=jugarSolo", null)' data-toggle="collapse" data-target=".sidebar-collapse.in"><i
							class="fa fa-user fa-2x"></i>Jugar Solo</a></li>
					<li><a onClick='loadDiv("principal", "invite.jsp", null)' data-toggle="collapse" data-target=".sidebar-collapse.in"><i class="fa fa-user-plus fa-2x"></i>Invitar
							a Alguien</a></li>
					<li><a onclick='loadDiv("principal", "Jugador?action=listInvites", null)' data-toggle="collapse" data-target=".sidebar-collapse.in"><i class="fa fa-envelope fa-2x"></i>Inivtaciones
							Pendientes</a></li>
					<li><a onclick='loadDiv("principal", "Jugador?action=listarRanking", null)' data-toggle="collapse" data-target=".sidebar-collapse.in"><i class="fa fa-sort-amount-desc fa-2x"></i>Ver
							Ranking</a></li>
					<li><a href="blank.html"><i class="fa fa-users fa-2x" data-toggle="collapse" data-target=".sidebar-collapse.in"></i>Grupos</a></li>
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
				<div align="center" id="principal" class="col-md-10" style="min-height:400px; background:#009933;">
				
				<div class="row" id="mesa">
				
				<div class="col-md-3" id="izquierda">	
					<img src="assets/img/cartas/4.PNG" class="carta" value="4" alt="Cinque Terre" onClick="alert(4)" style="transform: rotate(90deg);">
					<br>
					<img src="assets/img/cartas/0.PNG" class="carta" alt="Cinque Terre" onClick="alert(5)" style="transform: rotate(90deg);">
					<br>
					<img src="assets/img/cartas/0.PNG" class="carta" alt="Cinque Terre" onClick="alert(6)" style="transform: rotate(90deg);">
			</div>
				<div class="col-md-6">
				<div class="row" id="frente">
					<img src="assets/img/cartas/0.PNG" class="carta" alt="Cinque Terre" onClick="alert(1)" style="transform: rotate(180deg);">
					<img src="assets/img/cartas/0.PNG" class="carta" alt="Cinque Terre" onClick="alert(2)" style="transform: rotate(180deg);">
					<img src="assets/img/cartas/0.PNG" class="carta" alt="Cinque Terre" onClick="alert(3)" style="transform: rotate(180deg);">

				</div>
				<br>
				<br>
				<br>
				<br>
				<br>
				<div class="row" id="propias">	
					<img src="assets/img/cartas/0.PNG" class="carta" alt="Cinque Terre" onClick="alert(10)" >
					<img src="assets/img/cartas/0.PNG" class="carta" alt="Cinque Terre" onClick="alert(11)" >
					<img src="assets/img/cartas/0.PNG" class="carta" alt="Cinque Terre" onClick="alert(12)" >
				</div>
				
				
				</div>
				<div class="col-md-3" id="derecha">
					<img src="assets/img/cartas/0.PNG" class="carta" alt="Cinque Terre" onClick="alert(7)" style="transform: rotate(270deg);">
					<br>
					<img src="assets/img/cartas/0.PNG" class="carta" alt="Cinque Terre" onClick="alert(8)" style="transform: rotate(270deg);">
					<br>
					<img src="assets/img/cartas/0.PNG" class="carta" alt="Cinque Terre" onClick="alert(9)" style="transform: rotate(270deg);">
				</div>
				</div>
				<br>
				<div class="row">

				<div  id="contador" class="col-md-4"></div>
				<div class="col-md-4" id="cartasJugador">
				
					<img src="assets/img/cartas/0.PNG" class="carta"  id="carta1" name="1" alt="Cinque Terre" onClick="alert(13)" >
					<img src="assets/img/cartas/0.PNG" class="carta"  id="carta2" name="2"  alt="Cinque Terre" onClick="alert(14)" >
					<img src="assets/img/cartas/0.PNG" class="carta"  id="carta3" name="3"  alt="Cinque Terre" onClick="alert(15)" >
					
				
					</div>
				<div  id="puntosEnvido" class="col-md-4"></div>
				</div>
				<hr>
				<div class="row" id="botonera">
				 <div align="center" class="btn-group btn-group-sm">
   					 <button type="button" class="btn btn-danger" id="envido">ENVIDO</button>
    				 <button type="button" class="btn btn-danger" id="envidoEnvido">ENVIDO ENVIDO</button>
    				 <button type="button" class="btn btn-danger" id="realEnvido">REAL ENVIDO</button>
    				 <button type="button" class="btn btn-danger" id="faltaEnvido">FALTA ENVIDO</button>
    				 <button type="button" class="btn btn-danger" id="truco">TRUCO</button>
    				 <button type="button" class="btn btn-danger" id="reTruco">RE TRUCO</button>
 					 <button type="button" class="btn btn-danger" id="vale4">VALE 4</button>
 				 	 <button type="button" class="btn btn-danger" id="quiero">QUIERO</button>
 				 	 <button type="button" class="btn btn-danger" id="noQuiero">NO QUIERO</button>
 				 	 <button type="button" class="btn btn-danger" id="recargar">recargar</button>
 				 	 <button type="button" class="btn btn-danger" id="iniciar">iniciar</button>
 				 </div>
				</div>
				
				</div>
				<div id=rightMenu class="col-md-2">Partida 1</br>Partida 2</br>Partida 3</br></div>
			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
	<!-- /. WRAPPER  -->
	<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
	<!-- JQUERY SCRIPTS -->

	<!-- BOOTSTRAP SCRIPTS -->
	<script src="assets/js/bootstrap.min.js"></script>
	<!-- METISMENU SCRIPTS -->
	<script src="assets/js/jquery.metisMenu.js"></script>
	<!-- CUSTOM SCRIPTS -->
	
	<!-- DATA TABLE SCRIPTS -->
	<script src="assets/js/dataTables/jquery.dataTables.js"></script>
	<script src="assets/js/dataTables/dataTables.bootstrap.js"></script>
	
</body>
</html>