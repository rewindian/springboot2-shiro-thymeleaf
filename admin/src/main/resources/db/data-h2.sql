
DELETE FROM `sys_param`;

INSERT INTO `sys_param`(`id`, `param_code`, `param_desc`, `value`, `update_date`, `hide`) VALUES (1, 'SUPER_ADMIN', '超级管理员', 'admin', NULL, '0');
INSERT INTO `sys_param`(`id`, `param_code`, `param_desc`, `value`, `update_date`, `hide`) VALUES (2, 'NORMAL_USER', '普通用户', 'normal', NULL, '0');

DELETE FROM `sys_user`;

INSERT INTO `sys_user`(`id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `dept_id`, `create_time`, `real_name`, `avatar`) VALUES (1, 'admin', '3bf6e666ad793b1e2c69b3da472504d4', 'YzcmCZNvbXocrsz9dm8e', 'admin@abc.com', '13974568473', 1, NULL, '2018-11-27 11:19:11', '管理员', NULL);
INSERT INTO `sys_user`(`id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `dept_id`, `create_time`, `real_name`, `avatar`) VALUES (2, 'test', '83d1052490a2868a4d379c1213fe5736', 'YzcmCZNvbXocrsz9dm8e', 'dsadsada@cdas.cn', '12345678900', 1, NULL, '2019-04-08 15:20:54', '测试', NULL);

DELETE FROM `sys_role`;

INSERT INTO `sys_role`(`id`, `role_name`, `description`) VALUES (1, '超级管理员', '超级管理员');
INSERT INTO `sys_role`(`id`, `role_name`, `description`) VALUES (2, '普通用户', '普通用户');


DELETE FROM `sys_user_role`;

INSERT INTO `sys_user_role`(`id`, `user_id`, `role_id`) VALUES (1, 1, 1);
INSERT INTO `sys_user_role`(`id`, `user_id`, `role_id`) VALUES (2, 2, 2);


DELETE FROM `sys_resource`;

INSERT INTO `sys_resource`(`id`, `resource_name`, `parent_id`, `permission`, `resource_type`, `description`, `sort_order`, `subsys_id`, `icon`) VALUES (1, '测试权限', 0, 'test_permission', '0', '测试用', 1, 2, NULL);

DELETE FROM `sys_role_resource`;

INSERT INTO `sys_role_resource`(`id`, `role_id`, `resource_id`) VALUES (1, 2, 1);
