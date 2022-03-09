/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : basic

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 09/03/2022 20:11:35
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
INSERT INTO `attachment_tbl` VALUES (1498559486284029954, '搜狗截图20220228194221.png', NULL, NULL, NULL, '2022/03/01/8d6119e632ac4187b26e8bd75e6c14d3.png', '2022-03-01 15:23:18', '2022-03-01 15:23:18', 1);
INSERT INTO `attachment_tbl` VALUES (1501181320410185730, 'springboot自动装装配.png', NULL, NULL, NULL, '2022/03/08/dd2689773e40430aa15a8860fe91b295.png', '2022-03-08 21:01:32', '2022-03-08 21:01:32', 1);

-- ----------------------------
-- Table structure for sys_log_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_tbl`;
CREATE TABLE `sys_log_tbl`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `execute_time` bigint(20) NULL DEFAULT NULL COMMENT '执行时间',
  `operation_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `operation_object` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作对象',
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类名',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log_tbl
-- ----------------------------
INSERT INTO `sys_log_tbl` VALUES (1501459777216061442, NULL, 120, '新建', '系统用户', 'com.horqian.basic.controller.SysUserTblController', 'add', NULL, '[{\"id\":\"1501459777081843714\",\"loginName\":\"tangqi\",\"password\":\"$2a$10$nyGETwk8TU2PsrZCKJdW9uwYcRySCtqw1zehn3Ya.NRcvUn.ayk8G\",\"userName\":\"唐琪\",\"createTime\":\"Mar 9, 2022 3:11:13 PM\",\"updateTime\":\"Mar 9, 2022 3:11:13 PM\"}]');

-- ----------------------------
-- Table structure for sys_permission_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_tbl`;
CREATE TABLE `sys_permission_tbl`  (
  `id` bigint(20) NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级id',
  `order_num` int(10) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission_tbl
-- ----------------------------
INSERT INTO `sys_permission_tbl` VALUES (1, NULL, '系统管理', 0, 1);
INSERT INTO `sys_permission_tbl` VALUES (2, NULL, '系统监控', 0, 2);
INSERT INTO `sys_permission_tbl` VALUES (3, NULL, '系统工具', 0, 3);
INSERT INTO `sys_permission_tbl` VALUES (100, '/sysUser', '用户管理', 1, 1);
INSERT INTO `sys_permission_tbl` VALUES (101, '/sysRole', '角色管理', 1, 2);
INSERT INTO `sys_permission_tbl` VALUES (102, '/sysPermission', '菜单（权利）管理', 1, 3);
INSERT INTO `sys_permission_tbl` VALUES (103, '/dept', '部门管理', 1, 4);
INSERT INTO `sys_permission_tbl` VALUES (1000, '/sysUser/list', '用户查询', 100, 1);
INSERT INTO `sys_permission_tbl` VALUES (1001, '/sysUser/add', '用户新增', 100, 2);
INSERT INTO `sys_permission_tbl` VALUES (1002, '/sysUser/update', '用户修改', 100, 3);
INSERT INTO `sys_permission_tbl` VALUES (1003, '/sysUser/remove', '用户删除', 100, 4);
INSERT INTO `sys_permission_tbl` VALUES (1004, '/sysUser/export', '用户导出', 100, 5);
INSERT INTO `sys_permission_tbl` VALUES (1005, '/sysUser/import', '用户导入', 100, 6);
INSERT INTO `sys_permission_tbl` VALUES (1006, '/sysUser/resetPwd', '重置密码', 100, 7);
INSERT INTO `sys_permission_tbl` VALUES (1007, '/sysRole/list', '角色查询', 100, 1);
INSERT INTO `sys_permission_tbl` VALUES (1008, '/sysRole/add', '角色新增', 100, 2);
INSERT INTO `sys_permission_tbl` VALUES (1009, '/sysRole/update', '角色修改', 100, 3);
INSERT INTO `sys_permission_tbl` VALUES (1010, '/sysRole/remove', '角色删除', 100, 4);
INSERT INTO `sys_permission_tbl` VALUES (1011, '/sysRole/export', '角色导出', 100, 5);
INSERT INTO `sys_permission_tbl` VALUES (1012, '/sysPermission/list', '菜单查询（权限）', 100, 1);
INSERT INTO `sys_permission_tbl` VALUES (1013, '/sysPermission/add', '菜单新增（权限）', 100, 2);
INSERT INTO `sys_permission_tbl` VALUES (1014, '/sysPermission/update', '菜单修改（权限）', 100, 3);
INSERT INTO `sys_permission_tbl` VALUES (1015, '/sysPermission/remove', '菜单删除（权限）', 100, 4);
INSERT INTO `sys_permission_tbl` VALUES (1016, 'dept/list', '部门查询', 100, 1);
INSERT INTO `sys_permission_tbl` VALUES (1017, 'dept/add', '部门新增', 100, 2);
INSERT INTO `sys_permission_tbl` VALUES (1018, 'dept/update', '部门修改', 100, 3);
INSERT INTO `sys_permission_tbl` VALUES (1019, 'dept/remove', '部门删除', 100, 4);
INSERT INTO `sys_permission_tbl` VALUES (1501513311303249922, 'string', 'string', 0, 0);
INSERT INTO `sys_permission_tbl` VALUES (1501513311961755649, 'string', 'string', 0, 0);
INSERT INTO `sys_permission_tbl` VALUES (1501513312540569601, 'string', 'string', 0, 0);

-- ----------------------------
-- Table structure for sys_role_permission_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_tbl`;
CREATE TABLE `sys_role_permission_tbl`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission_tbl
-- ----------------------------
INSERT INTO `sys_role_permission_tbl` VALUES (1391228090011207554, 1);
INSERT INTO `sys_role_permission_tbl` VALUES (1, 100);

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
INSERT INTO `sys_role_tbl` VALUES ('1', 'ROLE_user', '普通用户', 0, '1501447613088587777', 1);
INSERT INTO `sys_role_tbl` VALUES ('1391228090011207554', 'ROLE_admin', '管理员', 1, NULL, 1);

-- ----------------------------
-- Table structure for sys_user_role_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_tbl`;
CREATE TABLE `sys_user_role_tbl`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role_tbl
-- ----------------------------
INSERT INTO `sys_user_role_tbl` VALUES (1391228068781207554, 1391228090011207554);
INSERT INTO `sys_user_role_tbl` VALUES (1501447613088587777, 1391228090011207554);
INSERT INTO `sys_user_role_tbl` VALUES (1501459777081843714, 1);

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
INSERT INTO `sys_user_tbl` VALUES (1391228068781207554, NULL, NULL, '16619702102', 'admin', 'admin', '$2a$10$hIZq5J2uvpAOK.rQCU3Wxu36aYyL3t/s2Kwh29ddz4K5vwUMk6ukG', '2022-03-09 14:35:00', '2022-03-09 14:35:00', 1);
INSERT INTO `sys_user_tbl` VALUES (1501447613088587777, NULL, NULL, '15553523757', '唐帅', 'tangshuai', '$2a$10$LM462bD12qqYt7BnKeAJleapLhCdq7X2ZnwPnbAQ6smjJHSdbcjfG', NULL, NULL, 1);
INSERT INTO `sys_user_tbl` VALUES (1501459777081843714, NULL, NULL, '13888888888', '唐琪', 'tangqi', '$2a$10$nyGETwk8TU2PsrZCKJdW9uwYcRySCtqw1zehn3Ya.NRcvUn.ayk8G', '2022-03-09 15:28:43', '2022-03-09 15:28:43', 1);

-- ----------------------------
-- View structure for sys_role_permission_view
-- ----------------------------
DROP VIEW IF EXISTS `sys_role_permission_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `sys_role_permission_view` AS select `spt`.`id` AS `id`,`spt`.`url` AS `url`,`spt`.`name` AS `name`,`srpt`.`role_id` AS `role_id`,`spt`.`parent_id` AS `parent_id`,`spt`.`order_num` AS `order_num` from (`sys_permission_tbl` `spt` left join `sys_role_permission_tbl` `srpt` on((`spt`.`id` = `srpt`.`permission_id`)));

-- ----------------------------
-- View structure for sys_user_role_view
-- ----------------------------
DROP VIEW IF EXISTS `sys_user_role_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `sys_user_role_view` AS select `srt`.`id` AS `id`,`srt`.`role_name` AS `role_name`,`srt`.`role_desc` AS `role_desc`,`srt`.`status` AS `status`,`srt`.`creator_id` AS `creator_id`,`srt`.`delete_flag` AS `delete_flag`,`surt`.`user_id` AS `user_id` from (`sys_role_tbl` `srt` left join `sys_user_role_tbl` `surt` on((`srt`.`id` = `surt`.`role_id`)));

SET FOREIGN_KEY_CHECKS = 1;
