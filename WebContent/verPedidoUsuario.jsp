<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.vulcanobike.app.entities.Pedido"%>
<%@ page import="com.vulcanobike.app.entities.Usuario"%>
<%@ page import="com.vulcanobike.app.entities.ItemPedido"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
  
    <title>VulcanoBike - Software de eCommerce</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">


.bloc_left_price {
    color: #c01508;
    text-align: center;
    font-weight: bold;
    font-size: 150%;
}
.category_block li:hover {
    background-color: #007bff;
}
.category_block li:hover a {
    color: #ffffff;
}
.category_block li a {
    color: #343a40;
}
.add_to_cart_block .price {
    color: #c01508;
    text-align: center;
    font-weight: bold;
    font-size: 200%;
    margin-bottom: 0;
}
.add_to_cart_block .price_discounted {
    color: #343a40;
    text-align: center;
    text-decoration: line-through;
    font-size: 140%;
}
.product_rassurance {
    padding: 10px;
    margin-top: 15px;
    background: #ffffff;
    border: 1px solid #6c757d;
    color: #6c757d;
}
.product_rassurance .list-inline {
    margin-bottom: 0;
    text-transform: uppercase;
    text-align: center;
}
.product_rassurance .list-inline li:hover {
    color: #343a40;
}
.reviews_product .fa-star {
    color: gold;
}
.pagination {
    margin-top: 20px;
}
footer {
    background: #343a40;
    padding: 40px;
}
footer a {
    color: #f8f9fa!important
}

    </style>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
 <%Usuario usuario = (Usuario)request.getSession().getAttribute("userSession"); %>


  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="#">VulcanoBike</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="#">Inicio
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="SrvListarProductoCarrito">Productos</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Contacto</a>
          </li>
          <li class="nav-item active">
            <a class="btn btn-success btn-sm ml-3 mr-3 ver" href="SrvCarrito?accion=ver" style="margin-top:0.25rem">
                                                           <i class="fa fa-shopping-cart"></i>Carrito
                                                          <span class="badge badge-light"></span>
           </a>
          </li>
          
          
            <li class="nav-item dropdown">
           
           <%if (usuario != null){ %>
           
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <%=usuario.getUser()  %>
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="SrvListarPedidoUsuario">Mis Pedidos</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="SrvLogin?accion=logoff">Cerrar Sesion</a>
              </div> 
                  <%} %>
           <%if (usuario == null){ %>
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Mi Cuenta</a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="login.jsp">Iniciar Sesion</a>
                <a class="dropdown-item" href="registro.jsp">Registrarse</a>
                </div>
              <%} %>
            
            
        </ul>
      </div>
    </div>
  </nav>

<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Pedidos de <%=usuario.getUser() %></h1>
     </div>
</section>

	<div class="container-fluid">
				<h1 class="mt-4">Ver Pedido</h1>

				<%
					Pedido p = (Pedido) request.getAttribute("pEncontrado");
				%>
				


				<form action="SrvPedido" method="post">

					<input type="hidden" name="accion" value="editar"></input>
					
					<input type="hidden" name="id" value="<%=p.getId()%>"></input>
					
			
					
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Estado</label>
						<div class="col-sm-4">
							<input type="text" name="estado" required="true" readonly
								value="<%=p.getEstado() %>" class="form-control" />
						</div>
					</div>
					
					
					
					
					
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Fecha Emision</label>
						<div class="col-sm-4">
							<input type="text" name="fechaEmision" required="true" readonly
								value="<%=p.getFechaEmision() %>" class="form-control" />
						</div>
					</div>

					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Importe</label>
						<div class="col-sm-4">
							<input type="text" name="importe" required="true" readonly
								value="<%=p.getImporte() %>" class="form-control" />
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Usuario</label>
						<div class="col-sm-4">
							<input type="text" name="usuario" required="true" readonly
								value="<%=p.getUsuario().getUser() %>" class="form-control" />
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Nombre Cliente</label>
						<div class="col-sm-4">
							<input type="text" name="nombre" required="true" readonly
								value="<%=p.getUsuario().getNombre() %>" class="form-control" />
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Apellido Cliente</label>
						<div class="col-sm-4">
							<input type="text" name="apellido" required="true" readonly
								value="<%=p.getUsuario().getApellido() %>" class="form-control" />
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Email</label>
						<div class="col-sm-4">
							<input type="text" name="email" required="true" readonly
								value="<%=p.getUsuario().getEmail() %>" class="form-control" />
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Direccion</label>
						<div class="col-sm-4">
							<input type="text" name="direccion" required="true" readonly
								value="<%=p.getUsuario().getDireccion() %>" class="form-control" />
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Telefono</label>
						<div class="col-sm-4">
							<input type="text" name="direccion" required="true" readonly
								value="<%=p.getUsuario().getTelefono() %>" class="form-control" />
						</div>
					</div>
					
					
					<div class="form-group row">
					
					
					
						<table class="table table-striped">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nombre</th>
							<th>Precio</th>
							<th>Cantidad</th>
							<th></th>
							<th>Importe</th>
						</tr>
					</thead>
					
					
					<tbody>
						<% for(ItemPedido ip : p.getItems()){ %>
							<tr>
								<td><%= ip.getProducto().getId() %></td>
								<td><%= ip.getProducto().getNombre() %></td>
								<td>$<%= ip.getProducto().getPrecio() %></td>
								<td><%= ip.getCantidad() %></td>
								<td></td>
								<td>$<%= ip.getImporte() %></td>
							</tr>	
						<%} %>		
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>Total: </td>
								<td>$<%= p.getImporte() %></td>
							</tr>									
					</tbody>
				</table>
					
					
					
					
					
					
					
					</div>
					
					

					<div class="form-group row">
						<label class="col-sm-2 col-form-label"></label>
						<div class="col-sm-5 text-center">
							<a href="SrvListarPedidoUsuario" type="button" class="btn btn-secondary">Volver</a>
						</div>
					</div>
				</form>

			</div>




<script type="text/javascript">

</script>