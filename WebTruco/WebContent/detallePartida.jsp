<%@ page import="dto.PartidaDTO"%>
<%@ page import="dto.JuegoDTO"%>
<%@ page import="dto.ManoDTO"%>
<%@ page import="dto.BazaDTO"%>
<%@ page import="dto.CartaDTO"%>
<%@ page import="dto.CantoDTO"%>

<%
	PartidaDTO partida = (PartidaDTO) request.getAttribute("detallePartida");
%>
<%!public int posicionMano(int numero) {
		if (numero < 4)
			return numero;
		else
			return numero - 4;
	}

	public String cartaJugador(int nroJug, BazaDTO baza) {
		for (CartaDTO carta : baza.getCartasbaza())
			if (carta.getIdJugador() == nroJug)
				return carta.getId().toString();
		return "00";
	}%>
<div class="row">
	<div class="panel panel-primary">
		<div class="panel-heading">
			Detalle partida
			<%=partida.getId()%></div>
		<div class="panel-body">
			<%
				int nroJuego = 1;
				for (JuegoDTO juego : partida.getJuegos()) {
			%>
			<div class="panel panel-default">
				<div class="panel-heading" >
					<a data-toggle="collapse" href="#juego_<%=nroJuego%>">Juego
					<%=nroJuego%></a></div>
				<div class="panel-body collapse" id="juego_<%=nroJuego%>">
					<%
						int nroMano = 1;
							for (ManoDTO mano : juego.getManos()) {
					%>
					<div class="panel panel-info">
						<div class="panel-heading">
							<a data-toggle="collapse" href="#mano_<%=nroJuego%>_<%=nroMano%>">Mano
							<%=nroMano%></a></div>
						<div class="panel-body collapse" id="mano_<%=nroJuego%>_<%=nroMano%>">
							<table border=1>
								<tr>
									<th style="width: 20px;">#</th>
									<th style="width: 50px;"><%=partida.getJugadores().get(this.posicionMano(mano.getNumeroMano() % 4 + 3)).getApodo()%></th>
									<th style="width: 50px;"><%=partida.getJugadores().get(this.posicionMano(mano.getNumeroMano() % 4)).getApodo()%></th>
									<th style="width: 50px;"><%=partida.getJugadores().get(this.posicionMano(mano.getNumeroMano() % 4 + 1)).getApodo()%></th>
									<th style="width: 50px;"><%=partida.getJugadores().get(this.posicionMano(mano.getNumeroMano() % 4 + 2)).getApodo()%></th>
								</tr>
								<%
									int nroBaza = 1;
											for (BazaDTO baza : mano.getBazas()) {
												if (baza.getCartasbaza().size() > 0) {
								%>
								<tr>
									<td style="text-align: center;"><b><%=nroBaza++%></b></td>
									<td style="background: #009933; text-align: center;"><img
										src="assets/img/cartas/<%=this.cartaJugador(this.posicionMano(mano.getNumeroMano() % 4 + 3) + 1, baza)%>.PNG"
										style="width: 20px;"></td>
									<td style="background: #009933; text-align: center;"><img
										src="assets/img/cartas/<%=this.cartaJugador(this.posicionMano(mano.getNumeroMano() % 4) + 1, baza)%>.PNG"
										style="width: 20px;"></td>
									<td style="background: #009933; text-align: center;"><img
										src="assets/img/cartas/<%=this.cartaJugador(this.posicionMano(mano.getNumeroMano() % 4 + 1) + 1, baza)%>.PNG"
										style="width: 20px;"></td>
									<td style="background: #009933; text-align: center;"><img
										src="assets/img/cartas/<%=this.cartaJugador(this.posicionMano(mano.getNumeroMano() % 4 + 2) + 1, baza)%>.PNG"
										style="width: 20px;"></td>
								</tr>
								<%
									}
											}
								%>
							</table>
							<br>
							<table border=1 style="width: 220px;">
								<tr>
									<th colspan=3>Cantos</th>
								</tr>
								<tr>
									<th style="width: 40%;">Canto</th>
									<th style="width: 40%;">Cantante</th>
									<th style="width: 30%;">Querido</th>
								</tr>
								<%
									boolean mostrarPtsEnvido = false;
											if (mano.getCantos().size() > 0) {
												for (CantoDTO canto : mano.getCantos()) {
													if (canto.getIdTipoCanto() < 5 && canto.isQuerido() != null && canto.isQuerido())
														mostrarPtsEnvido = true;
													else if (canto.getIdTipoCanto() < 5)
														mostrarPtsEnvido = false;
								%>
								<tr>
									<td style="text-align: center;"><%=canto.getDescTipoCanto()%></td>
									<td style="text-align: center;"><%=partida.getJugadores().get(canto.getCantante() - 1).getApodo()%></td>
									<td style="text-align: center;"><%=canto.isQuerido()%></td>
								</tr>

								<%
									}
											} else {
								%>
								<tr>
									<td colspan=3>No hubo cantos ene sta mano</td>
								</tr>
								<%
									}
								%>
							</table>
							<%
								if (mostrarPtsEnvido) {
							%>
							<br>
							<table border=1 style="width: 220px;">
								<tr>
									<th colspan=4>Puntos Envido</th>
								</tr>
								<tr>
									<th style="width: 25%;"><%=partida.getJugadores().get(this.posicionMano(mano.getNumeroMano() % 4 + 3)).getApodo()%></th>
									<th style="width: 25%;"><%=partida.getJugadores().get(this.posicionMano(mano.getNumeroMano() % 4)).getApodo()%></th>
									<th style="width: 25%;"><%=partida.getJugadores().get(this.posicionMano(mano.getNumeroMano() % 4 + 1)).getApodo()%></th>
									<th style="width: 25%;"><%=partida.getJugadores().get(this.posicionMano(mano.getNumeroMano() % 4 + 2)).getApodo()%></th>
								</tr>
								<tr>
									<td style="text-align: center;"><%=mano.getEnvidoValor()[this.posicionMano(mano.getNumeroMano() % 4 + 3)]%></td>
									<td style="text-align: center;"><%=mano.getEnvidoValor()[this.posicionMano(mano.getNumeroMano() % 4)]%></td>
									<td style="text-align: center;"><%=mano.getEnvidoValor()[this.posicionMano(mano.getNumeroMano() % 4 + 1)]%></td>
									<td style="text-align: center;"><%=mano.getEnvidoValor()[this.posicionMano(mano.getNumeroMano() % 4 + 2)]%></td>
								</tr>
							</table>
							<%
								}
							%>
						</div>
					</div>
					<%
						nroMano++;
						}
					%>
				</div>
			</div>
			<%
				nroJuego++;
				}
			%>
		</div>
	</div>
</div>