-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: vulcanobike_db
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aplicaciones`
--

DROP TABLE IF EXISTS `aplicaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `aplicaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aplicaciones`
--

LOCK TABLES `aplicaciones` WRITE;
/*!40000 ALTER TABLE `aplicaciones` DISABLE KEYS */;
INSERT INTO `aplicaciones` VALUES (1,'MTB','Mountain Bike, Bicicletas Todo Terreno'),(3,'Ciudad','Bicicletas de paseo, de ciudad'),(4,'Indistinta','Aplicacion valida para todo tipo de bicicletas');
/*!40000 ALTER TABLE `aplicaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items_pedidos`
--

DROP TABLE IF EXISTS `items_pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `items_pedidos` (
  `id_pedido` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `importe` float(9,2) NOT NULL,
  KEY `items_pedidos_id_pedido` (`id_pedido`),
  KEY `items_pedidos_id_producto` (`id_producto`),
  CONSTRAINT `items_pedidos_id_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `items_pedidos_id_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items_pedidos`
--

LOCK TABLES `items_pedidos` WRITE;
/*!40000 ALTER TABLE `items_pedidos` DISABLE KEYS */;
INSERT INTO `items_pedidos` VALUES (89,10,1,1500.00),(89,4,1,8000.00),(90,1,1,15000.00);
/*!40000 ALTER TABLE `items_pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcas`
--

DROP TABLE IF EXISTS `marcas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `marcas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  `origen` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcas`
--

LOCK TABLES `marcas` WRITE;
/*!40000 ALTER TABLE `marcas` DISABLE KEYS */;
INSERT INTO `marcas` VALUES (1,'GT','GT Bycicles','USA'),(3,'Generica','Marca generica','China'),(6,'Kask','Kas Cascos','USA'),(7,'FAD','FAD Bicicletas','Argentina');
/*!40000 ALTER TABLE `marcas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_emision` date DEFAULT NULL,
  `importe` float(9,2) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pedidos_usuarios` (`id_usuario`),
  CONSTRAINT `pedidos_usuarios` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (89,'2019-04-01',9500.00,5,3),(90,'2019-04-01',15000.00,NULL,1);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `productos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  `precio` float(9,2) NOT NULL,
  `stock` int(11) NOT NULL,
  `imagen` varchar(500) DEFAULT NULL,
  `id_tipo_producto` int(11) DEFAULT NULL,
  `id_rodado` int(11) DEFAULT NULL,
  `id_aplicacion` int(11) DEFAULT NULL,
  `id_marca` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `productos_tipo_producto` (`id_tipo_producto`),
  KEY `productos_aplicacion` (`id_aplicacion`),
  KEY `productos_rodado` (`id_rodado`),
  KEY `productos_marca` (`id_marca`),
  CONSTRAINT `productos_aplicacion` FOREIGN KEY (`id_aplicacion`) REFERENCES `aplicaciones` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `productos_marca` FOREIGN KEY (`id_marca`) REFERENCES `marcas` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `productos_rodado` FOREIGN KEY (`id_rodado`) REFERENCES `rodados` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `productos_tipo_producto` FOREIGN KEY (`id_tipo_producto`) REFERENCES `tipo_producto` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Bicicleta MTB','Bicicleta MTB todo terreno',15000.00,9,'https://bicicletasgt.com/wp-content/uploads/2018/09/GT-AVALANCHE-COMP-2019.jpg',7,3,1,1),(2,'Bicicleta Fixie','Bicicleta con pinion fijo',8000.00,0,'https://www.santafixie.com/media/catalog/product/cache/1/image/900x600/9df78eab33525d08d6e5fb8d27136e95/6/k/6ku-beach-bum-30mm-1.jpg',7,2,3,7),(3,'Bicicleta Paseo','Bicicleta de paseo con freno contrapedal',6000.00,8,'https://i.pinimg.com/originals/2c/5e/55/2c5e5515d3dccf2ae934a1762a474842.jpg',7,1,3,3),(4,'Bicicleta Inglesa','Bicicleta de paseo tipo inglesa con freno a varillas',8000.00,4,'https://falabella.scene7.com/is/image/FalabellaAR/2803149_1?$producto308$&wid=472&hei=472',7,2,3,3),(7,'Kit herramientas','Kit herramientas para reparacion de bicicletas',700.00,10,'https://http2.mlstatic.com/kit-de-herramientas-bicicleta-para-cinturon-D_NQ_NP_19684-MLC20175415289_102014-F.jpg',11,5,4,3),(8,'Kit velocimetro y luces','Kit velocimetro y luces para bicicletas varias',400.00,20,'https://http2.mlstatic.com/kit-velocimetro-luces-bicicleta-del-tras-roja-blanca-pilas-D_NQ_NP_882824-MLA28133868701_092018-F.jpg',12,5,4,3),(9,'Casco bicicleta','Casco bicicleta con visera y ventilacion',800.00,15,'http://www.bicitiendaonline.com/wp-content/uploads/2017/10/51wTE4-9IQL.jpg',12,5,4,6),(10,'Kit cubiertas x2','Kit cubiertas para MTB',1500.00,19,'https://http2.mlstatic.com/kit-x-2-cubierta-mountain-bike-arisun-26-x-210-camaras-D_NQ_NP_787770-MLA29051346922_122018-F.jpg',13,3,1,3);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rodados`
--

DROP TABLE IF EXISTS `rodados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rodados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rodados`
--

LOCK TABLES `rodados` WRITE;
/*!40000 ALTER TABLE `rodados` DISABLE KEYS */;
INSERT INTO `rodados` VALUES (1,'26','Rodado 26'),(2,'28','Rodado 28'),(3,'29','Rodado 29'),(5,'Indistinto','Todos los rodados');
/*!40000 ALTER TABLE `rodados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_producto`
--

DROP TABLE IF EXISTS `tipo_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tipo_producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `descripcion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (5,'Insumos','Insumos Varios'),(7,'Bicicletas','Bicicletas Armadas'),(11,'Herramientas','Herramientas para reparaciones varias'),(12,'Accesorios','Accesorios varios'),(13,'Repuestos','Repuestos varios');
/*!40000 ALTER TABLE `tipo_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `user` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `tipo_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `user` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','admin','admin@admin.com','admin 123','123456789','admin','admin',1),(3,'user','user','sales.vulcanobike@gmail.com','user 123','123','user','user',2),(5,'David','Cascallares','davidc2911@gmail.com','ocampo 537','3416416747','davidc2911','123456',2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-01 20:52:44
