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
-- Table structure for table `bibliotecas`
--

DROP TABLE IF EXISTS `bibliotecas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bibliotecas` (
  `idBibliotecas` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `provincia` varchar(45) NOT NULL,
  `ciudad` varchar(45) NOT NULL,
  `calle` varchar(45) NOT NULL,
  `telefono` int NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`idBibliotecas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bibliotecas`
--

LOCK TABLES `bibliotecas` WRITE;
/*!40000 ALTER TABLE `bibliotecas` DISABLE KEYS */;
INSERT INTO `bibliotecas` VALUES (1,'Biblioteca Central','Madrid','Madrid','Calle Mayor 15',911234567,'central@madridbibliotecas.es'),(2,'Biblioteca La Paz','Barcelona','Barcelona','Avenida Diagonal 220',932112233,'lapaz@barcelonabibliotecas.es'),(3,'Biblioteca del Norte','Valencia','Valencia','Calle Norte 10',961234567,'norte@valenciabibliotecas.es'),(4,'Biblioteca Sur','Sevilla','Sevilla','Avenida de Andalucía 34',954123456,'sur@sevillabibliotecas.es'),(5,'Biblioteca Municipal','Zaragoza','Zaragoza','Calle Central 25',976987654,'municipal@zaragozabibliotecas.es'),(6,'Biblioteca de la Playa','Málaga','Málaga','Paseo Marítimo 101',952345678,'playa@malagabibliotecas.es'),(7,'Biblioteca El Parque','Alicante','Alicante','Calle Jardín 45',965123456,'parque@alicantebibliotecas.es'),(8,'Biblioteca Las Flores','Murcia','Murcia','Avenida de la Libertad 56',968234567,'flores@murciabibliotecas.es'),(9,'Biblioteca de la Montaña','Granada','Granada','Calle Sierra Nevada 18',958765432,'montana@granadabibliotecas.es'),(10,'Biblioteca del Mar','Valencia','Valencia','Calle del Puerto 22',962234567,'delmar@valenciabibliotecas.es');
/*!40000 ALTER TABLE `bibliotecas` ENABLE KEYS */;
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
