CREATE TABLE ec_core.`manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID序列',
  `name` varchar(256) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户昵称',
  `email` varchar(128) COLLATE utf8_unicode_ci NOT NULL COMMENT '电子邮件',
  `mobile` varchar(128) COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号码联系方式',
  `weixin` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '微信联系方式',
  `password` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录密码',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='管理员用户信息';
