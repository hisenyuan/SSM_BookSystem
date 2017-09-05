-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: booksystem
-- ------------------------------------------------------
-- Server version	5.7.17-1

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `book_id` int(20) NOT NULL,
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_number` int(11) NOT NULL,
  `appointment_time` datetime NOT NULL,
  `expect_return_time` datetime NOT NULL,
  `real_return_time` datetime DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0 借出；1 已还；2 超时未还',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (103,1,20080808,'2017-08-02 11:37:24','2017-08-02 11:37:24','2017-08-03 18:22:32',1);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图书ID',
  `name` varchar(100) NOT NULL COMMENT '图书名称',
  `number` int(11) NOT NULL COMMENT '图书数量',
  `detail` varchar(200) NOT NULL COMMENT '图书描述',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2224 DEFAULT CHARSET=utf8 COMMENT='图书表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (103,'活着2',993,'描述2'),(104,'活着3',103,'描述3'),(105,'活着4',104,'描述4'),(106,'活着5',105,'描述5'),(107,'活着6',106,'描述6'),(108,'活着7',107,'描述7'),(109,'活着8',108,'描述8'),(110,'活着9',109,'描述9'),(111,'111',111,'111'),(113,'活着12',112,'描述12'),(114,'活着13',113,'描述13'),(115,'活着14',114,'描述14'),(116,'活着15',115,'描述15'),(117,'活着16',116,'描述16'),(118,'活着17',117,'描述17'),(119,'活着18',118,'描述18'),(120,'活着19',119,'描述19'),(121,'活着20',120,'描述20'),(122,'活着21',121,'描述21'),(123,'活着22',122,'描述22'),(124,'活着23',123,'描述23'),(125,'活着24',124,'描述24'),(126,'活着25',125,'描述25'),(127,'活着26',126,'描述26'),(128,'活着27',127,'描述27'),(129,'活着28',128,'描述28'),(130,'活着29',129,'描述29'),(131,'活着30',130,'描述30'),(132,'活着31',131,'描述31'),(133,'活着32',132,'描述32'),(134,'活着33',133,'描述33'),(135,'活着34',134,'描述34'),(136,'活着35',135,'描述35'),(137,'活着36',136,'描述36'),(138,'活着37',137,'描述37'),(139,'活着38',138,'描述38'),(140,'活着39',139,'描述39'),(141,'活着40',140,'描述40'),(142,'活着41',141,'描述41'),(143,'活着42',142,'描述42'),(144,'活着43',143,'描述43'),(145,'活着44',144,'描述44'),(146,'活着45',145,'描述45'),(147,'活着46',146,'描述46'),(148,'活着47',147,'描述47'),(149,'活着48',148,'描述48'),(150,'活着49',149,'描述49'),(151,'活着50',150,'描述50'),(152,'活着51',151,'描述51'),(153,'活着52',152,'描述52'),(154,'活着53',153,'描述53'),(155,'活着54',154,'描述54'),(156,'活着55',155,'描述55'),(157,'活着56',156,'描述56'),(158,'活着57',157,'描述57'),(159,'活着58',158,'描述58'),(160,'活着59',159,'描述59'),(161,'活着60',160,'描述60'),(162,'活着61',161,'描述61'),(163,'活着62',162,'描述62'),(164,'活着63',163,'描述63'),(165,'活着64',164,'描述64'),(166,'活着65',165,'描述65'),(167,'活着66',166,'描述66'),(168,'活着67',167,'描述67'),(169,'活着68',168,'描述68'),(170,'活着69',169,'描述69'),(171,'活着70',170,'描述70'),(172,'活着71',171,'描述71'),(173,'活着72',172,'描述72'),(174,'活着73',173,'描述73'),(175,'活着74',174,'描述74'),(176,'活着75',175,'描述75'),(177,'活着76',176,'描述76'),(178,'活着77',177,'描述77'),(179,'活着78',178,'描述78'),(180,'活着79',179,'描述79'),(181,'活着80',180,'描述80'),(182,'活着81',181,'描述81'),(183,'活着82',182,'描述82'),(184,'活着83',183,'描述83'),(185,'活着84',184,'描述84'),(186,'活着85',185,'描述85'),(187,'活着86',186,'描述86'),(188,'活着87',187,'描述87'),(189,'活着88',188,'描述88'),(190,'活着89',189,'描述89'),(191,'活着90',190,'描述90'),(192,'活着91',191,'描述91'),(193,'活着92',192,'描述92'),(194,'活着93',193,'描述93'),(195,'活着94',194,'描述94'),(196,'活着95',195,'描述95'),(197,'活着96',196,'描述96'),(198,'活着97',197,'描述97'),(199,'活着98',198,'描述98'),(200,'活着99',199,'描述99'),(201,'活着0',100,'描述0'),(202,'活着1',101,'描述1'),(203,'活着2',102,'描述2'),(204,'活着3',103,'描述3'),(205,'活着4',104,'描述4'),(206,'活着5',105,'描述5'),(207,'活着6',106,'描述6'),(208,'活着7',107,'描述7'),(209,'活着8',108,'描述8'),(210,'活着9',109,'描述9'),(222,'3333',6666,'5555'),(1234,'23',5464,'546'),(2223,'33333',66664,'55554');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `user_phone` varchar(11) CHARACTER SET utf8 NOT NULL,
  `user_address` varchar(255) CHARACTER SET utf8 NOT NULL,
  `user_number` varchar(11) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`user_id`,`user_number`),
  UNIQUE KEY `user_number` (`user_number`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'HiSEN','13820080808','1234','20080808'),(6,'阿星','13820080001','北京市朝阳区','20080001');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `gender` int(1) NOT NULL,
  `age` int(11) NOT NULL,
  `pwd` varchar(255) NOT NULL COMMENT '密码,MD5加密',
  `user_state` int(11) NOT NULL COMMENT '0:正常,1:锁定,2:封禁',
  `create_time` date NOT NULL COMMENT '创建时间',
  `last_time` date DEFAULT NULL COMMENT '上次登录时间',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `user_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'hisen',1,25,'28-80-51-21-1357-57-120-4292-821873-76-6107',0,'2017-08-27','2017-08-27','2017-08-27',0);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-06  0:00:08
