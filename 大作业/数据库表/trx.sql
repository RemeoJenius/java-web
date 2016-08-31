/*
 Navicat MySQL Data Transfer

 Source Server         : jenius
 Source Server Version : 50627
 Source Host           : localhost
 Source Database       : webTest

 Target Server Version : 50627
 File Encoding         : utf-8

 Date: 04/22/2016 18:55:05 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `trx`
-- ----------------------------
DROP TABLE IF EXISTS `trx`;
CREATE TABLE `trx` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contentId` int(11) DEFAULT NULL COMMENT '内容ID',
  `personId` int(11) DEFAULT NULL COMMENT '用户ID',
  `price` int(11) DEFAULT NULL COMMENT '购买价格',
  `time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `trx`
-- ----------------------------
BEGIN;
INSERT INTO `trx` VALUES ('1', '1', '0', '75', '1460607985365'), ('2', '2', '0', '75', '1460696227260'), ('6', '6', '0', '77', '1461237557551'), ('11', '32', '0', '106', '1461320948048');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
