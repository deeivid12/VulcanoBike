<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<h2>Listado de Marcas</h2>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Descripción</th>
				<th>Origen</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${catMarca}" var="m">
				<tr>
					<td>${m.nombre}</td>
					<td>${m.descripcion}</td>
					<td>${m.origen}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>



</body>
</html>