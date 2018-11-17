/*
Este script administra la visbilidad de los botones de un ABM estander 


*/

// document.getElementById('oculto').style ="display:block"; mostrar
// document.getElementById('oculto').style ="display:none"; ocultar
//document.getElementById('menuItem-modulo').style.display = 'none'; //visiblidad de la barra lateral
//document.getElementById("myText").readOnly = true; //setea el campo para que sea solo lectura
/*
document.getElementById('btn-nuevoObjeto').style.display = '';
document.getElementById('btn-habilitaEdicion').style.display = '';
document.getElementById('btn-editaObjeto').style.display = '';
document.getElementById('btn-bajaObjeto').style.display = '';
document.getElementById('btn-rehabilitaObjeto').style.display = '';
document.getElementById('btn-habilitaNuevoObjeto').style.display = '';
*/

var val = document.getElementById('estadoPantalla').value;
var per = document.getElementById('perfilEditor').value;
setVisibilidad(val);
configuracionPerfil(per);



function configuracionPerfil(per){
	var band;
	if(per=='S'){
	band=false;
	}else{
	band=true;
	}
	lbe= document.getElementsByClassName('btn-edicion');
	
	for(i = 0; i<lbe.length;i++){
		lbe[i].disabled= band;
	}


}



function setVisibilidad(val){

switch (val){
	case '0': //solo abre la menu buscar | nuevo
		vfVisible(true,false,false,false);
		
		simpreSoloLectura();
	break;
	
	case '1': //abre la pantalla para filtrar busqueda
		vfVisible(false,true,false,false);
	
		simpreSoloLectura();
	break;	
	
	case '2': // abre la tabla con reultado de busqueda
		vfVisible(false,false,true,false);
		
		simpreSoloLectura();
	break;
	
	case '3': //abre un objeto seleccionado
	
		vfVisible(false,false,false,true);
		
		if(document.getElementById('estado-objeto').value=='B'){
		
		vfVisibilidadObjeto(false,false,false,false,true);
		vfHabilitaEscritura(false,'campo-objeto');
		}else{
		
		vfVisibilidadObjeto(false,true,false,true,false);
		vfHabilitaEscritura(false,'campo-objeto');
		}
		
		simpreSoloLectura();
	break;	
	
	case '4': 
		vfVisible(false,false,false,true);
		vfVisibilidadObjeto(true,false,false,false,false);
		vfHabilitaEscritura(true,'campo-objeto');
		simpreSoloLectura();
	break;
	
	case '5':
		vfVisible(false,false,false,true);
		vfVisibilidadObjeto(false,false,true,false,false);
		vfHabilitaEscritura(true,'campo-objeto');
		simpreSoloLectura();
	break;	
}


}


function vfVisible(busquedaNuevo,filtrosBusqueda,resultadoBusqueda,pantallaObjeto){

	if(busquedaNuevo){
		document.getElementById('botonera-busqueda-nuevo').style ="display:block"
	}else{
		document.getElementById('botonera-busqueda-nuevo').style ="display:none";
	}

	if(filtrosBusqueda){
		document.getElementById('pantalla-busqueda').style ="display:block"
	}else{
		document.getElementById('pantalla-busqueda').style ="display:none";
	}

	if(resultadoBusqueda){
		document.getElementById('tabla-resultado').style ="display:block"
	}else{
		document.getElementById('tabla-resultado').style ="display:none";
	}

	if(pantallaObjeto){
		document.getElementById('pantalla-objeto').style ="display:block"
	}else{
		document.getElementById('pantalla-objeto').style ="display:none";
	}

	
	

}


function vfVisibilidadObjeto(nuevoObjeto,habilitaEdicion,editaObjeto,bajaObjeto,rehabilitaObjeto){

	if(nuevoObjeto){
		document.getElementById('btn-nuevoObjeto').style ="display:block";
		
		
	}else{
		document.getElementById('btn-nuevoObjeto').style ="display:none";
	}

	if(habilitaEdicion){
		document.getElementById('btn-habilitaEdicion').style ="display:block";
	
	}else{
		document.getElementById('btn-habilitaEdicion').style ="display:none";
	}
	
	if(editaObjeto){
		document.getElementById('btn-editaObjeto').style ="display:block";
		
	}else{
		document.getElementById('btn-editaObjeto').style ="display:none";
	}
	
	if(bajaObjeto){
		document.getElementById('btn-bajaObjeto').style ="display:block";
	}else{
		document.getElementById('btn-bajaObjeto').style ="display:none";
	}
	
	if(rehabilitaObjeto){
		document.getElementById('btn-rehabilitaObjeto').style ="display:block";
	}else{
		document.getElementById('btn-rehabilitaObjeto').style ="display:none";
	}



simpreSoloLectura();


}

function simpreSoloLectura(){
sl= document.getElementsByClassName("solo-lectura");
	
	for(i = 0; i<sl.length;i++){
		sl[i].readOnly = true;
	}
}

function vfHabilitaEscritura(val,clase){

	le= document.getElementsByClassName(clase);
	
	for(i = 0; i<le.length;i++){
		le[i].readOnly = !val;
		le[i].disabled=!val;
	}


}

function prueba(val){
alert(val);
}