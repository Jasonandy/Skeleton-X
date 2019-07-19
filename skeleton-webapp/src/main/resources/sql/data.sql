INSERT INTO `sys_permission` VALUES (1, 1, '首页', 1, 1, '', '/index', '首页', '', '');
INSERT INTO `sys_permission` VALUES (2, 2, '登录', 1, 1, NULL, '/account/login.do', '登录页面', NULL, NULL);

INSERT INTO `sys_role` VALUES (1, 'admin', '管理员', NULL, NULL, '', '2019-7-19 16:07:33', 'admin');
INSERT INTO `sys_role` VALUES (2, 'operator', '普通用户', NULL, NULL, '', '2019-7-19 16:07:51', 'normal');

INSERT INTO `sys_role_permission` VALUES (1, 1, 1);

INSERT INTO `sys_user` VALUES (1, 'admin', '123456', '系统管理员', '', '', '', '1', '1', '', NULL, '', '2019-7-19 12:59:08');

INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 1, 2);

