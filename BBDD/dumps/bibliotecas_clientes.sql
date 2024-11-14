-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: bibliotecas
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `idCliente` int NOT NULL,
  `bibliotecaAsociada` int NOT NULL,
  `dni` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `provincia` varchar(45) NOT NULL,
  `ciudad` varchar(45) NOT NULL,
  `calle` varchar(45) NOT NULL,
  `numSanciones` varchar(45) NOT NULL,
  `cuentaBancaria` varchar(45) NOT NULL,
  `pago` tinyint NOT NULL,
  PRIMARY KEY (`idCliente`),
  KEY `BilbiotecaAsociada_FK_idx` (`bibliotecaAsociada`),
  CONSTRAINT `BilbiotecaAsociada_FK` FOREIGN KEY (`bibliotecaAsociada`) REFERENCES `bibliotecas` (`idBibliotecas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,1,'12345678A','Luis','Martínez López','612345678','luis.martinez@gmail.com','Madrid','Madrid','Calle Alcalá 12','0','ES12345678901234567890',1),(2,2,'87654321B','María','García Sánchez','687654321','maria.garcia@gmail.com','Barcelona','Barcelona','Avenida Cataluña 45','1','ES09876543210987654321',0),(3,3,'23456789C','Carlos','Pérez Gómez','678912345','carlos.perez@hotmail.com','Valencia','Valencia','Calle Colón 23','0','ES23456789012345678901',1),(4,4,'34567890D','Ana','López Fernández','689123456','ana.lopez@outlook.com','Sevilla','Sevilla','Avenida de la Paz 67','2','ES34567890123456789012',0),(5,5,'45678901E','Sofía','Ramírez Torres','671234567','sofia.ramirez@yahoo.es','Zaragoza','Zaragoza','Calle Mayor 34','0','ES45678901234567890123',1),(6,6,'56789012F','Javier','Ortega Díaz','654321098','javier.ortega@gmail.com','Málaga','Málaga','Paseo de la Alameda 90','1','ES56789012345678901234',0),(7,7,'67890123G','Teresa','Molina Ruiz','673210987','teresa.molina@hotmail.com','Alicante','Alicante','Calle Primavera 78','0','ES67890123456789012345',1),(8,8,'78901234H','Raúl','Giménez Rojas','632109876','raul.gimenez@gmail.com','Murcia','Murcia','Calle del Sol 56','3','ES78901234567890123456',0),(9,9,'89012345I','Elena','Núñez Hernández','631098765','elena.nunez@yahoo.com','Granada','Granada','Calle Sierra Nevada 14','0','ES89012345678901234567',1),(10,10,'90123456J','David','Santos Castillo','690123456','david.santos@gmail.com','Valencia','Valencia','Calle del Mar 42','2','ES90123456789012345678',0);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-14 16:40:35
