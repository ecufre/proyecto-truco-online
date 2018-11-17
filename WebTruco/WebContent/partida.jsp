<%@ page import="dto.PartidaPantallaDTO"%>
<%@ page import="dto.CartaDTO"%>
<%@ page import="java.time.ZoneOffset"%>
<%!public int getPto(int pts, int i) {
		int aux = pts - ((i - 1) * 5);
		if (aux >= 0 && aux <= 5)
			return aux;
		else if (aux > 5)
			return 5;
		else
			return 0;
	}%>
<%
	PartidaPantallaDTO partida = (PartidaPantallaDTO) request.getAttribute("partidaActual");
	if (partida == null) {
		out.print("La partida finalizo");
	}
	else {
%>
<input type=hidden id="updateActual_<%=partida.getPartidaID()%>"
	value=<%=partida.getUltimaActualizacion().toEpochSecond(ZoneOffset.ofHours(0))%>>
<div style="background: #009933;" class="row" id="partidaActual">
	<div class="row">
		<div class="col-xs-4 textoPartida" style="text-align:left; padding-left:22px;"><b><%=partida.getJugadorIzquierda().getApodo()%></b> <% if (partida.getValorEnvidoJugadorIquierda() != null) out.print("(Envido: " + partida.getValorEnvidoJugadorIquierda() + ")"); %></div>
		<div class="col-xs-4 textoPartida"><b><%=partida.getJugadorFrente().getApodo()%></b> <% if (partida.getValorEnvidoJugadorFrente() != null) out.print("(Envido: " + partida.getValorEnvidoJugadorFrente() + ")"); %></div>
		<div class="col-xs-4 textoPartida" style="text-align:right; padding-right:22px"><b><%=partida.getJugadorDerecha().getApodo()%></b> <% if (partida.getValorEnvidoJugadorDerecha() != null) out.print("(Envido: " + partida.getValorEnvidoJugadorDerecha() + ")"); %></div>
	</div>
	<div class="row" id="mesa">
		<div class="col-xs-3" id="izquierda">
			<%
				int cc = 1;
				for (CartaDTO c : partida.getCartasMesajugadorIzquierda()) {
			%>
			<img src="assets/img/cartas/<%=c.getId()%>.PNG" class="carta"
				value="<%=c.getId()%>" style="transform: rotate(90deg);"> <br>
			<%
				cc++;
				}

				while (cc <= 3) {
			%>
			<img src="assets/img/cartas/0.PNG" class="carta"
				style="transform: rotate(90deg);<%if (!partida.getJugadorIzquierda().getApodo().equals(partida.getTurnoJugador().getApodo()))
					out.print("opacity: 0.50;");%>"><br>
			<%
				cc++;
				}
			%>
		</div>
		<div class="col-xs-6">
			<div class="row" id="frente">
				<%
					cc = 1;
					for (CartaDTO c : partida.getCartasMesaJugadorFrente()) {
				%>
				<img src="assets/img/cartas/<%=c.getId()%>.PNG" class="carta"
					value="<%=c.getId()%>" style="transform: rotate(180deg);">
				<%
					cc++;
					}

					while (cc <= 3) {
				%>
				<img src="assets/img/cartas/0.PNG" class="carta"
					style="transform: rotate(180deg);<%if (!partida.getJugadorFrente().getApodo().equals(partida.getTurnoJugador().getApodo()))
					out.print("opacity: 0.50;");%>">
				<%
					cc++;
					}
				%>
			</div>
			<br>
			<div class="row" id="info">
				Ultimo canto:
				<%
				if (partida.getUltimoCanto() == null)
					out.println("No se canto nada todavía");
				else {
					out.print("<b>" + partida.getUltimoCanto().getDescTipoCanto() + " </b> por <b>"
							+ partida.getUltimoCanto().getApodoCantante() + "</b>");
					if (partida.getUltimoCanto().isQuerido() == null)
						out.println(" - No fue respondido aun");
					else if (partida.getUltimoCanto().isQuerido())
						out.println(" - Querido");
					else
						out.println(" - No querido");
				}
			%>
				<br>Es el turno de: <b> <%=partida.getTurnoJugador().getApodo()%></b>
				<br>
			</div>
			<div class="row" id="propias">
				<%
					cc = 1;
					for (CartaDTO c : partida.getCartasMesaJugador()) {
				%>
				<img src="assets/img/cartas/<%=c.getId()%>.PNG" class="carta"
					value="<%=c.getId()%>" style="transform: rotate(00deg);">
				<%
					cc++;
					}

					while (cc <= 3) {
				%>
				<img src="assets/img/cartas/00.PNG" class="carta">
				<%
					cc++;
					}
				%>
			</div>


		</div>
		<div class="col-xs-3" id="derecha">
			<%
				cc = 1;
				for (CartaDTO c : partida.getCartasMesaJugadorDerecha()) {
			%>
			<img src="assets/img/cartas/<%=c.getId()%>.PNG" class="carta"
				value="<%=c.getId()%>" style="transform: rotate(270deg);"> <br>
			<%
				cc++;
				}

				while (cc <= 3) {
			%>
			<img src="assets/img/cartas/0.PNG" class="carta"
				style="transform: rotate(270deg);<%if (!partida.getJugadorDerecha().getApodo().equals(partida.getTurnoJugador().getApodo()))
					out.print("opacity: 0.50;");%>"><br>
			<%
				cc++;
				}
			%>
		</div>
	</div>
	<br>
	<div class="row">

		<div id="contador" class="col-md-4"></div>
		<div class="col-md-4" id="cartasJugador">

			<%
				cc = 1;
				for (CartaDTO c : partida.getCartasJugador()) {
			%>
			<img src="assets/img/cartas/<%=c.getId()%>.PNG" class="carta"
				value="<%=c.getId()%>"
				onclick="loadDiv('principal', 'Partidas?action=jugarCarta', 'cartaId=<%=c.getId()%>&partidaId=<%=partida.getPartidaID()%>')"
				style="transform: rotate(0deg);<%if (!partida.getJugador().getApodo().equals(partida.getTurnoJugador().getApodo()))
					out.print("opacity: 0.50;");%>">
			<%
				cc++;
				}

				while (cc <= 3) {
			%>
			<img src="assets/img/cartas/00.PNG" class="carta">
			<%
				cc++;
				}
			%>

		</div>
		<div id="puntosEnvido" class="col-md-4"></div>
	</div>
	<hr>
	<div class="row" id="botonera">
		<div align="center" class="btn-group-sm">
			<button class="btn btn-danger" data-toggle="modal"
				data-target="#modalPuntos">Puntos</button>
			<br>
			<button type="button" class="btn btn-danger textoPartida"
				onclick="loadDiv('mensajes', 'Partidas?action=cantarEnvite', 'partidaId=<%=partida.getPartidaID()%>&envite=Envido')">ENVIDO</button>
			<button type="button" class="btn btn-danger textoPartida"
				onclick="loadDiv('mensajes', 'Partidas?action=cantarEnvite', 'partidaId=<%=partida.getPartidaID()%>&envite=Envido envido')">ENVIDO
				ENVIDO</button>
			<button type="button" class="btn btn-danger textoPartida"
				onclick="loadDiv('mensajes', 'Partidas?action=cantarEnvite', 'partidaId=<%=partida.getPartidaID()%>&envite=Real envido')">REAL
				ENVIDO</button>
			<button type="button" class="btn btn-danger textoPartida"
				onclick="loadDiv('mensajes', 'Partidas?action=cantarEnvite', 'partidaId=<%=partida.getPartidaID()%>&envite=Falta envido')">FALTA
				ENVIDO</button>
			<br>
			<button type="button" class="btn btn-danger textoPartida"
				onclick="loadDiv('mensajes', 'Partidas?action=cantarEnvite', 'partidaId=<%=partida.getPartidaID()%>&envite=Truco')">TRUCO</button>
			<button type="button" class="btn btn-danger textoPartida"
				onclick="loadDiv('mensajes', 'Partidas?action=cantarEnvite', 'partidaId=<%=partida.getPartidaID()%>&envite=Re truco')">RE
				TRUCO</button>
			<button type="button" class="btn btn-danger textoPartida"
				onclick="loadDiv('mensajes', 'Partidas?action=cantarEnvite', 'partidaId=<%=partida.getPartidaID()%>&envite=Vale cuatro')">VALE
				4</button>
			<br>
			<button type="button" class="btn btn-danger textoPartida"
				onclick="loadDiv('mensajes', 'Partidas?action=responderEnvite', 'partidaId=<%=partida.getPartidaID()%>&respuesta=si&envite=<%if (partida.getUltimoCanto() != null)
				out.print(partida.getUltimoCanto().getDescTipoCanto());%>')"<% if (partida.getUltimoCanto() == null) out.print(" disabled"); %>>QUIERO</button>
			<button type="button" class="btn btn-danger textoPartida"
				onclick="loadDiv('mensajes', 'Partidas?action=responderEnvite', 'partidaId=<%=partida.getPartidaID()%>&respuesta=no&envite=<%if (partida.getUltimoCanto() != null)
				out.print(partida.getUltimoCanto().getDescTipoCanto());%>')"<% if (partida.getUltimoCanto() == null) out.print(" disabled"); %>>NO
				QUIERO</button>
			<button type="button" class="btn btn-danger textoPartida"
				onclick="loadDiv('principal', 'Partidas?action=retirarse', 'partidaId=<%=partida.getPartidaID()%>')">ME
				VOY</button>

		</div>

	</div>
	
</div>

<!--  Modals-->
<div class="modal fade" id="modalPuntos" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Puntos</h4>
			</div>
			<div class="modal-body">
				<table class="tablaPuntaje">
					<tr>
						<th align="center">N (<%=partida.getJuegosNosotros()%>)
						</th>
						<th align="center">E (<%=partida.getJuegosEllos()%>)
						</th>
					</tr>
					<tr>
						<td><img
							src="assets/img/puntos/<%=this.getPto(partida.getPuntosJuegoNosotros(), 1)%>.png"><img
							src="assets/img/puntos/<%=this.getPto(partida.getPuntosJuegoNosotros(), 2)%>.png"><img
							src="assets/img/puntos/<%=this.getPto(partida.getPuntosJuegoNosotros(), 3)%>.png"></td>
						<td><img
							src="assets/img/puntos/<%=this.getPto(partida.getPuntosJuegoEllos(), 1)%>.png"><img
							src="assets/img/puntos/<%=this.getPto(partida.getPuntosJuegoEllos(), 2)%>.png"><img
							src="assets/img/puntos/<%=this.getPto(partida.getPuntosJuegoEllos(), 3)%>.png"></td>
					</tr>
					<tr>
						<td><img
							src="assets/img/puntos/<%=this.getPto(partida.getPuntosJuegoNosotros(), 4)%>.png"><img
							src="assets/img/puntos/<%=this.getPto(partida.getPuntosJuegoNosotros(), 5)%>.png"><img
							src="assets/img/puntos/<%=this.getPto(partida.getPuntosJuegoNosotros(), 6)%>.png"></td>
						<td><img
							src="assets/img/puntos/<%=this.getPto(partida.getPuntosJuegoEllos(), 4)%>.png"><img
							src="assets/img/puntos/<%=this.getPto(partida.getPuntosJuegoEllos(), 5)%>.png"><img
							src="assets/img/puntos/<%=this.getPto(partida.getPuntosJuegoEllos(), 6)%>.png"></td>
					</tr>

				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>

			</div>
		</div>
	</div>
</div>



<!-- End Modals-->
<% } %>