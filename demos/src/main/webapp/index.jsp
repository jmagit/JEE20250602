<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Curso</title>
</head>
<body>
	<h1>Hola, mundo</h1>
	<ul>
		<li><a href="./saluda">Saluda</a></li>
		<li><a href="./actores">Actores</a></li>
	</ul>
	<footer>
	<hr>
		<div>&copy; <%=GregorianCalendar.getInstance().get(Calendar.YEAR)%></div>
	</footer>
</body>
</html>