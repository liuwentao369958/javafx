/*
SQLyog Professional v12.08 (64 bit)
MySQL - 8.0.18 : Database - dzcjk
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dzcjk` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `dzcjk`;

/*Table structure for table `dingdan` */

DROP TABLE IF EXISTS `dingdan`;

CREATE TABLE `dingdan` (
  `userid` int(20) NOT NULL,
  `dd_hao` int(30) NOT NULL,
  `dd_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `dd_type` varchar(8) NOT NULL,
  `dd_time` datetime DEFAULT NULL,
  `dd_jiege` int(20) DEFAULT NULL,
  `kehu_name` varchar(20) DEFAULT NULL,
  `kehu_phone` int(30) DEFAULT NULL,
  `dd_remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`dd_hao`),
  KEY `dingdan_userid_fk_user_userid` (`userid`),
  CONSTRAINT `dingdan_userid_fk_user_userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `dingdan` */

insert  into `dingdan`(`userid`,`dd_hao`,`dd_name`,`dd_type`,`dd_time`,`dd_jiege`,`kehu_name`,`kehu_phone`,`dd_remark`) values (1,5,'5','5','2020-12-17 10:28:17',5,'5',5,'5'),(1,6,'6','6','2020-12-17 10:28:27',6,'6',6,'6'),(1,7,'7','7','2020-12-17 10:28:37',7,'7',7,'7'),(1,8,'8','8','2020-12-17 10:28:46',8,'8',8,'8'),(1,9,'9','9','2020-12-17 10:28:56',9,'9',9,'9'),(1,11,'11','11','2020-12-17 10:29:09',11,'1',1,'1'),(1,33,'33','33','2020-12-18 15:53:51',1,'12',31232,'2131321'),(1,111,'11','11','2020-12-17 10:29:21',1,'1',111,'111'),(1,222,'22','22','2020-12-17 10:41:35',22,'22',22,'22'),(1,333,'33','33','2020-12-17 10:43:21',33,'33',333,'3333'),(1,342,'32423','3242','2020-12-18 15:54:14',4234,'342',2131,'432432');

/*Table structure for table `fa_package` */

DROP TABLE IF EXISTS `fa_package`;

CREATE TABLE `fa_package` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `packageno` varchar(50) DEFAULT NULL COMMENT '框号',
  `createtime` int(11) DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='框表';

/*Data for the table `fa_package` */

insert  into `fa_package`(`id`,`packageno`,`createtime`,`status`) values (11,'E20',1574910703,2),(12,'E30',1575016507,2),(13,'E40',1575016513,2),(14,'E50',1575016517,2),(15,'E60',1575016523,2),(16,'E70',1575016530,2),(17,'E80',1578990525,2),(18,'A01',1585102863,2),(19,'A02',1585102870,2);

/*Table structure for table `fa_package_record` */

DROP TABLE IF EXISTS `fa_package_record`;

CREATE TABLE `fa_package_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `operatid` int(10) DEFAULT NULL COMMENT '操作人',
  `parentno` varchar(50) DEFAULT NULL COMMENT '父框号',
  `childno` varchar(50) DEFAULT NULL COMMENT '子框号',
  `orderno` varchar(50) DEFAULT NULL COMMENT '订单号',
  `catid` int(10) DEFAULT NULL COMMENT '商品分类',
  `createtime` int(11) DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `type` tinyint(1) NOT NULL COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='框架历史记录';

/*Data for the table `fa_package_record` */

insert  into `fa_package_record`(`id`,`operatid`,`parentno`,`childno`,`orderno`,`catid`,`createtime`,`status`,`type`) values (12,2,'E20','E201','201904181003554986',71,1574930930,0,1),(21,2,'E70',NULL,'202003250925208694',71,1585102489,0,1),(22,2,'E60',NULL,'202003250925208694',71,1585102704,0,1),(23,2,'E50',NULL,'202003250925208694',71,1585102772,0,1),(24,2,'A01',NULL,'202003250925208694',71,1585102890,0,1),(25,2,'A02',NULL,'202003250925208694',71,1585102890,0,1),(30,2,'A01',NULL,'202003261558065712',62,1585209695,0,1),(31,2,'A02',NULL,'202003261558065712',62,1585209695,0,1),(32,2,'E70',NULL,'202003271016176134',65,1585275971,0,1),(33,2,'E80',NULL,'202003271016176134',65,1585275971,0,1),(34,2,'E60',NULL,'202005161113344573',198,1594866064,0,1);

/*Table structure for table `fa_purchase_record` */

DROP TABLE IF EXISTS `fa_purchase_record`;

CREATE TABLE `fa_purchase_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `orderno` varchar(50) DEFAULT NULL COMMENT '订单号',
  `operatid` int(10) DEFAULT NULL COMMENT '操作人',
  `createtime` int(11) DEFAULT NULL COMMENT '创建时间',
  `starttime` int(11) DEFAULT NULL COMMENT '种植时间',
  `markettime` int(11) DEFAULT NULL COMMENT '上市时间',
  `purchid` int(3) DEFAULT NULL COMMENT '采购点',
  `catid` int(10) DEFAULT NULL COMMENT '商品分类',
  `realamount` decimal(10,2) DEFAULT NULL COMMENT '结算重量',
  `realprice` decimal(5,2) DEFAULT NULL COMMENT '结算价格',
  `packageno` varchar(255) DEFAULT NULL COMMENT '框号',
  `type` tinyint(1) DEFAULT NULL COMMENT '分类',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `info` text NOT NULL COMMENT '信息溯源',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='采购点表';

/*Data for the table `fa_purchase_record` */

insert  into `fa_purchase_record`(`id`,`orderno`,`operatid`,`createtime`,`starttime`,`markettime`,`purchid`,`catid`,`realamount`,`realprice`,`packageno`,`type`,`status`,`info`) values (13,'202001080804035960',2,1578980739,1579009534,1577577600,141,69,'3.56','14.00',NULL,NULL,NULL,''),(25,'202003261535452438',2,1585209229,1585237996,1577577600,15,62,'2.62','1.00',NULL,NULL,NULL,''),(26,'202003261558065712',2,1585209628,1585238321,1577577600,15,62,'5.00','2.00','a:2:{i:0;s:3:\"A01\";i:1;s:3:\"A02\";}',NULL,NULL,''),(27,'202003271016176134',2,1585275913,1580552498,1585267200,15,65,'1.20','1.00','a:2:{i:0;s:3:\"E70\";i:1;s:3:\"E80\";}',NULL,NULL,''),(28,'202001080756207240',2,1585304440,1585333222,1577577600,15,72,'123.00','12.00',NULL,NULL,NULL,''),(29,'202001080756207240',2,1585304657,1585333432,1577577600,15,72,'123.00','12.00',NULL,NULL,NULL,''),(30,'202004101646153752',2,1586511033,1586539801,1577577600,15,192,'0.71','1.00',NULL,NULL,NULL,''),(32,'202005161113344573',2,1594865975,1594894512,1577577600,15,198,'0.18','1.00','a:1:{i:0;s:3:\"E60\";}',NULL,NULL,'');

/*Table structure for table `fa_supply_cai` */

DROP TABLE IF EXISTS `fa_supply_cai`;

CREATE TABLE `fa_supply_cai` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `orderno` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `uid` int(10) DEFAULT NULL COMMENT '发布人',
  `title` varchar(50) DEFAULT NULL COMMENT '供货名称',
  `category` varchar(50) DEFAULT NULL COMMENT '供货分类',
  `thumb` varchar(100) DEFAULT NULL COMMENT '商品图',
  `amount` int(10) DEFAULT '0' COMMENT '数量',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '单价',
  `total` decimal(10,2) DEFAULT '0.00' COMMENT '总价',
  `replyamount` int(10) DEFAULT '0' COMMENT '数量',
  `replyprice` decimal(10,2) DEFAULT '0.00' COMMENT '单价',
  `replytotal` decimal(10,2) DEFAULT '0.00' COMMENT '总价',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态',
  `createtime` int(11) DEFAULT NULL COMMENT '创建时间',
  `supplytime` varchar(20) DEFAULT NULL COMMENT '供货时间',
  `address` varchar(255) DEFAULT NULL COMMENT '供货地点',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `package` varchar(10) NOT NULL COMMENT '型号',
  `specification` varchar(50) DEFAULT NULL COMMENT '商品规格',
  `purchid` int(3) NOT NULL DEFAULT '0' COMMENT '采购点',
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderno_2` (`orderno`),
  KEY `orderno` (`orderno`),
  KEY `uid` (`uid`),
  KEY `category` (`category`),
  KEY `status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 COMMENT='农户采购表';

/*Data for the table `fa_supply_cai` */

insert  into `fa_supply_cai`(`id`,`orderno`,`uid`,`title`,`category`,`thumb`,`amount`,`price`,`total`,`replyamount`,`replyprice`,`replytotal`,`status`,`createtime`,`supplytime`,`address`,`remark`,`package`,`specification`,`purchid`) values (104,'202005221617538134',1878,'柑橘','195',NULL,36800,'0.42','15456.00',36800,'0.42','15456.00',1,1590135473,'2020-2-29','永顺县芙蓉镇新区社区',NULL,'斤','斤',15),(105,'202005221618339284',1878,'柑橘','195',NULL,34232,'0.44','15062.08',34232,'0.44','15062.08',0,1590135513,'2020-3-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(106,'202005221619032360',1878,'柑橘','195',NULL,21604,'0.46','9937.84',21604,'0.46','9937.84',0,1590135543,'2020-3-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(107,'202005221619389243',1878,'柑橘','195',NULL,250838,'0.50','125419.00',250838,'0.50','125419.00',0,1590135578,'2020-2-29','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(108,'202005221623093672',1878,'柑橘','195',NULL,119000,'0.52','61880.00',119000,'0.52','61880.00',0,1590135789,'2020-2-29','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(109,'202005221624082861',1878,'柑橘','195',NULL,170994,'0.53','90626.82',170994,'0.53','90626.82',0,1590135848,'2020-3-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(110,'202005221624397591',1878,'柑橘','195',NULL,32880,'0.54','17755.20',32880,'0.54','17755.20',0,1590135879,'2020-3-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(111,'202005221625092107',1878,'柑橘','195',NULL,311225,'0.56','174286.00',311225,'0.56','174286.00',0,1590135909,'2020-3-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(112,'202005221625461726',1878,'柑橘','195',NULL,40680,'0.58','23594.40',40680,'0.58','23594.40',0,1590135946,'2020-3-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(113,'202005221626171906',1878,'柑橘','195',NULL,103504,'0.59','61067.36',103504,'0.59','61067.36',0,1590135977,'2020-3-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(114,'202005221626468356',1878,'柑橘','195',NULL,115067,'0.60','69040.20',115067,'0.60','69040.20',0,1590136006,'2020-3-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(115,'202005221627169208',1878,'柑橘','195',NULL,12350,'0.62','7657.00',12350,'0.62','7657.00',0,1590136036,'2020-2-29','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(116,'202005221627451695',1878,'柑橘','195',NULL,9360,'0.68','6364.80',9360,'0.68','6364.80',0,1590136065,'2020-3-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(117,'202005221628196092',1878,'柑橘','195',NULL,32533,'0.70','22773.10',32533,'0.70','22773.10',0,1590136099,'2019-12-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(119,'202005221629272018',1878,'柑橘','195',NULL,16610,'0.82','13620.20',16610,'0.82','13620.20',0,1590136167,'2019-12-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(120,'202005221629576738',1878,'柑橘','195',NULL,55718,'0.83','46245.94',55718,'0.83','46245.94',0,1590136197,'2020-3-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(121,'202005221630249607',1878,'柑橘','195',NULL,37880,'0.85','32198.00',37880,'0.85','32198.00',0,1590136224,'2020-3-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(122,'202005221630574617',1878,'柑橘','195',NULL,75066,'0.86','64556.76',75066,'0.86','64556.76',0,1590136257,'2019-12-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(123,'202005221631242847',1878,'柑橘','195',NULL,6600,'0.90','5940.00',6600,'0.90','5940.00',0,1590136284,'2019-12-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(124,'202005221631517841',1878,'柑橘','195',NULL,50130,'0.94','47122.20',50130,'0.94','47122.20',0,1590136311,'2019-12-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(125,'202005221632204286',1878,'柑橘','195',NULL,42070,'0.97','40807.90',42070,'0.97','40807.90',0,1590136340,'2019-12-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(126,'202005221632474853',1878,'柑橘','195',NULL,9844,'1.00','9844.00',9844,'1.00','9844.00',0,1590136367,'2020-3-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(127,'202005221633151489',1878,'柑橘','195',NULL,8040,'1.20','9648.00',8040,'1.20','9648.00',0,1590136395,'2019-12-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(128,'202005221633448653',1878,'柑橘','195',NULL,2332,'1.26','2938.32',2332,'1.26','2938.32',0,1590136424,'2019-12-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(129,'202005221634117914',1878,'柑橘','195',NULL,24777,'1.70','42120.90',24777,'1.70','42120.90',0,1590136451,'2019-12-31','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(130,'202006301022449328',1877,'黄花菜','71',NULL,1,'1.00','1.00',1,'1.00','1.00',1,1593483764,'2020-6-30','保靖县清水坪清水坪居委会',NULL,'斤','1',15),(134,'202006301042012384',1878,'柑橘','195',NULL,12500,'0.42','5250.00',12500,'0.42','5250.00',4,1593484921,'2020-2-29','永顺县芙蓉镇新区社区',NULL,'斤','斤',85),(135,'202008261400367046',2666,'绿心猕猴桃','128',NULL,20000,'1.00','20000.00',20000,'1.00','20000.00',0,1598421636,'2020-8-31','龙山县兴隆街道太平山村',NULL,'斤','100',650),(136,'202011050938222160',3372,'本地肉鸭','168',NULL,3000,'8.00','24000.00',3000,'8.00','24000.00',0,1604540302,'2020-11-7','吉首市峒河街道峒河社区',NULL,'斤','10斤',838),(137,'202012021228484071',3994,'枞树菌','174',NULL,1,'1.00','1.00',1,'1.00','1.00',0,1606883328,'2020-12-2','保靖县清水坪清水坪居委会',NULL,'箱','好',15);

/*Table structure for table `fa_supply_gong` */

DROP TABLE IF EXISTS `fa_supply_gong`;

CREATE TABLE `fa_supply_gong` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `orderno` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `ordertype` tinyint(1) DEFAULT '1',
  `userid` int(10) DEFAULT NULL COMMENT '发布人',
  `title` varchar(50) DEFAULT NULL COMMENT '供货名称',
  `category` varchar(50) DEFAULT NULL COMMENT '供货分类',
  `thumb` varchar(100) DEFAULT NULL COMMENT '商品图',
  `amount` int(10) DEFAULT '0' COMMENT '数量',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '单价',
  `total` decimal(10,2) DEFAULT '0.00' COMMENT '总价',
  `replyamount` decimal(10,2) DEFAULT '0.00' COMMENT '数量',
  `replyprice` decimal(10,2) DEFAULT '0.00' COMMENT '单价',
  `replytotal` decimal(10,2) DEFAULT '0.00' COMMENT '总价',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态',
  `createtime` int(11) unsigned DEFAULT NULL COMMENT '创建时间',
  `supplytime` varchar(50) DEFAULT NULL COMMENT '供货时间',
  `address` varchar(255) DEFAULT NULL COMMENT '供货地点',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `package` varchar(10) NOT NULL COMMENT '型号',
  `specification` varchar(50) DEFAULT NULL COMMENT '商品规格',
  `purchid` int(3) DEFAULT '0' COMMENT '采购点',
  PRIMARY KEY (`id`),
  KEY `orderno` (`orderno`),
  KEY `uid` (`userid`),
  KEY `category` (`category`),
  KEY `status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=320 DEFAULT CHARSET=utf8 COMMENT='农户供货表';

/*Data for the table `fa_supply_gong` */

insert  into `fa_supply_gong`(`id`,`orderno`,`ordertype`,`userid`,`title`,`category`,`thumb`,`amount`,`price`,`total`,`replyamount`,`replyprice`,`replytotal`,`status`,`createtime`,`supplytime`,`address`,`remark`,`package`,`specification`,`purchid`) values (242,'201912041534586278',1,89,'椪柑','196','uploads/share/d4ff446a5c38fd1f60042ef4e52b7874.png',20000,'0.80','16000.00','20000.00','0.80','16000.00',5,1575444898,'2019-12-4','永顺县小溪镇长官居委会','已验收','斤','碰柑',141),(243,'201912041614522716',1,689,'椪柑','196','uploads/share/6ef7ca2de8f43af6b3b0418e323142ff.png',4000,'0.80','3200.00','4000.00','0.80','3200.00',2,1575447292,'2019-12-4','永顺县小溪镇长官居委会',NULL,'斤','碰柑',141),(247,'201912050952114735',1,298,'椪柑','196','uploads/share/355f074f08c539d9df4edc3d03c27ef0.png',40000,'1.00','40000.00','40000.00','1.00','40000.00',0,1575510731,'2019-12-5','永顺县小溪镇长官居委会',NULL,'斤','60~75',141),(253,'201912052120202643',1,243,'椪柑','196','uploads/share/674018038c2a6d024850c841a1675c5a.png',200000,'2.00','400000.00','200000.00','2.00','400000.00',0,1575552020,'2019-12-5','永顺县小溪镇集坪居委会',NULL,'箱','60',142),(255,'201912081930385724',1,184,'椪柑','196','uploads/share/37858afba34e66331266a52460df379a.png',25000,'1.50','37500.00','25000.00','1.50','37500.00',0,1575804638,'2019-12-8','永顺县小溪镇长官居委会',NULL,'个','60以上',141),(256,'201912102020427549',1,110,'椪柑','196','uploads/share/15bb9890405a58d41d9b3c2ac749d1a0.png',40000,'1.00','40000.00','40000.00','1.00','40000.00',0,1575980442,'2019-12-31','永顺县小溪镇集六村村委会',NULL,'箱','60',144),(258,'201912191918082178',1,149,'椪柑','196','uploads/share/9a48f2d34984a95662b88ac8a41285bf.png',50000,'1.20','60000.00','50000.00','1.20','60000.00',0,1576754288,'2019-12-26','永顺县小溪镇集六村村委会',NULL,'斤','60m',144),(259,'201912191922005472',1,149,'椪柑','196','uploads/share/13daaa6e60e31899638039254f2367b0.png',50000,'1.20','60000.00','50000.00','1.20','60000.00',0,1576754520,'2019-12-26','永顺县小溪镇集六村村委会',NULL,'斤','60m',144),(260,'201912252349582389',1,175,'椪柑','196','uploads/share/1bacf34c45413199e10287897205e4ce.png',500000,'1.35','675000.00','500000.00','1.35','675000.00',0,1577288998,'2019-12-25','永顺县小溪镇集坪居委会',NULL,'箱','60',142),(269,'202001162224336790',1,207,'椪柑','196','uploads/share/7b41b8109f8f6212ecf132ba62e08c53.png',50000,'0.80','40000.00','50000.00','0.30','40000.00',5,1579184673,'2020-1-16','保靖县清水坪清水坪居委会',NULL,'箱','6o起 步',15),(270,'202002071151298279',1,734,'黑猪','24','uploads/share/663d342dd27ffe653dea490753c02f54.png',10000,'18.00','180000.00','10000.00','18.00','180000.00',0,1581047489,'2020-2-7','凤凰县新场乡新场村 村委会',NULL,'','整猪',14),(271,'202002131134219861',1,734,'黑猪','24','uploads/share/b1e90850570605603732448621398884.png',1000,'18.00','18000.00','1000.00','18.00','18000.00',4,1581564861,'2020-2-13','保靖县清水坪清水坪居委会',NULL,'个','100',15),(287,'202004211249062046',1,134,'椪柑','196','uploads/share/0f311f36ada97298acec7c996c96d15f.png',100000,'1.00','100000.00','100000.00','1.00','100000.00',0,1587444546,'2020-11-1','永顺县小溪镇集六村村委会',NULL,'斤','55mm~85mm',144),(289,'202004211301122135',1,134,'红心猕猴桃','56','uploads/share/087bbfc8ed169be13974fa60ab6cabee.png',10000,'15.00','150000.00','10000.00','15.00','150000.00',0,1587445272,'2020-10-1','永顺县松柏镇龙头村 村委会',NULL,'斤','1两2起步',203),(290,'202004211303467860',1,134,'绿心猕猴桃','128','uploads/share/36ac97988d076308d44f0c852b49aee9.png',50000,'2.00','100000.00','50000.00','2.00','100000.00',0,1587445426,'2020-10-1','永顺县松柏镇龙头村 村委会',NULL,'斤','1两2起步',203),(291,'202004211305341985',1,134,'黄心猕猴桃','55','uploads/share/4eeb89b57f9517a047f7b0d3870d5eb6.png',10000,'5.00','50000.00','10000.00','5.00','50000.00',0,1587445534,'2020-10-1','永顺县松柏镇龙头村 村委会',NULL,'斤','1两2起步',203),(292,'202005161113344573',1,1804,'西瓜','198','uploads/share/25926304dfe051df646b2d173c941a99.png',60000,'1.00','60000.00','0.18','1.00','0.18',5,1589598814,'2020-5-16','保靖县清水坪清水坪居委会',NULL,'斤','8.5',15),(293,'202005261048261538',1,1928,'山药','89','uploads/share/dcdb0bd46004a82da0b5fadf243de14c.png',99999,'2.20','219997.80','99999.00','2.20','219997.80',0,1590461306,'2020-5-26','吉首市峒河街道峒河社区',NULL,'根','3-40',838),(294,'202005262056500162',1,1933,'土鸡（公）','165','uploads/share/d75f1735c1daf896874b5eeaa9dea4a5.png',10000,'150.00','1500000.00','10000.00','150.00','1500000.00',0,1590497810,'2020-5-26','保靖县清水坪清水坪居委会',NULL,'箱','羽',15),(295,'202005311517454719',1,1937,'土豆/洋芋','44','uploads/share/71123a17dbdf9e1aa5af0aec26dd60c9.png',60000,'0.85','51000.00','60000.00','0.85','51000.00',0,1590909465,'2020-5-31','保靖县清水坪清水坪居委会',NULL,'斤','2量以上',15),(296,'202005311845534783',1,1980,'折耳根','175','uploads/share/a91ac59e2e447315e19eb63eae40758e.png',800000,'6.50','5200000.00','800000.00','6.50','5200000.00',0,1590921953,'2020-5-31','凤凰县落潮井镇',NULL,'包','6-5/斤',0),(300,'202006101313387542',1,546,'柑橘','195','uploads/share/471a54a36d282b4502b824bb94e4039e.png',5000,'0.42','2100.00','5000.00','0.42','2100.00',1,1591766018,'2020-1-23','永顺县小溪镇长官居委会',NULL,'斤','5，5',141),(301,'202006101317105437',1,546,'柑橘','195','uploads/share/f25fbc08146308a8447659c251319502.png',50000,'1.00','50000.00','50000.00','1.00','50000.00',0,1591766230,'2020-12-30','永顺县小溪镇长官居委会',NULL,'斤','60以上',141),(302,'202006101552362503',1,2081,'柑橘','195','uploads/share/8477bfe44658bad8c00f56c0bc1e943a.png',5000,'0.42','2100.00','5000.00','0.42','2100.00',1,1591775556,'2019-12-24','永顺县小溪镇长官居委会',NULL,'斤','55',141),(306,'202006291511212451',1,1877,'黄花菜','71','uploads/share/17e0d42529ede8496dcaa37c45c8af8c.png',1,'2.00','2.00','1.16','2.00','2.32',5,1593414681,'2020-6-29','保靖县清水坪清水坪居委会',NULL,'个','2',15),(307,'202007072043032437',1,2296,'羊','164','uploads/share/bc0f0bbe10655c1c0e789a990a24c58b.png',180,'1880.00','338400.00','180.00','1880.00','338400.00',0,1594125783,'2020-7-7','吉首市太平镇太平村',NULL,'个','青年羊',959),(308,'202008091324250597',1,2538,'无籽西瓜','202','uploads/share/edf177062949368d1f69718023ee6976.png',100000,'10.00','1000000.00','100000.00','10.00','1000000.00',0,1596950665,'2020-8-9','保靖县清水坪清水坪居委会',NULL,'个','10',15),(309,'202008112141124360',1,2558,'黄心猕猴桃','55','uploads/share/c3ce0bf866c3ac3dc18a317fc0be55f0.png',3,'3.50','10.50','3.00','3.50','10.50',0,1597153272,'2020-8-31','保靖县清水坪清水坪居委会',NULL,'斤','徐香翠香猕猴桃。',15),(310,'202008130029444612',1,2558,'黄心猕猴桃','55','uploads/share/a8087c091e1355c0fa223a32fa082add.png',50000,'3.50','175000.00','50000.00','3.50','175000.00',0,1597249784,'2020-8-31','吉首市峒河街道峒河社区',NULL,'箱','猕猴桃',838),(311,'202008140716226987',1,2558,'绿心猕猴桃','128','uploads/share/7375f87c6720e20da69aba1494e15909.png',50000,'3.00','150000.00','50000.00','3.00','150000.00',0,1597360582,'2020-8-31','吉首市峒河街道峒河社区',NULL,'斤','纸箱',838),(312,'202008151849539471',1,2588,'土鸡（公）','165','uploads/share/8e2cdb8bf2fb728d8b1675c8eac15788.png',1000000,'8.50','8500000.00','1000000.00','8.50','85000000.00',0,1597488593,'2020-8-15','保靖县清水坪清水坪居委会',NULL,'斤','灵山土淹鸡，凤翔淹鸡',15),(314,'202009111602387953',1,2805,'黑猪','24','uploads/share/e50b521350b3c756937c37d547e6861d.png',80000,'3200.00','9999999.99','80000.00','3200.00','9999999.99',0,1599811358,'2020-9-11','保靖县清水坪清水坪居委会',NULL,'头','苏太',15),(316,'202010181327084520',1,3139,'山笋','178','uploads/share/1e4135ce092fe16b143f07293296e766.png',1000000,'4.00','4000000.00','1000000.00','4.00','4000000.00',0,1602998828,'2020-10-18','保靖县清水坪清水坪居委会',NULL,'','5/8寸',15),(317,'202010181329526904',1,3139,'蕨菜','177','uploads/share/90334bae577e167640920030097aba2b.png',100000,'2.00','200000.00','100000.00','2.00','200000.00',0,1602998992,'2020-10-18','保靖县清水坪清水坪居委会',NULL,'','您提',15),(318,'202010181609124691',1,3147,'金秋梨','208','uploads/share/002267cab4f954ffd96a70638cb17138.png',100000,'6.00','600000.00','100000.00','6.00','600000.00',0,1603008552,'2020-10-18','吉首市峒河街道峒河社区',NULL,'根','1',838),(319,'202011252014291832',1,3842,'青鱼','194','uploads/share/996d1fea88392debd448175468812296.png',10000,'115.00','1150000.00','10000.00','115.00','1150000.00',0,1606306469,'2020-11-25','保靖县清水坪清水坪居委会',NULL,'箱','两种规格，大的140元，小的115元净重40斤',15);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT '123',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`userid`,`username`,`password`) values (1,'11','22'),(2,'22','33'),(3,'33','44'),(4,'44','55'),(5,'44','123'),(6,'55','123');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
