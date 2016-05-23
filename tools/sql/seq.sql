/*
Navicat MySQL Data Transfer

Source Server         : 192.168.91.135_3306
Source Server Version : 50613
Source Host           : 192.168.91.135:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50613
File Encoding         : 65001

Date: 2016-05-19 11:23:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `sys_sequence`;
CREATE TABLE `sys_sequence` (
  `NAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `CURRENT_VALUE` int(11) NOT NULL DEFAULT '0',
  `INCREMENT` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_sequence
-- ----------------------------
INSERT INTO `sys_sequence` VALUES ('test', '28', '1');

-- ----------------------------
-- Function structure for `currval`
-- ----------------------------
DROP FUNCTION IF EXISTS `currval`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS int(11)
BEGIN
    DECLARE VALUE INTEGER;
    SET VALUE=0;
    SELECT current_value INTO VALUE
    FROM sys_sequence 
    WHERE NAME=seq_name;
    RETURN VALUE;
 END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `nextval`
-- ----------------------------
DROP FUNCTION IF EXISTS `nextval`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `nextval`(seq_name varchar(50)) RETURNS int(11)
BEGIN
     UPDATE sys_sequence
     SET CURRENT_VALUE = CURRENT_VALUE + INCREMENT
     where  name=seq_name;
     return currval(seq_name);
 END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `setval`
-- ----------------------------
DROP FUNCTION IF EXISTS `setval`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `setval`(seq_name varchar(50),value integer) RETURNS int(11)
BEGIN
     update sys_sequence 
     set current_value=value
     where name=seq_name;
     return currval(seq_name);
END
;;
DELIMITER ;
