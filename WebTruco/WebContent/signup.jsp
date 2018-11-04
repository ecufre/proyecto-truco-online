<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulario de registro</title>
</head>
<body>
<div align=center>
<form action=servlet?action=signup method="POST">
<table>
<tr><td>Nombre de usuario</td><td><input type=text name=apodo></td></tr>
<tr><td>Contraseña</td><td><input type=password name=password></td></tr>
<tr><td>Repita la contraseña</td><td><input type=password name=password2></td></tr>
<tr><td>Email</td><td><input type=text name=mail></td></tr>
<tr><td colspan=2><input type=submit value="Registrarse"></td></tr>
</table>
</form>
</div>
</body>
</html>