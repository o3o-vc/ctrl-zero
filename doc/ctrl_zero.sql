/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : localhost:3306
 Source Schema         : ctrl_zero

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 30/08/2023 17:30:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` bigint NOT NULL,
  `client_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `secret` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `redirect_uri` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `scope` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `auth_method` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `grant_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of client
-- ----------------------------
BEGIN;
INSERT INTO `client` (`id`, `client_id`, `secret`, `redirect_uri`, `scope`, `auth_method`, `grant_type`) VALUES (1, 'client', 'secret', 'https://springone.io/authorized', 'openid', 'client_secret_basic', 'authorization_code');
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` bigint DEFAULT NULL,
  `order_no` int DEFAULT NULL,
  `is_leaf` int DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `component` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `requires_auth` int DEFAULT NULL,
  `keep_alive` int DEFAULT NULL,
  `i18n_title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `local_icon` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `href` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `single_layout` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `creator` bigint DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `creator_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `modifier` bigint DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `modifier_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (1, 0, 1, 0, 'dashboard', '/dashboard', 'basic', '仪表盘', 'mdi:monitor-dashboard', 1, NULL, 'routes.dashboard._value', NULL, NULL, NULL, 1, '2023-08-27 21:07:52', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (2, 1, NULL, 1, 'dashboard_analysis', '/dashboard/analysis', 'self', '分析页', 'icon-park-outline:analysis', 1, NULL, 'routes.dashboard.analysis', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (3, 1, NULL, 1, 'dashboard_workbench', '/dashboard/workbench', 'self', '工作台', 'icon-park-outline:workbench', 1, NULL, 'routes.dashboard.workbench', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (4, 0, 2, 0, 'document', '/document', 'basic', '文档', 'mdi:file-document-multiple-outline', 1, NULL, 'routes.document._value', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (5, 4, NULL, 1, 'document_vue', '/document/vue', 'self', 'vue文档', 'logos:vue', 1, NULL, 'routes.document.vue', NULL, NULL, 'blank', 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (6, 4, NULL, 1, 'document_vite', '/document/vite', 'self', 'vite文档', 'logos:vitejs', 1, NULL, 'routes.document.vite', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (7, 4, NULL, 1, 'document_naive', '/document/naive', 'self', 'naive文档', 'logos:naiveui', 1, NULL, 'routes.document.naive', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (8, 4, NULL, 1, 'document_project', '/document/project', 'self', '项目文档', NULL, 1, NULL, 'routes.document.project', 'logo', NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (9, 4, NULL, 1, 'document_project-link', '/document/project-link', NULL, '项目文档(外链)', NULL, 1, NULL, 'routes.document.project-link', 'logo', 'https://docs.soybean.pro/', NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (10, 0, 3, 0, 'component', '/component', 'basic', '组件示例', 'cib:app-store', 1, NULL, 'routes.component._value', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (11, 10, NULL, 1, 'component_button', '/component/button', 'self', '按钮', 'mdi:button-cursor', 1, NULL, 'routes.component.button', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (12, 10, NULL, 1, 'component_card', '/component/card', 'self', '卡片', 'mdi:card-outline', 1, NULL, 'routes.component.card', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (13, 10, NULL, 1, 'component_table', '/component/table', 'self', '表格', 'mdi:table-large', 1, NULL, 'routes.component.table', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (14, 0, 4, 0, 'plugin', '/plugin', 'basic', '插件示例', 'clarity:plugin-line', 1, NULL, 'routes.plugin._value', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (15, 14, NULL, 0, 'plugin_charts', '/plugin/charts', 'multi', '图表', 'mdi:chart-areaspline', 1, NULL, 'routes.plugin.charts._value', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (16, 15, NULL, 1, 'plugin_charts_echarts', '/plugin/charts/echarts', 'self', 'ECharts', 'simple-icons:apacheecharts', 1, NULL, 'routes.plugin.charts.echarts', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (17, 15, NULL, 1, 'plugin_charts_antv', '/plugin/charts/antv', 'self', 'AntV', 'simple-icons:antdesign', 1, NULL, 'routes.plugin.charts.antv', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (18, 14, NULL, 1, 'plugin_map', '/plugin/map', 'self', '地图', 'mdi:map', 1, NULL, 'routes.plugin.map', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (19, 14, NULL, 1, 'plugin_video', '/plugin/video', 'self', '视频', 'mdi:video', 1, NULL, 'routes.plugin.video', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (20, 14, NULL, 0, 'plugin_editor', '/plugin/editor', 'multi', '编辑器', 'icon-park-outline:editor', 1, NULL, 'routes.plugin.editor._value', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (21, 20, NULL, 1, 'plugin_editor_quill', '/plugin/editor/quill', 'self', '富文本编辑器', 'mdi:file-document-edit-outline', 1, NULL, 'routes.plugin.editor.quill', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (22, 20, NULL, 1, 'plugin_editor_markdown', '/plugin/editor/markdown', 'self', 'markdown编辑器', 'ri:markdown-line', 1, NULL, 'routes.plugin.editor.markdown', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (23, 14, NULL, 1, 'plugin_swiper', '/plugin/swiper', 'self', 'Swiper插件', 'simple-icons:swiper', 1, NULL, 'routes.plugin.swiper', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (24, 14, NULL, 1, 'plugin_copy', '/plugin/copy', 'self', '剪贴板', 'mdi:clipboard-outline', 1, NULL, 'routes.plugin.copy', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (25, 14, NULL, 1, 'plugin_icon', '/plugin/icon', 'self', '图标', NULL, 1, NULL, 'routes.plugin.icon', 'custom-icon', NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (26, 14, NULL, 1, 'plugin_print', '/plugin/print', 'self', '打印', 'mdi:printer', 1, NULL, 'routes.plugin.print', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (27, 0, 5, 0, 'auth-demo', '/auth-demo', 'basic', '权限示例', 'ic:baseline-security', 1, NULL, 'routes.auth-demo._value', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (28, 27, NULL, 1, 'auth-demo_permission', '/auth-demo/permission', 'self', '权限切换', 'ic:round-construction', 1, NULL, 'routes.auth-demo.permission', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (29, 27, NULL, 1, 'auth-demo_super', '/auth-demo/super', 'self', '超级管理员可见', 'ic:round-supervisor-account', 1, NULL, 'routes.auth-demo.super', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (30, 0, 6, 0, 'function', '/function', 'basic', '功能', 'icon-park-outline:all-application', 1, NULL, 'routes.function._value', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (31, 30, NULL, 1, 'function_tab', '/function/tab', 'self', 'Tab', 'ic:round-tab', 1, NULL, 'routes.function.tab', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (32, 30, NULL, 1, 'function_tab-detail', '/function/tab-detail', 'self', 'Tab Detail', 'ic:round-tab', 1, NULL, NULL, NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (33, 30, NULL, 1, 'function_tab-multi-detail', '/function/tab-multi-detail', 'self', 'Tab Multi Detail', 'ic:round-tab', 1, NULL, NULL, NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (34, 0, 7, 0, 'exception', '/exception', 'basic', '异常页', 'ant-design:exception-outlined', 1, NULL, 'routes.exception._value', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (35, 34, NULL, 1, 'exception_403', '/exception/403', 'self', '异常页403', 'ic:baseline-block', 1, NULL, 'routes.exception.403', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (36, 34, NULL, 1, 'exception_404', '/exception/404', 'self', '异常页404', 'ic:baseline-web-asset-off', 1, NULL, 'routes.exception.404', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (37, 34, NULL, 1, 'exception_500', '/exception/500', 'self', '异常页500', 'ic:baseline-wifi-off', 1, NULL, 'routes.exception.500', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (38, 0, 8, 0, 'multi-menu', '/multi-menu', 'basic', '多级菜单', 'carbon:menu', 1, NULL, 'routes.multi-menu._value', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (39, 38, NULL, 0, 'multi-menu_first', '/multi-menu/first', 'multi', '一级菜单', 'mdi:menu', 1, NULL, 'routes.multi-menu.first._value', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (40, 39, NULL, 1, 'multi-menu_first_second', '/multi-menu/first/second', 'self', '二级菜单', 'mdi:menu', 1, NULL, 'routes.multi-menu.first.second', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (41, 39, NULL, 0, 'multi-menu_first_second-new', '/multi-menu/first/second-new', 'multi', '二级菜单(有子菜单)', 'mdi:menu', 1, NULL, 'routes.multi-menu.first.second-new._value', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (42, 41, NULL, 1, 'multi-menu_first_second-new_third', '/multi-menu/first/second-new/third', 'self', '三级菜单', 'mdi:menu', 1, NULL, 'routes.multi-menu.first.second-new.third', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (43, 0, 9, 0, 'management', '/management', 'basic', '系统管理', 'carbon:cloud-service-management', 1, NULL, 'routes.management._value', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (44, 43, NULL, 1, 'management_auth', '/management/auth', 'self', '权限管理', 'ic:baseline-security', 1, NULL, 'routes.management.auth', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (45, 43, NULL, 1, 'management_role', '/management/role', 'self', '角色管理', 'carbon:user-role', 1, NULL, 'routes.management.role', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (46, 43, NULL, 1, 'management_user', '/management/user', 'self', '用户管理', 'ic:round-manage-accounts', 1, NULL, 'routes.management.user', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (47, 43, NULL, 1, 'management_route', '/management/route', 'self', '路由管理', 'material-symbols:route', 1, NULL, 'routes.management.route', NULL, NULL, NULL, 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (48, 0, 10, 1, 'about', '/about', 'self', '关于', 'fluent:book-information-24-regular', 1, 1, 'routes.about', NULL, NULL, 'basic', 1, '2023-08-27 21:07:53', NULL, NULL, NULL, NULL);
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (49, 0, 0, 0, 'test', '/test', 'basic', '测试', 'mdi:all-inclusive', 1, 0, '', '', '', '', 1, '2023-08-28 16:52:25', '', 1, '2023-08-28 16:59:26', '');
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (51, 49, 0, 0, 'test_test2', '/test/test2', 'multi', '测试2', 'mdi:all-inclusive', 1, 0, '', '', '', '', 1, '2023-08-28 17:00:56', '', 1, '2023-08-30 17:29:09', '');
INSERT INTO `menu` (`id`, `parent_id`, `order_no`, `is_leaf`, `name`, `path`, `component`, `title`, `icon`, `requires_auth`, `keep_alive`, `i18n_title`, `local_icon`, `href`, `single_layout`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (52, 49, 0, 1, 'test_test3', '/test/test3', 'self', '测试3', 'mdi:all-inclusive', 1, 0, '', '', '', '', 1, '2023-08-28 17:01:59', '', NULL, '2023-08-28 16:59:26', '');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` int DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `creator` bigint DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `creator_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `modifier` bigint DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `modifier_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`id`, `name`, `code`, `status`, `description`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (1, '测试', 'test', 1, NULL, 1, '2023-08-29 14:07:24', NULL, NULL, NULL, NULL);
INSERT INTO `role` (`id`, `name`, `code`, `status`, `description`, `creator`, `created`, `creator_name`, `modifier`, `modified`, `modifier_name`) VALUES (5, '管理员', 'admin', 1, '', 1, '2023-08-30 13:54:37', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for role_menu_mapping
-- ----------------------------
DROP TABLE IF EXISTS `role_menu_mapping`;
CREATE TABLE `role_menu_mapping` (
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  `is_mark` int NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of role_menu_mapping
-- ----------------------------
BEGIN;
INSERT INTO `role_menu_mapping` (`role_id`, `menu_id`, `is_mark`) VALUES (1, 1, 0);
INSERT INTO `role_menu_mapping` (`role_id`, `menu_id`, `is_mark`) VALUES (1, 2, 0);
INSERT INTO `role_menu_mapping` (`role_id`, `menu_id`, `is_mark`) VALUES (1, 3, 0);
INSERT INTO `role_menu_mapping` (`role_id`, `menu_id`, `is_mark`) VALUES (1, 49, 0);
INSERT INTO `role_menu_mapping` (`role_id`, `menu_id`, `is_mark`) VALUES (1, 51, 0);
INSERT INTO `role_menu_mapping` (`role_id`, `menu_id`, `is_mark`) VALUES (1, 52, 0);
COMMIT;

-- ----------------------------
-- Table structure for role_user_mapping
-- ----------------------------
DROP TABLE IF EXISTS `role_user_mapping`;
CREATE TABLE `role_user_mapping` (
  `role_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of role_user_mapping
-- ----------------------------
BEGIN;
INSERT INTO `role_user_mapping` (`role_id`, `user_id`) VALUES (1, 1);
INSERT INTO `role_user_mapping` (`role_id`, `user_id`) VALUES (1, 3);
INSERT INTO `role_user_mapping` (`role_id`, `user_id`) VALUES (1, 4);
INSERT INTO `role_user_mapping` (`role_id`, `user_id`) VALUES (1, 9);
COMMIT;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `mark` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `tags` json DEFAULT NULL,
  `creator` bigint DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modifier` bigint DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of test
-- ----------------------------
BEGIN;
INSERT INTO `test` (`id`, `name`, `mark`, `tags`, `creator`, `created`, `modifier`, `modified`) VALUES (1, 'test', 'haha', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `test` (`id`, `name`, `mark`, `tags`, `creator`, `created`, `modifier`, `modified`) VALUES (2, 'test', 'haha', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `test` (`id`, `name`, `mark`, `tags`, `creator`, `created`, `modifier`, `modified`) VALUES (3, 'test', 'haha', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `test` (`id`, `name`, `mark`, `tags`, `creator`, `created`, `modifier`, `modified`) VALUES (4, 'test', 'haha', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `test` (`id`, `name`, `mark`, `tags`, `creator`, `created`, `modifier`, `modified`) VALUES (5, 'test', 'haha', NULL, 1, '2023-07-19 11:26:56', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `authority` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` int DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `creator` bigint DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modifier` bigint DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `creator_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `modifier_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `name`, `username`, `password`, `authority`, `status`, `age`, `gender`, `phone`, `email`, `creator`, `created`, `modifier`, `modified`, `creator_name`, `modifier_name`) VALUES (1, '', 'Soybean', '$2a$10$M.E1.0QiRv1Hv8rIlNEun.f95VtG47gpc1SJ3AkdEtgfEQT6GVR36', NULL, '1', 0, 1, '13333333333', 'admin@qq.com', 1, '2023-08-25 17:43:39', 1, '2023-08-30 16:05:23', '', NULL);
INSERT INTO `user` (`id`, `name`, `username`, `password`, `authority`, `status`, `age`, `gender`, `phone`, `email`, `creator`, `created`, `modifier`, `modified`, `creator_name`, `modifier_name`) VALUES (3, NULL, 'admin', '$2a$10$IAiIxzSSL/9D41AlqoXfjOkLdNw.GaHSCzzeSMLqiV1pYAjQhGD1S', NULL, '1', 1, 0, '13333333333', 'admin@a.com', 1, '2023-08-26 11:27:35', NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `name`, `username`, `password`, `authority`, `status`, `age`, `gender`, `phone`, `email`, `creator`, `created`, `modifier`, `modified`, `creator_name`, `modifier_name`) VALUES (4, NULL, 'test', '$2a$10$woNG37gl/0PsMFBAhj/7U.xdgqn.X69.aM4c0NvYN2fqL20vpR35e', NULL, '2', 1, 1, '15555555555', 'test@test.com', 1, '2023-08-26 11:28:21', 1, '2023-08-26 13:44:51', '', '');
INSERT INTO `user` (`id`, `name`, `username`, `password`, `authority`, `status`, `age`, `gender`, `phone`, `email`, `creator`, `created`, `modifier`, `modified`, `creator_name`, `modifier_name`) VALUES (5, NULL, 'admin', '$2a$10$O6bigzv/PqUkVJlUH03N2Ok4smOXDeMUxQbVVvJyNNOw5zOKr2KPe', NULL, '1', 1, 0, '13333333333', 'admin@a.com', 1, '2023-08-26 11:29:53', 1, '2023-08-26 13:38:55', '', '');
INSERT INTO `user` (`id`, `name`, `username`, `password`, `authority`, `status`, `age`, `gender`, `phone`, `email`, `creator`, `created`, `modifier`, `modified`, `creator_name`, `modifier_name`) VALUES (6, NULL, '哈哈', '$2a$10$2NVjQu3bmo4bAD9MWH7hN.chbfuy75F1Hmu5eStYiQ4YN6.9pBoBa', NULL, '1', 4, 1, '13222222222', 'hh@t.com', 1, '2023-08-26 11:32:34', NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `name`, `username`, `password`, `authority`, `status`, `age`, `gender`, `phone`, `email`, `creator`, `created`, `modifier`, `modified`, `creator_name`, `modifier_name`) VALUES (8, NULL, 'kevin', '$2a$10$GmRIVTmucalnfkTyGzOTjuX5M21H/P.0W3Sjk/W8chRpHRiIUeOAa', NULL, '1', 0, 0, '13553535355', '', 1, '2023-08-26 11:37:30', NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `name`, `username`, `password`, `authority`, `status`, `age`, `gender`, `phone`, `email`, `creator`, `created`, `modifier`, `modified`, `creator_name`, `modifier_name`) VALUES (9, NULL, '牛牛', '$2a$10$68bIap18vdnoZnTo/sUKXOlG92wvLor.2ZtYu8N/s/N642PME6rtG', NULL, '1', 2, 0, '13244444444', '', 1, '2023-08-26 11:46:20', NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
