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
-- Table structure for table `libros`
--

DROP TABLE IF EXISTS `libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libros` (
  `idLibros` int NOT NULL,
  `bibliotecaAsociada` int NOT NULL,
  `isbn` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `autor` int NOT NULL,
  `editorial` varchar(45) NOT NULL,
  `precio` int NOT NULL,
  `estado` varchar(45) NOT NULL,
  PRIMARY KEY (`idLibros`),
  KEY `BibliotecaLibros_FK_idx` (`bibliotecaAsociada`),
  CONSTRAINT `BibliotecaLibros_FK` FOREIGN KEY (`bibliotecaAsociada`) REFERENCES `bibliotecas` (`idBibliotecas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES (1,1,'9780140449136','Don Quijote de la Mancha',1,'Penguin Classics',13,'Disponible'),(2,1,'9788420678212','Cien años de soledad',2,'Alfaguara',15,'Prestado'),(3,2,'9788491051420','La sombra del viento',3,'Planeta',19,'Disponible'),(4,2,'9788423347917','El amor en los tiempos del cólera',2,'Debolsillo',16,'En reparación'),(5,3,'9788478884452','1984',4,'Anagrama',15,'Disponible'),(6,3,'9788445000558','El Principito',5,'Salamandra',10,'Prestado'),(7,4,'9788466331979','Los pilares de la tierra',6,'DeBolsillo',21,'Disponible'),(8,4,'9788437604947','Rayuela',7,'Cátedra',14,'Extraviado'),(9,5,'9788467031709','La casa de los espíritus',8,'Plaza & Janés',17,'Disponible'),(10,5,'9788497939937','La sombra del viento',3,'Booket',13,'Prestado');
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-14 16:40:34
