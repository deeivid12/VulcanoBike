<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>VulcanoBike - Software de eCommerce</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>

</head>

<body>


<div class="container-fluid">
				<h1 class="mt-4">Productos</h1>


				<div class="row">
					<div class="col">
						<a class="btn btn-secondary"
							href="/VulcanoBike/formProducto.jsp" role="button">Agregar</a>
					</div>

				</div>




				<table class="table table-striped">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nombre</th>
							<th>Descripcion</th>
							<th>Precio</th>
							<th>Stock</th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${catProducto}" var="p">
							<tr>
								<td>${p.id}</td>
								<td>${p.nombre}</td>
								<td>${p.descripcion}</td>
								<td>${p.precio}</td>
								<td>${p.stock}</td>
								<td><a href="SrvCarrito?id=${p.id}&accion=add"
									class="add">Add Carrito</a></td>
								<td><a href="SrvTipoProducto?id=${p.id}&accion=editar"
									class="editar">Editar</a></td>
								<td><a
									href="SrvTipoProducto?id=${p.id}&accion=eliminar"
									class="eliminar" onclick="return confirm('¿Desea eliminar?')">Eliminar</a></td>
							</tr>
							</c:forEach>
							<tr>
								<td></td>
								<td></td>
								<td><a href="SrvCarrito?accion=ver"
									class="ver">Ver Carrito</a></td>
							</tr>
						
					</tbody>
				</table>
			</div>


</body>
</html>