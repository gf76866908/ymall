/*
 Navicat Premium Data Transfer

 Source Server         : ymall
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 115.159.95.37
 Source Database       : ymall

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 09/11/2017 23:03:58 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ymall_address`
-- ----------------------------
DROP TABLE IF EXISTS `ymall_address`;
CREATE TABLE `ymall_address` (
  `id`                INT(11) NOT NULL AUTO_INCREMENT,
  `user_id`           INT(11)          DEFAULT NULL
  COMMENT '用户id',
  `receiver_name`     VARCHAR(20)      DEFAULT NULL
  COMMENT '收货姓名',
  `receiver_phone`    VARCHAR(20)      DEFAULT NULL
  COMMENT '收货固定电话',
  `receiver_mobile`   VARCHAR(20)      DEFAULT NULL
  COMMENT '收货移动电话',
  `receiver_province` VARCHAR(20)      DEFAULT NULL
  COMMENT '省份',
  `receiver_city`     VARCHAR(20)      DEFAULT NULL
  COMMENT '城市',
  `receiver_district` VARCHAR(20)      DEFAULT NULL
  COMMENT '区/县',
  `receiver_address`  VARCHAR(200)     DEFAULT NULL
  COMMENT '详细地址',
  `receiver_zip`      VARCHAR(6)       DEFAULT NULL
  COMMENT '邮编',
  `create_time`       DATETIME         DEFAULT NULL,
  `update_time`       DATETIME         DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 36
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
--  Records of `ymall_address`
-- ----------------------------
BEGIN;
INSERT INTO `ymall_address` VALUES
  ('4', '13', 'geely', '010', '18688888888', '北京', '北京市', '海淀区', '中关村', '100000', '2017-01-22 14:26:25',
   '2017-01-22 14:26:25'),
  ('7', '17', 'Rosen', '13800138000', '13800138000', '北京', '北京', NULL, '中关村', '100000', '2017-03-29 12:11:01',
   '2017-03-29 12:11:01'),
  ('29', '1', '吉利', '13800138000', '13800138000', '北京', '北京', '海淀区', '海淀区中关村', '100000', '2017-04-09 18:33:32',
   '2017-04-09 18:33:32');
COMMIT;

-- ----------------------------
--  Table structure for `ymall_cart`
-- ----------------------------
DROP TABLE IF EXISTS `ymall_cart`;
CREATE TABLE `ymall_cart` (
  `id`          INT(11) NOT NULL AUTO_INCREMENT,
  `user_id`     INT(11) NOT NULL,
  `product_id`  INT(11)          DEFAULT NULL
  COMMENT '商品id',
  `quantity`    INT(11)          DEFAULT NULL
  COMMENT '数量',
  `checked`     TINYINT(1)       DEFAULT '1'
  COMMENT '是否选择,1=已勾选,0=未勾选',
  `create_time` DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `update_time` DATETIME         DEFAULT NULL
  COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 158
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
--  Records of `ymall_cart`
-- ----------------------------
BEGIN;
INSERT INTO `ymall_cart` VALUES ('126', '21', '26', '1', '1', '2017-04-13 21:27:06', '2017-04-13 21:27:06'),
  ('152', '1', '31', '4', '1', '2017-06-20 08:41:29', '2017-06-20 16:28:20'),
  ('157', '13', '33', '3', '1', '2017-06-23 17:08:37', '2017-06-23 17:13:27');
COMMIT;

-- ----------------------------
--  Table structure for `ymall_category`
-- ----------------------------
DROP TABLE IF EXISTS `ymall_category`;
CREATE TABLE `ymall_category` (
  `id`             INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '类别Id',
  `parent_id`      INT(11)          DEFAULT NULL
  COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name`           VARCHAR(50)      DEFAULT NULL
  COMMENT '类别名称',
  `category_image` VARCHAR(500)     DEFAULT NULL
  COMMENT '类别封面',
  `status`         TINYINT(1)       DEFAULT '1'
  COMMENT '类别状态1-正常,2-已废弃',
  `sort_order`     INT(4)           DEFAULT NULL
  COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `create_time`    DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `update_time`    DATETIME         DEFAULT NULL
  COMMENT '更新时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 100033
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
--  Records of `ymall_category`
-- ----------------------------
BEGIN;
INSERT INTO `ymall_category` VALUES
  ('100001', '0', '家用电器', 'http://img11.360buyimg.com/n1/jfs/t4315/291/3061863583/61523/2c444e21/58d9c786N0ffea01f.jpg',
   '1', NULL, '2017-03-25 16:46:00', '2017-03-25 16:46:00'),
  ('100002', '0', '数码3C', 'https://img11.360buyimg.com/n7/jfs/t4507/113/900444492/339808/f9c11ff4/58d645e5N85b92f3d.jpg', '1', NULL, '2017-03-25 16:46:21', '2017-03-25 16:46:21'),
  ('100003', '0', '服装箱包', 'https://img10.360buyimg.com/n8/jfs/t4972/362/1003917277/339550/c10099b5/58ebb0f5Ne325741f.jpg', '1', NULL, '2017-03-25 16:49:53', '2017-03-25 16:49:53'),
  ('100004', '0', '食品生鲜', 'https://img10.360buyimg.com/n7/jfs/t2908/3/3796506832/163005/3096bef9/579acfc0N5389cfc5.jpg', '1', NULL, '2017-03-25 16:50:19', '2017-03-25 16:50:19'),
  ('100005', '0', '酒水饮料', 'https://img13.360buyimg.com/n7/jfs/t3949/219/2611483222/99146/79b6408d/58ad0cb4Nb3a93807.jpg', '1', NULL, '2017-03-25 16:50:29', '2017-03-25 16:50:29'),
  ('100006', '100001', '冰箱', 'https://img14.360buyimg.com/n7/jfs/t2749/312/1338597980/18864/dd59dea7/573ab168N7e05c4ee.jpg', '1', NULL, '2017-03-25 16:52:15', '2017-03-25 16:52:15'),
  ('100007', '100001', '电视', 'https://img12.360buyimg.com/n7/jfs/t4252/43/3259960696/411912/6eb52b3/58dcc732N2bb6c270.jpg', '1', NULL, '2017-03-25 16:52:26', '2017-03-25 16:52:26'),
  ('100008', '100001', '洗衣机', 'https://img11.360buyimg.com/n7/jfs/t3133/2/8238052566/289523/42d18384/58c1173cN44e117fb.jpg', '1', NULL, '2017-03-25 16:52:39', '2017-03-25 16:52:39'),
  ('100009', '100001', '空调', 'https://img12.360buyimg.com/n7/jfs/t3265/243/8996491801/49181/84ea1da6/58ccfffcNd70c9a03.jpg', '1', NULL, '2017-03-25 16:52:45', '2017-03-25 16:52:45'),
  ('100010', '100001', '电热水器', 'https://img12.360buyimg.com/n7/jfs/t2638/298/2825201425/62272/fcb4e6fe/57748fa5Ncef3243a.jpg', '1', NULL, '2017-03-25 16:52:54', '2017-03-25 16:52:54'),
  ('100011', '100002', '电脑', 'https://img11.360buyimg.com/n7/jfs/t2968/143/2485546147/238650/70df281e/57b12a31N8f4f75a3.jpg', '1', NULL, '2017-03-25 16:53:18', '2017-03-25 16:53:18'),
  ('100012', '100002', '手机', 'https://img10.360buyimg.com/n7/jfs/t4276/257/2416766721/125228/ba72a107/58d1d078N20e18b62.jpg', '1', NULL, '2017-03-25 16:53:27', '2017-03-25 16:53:27'),
  ('100013', '100002', '平板电脑', 'https://img12.360buyimg.com/n7/jfs/t3214/177/9256496777/621169/2a1735b5/58d16208N55e7302b.jpg', '1', NULL, '2017-03-25 16:53:35', '2017-03-25 16:53:35'),
  ('100014', '100002', '数码相机', 'https://img11.360buyimg.com/n7/jfs/t2767/348/31307366/271969/d9ae2b61/56fc83a8Nb63148f4.jpg', '1', NULL, '2017-03-25 16:53:56', '2017-03-25 16:53:56'),
  ('100015', '100002', '3C配件', 'https://img12.360buyimg.com/n7/jfs/t6037/235/2061886257/170894/5c115459/593a4409N77023a75.jpg', '1', NULL, '2017-03-25 16:54:07', '2017-03-25 16:54:07'),
  ('100016', '100003', '女装', 'https://img13.360buyimg.com/n8/jfs/t6433/217/613925610/188744/5d3a138/594259faN71247c22.jpg', '1', NULL, '2017-03-25 16:54:44', '2017-03-25 16:54:44'),
  ('100017', '100003', '帽子', 'https://img14.360buyimg.com/n7/jfs/t5986/281/2494449499/173147/78ab62c/5940acf0N73a94d27.jpg', '1', NULL, '2017-03-25 16:54:51', '2017-03-25 16:54:51'),
  ('100018', '100003', '旅行箱', 'https://img11.360buyimg.com/n7/jfs/t3070/190/5270715477/271503/dcfa3ae/586b5a2fNe0edb9a0.jpg', '1', NULL, '2017-03-25 16:55:02', '2017-03-25 16:55:02'),
  ('100019', '100003', '手提包', 'https://img11.360buyimg.com/n7/jfs/t5515/35/1856494446/34102/1ac3cf59/5915124cNcfdaa96d.jpg', '1', NULL, '2017-03-25 16:55:09', '2017-03-25 16:55:09'),
  ('100020', '100003', '保暖内衣', 'https://img12.360buyimg.com/n7/jfs/t6145/286/571906797/375939/5c27cac8/59414317Nf46a4ba8.jpg', '1', NULL, '2017-03-25 16:55:18', '2017-03-25 16:55:18'),
  ('100021', '100004', '零食', 'https://img11.360buyimg.com/n7/jfs/t3199/145/1987788886/433883/96188263/57d7aec4Na57c2e12.jpg', '1', NULL, '2017-03-25 16:55:30', '2017-03-25 16:55:30'),
  ('100022', '100004', '生鲜',
   'https://img11.360buyimg.com/n7/jfs/t5782/64/1244820819/218580/e93e48b8/5924e807Nde651aa4.jpg', '1', NULL,
   '2017-03-25 16:55:37', '2017-03-25 16:55:37'), ('100023', '100004', '半成品菜',
                                                   'https://img14.360buyimg.com/n7/jfs/t4240/106/1391032630/392824/26f6bfa2/58c0f2ebNa74df58b.jpg',
                                                   '1', NULL, '2017-03-25 16:55:47', '2017-03-25 16:55:47'),
  ('100024', '100004', '速冻食品',
   'https://img13.360buyimg.com/n7/jfs/t4930/140/1058663958/584803/1a370a27/58ec37e9Na9669ac2.jpg', '1', NULL,
   '2017-03-25 16:55:56', '2017-03-25 16:55:56'), ('100025', '100004', '进口食品',
                                                   'https://img14.360buyimg.com/n7/jfs/t2890/271/363479149/476836/49e62c8a/570f5260N2c22280e.jpg',
                                                   '1', NULL, '2017-03-25 16:56:06', '2017-03-25 16:56:06'),
  ('100026', '100005', '白酒',
   'https://img13.360buyimg.com/n7/jfs/t4513/114/2031179321/494316/d3609864/58e9ef23N3b377e11.jpg', '1', NULL,
   '2017-03-25 16:56:22', '2017-03-25 16:56:22'), ('100027', '100005', '红酒',
                                                   'https://img14.360buyimg.com/n0/jfs/t3097/279/1424989832/358902/16d72bed/57cd23d9N003fe68c.jpg',
                                                   '1', NULL, '2017-03-25 16:56:30', '2017-03-25 16:56:30'),
  ('100028', '100005', '饮料',
   'https://img14.360buyimg.com/n0/jfs/t4705/17/3767099700/209718/e64ba898/5902945aN60d79d93.jpg', '1', NULL,
   '2017-03-25 16:56:37', '2017-03-25 16:56:37'), ('100029', '100005', '调制鸡尾酒',
                                                   'https://img14.360buyimg.com/n0/jfs/t2320/305/358309297/124541/bbb31b0b/56012e9fN66e83e93.jpg',
                                                   '1', NULL, '2017-03-25 16:56:45', '2017-03-25 16:56:45'),
  ('100030', '100005', '进口洋酒',
   'https://img12.360buyimg.com/n7/jfs/t5971/263/2497601765/301176/bb9afb09/5940ee8cN2dba8dcb.jpg', '1', NULL,
   '2017-03-25 16:57:05', '2017-03-25 16:57:05');
COMMIT;

-- ----------------------------
--  Table structure for `ymall_order`
-- ----------------------------
DROP TABLE IF EXISTS `ymall_order`;
CREATE TABLE `ymall_order` (
  `id`           INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '订单id',
  `order_no`     BIGINT(20)       DEFAULT NULL
  COMMENT '订单号',
  `user_id`      INT(11)          DEFAULT NULL
  COMMENT '用户id',
  `address_id`   INT(11)          DEFAULT NULL,
  `payment`      DECIMAL(20, 2)   DEFAULT NULL
  COMMENT '实际付款金额,单位是元,保留两位小数',
  `payment_type` INT(4)           DEFAULT NULL
  COMMENT '支付类型,1-在线支付',
  `postage`      INT(10)          DEFAULT NULL
  COMMENT '运费,单位是元',
  `status`       INT(10)          DEFAULT NULL
  COMMENT '订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
  `payment_time` DATETIME         DEFAULT NULL
  COMMENT '支付时间',
  `send_time`    DATETIME         DEFAULT NULL
  COMMENT '发货时间',
  `end_time`     DATETIME         DEFAULT NULL
  COMMENT '交易完成时间',
  `close_time`   DATETIME         DEFAULT NULL
  COMMENT '交易关闭时间',
  `create_time`  DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `update_time`  DATETIME         DEFAULT NULL
  COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no_index` (`order_no`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 118
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
--  Records of `ymall_order`
-- ----------------------------
BEGIN;
INSERT INTO `ymall_order` VALUES
  ('103', '1491753014256', '1', '25', '13998.00', '1', '0', '10', NULL, NULL, NULL, NULL, '2017-04-09 23:50:14',
   '2017-04-09 23:50:14'),
  ('104', '1491830695216', '1', '26', '13998.00', '1', '0', '10', NULL, NULL, NULL, NULL, '2017-04-10 21:24:55', '2017-04-10 21:24:55'),
  ('105', '1492089528889', '1', '29', '3299.00', '1', '0', '10', NULL, NULL, NULL, NULL, '2017-04-13 21:18:48', '2017-04-13 21:18:48'),
  ('106', '1492090946105', '1', '29', '27894.00', '1', '0', '20', '2017-04-13 21:42:40', NULL, NULL, NULL, '2017-04-13 21:42:26', '2017-04-13 21:42:41'),
  ('107', '1492091003128', '1', '29', '8597.00', '1', '0', '20', '2017-04-13 21:43:38', NULL, NULL, NULL, '2017-04-13 21:43:23', '2017-04-13 21:43:38'),
  ('108', '1492091051313', '1', '29', '1999.00', '1', '0', '10', NULL, NULL, NULL, NULL, '2017-04-13 21:44:11', '2017-04-13 21:44:11'),
  ('109', '1492091061513', '1', '29', '6598.00', '1', '0', '10', NULL, NULL, NULL, NULL, '2017-04-13 21:44:21', '2017-04-13 21:44:21'),
  ('110', '1492091069563', '1', '29', '3299.00', '1', '0', '10', NULL, NULL, NULL, NULL, '2017-04-13 21:44:29', '2017-04-13 21:44:29'),
  ('111', '1492091076073', '1', '29', '4299.00', '1', '0', '10', NULL, NULL, NULL, NULL, '2017-04-13 21:44:36', '2017-04-13 21:44:36'),
  ('112', '1492091083720', '1', '29', '3299.00', '1', '0', '10', NULL, NULL, NULL, NULL, '2017-04-13 21:44:43', '2017-04-13 21:44:43'),
  ('113', '1492091089794', '1', '29', '6999.00', '1', '0', '10', NULL, NULL, NULL, NULL, '2017-04-13 21:44:49', '2017-04-13 21:44:49'),
  ('114', '1492091096400', '1', '29', '6598.00', '1', '0', '10', NULL, NULL, NULL, NULL, '2017-04-13 21:44:56',
   '2017-04-13 21:44:56'),
  ('115', '1492091102371', '1', '29', '3299.00', '1', '0', '10', NULL, NULL, NULL, NULL, '2017-04-13 21:45:02',
   '2017-04-13 21:45:02'),
  ('116', '1492091110004', '1', '29', '8598.00', '1', '0', '40', '2017-04-13 21:55:16', '2017-04-13 21:55:31', NULL,
   NULL, '2017-04-13 21:45:09', '2017-04-13 21:55:31'),
  ('117', '1492091141269', '1', '29', '22894.00', '1', '0', '20', '2017-04-13 21:46:06', NULL, NULL, NULL,
   '2017-04-13 21:45:41', '2017-04-13 21:46:07');
COMMIT;

-- ----------------------------
--  Table structure for `ymall_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `ymall_order_item`;
CREATE TABLE `ymall_order_item` (
  `id`                 INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '订单子表id',
  `user_id`            INT(11)          DEFAULT NULL,
  `order_no`           BIGINT(20)       DEFAULT NULL,
  `product_id`         INT(11)          DEFAULT NULL
  COMMENT '商品id',
  `product_name`       VARCHAR(100)     DEFAULT NULL
  COMMENT '商品名称',
  `product_image`      VARCHAR(500)     DEFAULT NULL
  COMMENT '商品图片地址',
  `current_unit_price` DECIMAL(20, 2)   DEFAULT NULL
  COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
  `quantity`           INT(10)          DEFAULT NULL
  COMMENT '商品数量',
  `total_price`        DECIMAL(20, 2)   DEFAULT NULL
  COMMENT '商品总价,单位是元,保留两位小数',
  `create_time`        DATETIME         DEFAULT NULL,
  `update_time`        DATETIME         DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_no_index` (`order_no`) USING BTREE,
  KEY `order_no_user_id_index` (`user_id`, `order_no`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 135
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
--  Records of `ymall_order_item`
-- ----------------------------
BEGIN;
INSERT INTO `ymall_order_item` VALUES
  ('113', '1', '1491753014256', '26', 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机',
          '241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', '6999.00', '2', '13998.00', '2017-04-09 23:50:14',
          '2017-04-09 23:50:14'),
  ('114', '1', '1491830695216', '26', 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', '241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', '6999.00', '2', '13998.00', '2017-04-10 21:24:55', '2017-04-10 21:24:55'),
  ('115', '1', '1492089528889', '27', 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', '3299.00', '1', '3299.00', '2017-04-13 21:18:48', '2017-04-13 21:18:48'),
  ('116', '1', '1492090946105', '29', 'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体', '173335a4-5dce-4afd-9f18-a10623724c4e.jpeg', '4299.00', '2', '8598.00', '2017-04-13 21:42:26', '2017-04-13 21:42:26'),
  ('117', '1', '1492090946105', '28', '4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春', '0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', '1999.00', '1', '1999.00', '2017-04-13 21:42:26', '2017-04-13 21:42:26'),
  ('118', '1', '1492090946105', '27', 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', '3299.00', '1', '3299.00', '2017-04-13 21:42:26', '2017-04-13 21:42:26'),
  ('119', '1', '1492090946105', '26', 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', '241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', '6999.00', '2', '13998.00', '2017-04-13 21:42:26', '2017-04-13 21:42:26'),
  ('120', '1', '1492091003128', '27', 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', '3299.00', '2', '6598.00', '2017-04-13 21:43:23', '2017-04-13 21:43:23'),
  ('121', '1', '1492091003128', '28', '4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春', '0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', '1999.00', '1', '1999.00', '2017-04-13 21:43:23', '2017-04-13 21:43:23'),
  ('122', '1', '1492091051313', '28', '4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春', '0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', '1999.00', '1', '1999.00', '2017-04-13 21:44:11', '2017-04-13 21:44:11'),
  ('123', '1', '1492091061513', '27', 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', '3299.00', '2', '6598.00', '2017-04-13 21:44:21', '2017-04-13 21:44:21'),
  ('124', '1', '1492091069563', '27', 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', '3299.00', '1', '3299.00', '2017-04-13 21:44:29', '2017-04-13 21:44:29'),
  ('125', '1', '1492091076073', '29', 'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体', '173335a4-5dce-4afd-9f18-a10623724c4e.jpeg', '4299.00', '1', '4299.00', '2017-04-13 21:44:36', '2017-04-13 21:44:36'),
  ('126', '1', '1492091083720', '27', 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', '3299.00', '1', '3299.00', '2017-04-13 21:44:43', '2017-04-13 21:44:43'),
  ('127', '1', '1492091089794', '26', 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', '241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', '6999.00', '1', '6999.00', '2017-04-13 21:44:49', '2017-04-13 21:44:49'),
  ('128', '1', '1492091096400', '27', 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', '3299.00', '2', '6598.00', '2017-04-13 21:44:56', '2017-04-13 21:44:56'),
  ('129', '1', '1492091102371', '27', 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', '3299.00', '1', '3299.00', '2017-04-13 21:45:02', '2017-04-13 21:45:02'),
  ('130', '1', '1492091110004', '29', 'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体', '173335a4-5dce-4afd-9f18-a10623724c4e.jpeg', '4299.00', '2', '8598.00', '2017-04-13 21:45:09', '2017-04-13 21:45:09'),
  ('131', '1', '1492091141269', '26', 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', '241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', '6999.00', '1', '6999.00', '2017-04-13 21:45:41', '2017-04-13 21:45:41'),
  ('132', '1', '1492091141269', '27', 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', '3299.00', '1', '3299.00', '2017-04-13 21:45:41', '2017-04-13 21:45:41'),
  ('133', '1', '1492091141269', '29', 'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体', '173335a4-5dce-4afd-9f18-a10623724c4e.jpeg', '4299.00', '2', '8598.00', '2017-04-13 21:45:41', '2017-04-13 21:45:41'),
  ('134', '1', '1492091141269', '28', '4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春',
          '0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', '1999.00', '2', '3998.00', '2017-04-13 21:45:41',
          '2017-04-13 21:45:41');
COMMIT;

-- ----------------------------
--  Table structure for `ymall_pay_info`
-- ----------------------------
DROP TABLE IF EXISTS `ymall_pay_info`;
CREATE TABLE `ymall_pay_info` (
  `id`              INT(11) NOT NULL AUTO_INCREMENT,
  `user_id`         INT(11)          DEFAULT NULL
  COMMENT '用户id',
  `order_no`        BIGINT(20)       DEFAULT NULL
  COMMENT '订单号',
  `pay_platform`    INT(10)          DEFAULT NULL
  COMMENT '支付平台:1-支付宝,2-微信',
  `platform_number` VARCHAR(200)     DEFAULT NULL
  COMMENT '支付宝支付流水号',
  `platform_status` VARCHAR(20)      DEFAULT NULL
  COMMENT '支付宝支付状态',
  `create_time`     DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `update_time`     DATETIME         DEFAULT NULL
  COMMENT '更新时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 61
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
--  Records of `ymall_pay_info`
-- ----------------------------
BEGIN;
INSERT INTO `ymall_pay_info` VALUES
  ('53', '1', '1492090946105', '1', '2017041321001004300200116250', 'WAIT_BUYER_PAY', '2017-04-13 21:42:33',
   '2017-04-13 21:42:33'),
  ('54', '1', '1492090946105', '1', '2017041321001004300200116250', 'TRADE_SUCCESS', '2017-04-13 21:42:41',
   '2017-04-13 21:42:41'),
  ('55', '1', '1492091003128', '1', '2017041321001004300200116251', 'WAIT_BUYER_PAY', '2017-04-13 21:43:31',
   '2017-04-13 21:43:31'),
  ('56', '1', '1492091003128', '1', '2017041321001004300200116251', 'TRADE_SUCCESS', '2017-04-13 21:43:38',
   '2017-04-13 21:43:38'),
  ('57', '1', '1492091141269', '1', '2017041321001004300200116252', 'WAIT_BUYER_PAY', '2017-04-13 21:45:59',
   '2017-04-13 21:45:59'),
  ('58', '1', '1492091141269', '1', '2017041321001004300200116252', 'TRADE_SUCCESS', '2017-04-13 21:46:07',
   '2017-04-13 21:46:07'),
  ('59', '1', '1492091110004', '1', '2017041321001004300200116396', 'WAIT_BUYER_PAY', '2017-04-13 21:55:08',
   '2017-04-13 21:55:08'),
  ('60', '1', '1492091110004', '1', '2017041321001004300200116396', 'TRADE_SUCCESS', '2017-04-13 21:55:17',
   '2017-04-13 21:55:17');
COMMIT;

-- ----------------------------
--  Table structure for `ymall_product`
-- ----------------------------
DROP TABLE IF EXISTS `ymall_product`;
CREATE TABLE `ymall_product` (
  `id`          INT(11)        NOT NULL AUTO_INCREMENT
  COMMENT '商品id',
  `category_id` INT(11)        NOT NULL
  COMMENT '分类id,对应ymall_category表的主键',
  `name`        VARCHAR(100)   NOT NULL
  COMMENT '商品名称',
  `subtitle`    VARCHAR(200)            DEFAULT NULL
  COMMENT '商品副标题',
  `main_image`  VARCHAR(500)            DEFAULT NULL
  COMMENT '产品主图,url相对地址',
  `sub_images`  TEXT COMMENT '图片地址,json格式,扩展用',
  `detail`      TEXT COMMENT '商品详情',
  `price`       DECIMAL(20, 2) NOT NULL
  COMMENT '价格,单位-元保留两位小数',
  `stock`       INT(11)        NOT NULL
  COMMENT '库存数量',
  `sales`       INT(11)                 DEFAULT '0'
  COMMENT '销量',
  `status`      INT(6)                  DEFAULT '1'
  COMMENT '商品状态.1-在售 2-下架 3-删除',
  `create_time` DATETIME                DEFAULT NULL
  COMMENT '创建时间',
  `update_time` DATETIME                DEFAULT NULL
  COMMENT '更新时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 38
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
--  Records of `ymall_product`
-- ----------------------------
BEGIN;
INSERT INTO `ymall_product` VALUES
  ('26', '100012', 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', 'iPhone 7，现更以红色呈现。',
         'img/f35cde2b-2f4e-4e2e-80db-a358e8bb7477.jpg', 'img/f35cde2b-2f4e-4e2e-80db-a358e8bb7477.jpg',
         '<div> <img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2616970884/TB270qxaH1J.eBjy1zeXXX9kVXa-2616970884.jpg\" style=\"line-height: 1.5;\" class=\"img-ks-lazyload\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2616970884/TB2fmiublyN.eBjSZFkXXb8YFXa-2616970884.jpg\" style=\"line-height: 1.5;\" class=\"img-ks-lazyload\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2616970884/TB2MgcXXheI.eBjSsplXXX6GFXa-2616970884.jpg\" style=\"line-height: 1.5;\" class=\"img-ks-lazyload\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2616970884/TB2m4oYvYBnpuFjSZFGXXX51pXa-2616970884.jpg\" class=\"img-ks-lazyload\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2616970884/TB2JW4TwhxmpuFjSZFNXXXrRXXa-2616970884.jpg\" class=\"img-ks-lazyload\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2616970884/TB2WSJ5wm0mpuFjSZPiXXbssVXa-2616970884.jpg\" class=\"img-ks-lazyload\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2616970884/TB2VhI7XiKO.eBjSZPhXXXqcpXa-2616970884.jpg\" style=\"line-height: 1.5;\" class=\"img-ks-lazyload\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2616970884/TB2nzBjwipnpuFjSZFkXXc4ZpXa-2616970884.gif\" class=\"img-ks-lazyload\"> </div>',
         '6999.00', '9991', '10', '1', NULL, '2017-06-20 12:43:08'),
  ('27', '100006', 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', '送品牌烤箱，五一大促', 'img/ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', 'img/ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', '<p><img alt=\"miaoshu.jpg\" src=\"http://img.happymmall.com/9c5c74e6-6615-4aa0-b1fc-c17a1eff6027.jpg\" width=\"790\" height=\"444\"><br></p>', '3299.00', '8876', '28', '1', '2017-04-13 18:51:54', '2017-06-20 12:43:05'),
  ('28', '100012', '4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春', 'NOVA青春版1999元', 'img/0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', 'img/0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', '<p><b>这是一个非常流弊的华为手机！！</b></p><p><img alt=\"11TB2fKK3cl0kpuFjSsziXXa.oVXa_!!1777180618.jpg\" src=\"http://img.happymmall.com/5c2d1c6d-9e09-48ce-bbdb-e833b42ff664.jpg\" width=\"790\" height=\"966\"><img alt=\"22TB2YP3AkEhnpuFjSZFpXXcpuXXa_!!1777180618.jpg\" src=\"http://img.happymmall.com/9a10b877-818f-4a27-b6f7-62887f3fb39d.jpg\" width=\"790\" height=\"1344\"><img alt=\"33TB2Yyshk.hnpuFjSZFpXXcpuXXa_!!1777180618.jpg\" src=\"http://img.happymmall.com/7d7fbd69-a3cb-4efe-8765-423bf8276e3e.jpg\" width=\"790\" height=\"700\"><img alt=\"TB2diyziB8kpuFjSspeXXc7IpXa_!!1777180618.jpg\" src=\"http://img.happymmall.com/1d7160d2-9dba-422f-b2a0-e92847ba6ce9.jpg\" width=\"790\" height=\"393\"><br></p>', '1999.00', '4', '45', '2', '2017-04-13 18:57:18', '2017-06-20 12:43:11'),
  ('29', '100008', 'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体', '门店机型 德邦送货', 'img/173335a4-5dce-4afd-9f18-a10623724c4e.jpeg', 'img/173335a4-5dce-4afd-9f18-a10623724c4e.jpeg', '<p><img alt=\"1TB2WLZrcIaK.eBjSspjXXXL.XXa_!!2114960396.jpg\" src=\"http://img.happymmall.com/ffcce953-81bd-463c-acd1-d690b263d6df.jpg\" width=\"790\" height=\"920\"><img alt=\"2TB2zhOFbZCO.eBjSZFzXXaRiVXa_!!2114960396.jpg\" src=\"http://img.happymmall.com/58a7bd25-c3e7-4248-9dba-158ef2a90e70.jpg\" width=\"790\" height=\"1052\"><img alt=\"3TB27mCtb7WM.eBjSZFhXXbdWpXa_!!2114960396.jpg\" src=\"http://img.happymmall.com/2edbe9b3-28be-4a8b-a9c3-82e40703f22f.jpg\" width=\"790\" height=\"820\"><br></p>', '4299.00', '9993', '11', '1', '2017-04-13 19:07:47', '2017-06-17 00:09:36'),
  ('30', '100002', 'MacBookPro', '【新品首发】2017款MacBook全新登场！第七代Intel处理器带来更强性能！6.13-6.20享白条6期免息！下单即送iPod shuffle，数量有限，先到先得!', 'img/593959fbNa76b2674.jpg', 'img/593959fbNa76b2674.jpg', '<p><span style=\"color: rgb(102, 102, 102);\">商品介紹頁面素材由Apple提供</span></p><p><img src=\"https://img14.360buyimg.com/cms/jfs/t5680/348/3497865556/252479/c9ab6473/593cfc6bN4319b389.jpg\" height=\"810\" width=\"750\"><img src=\"https://img10.360buyimg.com/cms/jfs/t5809/283/3480465211/260897/df6ea4e6/593cfc66N70afa514.jpg\" height=\"1222\" width=\"750\"><img src=\"https://img13.360buyimg.com/cms/jfs/t6523/318/312054404/223321/1f85bb4/593cfc6cN91b57a3c.jpg\" height=\"1114\" width=\"750\"><img src=\"https://img10.360buyimg.com/cms/jfs/t6151/145/291610605/193392/fdeb4a4e/593cfdb9N707e855e.jpg\" height=\"520\" width=\"750\"><img src=\"https://img13.360buyimg.com/cms/jfs/t5812/284/3448086077/97528/35d23587/593cfdbdNbd8ea272.jpg\" height=\"494\" width=\"750\"><img src=\"https://img11.360buyimg.com/cms/jfs/t6178/184/288423119/192683/90b39aeb/593cfdc0N1f4b7810.jpg\" height=\"1031\" width=\"750\"><img src=\"https://img14.360buyimg.com/cms/jfs/t6298/117/297770385/255550/4b2795ac/593cfdc4Nb8667a19.jpg\" height=\"1044\" width=\"750\"><img src=\"https://img11.360buyimg.com/cms/jfs/t6193/221/301092556/283393/e5a86d53/593cfdc9Nf520ecbd.jpg\" height=\"1054\" width=\"750\"><img src=\"https://img10.360buyimg.com/cms/jfs/t6697/165/277021333/195676/d61afcb5/593cfe0cNee5d61a9.jpg\" height=\"640\" width=\"750\"><img src=\"https://img11.360buyimg.com/cms/jfs/t6019/126/2177972076/202546/ca22ad78/593cfe10Nf1a26b2c.jpg\" height=\"584\" width=\"750\"><img src=\"https://img10.360buyimg.com/cms/jfs/t6760/92/279911388/87077/7d74a2fe/593cfe0eNd44ad13e.jpg\" height=\"606\" width=\"750\"><img src=\"https://img11.360buyimg.com/cms/jfs/t6097/89/2219449340/97802/53a386c/593cfe16N6cb5c800.jpg\" height=\"604\" width=\"750\"><img src=\"https://img10.360buyimg.com/cms/jfs/t6133/34/302148892/246664/ff082ba0/593cfe1aN4a172a10.jpg\" height=\"518\" width=\"750\"></p>', '14288.80', '10000', '180', '1', '2017-06-17 00:21:23', '2017-06-17 00:21:23'),
  ('31', '100012', '华为(HUAWEI)华为揽阅M2青春版 7.0英寸(PLE-703L 1920×1200 8核 3G/16G 全网通)水晶粉', '【6.19-6.20】白条6期免息 华为6月狂欢 货量有限 价格好看 手慢无', 'img/b781200f-68d7-490d-83e7-4815e565856a.jpg', 'img/b781200f-68d7-490d-83e7-4815e565856a.jpg,img/8ae502c5-1cfa-418d-b902-21699aea9e05.jpg', '<p><img src=\"https://img20.360buyimg.com/vc/jfs/t5941/243/2590335576/220846/6d28534f/594236c5Na57f487a.jpg\" height=\"720\" width=\"750\"></p><p><img src=\"https://img20.360buyimg.com/vc/jfs/t3118/228/632021917/1020307/97f3a812/57bc1589N22a67afd.jpg\"></p><p><img src=\"https://img20.360buyimg.com/vc/jfs/t3475/206/2372567887/80964/b0b611db/584e540cNcdcefb0a.jpg\"></p>', '1999.00', '1887', '20', '2', '2017-06-19 23:45:57', '2017-06-20 16:36:28'),
  ('32', '100014', '索尼（SONY）A9/ILCE-9', 'α9全画幅微单相机 FE70-200mmF2.8镜头', 'img/f9f4fce0-78ea-4c4a-8430-b6278aa8cf2a.jpg', 'img/f9f4fce0-78ea-4c4a-8430-b6278aa8cf2a.jpg,img/c9061c07-2f4d-45e0-ae97-82a7ec27279c.jpg,img/beee3b28-9491-472e-bb9d-13f98f0ac7f0.png,img/0444c495-5672-4864-a99e-cf144f42d930.jpg,img/8ab0b78a-1e87-4011-873e-6d8ae5d577d7.jpg', '<p><br></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t5755/350/496710287/315709/632ced88/591fb1b6N3ea1806d.jpg\"></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t5674/97/490911579/208935/99d1200/591fb1b9Nc517409b.jpg\"></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t5788/282/476998620/368872/89d8ecb2/591fb1baNbb337c46.jpg\"></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t5788/319/487247652/520484/f08e7647/591fb1c9N02df0649.jpg\"></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t5617/308/483633594/387948/d24f1579/591fb1caN3285f9ce.jpg\"></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t4465/321/2933660796/502366/304f5b01/591fb1ceN13818925.jpg\"></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t5812/61/493656376/479369/fdc7dabd/591fb1d1N2216b03f.jpg\"></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t5896/280/477901529/740511/b5a8a212/591fb1d2Neeee892d.jpg\"></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t5575/136/485646162/923757/a7137b22/591fb1d4N17d2e720.jpg\"></p><p><br></p>', '49999.00', '2', '40', '1', '2017-06-20 11:25:33', '2017-06-20 11:25:33'),
  ('33', '100012', '苹果(Apple) iPhone7 Plus 4G手机 红色特别版 全网通(128G)', 'iPhone 7，现更以红色呈现。', 'img/c610462d-0fef-45c4-9a71-347d08ecab8b.jpg', 'img/c610462d-0fef-45c4-9a71-347d08ecab8b.jpg', '<p><span style=\"color: rgb(102, 102, 102);\"><img src=\"https://img30.360buyimg.com/popWareDetail/jfs/t3250/142/2397979831/709210/d6c87a6a/57e0a7a4N419fb409.jpg\"></span></p>', '6488.00', '5', '54', '1', '2017-06-20 14:05:32', '2017-06-20 14:05:32'),
  ('34', '100018', '美旅AmericanTourister万向轮拉杆箱79B*60002酒红色24英寸', '美旅AmericanTourister万向轮拉杆箱79B*60002酒红色24英寸', 'img/a49e706e-a8ce-47b4-ad0c-c477f63b015c.jpg', 'img/a49e706e-a8ce-47b4-ad0c-c477f63b015c.jpg', '<p class=\"ql-align-center\"><strong style=\"color: rgb(102, 102, 102);\"><img src=\"https://img11.360buyimg.com/cms/jfs/t3268/302/2944872048/304507/f8fd9887/57e9e817Nfc063d6d.jpg\" height=\"913\" width=\"750\"><img src=\"https://img10.360buyimg.com/cms/jfs/t3271/312/2951491102/215650/25fd7d85/57e9e825Neb525c66.jpg\" height=\"596\" width=\"750\"><img src=\"https://img13.360buyimg.com/cms/jfs/t3181/215/3876639545/296016/adea8b32/57fb01dcN625f1cbb.jpg\" height=\"961\" width=\"750\"><img src=\"https://img12.360buyimg.com/cms/jfs/t3052/248/2952973905/234856/c5c7bccb/57e9e846N01452d92.jpg\" height=\"868\" width=\"750\"><img src=\"https://img12.360buyimg.com/cms/jfs/t289/108/1294982547/8094/8d798c10/5435d4adN9a8720d2.jpg\" height=\"45\" width=\"750\"></strong>&nbsp;</p><p class=\"ql-align-center\"><br></p><p>美旅箱包（AmericanTourister）飞机轮可扩展万向轮拉杆箱 Munich系列79B*60002酒红色24英寸</p><p><strong style=\"color: rgb(102, 102, 102);\"><img src=\"https://img10.360buyimg.com/cms/jfs/t3100/239/2937150957/121063/1d36fb8f/57e9e85bNde6240ca.jpg\" height=\"498\" width=\"750\"><img src=\"https://img11.360buyimg.com/cms/jfs/t3124/364/2919935456/194113/78d78a33/57e9e862Nd9f157e7.jpg\" height=\"573\" width=\"750\"></strong></p><p><strong style=\"color: rgb(102, 102, 102);\"><img src=\"https://img11.360buyimg.com/cms/jfs/t325/136/1295253429/8099/4eca76a5/5435d474Ne6d2f421.jpg\" height=\"45\" width=\"750\"></strong></p><p><strong style=\"color: rgb(102, 102, 102);\"><img src=\"https://img14.360buyimg.com/cms/jfs/t3289/26/2955675385/279491/7117dcd/57e9e879Ne4bd2f84.jpg\" height=\"546\" width=\"750\"><img src=\"https://img10.360buyimg.com/cms/jfs/t3127/17/2959125796/160513/1e3bdf78/57e9e880Na7cd86f3.jpg\" height=\"690\" width=\"750\"><img src=\"https://img10.360buyimg.com/cms/jfs/t3304/3/2931066524/132671/737df2/57e9e886Nbbe47065.jpg\" height=\"539\" width=\"750\"><img src=\"https://img14.360buyimg.com/cms/jfs/t3292/329/2891156134/267365/2aeb3a8e/57e9e890N4ac15403.jpg\" height=\"690\" width=\"750\"><img src=\"https://img12.360buyimg.com/cms/jfs/t3127/303/2996588002/236133/8d734bd5/57e9e899Na27ae2d0.jpg\" height=\"658\" width=\"750\"><img src=\"https://img13.360buyimg.com/cms/jfs/t3259/76/2989070370/144236/fb07485a/57e9e8a1N026076ff.jpg\" height=\"494\" width=\"750\"></strong></p><p><strong style=\"color: rgb(102, 102, 102);\"><img src=\"https://img11.360buyimg.com/cms/jfs/t316/204/1296958752/7722/43b999fc/5435d4c0Nf8975b42.jpg\" height=\"45\" width=\"750\"></strong></p><p><strong style=\"color: rgb(102, 102, 102);\"><img src=\"https://img12.360buyimg.com/cms/jfs/t3130/135/3018088032/292996/8e2a410f/57e9e8b3Nbe68a8a2.jpg\" height=\"763\" width=\"750\"><img src=\"https://img12.360buyimg.com/cms/jfs/t3163/217/3014838955/297748/78100e51/57e9e8beN7c8dd3c3.jpg\" height=\"970\" width=\"750\"></strong></p><p class=\"ql-align-center\"><strong style=\"color: rgb(102, 102, 102);\"><img src=\"https://img14.360buyimg.com/cms/jfs/t5434/175/598299024/151076/ed4f85ed/5902d15aNbebeddcd.jpg\" height=\"774\" width=\"750\"></strong></p><p><br></p>', '566.00', '4', '30', '1', '2017-06-20 14:08:56', '2017-06-20 14:08:56'),
  ('35', '100016', 'ONLY2017春季新品字母印花纱网拼接层次设计连衣裙女', '连衣裙女L|117261516 凤仙花绿E56 165/84A/M', 'img/9692207a-7f72-4d54-b1fe-c07886a744bf.jpg', 'img/9692207a-7f72-4d54-b1fe-c07886a744bf.jpg', '<p>&nbsp;</p><p><br></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t3193/155/10049907906/6034/811c4edf/58d8b3caN76343850.jpg\"></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t3049/31/9885837648/151867/53215f4e/58d8b3c7N0d14a35b.jpg\" height=\"1046\" width=\"730\"></p><p>尺码&nbsp;因不同的计量方法，测量允许1-3cm内误差（CM）</p><p>身高/净腰围/型号衣长胸围腰围袖长肩宽臀围155/76A/XS10988641555/160/80A/S110906615.556/165/84A/M11294701658/170/88A/L114987416.560/175/92A/XL115102781762/175/96A/XXL116106821764/</p><p>尺码参考模特身高177胸围83臀围87腰围60穿着M码商品信息</p><p>货号：117261516</p><p>款名：SUCCESS A-LINE JERSEY DRESS (LOVE)</p><p>吊牌价：¥499</p><p>颜色：凤仙花绿</p><p>上身面料：棉62% 粘纤38%</p><p>裙子面料：聚酯纤维100%</p><p>裙子外层里料：聚酯纤维100%</p><p>裙子里层里料：聚酯纤维95% 氨纶5%</p><p>洗涤建议：30度水温下正常手洗，请避免含碱性的洗涤用品，反面洗涤、不宜用力拉伸，避免高温熨烫。</p><p>设计卖点</p><p>●&nbsp;卷边设计</p><p>●&nbsp;侧隐形拉链设计</p><p>商品指数</p><p>薄厚指数轻薄薄适中厚实弹性指数无弹微弹适中高弹温馨提示</p><p>模特所佩戴饰品、配件均为搭配使用，不做销售用途</p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t4693/359/1093471450/74417/b3cfa452/58d8b3c6N2182f229.jpg\" height=\"976\" width=\"610\"></p><p>模特佩戴配饰均为搭配不做销售用途。商品颜色请以实物为准，可参考平铺图颜色。</p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t4684/336/1100187792/63668/9eb6f7e5/58d8b3cbN90d33f16.jpg\" height=\"1168\" width=\"730\"></p><p>模特佩戴配饰均为搭配不做销售用途。商品颜色请以实物为准，可参考平铺图颜色。</p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t4336/157/2949027817/288230/91abc809/58d8b3cbN76ebaed4.jpg\" height=\"1168\" width=\"730\"></p><p><br></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t3061/28/9790079522/293207/29f14ecf/58d8b3c7N25c1d782.jpg\" height=\"1168\" width=\"730\"></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t3106/7/9888833736/299157/a03744a8/58d8b3ccN5b35eaee.jpg\" height=\"1168\" width=\"730\"></p><p>凤仙花绿</p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t4543/312/1124013398/1560/ce7d6763/58d8b3ccN6214e5dd.jpg\"></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t4366/333/3018457555/209162/d7922c46/58d8b3c7Nae32bd89.jpg\" width=\"730\"></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t4276/190/2954996750/176014/6f5fa72b/58d8b3c8N1e995fe8.jpg\" width=\"730\"></p><p><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t4579/9/1075876470/291275/d4aea6/58d8b3c9N5abb82e6.jpg\" width=\"730\"></p><p><br></p>', '249.00', '88', '50', '1', '2017-06-20 14:11:23', '2017-06-20 14:11:23'),
  ('36', '100011', '13.3英寸轻薄窄边框笔记本电脑(i7-7500U 8G 256GSSD FHD Win10)无忌金', '轻薄窄边框】七代i7双核CPU、SSD固态硬盘高端配备、全高清显示屏、超长续航无忧体验、全重1.2KG、轻薄便携优雅不', 'img/6c57e922-3f7b-4cac-847e-e8e0e47d4bae.jpg', 'img/6c57e922-3f7b-4cac-847e-e8e0e47d4bae.jpg', '<p><span style=\"color: rgb(102, 102, 102);\"><img src=\"https://img20.360buyimg.com/vc/jfs/t4285/211/2557721425/1642296/31a5e9fe/58d35b06N16c88a83.jpg\"></span></p>', '6999.00', '555', '0', '1', '2017-06-20 16:26:18', '2017-06-20 16:26:18'),
  ('37', '100024', 'sa', 'sasa', 'img/28c70855-480d-46a6-8fd9-724bd1e9159d.jpg',
         'img/28c70855-480d-46a6-8fd9-724bd1e9159d.jpg', '', '123.00', '1234', '0', '1', '2017-08-08 06:44:34',
   '2017-08-08 06:44:34');
COMMIT;

-- ----------------------------
--  Table structure for `ymall_user`
-- ----------------------------
DROP TABLE IF EXISTS `ymall_user`;
CREATE TABLE `ymall_user` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT '用户表id',
  `username`    VARCHAR(50) NOT NULL
  COMMENT '用户名',
  `password`    VARCHAR(50) NOT NULL
  COMMENT '用户密码，MD5加密',
  `email`       VARCHAR(50)          DEFAULT NULL,
  `phone`       VARCHAR(20)          DEFAULT NULL,
  `avatar`      VARCHAR(500)         DEFAULT NULL
  COMMENT '用户头像',
  `birthday`    DATE        NOT NULL DEFAULT '2017-01-01'
  COMMENT '生日',
  `description` VARCHAR(100)         DEFAULT NULL
  COMMENT '描述',
  `gender`      VARCHAR(5)  NOT NULL DEFAULT '保密'
  COMMENT '性别',
  `question`    VARCHAR(100)         DEFAULT NULL
  COMMENT '找回密码问题',
  `answer`      VARCHAR(100)         DEFAULT NULL
  COMMENT '找回密码答案',
  `role`        INT(4)      NOT NULL
  COMMENT '角色0-管理员,1-普通用户',
  `create_time` DATETIME    NOT NULL
  COMMENT '创建时间',
  `update_time` DATETIME    NOT NULL
  COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`username`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 29
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
--  Records of `ymall_user`
-- ----------------------------
BEGIN;
INSERT INTO `ymall_user` VALUES
  ('1', 'admin', '5FE4C20B8D41679113ED3F6859959F7A', 'admin@happyymall.com', '13800138000', 'img_url', '2017-01-01',
        '描述', '保密', '问题', '答案', '1', '2016-11-06 16:56:45', '2017-04-04 19:27:36'),
  ('13', 'ymall', 'F3478087B7BCDC5082A125C38435036F', 'ymall@126.com', '13800138001', 'img_url', '2017-06-26', '描述',
         '保密', '问题', '答案', '0', '2016-11-19 22:19:25', '2017-06-14 08:53:47'),
  ('22', 'zce', 'F3478087B7BCDC5082A125C38435036F', 'zcw@happyymall.com', NULL, NULL, '2017-01-01', NULL, '保密', NULL,
         NULL, '0', '2017-06-14 00:56:29', '2017-06-14 00:56:29'),
  ('23', 'zced', 'F3478087B7BCDC5082A125C38435036F', 'zcwf@happyymall.com', NULL, NULL, '2017-01-01', NULL, '保密', NULL,
         NULL, '0', '2017-06-14 00:56:52', '2017-06-14 00:56:52'),
  ('24', 'jjjjjjj', 'F3478087B7BCDC5082A125C38435036F', 'jjjjjjj@jj.com', NULL, NULL, '2017-01-01', NULL, '保密', NULL,
         NULL, '0', '2017-06-14 11:08:45', '2017-06-14 11:08:45'),
  ('25', 'lalal', 'F3478087B7BCDC5082A125C38435036F', 'zcwf@gogo.com', NULL, NULL, '2017-01-01', NULL, '保密', NULL, NULL,
   '0', '2017-06-15 10:42:01', '2017-06-15 10:42:01'),
  ('26', 'lalala', 'F3478087B7BCDC5082A125C38435036F', 'zcwf@goago.com', NULL, NULL, '2017-01-01', NULL, '保密', NULL,
         NULL, '0', '2017-06-15 10:50:09', '2017-06-15 10:50:09'),
  ('27', 'lalsala', 'F3478087B7BCDC5082A125C38435036F', 'zscwf@goago.com', NULL, NULL, '2017-01-01', NULL, '保密', NULL,
         NULL, '0', '2017-06-15 11:00:39', '2017-06-15 11:00:39'),
  ('28', 'zc_123', 'F3478087B7BCDC5082A125C38435036F', 'zc@124.com.cn', NULL, NULL, '2017-01-01', NULL, '保密', NULL,
         NULL, '0', '2017-06-23 17:30:11', '2017-06-23 17:30:11');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
