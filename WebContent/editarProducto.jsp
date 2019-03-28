<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.vulcanobike.app.entities.Producto"%>
<%@ page import="com.vulcanobike.app.business.Controlador"%>
<%@ page import="com.vulcanobike.app.entities.TipoProducto"%>
<%@ page import="com.vulcanobike.app.entities.Aplicacion"%>
<%@ page import="com.vulcanobike.app.entities.Rodado"%>
<%@ page import="com.vulcanobike.app.entities.Marca"%>
<%@ page import="java.util.*"%>
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
				<h1 class="mt-4">Editar Producto</h1>

				<%Controlador ctrl = new Controlador(); %>
				
				<%
					Producto p = (Producto) request.getAttribute("pEncontrado");
				%>
				
				
					<%List<TipoProducto> tps = ctrl.getAllTipoProducto();%>
					<%List<Rodado> rodados = ctrl.getAllRodado();%>
					<%List<Aplicacion> aplicaciones = ctrl.getAllAplicacion();%>
					<%List<Marca> marcas = ctrl.getAllMarca();%>
				


				<form action="SrvProducto" method="post">

					<input type="hidden" name="accion" value="editar"></input>
					
					<input type="hidden" name="id" value="<%=p.getId()%>"></input>
					
					

					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Nombre</label>
						<div class="col-sm-4">
							<input type="text" name="nombre" required="true"
								value="<%=p.getNombre()%>" class="form-control" />
						</div>
					</div>

					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Descripcion</label>
						<div class="col-sm-4">
							<input type="text" name="descripcion" required="true"
								value="<%=p.getDescripcion()%>" class="form-control" />
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Precio</label>
						<div class="col-sm-4">
							<input type="number" name="precio" step="0.01" min="0" required="true"
								value="<%=p.getPrecio()%>" class="form-control" />
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Stock</label>
						<div class="col-sm-4">
							<input type="number" name="stock" min="0" required="true"
								value="<%=p.getStock()%>" class="form-control" />
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Imagen</label>
						<div class="col-sm-4">
							<input type="text" name="imagen" required="true"
								value="<%=p.getImagen()%>" class="form-control" />
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Tipo Producto</label>
						<div class="col-sm-4">
							<select class="form-control" name="tipoProducto">
							 <option selected="true" value="<%=p.getTipoProducto().getId() %>" label="<%=p.getTipoProducto().getNombre() %>"></option>
							  <% for(TipoProducto tp:tps){ %>
							    <option value="<%=tp.getId() %>"
							    		label="<%=tp.getNombre() %>"></option>
							   <%} %>
							  </select>
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Rodado</label>
						<div class="col-sm-4">
							<select class="form-control" name="rodado">
							   <option selected="true" value="<%=p.getRodado().getId() %>" label="<%=p.getRodado().getNombre() %>"></option>
							  <% for(Rodado r:rodados){ %>
							    <option value="<%=r.getId() %>"
							    		label="<%=r.getNombre() %>"></option>
							   <%} %>
							  </select>
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Aplicacion</label>
						<div class="col-sm-4">
							<select class="form-control" name="aplicacion">
							  <option selected="true" value="<%=p.getAplicacionBicicleta().getId() %>" label="<%=p.getAplicacionBicicleta().getNombre() %>"></option>
							  <% for(Aplicacion a:aplicaciones){ %>
							    <option value="<%=a.getId() %>"
							    		label="<%=a.getNombre() %>"			    		
							    ></option>
							    <%} %> 
							    </select>
							   
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Marca</label>
						<div class="col-sm-4">
							<select class="form-control" name="marca">
							 <option selected="true" value="<%=p.getMarca().getId() %>" label="<%=p.getMarca().getNombre() %>"></option>
							  <% for(Marca m:marcas){ %>
							    <option value="<%=m.getId() %>"
							    		label="<%=m.getNombre() %>"></option>
							   <%} %>
							  </select>
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