-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bankingapp
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
-- Table structure for table `deposits`
--

DROP TABLE IF EXISTS `deposits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deposits` (
  `id` int(11) NOT NULL,
  `amount` bigint(12) NOT NULL,
  `user_accountNumber` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4r7k4eybnjgjfxe9y0vlr7q4` (`user_accountNumber`),
  CONSTRAINT `FKt4r7k4eybnjgjfxe9y0vlr7q4` FOREIGN KEY (`user_accountNumber`) REFERENCES `users` (`accountNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deposits`
--

LOCK TABLES `deposits` WRITE;
/*!40000 ALTER TABLE `deposits` DISABLE KEYS */;
INSERT INTO `deposits` VALUES (30,22222,'NJ96911150543615',NULL),(31,3221,'NJ96911150543615',NULL),(32,100000000,'NJ96911150543615',NULL),(36,42,'NJ96911150543615',NULL),(37,900,'NJ95091453066586',NULL),(69,100000,'NJ95091453066586',NULL),(73,11111,'NJ96911150543615',NULL),(92,10000,'NJ56310444104128',NULL),(93,1000000,'NJ56310444104128',NULL),(105,2122,'NJ95091453066586',NULL),(112,5555321,'NJ96911150543615',NULL),(122,111111,'NJ96911150543615',NULL),(123,535353535,'NJ96911150543615',NULL),(213,422,'NJ28119394188381',NULL),(214,2424,'NJ28119394188381',NULL),(265,422,'NJ28119394188381',NULL),(267,0,'NJ28119394188381',NULL),(300,42424,'NJ28119394188381',NULL),(347,900000,'NJ28119394188381',NULL),(353,111111,'NJ28119394188381',NULL),(354,1000000,'NJ28119394188381',NULL),(358,35355,'NJ28119394188381',NULL),(359,5325325,'NJ28119394188381',NULL);
/*!40000 ALTER TABLE `deposits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (360);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'USER'),(2,'ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `securities`
--

DROP TABLE IF EXISTS `securities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `securities` (
  `id` int(11) NOT NULL,
  `user_accountNumber` varchar(255) NOT NULL,
  `stockcode` varchar(45) NOT NULL,
  `quantity` varchar(45) NOT NULL,
  `amount` varchar(45) NOT NULL,
  `averagePrice` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo3915c3osj3icwfl9xgxyhu0u` (`user_accountNumber`),
  CONSTRAINT `FKo3915c3osj3icwfl9xgxyhu0u` FOREIGN KEY (`user_accountNumber`) REFERENCES `users` (`accountNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securities`
--

LOCK TABLES `securities` WRITE;
/*!40000 ALTER TABLE `securities` DISABLE KEYS */;
INSERT INTO `securities` VALUES (195,'NJ96911150543615','MSFT','4346','1015225.6',215.37),(197,'NJ96911150543615','AAPL','166','19913.36',122.29),(216,'NJ28119394188381','H','1','83.96',73.87),(286,'NJ96911150543615','BABA','88','19876.56',221.59),(289,'NJ96911150543615','MP','424','15455.648',35.51),(290,'NJ96911150543615','CL','424','33173.759999999995',85.04),(292,'NJ28119394188381','MP','9','317.47499999999997',33.33),(301,'NJ28119394188381','BTWN','42','464.94',16.14),(303,'NJ28119394188381','MGNI','230','9027.5',30.3),(311,'NJ28119394188381','BA','42','10500.315',217.78),(351,'NJ28119394188381','DMTK','6665','336849.1',50.74),(356,'NJ28119394188381','UPST','6621','809351.0399999999',122.75);
/*!40000 ALTER TABLE `securities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `id` int(11) NOT NULL,
  `user_accountNumber` varchar(255) DEFAULT NULL,
  `stockCode` varchar(255) DEFAULT NULL,
  `type` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `dateCreated` datetime(6) DEFAULT NULL,
  `dateformat` tinyblob,
  PRIMARY KEY (`id`),
  KEY `FK2xbilnaopa51m636cde4d1ywh` (`user_accountNumber`),
  CONSTRAINT `FK2xbilnaopa51m636cde4d1ywh` FOREIGN KEY (`user_accountNumber`) REFERENCES `users` (`accountNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (75,'NJ96911150543615','SHOP','0',6,881,'2020-06-22 16:53:02.707273','2020-06-22 16:53:02.707273',NULL),(77,'NJ96911150543615','SHOP','0',28,2000,'2020-06-22 17:09:22.469735','2020-06-22 17:09:22.469735',NULL),(78,'NJ96911150543615','SHOP','1',2,901,'2020-06-22 17:12:29.790129',NULL,NULL),(79,'NJ96911150543615','SHOP','1',4,512,'2020-06-22 17:12:50.207312',NULL,NULL),(81,'NJ96911150543615','SHOP','0',27,881,'2020-06-22 17:12:52.035496',NULL,NULL),(84,'NJ56310444104128','SHOP','0',5,905,'2020-06-23 15:36:57.302897',NULL,NULL),(86,'NJ56310444104128','AAPL','1',1,359,'2020-06-23 15:45:28.646965',NULL,NULL),(88,'NJ56310444104128','GOOGL','1',2,1451,'2020-06-23 15:45:52.001947',NULL,NULL),(90,'NJ56310444104128','GOOGL','0',8,1451,'2020-06-23 15:45:54.339650',NULL,NULL),(94,'NJ56310444104128','SHOP','1',2,905,'2020-06-23 15:52:10.091783',NULL,NULL),(96,'NJ56310444104128','SHOP','0',8,905,'2020-06-23 15:52:12.083039',NULL,NULL),(98,'NJ56310444104128','AXP','1',5,99,'2020-06-23 15:55:41.284354',NULL,NULL),(100,'NJ56310444104128','AXP','0',21,99,'2020-06-23 15:55:43.278709',NULL,NULL),(102,'NJ56310444104128','AMD','1',5,55,'2020-06-23 16:37:27.763260',NULL,NULL),(104,'NJ56310444104128','MA','1',5,304,'2020-06-23 16:47:04.274518',NULL,NULL),(109,'NJ96911150543615','SQ','0',13,198,'2020-11-07 15:11:44.433269',NULL,NULL),(111,'NJ96911150543615','AAPL','0',36,119,'2020-11-07 15:19:54.697377',NULL,NULL),(115,'NJ96911150543615','AAPL','0',3249,119,'2020-11-08 13:02:03.352592',NULL,NULL),(117,'NJ96911150543615','AAPL','0',5471,119,'2020-11-08 13:13:49.548090',NULL,NULL),(118,'NJ96911150543615','AAPL','1',22222222,119,'2020-11-08 13:18:43.675414',NULL,NULL),(121,'NJ96911150543615','AXP','0',26484,97,'2020-11-09 18:23:52.496468',NULL,NULL),(127,'NJ96911150543615','AAPL','1',42,119,'2020-11-09 18:40:44.988628',NULL,NULL),(129,'NJ96911150543615','AAPL','0',398,119,'2020-11-09 18:45:00.080829',NULL,NULL),(131,'NJ96911150543615','MSFT','1',4,215,'2020-11-13 21:35:57.110381',NULL,NULL),(132,'NJ96911150543615','AAPL','1',241,119,'2020-11-13 21:42:40.491616',NULL,NULL),(134,'NJ96911150543615','AAPL','0',158,119,'2020-11-13 21:42:42.066143',NULL,NULL),(136,'NJ96911150543615','AAPL','0',160,119,'2020-11-13 21:42:45.309606',NULL,NULL),(138,'NJ96911150543615','MSFT','0',2,215,'2020-11-13 21:43:36.418796',NULL,NULL),(142,'NJ96911150543615','AAPL','1',160,117,'2020-11-30 12:32:32.460861',NULL,NULL),(143,'NJ96911150543615','AAPL','1',160,117,'2020-11-30 12:32:32.513933',NULL,NULL),(144,'NJ96911150543615','AXP','1',26484,121,'2020-11-30 12:32:52.598298',NULL,NULL),(145,'NJ96911150543615','AXP','1',26484,121,'2020-11-30 12:32:52.628526',NULL,NULL),(146,'NJ96911150543615','KL','1',32,40,'2020-11-30 12:33:10.183427',NULL,NULL),(147,'NJ96911150543615','KL','1',32,40,'2020-11-30 12:33:10.217448',NULL,NULL),(148,'NJ96911150543615','MSFT','1',2,215,'2020-11-30 12:33:18.118441',NULL,NULL),(149,'NJ96911150543615','MSFT','1',2,215,'2020-11-30 12:33:18.157624',NULL,NULL),(150,'NJ96911150543615','IPOB','1',15222,22,'2020-11-30 12:33:29.533912',NULL,NULL),(151,'NJ96911150543615','IPOB','1',15222,22,'2020-11-30 12:33:29.569128',NULL,NULL),(152,'NJ96911150543615','BA','1',20,217,'2020-11-30 12:33:45.949721',NULL,NULL),(153,'NJ96911150543615','BA','1',20,217,'2020-11-30 12:33:45.984491',NULL,NULL),(154,'NJ96911150543615','SHOP','1',27,1034,'2020-11-30 12:33:54.808637',NULL,NULL),(155,'NJ96911150543615','SHOP','1',27,1034,'2020-11-30 12:33:54.840855',NULL,NULL),(156,'NJ96911150543615','SQ','1',13,213,'2020-11-30 12:34:09.757622',NULL,NULL),(157,'NJ96911150543615','SQ','1',13,213,'2020-11-30 12:34:09.802333',NULL,NULL),(158,'NJ96911150543615','MP','1',21,22,'2020-11-30 12:35:23.143852',NULL,NULL),(159,'NJ96911150543615','MP','1',20,22,'2020-11-30 12:35:28.159793',NULL,NULL),(161,'NJ96911150543615','MP','1',1,22,'2020-11-30 12:36:10.109694',NULL,NULL),(162,'NJ96911150543615','MP','1',1,22,'2020-11-30 12:36:10.156815',NULL,NULL),(165,'NJ96911150543615','AAPL','0',86,117,'2020-11-30 12:46:45.216366',NULL,NULL),(167,'NJ96911150543615','AAPL','0',98,117,'2020-11-30 12:49:08.489866',NULL,NULL),(174,'NJ96911150543615','SHOP','0',63,1034,'2020-11-30 12:54:56.438172',NULL,NULL),(175,'NJ96911150543615','SHOP','0',42,1034,'2020-11-30 12:54:56.439162',NULL,NULL),(177,'NJ96911150543615','SHOP','0',71,1034,'2020-11-30 12:59:09.890157',NULL,NULL),(179,'NJ96911150543615','SHOP','0',80,1034,'2020-11-30 12:59:11.995295',NULL,NULL),(182,'NJ96911150543615','BA','0',21,217,'2020-11-30 13:00:01.268449',NULL,NULL),(184,'NJ96911150543615','AAPL','0',21,117,'2020-11-30 13:03:26.021550',NULL,NULL),(186,'NJ96911150543615','AAPL','0',144,117,'2020-11-30 13:03:33.837991',NULL,NULL),(189,'NJ96911150543615','BABA','0',64,261,'2020-12-03 20:25:33.788967',NULL,NULL),(192,'NJ96911150543615','MSFT','0',2,215,'2020-12-03 20:25:53.322692',NULL,NULL),(194,'NJ96911150543615','MSFT','0',2124,215,'2020-12-03 20:26:01.093591',NULL,NULL),(196,'NJ96911150543615','MSFT','0',4346,215,'2020-12-03 20:26:15.093584',NULL,NULL),(198,'NJ96911150543615','AAPL','0',166,122,'2020-12-05 16:50:21.130205',NULL,NULL),(212,'NJ28119394188381','BA','0',32,232,'2020-12-10 11:46:11.100321',NULL,NULL),(287,'NJ96911150543615','BABA','0',88,222,'2020-12-28 16:18:22.104654',NULL,NULL),(293,'NJ28119394188381','MP','0',12,36,'2020-12-28 16:22:30.359633',NULL,NULL),(296,'NJ28119394188381','BA','0',43,217,'2020-12-28 16:23:00.884441',NULL,NULL),(297,'NJ28119394188381','MP','1',3,31,'2020-12-30 22:50:29.898793',NULL,NULL),(304,'NJ28119394188381','MGNI','0',472,30,'2020-12-30 22:55:33.191586',NULL,NULL),(305,'NJ28119394188381','MGNI','1',2,30,'2020-12-30 22:55:38.631815',NULL,NULL),(306,'NJ28119394188381','MGNI','1',3,30,'2020-12-30 22:55:40.888062',NULL,NULL),(307,'NJ28119394188381','MGNI','1',2,30,'2020-12-30 22:55:56.015588',NULL,NULL),(308,'NJ28119394188381','MGNI','1',2,30,'2020-12-30 22:56:00.090533',NULL,NULL),(309,'NJ28119394188381','MGNI','1',2,30,'2020-12-30 22:56:02.519589',NULL,NULL),(310,'NJ28119394188381','MGNI','1',231,30,'2020-12-30 22:56:03.966563',NULL,NULL),(312,'NJ28119394188381','BA','0',45,217,'2020-12-30 23:06:54.105116',NULL,NULL),(313,'NJ28119394188381','BA','1',3,218,'2020-12-30 23:06:56.102590',NULL,NULL),(350,'NJ28119394188381','DMTK','0',6110,51,'2021-04-08 21:55:07.238476',NULL,NULL),(352,'NJ28119394188381','DMTK','0',6665,51,'2021-04-08 21:55:09.199167',NULL,NULL),(357,'NJ28119394188381','UPST','0',6621,123,'2021-04-08 21:56:35.884190',NULL,NULL);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(13,1),(29,1),(82,1),(139,1),(140,1),(199,1),(221,1),(245,1),(271,1),(325,1),(10,2),(12,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_stocks`
--

DROP TABLE IF EXISTS `user_stocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_stocks` (
  `id` int(11) NOT NULL,
  `user_accountNumber` varchar(255) NOT NULL,
  `ticker` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_accountNumber_idx` (`user_accountNumber`),
  CONSTRAINT `user_accountNumber` FOREIGN KEY (`user_accountNumber`) REFERENCES `users` (`accountNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_stocks`
--

LOCK TABLES `user_stocks` WRITE;
/*!40000 ALTER TABLE `user_stocks` DISABLE KEYS */;
INSERT INTO `user_stocks` VALUES (222,'NJ39964892893347','AAPL'),(223,'NJ39964892893347','BABA'),(224,'NJ39964892893347','MSFT'),(225,'NJ39964892893347','AXP'),(226,'NJ39964892893347','BA'),(227,'NJ39964892893347','AMD'),(228,'NJ39964892893347','TSLA'),(229,'NJ39964892893347','MA'),(230,'NJ39964892893347','SHOP'),(231,'NJ39964892893347','GOOGL'),(232,'NJ39964892893347','KL'),(250,'NJ26424968121413','BA'),(251,'NJ26424968121413','AMD'),(257,'NJ26424968121413','BABA'),(272,'NJ48442774801424','AAPL'),(273,'NJ48442774801424','BABA'),(274,'NJ48442774801424','MSFT'),(275,'NJ48442774801424','AXP'),(276,'NJ48442774801424','BA'),(277,'NJ48442774801424','AMD'),(278,'NJ48442774801424','TSLA'),(279,'NJ48442774801424','MA'),(280,'NJ48442774801424','SHOP'),(281,'NJ48442774801424','GOOGL'),(282,'NJ48442774801424','KL'),(285,'NJ96911150543615','BABA'),(288,'NJ96911150543615','MP'),(322,'NJ28119394188381','MP'),(326,'NJ88147768812917','AAPL'),(327,'NJ88147768812917','BABA'),(328,'NJ88147768812917','MSFT'),(329,'NJ88147768812917','AXP'),(330,'NJ88147768812917','BA'),(331,'NJ88147768812917','AMD'),(332,'NJ88147768812917','TSLA'),(333,'NJ88147768812917','MA'),(334,'NJ88147768812917','SHOP'),(335,'NJ88147768812917','GOOGL'),(336,'NJ88147768812917','KL'),(339,'NJ28119394188381','UPST'),(340,'NJ28119394188381','DMTK'),(345,'NJ28119394188381','TMDX'),(346,'NJ28119394188381','CELH');
/*!40000 ALTER TABLE `user_stocks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL,
  `email` varchar(80) NOT NULL,
  `accountNumber` varchar(45) NOT NULL,
  `balance` int(10) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `reset_token` char(36) DEFAULT NULL,
  `timestamp` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`username`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `UK_sx468g52bpetvlad2j9y0lptc` (`accountNumber`),
  UNIQUE KEY `UK_su4mx9ybmrh547dt95himwyxm` (`accountNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Nicholas','Jojo','newaccount123','$2a$10$gLvz1fYkbLP3MeRArzzRt.EOM.0nfn9p89wSFX0MVA3KwauFOmKYu','test@test123.com','NJ25805798719294',25000,1,NULL,NULL),(10,'Nicholas','Jojo','nick88','$2a$10$oO.5mb9MTuRMbILe657z3Obpdf44/Hm4OLVMYy/d/cJ2aqMJ3IVDO','nicholasjojo888@gmail.com','NJ96911150543615',919882,1,'',''),(12,'Nicholas','Jojo','nicholasjojo','$2a$10$PdPQyVemSjSqYNWV9bnP3eoNfzdrRnWk2.NRRGxMyvcykVzjkwi.G','mcafos@gmail.com','NJ57669358828026',25000,1,NULL,NULL),(13,'Nicholas','Jojo','nick','$2a$10$87FgOgqhKhSf76rQpcD8t.RNu0cRqL.7lZ8C.iHCdSkDFe1WuENCq','middlecone@hotmail.com','NJ92936875133059',264406803,1,'c7ac0dd5-2e80-4893-8e39-30785411078f','09.19.7'),(29,'Nicholas 2','Jojo','nick882','$2a$10$DT2P6LYNMrg9OyR4lDppNu.q6DvfwPJ4WcHeBpzIMcUkJ0Vlqih/2','nicholasjojo888@gmail.com','NJ95091453066586',128022,1,NULL,NULL),(82,'Nicholas','Jojo','middlecone','$2a$10$vM.mg74Xr1abOVp4JMASmeuWrgdSS6AFgIaR3iVyRSsdPWvo87XtS','test@hotmail.com','NJ56310444104128',989930,1,NULL,NULL),(139,'Nick','Jojo','nickjojo','$2a$10$HIqLiE5KaYhaLi4y8kNeZep6yn.P.7nyhW1VnrtIbNgVmhFl0NcMG','888@gmail.com','NJ43679628604389',25000,1,NULL,NULL),(140,'nick','jojo','jojo','$2a$10$q4.6MjFzasBm0.fEO.PVyeLD/DCGyj2GsyJ0ZiaHslMAnS0vcXwqe','m@d.com','NJ59255927218323',25000,1,NULL,NULL),(199,'Nick','Jojo','nj89','$2a$10$7O8/hwqWg5Cus4xv34Qb1.O8wLpQUZn/wIQqPh/p4VWbtCJuLm24i','test@ggg.com','NJ28119394188381',4769240,1,NULL,NULL),(221,'dw','wd','dw','$2a$10$juY5PIC6YXATxc4ONmokYO.e5SfHzCFrPUKFip2LyaaChxyPXoKHS','dw','NJ39964892893347',25000,1,NULL,NULL),(245,'Peter','Atkins','peteratkins','$2a$10$JVWb2sv1EXTbgSd19brre.ByIA9seUH.ZmW5quPvOo.BpawCA14Ku','peter@gmail.com','NJ26424968121413',25000,1,NULL,NULL),(271,'nick','jojo','nick9','$2a$10$TVUkJ4oI/GNEM82lZrPzguZZYeCsajCqF7KBlj/UNjFpwNTEZ0Ohe','m@cok.com','NJ48442774801424',25000,1,NULL,NULL),(325,'nik','ko','8nj','$2a$10$F9uDErwqq40BQZIDjeey8e6.L0qRlA4Qk6Rk4qbJmQMv5cVsDdbAW','middlecone@hotmail.com','NJ88147768812917',25000,1,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `withdraw`
--

DROP TABLE IF EXISTS `withdraw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `withdraw` (
  `id` bigint(20) NOT NULL,
  `amount` double DEFAULT NULL,
  `toNumber` varchar(255) DEFAULT NULL,
  `user_accountNumber` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfvoqihdgvq3gpcn6ki63yvsuf` (`user_accountNumber`),
  CONSTRAINT `FKfvoqihdgvq3gpcn6ki63yvsuf` FOREIGN KEY (`user_accountNumber`) REFERENCES `users` (`accountNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `withdraw`
--

LOCK TABLES `withdraw` WRITE;
/*!40000 ALTER TABLE `withdraw` DISABLE KEYS */;
INSERT INTO `withdraw` VALUES (14,2123,'NJ92936875133059',NULL,NULL),(15,2123,'NJ92936875133059',NULL,NULL),(16,15000,'NJ92936875133059',NULL,NULL),(17,2000,'NJ92936875133059','NJ96911150543615',NULL),(18,1000,'NJ92936875133059','NJ96911150543615',NULL),(19,1000,'NJ92936875133059','NJ96911150543615',NULL),(20,200,'NJ92936875133059','NJ96911150543615',NULL),(21,215,'NJ92936875133059','NJ96911150543615',NULL),(22,21,'NJ92936875133059','NJ96911150543615',NULL),(23,123,'NJ92936875133059','NJ96911150543615',NULL),(24,232,'NJ92936875133059','NJ96911150543615',NULL),(25,321,'NJ92936875133059','NJ96911150543615',NULL),(26,100,'NJ92936875133059','NJ96911150543615',NULL),(27,21,'NJ92936875133059','NJ96911150543615',NULL),(33,424224,'NJ92936875133059','NJ96911150543615',NULL),(34,2424224,'NJ92936875133059','NJ96911150543615',NULL),(35,22222222,'NJ92936875133059','NJ96911150543615',NULL),(38,5000000,'NJ92936875133059','NJ96911150543615',NULL),(39,15000,'NJ92936875133059','NJ96911150543615',NULL),(48,21,'NJ92936875133059','NJ96911150543615',NULL),(70,500,'NJ92936875133059','NJ96911150543615',NULL),(91,5,'NJ92936875133059','NJ56310444104128',NULL),(106,4221,'NJ92936875133059','NJ96911150543615',NULL),(113,21421,'NJ92936875133059','NJ96911150543615',NULL),(124,234214214,'NJ92936875133059','NJ96911150543615',NULL),(125,21243,'NJ92936875133059','NJ96911150543615',NULL),(215,4222,'NJ92936875133059','NJ28119394188381',NULL),(235,1000,'NJ92936875133059','NJ28119394188381',NULL),(236,424,'NJ92936875133059','NJ28119394188381',NULL),(237,4222,'NJ92936875133059','NJ28119394188381',NULL),(260,55,'NJ92936875133059','NJ28119394188381',NULL),(266,41,'NJ92936875133059','NJ28119394188381',NULL),(268,53,'NJ92936875133059','NJ28119394188381',NULL),(284,8,'NJ92936875133059','NJ28119394188381',NULL),(337,4,'NJ92936875133059','NJ28119394188381',NULL);
/*!40000 ALTER TABLE `withdraw` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-09 11:21:10
