# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 139.196.29.122 (MySQL 5.6.35)
# Database: exportgateway
# Generation Time: 2017-01-09 06:27:19 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table app
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app`;

CREATE TABLE `app` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` varchar(64) DEFAULT NULL COMMENT '内部系统id',
  `app_name` varchar(64) DEFAULT NULL COMMENT '内部系统名称',
  `token` varchar(64) DEFAULT NULL COMMENT '令牌',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态 0:失效 1:有效',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_person` varchar(60) DEFAULT NULL,
  `update_person` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_idx_app_id` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内部系统表';

LOCK TABLES `app` WRITE;
/*!40000 ALTER TABLE `app` DISABLE KEYS */;

INSERT INTO `app` (`id`, `app_id`, `app_name`, `token`, `status`, `create_time`, `update_time`, `create_person`, `update_person`)
VALUES
  (38,'20160819113025272298','carman','20160819113026382185',1,'2016-08-19 11:44:59','2016-08-19 11:44:59','system','system');

/*!40000 ALTER TABLE `app` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table app_method
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_method`;

CREATE TABLE `app_method` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` int(11) DEFAULT NULL COMMENT '内部应用id',
  `method_id` int(11) DEFAULT NULL COMMENT '方法id',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态 0:失效 1:有效',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_person` varchar(60) DEFAULT NULL,
  `update_person` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_idx_app_id_method_id` (`app_id`,`method_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内部系统关联接口调用表';

LOCK TABLES `app_method` WRITE;
/*!40000 ALTER TABLE `app_method` DISABLE KEYS */;

INSERT INTO `app_method` (`id`, `app_id`, `method_id`, `status`, `create_time`, `update_time`, `create_person`, `update_person`)
VALUES
  (2,38,12,1,'2016-08-19 11:59:17','2016-08-19 11:59:17','system','system');

/*!40000 ALTER TABLE `app_method` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table external_system
# ------------------------------------------------------------

DROP TABLE IF EXISTS `external_system`;

CREATE TABLE `external_system` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `en_name` varchar(64) DEFAULT NULL COMMENT '英文名',
  `zh_name` varchar(64) DEFAULT NULL COMMENT '中文名',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态 0:失效 1:有效',
  `rsa_private_key` varchar(2048) DEFAULT '' COMMENT 'rsa私钥',
  `rsa_public_key` varchar(2048) DEFAULT '' COMMENT 'rsa公钥',
  `md5_secret_key` varchar(128) DEFAULT '' COMMENT 'md5私钥',
  `sign_back` tinyint(1) DEFAULT NULL COMMENT '是否需要回签',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_person` varchar(60) DEFAULT NULL,
  `update_person` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_idx_en_name` (`en_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='外部系统表';

LOCK TABLES `external_system` WRITE;
/*!40000 ALTER TABLE `external_system` DISABLE KEYS */;

INSERT INTO `external_system` (`id`, `en_name`, `zh_name`, `status`, `rsa_private_key`, `rsa_public_key`, `md5_secret_key`, `sign_back`, `create_time`, `update_time`, `create_person`, `update_person`)
VALUES
  (13,'baidu_map','百度地图',1,'','','',0,'2016-08-19 11:52:24','2016-08-19 11:52:29','system','system');

/*!40000 ALTER TABLE `external_system` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table method
# ------------------------------------------------------------

DROP TABLE IF EXISTS `method`;

CREATE TABLE `method` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `system_id` int(11) DEFAULT NULL COMMENT '系统id',
  `name` varchar(128) DEFAULT NULL COMMENT '接口名称',
  `url` varchar(256) DEFAULT NULL COMMENT '接口url',
  `sign_type` varchar(32) DEFAULT NULL COMMENT '加签类型 rsa，md5，null',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态 0:失效 1:有效',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `invoke_type` tinyint(2) DEFAULT NULL COMMENT '调用类型 1:通用 2:自定义',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_person` varchar(60) DEFAULT NULL,
  `update_person` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_idx_system_id_name` (`system_id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口表';

LOCK TABLES `method` WRITE;
/*!40000 ALTER TABLE `method` DISABLE KEYS */;

INSERT INTO `method` (`id`, `system_id`, `name`, `url`, `sign_type`, `status`, `remark`, `invoke_type`, `create_time`, `update_time`, `create_person`, `update_person`)
VALUES
  (12,13,'com.baidu.map.api.geocoder.v2','http://api.map.baidu.com/geocoder/v2/','null',1,'百度地图api版本2',2,'2016-08-19 11:58:47','2016-08-19 12:01:01','system','system');

/*!40000 ALTER TABLE `method` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
