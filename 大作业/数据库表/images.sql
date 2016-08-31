/*
 Navicat MySQL Data Transfer

 Source Server         : jenius
 Source Server Version : 50627
 Source Host           : localhost
 Source Database       : webTest

 Target Server Version : 50627
 File Encoding         : utf-8

 Date: 04/22/2016 18:54:53 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `images`
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) DEFAULT NULL,
  `pname` varchar(100) DEFAULT NULL,
  `detail` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `images`
-- ----------------------------
BEGIN;
INSERT INTO `images` VALUES ('1', 'images/java.jpg', 'Head First Java', 'Java是一种可以撰写跨平台应用程序的面向对象的程序设计语言。Java 技术具有卓越的通用性、高效性、平台移植性和安全性，广泛应用于PC、数据中心、游戏控制台、科学超级计算机、移动电话和互联网，同时拥有全球最大的开发者专业社群。'), ('2', 'images/Csharp.jpg', 'C#从入门到精通', 'C#是一种安全的、稳定的、简单的、优雅的，由C和C++衍生出来的面向对象的编程语言。它在继承C和C++强大功能的同时去掉了一些它们的复杂特性（例如没有宏以及不允许多重继承）。C#综合了VB简单的可视化操作和C++的高运行效率，以其强大的操作能力、优雅的语法风格、创新的语言特性和便捷的面向组件编程的支持成为.NET开发的首选语言。'), ('3', 'images/C.jpg', 'C语言从入门到精通', '尽管C语言提供了许多低级处理的功能，但仍然保持着良好跨平台的特性，以一个标准规格写出的C语言程序可在许多电脑平台上进行编译，甚至包含一些嵌入式处理器（单片机或称MCU）以及超级电脑等作业平台。'), ('4', 'images/python.jpg', 'Python基础教程', 'Python具有丰富和强大的库。它常被昵称为胶水语言，能够把用其他语言制作的各种模块（尤其是C/C++）很轻松地联结在一起。常见的一种应用情形是，使用Python快速生成程序的原型（有时甚至是程序的最终界面），然后对其中[2]  有特别要求的部分，用更合适的语言改写，比如3D游戏中的图形渲染模块，性能要求特别高，就可以用C/C++重写，而后封装为Python可以调用的扩展类库。需要注意的是在您使用扩展类库时可能需要考虑平台问题，某些可能不提供跨平台的实现。'), ('5', 'images/Cpp.jpg', 'C++primer', 'C++这个名字是Rick Mascitti于1983年中所建议的，并于1983年12月首次使用。更早以前，尚在研究阶段的发展中语言曾被称为“new C”，之后是“C with Class”[3]  。在计算机科学中，C++仍被称为C语言的上层结构。它最后得名于C语言中的“++”操作符（其对变量的值进行递增）。而且在共同的命名约定中，使用“+”以表示增强的程序。Stroustrup说：“这个名字象征着源自于C语言变化的自然演进”。'), ('6', 'http://static.freepik.com/free-photo/soccer-vector-ball_642751.jpg', '球球', '这是球哥的球球'), ('32', 'http://pic29.nipic.com/20130509/2303927_133248745000_2.jpg', '大山', '大山');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
