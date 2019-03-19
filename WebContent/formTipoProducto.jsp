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

	<div class="container">
		<p></p>


		<h2>Tipo Producto</h2>
		<p></p>

		<form action="SrvTipoProducto" method="post">
		
		<input type="hidden" name="accion" value="alta"></input>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">Nombre</label>
				<div class="col-sm-4">
					<input type="text" name="nombre" required="true"
						class="form-control" />
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">Descripcion</label>
				<div class="col-sm-4">
					<input type="text" name="descripcion" required="true"
						class="form-control" />
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label"></label>
				<div class="col-sm-5 text-center">
					<input type="submit" value="Confirmar" class="btn btn-primary" />
				</div>
			</div>
		</form>
		
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
					<td><a href="SrvEditTipoProducto?id=${tp.nombre}">Editar</a></td>
					<td>Eliminar</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>




</body>
</html>