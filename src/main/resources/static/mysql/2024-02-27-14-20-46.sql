-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (arm64)
--
-- Host: localhost    Database: WebMusic
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `avatar`
--

DROP TABLE IF EXISTS `avatar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avatar` (
  `name` varchar(60) NOT NULL,
  `avatarUrl` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avatar`
--

LOCK TABLES `avatar` WRITE;
/*!40000 ALTER TABLE `avatar` DISABLE KEYS */;
INSERT INTO `avatar` VALUES ('root','root');
/*!40000 ALTER TABLE `avatar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banlance`
--

DROP TABLE IF EXISTS `banlance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banlance` (
  `username` varchar(20) NOT NULL,
  `banlance` int DEFAULT '0',
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banlance`
--

LOCK TABLES `banlance` WRITE;
/*!40000 ALTER TABLE `banlance` DISABLE KEYS */;
INSERT INTO `banlance` VALUES ('aaa',0),('root',15),('test',0),('爱玩',0);
/*!40000 ALTER TABLE `banlance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combo`
--

DROP TABLE IF EXISTS `combo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `combo` (
  `name` varchar(255) DEFAULT NULL,
  `price_now` int DEFAULT NULL,
  `price_origin` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combo`
--

LOCK TABLES `combo` WRITE;
/*!40000 ALTER TABLE `combo` DISABLE KEYS */;
INSERT INTO `combo` VALUES ('年付',180,216),('半年付',90,108),('季付',45,54),('月付',15,18);
/*!40000 ALTER TABLE `combo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentList`
--

DROP TABLE IF EXISTS `commentList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commentList` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `musicName` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `userName` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `commentContent` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentList`
--

LOCK TABLES `commentList` WRITE;
/*!40000 ALTER TABLE `commentList` DISABLE KEYS */;
INSERT INTO `commentList` VALUES (3,' Confess To You [www.slider.kz]','test','这是一首令人陶醉的歌曲，它将我带回了青春的时光。这首歌的旋律欢快而动听，节奏感强烈，让人忍不住跟着节拍摇摆起来。歌词中的回忆和情感充满了共鸣，勾起了我对过去岁月的回忆和思考。每一句歌词都像是一本珍贵的青春笔记，记录着我们曾经的懵懂、追求和成长。歌曲传递出积极向上的能量，让人对未来充满希望和勇气。无论是在校园里还是在生活中，这首歌都能激发我们的热情和活力，让我们相信青春是无限美好的。 Confess To You是一首让人欢乐的歌曲，它让我重新感受到了青春的魅力和力量。','2023-08-31 15:24:36'),(4,'战斗-通往胜利 (OST Armor Hero) [www.slider.kz]','root','是一首令人陶醉的抒情歌曲，它深深触动了我的心灵。这首歌的旋律悠扬而优美，充满了浓厚的情感色彩。歌词中的深情表达和意境描绘令人沉醉其中，勾起了我对爱情和追求的思考。每一句歌词都仿佛是一颗闪烁的星星，照亮着夜空中的黑暗，带给人希望和温暖。歌曲传递出对爱情的坚定执着和对未来的向往，让人感受到了爱的力量和美好。无论是在寂静的夜晚还是在繁忙的都市中，这首歌都能引领我们沉思和感悟，让我们相信爱是世界上最美的事物。','2023-08-31 15:26:01'),(5,' Confess To You [www.slider.kz]','aaaa','\"Confess to You\" 是一首令人心动的浪漫歌曲。它融合了温暖的旋律和动人的歌词，将爱情的甜蜜和渴望表达得淋漓尽致。歌曲中充满了柔情的音乐元素，令人陶醉其中。歌词中的告白和真诚让人感受到爱的力量和勇气。每一句歌词都如同一份深情的情书，将内心深处的感受倾诉而出。这首歌勾起了我对爱情的回忆和幻想，让我想要勇敢地向心爱的人表白。\"Confess to You\" 是一首令人心动的浪漫之歌，它将爱情的美好和情感的深度娓娓道来。','2023-08-31 15:27:05'),(6,' Confess To You [www.slider.kz]','放松一下吧','\"Confess to You\" 是一首充满活力的流行歌曲。它的节奏明快、旋律欢快，让人忍不住跟着节拍跳动。歌曲中充满了自信和积极的能量，让人感受到追逐梦想和实现目标的勇气。歌词中的自我宣言和勇敢面对挑战的精神激励着人们追逐自己的梦想。每一句歌词都充满了力量，让人们勇敢地面对生活的起伏和困难。这首歌让我感到振奋和鼓舞，激发了我追求梦想的动力。\"Confess to You\" 是一首充满活力和正能量的歌曲，它让人信心满满、充满动力。','2023-08-31 15:27:50'),(7,' Confess To You [www.slider.kz]','lll','\"Confess to You\" 是一首深情款款的抒情歌曲。它以柔美的旋律和温暖的歌声打动人心。歌曲中流露出浓郁的情感和对爱情的真挚表达。歌词中的告白和倾诉让人感受到爱的纯粹和执着。每一句歌词都仿佛是对心上人的深深告白，让人陶醉其中。这首歌勾起了我对爱情的思念和渴望，让我沉浸在甜蜜的情感中。\"Confess to You\" 是一首充满柔情和温暖的歌曲，它让我感受到爱的美好和心灵的共鸣。','2023-08-31 15:28:44'),(8,' Confess To You [www.slider.kz]','root','《Confess to You》은 감성적인 가사와 아름다운 멜로디로 마음을 사로잡는 한국어 곡입니다. 이 노래는 사랑의 진심과 솔직한 고백을 다루고 있으며, 따뜻한 감성을 전달합니다. 노래의 멜로디는 부드럽고 우아하며, 청취자를 마음의 여정으로 안내합니다. 가사 속의 고백과 진심은 사랑의 순수함과 열정을 느끼게 합니다. 각각의 가사는 마치 마음 속의 일기처럼 내면을 털어놓는 듯한 느낌을 주고 있습니다. 이 노래는 나에게 사랑에 대한 추억과 상상을 떠올리게 하며, 담백한 감정에 잠기게 합니다. 《Confess to You》는 감미로운 감성과 아름다움으로 가득한 곡으로, 사랑의 아름다움과 마음의 공감을 느끼게 합니다.','2023-08-31 17:05:43'),(9,' Confess To You','root','很好听,建议大家多听一下','2023-09-05 18:11:11'),(21,' Confess To You','root','5555555555555555555555555','2024-01-02 22:14:44');
/*!40000 ALTER TABLE `commentList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musicList`
--

DROP TABLE IF EXISTS `musicList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `musicList` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `musicName` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `singerName_album` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `timeLength` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `url` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musicList`
--

LOCK TABLES `musicList` WRITE;
/*!40000 ALTER TABLE `musicList` DISABLE KEYS */;
INSERT INTO `musicList` VALUES (1,' Confess To You','Lim Kim','3:07','1.mp3'),(2,'战斗-通往胜利 (OST Armor Hero)','陈致逸','3:15','2.mp3'),(3,'云深不知处','七音盒','3:02','3.mp3'),(4,'芒种 [www.slider.kz]','音阙诗听, 赵方婧','3:36','4.mp3'),(5,'Ditto ','NewJeans (뉴진스)','3:06','5.mp3'),(6,'Beautiful Now [www.slider.kz]','Zedd feat. Jon Bellion','3:38','6.mp3'),(7,'Die For You','ALORANT, Grabbitz','3:33','7.mp3'),(9,'We Never (Original Mix) ','Hi Noise','3:42','8.mp3'),(24,'New Boy','房东的猫','4:12','9.mp3'),(25,'红莲の弓矢','Reno','5:16','10.mp3'),(26,'这里是神奇的赛尔号','张杰','5:16','11.mp3'),(27,'周大侠','周杰伦','1:33','12.mp3'),(28,'喜欢','阿肆','4:10','13.mp3'),(29,'2022大串烧','Acaibird','2:52','14.mp3'),(30,'wake','Hillsong Young & Free','4:39','15.mp3'),(31,'赐我(DJ版本)','一只白羊','3:12','16.mp3'),(32,'危险派对','王以太，刘至佳','3:00','17.mp3'),(33,'My Song','John Doe','3:30','http://example.com/song.mp3');
/*!40000 ALTER TABLE `musicList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `active` int DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('root','775028',1,'lian.wk04201@foxmail.com',1),('test','000012',3,'user3@example.com',0),('test3','292929',4,'aa2a@qq.com',1),('shabi','2929929299',5,'emiail@email.com',1),('试试看','22222',6,'fadsfasf',0),('复活撒的','fdasfdasf24rt',7,'fsdQ#@fasf.com',1),('ok','fasd',8,'jiji@mail.com',1),('a','b',9,'c@a.com',1),('6','7777',10,'3.@a.com',1),('cao','fasd',11,'wu@.com',1),('ni','wei',12,'s@.com',0),('rou','7777',13,'fas@a.com',1),('rile','fasdfaf',14,'2@fasdfas.com',1),('meinv ','fads',16,'fasd',1),('shuaige','fdsa',17,'fasd',1),('shabia','fdsaf',18,'fdas',1),('root2','fasd',20,'afd',1),('roottest','775028',21,'new@i.com',1),('fasdfad543289523','fasfdsa',30,'fasdfas',1),('爱玩','fasfd',34,'发所发生的',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip`
--

DROP TABLE IF EXISTS `vip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vip` (
  `username` varchar(20) NOT NULL,
  `vipStatus` int DEFAULT '0',
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip`
--

LOCK TABLES `vip` WRITE;
/*!40000 ALTER TABLE `vip` DISABLE KEYS */;
INSERT INTO `vip` VALUES ('root',1,'2023-10-29','2024-01-29'),('test',1,NULL,NULL),('爱玩',0,NULL,NULL);
/*!40000 ALTER TABLE `vip` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-27 14:20:46
