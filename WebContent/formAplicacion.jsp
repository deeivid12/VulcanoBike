<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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


		<h2>Aplicacion</h2>
		<p></p>

		<form action="SrvAplicacion" method="post">
		
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
	</div>




</body>
</html>