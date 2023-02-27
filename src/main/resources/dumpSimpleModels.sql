CREATE DATABASE  IF NOT EXISTS `simplemodels` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `simplemodels`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: simplemodels
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lastName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `firstName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `phone` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `addressLine1` varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  `addressLine2` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `state` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `postalCode` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (31,'Smith','Harry','hsmith@A.com','421-485-7234','53 Peach Ave',NULL,'Annapolis','MD','25621'),(32,'Jones','Cecil','cjones@A.com','148-185-2294','72 Beach Ave',NULL,'Boston','MA','75621'),(33,'Brown','Alan','abrown@C.com',NULL,'94 Oak St',NULL,'Boston','MA','75621'),(34,'Resnick','Joe','jresnick@B.com','136-485-3498','42 Cherry St','Apt 206','Princeton','NJ','13250'),(35,'Franklin','Joe','jfranklin@E.com','634-485-3498','71 Pine St','Apt 549','Richmond','VA','86259'),(36,'Hughes','Jim','jhughes@F.com','634-485-3498','832 Juniper St','Apt 402','Greenville','NC','57256'),(37,'Ingalls','Frank','fingalls@G.com',NULL,'32 Sunrise Ave','Apt 16','Charleston','SC','79256'),(38,'Jackson','Bill','bjackson@H.com','947-485-3445','35 Walnut St','Apt 74','Atlanta','GA','67251'),(39,'Grant','Dave','dgrant@J.com',NULL,'82 Peachtree Lane',NULL,'Harrisburg','PA','67256'),(40,'Bradley','Hank','hbradley@K.com',NULL,'35 Shore Dr','Apt 26','Ithaca','NY','86256'),(41,'Monroe','Kate','kmonroe@L.com','634-485-3498','84 Spruce St','Apt 5','Concord','NH','86255'),(42,'Madison','Alice','amadison@M.com','634-485-5698','64 Cherry Ave','Apt 604','Bangor','ME','86257'),(43,'Huxley','Mary','mhuxley@N.com','234-485-3498','9425 Elm St',NULL,'Dallas','TX','16254'),(44,'Ford','Ellen','lford@P.com','123-456-5432','21 Maple St',NULL,'Omaha','NE','35621'),(45,'Pierce','Cathy','cpierce@Q.com','321-456-1234','293 Ivy Lane',NULL,'Albany','NY','25625');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` int NOT NULL,
  `lastName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `firstName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `phone` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `reportsTo` int DEFAULT NULL,
  `jobTitle` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `addressLine1` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `addressLine2` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `state` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `postalCode` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `reportsTo` (`reportsTo`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`reportsTo`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1001,'Smith','Harry','hsmith@A.com','421-485-7234',NULL,'President','53 Peach Ave',NULL,'Annapolis','MD','25625'),(2001,'Jones','Cecil','cjones@A.com','148-185-2294',1001,'VP Hardware','72 Beach Ave',NULL,'Boston','MA','75621'),(2002,'Brown','Alan','abrown@C.com',NULL,1001,'VP Software','94 Oak St',NULL,'Boston','MA','75621'),(2003,'Watson','Sally','swatson@B.com','748-185-2291',1001,'VP Research','16 Sunset Ave','Apt 304','Eureka','CA','95621'),(4001,'Bradley','Hank','hbradley@K.com',NULL,2001,'HW Engineer','35 Shore Dr','Apt 26','Ithaca','NY','86256'),(4002,'Monroe','Kate','kmonroe@L.com','634-485-3498',2001,'HW Engineer','84 Spruce St','Apt 5','Concord','NH','86255'),(4003,'Madison','Alice','amadison@M.com','634-485-5698',2002,'SW Engineer','64 Cherry Ave','Apt 604','Bangor','ME','86257'),(4004,'Huxley','Mary','mhuxley@N.com','234-485-3498',2002,'SW Engineer','9425 Elm St',NULL,'Dallas','TX','16254'),(4005,'Blake','Karen','kblake@M.com','534-485-7498',2003,'SW Engineer','43 Green Lane','Apt 64','Topeka','KS','66257'),(4006,'Doyle','Betty','bdoyle@M.com','634-485-3458',2003,'SW Engineer','52 Lake St','Apt 31','Chicago','IL','34253'),(4007,'Hoyle','Amy','ahoyle@M.com',NULL,2002,'SW Engineer','93 Grove St','Apt 19','Austin','TX','55257'),(4008,'Mead','Jeff','jmead@M.com','834-485-7448',2002,'HW Engineer','47 Madison St',NULL,'Boise','ID','33254'),(4009,'Nash','Scott','snash@M.com','434-485-7558',2003,'HW Engineer','81 Lincoln Ave',NULL,'Denver','CO','77252'),(5007,'Bond','James','jbond@mi5.org','007-007-1007',2003,'HW Engineer','007 Ivy Lane',NULL,'London','CT','11007');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-26 15:05:43
