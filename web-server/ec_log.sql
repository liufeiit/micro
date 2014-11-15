-- MySQL dump 10.13  Distrib 5.1.62, for Win32 (ia32)
--
-- Host: localhost    Database: ec_log
-- ------------------------------------------------------
-- Server version	5.1.62-community

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
-- Table structure for table `log_api`
--

DROP TABLE IF EXISTS `log_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_api` (
  `log_api_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `uri` varchar(255) NOT NULL DEFAULT '',
  `platform` varchar(45) NOT NULL DEFAULT 'rest',
  `user_agent` varchar(2048) DEFAULT NULL,
  `method` varchar(10) NOT NULL DEFAULT '',
  `params` text,
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`log_api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_api`
--

LOCK TABLES `log_api` WRITE;
/*!40000 ALTER TABLE `log_api` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_exception`
--

DROP TABLE IF EXISTS `log_exception`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_exception` (
  `log_exception_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `level` enum('fatal','error','warn','info','debug') NOT NULL DEFAULT 'info',
  `thrower` varchar(255) NOT NULL,
  `message` text NOT NULL,
  `memo` text,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`log_exception_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_exception`
--

LOCK TABLES `log_exception` WRITE;
/*!40000 ALTER TABLE `log_exception` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_exception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_system`
--

DROP TABLE IF EXISTS `log_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_system` (
  `log_system_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `level` enum('fatal','error','warn','info','debug') NOT NULL DEFAULT 'info',
  `type` varchar(255) NOT NULL,
  `memo` text,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`log_system_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_system`
--

LOCK TABLES `log_system` WRITE;
/*!40000 ALTER TABLE `log_system` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_user`
--

DROP TABLE IF EXISTS `log_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_user` (
  `log_user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `level` enum('fatal','error','warn','info','debug') NOT NULL DEFAULT 'info',
  `user_id` bigint(20) unsigned NOT NULL,
  `type` varchar(255) NOT NULL,
  `memo` text,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`log_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_user`
--

LOCK TABLES `log_user` WRITE;
/*!40000 ALTER TABLE `log_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-16  1:20:13
