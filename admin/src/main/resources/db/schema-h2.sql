
DROP TABLE IF EXISTS `sys_param`;

CREATE TABLE `sys_param` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `param_code` varchar(50) DEFAULT NULL COMMENT '系统参数',
  `param_desc` varchar(200) DEFAULT NULL COMMENT '参数描述',
  `value` varchar(100) DEFAULT NULL COMMENT '参数值',
  `update_date` date DEFAULT NULL COMMENT '更新时间',
  `hide` varchar(2) DEFAULT NULL COMMENT '是否隐藏(1:是；0:否；)',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL,
  `resource_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级id',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限',
  `resource_type` varchar(2) DEFAULT NULL COMMENT '资源类型--\r\n0：目录；\r\n1：菜单；\r\n2：按钮；\r\n3：数据； 4：综合',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `sort_order` bigint(255) DEFAULT NULL COMMENT '排序',
  `subsys_id` bigint(20) DEFAULT NULL COMMENT '子系统id',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `description` varchar(500) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `sys_role_resource`;

CREATE TABLE `sys_role_resource` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `sys_subsystem`;

CREATE TABLE `sys_subsystem` (
  `id` bigint(20) NOT NULL,
  `subsys_name` varchar(255) DEFAULT NULL COMMENT '子系统名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(36) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `real_name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT 'sys_user表id',
  `role_id` bigint(20) DEFAULT NULL COMMENT 'sys_role表id',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `sys_website_info`;

CREATE TABLE `sys_website_info` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '站点名称',
  `logo_url` varchar(255) NOT NULL COMMENT '网站logo地址',
  `phone` varchar(32) NOT NULL COMMENT '联系电话',
  `email` varchar(255) NOT NULL COMMENT '联系邮箱',
  `copy_right` varchar(255) NOT NULL COMMENT '版权',
  `record_number` varchar(255) NOT NULL COMMENT '备案号',
  `technical_support` varchar(255) NOT NULL COMMENT '技术支持',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `dr` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除：1删除，0未删除',
  PRIMARY KEY (`id`)
)