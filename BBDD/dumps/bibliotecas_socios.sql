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
-- Table structure for table `socios`
--

DROP TABLE IF EXISTS `socios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `socios` (
  `id_soc` int NOT NULL AUTO_INCREMENT,
  `biblioteca_soc` int NOT NULL,
  `dni_soc` varchar(45) NOT NULL,
  `nombre_soc` varchar(45) NOT NULL,
  `apellidos_soc` varchar(45) NOT NULL,
  `tlf_soc` varchar(45) NOT NULL,
  `email_soc` varchar(45) NOT NULL,
  `provincia_soc` varchar(45) NOT NULL,
  `ciudad_soc` varchar(45) NOT NULL DEFAULT 'No asignada',
  `calle_soc` varchar(45) NOT NULL DEFAULT 'No asignada',
  `numSanciones_soc` varchar(45) NOT NULL DEFAULT '0',
  `cuentaBancaria_soc` varchar(45) NOT NULL,
  `pago_soc` tinyint NOT NULL DEFAULT '0',
  `actual_soc` tinyint NOT NULL DEFAULT '1' COMMENT 'Si es un socio actual por defecto se pondrá en 1, que es true',
  PRIMARY KEY (`id_soc`),
  KEY `BilbiotecaAsociada_FK_idx` (`biblioteca_soc`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socios`
--

LOCK TABLES `socios` WRITE;
/*!40000 ALTER TABLE `socios` DISABLE KEYS */;
INSERT INTO `socios` VALUES (1,1,'12345678A','Luis','Martínez López','612345678','luis.martinez@gmail.com','Madrid','Madrid','Calle Alcalá 12','0','ES12345678901234567890',1,1),(2,2,'87654321B','María','García Sánchez','687654321','maria.garcia@gmail.com','Barcelona','Barcelona','Avenida Cataluña 45','1','ES09876543210987654321',0,1),(3,3,'23456789C','Carlos','Pérez Gómez','678912345','carlos.perez@hotmail.com','Valencia','Valencia','Calle Colón 23','0','ES23456789012345678901',1,0),(4,4,'34567890D','Ana','López Fernández','689123456','ana.lopez@outlook.com','Sevilla','Sevilla','Avenida de la Paz 67','2','ES34567890123456789012',0,1),(5,5,'45678901E','Sofía','Ramírez Torres','671234567','sofia.ramirez@yahoo.es','Zaragoza','Zaragoza','Calle Mayor 34','0','ES45678901234567890123',1,1),(6,6,'56789012F','Javier','Ortega Díaz','654321098','javier.ortega@gmail.com','Málaga','Málaga','Paseo de la Alameda 90','1','ES56789012345678901234',0,1),(7,7,'67890123G','Teresa','Molina Ruiz','673210987','teresa.molina@hotmail.com','Alicante','Alicante','Calle Primavera 78','0','ES67890123456789012345',1,1),(8,8,'78901234H','Raúl','Giménez Rojas','632109876','raul.gimenez@gmail.com','Murcia','Murcia','Calle del Sol 56','3','ES78901234567890123456',0,1),(9,9,'89012345I','Elena','Núñez Hernández','631098765','elena.nunez@yahoo.com','Granada','Granada','Calle Sierra Nevada 14','0','ES89012345678901234567',1,1),(10,10,'90123456J','David','Santos Castillo','690123456','david.santos@gmail.com','Valencia','Valencia','Calle del Mar 42','2','ES90123456789012345678',0,1),(11,1,'12345678A','Juan','Pérez','600123456','juan.perez@email.com','Madrid','Madrid','Calle Mayor 10','2','ES12345678901234567890',1,1),(12,1,'23456789B','Ana','García','610234567','ana.garcia@email.com','Barcelona','Barcelona','Calle Gran Vía 20','0','ES23456789012345678901',1,1),(13,2,'34567890C','Luis','Martínez','620345678','luis.martinez@email.com','Sevilla','Sevilla','Calle Feria 15','1','ES34567890123456789012',0,1),(14,2,'45678901D','María','López','630456789','maria.lopez@email.com','Valencia','Valencia','Calle Colón 5','0','ES45678901234567890123',1,1),(15,3,'56789012E','Carlos','Sánchez','640567890','carlos.sanchez@email.com','Vizcaya','Bilbao','Calle Etxebarri 8','3','ES56789012345678901234',0,1),(16,3,'67890123F','Laura','Ruiz','650678901','laura.ruiz@email.com','Granada','Granada','Calle Albaicín 12','0','ES67890123456789012345',1,1),(17,4,'78901234G','Pedro','Hernández','660789012','pedro.hernandez@email.com','Málaga','Málaga','Calle Larios 22','2','ES78901234567890123456',0,1),(18,4,'89012345H','Sara','Jiménez','670890123','sara.jimenez@email.com','Zaragoza','Zaragoza','Calle Alfonso I 6','1','ES89012345678901234567',1,1),(19,5,'90123456I','Miguel','Díaz','680901234','miguel.diaz@email.com','Valladolid','Valladolid','Calle Platerías 9','0','ES90123456789012345678',1,1),(20,5,'01234567J','Paula','Molina','690012345','paula.molina@email.com','Murcia','Murcia','Calle Trapería 18','4','ES01234567890123456789',0,1),(21,6,'11234568K','Jorge','Romero','600112345','jorge.romero@email.com','Alicante','Alicante','Calle San Francisco 3','0','ES11234567890123456789',1,1),(22,6,'21234569L','Carmen','Navarro','610212345','carmen.navarro@email.com','Navarra','Pamplona','Calle Estafeta 14','2','ES21234567890123456789',0,1),(23,7,'31234560M','Fernando','Vega','620312345','fernando.vega@email.com','Salamanca','Salamanca','Calle Zamora 7','1','ES31234567890123456789',1,1),(24,7,'41234561N','Lucía','Iglesias','630412345','lucia.iglesias@email.com','Cantabria','Santander','Calle Castelar 4','0','ES41234567890123456789',1,1),(25,8,'51234562O','Roberto','Gil','640512345','roberto.gil@email.com','Córdoba','Córdoba','Calle Cruz Conde 8','5','ES51234567890123456789',0,1),(26,8,'61234563P','Marta','Ortega','650612345','marta.ortega@email.com','Badajoz','Badajoz','Calle Menacho 11','0','ES61234567890123456789',1,1),(27,9,'71234564Q','Pablo','Lara','660712345','pablo.lara@email.com','Almería','Almería','Calle Real 17','3','ES71234567890123456789',0,1),(28,9,'81234565R','Elena','Cano','670812345','elena.cano@email.com','La_Rioja','Logroño','Calle Laurel 10','1','ES81234567890123456789',1,1),(29,10,'91234566S','Adrián','Blanco','680912345','adrian.blanco@email.com','Toledo','Toledo','Calle Comercio 6','0','ES91234567890123456789',1,1),(30,10,'01234567T','Beatriz','Flores','690012345','beatriz.flores@email.com','Asturias','Gijón','Calle Corrida 2','2','ES01234567890123456789',0,1),(31,11,'12234568U','Ismael','Castro','600122345','ismael.castro@email.com','León','León','Calle Ordoño II 19','1','ES12234567890123456789',1,1),(32,11,'22234569V','Natalia','Santana','610222345','natalia.santana@email.com','Tenerife','Santa Cruz','Calle Castillo 21','0','ES22234567890123456789',1,1),(33,12,'32234560W','Víctor','Marín','620322345','victor.marin@email.com','Mallorca','Palma','Calle Sindicato 9','0','ES32234567890123456789',1,1),(34,12,'42234561X','Teresa','Cruz','630422345','teresa.cruz@email.com','Burgos','Burgos','Calle Avellanos 7','3','ES42234567890123456789',0,1),(35,13,'52234562Y','Alberto','Gómez','640522345','alberto.gomez@email.com','Huelva','Huelva','Calle Rico 5','0','ES52234567890123456789',1,1),(36,13,'62234563Z','Lorena','Delgado','650622345','lorena.delgado@email.com','Zamora','Zamora','Calle Balborraz 11','1','ES62234567890123456789',1,1),(37,14,'72234564A','Manuel','Nieto','660722345','manuel.nieto@email.com','Albacete','Albacete','Calle Mayor 12','2','ES72234567890123456789',0,1),(38,14,'82234565B','Patricia','Lázaro','670822345','patricia.lazaro@email.com','Segovia','Segovia','Calle Real 6','0','ES82234567890123456789',1,0),(39,15,'92234566C','Raúl','Peña','680922345','raul.pena@email.com','Cádiz','Cádiz','Calle Ancha 4','0','ES92234567890123456789',1,0),(40,1,'12345601A','Andrea','Moreno','600123001','andrea.moreno@email.com','Madrid','Madrid','Calle Princesa 1','0','ES12345678901234567801',1,1),(41,1,'23456702B','Santiago','Hidalgo','610234002','santiago.hidalgo@email.com','Barcelona','Barcelona','Calle Aragón 15','1','ES23456789012345678012',0,1),(42,2,'34567803C','Clara','Fuentes','620345003','clara.fuentes@email.com','Sevilla','Sevilla','Calle San Luis 22','2','ES34567890123456780123',1,1),(43,2,'45678904D','Hugo','Palacios','630456004','hugo.palacios@email.com','Valencia','Valencia','Calle de la Paz 9','0','ES45678901234567801234',1,1),(44,3,'56789005E','Daniela','Ramos','640567005','daniela.ramos@email.com','Vizcaya','Bilbao','Calle Sabino Arana 3','1','ES56789012345678012345',0,1),(45,3,'67890106F','Alonso','Mendoza','650678006','alonso.mendoza@email.com','Granada','Granada','Calle Elvira 5','0','ES67890123456780123456',1,1),(46,4,'78901207G','Inés','Luna','660789007','ines.luna@email.com','Málaga','Málaga','Calle Victoria 11','3','ES78901234567801234567',0,1),(47,4,'89012308H','Víctor','Crespo','670890008','victor.crespo@email.com','Zaragoza','Zaragoza','Calle San Jorge 8','0','ES89012345678012345678',1,1),(48,5,'90123409I','Carla','Espinosa','680901009','carla.espinosa@email.com','Valladolid','Valladolid','Calle López Gómez 4','2','ES90123456780123456789',0,1),(49,5,'01234510J','Felipe','Esteban','690012010','felipe.esteban@email.com','Murcia','Murcia','Calle Apóstoles 14','1','ES01234567801234567890',1,1),(50,6,'11234511K','Rosa','Muñoz','600112011','rosa.munoz@email.com','Alicante','Alicante','Calle Castaños 19','0','ES11234567890123456701',1,1),(51,6,'21234512L','Sergio','Guerrero','610212012','sergio.guerrero@email.com','Navarra','Pamplona','Calle Chapitela 6','3','ES21234567801234567812',0,1),(52,7,'31234513M','Elisa','Campos','620312013','elisa.campos@email.com','Salamanca','Salamanca','Calle Toro 7','0','ES31234567801234567823',1,1),(53,7,'41234514N','Julio','Salas','630412014','julio.salas@email.com','Cantabria','Santander','Calle Burgos 15','2','ES41234567801234567834',1,1),(54,8,'51234515O','Marina','Vázquez','640512015','marina.vazquez@email.com','Córdoba','Córdoba','Calle Gondomar 6','1','ES51234567801234567845',0,1),(55,8,'61234516P','David','Paredes','650612016','david.paredes@email.com','Badajoz','Badajoz','Calle Zurbarán 3','0','ES61234567801234567856',1,1),(56,9,'71234517Q','Adriana','Carrasco','660712017','adriana.carrasco@email.com','Almería','Almería','Calle Alcazaba 10','4','ES71234567801234567867',0,1),(57,9,'81234518R','Iván','Campos','670812018','ivan.campos@email.com','La_Rioja','Logroño','Calle Portales 13','0','ES81234567801234567878',1,1),(58,10,'91234519S','Lucía','Serrano','680912019','lucia.serrano@email.com','Toledo','Toledo','Calle Alfileritos 8','2','ES91234567801234567889',1,1),(59,10,'01234520T','Pablo','Ordoñez','690012020','pablo.ordonez@email.com','Asturias','Gijón','Calle Asturias 5','0','ES01234567801234567901',0,1),(60,11,'12234521U','Carolina','Varela','600122021','carolina.varela@email.com','León','León','Calle Ancha 12','1','ES12234567801234567912',1,1),(61,11,'22234522V','Fernando','Aguilar','610222022','fernando.aguilar@email.com','Tenerife','Santa Cruz','Calle Méndez Núñez 16','0','ES22234567801234567923',1,1),(62,12,'32234523W','Elena','Navas','620322023','elena.navas@email.com','Mallorca','Palma','Calle Jaime III 9','3','ES32234567801234567934',0,1),(63,12,'42234524X','Óscar','Calderón','630422024','oscar.calderon@email.com','Burgos','Burgos','Calle Espolón 20','0','ES42234567801234567945',1,1),(64,13,'52234525Y','María','Montes','640522025','maria.montes@email.com','Huelva','Huelva','Calle Puerto 3','1','ES52234567801234567956',1,1),(65,13,'62234526Z','Álvaro','Prados','650622026','alvaro.prados@email.com','Zamora','Zamora','Calle Balborraz 6','0','ES62234567801234567967',1,1),(66,14,'72234527A','Lidia','Cabrera','660722027','lidia.cabrera@email.com','Albacete','Albacete','Calle Rosario 14','2','ES72234567801234567978',0,1),(67,14,'82234528B','Esteban','Corral','670822028','esteban.corral@email.com','Segovia','Segovia','Calle Real 10','0','ES82234567801234567989',1,1),(68,15,'92234529C','Rebeca','López','680922029','rebeca.lopez@email.com','Cádiz','Cádiz','Calle San Francisco 4','1','ES92234567801234568012',1,1),(69,1,'12345601A','Luis','García','600123001','luis.garcia@email.com','MADRID','Madrid','Calle Gran Vía 1','1','ES12345678901234567801',1,1),(70,2,'23456702B','Ana','Martínez','610234002','ana.martinez@email.com','ALICANTE','Alicante','Calle Alicante 4','0','ES23456789012345678012',0,1),(71,3,'34567803C','Carlos','López','620345003','carlos.lopez@email.com','BADAJOZ','Badajoz','Calle del Río 6','0','ES34567890123456780123',1,1),(72,4,'45678904D','Marta','Rodríguez','630456004','marta.rodriguez@email.com','SEVILLA','Sevilla','Calle Sierpes 3','2','ES45678901234567801234',1,1),(73,5,'56789005E','Javier','Sánchez','640567005','javier.sanchez@email.com','CÓRDOBA','Córdoba','Calle Templo 7','1','ES56789012345678012345',0,0),(74,6,'67890106F','Lucía','Fernández','650678006','lucia.fernandez@email.com','VALENCIA','Valencia','Calle Colón 5','0','ES67890123456780123456',1,1),(75,7,'78901207G','Pedro','González','660789007','pedro.gonzalez@email.com','MÁLAGA','Málaga','Calle Larios 2','3','ES78901234567801234567',1,1),(76,8,'89012308H','Beatriz','Pérez','670890008','beatriz.perez@email.com','ALBACETE','Albacete','Calle Ancha 9','0','ES89012345678012345678',0,1),(77,9,'90123409I','Manuel','Vázquez','680901009','manuel.vazquez@email.com','BURGOS','Burgos','Calle Vitoria 8','2','ES90123456780123456789',1,1),(78,10,'01234510J','Carolina','Serrano','690012010','carolina.serrano@email.com','LUGO','Lugo','Calle Miño 12','0','ES01234567801234567890',1,1),(79,11,'11234511K','José','Martín','600112011','jose.martin@email.com','TARRAGONA','Tarragona','Calle Rambla 15','1','ES11234567890123456701',0,1),(80,12,'21234512L','Laura','Jiménez','610212012','laura.jimenez@email.com','CASTELLÓN','Castellón','Calle Mayor 4','0','ES21234567801234567812',1,1),(81,13,'31234513M','Antonio','Molina','620312013','antonio.molina@email.com','SEVILLA','Sevilla','Calle Cuna 10','2','ES31234567801234567823',0,1),(82,14,'41234514N','Irene','González','630412014','irene.gonzalez@email.com','GUIPÚZKOA','San Sebastián','Calle Bergara 5','1','ES41234567801234567834',1,1),(83,15,'51234515O','Fernando','Núñez','640512015','fernando.nunez@email.com','MURCIA','Murcia','Calle Fuensanta 9','3','ES51234567801234567845',1,1),(84,16,'61234516P','Sofía','Ramos','650612016','sofia.ramos@email.com','CÁDIZ','Cádiz','Calle de la Palma 13','1','ES61234567801234567856',0,0),(85,17,'71234517Q','Miguel','Pardo','660712017','miguel.pardo@email.com','VIZCAYA','Bilbao','Calle de la Ribera 4','0','ES71234567801234567867',1,1),(86,18,'81234518R','Alba','López','670812018','alba.lopez@email.com','LEÓN','León','Calle Ancha 3','2','ES81234567801234567878',0,1),(87,19,'91234519S','Pedro','González','680912019','pedro.gonzalez@email.com','LLEIDA','Lleida','Calle del Nord 8','1','ES91234567801234567889',1,1),(88,20,'01234520T','Mónica','Fernández','690012020','monica.fernandez@email.com','GIPUZKOA','Donostia','Calle Easo 6','0','ES01234567801234567901',0,1),(89,21,'12234521U','Samuel','Martín','600122021','samuel.martin@email.com','ALMERÍA','Almería','Calle Obispo Orbera 10','3','ES12234567801234567912',1,1),(90,22,'22234522V','Carolina','Torres','610222022','carolina.torres@email.com','JAÉN','Jaén','Calle Bernabé Soriano 8','2','ES22234567801234567923',1,1),(91,23,'32234523W','Juan','Castaño','620322023','juan.castano@email.com','PALENCIA','Palencia','Calle Mayor 15','0','ES32234567801234567934',1,1),(92,24,'42234524X','Cristina','González','630422024','cristina.gonzalez@email.com','CÓRDOBA','Córdoba','Calle Cañuelo 6','1','ES42234567801234567945',0,1),(93,25,'52234525Y','José','Martínez','640522025','jose.martinez@email.com','MÁLAGA','Málaga','Calle La Malagueta 10','2','ES522345678012345679',1,1);
/*!40000 ALTER TABLE `socios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-21 12:01:45
