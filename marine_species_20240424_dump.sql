-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 139.196.197.42    Database: marine_species
-- ------------------------------------------------------
-- Server version	5.7.40

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(36) NOT NULL COMMENT '昵称',
  `password` varchar(32) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `token` varchar(512) DEFAULT NULL,
  `avatar_url` varchar(512) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'Violet','forever25','我吃好了','81018A14B936E7EA21990E643E70B5B3','https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/23749d19043c41b6957968904c9d165acropped.png','2152189@tongji.edu.cn','2024-04-22 11:00:00','2024-04-24 10:00:01');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contributor`
--

DROP TABLE IF EXISTS `contributor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contributor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operator_id` int(11) NOT NULL COMMENT '使用者在user/admin库中的id',
  `admin` int(1) NOT NULL COMMENT '是否是管理员，bool值',
  `marine_species_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='哪个使用者贡献了哪条物种信息记录。默认每个marine_species_id的第一个为创建者，之后所有人都是贡献者';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contributor`
--

LOCK TABLES `contributor` WRITE;
/*!40000 ALTER TABLE `contributor` DISABLE KEYS */;
INSERT INTO `contributor` VALUES (1,1,0,1),(2,1,0,2),(3,1,1,3),(4,1,1,4),(5,1,1,5),(6,1,1,6),(7,1,0,7),(8,1,0,8),(9,1,0,9),(10,1,0,10),(11,1,0,11),(12,1,0,12),(13,1,0,13),(14,1,0,14),(15,1,0,15),(16,1,0,16),(17,1,1,1);
/*!40000 ALTER TABLE `contributor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `family`
--

DROP TABLE IF EXISTS `family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `family` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cn_name` varchar(32) NOT NULL,
  `en_name` varchar(32) DEFAULT NULL,
  `latin_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `family`
--

LOCK TABLES `family` WRITE;
/*!40000 ALTER TABLE `family` DISABLE KEYS */;
INSERT INTO `family` VALUES (1,'海星科','Asteriidae',NULL),(2,'海马科','Syngnathidae',NULL),(3,'鳐鱼科','Dasyatidae',NULL),(4,'乌贼科','Architeuthidae',NULL),(5,'鲸鱼科','Balaenopteridae',NULL),(6,'鲨鱼科','Lamnidae',NULL),(7,'章鱼科','Octopodidae',NULL),(8,'水母科','Cyaneidae',NULL),(9,'螃蟹科','Brachyuridae',NULL),(10,'龙虾科','Nephropidae',NULL),(11,'牡蛎科','Ostreidae',NULL),(12,'海葵科','Actiniidae',NULL),(13,'珊瑚科','Acroporidae',NULL),(14,'海胆科','Echinoidea',NULL),(15,'海螺科','Gastropoda',NULL),(16,'甲壳动物科','Crustacea',NULL),(17,'硬骨鱼科','Actinopterygii',NULL),(18,'软骨鱼科','Chondrichthyes',NULL),(19,'鲸鱼科','Balaenopteridae',NULL),(20,'剑鱼科','Xiphiidae',NULL),(21,'海牛科','Trichechidae',NULL),(22,'海豹科','Otariidae',NULL),(23,'刺鳐科','Dasyatidae',NULL),(24,'比目鱼科','Pleuronectidae',NULL),(25,'鳕鱼科','Gadidae',NULL),(26,'鲅鱼科','Scombridae',NULL),(27,'鲸鱼科','Cetacea',NULL),(28,'海带科','Laminariaceae',NULL),(29,'石藻科','Ulva',NULL),(30,'裙带菜科','Rhodomelaceae',NULL),(31,'saf',NULL,NULL);
/*!40000 ALTER TABLE `family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL,
  `content` varchar(256) NOT NULL,
  `upload_id` int(11) NOT NULL COMMENT '上传者一定是用户',
  `reply` varchar(256) DEFAULT NULL,
  `review_id` int(11) DEFAULT NULL COMMENT '审核者一定是管理员',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'asf','sfa',1,NULL,NULL,'2024-04-22 11:00:00','2024-04-22 11:00:00');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genus`
--

DROP TABLE IF EXISTS `genus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cn_name` varchar(32) NOT NULL,
  `en_name` varchar(32) DEFAULT NULL,
  `latin_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genus`
--

LOCK TABLES `genus` WRITE;
/*!40000 ALTER TABLE `genus` DISABLE KEYS */;
INSERT INTO `genus` VALUES (1,'海星属','Asterias',NULL),(2,'海马属','Hippocampus',NULL),(3,'鳐鱼属','Dasyatis',NULL),(4,'乌贼属','Architeuthis',NULL),(5,'鲸鱼属','Balaenoptera',NULL),(6,'鲨鱼属','Carcharodon',NULL),(7,'章鱼属','Octopus',NULL),(8,'水母属','Cyanea',NULL),(9,'螃蟹属','Cancer',NULL),(10,'龙虾属','Homarus',NULL),(11,'牡蛎属','Ostrea',NULL),(12,'海葵属','Actinia',NULL),(13,'珊瑚属','Acropora',NULL),(14,'鲇鱼属','Silurus',NULL),(15,'鳗鱼属','Anguilla',NULL),(16,'海胆属','Echinus',NULL),(17,'鹦鹉鱼属','Scarus',NULL),(18,'鳗鱼属','Anguilla',NULL),(19,'鳐鱼属','Myliobatis',NULL),(20,'海豹属','Phoca',NULL),(21,'鲸鱼属','Balaenoptera',NULL),(22,'螃蟹属','Cancer',NULL),(23,'鲨鱼属','Carcharodon',NULL),(24,'海豚属','Delphinus',NULL),(25,'海龟属','Chelonia',NULL),(26,'剑鱼属','Xiphias',NULL),(27,'鲅鱼属','Scomber',NULL),(28,'虎鲸属','Orcinus',NULL),(29,'鳗鱼属','Muraena',NULL),(30,'海獭属','Enhydra',NULL),(31,'海狮属','Otariidae',NULL),(32,'刺鳐属','Dasyatis',NULL),(33,'鳕鱼属','Gadus',NULL),(34,'比目鱼属','Hippoglossus',NULL),(35,'黄貂鱼属','Dasyatis',NULL),(36,'虎鲨属','Galeocerdo',NULL),(37,'鲍鱼属','Haliotis',NULL),(38,'鲸鱼属','Cetacea',NULL),(58,'海胆属','Echinoidea',NULL),(59,'沙鳐属','Dasyatidae',NULL),(60,'鲅鱼属','Scombridae',NULL),(61,'白鲸属','Delphinapterus leucas',NULL),(62,'虎鲸属','Orcinus orca',NULL),(63,'大黄蜂鱼属','Thunnus albacares',NULL),(64,'鳕鱼属','Gadidae',NULL),(65,'蟹属','Brachyura',NULL),(66,'海星属','Asteroidea',NULL),(67,'石斑鱼属','Epinephelinae',NULL),(68,'鲍鱼属','Haliotis',NULL),(69,'龙虾属','Homarus',NULL),(70,'海鳝属','Electrophorus',NULL),(71,'海豹属','Phocidae',NULL),(72,'锯鱼属','Pristidae',NULL),(73,'鳍足属','Pinnipedia',NULL),(74,'海牛属','Trichechus',NULL),(75,'海龟属','Chelonia mydas',NULL),(76,'海带属','Laminaria',NULL),(77,'海莴苣属','Ulva',NULL),(78,'菜花属','Caulerpa',NULL),(79,'箭鱼属',NULL,NULL),(80,'saf',NULL,NULL);
/*!40000 ALTER TABLE `genus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `marine_species_id` int(11) NOT NULL,
  `url` varchar(256) NOT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,1,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.png',0),(2,1,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.png',0),(3,1,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2f862605c8c64b72bd60d6565e6aad9f2.jpg',0),(4,2,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.jpg',0),(5,3,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/fce4fc10ae1e4718930c1e92afb25fbe7LI1UAL9}PSHL@[IR~KEVSK.png',0),(6,3,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/team/005225014a604375848e7d311c876678tmp_5fb4503c952903826c1a8b74cb83ce23a609b7fa3b243264.jpg',0),(7,111,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.jpg',0),(8,111,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/fce4fc10ae1e4718930c1e92afb25fbe7LI1UAL9}PSHL@[IR~KEVSK.png',0),(9,111,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2f862605c8c64b72bd60d6565e6aad9f2.jpg',0),(10,112,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.jpg',1),(11,112,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.png',1),(12,112,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/f15b74051f4043febd0dc9981d9635b5BV47TFKAYQQ$N)P3I))FPVU.jpg',1),(13,112,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/team/005225014a604375848e7d311c876678tmp_5fb4503c952903826c1a8b74cb83ce23a609b7fa3b243264.jpg',0),(14,113,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.jpg',0),(15,113,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/fce4fc10ae1e4718930c1e92afb25fbe7LI1UAL9}PSHL@[IR~KEVSK.png',0),(16,114,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/f15b74051f4043febd0dc9981d9635b5BV47TFKAYQQ$N)P3I))FPVU.jpg',0),(17,114,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/5a5e87e9827f41ffa095c7f0d3b913a251013a916caba385221275a09eddffc5e20a1902.jpg',0),(18,4,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/fc91d52938f7436aae8e58c7f6f319421.gif',0),(19,5,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/cd48673750eb4c37b55b2832c504fc202.jpg',0),(20,5,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.png',0),(21,5,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/f15b74051f4043febd0dc9981d9635b5BV47TFKAYQQ$N)P3I))FPVU.jpg',0),(22,14,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/team/005225014a604375848e7d311c876678tmp_5fb4503c952903826c1a8b74cb83ce23a609b7fa3b243264.jpg',0),(23,6,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/f15b74051f4043febd0dc9981d9635b5BV47TFKAYQQ$N)P3I))FPVU.jpg',0),(24,7,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/63c7410ed73842cbae4d340855d9db37M7}23W_3N`8P8NJD~2JJM9H.png',0),(25,8,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/fce4fc10ae1e4718930c1e92afb25fbe7LI1UAL9}PSHL@[IR~KEVSK.png',0),(26,9,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/team/005225014a604375848e7d311c876678tmp_5fb4503c952903826c1a8b74cb83ce23a609b7fa3b243264.jpg',0),(27,10,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/fc91d52938f7436aae8e58c7f6f319421.gif',0),(28,7,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/cd48673750eb4c37b55b2832c504fc202.jpg',0),(29,11,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.png',0),(30,9,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/team/005225014a604375848e7d311c876678tmp_5fb4503c952903826c1a8b74cb83ce23a609b7fa3b243264.jpg',0),(31,12,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/f15b74051f4043febd0dc9981d9635b5BV47TFKAYQQ$N)P3I))FPVU.jpg',0),(32,7,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/cd48673750eb4c37b55b2832c504fc202.jpg',0),(33,13,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.png',0),(34,14,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/63c7410ed73842cbae4d340855d9db37M7}23W_3N`8P8NJD~2JJM9H.png',0),(35,15,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/fce4fc10ae1e4718930c1e92afb25fbe7LI1UAL9}PSHL@[IR~KEVSK.png',0),(36,16,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/5a5e87e9827f41ffa095c7f0d3b913a251013a916caba385221275a09eddffc5e20a1902.jpg',0),(37,17,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.png',0),(38,17,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/team/005225014a604375848e7d311c876678tmp_5fb4503c952903826c1a8b74cb83ce23a609b7fa3b243264.jpg',0),(39,18,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/5a5e87e9827f41ffa095c7f0d3b913a251013a916caba385221275a09eddffc5e20a1902.jpg',0),(40,18,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/cd48673750eb4c37b55b2832c504fc202.jpg',0),(41,21,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.png',0),(42,19,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/63c7410ed73842cbae4d340855d9db37M7}23W_3N`8P8NJD~2JJM9H.png',0),(43,20,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/fc91d52938f7436aae8e58c7f6f319421.gif',0),(44,21,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/63c7410ed73842cbae4d340855d9db37M7}23W_3N`8P8NJD~2JJM9H.png',0),(45,21,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/fce4fc10ae1e4718930c1e92afb25fbe7LI1UAL9}PSHL@[IR~KEVSK.png',0),(46,19,'https://blossoms-garden.oss-cn-shanghai.aliyuncs.com/marine/f15b74051f4043febd0dc9981d9635b5BV47TFKAYQQ$N)P3I))FPVU.jpg',0),(47,115,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.jpg',0),(48,115,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.png',0),(49,115,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2f862605c8c64b72bd60d6565e6aad9f2.jpg',0),(50,116,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.jpg',0),(51,116,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2024-sakura-team-modal.png',0),(52,116,'https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/2f862605c8c64b72bd60d6565e6aad9f2.jpg',0);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kingdom`
--

DROP TABLE IF EXISTS `kingdom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kingdom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cn_name` varchar(32) NOT NULL,
  `en_name` varchar(32) DEFAULT NULL,
  `latin_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kingdom`
--

LOCK TABLES `kingdom` WRITE;
/*!40000 ALTER TABLE `kingdom` DISABLE KEYS */;
INSERT INTO `kingdom` VALUES (1,'动物界','Animalia',NULL),(2,'植物界','Plantae',NULL),(3,'原核生物界',NULL,NULL),(4,'asf',NULL,NULL);
/*!40000 ALTER TABLE `kingdom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marine_species`
--

DROP TABLE IF EXISTS `marine_species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marine_species` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cn_name` varchar(32) NOT NULL COMMENT '''中文名''',
  `en_name` varchar(32) DEFAULT NULL COMMENT '''英文名''',
  `latin_name` varchar(32) DEFAULT NULL COMMENT '''拉丁文名''',
  `morphology` varchar(256) DEFAULT NULL COMMENT '''形态描述''',
  `habitat` varchar(128) DEFAULT NULL COMMENT '''生境信息''',
  `feature` varchar(128) DEFAULT NULL COMMENT '''鉴别特征''',
  `kingdom_id` int(11) DEFAULT NULL COMMENT '界',
  `phylum_id` int(11) DEFAULT NULL COMMENT '''门''',
  `class_id` int(11) DEFAULT NULL COMMENT '纲',
  `order_id` int(11) DEFAULT NULL COMMENT '''目''',
  `family_id` int(11) DEFAULT NULL COMMENT '科',
  `genus_id` int(11) DEFAULT NULL COMMENT '属',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marine_species`
--

LOCK TABLES `marine_species` WRITE;
/*!40000 ALTER TABLE `marine_species` DISABLE KEYS */;
INSERT INTO `marine_species` VALUES (1,'海星','Starfish','Asteroidea','五臂放射状对称','海洋底栖','柔软身体，具有刺状结构',1,1,1,1,1,1,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(2,'海马','Seahorse','Hippocampus','长颈、弯曲尾巴','珊瑚礁','头部形状独特',1,1,2,2,1,2,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(3,'鳐鱼','Ray','Batoidea','扁平体形','海洋底部','宽阔的鳍',1,1,3,3,1,3,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(4,'大王乌贼','Giant Squid','Architeuthis','巨大体积，长触手','深海','大型触手',1,1,4,4,1,4,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(5,'鲸鱼','Whale','Balaenoptera','巨大体形，哺乳动物','深海','呼吸孔，尾部巨大',1,1,5,5,1,5,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(6,'鲨鱼','Shark','Carcharodon','流线型体形','海洋深处','锋利牙齿',1,1,6,6,1,6,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(7,'章鱼','Octopus','Octopus','八只触手','珊瑚礁','敏捷、变色',1,1,4,7,1,7,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(8,'水母','Jellyfish','Cnidaria','半透明伞状身体','海洋表层','有毒触手',1,2,8,8,1,8,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(9,'螃蟹','Crab','Brachyura','坚硬的甲壳','海洋边缘','大螯',1,2,9,9,2,9,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(10,'龙虾','Lobster','Homarus','长身，坚硬甲壳','海洋底栖','强壮的尾部',1,2,9,10,2,10,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(11,'牡蛎','Oyster','Ostreidae','坚硬的外壳','海洋底部','具有珍珠',1,2,10,11,3,11,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(12,'海葵','Anemone','Actiniaria','长触手','珊瑚礁','附着在岩石上',1,2,11,12,4,12,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(13,'珊瑚','Coral','Anthozoa','硬骨架','珊瑚礁','多种颜色',1,2,11,13,4,13,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(14,'鲇鱼','Catfish','Siluriformes','长须','淡水和海水','强烈味道',1,1,3,14,5,14,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(15,'鳗鱼','Eel','Anguilliformes','长身体','海洋和淡水','滑溜身体',1,1,3,15,6,15,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(16,'海胆','Sea Urchin','Echinoidea','球状体形，带刺','海洋底部','长刺',1,1,1,1,2,16,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(17,'鹦鹉鱼','Parrotfish','Scarinae','鲜艳颜色','珊瑚礁','硬喙',1,1,2,2,2,17,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(18,'大西洋鳐','Atlantic Ray','Raja','扁平体形','大西洋','扁平鳍',1,1,3,3,3,18,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(19,'大螯虾','Crayfish','Astacoidea','大螯','淡水','大螯',1,1,3,9,3,19,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(20,'大鲨鱼','Great White Shark','Carcharodon carcharias','巨型体积','深海','锋利牙齿',1,1,6,6,6,20,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(21,'短吻鳄','Alligator','Alligator','长嘴','沼泽','强力咬合',1,1,7,7,3,21,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(22,'蓝鲸','Blue Whale','Balaenoptera musculus','巨大的蓝色哺乳动物','深海','巨大呼吸孔',1,1,5,5,5,22,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(23,'海豹','Seal','Phocidae','软皮肤','极地区域','善于游泳',1,1,5,5,3,23,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(24,'海龟','Sea Turtle','Chelonioidea','壳体','热带海洋','坚硬外壳',1,1,7,7,3,24,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(25,'磷虾','Krill','Euphausia','小型甲壳类','海洋浮游','大量出现',1,1,9,9,4,25,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(26,'小丑鱼','Clownfish','Amphiprioninae','鲜艳橙色体形','珊瑚礁','与海葵共生',1,1,2,2,2,26,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(27,'魔鬼鱼','Manta Ray','Mobula','巨大扁平体形','热带海洋','翼状鳍',1,1,3,3,3,27,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(28,'红狮鱼','Lionfish','Pterois','长鳍刺','珊瑚礁','有毒',1,1,2,2,2,28,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(29,'刀鱼','Barracuda','Sphyraena','流线型体形','深海','锋利牙齿',1,1,2,2,2,29,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(30,'鲟鱼','Sturgeon','Acipenseridae','长身体','淡水和海水','坚硬鳍',1,1,3,3,3,30,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(31,'石斑鱼','Grouper','Epinephelinae','大型鱼类','珊瑚礁','强壮体型',1,1,2,2,2,31,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(32,'大剑鱼','Swordfish','Xiphias gladius','长剑状嘴','深海','高速游泳',1,1,3,3,3,32,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(33,'海象','Walrus','Odobenidae','巨大体型','极地区域','长獠牙',1,1,5,5,3,33,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(34,'鳗鲡','Moray Eel','Muraenidae','长身体','珊瑚礁','多彩花纹',1,1,3,3,3,34,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(35,'海狼鱼','Wolf Fish','Anarhichas','大型鳍','海洋底部','粗糙牙齿',1,1,3,3,3,35,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(36,'鲍鱼','Abalone','Haliotidae','坚硬壳体','海洋底部','多孔壳',1,1,4,4,4,36,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(37,'海龙','Seadragon','Phyllopteryx','长身体，奇异外形','珊瑚礁','独特形态',1,1,2,2,2,37,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(38,'海獭','Sea Otter','Enhydra','毛茸茸身体','海洋边缘','善于游泳',1,1,5,5,5,38,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(39,'海狮','Sea Lion','Otariidae','强壮体型','海洋边缘','敏捷',1,1,5,5,5,59,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(40,'鲷鱼','Snapper','Lutjanidae','厚实身体','热带海洋','强壮',1,1,3,3,3,60,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(41,'石头鱼','Stonefish','Synanceiidae','厚实体型','珊瑚礁','有毒',1,1,2,2,2,61,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(42,'水龙鱼','Dragonfish','Stomiidae','深海鱼类','深海区域','奇异外形',1,1,3,3,3,62,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(43,'金枪鱼','Tuna','Thunnus','流线型体形','深海','高速游泳',1,1,3,3,3,63,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(44,'魔鱼','Anglerfish','Lophiiformes','奇异外形','深海区域','独特诱饵',1,1,3,3,3,64,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(45,'梭鱼','Gar','Lepisosteidae','长嘴','淡水','坚硬鳍',1,1,3,3,3,65,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(46,'箭鱼sss','Swordfish','Xiphias gladius','长剑状嘴笑死我了','海洋中是个好地方','速度非常快',1,5,10,14,19,66,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(47,'海牛','Manatee','Trichechus','巨大的海洋哺乳动物','浅水','温和',1,5,12,21,20,67,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(48,'海狼','Sea Wolf','Anarhichas','大鳍','深海','大牙',1,5,10,18,21,68,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(49,'海狮','Sea Lion','Otariidae','毛茸茸的皮肤','海洋','灵活',1,5,12,19,20,69,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(50,'海獭','Sea Otter','Enhydra lutris','毛茸茸的皮肤','海洋','善游泳',1,5,12,19,21,70,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(51,'虎鲨','Tiger Shark','Galeocerdo cuvier','大鳍','热带海洋','带条纹',1,5,11,16,17,71,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(52,'大比目鱼','Halibut','Hippoglossus','扁平的体型','深海','大尾鳍',1,5,10,17,22,72,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(53,'鳍足类','Pinnipeds','Pinnipedia','毛皮','冰冷的海洋','游泳',1,5,12,21,22,73,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(54,'黄貂鱼','Stingray','Dasyatidae','扁平体型','热带海洋','带尾刺',1,5,11,18,23,74,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(55,'蓝鲨','Blue Shark','Prionace glauca','细长体型','深海','蓝色鳍',1,5,11,18,22,75,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(56,'海鳗','Moray Eel','Muraenidae','长身体','珊瑚礁','有毒',1,5,10,17,21,76,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(57,'章鱼','Octopus','Octopoda','多爪','深海','灵活',1,5,8,15,24,77,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(58,'海胆','Sea Urchin','Echinoidea','球状','海底','多刺',1,5,9,15,25,58,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(59,'沙鳐','Sand Ray','Dasyatidae','扁平体型','海洋','有尾刺',1,5,11,18,23,59,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(60,'鲅鱼','Mackerel','Scombridae','长身体','浅海','流线型',1,5,10,17,22,60,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(61,'白鲸','Beluga Whale','Delphinapterus leucas','白色','冰冷海域','巨大的呼吸孔',1,5,12,21,22,61,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(62,'虎鲸','Killer Whale','Orcinus orca','黑白条纹','深海','大型哺乳动物',1,5,12,21,22,62,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(63,'大黄蜂鱼','Yellowfin Tuna','Thunnus albacares','黄色鳍','深海','高速游泳',1,5,10,17,22,63,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(64,'鳕鱼','Cod','Gadidae','厚实的身体','冷海洋','大尾鳍',1,5,10,17,22,64,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(65,'蟹','Crab','Brachyura','多脚','浅海','坚硬壳体',1,5,9,17,23,65,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(66,'海星','Starfish','Asteroidea','星状','海底','多脚',1,5,9,15,25,66,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(67,'石斑鱼','Grouper','Epinephelinae','厚实体型','珊瑚礁','坚硬鳍',1,5,10,17,23,67,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(68,'鲍鱼','Abalone','Haliotis','圆形壳','海底','多孔',1,5,9,16,24,68,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(69,'龙虾','Lobster','Homarus','大鳌','浅海','坚硬壳体',1,5,9,17,23,69,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(70,'海鳝','Electric Eel','Electrophorus','长身体','淡水','电击能力',1,5,10,18,21,70,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(71,'海豹','Seal','Phocidae','软皮肤','极地区域','善游泳',1,5,12,21,22,71,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(72,'锯鱼','Sawfish','Pristidae','长锯状嘴','浅海','坚硬鳍',1,5,11,17,23,72,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(73,'鳍足类','Pinnipeds','Pinnipedia','厚实的皮肤','海洋','耐寒',1,5,12,21,22,73,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(74,'海牛','Manatee','Trichechus','厚实体型','浅海','温和',1,5,12,21,20,74,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(75,'海龟','Sea Turtle','Chelonia mydas','坚硬的壳','热带海洋','强壮',1,5,12,21,21,75,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(76,'大叶海藻','Giant Kelp','Macrocystis pyrifera','高大的褐藻','海洋中','细长的叶子',2,10,17,28,28,76,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(77,'海带','Kombu','Laminaria japonica','长长的叶子','海洋中','厚叶',2,10,17,28,28,77,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(78,'墨西哥波浪海藻','Mexican Wave Kelp','Nereocystis luetkeana','大大的海藻','海洋中','膨胀的茎',2,10,17,28,28,78,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(79,'藻栉藻','Alaria','Alaria esculenta','长且柔软','海洋中','宽叶',2,10,17,28,29,76,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(80,'黑紫海藻','Dulse','Palmaria palmata','深紫色','海洋中','柔软的',2,10,17,28,29,77,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(81,'岩藻','Rockweed','Fucus vesiculosus','暗绿色','岩石上','带气泡',2,11,18,28,29,78,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(82,'褐藻','Brown Algae','Sargassum','深褐色','漂浮','枝状',2,11,18,28,30,76,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(83,'绿藻','Green Algae','Ulva lactuca','明亮的绿色','海洋中','薄而宽',2,12,19,28,30,77,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(84,'海面藤','Sea Grapes','Caulerpa lentillifera','小而圆','海洋中','小圆球',2,12,19,28,30,78,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(85,'石花菜','Agar','Gelidium','红色的','海洋中','坚硬的',2,12,19,29,28,76,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(86,'海苔','Nori','Porphyra','深紫色','海洋中','薄且平',2,12,19,29,28,77,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(87,'红藻','Red Algae','Gracilaria','鲜红色','海洋中','多分枝',2,10,17,29,29,76,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(88,'鳄鱼藻','Crocodile Weed','Hydrilla verticillata','细长的','海洋中','狭长的叶片',2,12,19,29,28,76,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(89,'蕨藻','Sea Fern','Sargassum horneri','宽大','海洋中','多分枝',2,12,19,30,28,77,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(90,'龙须菜','Dragon Whiskers','Halophila ovalis','细长的','海洋中','细长的叶子',2,13,17,30,29,76,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(91,'马尾藻','Sargassum','Sargassum natans','深褐色','漂浮','枝状',2,13,17,30,29,77,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(92,'海松','Sea Pine','Asparagopsis','红色','海洋中','多分枝',2,13,17,30,29,78,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(93,'海灯笼','Sea Lantern','Codium fragile','绿色','海洋中','多分枝',2,13,17,31,28,76,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(94,'海藻','Sea Moss','Chondrus crispus','暗红色','海洋中','多分枝',2,13,17,31,28,77,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(95,'角叉藻','Horned Weed','Ceramium','红色','海洋中','细长的',2,14,18,31,29,76,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(96,'石花菜','Agar','Pterocladiella capillacea','深红色','海洋中','坚硬的',2,14,18,31,29,77,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(97,'微孔藻','Microalgae','Chlamydomonas reinhardtii','绿色','海洋中','单细胞',2,14,18,32,28,76,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(98,'海生藻','Sea Lettuce','Ulva prolifera','绿色','海洋中','薄且宽',2,14,18,32,28,77,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(99,'锥藻','Cone Weed','Acetabularia','绿色','海洋中','有锥形顶端',2,14,18,32,28,78,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(100,'海带','Kombu','Saccharina japonica','褐色','海洋中','厚叶',2,10,17,32,29,76,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(101,'小海藻','Little Seaweed','Phaeophyceae','褐色','海洋中','多分枝',2,13,19,32,29,77,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(102,'绿苔','Green Moss','Cladophora','绿色','海洋中','细长的',2,13,19,32,29,78,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(103,'海葵藻','Sea Anemone Algae','Anemonia','橙色','海洋中','触须',2,13,19,32,30,76,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(104,'羽藻','Feather Algae','Dasycladus','绿色','海洋中','多分枝',2,14,19,32,30,77,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(105,'海扁豆','Sea Buckthorn','Haloragis','橙色','海洋中','多分枝',2,14,19,32,30,78,'2024-04-22 11:00:00','2024-04-22 11:00:00'),(116,'箭鱼sssfafs','Swordfish','Xiphias gladius','长剑状嘴','海洋','速度快',1,3,2,14,5,1,'2024-04-23 20:02:01','2024-04-23 20:02:00');
/*!40000 ALTER TABLE `marine_species` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_permission`
--

DROP TABLE IF EXISTS `menu_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route_id` int(11) NOT NULL,
  `route_name` varchar(32) NOT NULL,
  `description` varchar(64) NOT NULL,
  `admin` tinyint(1) NOT NULL COMMENT '1表示管理员、0表示普通用户',
  `operator_id` int(11) NOT NULL COMMENT '操作者在对应表中的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_permission`
--

LOCK TABLES `menu_permission` WRITE;
/*!40000 ALTER TABLE `menu_permission` DISABLE KEYS */;
INSERT INTO `menu_permission` VALUES (1,1,'dashBoard','dashboard看板，包括2个页面',1,1),(2,2,'form','form表单，包括3个页面',1,1),(3,1,'dashBoard','dashboard看板，包括2个页面',0,1),(4,3,'list','list看板，包括4个页面',1,1),(6,4,'profile','profile看板，包括2个页面',1,1),(7,5,'result','result看板，包括2个页面',1,1),(8,6,'Account','Account看板，无下拉菜单',1,1),(9,6,'Account','Account看板，无下拉菜单',0,1),(10,7,'PlatformManagement','PlatformManagement看板，包括2个页面',1,1),(11,8,'UserActivity','UserActivity看板，包括3个页面',1,1),(12,9,'Encyclopedia','Encyclopedia看板，包括3个页面',1,1),(13,10,'AddSpecies','AddSpecies看板，只有1个页面，无下拉菜单',1,1),(14,11,'PostFeedback','PostFeedback看板，只有1个页面，无下拉菜单',1,1),(15,11,'PostFeedback','PostFeedback看板，只有1个页面，无下拉菜单',0,1),(16,9,'Encyclopedia','Encyclopedia看板，包括3个页面',0,1),(17,10,'AddSpecies','AddSpecies看板，只有1个页面，无下拉菜单',0,1);
/*!40000 ALTER TABLE `menu_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_class`
--

DROP TABLE IF EXISTS `my_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cn_name` varchar(32) NOT NULL,
  `en_name` varchar(32) DEFAULT NULL,
  `latin_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_class`
--

LOCK TABLES `my_class` WRITE;
/*!40000 ALTER TABLE `my_class` DISABLE KEYS */;
INSERT INTO `my_class` VALUES (1,'海星纲','Asteroidea',NULL),(2,'鱼纲','Actinopterygii',NULL),(3,'软骨鱼纲','Chondrichthyes',NULL),(4,'头足纲','Cephalopoda',NULL),(5,'哺乳动物纲','Mammalia',NULL),(6,'鳕鱼纲','Catfish',NULL),(7,'爬行纲','Alligatoridae',NULL),(8,'软体动物纲','Mollusca',NULL),(9,'甲壳动物纲','Crustacea',NULL),(10,'鱼纲','Actinopterygii',NULL),(11,'软骨鱼纲','Chondrichthyes',NULL),(12,'哺乳动物纲','Mammalia',NULL),(13,'硬骨鱼纲','Osteichthyes',NULL),(14,'软骨鱼纲','Chondrichthyes',NULL),(15,'软体动物纲','Mollusca',NULL),(16,'海洋哺乳动物纲','Marine Mammals',NULL),(17,'大型褐藻纲','Phaeophyceae',NULL),(18,'绿藻纲','Chlorophyceae',NULL),(19,'红藻纲','Rhodophyceae',NULL),(20,'saf',NULL,NULL);
/*!40000 ALTER TABLE `my_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_order`
--

DROP TABLE IF EXISTS `my_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cn_name` varchar(32) NOT NULL,
  `en_name` varchar(32) DEFAULT NULL,
  `latin_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_order`
--

LOCK TABLES `my_order` WRITE;
/*!40000 ALTER TABLE `my_order` DISABLE KEYS */;
INSERT INTO `my_order` VALUES (1,'海星目','Forcipulatida',NULL),(2,'海马目','Syngnathiformes',NULL),(3,'鳐鱼目','Myliobatiformes',NULL),(4,'乌贼目','Teuthida',NULL),(5,'鲸鱼目','Cetacea',NULL),(6,'鲨鱼目','Lamniformes',NULL),(7,'章鱼目','Octopoda',NULL),(8,'水母目','Scyphozoa',NULL),(9,'螃蟹目','Decapoda',NULL),(10,'龙虾目','Homarida',NULL),(11,'牡蛎目','Ostreoida',NULL),(12,'海葵目','Actiniaria',NULL),(13,'珊瑚目','Scleractinia',NULL),(14,'鲇鱼目','Siluriformes',NULL),(15,'海胆目','Echinoida',NULL),(16,'牡蛎目','Ostreoida',NULL),(17,'十足目','Decapoda',NULL),(18,'蛇目','Ophidia',NULL),(19,'翼龙目','Pterosauria',NULL),(20,'虎鲨目','Carcharhiniformes',NULL),(21,'鱼目','Perciformes',NULL),(22,'剑鱼目','Xiphioidea',NULL),(23,'海牛目','Sirenia',NULL),(24,'海豹目','Phocidae',NULL),(25,'鲇鱼目','Siluriformes',NULL),(26,'刺鳐目','Myliobatiformes',NULL),(27,'鳐鱼目','Rajiformes',NULL),(28,'褐藻目','Laminariales',NULL),(29,'绿藻目','Ulvales',NULL),(30,'红藻目','Gigartinales',NULL),(31,'多管藻目','Laminariales',NULL),(32,'隐藻目','Ulvales',NULL),(33,'fsa',NULL,NULL);
/*!40000 ALTER TABLE `my_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phylum`
--

DROP TABLE IF EXISTS `phylum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phylum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cn_name` varchar(32) NOT NULL,
  `en_name` varchar(32) DEFAULT NULL,
  `latin_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phylum`
--

LOCK TABLES `phylum` WRITE;
/*!40000 ALTER TABLE `phylum` DISABLE KEYS */;
INSERT INTO `phylum` VALUES (1,'棘皮动物门','Echinodermata',NULL),(2,'腔肠动物门','Cnidaria',NULL),(3,'脊椎动物门','Vertebrata',NULL),(4,'软体动物门','Mollusca',NULL),(5,'脊椎动物门','Vertebrata',NULL),(6,'环节动物门','Annelida',NULL),(7,'软体动物门','Mollusca',NULL),(8,'脊索动物门','Chordata',NULL),(9,'刺胞动物门','Cnidaria',NULL),(10,'红藻门','Rhodophyta',NULL),(11,'褐藻门','Phaeophyta',NULL),(12,'绿藻门','Chlorophyta',NULL),(13,'多管藻门','Ceramium',NULL),(14,'隐藻门','Cryptophyta',NULL),(15,'asf',NULL,NULL);
/*!40000 ALTER TABLE `phylum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `species_image`
--

DROP TABLE IF EXISTS `species_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `species_image` (
  `id` int(11) NOT NULL,
  `marine_species_id` int(11) NOT NULL,
  `url` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species_image`
--

LOCK TABLES `species_image` WRITE;
/*!40000 ALTER TABLE `species_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `species_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `organization_id` int(11) NOT NULL COMMENT '所归属的组织',
  `name` varchar(36) NOT NULL COMMENT '昵称',
  `password` varchar(45) NOT NULL,
  `description` varchar(128) DEFAULT NULL,
  `token` varchar(45) DEFAULT NULL,
  `avatar_url` varchar(256) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT '2024-04-22 11:00:00',
  `update_time` datetime DEFAULT '2024-04-22 11:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'Disney','forever25',NULL,'BC26AA5CF75383F8E83D0A0FEBB3FB8B','https://poby-tongji.oss-cn-shanghai.aliyuncs.com/static-img/jiren/team/2.jpg',NULL,'2024-04-22 11:00:00','2024-04-24 14:26:57');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-24 14:32:58
