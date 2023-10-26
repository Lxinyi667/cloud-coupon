/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : coupon_db

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 08/09/2023 23:31:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `template_id` int NOT NULL DEFAULT '0' COMMENT '主键',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '拥有这张券的用户ID',
  `created_time` datetime NOT NULL DEFAULT '2023-09-08 20:00:00' COMMENT '领券时间',
  `status` int NOT NULL DEFAULT '0' COMMENT '优惠券的状态，比如未用，已用',
  `shop_id` bigint DEFAULT NULL COMMENT '冗余字段，方便查找',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_template_id` (`template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COMMENT='领到手的优惠券';

-- ----------------------------
-- Records of coupon
-- ----------------------------
BEGIN;
INSERT INTO `coupon` (`id`, `template_id`, `user_id`, `created_time`, `status`, `shop_id`) VALUES (1, 3, 1, '2023-09-08 23:02:43', 2, NULL);
INSERT INTO `coupon` (`id`, `template_id`, `user_id`, `created_time`, `status`, `shop_id`) VALUES (2, 2, 1, '2023-09-08 23:02:53', 3, NULL);
INSERT INTO `coupon` (`id`, `template_id`, `user_id`, `created_time`, `status`, `shop_id`) VALUES (3, 1, 1, '2023-09-08 23:02:56', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for coupon_template
-- ----------------------------
DROP TABLE IF EXISTS `coupon_template`;
CREATE TABLE `coupon_template` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `available` tinyint(1) NOT NULL DEFAULT '0' COMMENT '优惠券可用状态',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '优惠券名称',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '优惠券详细信息',
  `type` varchar(10) NOT NULL DEFAULT '' COMMENT '优惠券类型，比如满减、随机立减、晚间双倍等等',
  `shop_id` bigint DEFAULT NULL COMMENT '优惠券适用的门店，如果是空则代表全场适用',
  `created_time` datetime NOT NULL DEFAULT '2023-09-08 20:00:00' COMMENT '创建时间',
  `rule` varchar(2000) NOT NULL DEFAULT '' COMMENT '详细的使用规则',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COMMENT='优惠券模板';

-- ----------------------------
-- Records of coupon_template
-- ----------------------------
BEGIN;
INSERT INTO `coupon_template` (`id`, `available`, `name`, `description`, `type`, `shop_id`, `created_time`, `rule`) VALUES (1, 1, '全场满10减1元', '满减券描述，每人限制最多10张', '1', NULL, '2023-09-08 22:59:25', '{\"discount\":{\"quota\":10,\"threshold\":1000},\"limitation\":10}');
INSERT INTO `coupon_template` (`id`, `available`, `name`, `description`, `type`, `shop_id`, `created_time`, `rule`) VALUES (2, 1, '晚间双倍立减券', '满50随机立减最多5元，晚间减10元', '4', NULL, '2023-09-08 22:59:46', '{\"discount\":{\"quota\":500,\"threshold\":5000},\"limitation\":10}');
INSERT INTO `coupon_template` (`id`, `available`, `name`, `description`, `type`, `shop_id`, `created_time`, `rule`) VALUES (3, 1, '随机立减券', '满50随机立减最多5元', '3', NULL, '2023-09-08 22:59:49', '{\"discount\":{\"quota\":500,\"threshold\":5000},\"limitation\":10}');
INSERT INTO `coupon_template` (`id`, `available`, `name`, `description`, `type`, `shop_id`, `created_time`, `rule`) VALUES (4, 1, '打折券满10元打9折', '每人限制最多10张', '2', NULL, '2023-09-08 22:59:52', '{\"discount\":{\"quota\":90,\"threshold\":1000},\"limitation\":10}');
INSERT INTO `coupon_template` (`id`, `available`, `name`, `description`, `type`, `shop_id`, `created_time`, `rule`) VALUES (5, 0, '全场满10减1元', '满减券描述，每人限制最多10张', '1', NULL, '2023-09-08 23:00:11', '{\"discount\":{\"quota\":10,\"threshold\":1000},\"limitation\":10}');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
