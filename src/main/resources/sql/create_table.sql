
-- ----------------------------
--- 项目启动默认创建 sys_log 系统日志
CREATE TABLE IF NOT EXISTS `sys_log` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `user_id` int(11) DEFAULT NULL COMMENT '用户id',
   `operation` varchar(32) DEFAULT NULL COMMENT '用户操作',
   `method` varchar(32) DEFAULT NULL COMMENT '请求方法',
   `params` varchar(255) DEFAULT NULL COMMENT '请求参数',
   `time` bigint(20) DEFAULT NULL COMMENT '执行时长（毫秒）',
   `ip_address` varchar(255) DEFAULT NULL COMMENT 'ip地址',
   `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
--- 项目启动默认创建 sys_user_info 用户信息
CREATE TABLE  IF NOT EXISTS `sys_user_info` (
     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
     `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
     `user_avatar` varchar(255) DEFAULT NULL COMMENT '用户图像',
     `email` varchar(32) DEFAULT NULL COMMENT '邮箱地址',
     `phone` varchar(32) DEFAULT NULL COMMENT '联系方式',
     `password` varchar(255) DEFAULT NULL COMMENT '密码',
     `creator` int(11) DEFAULT NULL COMMENT '创建者Id',
     `mender` int(11) DEFAULT NULL COMMENT '修改者Id',
     `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
     `modify_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
     `is_delete` tinyint(4) DEFAULT '1' COMMENT '状态，是否删除 1：正常，-1:删除',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户详情';

---