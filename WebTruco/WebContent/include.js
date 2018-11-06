function loadDiv(divName, url, postInfo) {
	var mensajes = document.getElementById("mensajes");
	if (mensajes != null) {mensajes.innerHTML = "";}
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {document.getElementById(divName).innerHTML = this.responseText;}
	else if (this.readyState == 4 && this.status == 599) {document.getElementById("mensajes").innerHTML = this.responseText;}
	else if (this.readyState == 4 && this.status == 401) {window.location.href = "index.jsp";}};
    xmlhttp.open("POST", url, true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send(postInfo);
}