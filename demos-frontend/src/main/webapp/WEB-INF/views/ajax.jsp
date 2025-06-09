<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../fragmentos/encabezados.jsp"%>
<title>Ejemplos del curso</title>
</head>
<body>
	<%@include file="../fragmentos/menu.jsp"%>
	<main class="container-fluid">
		<h1>AJAX</h1>
		<img id="ajaxWait" alt="Cargando ..." src="/images/loading.gif" style="display: none" >
		<div id="tabla"></div>
		<div id="paginacion"></div>
		<input id="btnAdd" class="btn btn-primary" type="button" value="add"/>
		<form id="elemento" novalidate="true" method="post" style="display: none" >
			
			<div class="form-group">
				<label for="actorId">Código</label>
				<input id="actorId" name="actorId" class="form-control" type="number" required="true" value="0"/>
				
				<div id="actorIdErr" class="is-invalid"></div>
			</div>
			<div class="form-group">
				<label for="firstName">Nombre</label>
				<input id="firstName" name="firstName" class="form-control" minlength="2" required="true" type="text" value="" maxlength="45"/>
				
				<div id="firstNameErr" class="is-invalid"></div>
			</div>
			<div class="form-group">
				<label for="lastName">Apellidos</label>
				<input id="lastName" name="lastName" class="form-control" required="true" type="text" value="" maxlength="45"/>
				
				<div id="lastNameErr" class="is-invalid"></div>
			</div>
			<div class="form-group">            
				<input id="btnEnviar" class="btn btn-primary" type="button" value="Enviar"/>
				<a href="/actores" class="btn btn-primary">Volver</a>   
			</div>
		</form>
		
	</main>
	<%@include file="../fragmentos/scripting.jsp"%>
	<script type="text/javascript">
	function pide(pagina) {
		 document.querySelector('#ajaxWait').style = "display: block";
		 fetch('http://localhost:8080/api/actores?page=' + pagina + '&size=10&sort=firstName,lastName').then(function (response) {
             if (response.ok) {
                 response.json().then(function (page) {
                     let tabla = '<table class="table">';
                     tabla += '<tr><th>Lista de Actores</th><th><a class="btn btn-primary" href="/actores/add"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a></th></tr>';
                     for(let item of page.content) {
                         tabla += '<tr><td>' + item.firstName + ' ' + item.lastName + '</td><td><a class="btn btn-success" href="${pageContext.request.contextPath}/actores/${row.actorId}/edit"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
                         tabla += ' | <a class="btn btn-danger" href="/actores/${row.actorId}/delete"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td></tr>';
                     }
                     tabla += '</table>';
                     document.querySelector('#tabla').innerHTML = tabla;
                     let paginacion = "<p>";
                     for(let i=0; i < page.totalPages; i++) {
                    	 paginacion += '<button type="button" onclick="pide(' + i + ')">' + (i + 1) + '</button>';
                     }
                     paginacion += "</p>";
                     document.querySelector('#paginacion').innerHTML = paginacion;
                 }).catch(function (error) {
                     console.error('Error en los datos recibidos: ' + error.message);
                 });
             } else {
                 console.error('Error ' + response.status + ': ' + response.statusText);
             }
             document.querySelector('#ajaxWait').style = "display: none";
         }).catch(function (error) {
                 console.error('Hubo un problema con la petición Fetch:' + error.message);
                 document.querySelector('#ajaxWait').style = "display: none";
         });

	}

	pide(0);

	document.getElementById("btnAdd").addEventListener('click', function() {
		document.querySelector('#tabla').style = "display: none";
		document.querySelector('#paginacion').style = "display: none";
		document.querySelector('#elemento').style = "display: block";
		});
	document.getElementById("btnEnviar").addEventListener('click', function() {
		let form = document.querySelector('#elemento');
		let datos = {
			      "actorId": form['actorId'].value,
			      "firstName": form['firstName'].value,
			      "lastName": form['lastName'].value,
							
				};
		alert(JSON.stringify(datos));
		document.querySelector('#tabla').style = "display: block";
		document.querySelector('#paginacion').style = "display: block";
		form.style = "display: none";
		});
	
	</script>
</body>
</html>