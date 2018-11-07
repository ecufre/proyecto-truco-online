<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
								<div class="form-group col-lg-11 center-block"
									style="float: none;">
									<br> <input id="apodo" class="form-control"
										placeholder="Nombre de usuario" name="apodo" required
										autofocus>
								</div>
								<div id="divmail" class="form-group col-lg-11 center-block"
									style="float: none;">
									<input id="mail" class="form-control" placeholder="Email"
										name="mail" onchange="validarMail();" required>
								</div>
								<div id="divpwd1" class="form-group col-lg-11 center-block"
									style="float: none;">
									<input id="password" type=password class="form-control"
										placeholder="Contraseña" name="password"
										onkeyup="revisarPassword();" required></div><div id="divpwd2" class="form-group col-lg-11 center-block"
									style="float: none;"><input
										id="password2" type=password class="form-control"
										placeholder="Repita su Constraseña"
										onkeyup="revisarPassword();" required>
								</div>
								<button id="submit" type="submit" class="btn btn-login" disabled>Registrarse</button>
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
	<script>
		function revisarPassword() {
			var pwd1 = document.getElementById("password");
			var pwd2 = document.getElementById("password2");
			var div1 = document.getElementById("divpwd1");
			var div2 = document.getElementById("divpwd2");
			var submit = document.getElementById("submit");
			if (pwd1.value == "" || pwd2.value == "") {
				submit.setAttribute("disabled", "disabled");
			} else {
				if (pwd1.value != pwd2.value) {
					div1.classList.add("has-error");
					div1.classList.remove("has-success");
					div2.classList.add("has-error");
					div2.classList.remove("has-success");
					submit.setAttribute("disabled", "disabled");
				} else {
					div1.classList.add("has-success");
					div1.classList.remove("has-error");
					div2.classList.add("has-success");
					div2.classList.remove("has-error");
					submit.removeAttribute("disabled");
				}
			}
			validarMail();
		}

		function validarMail() {
			var mail = document.getElementById("mail")
			var div = document.getElementById("divmail")
			var submit = document.getElementById("submit");
			var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			if (mail.value == "") {
				submit.setAttribute("disabled", "disabled");
			}
			else {
				if (re.test(String(mail.value).toLowerCase())) {
					div.classList.add("has-success");
					div.classList.remove("has-error");
					submit.removeAttribute("disabled");
				}
				else {
					div.classList.remove("has-success");
					div.classList.add("has-error");
					submit.setAttribute("disabled", "disabled");
				}
			}
			revisarPassword();
		}
	</script>
</body>
</html>