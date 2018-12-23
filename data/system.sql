/*
Navicat MySQL Data Transfer

Source Server         : Student
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : task_train

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-01-14 19:40:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `station_id` int(11) NOT NULL AUTO_INCREMENT,
  `station_name` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`station_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of station
-- ----------------------------
INSERT INTO `station` VALUES ('1', '北京');
INSERT INTO `station` VALUES ('2', '上海');
INSERT INTO `station` VALUES ('3', '杭州');
INSERT INTO `station` VALUES ('4', '南京');
INSERT INTO `station` VALUES ('5', '西安');
INSERT INTO `station` VALUES ('6', '成都');
INSERT INTO `station` VALUES ('7', '广州');
INSERT INTO `station` VALUES ('8', '天津');
INSERT INTO `station` VALUES ('9', '重庆');
INSERT INTO `station` VALUES ('10', '辽宁');

-- ----------------------------
-- Table structure for train
-- ----------------------------
DROP TABLE IF EXISTS `train`;
CREATE TABLE `train` (
  `train_id` int(11) NOT NULL AUTO_INCREMENT,
  `train_name` varchar(255) NOT NULL,
  PRIMARY KEY (`train_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of train
-- ----------------------------
INSERT INTO `train` VALUES ('1', 'K123');
INSERT INTO `train` VALUES ('2', 'K456');
INSERT INTO `train` VALUES ('3', 'Z86');
INSERT INTO `train` VALUES ('4', 'G520');

-- ----------------------------
-- Table structure for train_station
-- ----------------------------
DROP TABLE IF EXISTS `train_station`;
CREATE TABLE `train_station` (
  `train_id` int(11) NOT NULL,
  `station_id` int(11) NOT NULL,
  `distance` int(11) NOT NULL,
  `arrive_time` time NOT NULL,
  `leave_time` time NOT NULL,
  PRIMARY KEY (`train_id`,`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of train_station
-- ----------------------------
INSERT INTO `train_station` VALUES ('1', '1', '0', '00:00:00', '00:00:00');
INSERT INTO `train_station` VALUES ('1', '2', '100', '22:00:00', '00:00:00');
INSERT INTO `train_station` VALUES ('1', '5', '400', '23:00:00', '24:00:00');
INSERT INTO `train_station` VALUES ('1', '7', '500', '03:00:00', '00:00:00');
INSERT INTO `train_station` VALUES ('1', '10', '700', '06:04:00', '09:04:00');
INSERT INTO `train_station` VALUES ('2', '2', '0', '10:04:00', '11:00:00');
INSERT INTO `train_station` VALUES ('2', '3', '150', '11:00:00', '12:00:00');
INSERT INTO `train_station` VALUES ('2', '7', '250', '00:00:00', '12:00:00');
INSERT INTO `train_station` VALUES ('2', '8', '350', '11:00:01', '11:00:00');
INSERT INTO `train_station` VALUES ('2', '10', '450', '22:00:00', '22:00:00');
INSERT INTO `train_station` VALUES ('3', '3', '0', '04:00:00', '15:37:36');
INSERT INTO `train_station` VALUES ('3', '4', '333', '04:00:00', '05:00:00');
INSERT INTO `train_station` VALUES ('3', '6', '444', '05:00:00', '04:00:00');
INSERT INTO `train_station` VALUES ('3', '8', '555', '11:00:00', '00:00:08');
INSERT INTO `train_station` VALUES ('3', '9', '666', '05:00:00', '05:00:00');
INSERT INTO `train_station` VALUES ('4', '3', '0', '00:00:00', '00:00:00');
INSERT INTO `train_station` VALUES ('4', '5', '123', '00:00:00', '00:00:00');
INSERT INTO `train_station` VALUES ('4', '6', '456', '00:00:00', '00:00:00');
INSERT INTO `train_station` VALUES ('4', '7', '789', '00:00:00', '00:00:00');
INSERT INTO `train_station` VALUES ('4', '9', '888', '00:00:00', '00:00:00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `admin` int(2) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2', '1', '0');
INSERT INTO `user` VALUES ('2', 'admin', 'admin', '1');
