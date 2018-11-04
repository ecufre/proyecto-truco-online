<html>
<head>
<style>
tr.lMenu { background-color: white }
tr.lMenu:hover { background-color: red };
</style>
</head>
<body>
<table>
<tr class=lMenu><td onclick='loadDiv("mensajes", "Jugador?action=jugarSolo", null)'>Jugar Solo</td></tr>
<tr class=lMenu><td onclick='loadDiv("principal", "invite.jsp", null)'>Invitar a alguien</td></tr>
<tr class=lMenu><td>Invitaciones pendientes</td></tr>
<tr class=lMenu><td>Ver ranking</td></tr>
<tr class=lMenu><td>Listar grupos</td></tr>
<tr class=lMenu><td>Crear grupo</td></tr>
<tr class=lMenu><td><a href=Jugador?action=logout>Desloguearse</a></td></tr>
</table>
</body>
</html>