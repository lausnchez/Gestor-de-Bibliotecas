CREATE DATABASE  IF NOT EXISTS `bibliotecas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bibliotecas`;
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
-- Table structure for table `administradores`
--

DROP TABLE IF EXISTS `administradores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administradores` (
  `id_admin` int NOT NULL AUTO_INCREMENT,
  `biblioteca_admin` int NOT NULL,
  `dni_admin` varchar(45) NOT NULL,
  `nombre_admin` varchar(45) NOT NULL,
  `apellido_admin` varchar(45) NOT NULL,
  `telefono_admin` varchar(45) NOT NULL,
  `email_admin` varchar(45) NOT NULL,
  `usuario_admin` int NOT NULL,
  PRIMARY KEY (`id_admin`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administradores`
--

LOCK TABLES `administradores` WRITE;
/*!40000 ALTER TABLE `administradores` DISABLE KEYS */;
INSERT INTO `administradores` VALUES (1,1,'11122233Z','Laura','Pérez','612345679','admin.lauraperez@biblioteca1.com',21),(2,2,'22233344Y','Mario','López','687654322','admin.mariolopez@biblioteca2.com',22),(3,3,'33344455X','Sofía','Martínez','678912346','admin.sofiamartinez@biblioteca3.com',23),(4,4,'44455566W','Andrés','García','689123457','admin.andresgarcia@biblioteca4.com',24),(5,5,'55566677V','María','Hernández','671234568','admin.mariahernandez@biblioteca5.com',25),(6,6,'66677788U','Pablo','Jiménez','654321099','admin.pablojimenez@biblioteca6.com',26),(7,7,'77788899T','Elena','Ortega','673210988','admin.elenaortega@biblioteca7.com',27),(8,8,'88899900S','Carlos','Santos','632109877','admin.carlossantos@biblioteca8.com',28),(9,9,'99900011R','Alicia','Núñez','631098766','admin.alicianunez@biblioteca9.com',29),(10,10,'00011122Q','David','Castillo','690123457','admin.davidcastillo@biblioteca10.com',30);
/*!40000 ALTER TABLE `administradores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-21 13:14:47
