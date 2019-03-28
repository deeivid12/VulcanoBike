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

	<h2>Listado de Rodados</h2>
	
	<input type="hidden" name="accion" value="listar"></input>
	
	<div class="row">
					<div class="col">
						<a class="btn btn-secondary"
							href="formRodado.jsp" role="button">Agregar</a>
					</div>

				</div>

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
			<c:forEach items="${catRodado}" var="r">
				<tr>
					<td>${r.nombre}</td>
					<td>${r.descripcion}</td>
					<td><a href="SrvRodado?id=${r.id}&accion=editar"
									class="editar">Editar</a></td>
					<td><a href="SrvRodado?id=${r.id}&accion=eliminar"
									class="eliminar" onclick="return confirm('¿Desea eliminar?')">Eliminar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>



</body>
</html>