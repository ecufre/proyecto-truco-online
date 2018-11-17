<%@ page import="dto.PartidaDTO"%>
<%@ page import="dto.JugadorDTO"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.ArrayList"%>
<%!public int calcularPosicion(String miApodo, ArrayList<JugadorDTO> jugadores) {
		for (int i = 1; i <= 4; i++) {
			if (jugadores.get(i - 1).getApodo().equals(miApodo))
				return i;
		}
		return 0;
	}

	public String getCompanero(String miApodo, PartidaDTO partida) {
		int miPos = this.calcularPosicion(miApodo, partida.getJugadores());
		switch (miPos) {
		case 1:
			return partida.getJugadores().get(2).getApodo();
		case 2:
			return partida.getJugadores().get(3).getApodo();
		case 3:
			return partida.getJugadores().get(0).getApodo();
		case 4:
			return partida.getJugadores().get(1).getApodo();
		}
		return null;
	}

	public String getRivales(String miApodo, PartidaDTO partida) {
		int miPos = this.calcularPosicion(miApodo, partida.getJugadores());
		if (miPos % 2 != 0) {
			return (partida.getJugadores().get(1).getApodo() + " y " + partida.getJugadores().get(3).getApodo());
		} else {
			return (partida.getJugadores().get(0).getApodo() + " y " + partida.getJugadores().get(2).getApodo());
		}
	}

	public boolean isVictoria(String miApodo, PartidaDTO partida) {
		int miPos = this.calcularPosicion(miApodo, partida.getJugadores());
		if (miPos % 2 == partida.getGanador() % 2)
			return true;
		else
			return false;
	}

	public String getResultado(String miApodo, PartidaDTO partida) {
		if (this.isVictoria(miApodo, partida))
			return "Victoria";
		else
			return "Derrota";
	}

	public String getTipoPartida(PartidaDTO partida) {
		if (partida.isEsAbierta())
			return "Abierta";
		else
			return "Cerrada";
	}%>

<div class=row>
	<div class="panel panel-default">
		<div class="panel-heading">Partidas Finalizadas</div>
		<div class="panel-body">
			<div class="table-responsive" style="overflow-x: scroll;">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="width: 10%;">ID Partida</th>
							<th style="width: 15%;">Compañero</th>
							<th style="width: 15%;">Rivales</th>
							<th style="width: 15%;">Inicio</th>
							<th style="width: 15%;">Fin</th>
							<th style="width: 15%;">Tipo</th>
							<th style="width: 15%;">Resultado</th>
						</tr>
					</thead>
					<tbody>
						<%
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
							String miApodo = (String) request.getAttribute("miApodo");
							for (PartidaDTO partida : (ArrayList<PartidaDTO>) request.getAttribute("listaPartidas")) {
						%>
						<%
							if (this.isVictoria(miApodo, partida)) {
						%>
						<tr class="success" onclick="loadDiv('principal', 'Partidas?action=detallePartida', 'partidaId=<%=partida.getId() %>');">
							<%
								} else {
							%>
						
						<tr class="danger" onclick="loadDiv('principal', 'Partidas?action=detallePartida', 'partidaId=<%=partida.getId() %>');">
							<%
								}
							%>
							<td><%=partida.getId()%></td>
							<td><%=this.getCompanero(miApodo, partida)%></td>
							<td><%=this.getRivales(miApodo, partida)%></td>
							<td><%=partida.getFechaCreacion().format(formatter)%></td>
							<td><%=partida.getFechaActualizacion().format(formatter)%></td>
							<td><%=this.getTipoPartida(partida)%></td>
							<td><%=this.getResultado(miApodo, partida)%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>