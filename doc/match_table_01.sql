/*
Navicat MySQL Data Transfer

Source Server         : 10.11.11.85
Source Server Version : 50544
Source Host           : 10.11.11.85:3306
Source Database       : crawler

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2016-01-19 15:00:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for match_table_01
-- ----------------------------
DROP TABLE IF EXISTS `match_table_01`;
CREATE TABLE `match_table_01` (
  `MATCH_DATE` date DEFAULT NULL COMMENT '创建时间',
  `CAT1_NAME` varchar(255) DEFAULT NULL COMMENT '类目一级名称',
  `CAT2_NAME` varchar(255) DEFAULT NULL COMMENT '类目二级名称',
  `CAT3_NAME` varchar(255) DEFAULT NULL COMMENT '类目三级名称',
  `YYW_ID` varchar(255) DEFAULT NULL COMMENT '药网商品编码',
  `COMP_NAME` varchar(255) DEFAULT NULL COMMENT '竞争对手商品名称',
  `YYW_NAME` varchar(200) DEFAULT NULL COMMENT '药网商品名称',
  `NORMS` varchar(100) DEFAULT NULL COMMENT '规格',
  `NEW_NORMS` varchar(100) DEFAULT NULL COMMENT '格式化规格',
  `YYW_PRICE` double(11,2) DEFAULT '0.00' COMMENT '药网销售价格',
  `COMP_PRICE` double(11,2) DEFAULT '0.00' COMMENT '竞争对手销售价格',
  `DIFF` double(11,2) DEFAULT '0.00' COMMENT '价格差',
  `DIFF_PER` double(11,2) DEFAULT '0.00' COMMENT '价格差比例',
  `PM_NUM` int(11) DEFAULT NULL COMMENT '销售数量',
  `URL` varchar(255) DEFAULT NULL COMMENT '链接地址'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
