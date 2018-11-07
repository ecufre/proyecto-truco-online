<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"   

    %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="shortcut icon" href="assets/img/icono.ico">
    <title>Truco</title>
	<!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
	<!-- jquery STYLES-->
    <link href="assets/css/jquery-ui.css" rel="stylesheet" />

        <!-- CUSTOM STYLES-->
    <link href="assets/css/custom.css" rel="stylesheet" />
     <!-- GOOGLE FONTS-->
   <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
     <!-- TABLE STYLES-->
    <link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
	 <link href="assets/css/signin.css" rel="stylesheet">
</head>

        
  <body class="text-center">
  
 
	
    <div class="content col-lg-8">
    <% if (request.getAttribute("error") != null || (request.getParameter("error") != null && request.getParameter("error") != "null")) { %>
    </br>
    <div class="alert alert-danger fade in alert-dismissible">
	<a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>
  	 	<strong><% 
  	 	if (request.getAttribute("error") != null) out.print(request.getAttribute("error"));
  	 	else out.print(request.getParameter("error"));
  	 	%></strong>
	</div>
	<% } %>
	<form class="form-signin" id="idLogIn" method="post" action="Jugador?action=login" autocomplete="off">
      <img class="mb-4" src="assets/img/logo.png" alt="" width="320" height="170">
     <br>
	<br>
	
      <input type="input" id="apodo" name="apodo" class="form-control" placeholder="Usuario" required autofocus>
	  <br>
      <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
     
	  <br>
      <button class="btn btn-lg btn-login btn-block" type="submit">Ingresar</button>
      <br>
      <button class="btn btn-lg btn-login btn-block" onclick="window.location.href = 'signup.jsp'">Registrarse</button>
    </div>
 
     <!-- /. WRAPPER class="center-block"  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="assets/js/jquery-1.12.4.js"></script>
	<script src="assets/js/jquery-ui.js"></script>
      <!-- BOOTSTRAP SCRIPTS -->
    <script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/misJs/accion.js">	</script>
 
</body>
</html>
