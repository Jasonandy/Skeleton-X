CREATE TABLE `sys_user` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` char(64) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `tel` varchar(64) DEFAULT NULL COMMENT '电话',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `dept_id` varchar(64) DEFAULT NULL COMMENT '部门id',
  `has_valid` varchar(32) DEFAULT NULL COMMENT '状态',
  `has_dept_admin` varchar(32) DEFAULT NULL COMMENT '部门管理员',
  `themes` varchar(64) DEFAULT NULL COMMENT '主题',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(50) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1562651818748 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户表';

CREATE TABLE `sys_role_permission` (
  `role_permission_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) unsigned DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`role_permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=677457522 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色与权限关联表';


CREATE TABLE `sys_role` (
  `role_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名',
  `role_remark` varchar(512) DEFAULT NULL COMMENT '角色描述',
  `state` bigint(8) DEFAULT NULL COMMENT '状态',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(50) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `role_sign` varchar(128) DEFAULT NULL COMMENT '角色标识,程序中判断使用,如"admin"',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色表';


CREATE TABLE `sys_permission` (
  `permission_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源id',
  `resource_name` varchar(128) DEFAULT NULL COMMENT '资源名称',
  `resource_type` bigint(8) DEFAULT NULL COMMENT '资源类型',
  `action_id` bigint(8) DEFAULT NULL COMMENT '动作',
  `action_name` varchar(128) DEFAULT NULL,
  `function_code` varchar(64) DEFAULT NULL COMMENT '功能编码',
  `permission_name` varchar(128) DEFAULT NULL COMMENT '权限名',
  `permission_sign` varchar(128) DEFAULT NULL COMMENT '权限标识,程序中判断使用,如"user:create"',
  `description` varchar(256) DEFAULT NULL COMMENT '权限描述,UI界面显示使用',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1494399268501 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='权限表';


CREATE TABLE `sys_module` (
  `module_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '模块id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点id',
  `module_url` varchar(512) DEFAULT NULL COMMENT '链接',
  `module_state` varchar(8) DEFAULT NULL COMMENT '状态',
  `has_visible` varchar(8) DEFAULT NULL COMMENT '是否可见',
  `actions` varchar(256) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `module_level` bigint(8) DEFAULT NULL COMMENT '部门id',
  `display_name` varchar(512) DEFAULT NULL COMMENT '显示名称',
  `module_leaf` bigint(8) DEFAULT NULL COMMENT '是否叶子',
  `module_seq` bigint(8) DEFAULT NULL COMMENT '排序',
  `module_name` varchar(512) DEFAULT NULL COMMENT '名称',
  `module_number` varchar(512) DEFAULT NULL COMMENT '编号',
  `module_description` varchar(512) DEFAULT NULL COMMENT '描述',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_user_id` bigint(20) DEFAULT NULL COMMENT '最新更新用户id',
  `last_updated_time` datetime DEFAULT NULL COMMENT '最新更新时间',
  `department_id` varchar(32) DEFAULT NULL COMMENT '部门id',
  `module_icon` varchar(512) DEFAULT NULL COMMENT '图标',
  `has_display` varchar(8) DEFAULT NULL COMMENT '是否首页显示',
  `display_order` bigint(8) DEFAULT NULL COMMENT '显示顺序',
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1494399268491 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块表';