/*
Navicat MySQL Data Transfer

Source Server         : 10.11.11.85
Source Server Version : 50544
Source Host           : 10.11.11.85:3306
Source Database       : crawler

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2016-01-19 17:47:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for medicine_info
-- ----------------------------
DROP TABLE IF EXISTS `medicine_info`;
CREATE TABLE `medicine_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `detailUrl` varchar(255) DEFAULT NULL,
  `site` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `guige` varchar(255) DEFAULT NULL,
  `changjia` varchar(255) DEFAULT NULL,
  `pzwh` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=332309 DEFAULT CHARSET=utf8;
