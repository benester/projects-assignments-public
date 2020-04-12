-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: etapp2
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `besiktningsfirma`
--

DROP TABLE IF EXISTS `besiktningsfirma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `besiktningsfirma` (
  `namn` varchar(40) NOT NULL,
  PRIMARY KEY (`namn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `besiktningsfirma`
--

LOCK TABLES `besiktningsfirma` WRITE;
/*!40000 ALTER TABLE `besiktningsfirma` DISABLE KEYS */;
INSERT INTO `besiktningsfirma` VALUES ('HissRisk'),('InspectorGadget'),('SäkerhetAB');
/*!40000 ALTER TABLE `besiktningsfirma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `besiktningsinfo`
--

DROP TABLE IF EXISTS `besiktningsinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `besiktningsinfo` (
  `hissID` int(11) NOT NULL,
  `datum` date NOT NULL,
  `firmaNamn` varchar(40) NOT NULL,
  PRIMARY KEY (`hissID`,`datum`),
  KEY `firmaNamn` (`firmaNamn`),
  CONSTRAINT `besiktningsinfo_ibfk_1` FOREIGN KEY (`firmaNamn`) REFERENCES `besiktningsfirma` (`namn`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `besiktningsinfo_ibfk_2` FOREIGN KEY (`hissID`) REFERENCES `hiss` (`hissID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `besiktningsinfo`
--

LOCK TABLES `besiktningsinfo` WRITE;
/*!40000 ALTER TABLE `besiktningsinfo` DISABLE KEYS */;
INSERT INTO `besiktningsinfo` VALUES (1,'2019-03-15','HissRisk'),(2,'2019-02-03','HissRisk'),(1,'2019-01-01','InspectorGadget'),(3,'2019-04-29','SäkerhetAB');
/*!40000 ALTER TABLE `besiktningsinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bostadsgrupp`
--

DROP TABLE IF EXISTS `bostadsgrupp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bostadsgrupp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `namn` varchar(40) NOT NULL,
  `orgNr` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AN1` (`namn`,`orgNr`),
  KEY `orgNr` (`orgNr`) /*!80000 INVISIBLE */,
  CONSTRAINT `bostadsgrupp_ibfk_1` FOREIGN KEY (`orgNr`) REFERENCES `förening` (`orgNr`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bostadsgrupp`
--

LOCK TABLES `bostadsgrupp` WRITE;
/*!40000 ALTER TABLE `bostadsgrupp` DISABLE KEYS */;
INSERT INTO `bostadsgrupp` VALUES (1,'Grupp1',1),(3,'Grupp1',2),(5,'Grupp1',3),(2,'Grupp2',1),(4,'Grupp2',2),(6,'Grupp2',3),(7,'Grupp3',3);
/*!40000 ALTER TABLE `bostadsgrupp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bostadsrätt`
--

DROP TABLE IF EXISTS `bostadsrätt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bostadsrätt` (
  `lghID` int(11) NOT NULL AUTO_INCREMENT,
  `bostadsgruppsID` int(11) NOT NULL,
  `husID` int(11) NOT NULL,
  `lghNr` int(11) unsigned NOT NULL,
  `lghTyp` varchar(40) NOT NULL,
  `yta` int(11) unsigned NOT NULL,
  `våning` int(11) unsigned NOT NULL,
  PRIMARY KEY (`lghID`),
  UNIQUE KEY `AN1` (`husID`,`lghNr`),
  KEY `bostadsgruppsID` (`bostadsgruppsID`) /*!80000 INVISIBLE */,
  CONSTRAINT `bostadsrätt_ibfk_1` FOREIGN KEY (`husID`) REFERENCES `fastighet` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `bostadsrätt_ibfk_2` FOREIGN KEY (`bostadsgruppsID`) REFERENCES `bostadsgrupp` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bostadsrätt`
--

LOCK TABLES `bostadsrätt` WRITE;
/*!40000 ALTER TABLE `bostadsrätt` DISABLE KEYS */;
INSERT INTO `bostadsrätt` VALUES (1,1,4,15,'1a',25,2),(2,1,1,17,'2a',50,1),(3,2,1,32,'3a',75,2),(4,3,1,22,'2a',60,3),(5,4,2,11,'2a',50,4),(6,5,2,7,'5a',125,3),(7,6,4,3,'4a',110,2),(8,7,2,14,'3a',80,1);
/*!40000 ALTER TABLE `bostadsrätt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fastighet`
--

DROP TABLE IF EXISTS `fastighet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fastighet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `föreningsOrgnr` int(11) NOT NULL,
  `adress` varchar(40) NOT NULL,
  `postnr` int(11) unsigned NOT NULL,
  `postort` varchar(40) NOT NULL,
  `byggår` int(11) unsigned NOT NULL,
  `antalvåningar` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AN1` (`adress`,`postnr`),
  KEY `föreningsOrgnr` (`föreningsOrgnr`),
  CONSTRAINT `fastighet_ibfk_1` FOREIGN KEY (`föreningsOrgnr`) REFERENCES `förening` (`orgNr`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fastighet`
--

LOCK TABLES `fastighet` WRITE;
/*!40000 ALTER TABLE `fastighet` DISABLE KEYS */;
INSERT INTO `fastighet` VALUES (1,1,'Hästvägen',1337,'Kista',2015,2),(2,2,'Prästvägen',1336,'Kista',2014,15),(4,3,'Skovägen',1335,'Järfälla',2000,1);
/*!40000 ALTER TABLE `fastighet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `förening`
--

DROP TABLE IF EXISTS `förening`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `förening` (
  `orgNr` int(11) NOT NULL,
  `namn` varchar(40) NOT NULL,
  `tillåterFleraParkeringar` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`orgNr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `förening`
--

LOCK TABLES `förening` WRITE;
/*!40000 ALTER TABLE `förening` DISABLE KEYS */;
INSERT INTO `förening` VALUES (1,'Solgläntan',1),(2,'Kvarnbacken',0),(3,'Gladahusen',1);
/*!40000 ALTER TABLE `förening` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hiss`
--

DROP TABLE IF EXISTS `hiss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hiss` (
  `hissID` int(11) NOT NULL AUTO_INCREMENT,
  `husID` int(11) NOT NULL,
  `namn` varchar(40) DEFAULT NULL,
  `maxAntalPers` int(11) unsigned NOT NULL,
  `maxVikt` int(11) unsigned NOT NULL,
  `besiktningsIntervall` int(11) unsigned NOT NULL,
  PRIMARY KEY (`hissID`),
  UNIQUE KEY `AN1` (`namn`,`husID`),
  KEY `hiss_ibfk_1` (`husID`),
  CONSTRAINT `hiss_ibfk_1` FOREIGN KEY (`husID`) REFERENCES `fastighet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hiss`
--

LOCK TABLES `hiss` WRITE;
/*!40000 ALTER TABLE `hiss` DISABLE KEYS */;
INSERT INTO `hiss` VALUES (1,1,'Blå',8,1000,150),(2,1,'Röd',6,800,200),(3,4,'Blå',8,1000,150),(4,2,'Grön',7,800,100);
/*!40000 ALTER TABLE `hiss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `årsavgift`
--

DROP TABLE IF EXISTS `årsavgift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `årsavgift` (
  `bostadsGruppID` int(11) NOT NULL AUTO_INCREMENT,
  `år` int(11) unsigned NOT NULL,
  `avgift` int(11) unsigned NOT NULL,
  PRIMARY KEY (`bostadsGruppID`,`år`),
  CONSTRAINT `årsavgift_ibfk_1` FOREIGN KEY (`bostadsGruppID`) REFERENCES `bostadsgrupp` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `årsavgift`
--

LOCK TABLES `årsavgift` WRITE;
/*!40000 ALTER TABLE `årsavgift` DISABLE KEYS */;
INSERT INTO `årsavgift` VALUES (1,2017,4000),(1,2018,4500),(2,2017,5000),(2,2018,5500),(3,2017,5000),(3,2018,5600),(4,2017,6000),(4,2018,6700),(5,2017,7000),(5,2018,7000),(6,2017,8000),(6,2018,7500),(7,2017,9000),(7,2018,10500);
/*!40000 ALTER TABLE `årsavgift` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-13 11:35:44
