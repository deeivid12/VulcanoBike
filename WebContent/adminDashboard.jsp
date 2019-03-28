<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.vulcanobike.app.entities.Usuario"%>
<!DOCTYPE html>
<html>
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
        <a href="#" class="list-group-item list-group-item-action bg-light">Usuarios</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">Pedidos</a>
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
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="SrvUsuario?accion=logoff">Cerrar Sesion</a>
            </li>
            <%} %>
            
            
          </ul>
        </div>
      </nav>

      <div class="container-fluid">
        <h1 class="mt-4">VulcanoBike eCommerce</h1>
        <p>Software para gestion de eCommerce.</p>
        <p>TP Java Final</p>
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