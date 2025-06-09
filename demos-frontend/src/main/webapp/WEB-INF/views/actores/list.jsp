<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.example.domain.entities.Actor"%>
<%@ include file="../../parts/header.jsp" %>
	<h1>Listado de Actores</h1>
	
	<table class="table table-hover table-striped table-bordered">
		<thead>
			<tr>
				<th>Actores</th>
				<th><a href="<%=request.getContextPath() + "/actores/add"%>" class="btn btn-primary"><i class="fas fa-plus"></i></a></th>
			</tr>
		</thead>
		<tbody>
				<%
				for (Actor item : (ArrayList<Actor>) request.getAttribute("listado")) {
				%>
			<tr>
				<td><a class="link"
					href="<%=request.getContextPath() + "/actores/" + item.getActorId()%>">
						<%=item.getFirstName() + " " + item.getLastName()%>
				</a></td>
				<td><a
					href="<%=request.getContextPath() + "/actores/" + item.getActorId() + "/edit"%>" class="btn btn-success"><i class="fas fa-pen"></i></a>
					<a
					href="<%=request.getContextPath() + "/actores/" + item.getActorId() + "/delete"%>"class="btn btn-danger"><i class="fas fa-trash"></i></a>
				</td>
			</tr>
			<% } %>
		</tbody>
	</table>

<%@ include file="../../parts/footer.jsp" %>
