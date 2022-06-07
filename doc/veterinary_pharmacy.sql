-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: veterinary_pharmacy
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `cart_details`
--

DROP TABLE IF EXISTS `cart_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_details` (
  `c_id` int NOT NULL AUTO_INCREMENT,
  `cart_id` int NOT NULL,
  `medicine_id` int NOT NULL,
  `quantity` int NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`c_id`),
  KEY `FK13f85dcqtvrqvut10srepr43r` (`cart_id`),
  KEY `FK2lc28wrmw3iac0bl5nexpooxk` (`medicine_id`),
  CONSTRAINT `FK13f85dcqtvrqvut10srepr43r` FOREIGN KEY (`cart_id`) REFERENCES `shopping_cart` (`id`),
  CONSTRAINT `FK2lc28wrmw3iac0bl5nexpooxk` FOREIGN KEY (`medicine_id`) REFERENCES `medicines` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_code` varchar(45) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_code_UNIQUE` (`category_code`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medicines`
--

DROP TABLE IF EXISTS `medicines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicines` (
  `id` int NOT NULL AUTO_INCREMENT,
  `m_name` varchar(100) NOT NULL,
  `m_description` varchar(255) NOT NULL,
  `m_code` varchar(8) NOT NULL,
  `unit_in_stock` int NOT NULL,
  `amount` double NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `categoryId` int NOT NULL,
  `category_id` int DEFAULT NULL,
  `create_user_id` int DEFAULT NULL,
  `deleted_user_id` int DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `updated_user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK67kgcomhmvewc8obgigvhvhi` (`categoryId`),
  CONSTRAINT `FK67kgcomhmvewc8obgigvhvhi` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `o_id` int NOT NULL,
  `m_id` int NOT NULL,
  `m_quantity` int NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrmf6enpsuh70a0r0sry7ixb48` (`m_id`),
  CONSTRAINT `FKrmf6enpsuh70a0r0sry7ixb48` FOREIGN KEY (`m_id`) REFERENCES `medicines` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `u_name` varchar(45) NOT NULL,
  `u_phone` varchar(20) NOT NULL,
  `u_address` varchar(255) NOT NULL,
  `date` datetime DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  `amount` double NOT NULL,
  `created_user_id` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_user_id` int DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_user_id` int DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `orderDetail` tinyblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shopping_cart`
--

DROP TABLE IF EXISTS `shopping_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `checkout_flg` tinyint DEFAULT '0',
  `amount` double NOT NULL,
  `created_user_id` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_user_id` int DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_user_id` int DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` tinyint NOT NULL DEFAULT '1',
  `phone` varchar(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `created_user_id` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_user_id` int DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_user_id` int DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `create_user_id` int DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `deletedAt` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-07  0:50:27
