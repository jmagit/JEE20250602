<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Curso: Actores</title>
</head>
<body>
<h1>Actor (<%= request.getAttribute("modo") %>)</h1>
<form method="post">
<!-- 
    <input type="hidden" name="id" value="${actor.id}">
    <div>
        <label for="name">Nombre:</label>
        <input type="text" id="name" name="name" value="${actor.name}" required>
    </div>
    <div>
        <label for="surname">Apellidos:</label>
        <input type="text" id="surname" name="surname" value="${actor.surname}" required>
    </div>
 --> 
    <div>
        <button type="submit">enviar</button>
    </div>
</body>
</html>