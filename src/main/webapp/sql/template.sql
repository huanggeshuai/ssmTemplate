/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : template

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 11/02/2019 20:18:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for function
-- ----------------------------
DROP TABLE IF EXISTS `function`;
CREATE TABLE `function`  (
  `function_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统功能id',
  `parent_id` int(11) DEFAULT NULL COMMENT '系统功能父id',
  `function_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '功能名称',
  `function_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '功能描述',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路径',
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '功能说明',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限代码',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标名称',
  `type` int(255) DEFAULT NULL COMMENT '0代表按钮\'button\' 1代表页面\'page\'',
  PRIMARY KEY (`function_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of function
-- ----------------------------
INSERT INTO `function` VALUES (1, 0, '1', '总目录', '无', '总目录', '', 'main', '', 1);
INSERT INTO `function` VALUES (12, 1, NULL, '', '无', '系统管理', NULL, 'sys', 'fa fa-bank', 1);
INSERT INTO `function` VALUES (13, 12, NULL, '', '/sys/fun/index.action', '功能管理', NULL, 'fun:main', 'fa fa-adn', 1);
INSERT INTO `function` VALUES (14, 13, NULL, '', '无', '功能管理增加按钮', NULL, 'fun:add', 'fa fa-clipboard', 0);
INSERT INTO `function` VALUES (15, 13, NULL, '', '无', '功能管理修改按钮', NULL, 'fun:edit', 'fa fa-anchor', 0);
INSERT INTO `function` VALUES (16, 13, NULL, '', '无', '功能管理删除按钮', NULL, 'fun:del', 'fa fa-connectdevelop', 0);
INSERT INTO `function` VALUES (17, 12, NULL, '', '/sys/role/index.action', '角色管理', NULL, 'role:main', 'fa fa-medkit', 1);
INSERT INTO `function` VALUES (19, 17, NULL, '', '无', '角色管理增加按钮', NULL, 'role:add', 'fa fa-eye', 0);
INSERT INTO `function` VALUES (20, 17, NULL, '', '无', '角色管理修改按钮', NULL, 'role:edit', 'fa fa-cab', 0);
INSERT INTO `function` VALUES (21, 17, NULL, '', '无', '角色管理删除按钮', NULL, 'role:delete', 'fa fa-diamond', 0);
INSERT INTO `function` VALUES (22, 17, NULL, '', '无', '角色管理授权', NULL, 'role:auth', 'fa fa-life-bouy', 0);
INSERT INTO `function` VALUES (23, 12, NULL, '', '/sys/user/index.action', '用户管理', NULL, 'user:main', 'fa fa-simplybuilt', 1);
INSERT INTO `function` VALUES (24, 23, NULL, '就是增加用户 的按钮', '无', '用户增加按钮', NULL, 'user:add', 'fa fa-th-large', 0);
INSERT INTO `function` VALUES (25, 23, NULL, '就是用户修改按钮', '无', '用户修改按钮', NULL, 'user:edit', 'fa fa-joomla', 0);
INSERT INTO `function` VALUES (26, 23, NULL, '就是删除用户的按钮', '无', '用户删除按钮', NULL, 'user:delete', 'fa fa-wordpress', 0);
INSERT INTO `function` VALUES (27, 23, NULL, '就是重置密码', '无', '重置密码', NULL, 'user:resetpassword', 'fa fa-android', 0);
INSERT INTO `function` VALUES (39, 1, NULL, '', '瑞特让他', '234', NULL, '委任为吕', 'fa fa-adn', 1);
INSERT INTO `function` VALUES (40, 39, NULL, '', 'ui与i', '768', NULL, '再', 'fa fa-500px', 0);
INSERT INTO `function` VALUES (42, 39, NULL, 'ertret', 'retret', 'ret', NULL, 'ret', 'fa fa-500px', 1);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `logid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'msg 操作信息',
  `exception` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储异常信息',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问的方法名称',
  `userid` int(255) NULL DEFAULT NULL COMMENT '操作人id',
  `operate_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储ip地址',
  PRIMARY KEY (`logid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `rold_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色标识',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '董事长', '全功能 董事长', 'chairman');
INSERT INTO `role` VALUES (2, '总经理', '全功能', 'general');
INSERT INTO `role` VALUES (3, '生产主管', '全功能', 'product');
INSERT INTO `role` VALUES (4, '物料库', '仅物料库表增删改查', 'material');
INSERT INTO `role` VALUES (5, '轧机主管', '仅轧机生产记录增加、删除、查询', 'roll');
INSERT INTO `role` VALUES (6, '剪切主管', '仅剪切生产记录增加、删除、查询', 'cut');
INSERT INTO `role` VALUES (7, '半成品库', '仅半成品库表crud', 'half-product');
INSERT INTO `role` VALUES (8, '退火', '仅退货单增加、修改、查询', 'backstove');
INSERT INTO `role` VALUES (9, '包装', '仅对剪切、退火审核', 'package');
INSERT INTO `role` VALUES (10, '成品库', '仅对成品库crud', 'finishproduct');
INSERT INTO `role` VALUES (11, '管理员', '管理员最厉害了', 'admin');
INSERT INTO `role` VALUES (124, '34', '24341111', '234');

-- ----------------------------
-- Table structure for role_function
-- ----------------------------
DROP TABLE IF EXISTS `role_function`;
CREATE TABLE `role_function`  (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `function_id` int(11) NOT NULL COMMENT '功能id',
  PRIMARY KEY (`role_id`, `function_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_function
-- ----------------------------
INSERT INTO `role_function` VALUES (1, 1);
INSERT INTO `role_function` VALUES (1, 12);
INSERT INTO `role_function` VALUES (1, 13);
INSERT INTO `role_function` VALUES (1, 14);
INSERT INTO `role_function` VALUES (1, 15);
INSERT INTO `role_function` VALUES (1, 16);
INSERT INTO `role_function` VALUES (1, 17);
INSERT INTO `role_function` VALUES (1, 19);
INSERT INTO `role_function` VALUES (1, 20);
INSERT INTO `role_function` VALUES (1, 21);
INSERT INTO `role_function` VALUES (1, 22);
INSERT INTO `role_function` VALUES (1, 23);
INSERT INTO `role_function` VALUES (1, 24);
INSERT INTO `role_function` VALUES (1, 25);
INSERT INTO `role_function` VALUES (1, 26);
INSERT INTO `role_function` VALUES (1, 27);
INSERT INTO `role_function` VALUES (1, 39);
INSERT INTO `role_function` VALUES (1, 40);
INSERT INTO `role_function` VALUES (1, 42);
INSERT INTO `role_function` VALUES (2, 1);
INSERT INTO `role_function` VALUES (2, 12);
INSERT INTO `role_function` VALUES (2, 13);
INSERT INTO `role_function` VALUES (2, 14);
INSERT INTO `role_function` VALUES (2, 15);
INSERT INTO `role_function` VALUES (2, 16);
INSERT INTO `role_function` VALUES (7, 1);
INSERT INTO `role_function` VALUES (7, 12);
INSERT INTO `role_function` VALUES (7, 17);
INSERT INTO `role_function` VALUES (7, 22);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id 主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户密码 加密的',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盐 混淆加密',
  `role_id` int(11) DEFAULT NULL COMMENT '角色编号',
  `user_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户头像',
  `user_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户颜色',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '102e5cb1ae154e6fbafd50a4e2216bb5b7baa84e8739e5ed2aa374f52688a245ac42bd5826a4edf40bc9bb2c5db85a5fbecd6213516160f6b8cb95da9ae6e63a', '93f1dc4c6d0f320a88ff21cbb260f578', 1, NULL, NULL);
INSERT INTO `user` VALUES (2, 'admin1', 'b7fc7af904fd89cf24e27b340283b37b7e3ddcacd2b31193f7c1d4673157ecf5162e47e867ae520786f26b5af49719f3eb717deff8a7d00b8b27d6a3a5e0c212', 'b0fa67b41fcca2f016374017831b78a6', 7, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
