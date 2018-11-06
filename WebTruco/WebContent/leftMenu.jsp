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
<tr class=lMenu><td onclick='loadDiv("principal", "Jugador?action=listInvites", null)'>Invitaciones pendientes</td></tr>
<tr class=lMenu><td>Ver ranking</td></tr>
<tr class=lMenu><td>Listar grupos</td></tr>
<tr class=lMenu><td>Crear grupo</td></tr>
<tr class=lMenu><td onclick="window.location.href = 'Jugador?action=logout'">Desloguearse</td></tr>
</table>
</body>
</html>