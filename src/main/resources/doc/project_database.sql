# Host: 120.78.120.128  (Version 5.7.29-log)
# Date: 2020-06-01 02:07:08
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "tbl_dept"
#

DROP TABLE IF EXISTS `tbl_dept`;
CREATE TABLE `tbl_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门表';

#
# Data for table "tbl_dept"
#

INSERT INTO `tbl_dept` VALUES (101,'研发部门',1,'0','0','admin','2018-03-16 11:33:00','jj','2020-05-31 15:35:17'),(102,'市场部门',2,'0','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00'),(103,'测试部门',3,'0','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00'),(104,'财务部门',4,'0','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00'),(105,'运维部门',5,'0','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00'),(106,'市场部门',6,'0','1','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00'),(108,'吉儿部门',0,'0','1','jj','2020-05-31 17:32:03','jj','2020-05-31 17:32:18'),(109,'秀儿部门',0,'0','0','jj','2020-05-31 17:39:14','jj','2020-05-31 17:39:54'),(110,'吉尔部门',0,'1','1','jj','2020-05-31 17:39:30','',NULL);

#
# Structure for table "tbl_menu"
#

DROP TABLE IF EXISTS `tbl_menu`;
CREATE TABLE `tbl_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` int(11) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT '' COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1072 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

#
# Data for table "tbl_menu"
#

INSERT INTO `tbl_menu` VALUES (1,'系统管理',0,1,'#','M','0','','layui-icon-spread-left','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','系统管理目录'),(2,'系统监控',0,2,'#','M','0','','layui-icon-set-sm','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','系统监控目录'),(100,'用户管理',1,1,'/toShowUser','C','0','system:user:view','layui-icon-username','admin','2018-03-16 11:33:00','admin','2020-05-30 08:02:31','用户管理菜单'),(101,'角色管理',1,2,'/role/toShowRole','C','0','system:role:view','layui-icon-user','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','角色管理菜单'),(102,'菜单管理',1,3,'/menu','C','0','system:menu:view','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','菜单管理菜单'),(103,'部门管理',1,4,'/dept/toShowDept','C','0','system:dept:view','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','部门管理菜单'),(109,'在线用户',2,1,'/monitor/online','C','0','monitor:online:view','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','在线用户菜单'),(111,'数据监控',2,3,'/monitor/data','C','0','monitor:data:view','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','数据监控菜单'),(112,'服务监控',2,3,'/monitor/server','C','0','monitor:server:view','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','服务监控菜单'),(1000,'用户查询',100,1,'#','F','0','system:user:list','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1001,'用户新增',100,2,'#','F','0','system:user:add','layui-icon-addition','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1002,'用户修改',100,3,'#','F','0','system:user:edit','layui-icon-edit','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1003,'用户删除',100,4,'#','F','0','system:user:remove','layui-icon-delete','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1004,'用户导出',100,5,'#','F','0','system:user:export','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1005,'重置密码',100,6,'#','F','0','system:user:resetPwd','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1006,'角色查询',101,1,'#','F','0','system:role:list','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1007,'角色新增',101,2,'#','F','0','system:role:add','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1008,'角色修改',101,3,'#','F','0','system:role:edit','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1009,'角色删除',101,4,'#','F','0','system:role:remove','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1010,'角色导出',101,5,'#','F','0','system:role:export','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1011,'菜单查询',102,1,'/menu/select','F','0','system:menu:list','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1012,'菜单新增',102,2,'/menu/add','F','0','system:menu:add','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1013,'菜单修改',102,3,'/menu/edit','F','0','system:menu:edit','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1014,'菜单删除',102,4,'/menu/ids','F','0','system:menu:remove','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1015,'部门查询',103,1,'#','F','0','system:dept:list','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1016,'部门新增',103,2,'#','F','0','system:dept:add','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1017,'部门修改',103,3,'#','F','0','system:dept:edit','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1018,'部门删除',103,4,'#','F','0','system:dept:remove','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00',''),(1062,'test',0,1,'/hello','C','1','hello:view','icon','admin','2020-05-30 05:23:01','admin','2020-05-30 05:23:01','123'),(1063,'66666666',1062,66666666,'666666666','C','0','6','66666666666666666','admin','2020-05-30 05:26:17','admin','2020-05-30 06:21:20','666666666'),(1065,'123',0,12,'1231','M','0','231231','#','admin','2020-05-30 06:27:19','admin','2020-05-30 06:27:19','12312312'),(1066,'66666666666',1065,231,'/showMenu','C','0','231','#','admin','2020-05-30 06:27:59','jj','2020-05-31 10:25:35','test'),(1070,'菜单指定删除',102,0,'/menu/id','F','0','system:menu:remove','#','admin','2020-05-30 05:26:17','admin','2020-05-30 06:21:20',''),(1071,'213',1062,123123,'1231','F','0','23123','#','jj','2020-05-31 13:43:44','jj','2020-05-31 13:43:44','12312');

#
# Structure for table "tbl_role"
#

DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

#
# Data for table "tbl_role"
#

INSERT INTO `tbl_role` VALUES (1,'管理员','admin',1,'0','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','管理员'),(2,'普通角色','common',2,'0','0','admin','2018-03-16 11:33:00','admin','2019-08-07 16:24:20','普通角色'),(3,'测试角色','ceshi',4,'0','0','admin','2019-12-31 15:40:32','jj','2020-05-31 15:36:57','1'),(8,'2','2',2,'0','1','',NULL,'',NULL,''),(11,'33','2',1,'0','1','',NULL,'',NULL,''),(12,'ds','ad',55,'0','1','',NULL,'',NULL,''),(14,'11','11',11,'0','1','',NULL,'',NULL,''),(15,'清洁工','3',3,'0','1','',NULL,'jj','2020-05-31 09:26:05','3'),(16,'111','11',1,'0','1','',NULL,'',NULL,''),(17,'2222','1',1,'0','1','jj','2020-05-31 07:42:02','',NULL,''),(18,'胖纸全2','3',23,'0','1','jj','2020-05-31 07:49:46','jj','2020-05-31 09:09:54','23'),(19,'2222222','2',2,'0','1','jj','2020-05-31 07:53:53','',NULL,'2'),(20,'啊','2',2,'0','1','',NULL,'jj','2020-05-31 09:09:04','2'),(21,'test','ccc',3,'0','1','jj','2020-05-31 15:42:50','',NULL,'dd'),(22,'1','1',1,'0','1','jj','2020-05-31 16:49:53','',NULL,'1'),(23,'1','1',1,'0','1','jj','2020-05-31 17:09:15','',NULL,'1'),(24,'1','1',1,'0','1','jj','2020-05-31 17:13:13','',NULL,'1'),(25,'1','1',1,'0','1','jj','2020-05-31 17:17:26','',NULL,'1');

#
# Structure for table "tbl_roles_menus"
#

DROP TABLE IF EXISTS `tbl_roles_menus`;
CREATE TABLE `tbl_roles_menus` (
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`menu_id`,`role_id`) USING BTREE,
  KEY `FKcngg2qadojhi3a651a5adkvbq` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色菜单关联';

#
# Data for table "tbl_roles_menus"
#

INSERT INTO `tbl_roles_menus` VALUES (1,1),(1,2),(2,1),(2,2),(3,2),(5,2),(6,2),(9,2),(10,2),(11,2),(14,2),(15,2),(18,2),(19,2),(21,2),(23,2),(24,2),(27,2),(28,2),(30,2),(33,2),(34,2),(35,2),(36,2),(37,2),(38,2),(39,2),(44,2),(48,2),(49,2),(50,2),(77,2),(78,2),(79,2),(100,1),(101,1),(102,1),(103,1),(109,1),(111,1),(112,1),(116,1),(1000,1),(1001,1),(1002,1),(1003,1),(1004,1),(1005,1),(1006,1),(1007,1),(1008,1),(1009,1),(1010,1),(1011,1),(1012,1),(1013,1),(1014,2),(1015,1),(1017,1),(1018,1),(1019,1),(1062,1);

#
# Structure for table "tbl_user"
#

DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(100) DEFAULT '' COMMENT '盐加密',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` time DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

#
# Data for table "tbl_user"
#

INSERT INTO `tbl_user` VALUES (1,101,'admin','密码123','86521760@qq.com','18538062907','0',NULL,'837b614697139ee473e551d04c0efe95','123','0','0','admin','2018-03-16 11:33:00','admin','21:15:13','管理员'),(3,101,'chenjian','陈建','chenjian3822515@163.com','18538062906','0',NULL,'f4a77e1417b03bf32d4b33a137282093','4bd21f','0','0','admin','2019-04-18 11:48:31','admin','12:31:29',''),(4,101,'zhangsan','张三','86587432@qq.com','18538062999','0',NULL,'9da8d52c4a42978c876621546ccf298e','6ac61b','0','0','admin','2019-08-07 15:46:18','admin','16:24:29',''),(7,NULL,'jj','','1441507758@qq.com','18420014153','0',NULL,'e7fd5a6f0fbb845d18ebbc2abcdb89e7','f5fe096e-8021-49f6-85b3-455a2053ce7d','0','0','',NULL,'',NULL,''),(9,NULL,'xiaopang','小庞','gd.pang@foxmail.com','10086','0',NULL,'','','1','0','5',NULL,'xiaopang',NULL,'修改信息'),(17,NULL,'zhansan','张三','zhansan@foxmail.com','10086000','1',NULL,'ffa223001d5532a6bcd9d7b2764c6b65','83faf739-fd4b-445d-a865-6e133ddb0804','0','0','gd.pang',NULL,'',NULL,'添加张三'),(18,NULL,'twodog','二狗','twodog@foxmail.com','10086000','1',NULL,'714e7b4c31369a324fac9ed06c315f7b','5ce38355-3035-43ad-927b-acb0037feade','0','0','gd.pang',NULL,'',NULL,'添加二狗');

#
# Structure for table "tbl_users_roles"
#

DROP TABLE IF EXISTS `tbl_users_roles`;
CREATE TABLE `tbl_users_roles` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  KEY `FKq4eq273l04bpu4efj0jd0jb98` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户角色关联';

#
# Data for table "tbl_users_roles"
#

INSERT INTO `tbl_users_roles` VALUES (1,1),(3,2),(4,1),(7,1);
