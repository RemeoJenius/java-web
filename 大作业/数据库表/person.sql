/*
 Navicat MySQL Data Transfer

 Source Server         : jenius
 Source Server Version : 50627
 Source Host           : localhost
 Source Database       : webTest

 Target Server Version : 50627
 File Encoding         : utf-8

 Date: 04/22/2016 18:54:59 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userName` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码md5加密',
  `nickName` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `userType` tinyint(3) DEFAULT NULL COMMENT '类型，买家0，卖家1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `person`
-- ----------------------------
BEGIN;
INSERT INTO `person` VALUES ('0', 'buyer', '37254660e226ea65ce6f1efd54233424', 'buyer', '0'), ('1', 'seller', '981c57a5cfb0f868e064904b8745766f', 'seller', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
