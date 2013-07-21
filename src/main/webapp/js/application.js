function changeAction(formName, accion){
	document.getElementById(formName).action=accion;
	document.getElementById(formName).submit();
}