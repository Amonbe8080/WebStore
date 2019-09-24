-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: webstore
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.39-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `cuentausr`
--

DROP TABLE IF EXISTS `cuentausr`;
/*!50001 DROP VIEW IF EXISTS `cuentausr`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `cuentausr` AS SELECT 
 1 AS `(select count(*) from usuario where idtipousua =1)`,
 1 AS `(select count(*) from usuario where idtipousua =2)`,
 1 AS `(select count(*) from usuario where idtipousua =3)`,
 1 AS `(select count(*) from usuario where idtipousua =4)`,
 1 AS `(select count(*) from usuario where idtipousua =5)`,
 1 AS `(select count(*) from usuario where EstaUsua = 'Activo')`,
 1 AS `(select count(*) from usuario where EstaUsua = 'Inactivo')`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `datocont`
--

DROP TABLE IF EXISTS `datocont`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `datocont` (
  `idDatoCont` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador de la tabla para indicar a que usuario pertenece',
  `idTipoDocu` varchar(4) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Identificacion de tipo de Documento',
  `idEmpresa` int(11) NOT NULL COMMENT 'Numero de Identificacion de la Empresa',
  `idTipoUsua` int(11) NOT NULL COMMENT 'Identificacion de Tipo de Usuario',
  `idUsuario` decimal(10,0) NOT NULL COMMENT 'Identificacion del Usuario (puede Ser el Numero de Cedula)',
  `Direccion` varchar(200) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Direccion postal o ubicacion fisica de la persona, usuario o provedor',
  `TeleFijo` decimal(10,0) DEFAULT NULL COMMENT 'Numero telefonico de contacto',
  `Fax` decimal(10,0) DEFAULT NULL COMMENT 'Numero de Fax de la persona, usuario o proveedor',
  `Celular` decimal(10,0) DEFAULT NULL COMMENT 'Numero de Celular persona, usuario o proveedor',
  `Email` varchar(100) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Correo Electronico de persona, usuario o proveedor',
  `EstaDato` varchar(15) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Estao de los datos de contacto',
  PRIMARY KEY (`idDatoCont`,`idTipoDocu`,`idEmpresa`,`idTipoUsua`,`idUsuario`),
  KEY `fk_DatoCont_Empresa_idx` (`idEmpresa`),
  KEY `fk_DatoCont_TipoDocu1_idx` (`idTipoDocu`),
  KEY `fk_DatoCont_Usuario1_idx` (`idUsuario`,`idTipoUsua`),
  CONSTRAINT `fk_DatoCont_Empresa` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DatoCont_TipoDocu1` FOREIGN KEY (`idTipoDocu`) REFERENCES `tipodocu` (`idTipoDocu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DatoCont_Usuario1` FOREIGN KEY (`idUsuario`, `idTipoUsua`) REFERENCES `usuario` (`idUsuario`, `idTipoUsua`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Tabla para los datos de Contacto';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datocont`
--

LOCK TABLES `datocont` WRITE;
/*!40000 ALTER TABLE `datocont` DISABLE KEYS */;
/*!40000 ALTER TABLE `datocont` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detafopa`
--

DROP TABLE IF EXISTS `detafopa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detafopa` (
  `idDetaFoPa` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador de detalle de Forma de Pago',
  `idFormPago` varchar(2) COLLATE latin1_spanish_ci NOT NULL,
  `idTipoTran` varchar(3) COLLATE latin1_spanish_ci NOT NULL,
  `idTransaccion` int(11) NOT NULL,
  `VaFoPago` decimal(20,2) DEFAULT NULL COMMENT 'Valor de Cada Forma de Pago',
  PRIMARY KEY (`idDetaFoPa`,`idFormPago`,`idTipoTran`,`idTransaccion`),
  KEY `fk_DetaFoPa_Transaccion1_idx` (`idTransaccion`,`idTipoTran`),
  KEY `fk_DetaFoPa_FormPago1_idx` (`idFormPago`),
  CONSTRAINT `fk_DetaFoPa_FormPago1` FOREIGN KEY (`idFormPago`) REFERENCES `formpago` (`idFormPago`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DetaFoPa_Transaccion1` FOREIGN KEY (`idTransaccion`, `idTipoTran`) REFERENCES `transaccion` (`idTransaccion`, `idTipoTran`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Tabla de detalle de Forma de Pago';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detafopa`
--

LOCK TABLES `detafopa` WRITE;
/*!40000 ALTER TABLE `detafopa` DISABLE KEYS */;
/*!40000 ALTER TABLE `detafopa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detatran`
--

DROP TABLE IF EXISTS `detatran`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detatran` (
  `idDetaTran` int(11) NOT NULL AUTO_INCREMENT,
  `idTipoTran` varchar(3) COLLATE latin1_spanish_ci NOT NULL,
  `idTransaccion` int(11) NOT NULL,
  `idProductos` int(11) NOT NULL,
  `Cantidad` decimal(10,0) NOT NULL,
  `ValoCant` decimal(20,2) NOT NULL,
  PRIMARY KEY (`idDetaTran`,`idTipoTran`,`idTransaccion`,`idProductos`),
  KEY `fk_DetaTran_Transaccion1_idx` (`idTransaccion`,`idTipoTran`),
  KEY `fk_DetaTran_Productos1_idx` (`idProductos`),
  CONSTRAINT `fk_DetaTran_Productos1` FOREIGN KEY (`idProductos`) REFERENCES `productos` (`idProductos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DetaTran_Transaccion1` FOREIGN KEY (`idTransaccion`, `idTipoTran`) REFERENCES `transaccion` (`idTransaccion`, `idTipoTran`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detatran`
--

LOCK TABLES `detatran` WRITE;
/*!40000 ALTER TABLE `detatran` DISABLE KEYS */;
/*!40000 ALTER TABLE `detatran` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `idEmpresa` int(11) NOT NULL COMMENT 'Número de NIT (Numero de Identificación Tributario) de la Empresa',
  `DigiVeri` decimal(10,0) NOT NULL COMMENT 'Digito de Verificacion del NIT Ej (900555630-9) -9',
  `NombEmpr` varchar(200) NOT NULL COMMENT 'Nombre de la empresa',
  `WebEmpresa` varchar(50) NOT NULL COMMENT 'Direccion de página WEB de la Empresa',
  `RepreLegal` varchar(45) NOT NULL COMMENT 'Nombre del Representante legal',
  PRIMARY KEY (`idEmpresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabla para los datos de la empresa';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (900100200,9,'The Eder\'s webstore','www.ederwebstore.com','Eder Lara');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `enterprice`
--

DROP TABLE IF EXISTS `enterprice`;
/*!50001 DROP VIEW IF EXISTS `enterprice`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `enterprice` AS SELECT 
 1 AS `empresa`,
 1 AS `nombempr`,
 1 AS `Direccion`,
 1 AS `telefonos`,
 1 AS `fax`,
 1 AS `Email`,
 1 AS `webempresa`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!50001 DROP VIEW IF EXISTS `factura`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `factura` AS SELECT 
 1 AS `numefact`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `formpago`
--

DROP TABLE IF EXISTS `formpago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formpago` (
  `idFormPago` varchar(2) COLLATE latin1_spanish_ci NOT NULL,
  `NombFoPa` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`idFormPago`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formpago`
--

LOCK TABLES `formpago` WRITE;
/*!40000 ALTER TABLE `formpago` DISABLE KEYS */;
INSERT INTO `formpago` VALUES ('BN','BONO'),('CH','CHEQUE'),('CR','CREDITO'),('EF','EFECTIVO'),('TC','TARJETA CREDITO'),('TD','TARJETA DEBITO');
/*!40000 ALTER TABLE `formpago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `listarproductos`
--

DROP TABLE IF EXISTS `listarproductos`;
/*!50001 DROP VIEW IF EXISTS `listarproductos`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `listarproductos` AS SELECT 
 1 AS `idProductos`,
 1 AS `Categoria`,
 1 AS `NombProd`,
 1 AS `ValorComp`,
 1 AS `ValorVent`,
 1 AS `CantProd`,
 1 AS `EstaProd`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `listarusuarios`
--

DROP TABLE IF EXISTS `listarusuarios`;
/*!50001 DROP VIEW IF EXISTS `listarusuarios`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `listarusuarios` AS SELECT 
 1 AS `idUsuario`,
 1 AS `idTipoUsua`,
 1 AS `NombUsua`,
 1 AS `ApelUsua`,
 1 AS `GeneUsua`,
 1 AS `EstaUsua`,
 1 AS `NickUsua`,
 1 AS `PassUsua`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `idProductos` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificacion del Producto ',
  `idTipoProd` int(11) NOT NULL,
  `NombProd` varchar(100) NOT NULL COMMENT 'Nombre del Producto',
  `ValorComp` decimal(20,2) NOT NULL COMMENT 'Valor de compra al proveedor',
  `ValorVent` decimal(20,2) NOT NULL COMMENT 'Valor de Venta al Cliente',
  `CantProd` decimal(20,0) NOT NULL,
  `EstaProd` varchar(45) NOT NULL COMMENT 'Estado del Producto',
  `img` blob,
  PRIMARY KEY (`idProductos`,`idTipoProd`),
  KEY `fk_Productos_TipoProd1_idx` (`idTipoProd`),
  CONSTRAINT `fk_Productos_TipoProd1` FOREIGN KEY (`idTipoProd`) REFERENCES `tipoprod` (`idTipoProd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='Tabla para la Discriminacion de los Productos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,1,'Camisa Polo Cafe',34.00,34.00,1,'Disponible',NULL),(6,4,'Zapato en Cuerina Negra',65.00,75.00,50,'Disponible',NULL),(7,4,'Zapato en Cuerina Cafe',65.00,115.00,50,'Disponible',NULL),(8,4,'Zapato en Cuerina Naranja',65.00,175.00,50,'Disponible','java.io.FileInputStream@7d717332'),(9,5,'Tanga Narizona',5.00,8.00,12,'Disponible',NULL),(10,1,'Camisa de manga larga',5.00,5.00,5,'Disponible',NULL),(11,4,'Zapatilla Rosada',3.00,3.00,3,'Disponible',NULL),(12,1,'Camida de manga corta',5.00,5.00,5,'Disponible',NULL),(13,1,'Camisa de manga larga color negro',55.00,51.00,52,'Disponible',NULL),(14,2,' pantalon cuero dama negro',100.00,101.00,52,'Disponible',NULL),(15,2,' pantalon cuero unisex',100.00,101.00,52,'Disponible',NULL),(16,2,' pantalon cuero dama',100.00,101.00,52,'Disponible',NULL),(17,2,' pantalon cuero Hombre',100.00,101.00,52,'Disponible',NULL),(18,2,' pantalon cuero hombre piel',100.00,101.00,52,'Disponible',NULL),(19,2,' pantalon cuero XL',100.00,101.00,52,'Disponible',NULL),(20,2,' pantalon cuero M',100.00,101.00,52,'Disponible',NULL),(21,4,'chanclas nike',15.00,50.00,25,'Disponible',NULL),(22,3,'Camiseta azul que no es azul',12.00,42.00,10,'Disponible',NULL),(23,2,'Jersey Camuflado',224.00,223.00,78,'Disponible',NULL);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipodocu`
--

DROP TABLE IF EXISTS `tipodocu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipodocu` (
  `idTipoDocu` varchar(4) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Tipo de Documento de Identificacion, C.C. , R.C. , T.I. , etc.',
  `NombTiDo` varchar(45) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Nombre de tipo de Documento de Identificacion Ej: Cedula de Ciudadania, Registro Civil, etc.',
  PRIMARY KEY (`idTipoDocu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipodocu`
--

LOCK TABLES `tipodocu` WRITE;
/*!40000 ALTER TABLE `tipodocu` DISABLE KEYS */;
INSERT INTO `tipodocu` VALUES ('CC','Cedula de Ciudadania'),('CE','Cedula Extrranjeria'),('MS','Menor sin Idenfiicacion'),('NIT','Numero Tributario'),('NN','NO Identificado'),('PS','Pasaporte'),('RC','Registro Civil'),('TI','Tarjeta de Identidad');
/*!40000 ALTER TABLE `tipodocu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoprod`
--

DROP TABLE IF EXISTS `tipoprod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoprod` (
  `idTipoProd` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificacion de tipo de producto',
  `NoTiProd` varchar(100) NOT NULL COMMENT 'Nombre de tipo de Producto',
  PRIMARY KEY (`idTipoProd`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='Tabla para los tipos de Producto';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoprod`
--

LOCK TABLES `tipoprod` WRITE;
/*!40000 ALTER TABLE `tipoprod` DISABLE KEYS */;
INSERT INTO `tipoprod` VALUES (1,'Camisas'),(2,'Pantalones'),(3,'Camisetas'),(4,'Calzado'),(5,'Ropa Interior');
/*!40000 ALTER TABLE `tipoprod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipotran`
--

DROP TABLE IF EXISTS `tipotran`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipotran` (
  `idTipoTran` varchar(3) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Llave primaria de la tabla tipo de transaccion',
  `NoTiTran` varchar(45) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Nombre de Tipo de Transaccion',
  PRIMARY KEY (`idTipoTran`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Tabla que muestra los documentos que puedo generar en la tienda';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipotran`
--

LOCK TABLES `tipotran` WRITE;
/*!40000 ALTER TABLE `tipotran` DISABLE KEYS */;
INSERT INTO `tipotran` VALUES ('ABN','ABONOS'),('COM','COMPRAS'),('COT','COTIZACIONES'),('PED','PEDIDOS'),('VEN','VENTAS');
/*!40000 ALTER TABLE `tipotran` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipousua`
--

DROP TABLE IF EXISTS `tipousua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipousua` (
  `idTipoUsua` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificacion de tipo de Usuario',
  `NombTiUs` varchar(45) NOT NULL COMMENT 'Nombre de Tipo de Usuario (Clientes, Proveedores, Empleados y Administradores)',
  PRIMARY KEY (`idTipoUsua`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='Tabla de Tipos de Usuarios, Clientes, Proveedores, Empleados y Administradores';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipousua`
--

LOCK TABLES `tipousua` WRITE;
/*!40000 ALTER TABLE `tipousua` DISABLE KEYS */;
INSERT INTO `tipousua` VALUES (1,'Administrador'),(2,'Empleado'),(3,'Proveedor'),(4,'Cliente'),(5,'Aprendiz');
/*!40000 ALTER TABLE `tipousua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaccion`
--

DROP TABLE IF EXISTS `transaccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaccion` (
  `idTransaccion` int(11) NOT NULL AUTO_INCREMENT COMMENT 'LLave primaria de la transaccion, indica cual es el  numero de transacccion que llevamos',
  `idTipoTran` varchar(3) COLLATE latin1_spanish_ci NOT NULL,
  `idTipoUsua` int(11) NOT NULL,
  `idUsuario` decimal(10,0) NOT NULL,
  `idFormPago` varchar(2) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Forma de Pago de la Transaccion',
  `NumeTran` decimal(10,0) NOT NULL COMMENT 'Es el numero de documento segun el tipo de transaccion',
  `FechTran` date NOT NULL COMMENT 'Fecha de elaboracion de la transaccion',
  `NotaTran` varchar(250) COLLATE latin1_spanish_ci NOT NULL,
  `ValoTran` decimal(20,2) NOT NULL COMMENT 'Total o costo de la transaccion',
  `SubToTran` decimal(20,2) NOT NULL,
  `IvaTran` decimal(20,2) NOT NULL,
  `SaldTran` decimal(20,2) NOT NULL,
  `EstaTran` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`idTransaccion`,`idTipoTran`,`idTipoUsua`,`idUsuario`,`idFormPago`),
  KEY `fk_Transaccion_TipoTran1_idx` (`idTipoTran`),
  KEY `fk_Transaccion_Usuario1_idx` (`idUsuario`,`idTipoUsua`),
  KEY `fk_Transaccion_FormPago1_idx` (`idFormPago`),
  CONSTRAINT `fk_Transaccion_FormPago1` FOREIGN KEY (`idFormPago`) REFERENCES `formpago` (`idFormPago`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaccion_TipoTran1` FOREIGN KEY (`idTipoTran`) REFERENCES `tipotran` (`idTipoTran`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaccion_Usuario1` FOREIGN KEY (`idUsuario`, `idTipoUsua`) REFERENCES `usuario` (`idUsuario`, `idTipoUsua`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Tabla que detalla todas las transacciones de la tienda';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaccion`
--

LOCK TABLES `transaccion` WRITE;
/*!40000 ALTER TABLE `transaccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` decimal(10,0) NOT NULL COMMENT 'Numero de idenficacion del Usuario',
  `idTipoUsua` int(11) NOT NULL COMMENT 'Tipo de Usuario ej 1.Cliente',
  `NombUsua` varchar(50) NOT NULL COMMENT 'Nombres del Usuario',
  `ApelUsua` varchar(50) NOT NULL COMMENT 'Apellidos del Usuario',
  `GeneUsua` varchar(1) NOT NULL COMMENT 'Genero Sexual con el que Nació El Usuario',
  `EstaUsua` varchar(15) NOT NULL COMMENT 'Estado del Usuario Ej  (Activo o Inactivo)',
  `NickUsua` varchar(50) NOT NULL DEFAULT 'elara',
  `PassUsua` varchar(100) DEFAULT NULL COMMENT 'Password del usuario',
  PRIMARY KEY (`idUsuario`,`idTipoUsua`),
  KEY `fk_Usuario_TipoUsua1_idx` (`idTipoUsua`),
  CONSTRAINT `fk_Usuario_TipoUsua1` FOREIGN KEY (`idTipoUsua`) REFERENCES `tipousua` (`idTipoUsua`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabla para la discriminacion de los usuarios del sistema';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (12,5,'Kevin','Suaza Gomez','M','Activo','kealsugo','12345'),(102,5,'Juan de Dios','Gonzales Pata','M','Activo','oefe','lipe'),(12343,2,'Bonzo Gonzo','Bonanza Gomez','M','Inactivo','jije','132'),(10062221,1,'Juan','Tamallo Ochoa','M','Activo','tamayito','re'),(14355955,5,'Eder','Lara Trujillo','M','Activo','elarat','123456'),(14395999,1,'Eder','Lara Trujillo','M','Inactivo','elara','fu');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!50001 DROP VIEW IF EXISTS `usuarios`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `usuarios` AS SELECT 
 1 AS `Admin`,
 1 AS `Empleado`,
 1 AS `Proveedor`,
 1 AS `Cliente`,
 1 AS `Aprendiz`,
 1 AS `Activos`,
 1 AS `InActivos`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'webstore'
--

--
-- Dumping routines for database 'webstore'
--
/*!50003 DROP PROCEDURE IF EXISTS `CrudProducto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CrudProducto`(IN `v_idProductos` INT(11), IN `v_categoria` int(11), IN `v_NombProd` VARCHAR(100), IN `v_ValorComp` DECIMAL(20,2), IN `v_ValorVenta`  DECIMAL(20,2), IN `v_CantProd`  DECIMAL(20,2), IN `v_EstaProd` VARCHAR(45), IN `boton` VARCHAR(45))
BEGIN
-- Procedimiento Almacenado para gestionar los datos del producto:
-- Edicion = Amonbe - Autor = @Eder Lara 2019
-- Declaracion de constantes:
set @EstaProd = 'Disponible';
-- condicionamos el tipo de operacion con la tabla:
case
when boton = 'guardar' then
-- Insertamos los datos a la tabla:
insert into productos (`idTipoProd`, `NombProd`, `ValorComp`, `ValorVent`, `CantProd`, `EstaProd`) values (v_categoria, v_NombProd, v_ValorComp, v_ValorVenta, v_CantProd, v_EstaProd);

when boton = 'modificar' then
-- Modificamos los datos de la tabla, en este caso podemos modificar todo excepto el id del producto, se podra modificar EstaProd a Agotado o En espera:
update productos
set IdTipoProd = v_categoria, NombProd = v_NombProd, ValorComp = v_ValorComp, ValorVent = v_ValorVenta, CantProd = v_CantProd, EstaProd = v_EstaProd
where idProductos = v_idProductos;

when boton = 'eliminar' then
set  @EstaProd = 'No Disponible';
update productos
set EstaProd =  @EstaProd
where idProductos = v_idProductos;

end case;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `CrudUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CrudUsuario`(IN `v_idUsuario` DECIMAL(10,0), IN `v_idTipoUsua` INT, IN `v_NombUsua` VARCHAR(255), IN `v_ApelUsua` VARCHAR(255), IN `v_GeneUsua` VARCHAR(1), IN `v_NickUsua` VARCHAR(255), IN `v_PassUsua` VARCHAR(255), IN `boton` VARCHAR(45))
BEGIN
-- Procedimiento Almacenado para gestionar los datos del usuario:
-- Autor = @Eder Lara 2019
-- Declaracion de constantes:
set @estausua = 'Activo';

-- condicionamos el tipo de operacion con la tabla:
case
when boton = 'guardar' then
-- Insertamos los datos a la tabla:
insert into usuario values (v_idUsuario, v_idTipoUsua, v_NombUsua, v_ApelUsua, v_GeneUsua, @estausua, v_NickUsua, v_PassUsua);

when boton = 'modificar' then
-- Modificamos los datos de la tabla, en este caso podemos modificar todo excepto el id del usuario, el tipo del usuario y el estado:
update usuario
set NombUsua = v_NombUsua, idTipoUsua = v_idTipoUsua, ApelUsua = v_ApelUsua, GeneUsua = v_GeneUsua, NickUsua = v_NickUsua, PassUsua = v_PassUsua
where idUsuario = v_idUsuario ;

when boton = 'eliminar' then
set  @estausua = 'Inactivo';
update usuario
set estausua =  @estausua 
where idUsuario = v_idUsuario and idTipoUsua= v_idTipoUsua;

end case;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `login`(`usuario` VARCHAR(255), `passusua` VARCHAR(255))
BEGIN 
-- Procedimiento Alamacenado para iniciar sesion 
-- Autor : @Eder Lara -2019

SELECT idUsuario, idTipoUsua, concat(NombUsua, ' ', ApelUsua) as usuario 
from usuario
where NickUsua = usuario  and PassUsua = passusua and EstaUsua = 'Activo';

-- Fin de procedimiento almacenado para Iniciar Sesion

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `cuentausr`
--

/*!50001 DROP VIEW IF EXISTS `cuentausr`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `cuentausr` AS select 1 AS `(select count(*) from usuario where idtipousua =1)`,1 AS `(select count(*) from usuario where idtipousua =2)`,1 AS `(select count(*) from usuario where idtipousua =3)`,1 AS `(select count(*) from usuario where idtipousua =4)`,1 AS `(select count(*) from usuario where idtipousua =5)`,1 AS `(select count(*) from usuario where EstaUsua = 'Activo')`,1 AS `(select count(*) from usuario where EstaUsua = 'Inactivo')` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `enterprice`
--

/*!50001 DROP VIEW IF EXISTS `enterprice`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `enterprice` AS select 1 AS `empresa`,1 AS `nombempr`,1 AS `Direccion`,1 AS `telefonos`,1 AS `fax`,1 AS `Email`,1 AS `webempresa` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `factura`
--

/*!50001 DROP VIEW IF EXISTS `factura`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `factura` AS select 1 AS `numefact` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `listarproductos`
--

/*!50001 DROP VIEW IF EXISTS `listarproductos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `listarproductos` AS select `p`.`idProductos` AS `idProductos`,`tp`.`NoTiProd` AS `Categoria`,`p`.`NombProd` AS `NombProd`,`p`.`ValorComp` AS `ValorComp`,`p`.`ValorVent` AS `ValorVent`,`p`.`CantProd` AS `CantProd`,`p`.`EstaProd` AS `EstaProd` from (`productos` `p` join `tipoprod` `tp`) where ((`p`.`EstaProd` = 'Disponible') and (`tp`.`idTipoProd` = `p`.`idTipoProd`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `listarusuarios`
--

/*!50001 DROP VIEW IF EXISTS `listarusuarios`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `listarusuarios` AS select `usuario`.`idUsuario` AS `idUsuario`,`usuario`.`idTipoUsua` AS `idTipoUsua`,`usuario`.`NombUsua` AS `NombUsua`,`usuario`.`ApelUsua` AS `ApelUsua`,`usuario`.`GeneUsua` AS `GeneUsua`,`usuario`.`EstaUsua` AS `EstaUsua`,`usuario`.`NickUsua` AS `NickUsua`,`usuario`.`PassUsua` AS `PassUsua` from `usuario` where (`usuario`.`EstaUsua` = 'Activo') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `usuarios`
--

/*!50001 DROP VIEW IF EXISTS `usuarios`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `usuarios` AS select 1 AS `Admin`,1 AS `Empleado`,1 AS `Proveedor`,1 AS `Cliente`,1 AS `Aprendiz`,1 AS `Activos`,1 AS `InActivos` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-23 17:43:56
