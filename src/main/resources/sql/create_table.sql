
-- 2024 年 1月 3日，新增测试表 tb_test
-- 判断表是否存在，如果不存在就创建
CREATE TABLE IF NOT EXISTS `tb_test` (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    is_deleted int(11) DEFAULT '1' COMMENT '是否删除 1 未删除 || -1 已删除',
    comment	varchar(255) DEFAULT null COMMENT '测试数据说明',
    PRIMARY KEY (`id`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8  COMMENT='测试数据表';