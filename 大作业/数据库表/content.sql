/*
 Navicat MySQL Data Transfer

 Source Server         : jenius
 Source Server Version : 50627
 Source Host           : localhost
 Source Database       : webTest

 Target Server Version : 50627
 File Encoding         : utf-8

 Date: 04/22/2016 18:54:41 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `content`
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` bigint(20) DEFAULT NULL COMMENT '当前价格',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `icon` varchar(100) DEFAULT NULL,
  `abstract` varchar(200) DEFAULT NULL COMMENT '摘要',
  `text` blob COMMENT '正文',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `content`
-- ----------------------------
BEGIN;
INSERT INTO `content` VALUES ('1', '75', 'Head First Java', null, 'Java语言提供类、接口和继承等原语，为了简单起见，只支持类之间的单继承，但支持接口之间的多继承，并支持类与接口之间的实现机制（关键字为implements）。', null), ('2', '75', 'C#从入门到精通', null, 'C#是微软推出的一种基于.NET框架的、面向对象的高级编程语言。C#的发音为“C sharp”，模仿音乐上的音名“C♯”（C调升），是C语言的升级的意思。', null), ('3', '75', 'C语言从入门到精通', null, 'C语言的设计影响了众多后来的编程语言，例如C++、Objective-C、Java、C#等。', null), ('4', '75', 'Python基础教程', null, 'Python语法简洁清晰，特色之一是强制用空白符(white space)作为语句缩进。', null), ('5', '77', 'C++primer', null, 'C++是在C语言的基础上开发的一种面向对象编程语言，应用广泛；C++支持多种编程范式 －－面向对象编程、泛型编程和过程化编程。最新正式标准C++于2014年8月18日公布。', null), ('6', '77', '球球', null, '这是球哥的球球', null), ('32', '106', '大山', null, '大山', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
