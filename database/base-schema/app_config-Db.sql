CREATE DATABASE  IF NOT EXISTS `appconfig` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `appconfig`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: app_config
-- ------------------------------------------------------
-- Server version	8.0.18
DROP TABLE IF EXISTS `app_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `optlock` bigint(20) NOT NULL,
  `rfu1` varchar(100) DEFAULT NULL,
  `rfu2` varchar(100) DEFAULT NULL,
  `rfu3` varchar(100) DEFAULT NULL,
  `code` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `device_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `app_product_offering_service`
--

DROP TABLE IF EXISTS `app_product_offering_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_product_offering_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `optlock` bigint(20) NOT NULL,
  `rfu1` varchar(100) DEFAULT NULL,
  `rfu2` varchar(100) DEFAULT NULL,
  `rfu3` varchar(100) DEFAULT NULL,
  `product_offering_service_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `application_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `app_user_field_map`
--

DROP TABLE IF EXISTS `app_user_field_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_user_field_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `optlock` bigint(20) NOT NULL,
  `rfu1` varchar(100) DEFAULT NULL,
  `rfu2` varchar(100) DEFAULT NULL,
  `rfu3` varchar(100) DEFAULT NULL,
  `app_id` int(11) DEFAULT NULL,
  `group_name` varchar(50) DEFAULT NULL,
  `is_mandatory` int(11) DEFAULT NULL,
  `profile_score` int(11) DEFAULT NULL,
  `registration_field` int(11) DEFAULT NULL,
  `user_field_code` varchar(20) DEFAULT NULL,
  `user_field_name` varchar(50) DEFAULT NULL,
  `validation_regex` varchar(255) DEFAULT NULL,
  `master_user_field_id` bigint(20) DEFAULT NULL,
  `group_sort_order` int(11) DEFAULT NULL,
  `field_sort_order` int(11) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `optlock` bigint(20) NOT NULL,
  `rfu1` varchar(100) DEFAULT NULL,
  `rfu2` varchar(100) DEFAULT NULL,
  `rfu3` varchar(100) DEFAULT NULL,
  `code` varchar(20) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `partner_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `master_user_field`
--

DROP TABLE IF EXISTS `master_user_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `master_user_field` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `optlock` bigint(20) NOT NULL,
  `rfu1` varchar(100) DEFAULT NULL,
  `rfu2` varchar(100) DEFAULT NULL,
  `rfu3` varchar(100) DEFAULT NULL,
  `code` varchar(20) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `partner`
--

DROP TABLE IF EXISTS `partner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `optlock` bigint(20) NOT NULL,
  `rfu1` varchar(100) DEFAULT NULL,
  `rfu2` varchar(100) DEFAULT NULL,
  `rfu3` varchar(100) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `owner_type` int(11) DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_offering`
--

DROP TABLE IF EXISTS `product_offering`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_offering` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `optlock` bigint(20) NOT NULL,
  `rfu1` varchar(100) DEFAULT NULL,
  `rfu2` varchar(100) DEFAULT NULL,
  `rfu3` varchar(100) DEFAULT NULL,
  `code` varchar(20) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `sort_order` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_offering_service`
--

DROP TABLE IF EXISTS `product_offering_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_offering_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `optlock` bigint(20) NOT NULL,
  `rfu1` varchar(100) DEFAULT NULL,
  `rfu2` varchar(100) DEFAULT NULL,
  `rfu3` varchar(100) DEFAULT NULL,
  `code` varchar(20) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `product_offering_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `web_request_log`
--

DROP TABLE IF EXISTS `web_request_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `web_request_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `context` varchar(55) DEFAULT NULL,
  `http_method` varchar(10) DEFAULT NULL,
  `processing_time_inms` bigint(20) NOT NULL,
  `query_string` varchar(255) DEFAULT NULL,
  `remote_address` varchar(128) DEFAULT NULL,
  `request_body` text,
  `request_header_and_values` text,
  `request_id` varchar(255) DEFAULT NULL,
  `request_received_date` datetime(6) DEFAULT NULL,
  `request_size` int(11) NOT NULL,
  `response_body` text,
  `response_header_and_values` varchar(4096) DEFAULT NULL,
  `response_size` int(11) NOT NULL,
  `response_status` int(11) DEFAULT NULL,
  `source_address` varchar(55) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `client_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Dump completed on 2020-10-23 14:08:42
