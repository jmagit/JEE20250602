<%@page import="java.util.Date"%>
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
		<li><a href="saluda">Saludar</a></li>
	</ul>
	<footer>
	<hr>
		<div>&copy; <%= (new Date()).toLocaleString() %></div>
	</footer>
</body>
</html>