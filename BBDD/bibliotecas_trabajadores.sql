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
-- Table structure for table `trabajadores`
--

DROP TABLE IF EXISTS `trabajadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trabajadores` (
  `id_trab` int NOT NULL AUTO_INCREMENT,
  `biblioteca_trab` int NOT NULL,
  `dni_trab` varchar(45) NOT NULL,
  `nombre_trab` varchar(45) NOT NULL,
  `apellidos_trab` varchar(45) NOT NULL,
  `telefono_trab` varchar(45) NOT NULL,
  `email_trab` varchar(45) NOT NULL,
  `admin_trab` tinyint NOT NULL DEFAULT '0' COMMENT 'En caso de ser un admin se le dirá 1 (TRUE)',
  PRIMARY KEY (`id_trab`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajadores`
--

LOCK TABLES `trabajadores` WRITE;
/*!40000 ALTER TABLE `trabajadores` DISABLE KEYS */;
INSERT INTO `trabajadores` VALUES (1,1,'11122233Z','Laura','Pérez','612345679','admin.lauraperez@biblioteca1.com',1),(2,2,'22233344Y','Mario','López','687654322','admin.mariolopez@biblioteca2.com',1),(3,3,'33344455X','Sofía','Martínez','678912346','admin.sofiamartinez@biblioteca3.com',1),(4,4,'44455566W','Andrés','García','689123457','admin.andresgarcia@biblioteca4.com',1),(5,5,'55566677V','María','Hernández','671234568','admin.mariahernandez@biblioteca5.com',1),(6,6,'66677788U','Pablo','Jiménez','654321099','admin.pablojimenez@biblioteca6.com',1),(7,7,'77788899T','Elena','Ortega','673210988','admin.elenaortega@biblioteca7.com',1),(8,8,'88899900S','Carlos','Santos','632109877','admin.carlossantos@biblioteca8.com',1),(9,9,'99900011R','Alicia','Núñez','631098766','admin.alicianunez@biblioteca9.com',1),(10,10,'00011122Q','David','Castillo','690123457','admin.davidcastillo@biblioteca10.com',1),(11,1,'12345678Z','Laura','Pérez González','612345678','laura.perez@biblioteca1.com',0),(12,2,'87654321Y','Mario','López Sánchez','687654321','mario.lopez@biblioteca2.com',0),(13,3,'23456789X','Sofía','Martínez Ruiz','678912345','sofia.martinez@biblioteca3.com',0),(14,4,'34567890W','Andrés','García Torres','689123456','andres.garcia@biblioteca4.com',0),(15,5,'45678901V','María','Hernández López','671234567','maria.hernandez@biblioteca5.com',0),(16,6,'56789012U','Pablo','Jiménez Díaz','654321098','pablo.jimenez@biblioteca6.com',0),(17,7,'67890123T','Elena','Ortega Pérez','673210987','elena.ortega@biblioteca7.com',0),(18,8,'78901234S','Carlos','Santos Ruiz','632109876','carlos.santos@biblioteca8.com',0),(19,9,'89012345R','Alicia','Núñez Ramírez','631098765','alicia.nunez@biblioteca9.com',0),(20,10,'90123456Q','David','Castillo Romero','690123456','david.castillo@biblioteca10.com',0),(21,1,'11223344A','Ana','Ramírez Fernández','612345679','ana.ramirez@biblioteca1.com',0),(22,2,'22334455B','Luis','Gómez Morales','687654322','luis.gomez@biblioteca2.com',0),(23,3,'33445566C','Carla','Torres Vega','678912346','carla.torres@biblioteca3.com',0),(24,4,'44556677D','Manuel','Luna Ortega','689123457','manuel.luna@biblioteca4.com',0),(25,5,'55667788E','Lucía','Navarro Reyes','671234568','lucia.navarro@biblioteca5.com',0),(26,6,'66778899F','Rafael','Serrano Cruz','654321099','rafael.serrano@biblioteca6.com',0),(27,7,'77889900G','Sonia','Flores Ríos','673210988','sonia.flores@biblioteca7.com',0),(28,8,'88990011H','Diego','Sánchez Peña','632109877','diego.sanchez@biblioteca8.com',0),(29,9,'99001122I','Marta','Blanco Vázquez','631098766','marta.blanco@biblioteca9.com',0),(30,10,'00112233J','Roberto','Paredes Navas','690123457','roberto.paredes@biblioteca10.com',0);
/*!40000 ALTER TABLE `trabajadores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-22 13:27:09
