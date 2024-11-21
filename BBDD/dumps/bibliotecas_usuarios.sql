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
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usu` int NOT NULL,
  `nombre_usu` varchar(45) NOT NULL,
  `almacen_usu` int NOT NULL,
  PRIMARY KEY (`id_usu`),
  KEY `almacen_FK_idx` (`almacen_usu`),
  CONSTRAINT `almacen_FK` FOREIGN KEY (`almacen_usu`) REFERENCES `almacen` (`id_almacen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'laura.perez',1),(2,'mario.lopez',2),(3,'sofia.martinez',3),(4,'andres.garcia',4),(5,'maria.hernandez',5),(6,'pablo.jimenez',6),(7,'elena.ortega',7),(8,'carlos.santos',8),(9,'alicia.nunez',9),(10,'david.castillo',10),(11,'ana.ramirez',11),(12,'luis.gomez',12),(13,'carla.torres',13),(14,'manuel.luna',14),(15,'lucia.navarro',15),(16,'rafael.serrano',16),(17,'sonia.flores',17),(18,'diego.sanchez',18),(19,'marta.blanco',19),(20,'roberto.paredes',20),(21,'admin.lauraperez',21),(22,'admin.mariolopez',22),(23,'admin.sofiamartinez',23),(24,'admin.andresgarcia',24),(25,'admin.mariahernandez',25),(26,'admin.pablojimenez',26),(27,'admin.elenaortega',27),(28,'admin.carlossantos',28),(29,'admin.alicianunez',29),(30,'admin.davidcastillo',30);
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

-- Dump completed on 2024-11-21 12:01:44
