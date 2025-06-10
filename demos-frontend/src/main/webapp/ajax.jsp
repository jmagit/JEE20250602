<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="WEB-INF/parts/header.jsp" %>
		<h1>AJAX</h1>
		<img id="ajaxWait" alt="Cargando ..." src="${pageContext.request.contextPath}/images/loading.gif" style="display: none" >
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
				<a href="${pageContext.request.contextPath}/ajax.jsp" class="btn btn-primary">Volver</a>   
			</div>
		</form>
		
	</main>
<%@ include file="WEB-INF/parts/footerScripts.jsp" %>
	<script type="text/javascript">
	function pide(pagina) {
		 document.querySelector('#ajaxWait').style = "display: block";
		 fetch('${pageContext.request.contextPath}/api/actores?page=' + pagina + '&size=10&sort=firstName,lastName').then(function (response) {
             if (response.ok) {
                 response.json().then(function (page) {
                     let tabla = '<table class="table">';
                     tabla += '<tr><th>Lista de Actores</th><th><a class="btn btn-primary" href="${pageContext.request.contextPath}/actores/add"><span class="fas fa-plus" aria-hidden="true"></span></a></th></tr>';
                     for(let item of page.content) {
                         tabla += '<tr><td>' + item.firstName + ' ' + item.lastName + '</td><td><div class="btn-group"><a class="btn btn-success" href="${pageContext.request.contextPath}/actores/' + item.actorId +'/edit"><span class="fas fa-pen" aria-hidden="true"></span></a>';
                         tabla += '<a class="btn btn-danger" href="${pageContext.request.contextPath}/actores/' + item.actorId +'/delete"><span class="fas fa-trash" aria-hidden="true"></span></a></div></td></tr>';
                     }
                     tabla += '</table>';
                     document.querySelector('#tabla').innerHTML = tabla;
                     let paginacion = '<nav><ul class="pagination">';
                     for(let i=0; i < page.totalPages; i++) {
                    	 paginacion += '<li class="page-item' + (i === page.number ? ' active' : '') + 
                    	 	'"><button type="button" class="page-link" onclick="pide(' + i + ')">' + (i + 1) + '</button></li>';
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
<%@ include file="WEB-INF/parts/footerEnd.jsp" %>
