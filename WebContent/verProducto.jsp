<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.vulcanobike.app.entities.Producto"%>
<%@ page import="com.vulcanobike.app.entities.Usuario"%>
<%@ page import="com.vulcanobike.app.entities.ItemPedido"%>
<%@ page import="java.util.*"%>
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
  <link href="css/shop-item.css" rel="stylesheet">
  
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

</head>



<body>

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

	<%
					Producto p = (Producto) request.getAttribute("pEncontrado");
				%>			

<script type="text/javascript">

$(document).ready(function() {
	$(".addCarrito").click(function() {
		
		var cant = $("#cant").val();
		var string = "SrvCarrito?id="+<%=p.getId()%>+"&accion=add&cant="+cant;
		this.href = string;
		
	})
	
})

</script>

<%List<ItemPedido> itemsPedido = (List<ItemPedido>)request.getSession().getAttribute("items"); %>

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
          <li class="nav-item active">
            <a class="nav-link" href="SrvListarProductoCarrito">Productos</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Contacto</a>
          </li>
          <li class="nav-item">
            <a class="btn btn-success btn-sm ml-3 mr-3 ver" href="SrvCarrito?accion=ver" style="margin-top:0.25rem">
                                                           <i class="fa fa-shopping-cart"></i>Carrito
                                                           <%if (itemsPedido != null){ %>
                                                           <span class="badge badge-light"><%=itemsPedido.size() %></span>
                                                           <%} %>
           </a>
          </li>
          
          <%Usuario usuario = (Usuario)request.getSession().getAttribute("userSession"); %>
          
           <li class="nav-item dropdown">
           
           <%if (usuario != null){ %>
           
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <%=usuario.getUser()  %>
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="SrvUsuario?accion=logoff">Cerrar Sesion</a>
                  <%} %>
           <%if (usuario == null){ %>
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Mi Cuenta</a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="login.jsp">Iniciar Sesion</a>
                <a class="dropdown-item" href="registro.jsp">Registrarse</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Something else here</a>
              </div> 
              <%} %>
               
               
                  
              </div>
            </li>
        </ul>
      </div>
    </div>
  </nav>
 

  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">
        <h1 class="my-4">Detalle Producto</h1>
        <!--<div class="list-group">
          <a href="#" class="list-group-item active">Category 1</a>
          <a href="#" class="list-group-item">Category 2</a>
          <a href="#" class="list-group-item">Category 3</a>
        </div>-->
      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">

        <div class="card mt-4">
          <img class="card-img-top img-fluid" src="<%=p.getImagen() %>" alt="" style="width:70%; align-self:center;">
          <div class="card-body">
            <h3 class="card-title"><%=p.getNombre()%></h3>
            <h4>$<%=p.getPrecio() %></h4>
            <p class="card-text"><%=p.getDescripcion()%></p>
          </div>
        </div>
        <!-- /.card -->

        <div class="card card-outline-secondary my-4">
          <div class="card-header">
            Informacion Producto
          </div>
          <div class="card-body">
            <p>Stock Actual: <%=p.getStock()%> </p>
            <p>Cantidad:  <select class="form-control" id="cant" name="cant">
								   <%for(int i=1;i<=p.getStock();i++){ %>
								    <option><%=i %></option>
								    <%} %>
								    
						  </select> 
			</p>
            <hr>
            <a href="#" class="btn btn-success addCarrito">Agregar a Carrito</a>           
          </div>
        </div>
        <!-- /.card -->

      </div>
      <!-- /.col-lg-9 -->

    </div>

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white"></p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
