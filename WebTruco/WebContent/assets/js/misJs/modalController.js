

function setVisibilidadModal(selecto){


	lb= document.getElementsByClassName("modal-subBody");
	
	for(i = 0; i<lb.length;i++){
		lb[i].style ="display:none";
		
	}
	document.getElementById(selecto).style ="display:block";



}

function retornar(input,valor){
document.getElementById(input).value =valor
}

$(document).on("click", "tr.lineaObjetoModal" , function(){
		
				var celda = $(this).children("#id").text();
				alert("por fuera");
				//idm.value=celda;
				//accion.value="mostrarCliente";
		
				//fCliente.submit();
		
	});	