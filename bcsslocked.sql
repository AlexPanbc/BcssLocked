/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : bcsslocked

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2016-10-20 23:06:50
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
INSERT INTO `goods` VALUES ('1', '猫食', '3', '21.02', '2', '2016-10-04 14:18:02', '2016-10-04 14:18:02');

-- ----------------------------
-- Table structure for goodsorder
-- ----------------------------
DROP TABLE IF EXISTS `goodsorder`;
CREATE TABLE `goodsorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `UserName` varchar(255) DEFAULT NULL,
  `GoodsId` int(11) NOT NULL,
  `GoodsName` varchar(255) DEFAULT NULL,
  `Money` float NOT NULL,
  `CreatedOn` datetime NOT NULL,
  `ModifiedOn` datetime NOT NULL,
  `Code` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `唯一索引` (`Code`) USING HASH COMMENT '订单号唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodsorder
-- ----------------------------
INSERT INTO `goodsorder` VALUES ('1', '1', 'pbc', '1', '猫食', '21.02', '2016-10-09 20:09:06', '2016-10-09 20:09:08', '1');
INSERT INTO `goodsorder` VALUES ('4', '1', '潘潘', '1', '猫食', '21.02', '2016-10-09 21:57:27', '2016-10-09 21:57:27', '2');
INSERT INTO `goodsorder` VALUES ('5', '0', '潘潘', '0', '猫食', '21.02', '2016-10-12 22:01:11', '2016-10-12 22:01:11', '3');
INSERT INTO `goodsorder` VALUES ('6', '0', '潘潘', '0', '猫食', '21.02', '2016-10-12 22:14:42', '2016-10-12 22:14:42', '4');
INSERT INTO `goodsorder` VALUES ('7', '0', '潘潘', '0', '猫食', '21.02', '2016-10-12 22:17:59', '2016-10-12 22:17:59', '5');

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
