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
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>	
</head>

<body>
	
	<table class="table">
		<tbody>
			<tr>
				<td><a href="/VulcanoBike/formTipoProducto.jsp" class="editar">Alta</a></td>
				<td><a href="/VulcanoBike" class="editar">Inicio</a></td>
			</tr>
		</tbody>
	</table>	

	<h2>Listado de Tipos de Productos</h2>
	
	
	
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Descripción</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${catTipoProducto}" var="tp">
				<tr>
					<td>${tp.nombre}</td>
					<td>${tp.descripcion}</td>
					<td><a href="SrvTipoProducto?id=${tp.nombre}&accion=editar" class="editar">Editar</a></td>
					<td><a href="SrvTipoProducto?id=${tp.nombre}&accion=eliminar" class="eliminar">Eliminar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>



</body>
</html>