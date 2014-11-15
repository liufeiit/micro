-- MySQL dump 10.13  Distrib 5.1.62, for Win32 (ia32)
--
-- Host: localhost    Database: ec_core
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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `fullname` varchar(32) NOT NULL,
  `location_id` int(10) unsigned NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `address` varchar(128) NOT NULL,
  `postcode` varchar(10) NOT NULL,
  `is_default` tinyint(1) NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(11) unsigned NOT NULL,
  `content` text NOT NULL,
  `gmt_created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `gmt_updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'<p>据香港媒体报导，黄晓明37岁生日(11月13日)，女友Angelababy相伴庆祝，上传 了两人对镜的自拍照，Angelababy负责拿相机因此只能拍到半边脸，黄晓明笑得十分甜蜜。黄晓明收到的生日蛋糕，有皇帝与皇后公子，再加上两个小孩 子，好像预告幸福家庭。黄晓明的好友纷纷祝贺，李冰冰祝他票房大卖，明年多拿几个影帝。林心如则催婚要快点儿喝到他的喜酒。</p>\r\n\r\n<p>黄晓明开心庆祝生日不忘做善事，他关注社会上容易走失的人群，所指是老人家、智障人士与小朋友。他先后在12个省市发放黄手环，是&ldquo;让爱回家 防走失黄手环&rdquo;，提醒大家关心身边有需要帮助的人。</p>\r\n\r\n<p><strong>剧照半裸露腹肌 </strong></p>\r\n\r\n<p>昨天(11月14日)，黄晓明他主演电影《撒娇女人最好命》剧照也同时曝光，半裸露腹肌的晓明，身形十分好。</p>\r\n','2014-11-15 14:58:18','2014-11-15 14:58:18'),(2,'<p>2014年6月4日，上海虹口区广灵五村，周围居民在事发现场外围观。当日13时左右，虹口区广中五村一处民宅内，一家三人被发现死于屋内。</p>\r\n\r\n<p>针对对部分银行对信用卡用户过度授信等违规行为，上海银监局开出240万罚单。</p>\r\n\r\n<p>上海银监局11月14日发布消息称，该局近日通过对辖内信用卡业务专项检查，决定对存在未依法审查申请人资料真实性、过度授信、异常交易管控不力等违规行为的7家商业银行，处以共计人民币240万元的罚款。</p>\r\n\r\n<p>根据该局同时发布的处罚决定书公示，涉及的银行分别是，中国银行上海分行和民生银行上海分行被罚款50万元；兴业银行信用卡中心、交通银行太平洋信用卡中心、工商银行上海分行、花旗银行被罚30万元；浦发银行信用卡中心20万元。且涉及到的客户有提及张XX和林XX。</p>\r\n\r\n<p>对 此，知情人士透露，此次处罚是此前上海三口无力坏还烧炭自杀事件的问责。据报道，2014年6月4日，上海市虹口区广中五村一户三口之家烧炭自杀，年过花 甲的张某和林某夫妇及其25岁的儿子无一幸免。警方排除他杀，根据报警人提供的遗书，死因疑为信用卡透支无力偿还。报道当时提及，这家人手上有十余张信用 卡，总透支额度达50多万，透支金额或被用于炒期货亏损。</p>\r\n\r\n<p>而除了开出罚单外，上海银监 局提出六点监管要求。针对市场上多起因信用卡欠款及其风险管理缺失引发潜在风险的事件，特别强调了授信一事，其中三条要求都是关于授信，包括&ldquo;不得以提高 总授信额度或设置限制性条件等形式来规避&lsquo;刚性扣减&rsquo;监管要求&rdquo;、&ldquo;防范因超需授信引发的过度透支或资金挪用风险&rdquo;以及&ldquo;给予持卡人临时授信额度期限一般 不超过1个月，将持卡人已使用的临时授信额度全额计入最低还款额&rdquo;等。</p>\r\n\r\n<p>所谓刚性扣减，就是说银行在给信用卡持卡人授信额度时，必须扣除他在其他银行已经获得的额度。比如，综合一个客户的多方条件，判断其可获得授信额度5万元。但该客户已经在其他银行获得了2万元的授信额度，则只能批准3万元额度。</p>\r\n\r\n<p>另外，加强对持卡人和收单商户的交易监测，完善现有监测规则，对异常交易行为进行风险调查，并及时采取相应管控措施等。</p>\r\n','2014-11-15 14:59:01','2014-11-15 14:59:01'),(3,'<p>日前，普通住房标准放款，这样的利好不仅仅在税费方面，之前的首套房贷标准也要求新增的一套住 宅必须是普通住房，从&ldquo;非普通&rdquo;到&ldquo;普通&rdquo;还牵涉到首付7成变3成，贷款利率从1.1倍降至基准。昨天多家银行信贷经理都表示，电话被打爆了，都是购房者 来咨询房贷的，甚至有此前为了买房离婚的，现在说还是复婚更划算。</p>\r\n\r\n<p>昨天，多家银行的信贷经理表示，前天政策一出，他们接到了一个又一个电话，都是询问何时能够享受首套房贷政策，甚至还有此前已经离婚的客户表示，现在准备复婚了，两口子一起申请房贷。</p>\r\n\r\n<p>某四大行信贷专员说：&ldquo;以前是离婚状态的，因为买了&lsquo;非普通&rsquo;的，它现在变成&lsquo;普通&rsquo;的了，他复婚了，公积金又可以多贷了。&rdquo;</p>\r\n\r\n<p>某中小银行信贷专员说：&ldquo;这种情况肯定存在，也不在少数，离婚是为了做首套，然后把财产重新分配一下，做到名下无房无贷，现在政策有贷款的也可以做成首套。&rdquo;</p>\r\n\r\n<p>还有此前已经签约的客户想要延后房贷，看看能否挤上新政班车，不过由于多数银行还没有出台细则，现在只能尽量延后签立贷款合同的时间。某中小银行信贷专员说：&ldquo;我们银行20号以后肯定没问题，这两天还是要问一问，现在真的，我们也没那么快反应。&rdquo;</p>\r\n\r\n<p>而对于购房者更为关心的贷款利率，目前上海各家银行仍以基准为主。业内人士认为，上海作为全国最有影响力的一线城市，房贷政策不会出现猛烈调整，大幅下调利率恐难实现。</p>\r\n','2014-11-15 14:59:43','2014-11-15 14:59:43'),(4,'<p>油价又跌了。昨日，国家发改委下发通知，从11月14日24时起，汽油下调190元/吨，柴 油下调180元/吨。这已是自7月以来成品油最高限价的第8次下调，也是国内油价首次&ldquo;8连跌&rdquo;。上海同步实行，92号汽油为6.73元/升，降0.15 元/升；95号汽油为7.16元/升，降0.16元/升；0号柴油为6.62元/升，降0.15元/升。</p>\r\n\r\n<p>据隆众石化统计，自7月21日起&ldquo;8连跌&rdquo;后，汽油累计平均下跌1.12元/升，柴油1.25元/升。隆众分析师薛群表示，此次油价下调以后，私家车的燃油费用继续下降，平均每行驶2000公里，将会比上半年减少180元左右的油费开支。</p>\r\n\r\n<p>另外，此次油价下调后，小型私家车每行驶1000公里费用可减少12元左右，大型的物流车辆每1000公里费用可减少50元左右。</p>\r\n\r\n<p>业内表示，本轮周期之内国际原油价格大幅度单边下行，亦为&ldquo;9连跌&rdquo;奠定基础。根据安迅思的调价模型测算，预计&ldquo;9连跌&rdquo;存较大可能性。根据现行成品油定价机制调价周期测算，11月28日24时将迎来下一轮汽柴油调价节点。</p>\r\n','2014-11-15 15:00:10','2014-11-15 15:00:10');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel`
--

DROP TABLE IF EXISTS `channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel` (
  `channel_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `alias` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`channel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel`
--

LOCK TABLES `channel` WRITE;
/*!40000 ALTER TABLE `channel` DISABLE KEYS */;
INSERT INTO `channel` VALUES (1,'电子邮件','email'),(2,'短信','sms');
/*!40000 ALTER TABLE `channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `content` (
  `content_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` enum('normal','ad','private','refer') NOT NULL DEFAULT 'normal',
  `title` varchar(255) NOT NULL,
  `content_category_id` int(11) NOT NULL,
  `status` enum('inactive','active','banned','deleted') NOT NULL DEFAULT 'inactive',
  `position` int(11) NOT NULL DEFAULT '0',
  `clicks` bigint(20) NOT NULL DEFAULT '0',
  `creator` bigint(20) NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`content_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
INSERT INTO `content` VALUES (1,'normal','37岁黄晓明庆生不忘做善事 Angelababy相伴左右',3,'active',0,0,1,'2014-11-15 14:58:18','2014-11-15 14:58:18'),(2,'normal','卡奴一家烧炭自杀续 7银行信用卡过度授信被罚',2,'banned',0,0,1,'2014-11-15 14:59:01','2014-11-15 14:59:01'),(3,'normal','首套房贷适用范围扩大 假离婚者如今要复婚',1,'deleted',0,0,1,'2014-11-15 14:59:43','2014-11-15 14:59:43'),(4,'normal','油价首迎“8连跌” 业内：“冲九”存较大可能',1,'inactive',0,0,1,'2014-11-15 15:00:10','2014-11-15 15:00:10');
/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content_category`
--

DROP TABLE IF EXISTS `content_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `content_category` (
  `content_category_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` text,
  `icon` varchar(255) DEFAULT NULL,
  `position` int(11) NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`content_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content_category`
--

LOCK TABLES `content_category` WRITE;
/*!40000 ALTER TABLE `content_category` DISABLE KEYS */;
INSERT INTO `content_category` VALUES (1,'两性话题','两性话题','两性话题',1,'2014-11-15 14:54:07','2014-11-15 14:54:07'),(2,'健康指南','健康指南','健康指南',2,'2014-11-15 14:54:32','2014-11-15 14:54:32'),(3,'正能量','正能量','正能量',3,'2014-11-15 14:54:46','2014-11-15 14:54:46');
/*!40000 ALTER TABLE `content_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content_history`
--

DROP TABLE IF EXISTS `content_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `content_history` (
  `content_history_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `content_id` int(11) NOT NULL,
  `ip` bigint(20) NOT NULL,
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`content_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content_history`
--

LOCK TABLES `content_history` WRITE;
/*!40000 ALTER TABLE `content_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `content_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content_media`
--

DROP TABLE IF EXISTS `content_media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `content_media` (
  `content_id` int(11) unsigned NOT NULL,
  `media_id` int(10) unsigned NOT NULL,
  `is_cover` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`content_id`,`media_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content_media`
--

LOCK TABLES `content_media` WRITE;
/*!40000 ALTER TABLE `content_media` DISABLE KEYS */;
/*!40000 ALTER TABLE `content_media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entity_type`
--

DROP TABLE IF EXISTS `entity_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entity_type` (
  `entity_type_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`entity_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entity_type`
--

LOCK TABLES `entity_type` WRITE;
/*!40000 ALTER TABLE `entity_type` DISABLE KEYS */;
INSERT INTO `entity_type` VALUES (1,'product'),(2,'content'),(3,'review');
/*!40000 ALTER TABLE `entity_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `location_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) unsigned NOT NULL DEFAULT '0',
  `name` varchar(64) NOT NULL,
  `pinyin` varchar(225) NOT NULL,
  `lat` decimal(10,6) DEFAULT NULL,
  `long` decimal(10,6) DEFAULT NULL,
  `is_listed` tinyint(1) NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_path`
--

DROP TABLE IF EXISTS `location_path`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location_path` (
  `location_id` int(10) unsigned NOT NULL,
  `path_id` int(10) unsigned NOT NULL,
  `level` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`location_id`,`path_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_path`
--

LOCK TABLES `location_path` WRITE;
/*!40000 ALTER TABLE `location_path` DISABLE KEYS */;
/*!40000 ALTER TABLE `location_path` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID序列',
  `name` varchar(256) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户昵称',
  `email` varchar(128) COLLATE utf8_unicode_ci NOT NULL COMMENT '电子邮件',
  `mobile` varchar(128) COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号码联系方式',
  `weixin` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '微信联系方式',
  `password` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录密码',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='管理员用户信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1,'Qian.CHEN','qian.chen@126.com','15255386280','qian20140714','woZhD5d0brZz-FEkAbMv8uOMxz4GVD4gZ2SRM3hpi20','2014-11-15 13:47:50','2014-11-15 13:47:50');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media` (
  `media_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `entity_type_id` int(10) unsigned NOT NULL,
  `url` varchar(255) NOT NULL,
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`media_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant`
--

DROP TABLE IF EXISTS `merchant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchant` (
  `merchant_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_name` varchar(128) NOT NULL,
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `icon` varchar(128) DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant`
--

LOCK TABLES `merchant` WRITE;
/*!40000 ALTER TABLE `merchant` DISABLE KEYS */;
INSERT INTO `merchant` VALUES (1,'默认商户',1,NULL,'2014-11-15 13:47:50','2014-11-15 13:47:50');
/*!40000 ALTER TABLE `merchant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant_role`
--

DROP TABLE IF EXISTS `merchant_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchant_role` (
  `merchant_role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`merchant_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant_role`
--

LOCK TABLES `merchant_role` WRITE;
/*!40000 ALTER TABLE `merchant_role` DISABLE KEYS */;
INSERT INTO `merchant_role` VALUES (1,'超级管理员','超级管理员',NULL,NULL),(2,'管理员','普通管理员',NULL,NULL),(3,'普通商户','普通商户',NULL,NULL);
/*!40000 ALTER TABLE `merchant_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant_setting`
--

DROP TABLE IF EXISTS `merchant_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchant_setting` (
  `merchant_id` int(10) unsigned NOT NULL,
  `path` varchar(255) NOT NULL,
  `data` text,
  `created` timestamp NULL DEFAULT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`merchant_id`,`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant_setting`
--

LOCK TABLES `merchant_setting` WRITE;
/*!40000 ALTER TABLE `merchant_setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `merchant_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant_user`
--

DROP TABLE IF EXISTS `merchant_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchant_user` (
  `merchant_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_id` int(10) unsigned NOT NULL,
  `merchant_role_id` int(10) unsigned NOT NULL,
  `store_id` int(10) unsigned NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `email` varchar(255) NOT NULL,
  `name` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `salt` char(6) NOT NULL,
  `created` timestamp NULL DEFAULT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  `last_login` timestamp NULL DEFAULT NULL,
  `last_ip` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`merchant_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant_user`
--

LOCK TABLES `merchant_user` WRITE;
/*!40000 ALTER TABLE `merchant_user` DISABLE KEYS */;
INSERT INTO `merchant_user` VALUES (1,1,1,0,1,'admin@test.com','admin','22fd30dad5400e32aaf59c66b5ac4e3a','123456',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `merchant_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `message_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `message_type_id` int(10) unsigned NOT NULL,
  `channel_id` int(11) NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `account` varchar(45) NOT NULL COMMENT 'email or mobile',
  `from_user_id` bigint(20) NOT NULL DEFAULT '0',
  `subject` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `is_read` tinyint(1) NOT NULL DEFAULT '0',
  `is_trashed` tinyint(1) NOT NULL DEFAULT '0',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `read` timestamp NULL DEFAULT NULL,
  `trashed` timestamp NULL DEFAULT NULL,
  `deleted` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_type`
--

DROP TABLE IF EXISTS `message_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_type` (
  `message_type_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`message_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_type`
--

LOCK TABLES `message_type` WRITE;
/*!40000 ALTER TABLE `message_type` DISABLE KEYS */;
INSERT INTO `message_type` VALUES (1,'register','注册',NULL),(2,'retrieve_passeord','找回密码',NULL),(3,'reset_password_success','重置密码成功',NULL);
/*!40000 ALTER TABLE `message_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preference`
--

DROP TABLE IF EXISTS `preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `preference` (
  `preference_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`preference_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preference`
--

LOCK TABLES `preference` WRITE;
/*!40000 ALTER TABLE `preference` DISABLE KEYS */;
/*!40000 ALTER TABLE `preference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site`
--

DROP TABLE IF EXISTS `site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site` (
  `site_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `ssl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site`
--

LOCK TABLES `site` WRITE;
/*!40000 ALTER TABLE `site` DISABLE KEYS */;
/*!40000 ALTER TABLE `site` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_setting`
--

DROP TABLE IF EXISTS `site_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_setting` (
  `site_setting_id` int(11) NOT NULL AUTO_INCREMENT,
  `site_id` int(11) NOT NULL,
  `group` varchar(45) NOT NULL,
  `key` varchar(45) NOT NULL,
  `value` varchar(255) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`site_setting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_setting`
--

LOCK TABLES `site_setting` WRITE;
/*!40000 ALTER TABLE `site_setting` DISABLE KEYS */;
INSERT INTO `site_setting` VALUES (1,0,'config','config_smtp_host','smtp.163.com',NULL,NULL),(2,0,'config','config_smtp_port','25',NULL,NULL),(3,0,'config','config_smtp_username','ec_java@163.com',NULL,NULL),(4,0,'config','config_smtp_password','abc123_',NULL,NULL),(5,0,'config','config_smtp_nickname','ec',NULL,NULL),(6,0,'config','config_front_page_limit','5',NULL,NULL),(7,0,'config','config_product_page_limit','10',NULL,NULL),(8,0,'config','config_review_images_limit','5',NULL,NULL),(9,0,'config','config_sms_uid','zing',NULL,NULL),(10,0,'config','config_sms_key','2f50a3c079f7eabe1687',NULL,NULL),(11,0,'ad_price','per_ip_price','0.01',NULL,NULL),(12,0,'ad_price','per_pv_price','0',NULL,NULL),(13,0,'ad_price','referee_award_percent','0.2',NULL,NULL),(14,0,'ad_price','activity_award_percent','0.2',NULL,NULL);
/*!40000 ALTER TABLE `site_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_group_id` int(11) NOT NULL,
  `status` enum('active','locked','banned','deleted') NOT NULL DEFAULT 'active',
  `email` varchar(255) DEFAULT NULL,
  `mobile` char(11) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `password` char(32) NOT NULL,
  `salt` char(6) NOT NULL,
  `fullname` varchar(64) DEFAULT NULL,
  `account_balance` decimal(10,2) unsigned NOT NULL DEFAULT '0.00',
  `is_email_verified` tinyint(1) NOT NULL DEFAULT '0',
  `is_mobile_verified` tinyint(1) NOT NULL DEFAULT '0',
  `client_ip` bigint(20) NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_login` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `last_ip` bigint(20) DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='<double-click to overwrite multiple objects>';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'active','test@test.com','15812345678','test','78794202cea9c66e26e85a395ac7e9e7','yEL0JM',NULL,'0.00',0,0,0,'2014-09-02 13:36:32','2014-09-02 13:36:32','2014-09-15 18:59:15',3232292387);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_confirmation`
--

DROP TABLE IF EXISTS `user_account_confirmation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_confirmation` (
  `account_type` enum('email','mobile') NOT NULL,
  `account` varchar(255) NOT NULL,
  `code` varchar(32) NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `is_confirmed` tinyint(1) NOT NULL DEFAULT '0',
  `is_expired` tinyint(1) NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `confirmed` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`account_type`,`account`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_confirmation`
--

LOCK TABLES `user_account_confirmation` WRITE;
/*!40000 ALTER TABLE `user_account_confirmation` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_account_confirmation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `user_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`user_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES (1,'normal','2014-09-02 04:37:00');
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_income`
--

DROP TABLE IF EXISTS `user_income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_income` (
  `user_id` bigint(20) unsigned NOT NULL,
  `year_month` char(7) NOT NULL,
  `u_ip` bigint(20) NOT NULL DEFAULT '0',
  `pv` bigint(20) NOT NULL DEFAULT '0',
  `referee_award` bigint(20) NOT NULL DEFAULT '0',
  `activity_award` varchar(45) NOT NULL DEFAULT '0',
  `total_income` decimal(15,4) NOT NULL DEFAULT '0.0000',
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`user_id`,`year_month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_income`
--

LOCK TABLES `user_income` WRITE;
/*!40000 ALTER TABLE `user_income` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_income` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_password_reset`
--

DROP TABLE IF EXISTS `user_password_reset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_password_reset` (
  `user_password_reset_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `is_reset` tinyint(1) NOT NULL DEFAULT '0',
  `is_expired` tinyint(1) NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `reset` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`user_password_reset_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_password_reset`
--

LOCK TABLES `user_password_reset` WRITE;
/*!40000 ALTER TABLE `user_password_reset` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_password_reset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_preference`
--

DROP TABLE IF EXISTS `user_preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_preference` (
  `user_id` bigint(20) unsigned NOT NULL,
  `preference_id` int(10) unsigned NOT NULL,
  `value` varchar(255) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`user_id`,`preference_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_preference`
--

LOCK TABLES `user_preference` WRITE;
/*!40000 ALTER TABLE `user_preference` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_preference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_referee`
--

DROP TABLE IF EXISTS `user_referee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_referee` (
  `user_id` bigint(20) unsigned NOT NULL,
  `referee_id` bigint(20) unsigned NOT NULL,
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_referee`
--

LOCK TABLES `user_referee` WRITE;
/*!40000 ALTER TABLE `user_referee` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_referee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_setting`
--

DROP TABLE IF EXISTS `user_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_setting` (
  `user_id` bigint(20) unsigned NOT NULL,
  `path` varchar(255) NOT NULL,
  `data` text,
  `created` timestamp NULL DEFAULT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`,`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_setting`
--

LOCK TABLES `user_setting` WRITE;
/*!40000 ALTER TABLE `user_setting` DISABLE KEYS */;
INSERT INTO `user_setting` VALUES (1,'alipay_account','15800000001',NULL,NULL),(1,'avatar','1.png',NULL,NULL),(1,'birthday','2000-12-22',NULL,NULL),(1,'gender','F',NULL,NULL),(1,'location','上海',NULL,NULL);
/*!40000 ALTER TABLE `user_setting` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-16  1:19:08
