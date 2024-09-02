/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : student_manage

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 02/09/2024 20:27:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exam_question
-- ----------------------------
DROP TABLE IF EXISTS `exam_question`;
CREATE TABLE `exam_question`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '描述',
  `score` float NOT NULL COMMENT '得分',
  `create_at` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `multiple_choice` blob NULL COMMENT '是否多选',
  `no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '题目编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_question
-- ----------------------------
INSERT INTO `exam_question` VALUES (1, '1+1等于多少', 0.5, '123', '2024-08-31 21:37:09', 0x30, '8月模拟卷');
INSERT INTO `exam_question` VALUES (2, '1*1等于多少', 1, '123', '2024-08-31 21:37:38', 0x30, '8月模拟卷');
INSERT INTO `exam_question` VALUES (3, '下列属于危险行为的是', 10, '123', '2024-08-31 21:38:27', 0x31, '8月模拟卷');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `label` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标签',
  `verbal_trick` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '话术',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for option
-- ----------------------------
DROP TABLE IF EXISTS `option`;
CREATE TABLE `option`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_id` int NULL DEFAULT NULL COMMENT '题目id',
  `option_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '选项',
  `answers` blob NULL COMMENT '是否是正确答案',
  `no` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `question_id`(`question_id` ASC) USING BTREE,
  CONSTRAINT `option_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `exam_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of option
-- ----------------------------
INSERT INTO `option` VALUES (1, 1, '2', 0x31, 'A');
INSERT INTO `option` VALUES (2, 1, '3', 0x30, 'B');
INSERT INTO `option` VALUES (3, 1, '1', 0x30, 'C');
INSERT INTO `option` VALUES (4, 1, '没有正确答案', 0x30, 'D');
INSERT INTO `option` VALUES (5, 2, '2', 0x30, 'A');
INSERT INTO `option` VALUES (6, 2, '3', 0x30, 'B');
INSERT INTO `option` VALUES (7, 2, '1', 0x31, 'C');
INSERT INTO `option` VALUES (8, 2, '0', 0x30, 'D');
INSERT INTO `option` VALUES (9, 3, '游泳', 0x30, 'A');
INSERT INTO `option` VALUES (10, 3, '打架', 0x31, 'B');
INSERT INTO `option` VALUES (11, 3, '闯红灯', 0x31, 'C');
INSERT INTO `option` VALUES (12, 3, '跑步', 0x30, 'D');
INSERT INTO `option` VALUES (13, 3, '唱《鸡你太美》', 0x31, 'E');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL,
  `authority` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名字',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色中文名',
  `sort_num` int NOT NULL COMMENT '排序',
  `enable` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否启用',
  `describe` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_sort_num_idx`(`sort_num` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_SUPER_ADMIN', '超级用户', 1, 't', NULL, '2024-05-14 22:04:03');
INSERT INTO `role` VALUES (2, 'ROLE_ADMIN', '管理用户', 2, 't', NULL, '2024-05-14 22:04:03');
INSERT INTO `role` VALUES (3, 'ROLE_USER', '普通用户', 3, 't', NULL, '2024-05-14 22:04:03');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `enable` blob NOT NULL COMMENT '是否启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tel` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '{bcrypt}$2a$10$oVSzGSlieTXrO2xmsqnegOwJJjVaIHhGRnZHm25yaiP0UvORJJn36', 0x74, '2023-04-27 23:06:51', NULL, NULL, NULL, '123');

-- ----------------------------
-- Table structure for user_option
-- ----------------------------
DROP TABLE IF EXISTS `user_option`;
CREATE TABLE `user_option`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户id',
  `option_id` int NOT NULL COMMENT '选项id',
  `create_time` timestamp NOT NULL COMMENT '做题时间',
  `question_id` int NOT NULL COMMENT '题目id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `user_option_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_option
-- ----------------------------
INSERT INTO `user_option` VALUES (3, 1, 2, '2024-08-31 21:56:06', 1);
INSERT INTO `user_option` VALUES (4, 1, 7, '2024-08-31 22:13:11', 2);
INSERT INTO `user_option` VALUES (5, 1, 10, '2024-08-31 22:13:33', 3);
INSERT INTO `user_option` VALUES (6, 1, 11, '2024-08-31 22:13:45', 3);
INSERT INTO `user_option` VALUES (7, 1, 13, '2024-08-31 22:13:50', 3);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_role_role_id_fkey`(`role_id` ASC) USING BTREE,
  INDEX `user_role_user_id_fkey`(`user_id` ASC) USING BTREE,
  CONSTRAINT `user_role_role_id_fkey` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_user_id_fkey` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
