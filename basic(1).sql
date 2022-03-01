/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.112
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 192.168.1.112:3306
 Source Schema         : basic

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 09/06/2021 15:44:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attachment_tbl
-- ----------------------------
DROP TABLE IF EXISTS `attachment_tbl`;
CREATE TABLE `attachment_tbl`  (
  `id` bigint(20) NOT NULL,
  `attachment_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `comments` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件说明',
  `user_id` bigint(255) NULL DEFAULT NULL COMMENT '用户id',
  `attachment_type` tinyint(3) NULL DEFAULT NULL COMMENT '附件分类',
  `attachment_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件地址',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `delete_flag` tinyint(3) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of attachment_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_tbl`;
CREATE TABLE `sys_permission_tbl`  (
  `id` bigint(20) NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission_tbl
-- ----------------------------
INSERT INTO `sys_permission_tbl` VALUES (1391228098789207110, '一级1', '一级1', 1);
INSERT INTO `sys_permission_tbl` VALUES (1391228098789207111, '一级2', '一级2', 1);
INSERT INTO `sys_permission_tbl` VALUES (1391228098789207112, '二级11', '二级11', 1391228098789207110);
INSERT INTO `sys_permission_tbl` VALUES (1391228098789207113, '二级12', '二级12', 1391228098789207110);
INSERT INTO `sys_permission_tbl` VALUES (1391228098789207114, '二级21', '二级21', 1391228098789207111);
INSERT INTO `sys_permission_tbl` VALUES (1391228098789207115, '三级111', '三级111', 1391228098789207112);
INSERT INTO `sys_permission_tbl` VALUES (1391228098789207116, '三级211', '三级211', 1391228098789207112);
INSERT INTO `sys_permission_tbl` VALUES (1391228098789207554, '/sysUser/list', '系统用户列表', NULL);

-- ----------------------------
-- Table structure for sys_role_permission_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_tbl`;
CREATE TABLE `sys_role_permission_tbl`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission_tbl
-- ----------------------------
INSERT INTO `sys_role_permission_tbl` VALUES (1391228090011207554, 1391228098789207554);

-- ----------------------------
-- Table structure for sys_role_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_tbl`;
CREATE TABLE `sys_role_tbl`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '状态',
  `creator_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_flag` tinyint(2) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_role_tbl
-- ----------------------------
INSERT INTO `sys_role_tbl` VALUES ('1391228090011207554', 'ROLE_admin', '管理员', 1, NULL, 1);

-- ----------------------------
-- Table structure for sys_user_role_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_tbl`;
CREATE TABLE `sys_user_role_tbl`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role_tbl
-- ----------------------------
INSERT INTO `sys_user_role_tbl` VALUES (1391228068781207554, 1391228090011207554);

-- ----------------------------
-- Table structure for sys_user_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_tbl`;
CREATE TABLE `sys_user_tbl`  (
  `id` bigint(20) NOT NULL,
  `unit_id` bigint(20) NULL DEFAULT NULL COMMENT '单位id',
  `department_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `delete_flag` tinyint(2) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_tbl
-- ----------------------------
INSERT INTO `sys_user_tbl` VALUES (1391228068781207554, NULL, NULL, '16619702102', 'admin', 'admin', '$2a$10$xPBThs3dLzCvM.oDXD1h4OXArBhHvfyuBd/VtQBTHLLET/goawv8u', '2021-06-08 15:27:35', '2021-06-08 15:27:35', 1);

-- ----------------------------
-- View structure for sys_role_permission_view
-- ----------------------------
DROP VIEW IF EXISTS `sys_role_permission_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `sys_role_permission_view` AS select `spt`.`id` AS `id`,`spt`.`url` AS `url`,`spt`.`name` AS `name`,`srpt`.`role_id` AS `role_id`,`spt`.`parent_id` AS `parent_id` from (`sys_permission_tbl` `spt` left join `sys_role_permission_tbl` `srpt` on((`spt`.`id` = `srpt`.`permission_id`)));

-- ----------------------------
-- View structure for sys_user_role_view
-- ----------------------------
DROP VIEW IF EXISTS `sys_user_role_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `sys_user_role_view` AS select `srt`.`id` AS `id`,`srt`.`role_name` AS `role_name`,`srt`.`role_desc` AS `role_desc`,`srt`.`status` AS `status`,`srt`.`creator_id` AS `creator_id`,`srt`.`delete_flag` AS `delete_flag`,`surt`.`user_id` AS `user_id` from (`sys_role_tbl` `srt` left join `sys_user_role_tbl` `surt` on((`srt`.`id` = `surt`.`role_id`)));

SET FOREIGN_KEY_CHECKS = 1;
