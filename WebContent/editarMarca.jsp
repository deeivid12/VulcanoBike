<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.vulcanobike.app.entities.Marca"%>
<!DOCTYPE html>
<html>
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
				<h1 class="mt-4">Editar Marca</h1>

				<%
					Marca m = (Marca) request.getAttribute("mEncontrado");
				%>


				<form action="SrvMarca" method="post">

					<input type="hidden" name="accion" value="editar"></input>
					
					<input type="hidden" name="id" value="<%=m.getId()%>"></input>
					
					

					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Nombre</label>
						<div class="col-sm-4">
							<input type="text" name="nombre" required="true"
								value="<%=m.getNombre()%>" class="form-control" />
						</div>
					</div>

					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Descripcion</label>
						<div class="col-sm-4">
							<input type="text" name="descripcion" required="true"
								value="<%=m.getDescripcion()%>" class="form-control" />
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Origen</label>
						<div class="col-sm-4">
							<input type="text" name="origen" required="true"
								value="<%=m.getOrigen()%>" class="form-control" />
						</div>
					</div>

					<div class="form-group row">
						<label class="col-sm-2 col-form-label"></label>
						<div class="col-sm-5 text-center">
							<input type="submit" value="Guardar" class="btn btn-secondary" />
						</div>
					</div>
				</form>

			</div>



</body>
</html>