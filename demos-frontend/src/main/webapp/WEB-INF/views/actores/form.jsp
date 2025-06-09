<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.example.domain.entities.Actor"%>
<%@include file="../../parts/header.jsp" %>

<h1>
	<%=request.getAttribute("modo").equals("add") ? "Añadir":"Modificar" %> Actor
</h1>
	<% if(request.getAttribute("errores") != null) { %>
		<div class="alert alert-danger">
		ERRORES: ${errores}
		</div>
	<% } %>
	<form id="elementoForm" method="post" >
		<div class="form-group">
			<label for="actorId">Código</label>
			<input type="number" class="form-control" value="${elemento.actorId}" id="actorId" name="actorId" required />
			<div id="actorIdErr" class="invalid-feedback"></div>
		</div>
		<div class="form-group">
			<label for="firstName">Nombre</label>
			<input type="text" class="form-control" value="${elemento.firstName}" id="firstName" name="firstName" required minlength="1" maxlength="45" />
			<div id="firstNameErr" class="invalid-feedback"></div>
		</div>
		<div class="form-group">
			<label for="lastName">Apellidos</label>
			<input type="text" class="form-control" value="${elemento.lastName}" id="lastName" name="lastName" required minlength="1" maxlength="45"  />
			<div id="lastNameErr" class="invalid-feedback"></div>
		</div>
		<div class="form-group">            
			<input id="btnEnviar" class="btn btn-primary" type="submit" value="Enviar"/>
			<a href="${pageContext.request.contextPath}/actores" class="btn btn-primary">Volver</a>   
		</div>
	</form>
<%@ include file="../../parts/footerScripts.jsp" %>
	<script type="text/javascript">
	document.getElementById('elementoForm').addEventListener('submit', function(ev) {
		let valid = true;
		let form = ev.target;
		for(let cmp in form) {
			if(cmp == undefined || isNaN(parseInt(cmp))) break;
			let value = form[cmp].value;
			let msg = '';
			switch(form[cmp].name) {
			case 'actorId':				
				if(value == '' ) {
					msg = 'Es obligatorio';
				} else if(isNaN(parseInt(value))) {
					msg = 'Debe ser nï¿½merico';
				}
				ponError(form[cmp].name, msg);
				if(msg !== '') valid = false;
				break;
			case 'firstName':				
				if(value == '' ) {
					msg = 'Es obligatorio';
				} else if(2 > value.length || value.length > 45) {
					msg = 'Debe tener entre 2 y 45 caracteres';
				}
				ponError(form[cmp].name, msg);
				if(msg !== '') valid = false;
				break;
			case 'lastName':				
				if(value == '' ) {
					msg = 'Es obligatorio';
				} else if(2 > value.length || value.length > 45) {
					msg = 'Debe tener entre 2 y 45 caracteres';
				}
				ponError(form[cmp].name, msg);
				if(msg !== '') valid = false;
				break;
			}
		}
		if(!valid)
			ev.preventDefault();
	});	
	document.getElementById('firstName').addEventListener('change', function(ev) {
		let value = ev.target.value;
		let msg = '';
		if(value == '' ) {
			msg = 'Es obligatorio';
		} else if(2 > value.length || value.length > 45) {
			msg = 'Debe tener entre 2 y 45 caracteres';
		}
		ponError('firstName', msg);
	});
	function ponError(cmp, msg) {
        document.getElementById(cmp + 'Err').textContent = msg;
        var cntrl = document.getElementById(cmp);
        if(msg === '')
        	cntrl.classList.remove('is-invalid');
        else
        	cntrl.classList.add('is-invalid');
    }
	</script>
<%@ include file="../../parts/footerEnd.jsp" %>
