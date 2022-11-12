--
-- Table structure for table `flyway_schema_history`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flyway_schema_history` (
    `installed_rank` int(11) NOT NULL,
    `version` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `description` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
    `type` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
    `script` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
    `checksum` int(11) DEFAULT NULL,
    `installed_by` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
    `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `execution_time` int(11) NOT NULL,
    `success` tinyint(1) NOT NULL,
    PRIMARY KEY (`installed_rank`),
    KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1','<< Flyway Baseline >>','BASELINE','<< Flyway Baseline >>',NULL,'anan','2022-11-12 03:32:09',0,1),(2,'3.2.0','Init-anan-platform','SQL','V3.2.0__Init-anan-platform.sql',1577626563,'anan','2022-11-12 03:32:13',32,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;
