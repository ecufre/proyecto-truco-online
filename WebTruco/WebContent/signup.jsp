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
	<div class="row col-md-4 center-block"  style="float: none;">
	<%
						if (request.getAttribute("error") != null) {
					%>
				<div class="alert alert-danger fade in alert-dismissible">
					<div class="row">
						<a href="#" class="close" data-dismiss="alert" aria-label="close"
							title="close">×</a> <strong> <%=request.getAttribute("error")%>
						</strong>
					</div>
				</div>
				<%
						}
					%>
					</div>
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
										name="mail" onchange="validarForm();" required>
								</div>
								<div id="divpwd1" class="form-group col-lg-11 center-block"
									style="float: none;">
									<input id="password" type=password class="form-control"
										placeholder="Contraseña" name="password"
										onkeyup="validarForm();" required>
								</div>
								<div id="divpwd2" class="form-group col-lg-11 center-block"
									style="float: none;">
									<input id="password2" type=password class="form-control"
										placeholder="Repita su Constraseña"
										onkeyup="validarForm();" required>
								</div>
								<button id="submit" type="submit" class="btn btn-login" disabled>Registrarse</button>
								<button class="btn btn-default" onclick="limpiarForm();">Limpiar</button>
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
		function validarForm() {
			var pwd1 = document.getElementById("password");
			var pwd2 = document.getElementById("password2");
			var mail = document.getElementById("mail");
			var divPwd1 = document.getElementById("divpwd1");
			var divPwd2 = document.getElementById("divpwd2");
			var divMail = document.getElementById("divmail");
			var submit = document.getElementById("submit");
			
			
			var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			var disabledSubmit = false;
			
			if (pwd1.value == "" || pwd2.value == "" || mail.value == "") {
				disabledSubmit = true;
			}
			if (pwd1.value != "" && pwd2.value != "") {
				if (pwd2.value != pwd2.value) {
					divPwd1.classList.add("has-error");
					divPwd1.classList.remove("has-success");
					divPwd2.classList.add("has-error");
					divPwd2.classList.remove("has-success");
					disabledSubmit = true;
				}
				else {
					divPwd1.classList.add("has-success");
					divPwd1.classList.remove("has-error");
					divPwd2.classList.add("has-success");
					divPwd2.classList.remove("has-error");
				}
			}
			
			if (mail.value != "") {
				if (re.test(String(mail.value).toLowerCase())) {
					divMail.classList.add("has-success");
					divMail.classList.remove("has-error");
				}
				else {
					divMail.classList.add("has-error");
					divMail.classList.remove("has-success");
					disabledSubmit = true;
				}
			}
			
			if (disabledSubmit) {
				submit.setAttribute("disabled", "disabled");
			}
			else {
				submit.removeAttribute("disabled");
			}
		}
		
		function limpiarForm() {
			var apodo = document.getElementById("apodo");
			var pwd1 = document.getElementById("password");
			var pwd2 = document.getElementById("password2");
			var mail = document.getElementById("mail");
			var divPwd1 = document.getElementById("divpwd1");
			var divPwd2 = document.getElementById("divpwd2");
			var divMail = document.getElementById("divmail");
			var submit = document.getElementById("submit");
			apodo.value = "";
			pwd1.value = "";
			pwd2.value = "";
			mail.value = "";
			divPwd1.classList.remove("has-success");
			divPwd1.classList.remove("has-error");
			divPwd2.classList.remove("has-success");
			divPwd2.classList.remove("has-error");
			divMail.classList.remove("has-success");
			divMail.classList.remove("has-error");
			submit.setAttribute("disabled", "disabled");
		}
	</script>
</body>
</html>