<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.example.domain.entities.Actor"%>
<%@include file="../../parts/header.jsp" %>
	<h1>${modo.equals("delete") ? "Borrar" : "Detalles del"} Actor</h1>
	<% if(request.getAttribute("errores") != null) { %>
		<div class="alert alert-danger">
		ERRORES: ${errores}
		</div>
	<% } %>
	<dl>
		<dt>Código:</dt><dd>${elemento.actorId}</dd>
		<dt>Nombre:</dt><dd>${elemento.firstName}</dd>
		<dt>Apellidos:</dt><dd>${elemento.lastName}</dd>
	</dl>
	<form method="post" >
		<p>
			<% if(request.getAttribute("modo").equals("delete")) { %>
				<input type="submit" value="Confirmar" class="btn btn-danger">
			<% } %>
			<a href="${pageContext.request.contextPath}/actores" class="btn btn-primary">Volver</a>
		</p>
	</form>
<%@ include file="../../parts/footer.jsp" %>
