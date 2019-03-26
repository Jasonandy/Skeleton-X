------------------------------------- 项目数据库DDL相关数据  by JASON 20190326 ------------------------------------------

-- 格式实例[规范化 方便以后的开发迭代 ]
-- 1.初始实例
-- 2.update更新等操作

-- example:
	-- describe + by author + modifited time

-----------------------------------------------init Data Start add  by JASON 20190326 ----------------------------------


-----------------------------------------------init Data end -----------------------------------------------------------

------------------------------------------------Update/Modifited something by @Author nowDate---------------------------


------------------------------------------------Update/Modifited end  --------------------------------------------------

------------------------------------------------CREATE TABLE BY JASON 20190326------------------------------------------
/*
Navicat MySQL Data Transfer

Source Server         : Ali
Source Server Version : 50640
Source Host           : 47.106.125.14:3306
Source Database       : skeleton

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-03-26 10:10:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(16) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
------------------------------------------------------------------------------------------------------------------------