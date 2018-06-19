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
-- Table structure for table `registro`
--

DROP TABLE IF EXISTS `registro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registro` (
  `ID_Registro` int(11) NOT NULL AUTO_INCREMENT,
  `Pedidos` varchar(100) DEFAULT NULL,
  `Preco` float DEFAULT NULL,
  `Quantidade` int(11) NOT NULL,
  `Total` float NOT NULL,
  `Data_Registrada` varchar(100) DEFAULT NULL,
  `Pagamento` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID_Registro`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro`
--

LOCK TABLES `registro` WRITE;
/*!40000 ALTER TABLE `registro` DISABLE KEYS */;
INSERT INTO `registro` VALUES (1,'X-Burguer',8,0,0,'18/06/18','Dinheiro'),(2,'Coca-Cola',2.5,0,0,'18/06/18','Dinheiro'),(3,'X-Burguer',8,0,0,'18/06/18','Cartão'),(4,'Coca-Cola',2.5,0,0,'18/06/18','Cartão'),(5,'X-Burguer',8,0,0,'18/06/18','Dinheiro'),(6,'Coca-Cola',2.5,0,0,'18/06/18','Dinheiro'),(7,'X-Burguer',8,0,0,'18/06/18','Cartão'),(8,'Coca-Cola',2.5,0,0,'18/06/18','Cartão'),(9,'X-Burguer',8,0,0,'18/06/18','Cartão'),(10,'Coca-Cola',2.5,0,0,'18/06/18','Cartão'),(11,'X-Burguer',8,0,0,'18/06/18','Dinheiro'),(12,'Coca-Cola',2.5,0,0,'18/06/18','Dinheiro'),(13,'X-Burguer',8,0,0,'18/06/18','Dinheiro'),(14,'Coca-Cola',2.5,0,0,'18/06/18','Dinheiro'),(15,'X-Burguer',8,0,0,'18/06/18','Dinheiro'),(16,'Coca-Cola',2.5,0,0,'18/06/18','Dinheiro'),(17,'X-Burguer',8,2,16,'18/06/18','Cartão'),(18,'Coca-Cola',2.5,2,5,'18/06/18','Cartão');
/*!40000 ALTER TABLE `registro` ENABLE KEYS */;
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
