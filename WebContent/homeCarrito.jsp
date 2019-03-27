<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.vulcanobike.app.entities.Usuario"%>
<%@ page import="com.vulcanobike.app.entities.ItemPedido"%>
<%@ page import="com.vulcanobike.app.entities.TipoProducto"%>
<%@ page import="com.vulcanobike.app.entities.Aplicacion"%>
<%@ page import="com.vulcanobike.app.entities.Rodado"%>
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
  <link href="css/shop-homepage.css" rel="stylesheet">

</head>

<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">


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
            <a class="nav-link" href="#">Productos</a>
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
      
      <%List<TipoProducto> tps = (List<TipoProducto>)request.getAttribute("catTipoProducto"); %>
      <%List<Aplicacion> aplicaciones = (List<Aplicacion>)request.getAttribute("catAplicacion");  %>
      <%List<Rodado> rodados = (List<Rodado>)request.getAttribute("catRodado");  %>
      
        <h1 class="my-4">Productos</h1>
        <h5 class="my-4">Tipo Producto</h5>
        <div class="list-group">
        	<a href="SrvListarProducto" class="list-group-item">Todo</a>
       <%if(tps != null){
    	   for(TipoProducto tp:tps){%>
          <a href="SrvListarProducto?filtro=<%= tp.getNombre() %>" class="list-group-item"><%=tp.getNombre() %></a>
         <%}} %>
         </div>
        
         <h5 class="my-4">Aplicacion</h5>
        <div class="list-group">
        	<a href="SrvListarProducto" class="list-group-item">Todo</a>
       <%if(aplicaciones != null){
    	   for(Aplicacion a:aplicaciones){%>
          <a href="SrvListarProducto?filtro=<%= a.getNombre() %>" class="list-group-item"><%=a.getNombre() %></a>
         <%}} %>
         </div>
        
        <h5 class="my-4">Rodado</h5>
         <div class="list-group">
        	<a href="SrvListarProducto" class="list-group-item">Todo</a>
       <%if(rodados != null){
    	   for(Rodado r:rodados){%>
          <a href="SrvListarProducto?filtro=<%= r.getNombre() %>" class="list-group-item"><%=r.getNombre() %></a>
         <%}} %>
         </div>
        
        <h5 class="my-4">Marca</h5>
         <div class="list-group">
          <a href="SrvListarTipoProducto" class="list-group-item">Todo</a>
          <a href="#" class="list-group-item">Category 2</a>
        </div>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">
		<%if(request.getAttribute("error") != null){%> 	
		<h5 class="text-danger"><%=request.getAttribute("error")%></h5>
		<%} %>	
         <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
          <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
          </ol>
          <div class="carousel-inner" role="listbox">
            <div class="carousel-item active">
              <img class="d-block img-fluid" src="http://glasgowwestend.today/wp-content/uploads/sites/11/2017/06/IMG_1879.jpg" alt="First slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="https://deltebre.cat/turisme/wp-content/uploads/2016/07/trasnport.jpg" alt="Second slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="https://carrascoesciclismo.es/wp-content/uploads/2018/09/Nueva-Scott-Ransom-2019.jpg" alt="Third slide">
            </div>
          </div>
          <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>
        
        
        
        
        
	
        <div class="row">
        
        <c:forEach items="${catProducto}" var="p">

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="SrvCarrito?id=${p.id}&accion=info" class="info"><img class="card-img-top" src="${p.imagen}" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="SrvCarrito?id=${p.id}&accion=info" class="info">${p.nombre}</a>
                </h4>
                <h5>$${p.precio}</h5>
                <p class="card-text">${p.descripcion}</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">Stock: ${p.stock}</small>
              </div>
            </div>
          </div>

          </c:forEach>

        </div>
        
    
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2019</p>
    </div>
    <!-- /.container -->
  </footer>

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
