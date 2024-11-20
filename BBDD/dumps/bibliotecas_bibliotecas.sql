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
  `id_biblio` int NOT NULL,
  `nombre_biblio` varchar(45) NOT NULL,
  `provincias_biblio` varchar(45) NOT NULL,
  `ciudad_biblio` varchar(45) NOT NULL,
  `calle_biblio` varchar(45) NOT NULL,
  `telefono_biblio` int NOT NULL,
  `email_blbio` varchar(45) NOT NULL,
  PRIMARY KEY (`id_biblio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bibliotecas`
--

LOCK TABLES `bibliotecas` WRITE;
/*!40000 ALTER TABLE `bibliotecas` DISABLE KEYS */;
INSERT INTO `bibliotecas` VALUES (1,'Biblioteca Central','Madrid','Madrid','Calle Mayor 15',911234567,'central@madridbibliotecas.es'),(2,'Biblioteca La Paz','Barcelona','Barcelona','Avenida Diagonal 220',932112233,'lapaz@barcelonabibliotecas.es'),(3,'Biblioteca del Norte','Valencia','Valencia','Calle Norte 10',961234567,'norte@valenciabibliotecas.es'),(4,'Biblioteca Sur','Sevilla','Sevilla','Avenida de Andalucía 34',954123456,'sur@sevillabibliotecas.es'),(5,'Biblioteca Municipal','Zaragoza','Zaragoza','Calle Central 25',976987654,'municipal@zaragozabibliotecas.es'),(6,'Biblioteca de la Playa','Málaga','Málaga','Paseo Marítimo 101',952345678,'playa@malagabibliotecas.es'),(7,'Biblioteca El Parque','Alicante','Alicante','Calle Jardín 45',965123456,'parque@alicantebibliotecas.es'),(8,'Biblioteca Las Flores','Murcia','Murcia','Avenida de la Libertad 56',968234567,'flores@murciabibliotecas.es'),(9,'Biblioteca de la Montaña','Granada','Granada','Calle Sierra Nevada 18',958765432,'montana@granadabibliotecas.es'),(10,'Biblioteca del Mar','Valencia','Valencia','Calle del Puerto 22',962234567,'delmar@valenciabibliotecas.es'),(11,'Biblioteca San Miguel','MADRID','Madrid','Calle Alcalá 45',123456789,'sanmiguel@biblioteca.com'),(12,'Biblioteca Aurora','BARCELONA','Barcelona','Rambla Catalunya 98',987654321,'aurora@biblioteca.com'),(13,'Biblioteca Horizonte','VALENCIA','Valencia','Avenida del Puerto 22',654987321,'horizonte@biblioteca.com'),(14,'Biblioteca Fuente Clara','SEVILLA','Sevilla','Calle Sierpes 15',321456987,'fuenteclara@biblioteca.com'),(15,'Biblioteca Alhambra','GRANADA','Granada','Calle Reyes Católicos 80',112233445,'alhambra@biblioteca.com'),(16,'Biblioteca El Faro','MÁLAGA','Málaga','Paseo Marítimo 33',998877665,'elfaro@biblioteca.com'),(17,'Biblioteca Campo Verde','VALLADOLID','Valladolid','Calle López Gómez 12',556677889,'campoverde@biblioteca.com'),(18,'Biblioteca del Águila','BURGOS','Burgos','Avenida Camino Santiago 9',445566778,'aguila@biblioteca.com'),(19,'Biblioteca Arco Iris','CÓRDOBA','Córdoba','Plaza Tendillas 20',334455667,'arcoiris@biblioteca.com'),(20,'Biblioteca Nuevos Horizontes','ZARAGOZA','Zaragoza','Calle Alfonso I 8',776655443,'nuevoshorizontes@biblioteca.com'),(21,'Biblioteca Altamira','CÁDIZ','Cádiz','Paseo Canalejas 15',665544332,'altamira@biblioteca.com'),(22,'Biblioteca Mirador','ALICANTE','Alicante','Avenida de la Constitución 5',554433221,'mirador@biblioteca.com'),(23,'Biblioteca Las Letras','SALAMANCA','Salamanca','Calle Libreros 10',443322110,'lasletras@biblioteca.com'),(24,'Biblioteca del Tiempo','TOLEDO','Toledo','Calle Comercio 14',332211009,'deltiempo@biblioteca.com'),(25,'Biblioteca Renacimiento','CANTABRIA','Santander','Calle Burgos 30',221100887,'renacimiento@biblioteca.com'),(26,'Biblioteca La Luna','LA RIOJA','Logroño','Plaza Mercado 1',110998776,'laluna@biblioteca.com'),(27,'Biblioteca del Arte','NAVARRA','Pamplona','Calle Mayor 22',998877665,'delarte@biblioteca.com'),(28,'Biblioteca Estrella','ARAGÓN','Huesca','Avenida Pirineos 9',887766554,'estrella@biblioteca.com'),(29,'Biblioteca Cielo Azul','ASTURIAS','Oviedo','Calle Uría 17',776655443,'cieloazul@biblioteca.com'),(30,'Biblioteca Primavera','BALEARES','Palma','Paseo del Borne 6',665544332,'primavera@biblioteca.com');
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

-- Dump completed on 2024-11-20 16:37:15
