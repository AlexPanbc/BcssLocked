/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : bcsslocked

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2016-10-09 15:01:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Type` int(11) NOT NULL,
  `Price` float NOT NULL,
  `Number` int(11) NOT NULL DEFAULT '0',
  `CreatedOn` datetime NOT NULL,
  `ModifiedOn` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '猫食', '2', '21.02', '2', '2016-10-04 14:18:02', '2016-10-04 14:18:02');

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype` (
  `id` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `CreatedOn` datetime NOT NULL,
  `ModifiedOn` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES ('1', '电子产品类', '2016-10-04 14:18:02', '2016-10-04 14:18:02');
INSERT INTO `goodstype` VALUES ('2', '鱼类', '2016-10-04 14:18:02', '2016-10-04 14:18:02');
INSERT INTO `goodstype` VALUES ('3', '猫类', '2016-10-04 14:18:02', '2016-10-04 14:18:02');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `UserName` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `GoodsId` int(11) NOT NULL,
  `GoodsName` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `Money` float NOT NULL,
  `CreateOn` datetime NOT NULL,
  `ModifiedOn` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Pass` varchar(255) CHARACTER SET latin1 NOT NULL,
  `Phone` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `Mailbox` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `CreatedOn` datetime NOT NULL,
  `ModifiedOn` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'pbc', '1', '110', 'liuhuichao@163.com', '2016-10-04 14:11:37', '2016-10-04 14:11:37');
INSERT INTO `userinfo` VALUES ('2', 'lhc', '1', '110', '2207221755@qq.com', '2016-10-04 14:11:37', '2016-10-04 14:11:37');
