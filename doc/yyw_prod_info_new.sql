/*
Navicat MySQL Data Transfer

Source Server         : 10.11.11.85
Source Server Version : 50544
Source Host           : 10.11.11.85:3306
Source Database       : crawler

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2016-01-19 17:48:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yyw_prod_info_new
-- ----------------------------
DROP TABLE IF EXISTS `yyw_prod_info_new`;
CREATE TABLE `yyw_prod_info_new` (
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `PROD_NAME` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `PROD_ID` varchar(20) DEFAULT NULL COMMENT '商品编码',
  `CAT1_NAME` varchar(255) DEFAULT NULL COMMENT '类目一级名称',
  `CAT2_NAME` varchar(255) DEFAULT NULL COMMENT '类目二级名称',
  `CAT3_NAME` varchar(255) DEFAULT NULL COMMENT '类目三级名称',
  `PM_NUM` int(11) DEFAULT NULL COMMENT '销售数量',
  `NORMS` varchar(100) DEFAULT NULL COMMENT '规格',
  `NEW_NORMS` varchar(100) DEFAULT NULL COMMENT '格式化规格',
  `APPROVALNUM` varchar(100) DEFAULT NULL COMMENT '批准文号',
  `STATUS` int(11) DEFAULT '0' COMMENT '状态 0禁用 4下架 8上架',
  `saleArea` int(11) DEFAULT '1' COMMENT '销售区域，每个商家有自己的销售区域 1.北京 2.上海 3.广州',
  `originalPrice` double(11,2) DEFAULT '0.00' COMMENT '销售价格'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
