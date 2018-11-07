<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulario de registro</title>
<!-- BOOTSTRAP STYLES-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="assets/css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
</head>
<body>
	<!-- /. NAV SIDE  -->
	<div id="page-inner">
		<div class="row">
			<div class="col-md-4 center-block" style="float: none;">
				<!-- Form Elements -->
				<div class="panel panel-default">
					<div class="panel-heading">Registro</div>
					<div class="panel-body" align=center>
						<div class="row">
							<form role="form" action="Jugador?action=signup" method="POST">
								<div class="form-group col-md-11 center-block" style="float: none;">
									<br> <input id="apodo" class="form-control"
										placeholder="Nombre de usuario" name="apodo" required
										autofocus><br> <input id="mail"
										class="form-control" placeholder="Email" name="mail" required><br>
									<input id="password" type=password class="form-control"
										placeholder="Contraseña" name="password" required> <br>
									<input id="password2" type=password class="form-control"
										placeholder="Repita su Constraseña" required><br>
								</div>
								<button type="submit" class="btn btn-login">Registrarse</button>
								<button type="reset" class="btn btn-default">Limpiar</button>
								<button class="btn btn-default"
									onclick="window.location.href = 'index.jsp'">Cancelar</button>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- End Form Elements -->
		</div>
	</div>
	<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
	<!-- JQUERY SCRIPTS -->
	<script src="assets/js/jquery-1.10.2.js"></script>
	<!-- BOOTSTRAP SCRIPTS -->
	<script src="assets/js/bootstrap.min.js"></script>
	<!-- METISMENU SCRIPTS -->
	<script src="assets/js/jquery.metisMenu.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script src="assets/js/custom.js"></script>
</body>
</html>