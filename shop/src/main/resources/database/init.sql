create database if not exists shop;
use shop;
-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	8.0.22-0ubuntu0.20.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bucket`
--

DROP TABLE IF EXISTS `bucket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bucket` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `bucket_user_id_uindex` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bucket`
--

LOCK TABLES `bucket` WRITE;
/*!40000 ALTER TABLE `bucket` DISABLE KEYS */;
INSERT INTO `bucket` VALUES (15,'09ef95a0-ea97-4342-aebc-a9acb251a8a4'),(14,'0c8cecf0-2a7b-41a4-af98-3e83189dd068'),(12,'13d66331-875c-43de-bfc5-b178228de6df'),(5,'20bd0117-ad99-4341-b372-7de1100d1b9c'),(16,'2d4b7c2f-b204-4b64-bd9f-3e336be44090'),(8,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8'),(9,'4db0d4ae-7928-4530-aa81-be2704896983'),(13,'6742d03b-b9e8-4a81-a2fa-0fc057ecae26'),(7,'738c7cee-a2f3-45b8-907d-d435049d78ff'),(10,'7be46add-98ba-481b-816b-a7318765528b'),(4,'84f02435-84ec-436c-9071-afffa952c682'),(6,'a0cd60f3-bd12-409f-816f-6dc8bec00c89'),(17,'a13534d6-876b-4e5e-91ab-cbfc66a98b4c'),(2,'a6aacd58-f649-4591-b5a1-c589419a5a41'),(1,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5'),(11,'ed0956af-e0a0-454e-97b7-2492ab17eaf8'),(3,'f3c385ec-02e7-4735-8f41-60967c37f320');
/*!40000 ALTER TABLE `bucket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bucket_product`
--

DROP TABLE IF EXISTS `bucket_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bucket_product` (
  `bucket_id` bigint unsigned NOT NULL,
  `product_id` bigint unsigned NOT NULL,
  `count` bigint unsigned NOT NULL,
  PRIMARY KEY (`bucket_id`,`product_id`),
  KEY `bucket_product_products__fk` (`product_id`),
  CONSTRAINT `bucket_product_bucket__fk` FOREIGN KEY (`bucket_id`) REFERENCES `bucket` (`id`),
  CONSTRAINT `bucket_product_products__fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bucket_product`
--

LOCK TABLES `bucket_product` WRITE;
/*!40000 ALTER TABLE `bucket_product` DISABLE KEYS */;
INSERT INTO `bucket_product` VALUES (4,2,10),(4,3,10),(16,1,1);
/*!40000 ALTER TABLE `bucket_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customers_id_uindex` (`id`),
  UNIQUE KEY `customers_login_uindex` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES ('','admin','admin'),('09ef95a0-ea97-4342-aebc-a9acb251a8a4','vv','31f0325bff1f0bd1afcb9b6b443922c6a4ac87f984a995af7e3aa65f'),('0c8cecf0-2a7b-41a4-af98-3e83189dd068','wq','f955c8dce95151aca2d7db09bbdf3940047d510030de66c31e5b9013'),('13d66331-875c-43de-bfc5-b178228de6df','ww','0bbe9d812d693c60e2c5180c50c5d73a9915f98be882702c8aea1db0'),('20bd0117-ad99-4341-b372-7de1100d1b9c','dasd','74d64205e9f648e0b1123c0a77a8b9035bf77c946ef6a6586a71a1de'),('2d4b7c2f-b204-4b64-bd9f-3e336be44090','bb','c0bf60326a5c949e2c791bb640f5d08f4703ce68afd692b8c1220554'),('41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','dd','e725a34798186f312b07d3950b9a6c6683d1613e603e9951382e8932'),('4db0d4ae-7928-4530-aa81-be2704896983','aa','cefada8b93bc36184b95cad9d5e0f19f6061df5709de69ac4c6c8fde'),('5c4eeb47-f73e-4370-9ddd-6e3d55211a0e','eee','b96e76ea6a378be70c0847e375569685542bf8886a9dbc33b4c925ee'),('6742d03b-b9e8-4a81-a2fa-0fc057ecae26','qw','c084b0e2f2457430b35f04901163119db068978d4ca9c7715ffdfd86'),('738c7cee-a2f3-45b8-907d-d435049d78ff','ggg','1a065ad5fd22c69641bd65478b4ee6f11d1a75855013978c35903758'),('7be46add-98ba-481b-816b-a7318765528b','cc','90388c3ec5ea7cef40f3e0ca9806c119001b1a37f0769066f11cd3e3'),('84f02435-84ec-436c-9071-afffa952c682','xxx','ef568c1a4b11c92db33c8f2944641b7687767b7c2cf330b011491219'),('8d2641d2-e49d-4405-a640-9a0624090b48','tt','9b4aa2336edf356e09d810c781324200b488eb86841fc3df94765acc'),('991b0d5b-d478-419a-ba9c-d2f0647d78cd','fff','fff'),('a0cd60f3-bd12-409f-816f-6dc8bec00c89','uu','f4b1ea2adba59faf10ef3376e964efb3e860b9ff8d194b13c37ec804'),('a13534d6-876b-4e5e-91ab-cbfc66a98b4c','nn','31fb49debecd248b1486edc868e015f85b732cf66f39b4137c7b8a28'),('a6aacd58-f649-4591-b5a1-c589419a5a41','www','bce14a96126d337063f4919fc7e1cf2ecebb978a49a445aa87bdd78e'),('bfc202cf-7b6d-4ba8-a87a-0d215526840a','aaa','5a13de8e1efb1dc81dba95a445e7e89b6e4d4565305e4d155a5d5163'),('d6321d33-9ff1-4ce1-9fa5-375da4c5c3b2','admin-d632','admin'),('dcea6379-bf2c-4584-9873-926a0cf35628','admindcea','admin'),('e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','qqq','b6a79167eb065e79e02a258b63a7892076c387d034c90a6c7aafa402'),('e71d162e-4dff-4563-9293-c27a502b3d01','admin-e71d','admin'),('ed0956af-e0a0-454e-97b7-2492ab17eaf8','yy','5037a5277dd1f58c6f697c0c6e9b062c3760b2060d65234652233c8b'),('ed207b35-a4d2-49c4-a713-6f15451a6ca9','ttt','8ccd8c71d1b9ceaa85d66bfeae9868e8ffaba66bfa344d3084fe0022'),('ef8d430d-e369-4137-8585-94689db80963','wer','048d60108a5575a05f89fd5114599099c2b7924f0ed113f0d0e6c526'),('f3c385ec-02e7-4735-8f41-60967c37f320','zzz','fcfbcfff27be142fe175089a8e76a27c0b8f19658d2c4c0091c1269c');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_Id` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `total` double NOT NULL,
  `accepted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `orders_customers__fk` (`user_Id`),
  CONSTRAINT `orders_customers__fk` FOREIGN KEY (`user_Id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-04 00:00:00',2900,1),(2,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-04 00:00:00',638000,1),(3,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-04 00:00:00',1450,1),(4,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-04 00:00:00',869,1),(5,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-04 00:00:00',3730,1),(6,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-04 00:00:00',3730,1),(7,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-04 00:00:00',32160,1),(8,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-04 00:00:00',2460,1),(9,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-04 00:00:00',541200,1),(10,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-05 00:00:00',2560,1),(11,'84f02435-84ec-436c-9071-afffa952c682','2021-01-05 00:00:00',14500,1),(12,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-05 00:00:00',17615,1),(13,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-05 00:00:00',0,1),(14,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-05 00:00:00',1200,1),(15,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-05 00:00:00',100,1),(16,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-05 00:00:00',12000,1),(17,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-05 00:00:00',1107,1),(18,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-05 00:00:00',10,1),(19,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,1),(20,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',1353,1),(21,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,1),(22,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,1),(23,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,1),(24,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(25,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(26,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(27,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(28,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(29,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(30,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(31,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(32,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(33,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(34,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(35,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(36,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(37,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(38,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(39,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(40,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(41,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(42,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(43,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(44,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(45,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(46,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(47,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(48,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(49,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(50,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(51,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(52,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(53,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(54,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(55,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(56,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(57,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(58,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(59,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(60,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(61,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(62,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(63,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(64,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',0,0),(65,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',429,1),(66,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',4428,1),(67,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-06 00:00:00',2523,1),(68,'a0cd60f3-bd12-409f-816f-6dc8bec00c89','2021-01-06 00:00:00',360,1),(69,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-10 00:00:00',23,1),(70,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-10 00:00:00',1476,1),(71,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-11 00:00:00',246,1),(72,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-11 00:00:00',15876,1),(73,'738c7cee-a2f3-45b8-907d-d435049d78ff','2021-01-11 00:00:00',123,1),(74,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-11 00:00:00',27060,1),(75,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-11 00:00:00',2952,1),(76,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',30,1),(77,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',360,1),(78,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',4428,1),(79,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',100,1),(80,'e6e323a8-d0f7-4816-9a4b-c319d4b84ea5','2021-01-11 00:00:00',27060,1),(81,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',27060,1),(82,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',1476,1),(83,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',1476,1),(84,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',27060,1),(85,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',246,1),(86,'4db0d4ae-7928-4530-aa81-be2704896983','2021-01-11 00:00:00',2952,1),(87,'4db0d4ae-7928-4530-aa81-be2704896983','2021-01-11 00:00:00',291060,1),(88,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',1476,1),(89,'7be46add-98ba-481b-816b-a7318765528b','2021-01-11 00:00:00',2952,1),(90,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',123,1),(91,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',4400,1),(92,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',295460,1),(93,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',3300,1),(94,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',720,1),(95,'ed0956af-e0a0-454e-97b7-2492ab17eaf8','2021-01-11 00:00:00',264000,1),(96,'13d66331-875c-43de-bfc5-b178228de6df','2021-01-11 00:00:00',28160,1),(97,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-11 00:00:00',2640,1),(98,'6742d03b-b9e8-4a81-a2fa-0fc057ecae26','2021-01-11 00:00:00',1476,1),(99,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-12 00:00:00',2952,1),(100,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-12 00:00:00',1476,1),(101,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-14 00:00:00',6600,1),(102,'a13534d6-876b-4e5e-91ab-cbfc66a98b4c','2021-01-14 00:00:00',1476,1),(103,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-14 00:00:00',2952,1),(104,'41448fc9-858e-4d41-a07e-ddfb0e2bc8a8','2021-01-14 00:00:00',720,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`admin`@`localhost`*/ /*!50003 TRIGGER `create_order` AFTER INSERT ON `orders` FOR EACH ROW BEGIN
    INSERT INTO orders_history SET
           order_id = new.id,
           updated_at = now(),
           created_at = new.created_at,
           total = new.total,
           accepted = new.accepted;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`admin`@`localhost`*/ /*!50003 TRIGGER `update_order` AFTER UPDATE ON `orders` FOR EACH ROW BEGIN
    INSERT INTO orders_history SET
           order_id = new.id,
           updated_at = now(),
           created_at = new.created_at,
           total = new.total,
           accepted = new.accepted;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `orders_history`
--

DROP TABLE IF EXISTS `orders_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_history` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `total` double NOT NULL,
  `accepted` tinyint(1) NOT NULL,
  `order_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_history`
--

LOCK TABLES `orders_history` WRITE;
/*!40000 ALTER TABLE `orders_history` DISABLE KEYS */;
INSERT INTO `orders_history` VALUES (1,'2021-01-04 00:00:00','2021-01-04 17:42:47',32160,1,7),(2,'2021-01-04 00:00:00','2021-01-04 18:39:32',2460,1,8),(3,'2021-01-04 00:00:00','2021-01-04 18:40:04',541200,0,9),(4,'2021-01-05 00:00:00','2021-01-05 10:05:28',2560,1,10),(5,'2021-01-05 00:00:00','2021-01-05 10:14:15',14500,1,11),(6,'2021-01-04 00:00:00','2021-01-05 10:31:57',638000,0,2),(7,'2021-01-04 00:00:00','2021-01-05 10:34:18',3730,0,5),(8,'2021-01-04 00:00:00','2021-01-05 10:36:34',541200,1,9),(9,'2021-01-05 00:00:00','2021-01-05 13:11:40',17615,1,12),(10,'2021-01-05 00:00:00','2021-01-05 13:15:41',0,0,13),(11,'2021-01-05 00:00:00','2021-01-05 13:15:57',0,1,13),(12,'2021-01-05 00:00:00','2021-01-05 13:17:07',1200,0,14),(13,'2021-01-05 00:00:00','2021-01-05 13:17:20',1200,1,14),(14,'2021-01-05 00:00:00','2021-01-05 13:19:06',100,1,15),(15,'2021-01-05 00:00:00','2021-01-05 13:24:51',12000,0,16),(16,'2021-01-05 00:00:00','2021-01-05 13:25:06',12000,1,16),(17,'2021-01-05 00:00:00','2021-01-05 13:26:43',1107,0,17),(18,'2021-01-05 00:00:00','2021-01-05 13:27:00',1107,1,17),(19,'2021-01-05 00:00:00','2021-01-05 13:59:33',10,0,18),(20,'2021-01-05 00:00:00','2021-01-05 13:59:47',10,1,18),(21,'2021-01-06 00:00:00','2021-01-06 16:51:58',0,0,19),(22,'2021-01-06 00:00:00','2021-01-06 16:53:03',1353,0,20),(23,'2021-01-06 00:00:00','2021-01-06 17:09:13',0,1,19),(24,'2021-01-06 00:00:00','2021-01-06 18:18:42',0,0,21),(25,'2021-01-06 00:00:00','2021-01-06 18:18:42',0,0,22),(26,'2021-01-06 00:00:00','2021-01-06 18:18:43',0,0,23),(27,'2021-01-06 00:00:00','2021-01-06 18:18:43',0,0,24),(28,'2021-01-06 00:00:00','2021-01-06 18:18:43',0,0,25),(29,'2021-01-06 00:00:00','2021-01-06 18:18:43',0,0,26),(30,'2021-01-06 00:00:00','2021-01-06 18:18:43',0,0,27),(31,'2021-01-06 00:00:00','2021-01-06 18:18:43',0,0,28),(32,'2021-01-06 00:00:00','2021-01-06 18:18:44',0,0,29),(33,'2021-01-06 00:00:00','2021-01-06 18:18:44',0,0,30),(34,'2021-01-06 00:00:00','2021-01-06 18:18:44',0,0,31),(35,'2021-01-06 00:00:00','2021-01-06 18:18:44',0,0,32),(36,'2021-01-06 00:00:00','2021-01-06 18:18:44',0,0,33),(37,'2021-01-06 00:00:00','2021-01-06 18:18:44',0,0,34),(38,'2021-01-06 00:00:00','2021-01-06 18:18:44',0,0,35),(39,'2021-01-06 00:00:00','2021-01-06 18:18:45',0,0,36),(40,'2021-01-06 00:00:00','2021-01-06 18:18:45',0,0,37),(41,'2021-01-06 00:00:00','2021-01-06 18:18:45',0,0,38),(42,'2021-01-06 00:00:00','2021-01-06 18:18:45',0,0,39),(43,'2021-01-06 00:00:00','2021-01-06 18:18:45',0,0,40),(44,'2021-01-06 00:00:00','2021-01-06 18:18:46',0,0,41),(45,'2021-01-06 00:00:00','2021-01-06 18:18:46',0,0,42),(46,'2021-01-06 00:00:00','2021-01-06 18:18:46',0,0,43),(47,'2021-01-06 00:00:00','2021-01-06 18:18:46',0,0,44),(48,'2021-01-06 00:00:00','2021-01-06 18:18:46',0,0,45),(49,'2021-01-06 00:00:00','2021-01-06 18:18:46',0,0,46),(50,'2021-01-06 00:00:00','2021-01-06 18:18:46',0,0,47),(51,'2021-01-06 00:00:00','2021-01-06 18:18:47',0,0,48),(52,'2021-01-06 00:00:00','2021-01-06 18:18:47',0,0,49),(53,'2021-01-06 00:00:00','2021-01-06 18:18:47',0,0,50),(54,'2021-01-06 00:00:00','2021-01-06 18:18:47',0,0,51),(55,'2021-01-06 00:00:00','2021-01-06 18:18:47',0,0,52),(56,'2021-01-06 00:00:00','2021-01-06 18:18:47',0,0,53),(57,'2021-01-06 00:00:00','2021-01-06 18:18:47',0,0,54),(58,'2021-01-06 00:00:00','2021-01-06 18:18:48',0,0,55),(59,'2021-01-06 00:00:00','2021-01-06 18:18:48',0,0,56),(60,'2021-01-06 00:00:00','2021-01-06 18:18:48',0,0,57),(61,'2021-01-06 00:00:00','2021-01-06 18:18:48',0,0,58),(62,'2021-01-06 00:00:00','2021-01-06 18:18:48',0,0,59),(63,'2021-01-06 00:00:00','2021-01-06 18:18:48',0,0,60),(64,'2021-01-06 00:00:00','2021-01-06 18:23:03',0,0,61),(65,'2021-01-06 00:00:00','2021-01-06 18:25:25',0,0,62),(66,'2021-01-06 00:00:00','2021-01-06 18:28:13',0,0,63),(67,'2021-01-06 00:00:00','2021-01-06 18:30:06',0,0,64),(68,'2021-01-06 00:00:00','2021-01-06 19:23:49',429,0,65),(69,'2021-01-06 00:00:00','2021-01-06 19:24:36',429,1,65),(70,'2021-01-06 00:00:00','2021-01-06 19:27:35',4428,0,66),(71,'2021-01-06 00:00:00','2021-01-06 19:27:49',4428,1,66),(72,'2021-01-06 00:00:00','2021-01-06 20:05:42',2523,0,67),(73,'2021-01-06 00:00:00','2021-01-06 20:05:55',2523,1,67),(74,'2021-01-06 00:00:00','2021-01-06 20:06:54',360,0,68),(75,'2021-01-06 00:00:00','2021-01-06 20:07:05',360,1,68),(76,'2021-01-06 00:00:00','2021-01-10 22:26:54',1353,1,20),(77,'2021-01-10 00:00:00','2021-01-10 22:27:50',23,0,69),(78,'2021-01-10 00:00:00','2021-01-10 22:27:57',23,1,69),(79,'2021-01-10 00:00:00','2021-01-10 22:39:25',1476,0,70),(80,'2021-01-10 00:00:00','2021-01-10 22:39:29',1476,1,70),(81,'2021-01-06 00:00:00','2021-01-11 01:49:02',0,1,21),(82,'2021-01-06 00:00:00','2021-01-11 01:51:30',0,1,22),(83,'2021-01-06 00:00:00','2021-01-11 01:56:25',0,1,23),(84,'2021-01-11 00:00:00','2021-01-11 02:08:51',246,0,71),(85,'2021-01-11 00:00:00','2021-01-11 02:09:04',246,1,71),(86,'2021-01-11 00:00:00','2021-01-11 02:15:50',15876,0,72),(87,'2021-01-11 00:00:00','2021-01-11 02:15:56',15876,1,72),(88,'2021-01-11 00:00:00','2021-01-11 02:16:44',123,0,73),(89,'2021-01-11 00:00:00','2021-01-11 02:16:52',123,1,73),(90,'2021-01-11 00:00:00','2021-01-11 02:40:03',27060,0,74),(91,'2021-01-11 00:00:00','2021-01-11 02:40:09',27060,1,74),(92,'2021-01-11 00:00:00','2021-01-11 02:54:20',2952,0,75),(93,'2021-01-11 00:00:00','2021-01-11 02:54:30',2952,1,75),(94,'2021-01-11 00:00:00','2021-01-11 02:57:47',30,0,76),(95,'2021-01-11 00:00:00','2021-01-11 02:57:57',30,1,76),(96,'2021-01-11 00:00:00','2021-01-11 03:21:47',360,0,77),(97,'2021-01-11 00:00:00','2021-01-11 03:21:57',360,1,77),(98,'2021-01-11 00:00:00','2021-01-11 04:18:59',4428,0,78),(99,'2021-01-11 00:00:00','2021-01-11 04:19:06',4428,1,78),(100,'2021-01-11 00:00:00','2021-01-11 04:24:19',100,0,79),(101,'2021-01-11 00:00:00','2021-01-11 04:24:26',100,1,79),(102,'2021-01-11 00:00:00','2021-01-11 10:16:00',27060,0,80),(103,'2021-01-11 00:00:00','2021-01-11 10:16:06',27060,1,80),(104,'2021-01-11 00:00:00','2021-01-11 10:57:52',27060,0,81),(105,'2021-01-11 00:00:00','2021-01-11 10:57:54',27060,1,81),(106,'2021-01-11 00:00:00','2021-01-11 11:04:50',1476,0,82),(107,'2021-01-11 00:00:00','2021-01-11 11:04:52',1476,1,82),(108,'2021-01-11 00:00:00','2021-01-11 11:40:56',1476,0,83),(109,'2021-01-11 00:00:00','2021-01-11 11:40:58',1476,1,83),(110,'2021-01-11 00:00:00','2021-01-11 11:55:18',27060,0,84),(111,'2021-01-11 00:00:00','2021-01-11 11:55:21',27060,1,84),(112,'2021-01-11 00:00:00','2021-01-11 12:17:51',246,0,85),(113,'2021-01-11 00:00:00','2021-01-11 12:18:03',246,1,85),(114,'2021-01-11 00:00:00','2021-01-11 12:27:51',2952,0,86),(115,'2021-01-11 00:00:00','2021-01-11 12:27:57',2952,1,86),(116,'2021-01-11 00:00:00','2021-01-11 12:31:07',291060,0,87),(117,'2021-01-11 00:00:00','2021-01-11 12:31:12',291060,1,87),(118,'2021-01-11 00:00:00','2021-01-11 13:21:17',1476,0,88),(119,'2021-01-11 00:00:00','2021-01-11 13:21:19',1476,1,88),(120,'2021-01-11 00:00:00','2021-01-11 13:25:21',2952,0,89),(121,'2021-01-11 00:00:00','2021-01-11 13:25:35',2952,1,89),(122,'2021-01-11 00:00:00','2021-01-11 13:57:18',123,0,90),(123,'2021-01-11 00:00:00','2021-01-11 13:57:31',123,1,90),(124,'2021-01-11 00:00:00','2021-01-11 13:57:49',4400,0,91),(125,'2021-01-11 00:00:00','2021-01-11 13:57:53',4400,1,91),(126,'2021-01-11 00:00:00','2021-01-11 14:11:05',295460,0,92),(127,'2021-01-11 00:00:00','2021-01-11 14:11:10',295460,1,92),(128,'2021-01-11 00:00:00','2021-01-11 14:19:40',3300,0,93),(129,'2021-01-11 00:00:00','2021-01-11 14:19:47',3300,1,93),(130,'2021-01-11 00:00:00','2021-01-11 14:24:09',720,0,94),(131,'2021-01-11 00:00:00','2021-01-11 14:24:17',720,1,94),(132,'2021-01-11 00:00:00','2021-01-11 14:25:05',264000,0,95),(133,'2021-01-11 00:00:00','2021-01-11 14:25:11',264000,1,95),(134,'2021-01-11 00:00:00','2021-01-11 14:41:24',28160,0,96),(135,'2021-01-11 00:00:00','2021-01-11 14:41:27',28160,1,96),(136,'2021-01-11 00:00:00','2021-01-11 17:05:37',2640,0,97),(137,'2021-01-11 00:00:00','2021-01-11 17:05:46',2640,1,97),(138,'2021-01-11 00:00:00','2021-01-11 17:06:28',1476,0,98),(139,'2021-01-11 00:00:00','2021-01-11 17:06:31',1476,1,98),(140,'2021-01-12 00:00:00','2021-01-12 16:39:33',2952,0,99),(141,'2021-01-12 00:00:00','2021-01-12 16:39:41',2952,1,99),(142,'2021-01-12 00:00:00','2021-01-12 18:51:15',1476,0,100),(143,'2021-01-12 00:00:00','2021-01-12 18:51:21',1476,1,100),(144,'2021-01-14 00:00:00','2021-01-14 14:58:11',6600,0,101),(145,'2021-01-14 00:00:00','2021-01-14 14:58:17',6600,1,101),(146,'2021-01-14 00:00:00','2021-01-14 14:59:11',1476,0,102),(147,'2021-01-14 00:00:00','2021-01-14 14:59:16',1476,1,102),(148,'2021-01-14 00:00:00','2021-01-14 15:15:51',2952,0,103),(149,'2021-01-14 00:00:00','2021-01-14 15:15:56',2952,1,103),(150,'2021-01-14 00:00:00','2021-01-14 15:27:26',720,0,104),(151,'2021-01-14 00:00:00','2021-01-14 15:27:31',720,1,104);
/*!40000 ALTER TABLE `orders_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `category` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `products_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'milk',123,'MILK'),(2,'table',250,'NOT_FOOD'),(3,'notebook',1200,'NOT_FOOD'),(4,'water',5,'FOOD'),(5,'apple',10,'FOOD'),(6,'bread',4,'FOOD'),(7,'cheese',40,'MILK'),(8,'oil',30,'MILK'),(9,'yogurt',20,'MILK'),(11,'kitkat',15,'FOOD'),(12,'tea',25,'FOOD');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse` (
  `product_id` bigint unsigned DEFAULT NULL,
  `count` bigint NOT NULL,
  KEY `warehouse_products__fk` (`product_id`),
  CONSTRAINT `warehouse_products__fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` VALUES (2,0),(3,3),(4,43),(5,53),(6,44),(7,56),(8,96),(9,47),(11,108),(1,84);
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-14 20:14:42
