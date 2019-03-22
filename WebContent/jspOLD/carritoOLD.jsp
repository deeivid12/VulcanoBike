<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.vulcanobike.app.entities.ItemPedido"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="java.util.*"%>

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
				<h1 class="mt-4">Carrito</h1>
				
				<% HttpSession sesion= (HttpSession) request.getSession();%>
				
				<% if(sesion != null) %>
				<% List<ItemPedido> items = (List<ItemPedido>) sesion.getAttribute("items"); %>
				


				<div class="row">
					<div class="col">
						<a class="btn btn-secondary"
							href="/VulcanoBike/formProducto.jsp" role="button">Agregar</a>
					</div>

				</div>




				<table class="table table-striped">
					<thead>
						<tr>
							<th>Nombre</th>
							<th>Descripcion</th>
							<th>Cantidad</th>
							<th>Precio</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${items}" var="i">
							<tr>
								<td>${i.producto.nombre}</td>
								<td>${i.producto.descripcion}</td>
								<td>${i.cantidad}</td>
								<td>${i.importe}</td>
								<td><a href="SrvCarrito?id=${i.producto.id}&accion=eliminar">Eliminar</a></td>
							</tr>
							</c:forEach>
							<tr>
								<td></td>
								<td></td>
								<td><a href="SrvCarrito?accion=fin"
									class="ver">Finalizar</a></td>
							</tr>
						
					</tbody>
				</table>
			</div>


</body>
</html>