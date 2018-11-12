


function pruebaa(){
	 document.getElementById("accion").value="INGRESAR";
	 document.getElementById("idLogIn").submit();
}


$(document).ready(function() {
$('#recargar').click(function(event) {
	var div = "contador";
		$.post('Partida', {
			// derecha
			div : div,
		}, function(responseText) {
			$('#contador').html(responseText);
		});
	});

$('#recargar').click(function(event) {
	var div = "puntosEnvido";	
		$.post('Partida', {
			// izquierda
			div : div,
		}, function(responseText) {
			$('#puntosEnvido').html(responseText);
		});
	});

$('#recargar').click(function(event) {
	var div = "izquierda";
		$.post('Partida', {
			// derecha
			div : div,
		}, function(responseText) {
			$('#izquierda').html(responseText);
		});
	});

$('#recargar').click(function(event) {
	var div = "frente";
		$.post('Partida', {
			// derecha
			div : div,
		}, function(responseText) {
			$('#frente').html(responseText);
		});
	});

$('#recargar').click(function(event) {
	var div = "propias";
		$.post('Partida', {
			// propias
			div : div,
		}, function(responseText) {
			$('#propias').html(responseText);
		});
	});

$('#recargar').click(function(event) {
	var div = "derecha";
		$.post('Partida', {
			// derecha
			div : div,
		}, function(responseText) {
			$('#derecha').html(responseText);
		});
	});

$('#recargar').click(function(event) {
	var div = "cartasJugador";
		$.post('Partida', {
			// cartasJugador
			div : div,
		}, function(responseText) {
			$('#cartasJugador').html(responseText);
		});
	});


});
/*
$(document).ready(function() {
	$('#recargar').click(function(event) {
		// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
		$.post('Partida', {
			
		}, function(responseText) {
			$('#contador').html(responseText);
		});
	});
});


$(document).ready(function(){
    $('#recargar').click(function(event){
    	 $.post('ActionServlet',
    			 {},
    			 function(responseText){
    			       $('#contador').html(responseText);
    			    });
    	});
		});
	
*/

$(document).ready(function(){
    $('#envido').click(function(){
       alert('envido');
    });
	$('#envido').click(function(event) {
		// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
		$.post('Partida', {
			
		}, function(responseText) {
			$('#contador').html(responseText);
		});
	});
});

$(document).ready(function(){

	$('#iniciar').click(function(event) {
		var div = "iniciar";
		$.post('Partida', {
			// derecha
			div : div,	
		}, null);
	});
});


$(document).ready(function(){
    $('#envidoEnvido').click(function(){
       alert('envidoEnvido');
    });
});



$(document).ready(function(){
    $('#realEnvido').click(function(){
       alert('realEnvido');
    });
});


$(document).ready(function(){
    $('#faltaEnvido').click(function(){
       alert('faltaEnvido');
    });
});

$(document).ready(function(){
    $('#truco').click(function(){
       alert('truco');
    });
});

$(document).ready(function(){
    $('#reTruco').click(function(){
       alert('reTruco');
    });
});

$(document).ready(function(){
    $('#vale4').click(function(){
       alert('vale4');
    });
});

$(document).ready(function(){
    $('#quiero').click(function(){
       alert('quiero');
    });
});

$(document).ready(function(){
    $('#noQuiero').click(function(){
       alert('noQuiero');
    });
});

$(document).ready(function(){
	 var a = document.getElementById('carta1').name;
	 //$('#carta1').remove();
	 $('#carta1').click(function(event) {
			$.post('Partida', {
				div: "jugarCarta",
				carta: a
			}, function(responseText) {
				$('#propias').html(responseText);
			});
		});
	 $('#carta1').click(function(){
		 $('#carta1').remove();});
});

$(document).ready(function(){
    var a = document.getElementById('carta2').name;
  //  $('#carta2').remove();
	$('#carta2').click(function(){
		$.post('Partida', {
			div: "jugarCarta",
			carta: a
		}, function(responseText) {
			$('#propias').html(responseText);
		});
    });
	$('#carta2').click(function(){
		 $('#carta2').remove();});
});

$(document).ready(function(){
	 var a = document.getElementById('carta3').name;
	
    $('#carta3').click(	function(){
    	$.post('Partida', {
			div: "jugarCarta",
			carta: a
		}, function(responseText) {
			$('#propias').html(responseText);
		});
    });
	 $('#carta3').click(function(){
		 $('#carta3').remove();});
});



function prueba(){
	alert('prueba');
}

function prueba2(input){
	alert(input);
}