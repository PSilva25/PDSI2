-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: pdsi_ii
-- ------------------------------------------------------
-- Server version	5.7.14

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
-- Table structure for table `estoque_bebidas`
--

DROP TABLE IF EXISTS `estoque_bebidas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estoque_bebidas` (
  `ID_bebida` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(100) DEFAULT NULL,
  `Nome` varchar(100) DEFAULT NULL,
  `Fornecedor` varchar(100) DEFAULT NULL,
  `Volume` varchar(100) DEFAULT NULL,
  `Quantidade` int(11) DEFAULT NULL,
  `Preco` float DEFAULT NULL,
  PRIMARY KEY (`ID_bebida`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque_bebidas`
--

LOCK TABLES `estoque_bebidas` WRITE;
/*!40000 ALTER TABLE `estoque_bebidas` DISABLE KEYS */;
INSERT INTO `estoque_bebidas` VALUES (1,'Refrigerante','Coca-Cola','Coca-Cola Inc.','300 ML',95,2.5),(2,'Refrigerante','Coca-Cola','Coca-Cola Inc.','600 ML',97,3.5),(3,'Refrigerante','Coca-Cola','Coca-Cola Inc.','1 L',100,5),(4,'Refrigerante','Coca-Cola','Coca-Cola Inc.','2 L',100,8),(5,'Refrigerante','Guarana','Coca-Cola Inc.','300 ML',100,2.5),(6,'Refrigerante','Guarana','Coca-Cola Inc.','600 ML',100,3.5),(7,'Refrigerante','Guarana','Coca-Cola Inc.','1 L',100,5);
/*!40000 ALTER TABLE `estoque_bebidas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-19  9:02:10
