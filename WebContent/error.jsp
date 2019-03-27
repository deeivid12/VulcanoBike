<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>VulcanoBike - Software de eCommerce</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        /*
** Style Simple Ecommerce Theme for Bootstrap 4
** Created by T-PHP https://t-php.fr/43-theme-ecommerce-bootstrap-4.html
*/
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


<% if(request.getAttribute("error")!= null)%>
<% String error = (String)request.getAttribute("error"); %>

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
            <a class="nav-link" href="#">Productos</a>
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
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Mi Cuenta
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">Iniciar Sesion</a>
                <a class="dropdown-item" href="#">Registrarse</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Something else here</a>
              </div>
            </li>
        </ul>
      </div>
    </div>
  </nav>

<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Error</h1>
     </div>
</section>

	<div class="container">
        <h4 class="jumbotron-heading">Se ha encontrado un error.</h4>
        <h6 class="jumbotron-heading"><%=error %></h6>
     </div>




<script type="text/javascript">

</script>