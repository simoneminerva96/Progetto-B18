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
-- Table structure for table `domande`
--

DROP TABLE IF EXISTS `domande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domande` (
  `ID_QUEST` varchar(5) NOT NULL,
  `DESCRIZIONE` varchar(300) NOT NULL,
  PRIMARY KEY (`ID_QUEST`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domande`
--

LOCK TABLES `domande` WRITE;
/*!40000 ALTER TABLE `domande` DISABLE KEYS */;
INSERT INTO `domande` VALUES ('ART1','Autore Divina Commedia'),('ATT1','Presidente degli Stati Uniti'),('FART1','L\'autore di \"Fontata\" del 1917 é...'),('FGEO1','La bandiera di quale, di questi stati non contiene il colore blu?'),('FGEO2','Nel 1960 è avvenuto il terremono più forte mai registrato con ben 9.5 gradi della scala richter. Dove è avvenuto?'),('FGEO3','Quale stato detiene il record per il maggior numero di paesi confinanti con esso?'),('FGEO4','Quale stato ha la costa più estesa al mondo?'),('FGEO5','Quae di queste città oggi non esiste più?'),('FSCI1','Il modello atomico di Thomson, viene chiamato anche con un altro nome, quale?'),('FSPE1','Quando è stato girato il primo film muto?'),('FSPO1','Quale di questi sport non è stato, almeno una volta, una disciplina olimpica?'),('FSTO1','Cosa prevedeva il progetto Gladio in Italia?'),('GEO1','Capitale Italia'),('GEO10','Cos\'è lo Scirocco?'),('GEO11','Quanto è profonda la fossa delle Marianne?'),('GEO12','Lo stato più piccolo d\'Europa é'),('GEO13','La regione più estesa d\'Italia é'),('GEO14','Quante sono le regioni della Francia?'),('GEO15','Da quanti strati è composta l\'atmosfera?'),('GEO16','Quale tra questi stati è il più esteso?'),('GEO17','Quanto è alta Salto Angel?'),('GEO18','Qual\'è la catena montuosa più lunga al mondo?'),('GEO19','Con una temperatura media invernale di -52 gradi, qual\'è la capitale più fredda al mondo?'),('GEO2','Qual\'è la città più popolosa al mondo?'),('GEO20','A che profondità dalla superficie si trova il nucleo interno terrestre?'),('GEO21','Dove si trova l\'isola di Pasqua?'),('GEO22','Qual\'è la temperatura più bassa mai registrata sul pianeta?'),('GEO23','In quale fasce climatiche è solitamente suddivisa la terra?'),('GEO24','Quanto è lungo l\'equatore?'),('GEO25','Quale di questi è il deserto più caldo al mondo?'),('GEO26','Quanti anni fa si formò la Terra?'),('GEO27','Il Monte Bianco è la montagna più alta d\'Italia, ma quanto è alta?'),('GEO28','Qual\'è lo stato più esteso degli USA?'),('GEO29','Quanti abitanti vi sono in Groelandia?'),('GEO3','Da quale mare è bagnata la Finlandia?'),('GEO30','Negli Stati Uniti d\'America quanti fusi orari sono presenti?'),('GEO31','Qual\'è il lago più grande d\'Italia?'),('GEO32','Quale di questi paesi non si affaccia sul bacino Mediterraneo?'),('GEO33','Il canale di Suez cosa separa?'),('GEO34','Posizione del grand Canyon'),('GEO35','Da cosa deriva il nome del monte Everest?'),('GEO36','A quale arcipelago italiano appartiene l\'Isola di Vulcano?'),('GEO37','La bandiera di quale stato europeo contiene una croce bianca su sfondo rosso?'),('GEO38','Quante sono le regioni d\'Italia a statuto speciale?'),('GEO39','Quale di questi stati ha il maggior numero di abitanti?'),('GEO4','Dove si trova la \"Tornado Alley\", la zona del mondo più colpita dai tornadi?'),('GEO40','Tallin è la capitale di quale stato?'),('GEO41','Sul totale di acqua presente sulla Terra, quale percentuale di questa è acqua dolce?'),('GEO42','L\'Everest è la montagna più alta del mondo, e si trova nell\'Himalaya. Dove è situata però la seconda montagna più alta del mondo?'),('GEO43','Quale paese ha il PIL Nominale più alto nel mondo?'),('GEO44','Qale di questi stati non aderisce al Commonwealth?'),('GEO45','Qual\'è la distanza media tra la Terra e la Luna?'),('GEO46','Qual\'è la capitale della Colombia?'),('GEO47','Quanti sono i meridiani?'),('GEO48','Quanti sono i paralleli?'),('GEO49','Quante sono le provincie della Sardegna dopo la riorganizzazione del 2016?'),('GEO5','Cosa sono le fumarole?'),('GEO50','Quale stato ha la stessa bandiera dell\'Italia, fatta eccezione per uno stemma al centro di essa?'),('GEO6','Quanti vulcani attivi sono presenti in Italia?'),('GEO7','Dove si trova la Mecca?'),('GEO8','Quale\'è il fiume più lungo del mondo?'),('GEO9','Quale di questi paesi non confina con l\'Italia?'),('SCI1','Scopreta di Newton'),('SPE1','Ultimo Film vincitore dell\'Oscar'),('SPO1','Vittoria Ultimi Mondiali di Calcio'),('STO1','Prima Guerra Mondiale');
/*!40000 ALTER TABLE `domande` ENABLE KEYS */;
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
