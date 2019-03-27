<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.vulcanobike.app.entities.Producto"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Shop Item - Start Bootstrap Template</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/shop-item.css" rel="stylesheet">
  
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

</head>



<body>

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

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="#">Start Bootstrap</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="#">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Services</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Contact</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">
        <h1 class="my-4">Shop Name</h1>
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
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2019</p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
