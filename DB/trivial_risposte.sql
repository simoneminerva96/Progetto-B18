CREATE DATABASE  IF NOT EXISTS `trivial` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `trivial`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: trivial
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `risposte`
--

DROP TABLE IF EXISTS `risposte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `risposte` (
  `COD_ANS` int(11) NOT NULL,
  `ID_DOMANDA` varchar(5) NOT NULL,
  `ID_RISPOSTA` int(11) NOT NULL,
  `RISPOSTA` varchar(300) NOT NULL,
  `VALUE` varchar(1) NOT NULL,
  PRIMARY KEY (`COD_ANS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `risposte`
--

LOCK TABLES `risposte` WRITE;
/*!40000 ALTER TABLE `risposte` DISABLE KEYS */;
INSERT INTO `risposte` VALUES (1,'GEO1',1,'Roma','Y'),(2,'GEO1',2,'Firenze','N'),(3,'GEO1',3,'Napoli','N'),(4,'GEO1',4,'Venezia','N'),(5,'STO1',1,'1200','N'),(6,'STO1',2,'1914','Y'),(7,'STO1',3,'2006','N'),(8,'STO1',4,'400','N'),(9,'ATT1',1,'Stefano','N'),(10,'ATT1',2,'Trmup','Y'),(11,'ATT1',3,'Pippo','N'),(12,'ATT1',4,'Pluto','N'),(13,'ART1',1,'Stefano','N'),(14,'ART1',2,'Dante Alighieri','Y'),(15,'ART1',3,'Tizio','N'),(16,'ART1',4,'Pluto','N'),(17,'SCI1',1,'Stefano','N'),(18,'SCI1',2,'Gravità','Y'),(19,'SCI1',3,'Tizio','N'),(20,'SCI1',4,'Pluto','N'),(21,'SPO1',1,'Stefano','N'),(22,'SPO1',2,'Germania','Y'),(23,'SPO1',3,'Tizio','N'),(24,'SPO1',4,'Pluto','N'),(25,'SPE1',1,'Stefano','N'),(26,'SPE1',2,'La forma dell\'Acqua','Y'),(27,'SPE1',3,'Tizio','N'),(28,'SPE1',4,'Pluto','N'),(29,'GEO2',1,'Tokio','N'),(30,'GEO2',2,'Shangai','Y'),(31,'GEO2',3,'New York','N'),(32,'GEO2',4,'Nuova Delhi','N'),(33,'GEO3',1,'Mare di Bering','N'),(34,'GEO3',2,'Mar Baltico','Y'),(35,'GEO3',3,'Mar Nero','N'),(36,'GEO3',4,'Mar Mediterraneo','N'),(37,'GEO4',1,'Argentina','N'),(38,'GEO4',2,'Russia','N'),(39,'GEO4',3,'Cina','N'),(40,'GEO4',4,'Usa','Y'),(41,'GEO5',1,'Pietre vulcaniche','N'),(42,'GEO5',2,'Fessure nel suolo da cui fuoriesce gas e vapori','Y'),(43,'GEO5',3,'Un tipo di geyser','N'),(44,'GEO5',4,'Crateri vulcanici situati sul fondo dell\'oceano','N'),(45,'GEO6',1,'1','N'),(46,'GEO6',2,'2','N'),(47,'GEO6',3,'5','N'),(48,'GEO6',4,'10','Y'),(49,'GEO7',1,'Siria','N'),(50,'GEO7',2,'Egitto','N'),(51,'GEO7',3,'Arabia Saudita','Y'),(52,'GEO7',4,'Giordania','N'),(53,'GEO8',1,'Nilo','N'),(54,'GEO8',2,'Fiume Azzurro','N'),(55,'GEO8',3,'Rio delle Amazzoni','Y'),(56,'GEO8',4,'Volga','N'),(57,'GEO9',1,'Francia','N'),(58,'GEO9',2,'Slovenia','N'),(59,'GEO9',3,'Croazia','Y'),(60,'GEO9',4,'Austria','N'),(61,'GEO10',1,'Un particolare fenomeno sismico','N'),(62,'GEO10',2,'Un vulcano inattivo','N'),(63,'GEO10',3,'Un lago ','N'),(64,'GEO10',4,'Un vento caldo','Y'),(65,'GEO11',1,'Circa 5 km','N'),(66,'GEO11',2,'Circa 10 km','Y'),(67,'GEO11',3,'Circa 20 km','N'),(68,'GEO11',4,'Circa 30 km','N'),(69,'GEO12',1,'Sato del Vaticano','Y'),(70,'GEO12',2,'Andorra','N'),(71,'GEO12',3,'Lietchenstein','N'),(72,'GEO12',4,'Principato di Monaco','N'),(73,'GEO13',1,'Lombardia','N'),(74,'GEO13',2,'Piemonte','N'),(75,'GEO13',3,'Sicilia','Y'),(76,'GEO13',4,'Puglia','N'),(77,'GEO14',1,'13','N'),(78,'GEO14',2,'20','N'),(79,'GEO14',3,'18','Y'),(80,'GEO14',4,'15','N'),(81,'GEO15',1,'5','Y'),(82,'GEO15',2,'7','N'),(83,'GEO15',3,'10','N'),(84,'GEO15',4,'20','N'),(85,'GEO16',1,'Francia','Y'),(86,'GEO16',2,'Germania','N'),(87,'GEO16',3,'Italia','N'),(88,'GEO16',4,'Spagna','N'),(89,'GEO17',1,'Circa 200 metri','N'),(90,'GEO17',2,'Circa 350 metri','N'),(91,'GEO17',3,'Circa 500 metri','N'),(92,'GEO17',4,'Circa 1000 metri','Y'),(93,'GEO18',1,'Himalaya','N'),(94,'GEO18',2,'Urali','N'),(95,'GEO18',3,'Cordigliera delle Ande','Y'),(96,'GEO18',4,'Montagne Rocciose','N'),(97,'GEO19',1,'Reykjavik','N'),(98,'GEO19',2,'Mosca','N'),(99,'GEO19',3,'Astana','Y'),(100,'GEO19',4,'Ottawa','N'),(101,'GEO20',1,'Circa 1000 km','N'),(102,'GEO20',2,'Circa 5000 km','Y'),(103,'GEO20',3,'Circa 10000 km','N'),(104,'GEO20',4,'Circa 30000 km','N'),(105,'GEO21',1,'Mar Glaciale Artico','N'),(106,'GEO21',2,'Oceano Atlantico','N'),(107,'GEO21',3,'Oceano Indiano','N'),(108,'GEO21',4,'Oceano Pacifico','Y'),(109,'GEO22',1,'-66 gradi celsius','N'),(110,'GEO22',2,'-72 gradi celsius','N'),(111,'GEO22',3,'-89 gradi celsius','Y'),(112,'GEO22',4,'-94 gradi celsius','N'),(113,'GEO23',1,'Equatore, Tropicale, Temperata, Artica, Polare','N'),(114,'GEO23',2,'Equatore, Tropicale, Polare','N'),(115,'GEO23',3,'Tropicale, Polare','N'),(116,'GEO23',4,'Tropicale, Temperata, Polare','Y'),(117,'GEO24',1,'Circa 40000 km','Y'),(118,'GEO24',2,'Circa 10000 km','N'),(119,'GEO24',3,'Circa 80000 km','N'),(120,'GEO24',4,'Circa 20000 km','N'),(121,'GEO25',1,'Rub\' al Khali','N'),(122,'GEO25',2,'Sahara','Y'),(123,'GEO25',3,'Kalahari','N'),(124,'GEO25',4,'Deserto del Gobi','N'),(125,'GEO26',1,'Circa 500 milioni di anni fa','N'),(126,'GEO26',2,'Circa 2 miliardi di anni fa','N'),(127,'GEO26',3,'circa 10 miliardi di anni fa','N'),(128,'GEO26',4,'Circa 5 miliardi di anni fa','Y'),(129,'GEO27',1,'4310 m','N'),(130,'GEO27',2,'4810 m','Y'),(131,'GEO27',3,'5710 m','N'),(132,'GEO27',4,'5310 m','N'),(133,'GEO28',1,'Alaska','Y'),(134,'GEO28',2,'Texas','N'),(135,'GEO28',3,'California','N'),(136,'GEO28',4,'Florida','N'),(137,'GEO29',1,'Circa 50','N'),(138,'GEO29',2,'Circa 500','N'),(139,'GEO29',3,'Circa 5000','N'),(140,'GEO29',4,'Circa 50000 ','Y'),(141,'GEO30',1,'2','N'),(142,'GEO30',2,'4','N'),(143,'GEO30',3,'6','Y'),(144,'GEO30',4,'8','N'),(145,'GEO31',1,'Lago di Garda','Y'),(146,'GEO31',2,'Lago Maggiore','N'),(147,'GEO31',3,'Lago Trasimeno','N'),(148,'GEO31',4,'Lago di Como','N'),(149,'GEO32',1,'Siria','N'),(150,'GEO32',2,'Bulgaria','Y'),(151,'GEO32',3,'Albania','N'),(152,'GEO32',4,'Algeria','N'),(153,'GEO33',1,'Mar Rosso e Mar Glaciale Artico','N'),(154,'GEO33',2,'Mar Mediterrano e Mar Rosso','Y'),(155,'GEO33',3,'Oceano Atlantico e Oceano Pacifico','N'),(156,'GEO33',4,'Mar Baltico e Mar Mediterraneo','N'),(157,'GEO34',1,'Messico','N'),(158,'GEO34',2,'California','N'),(159,'GEO34',3,'Bolivia','N'),(160,'GEO34',4,'Arizona','Y'),(161,'GEO35',1,'Dal nome di una antica divinità nepalese','N'),(162,'GEO35',2,'Dal nome di un geografo e cartografo britannico','N'),(163,'GEO35',3,'Da una parola che in lingua locale significa \"forte\"','Y'),(164,'GEO35',4,'La sua origine è tutt\'ora sconosciuta','N'),(165,'GEO36',1,'Isole Eolie','Y'),(166,'GEO36',2,'Arcipelago campano','N'),(167,'GEO36',3,'Arcipelago toscano','N'),(168,'GEO36',4,'Isole Egadi','N'),(169,'GEO37',1,'Stato del Vaticano','N'),(170,'GEO37',2,'Olanda','N'),(171,'GEO37',3,'Repubblica Ceca','N'),(172,'GEO37',4,'Svizzera','Y'),(173,'GEO38',1,'2','N'),(174,'GEO38',2,'3','N'),(175,'GEO38',3,'4','N'),(176,'GEO38',4,'5','Y'),(177,'GEO39',1,'Italia','N'),(178,'GEO39',2,'Regno Unito','N'),(179,'GEO39',3,'Germania','Y'),(180,'GEO39',4,'Francia','N'),(181,'GEO40',1,'Estonia','Y'),(182,'GEO40',2,'Lettonia','N'),(183,'GEO40',3,'Lituania','N'),(184,'GEO40',4,'Bielorussia','N'),(185,'GEO41',1,'1%','N'),(186,'GEO41',2,'3%','Y'),(187,'GEO41',3,'5%','N'),(188,'GEO41',4,'12%','N'),(189,'GEO42',1,'Balcani','N'),(190,'GEO42',2,'Ande','N'),(191,'GEO42',3,'Himalaya','Y'),(192,'GEO42',4,'Alpi','N'),(193,'GEO43',1,'Usa','N'),(194,'GEO43',2,'Cina','Y'),(195,'GEO43',3,'Giappone','N'),(196,'GEO43',4,'Germania','N'),(197,'GEO44',1,'Canada','N'),(198,'GEO44',2,'Sudafrica','N'),(199,'GEO44',3,'Australia','N'),(200,'GEO44',4,'Somalia','Y'),(201,'GEO45',1,'Circa 4 mila km','N'),(202,'GEO45',2,'Circa 40 mila km','N'),(203,'GEO45',3,'Circa 400 mila km','Y'),(204,'GEO45',4,'Circa 4 milioni di km','N'),(205,'GEO46',1,'Bogotà','Y'),(206,'GEO46',2,'Medellin','N'),(207,'GEO46',3,'Caracas','N'),(208,'GEO46',4,'Lima','N'),(209,'GEO47',1,'90','N'),(210,'GEO47',2,'180','N'),(211,'GEO47',3,'360','Y'),(212,'GEO47',4,'720','N'),(213,'GEO48',1,'90','N'),(214,'GEO48',2,'180','Y'),(215,'GEO48',3,'360','N'),(216,'GEO48',4,'720','N'),(217,'GEO49',1,'5','Y'),(218,'GEO49',2,'6','N'),(219,'GEO49',3,'7','N'),(220,'GEO49',4,'8','N'),(221,'GEO50',1,'Ungheria','N'),(222,'GEO50',2,'Costa d\'Avorio','N'),(223,'GEO50',3,'Messico','Y'),(224,'GEO50',4,'Bulgaria','N'),(225,'FGEO1',1,'Colombia','N'),(226,'FGEO1',2,'Bosnia es Erzegovina','N'),(227,'FGEO1',3,'Sudafrica','N'),(228,'FGEO1',4,'Danimarca','Y'),(229,'FGEO2',1,'Giappone','N'),(230,'FGEO2',2,'Iran','N'),(231,'FGEO2',3,'Usa','N'),(232,'FGEO2',4,'Cile','Y'),(233,'FGEO3',1,'Russia','N'),(234,'FGEO3',2,'Cina','Y'),(235,'FGEO3',3,'Brasile','N'),(236,'FGEO3',4,'Congo','N'),(237,'FGEO4',1,'Australia','N'),(238,'FGEO4',2,'Russia','N'),(239,'FGEO4',3,'Canada','Y'),(240,'FGEO4',4,'Indonesia','N'),(241,'FGEO5',1,'Atene','N'),(242,'FGEO5',2,'Sparta','Y'),(243,'FGEO5',3,'Stalingrado','N'),(244,'FGEO5',4,'Costantinopoli','N'),(245,'FSTO1',1,'La realizzazione di armi nucleari ','N'),(246,'FSTO1',2,'Contrastare un\'invasione dell\'Europa Occidentale','Y'),(247,'FSTO1',3,'La vendita di armi in Libia','N'),(248,'FSTO1',4,'La creazione di un virus informatico','N'),(249,'FSCI1',1,'Modello a pandoro','N'),(250,'FSCI1',2,'Modello a colomba','N'),(251,'FSCI1',3,'Modello a panettone','Y'),(252,'FSCI1',4,'Modello a ciambella','N'),(253,'FSPO1',1,'Tiro al piccione','N'),(254,'FSPO1',2,'Badminton','N'),(255,'FSPO1',3,'Tiro alla fune','N'),(256,'FSPO1',4,'Calcio a 5','Y'),(257,'FART1',1,'Salvador Dalì','N'),(258,'FART1',2,'Cristian Tzara','N'),(259,'FART1',3,'Marcel Duchamp','Y'),(260,'FART1',4,'Pablo Picasso','N'),(261,'FSPE1',1,'1912','N'),(262,'FSPE1',2,'1895','Y'),(263,'FSPE1',3,'1888','N'),(264,'FSPE1',4,'1901','N');
/*!40000 ALTER TABLE `risposte` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-24 11:55:38
