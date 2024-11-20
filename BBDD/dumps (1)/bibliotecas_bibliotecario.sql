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
-- Table structure for table `bibliotecario`
--

DROP TABLE IF EXISTS `bibliotecario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bibliotecario` (
  `id_trab` int NOT NULL,
  `biblioteca_trab` int NOT NULL,
  `dni_trab` varchar(45) NOT NULL,
  `nombre_trab` varchar(45) NOT NULL,
  `apellidos_trab` varchar(45) NOT NULL,
  `usuario_trab` int NOT NULL,
  `telefono_trab` varchar(45) NOT NULL,
  `email_trab` varchar(45) NOT NULL,
  PRIMARY KEY (`id_trab`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bibliotecario`
--

LOCK TABLES `bibliotecario` WRITE;
/*!40000 ALTER TABLE `bibliotecario` DISABLE KEYS */;
INSERT INTO `bibliotecario` VALUES (1,1,'12345678Z','Laura','Pérez González',1,'612345678','laura.perez@biblioteca1.com'),(2,2,'87654321Y','Mario','López Sánchez',2,'687654321','mario.lopez@biblioteca2.com'),(3,3,'23456789X','Sofía','Martínez Ruiz',3,'678912345','sofia.martinez@biblioteca3.com'),(4,4,'34567890W','Andrés','García Torres',4,'689123456','andres.garcia@biblioteca4.com'),(5,5,'45678901V','María','Hernández López',5,'671234567','maria.hernandez@biblioteca5.com'),(6,6,'56789012U','Pablo','Jiménez Díaz',6,'654321098','pablo.jimenez@biblioteca6.com'),(7,7,'67890123T','Elena','Ortega Pérez',7,'673210987','elena.ortega@biblioteca7.com'),(8,8,'78901234S','Carlos','Santos Ruiz',8,'632109876','carlos.santos@biblioteca8.com'),(9,9,'89012345R','Alicia','Núñez Ramírez',9,'631098765','alicia.nunez@biblioteca9.com'),(10,10,'90123456Q','David','Castillo Romero',10,'690123456','david.castillo@biblioteca10.com'),(11,1,'11223344A','Ana','Ramírez Fernández',11,'612345679','ana.ramirez@biblioteca1.com'),(12,2,'22334455B','Luis','Gómez Morales',12,'687654322','luis.gomez@biblioteca2.com'),(13,3,'33445566C','Carla','Torres Vega',13,'678912346','carla.torres@biblioteca3.com'),(14,4,'44556677D','Manuel','Luna Ortega',14,'689123457','manuel.luna@biblioteca4.com'),(15,5,'55667788E','Lucía','Navarro Reyes',15,'671234568','lucia.navarro@biblioteca5.com'),(16,6,'66778899F','Rafael','Serrano Cruz',16,'654321099','rafael.serrano@biblioteca6.com'),(17,7,'77889900G','Sonia','Flores Ríos',17,'673210988','sonia.flores@biblioteca7.com'),(18,8,'88990011H','Diego','Sánchez Peña',18,'632109877','diego.sanchez@biblioteca8.com'),(19,9,'99001122I','Marta','Blanco Vázquez',19,'631098766','marta.blanco@biblioteca9.com'),(20,10,'00112233J','Roberto','Paredes Navas',20,'690123457','roberto.paredes@biblioteca10.com');
/*!40000 ALTER TABLE `bibliotecario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-20  2:17:55
