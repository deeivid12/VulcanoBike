<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.vulcanobike.app.entities.Usuario"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

</head>

<body>

 <% Usuario usuario = (Usuario)request.getSession().getAttribute("userSession"); %>

  <div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">VulcanoBike </div>
      <div class="list-group list-group-flush">
        <a href="srvListarTipoProducto" class="list-group-item list-group-item-action bg-light">Tipos Productos</a>
        <a href="SrvListarMarca" class="list-group-item list-group-item-action bg-light">Marcas</a>
        <a href="SrvListarRodado" class="list-group-item list-group-item-action bg-light">Rodados</a>
        <a href="SrvListarAplicacion" class="list-group-item list-group-item-action bg-light">Aplicaciones</a>
        <a href="SrvListarProducto" class="list-group-item list-group-item-action bg-light">Productos</a>
        <a href="SrvListarUsuario" class="list-group-item list-group-item-action bg-light">Usuarios</a>
		<a href="SrvListarPedido" class="list-group-item list-group-item-action bg-light">Pedidos</a>
      </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
        <button class="btn btn-primary" id="menu-toggle">Ocultar Menu</button>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item active">
              <a class="nav-link" href="#">Inicio <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="SrvListarProductoCarrito">Productos</a>
            </li>
            
           <%if(usuario != null){%> 
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <%=usuario.getUser() %>
              </a>
              <div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="SrvListarPedidoUsuario">Mis Pedidos</a>
				                <div class="dropdown-divider"></div>
				                <a class="dropdown-item" href="SrvUsuario?accion=logoff">Cerrar Sesion</a>
							</div> 
            </li>
            <%} %>
            
            
          </ul>
        </div>
      </nav>

      <div class="container-fluid">
        <h1 class="mt-4">Alta Rodado</h1>

        <form action="SrvRodado" method="post">
		
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
					<input type="submit" value="Confirmar" class="btn btn-secondary" />
				</div>
			</div>
		</form>

      </div>
    </div>
    <!-- /#page-content-wrapper -->

  </div>
  <!-- /#wrapper -->

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Menu Toggle Script -->
  <script>
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>

</body>

</html>
