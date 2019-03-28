<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="com.vulcanobike.app.business.Controlador"%>
<%@ page import="com.vulcanobike.app.entities.TipoProducto"%>
<%@ page import="com.vulcanobike.app.entities.Aplicacion"%>
<%@ page import="com.vulcanobike.app.entities.Rodado"%>
<%@ page import="com.vulcanobike.app.entities.Marca"%>
<%@ page import="java.util.*"%>
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


		<h2>Producto</h2>
		<p></p>

		<form action="SrvProducto" method="post">
		
			<input type="hidden" name="accion" value="alta"></input>
			
			<%Controlador ctrl = new Controlador(); %>
			<%List<TipoProducto> tps = ctrl.getAllTipoProducto();%>
			<%List<Rodado> rodados = ctrl.getAllRodado();%>
			<%List<Aplicacion> aplicaciones = ctrl.getAllAplicacion();%>
			<%List<Marca> marcas = ctrl.getAllMarca();%>
			
			

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
						<label class="col-sm-2 col-form-label">Precio</label>
						<div class="col-sm-4">
							<input type="number" name="precio" step="0.01" min="0" required="true"
								 class="form-control" />
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Stock</label>
						<div class="col-sm-4">
							<input type="number" name="stock" min="0" required="true"
								 class="form-control" />
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Imagen</label>
						<div class="col-sm-4">
							<input type="text" name="imagen" required="true"
								 class="form-control" />
						</div>
						<label class="col-sm-2 col-form-label">(Solo link a imagen)</label>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Tipo Producto</label>
						<div class="col-sm-4">
							<select class="form-control" name="tipoProducto">
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
					<input type="submit" value="Confirmar" class="btn btn-primary" />
				</div>
			</div>
		</form>
	</div>




</body>
</html>