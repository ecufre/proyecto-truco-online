function loadDiv(divName, url, postInfo) {
	var mensajes = document.getElementById("mensajes");
	if (mensajes != null) {mensajes.innerHTML = "";}
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById(divName).innerHTML = this.responseText;
			if (url == "Jugador?action=listarRanking" || url.startsWith("Grupo")) {
				$('#dataTable').dataTable({
					"order": [[ 3, "desc" ]],
					"language": {"decimal":        "",
						"emptyTable":     "No hay jugadores o partidas para armar un ranking",
						"info":           "_TOTAL_ resultados. Mostrando de _START_ a _END_",
						"infoEmpty":      "0 resultados",
						"infoFiltered":   "(filtrado de un total de _MAX_)",
						"infoPostFix":    "",
						"thousands":      ".",
						"lengthMenu":     "Mostrar _MENU_ por pagina",
						"loadingRecords": "Cargando...",
						"processing":     "Procesando...",
						"search":         "Buscar:",
						"zeroRecords":    "No se encontraron resultados",
						"paginate": {
							"first":      "Primera",
							"last":       "Ultima",
							"next":       "Proxima",
							"previous":   "Anterior"
						},
						"aria": {
							"sortAscending":  ": activar para ordenar de forma ascendente",
							"sortDescending": ": activar para ordenar de forma descendente"
						}
					}});
			}
		}
		else if (this.readyState == 4 && this.status == 599) {document.getElementById("mensajes").innerHTML = this.responseText;}
		else if (this.readyState == 4 && this.status == 401) {window.location.href = "";}
		else if (this.readyState == 4 && this.status == 404) {window.location.href = "";}};
		xmlhttp.open("POST", url, true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlhttp.send(postInfo);
}

setTimeout(actualizarPartidas, 0);

setInterval(actualizarPartidas, 15000);

function actualizarPartidas() {
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var listaPartidas = JSON.parse(this.responseText);
			for (i = 0; i < listaPartidas.Pendientes.length; i++) {
				var idPartida = listaPartidas.Pendientes[i].idPartida;
				var hiddenInput =document.getElementById("partida_p_" + idPartida);
				if (hiddenInput == null) {
					$("#tablaPendientes").append("<tr align='center' id='partida_p_" + idPartida + "' onclick=\"loadDiv('mensajes', 'Partidas?action=jugadorListo', 'partidaId=" + idPartida + "')\"><td>" + idPartida + "</td><input type='hidden' id='update_" + idPartida + "' value=" + listaPartidas.Pendientes[i].lastUpdate + "></tr>");
				}
			}
			for (i = 0; i < listaPartidas.EnCurso.length; i++) {
				var idPartida = listaPartidas.EnCurso[i].idPartida;
				var partidaPendienteRow = document.getElementById("partida_p_" + idPartida);
				if (partidaPendienteRow != null) {
					var jqpartidaPendienteRow = $("#partida_p_" + idPartida);
					partidaPendienteRow.remove();
				}
				var partidaEnCursoRow = document.getElementById("partida_" + idPartida);
				if (partidaEnCursoRow == null) {
					$("#tablaEnCurso").append("<tr align='center' id='partida_" + idPartida + "' onclick=\"loadDiv('principal', 'Partidas?action=mostrarPartida', 'partidaId=" + idPartida + "'); this.className=''\"><td>" + idPartida + "</td><input type='hidden' id='update_" + idPartida + "' value=0></tr>");
				}
				var jqPartidaEnCursoRow = $("#partida_" + idPartida);
				var hiddenInputC = $("#update_" + idPartida);
				if (listaPartidas.EnCurso[i].lastUpdate > hiddenInputC.val()) {
					jqPartidaEnCursoRow.addClass('warning');
					hiddenInputC.val(listaPartidas.EnCurso[i].lastUpdate);
				}

				if (listaPartidas.EnCurso[i].miTurno) {
					jqPartidaEnCursoRow.addClass('danger').removeClass('warning');
				}
				else {
					jqPartidaEnCursoRow.removeClass('danger');
				}
			}
		}
		else if (this.readyState == 4 && this.status == 599) {document.getElementById("mensajes").innerHTML = this.responseText;}
		else if (this.readyState == 4 && this.status == 401) {window.location.href = "";}
	};
	xmlhttp.open("POST", "Partidas?action=listarMisPartidas", true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send(null);
}