package com.example.springbootlearn.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Project springboot-learn
 * @Description 数据库 表结构字段配置
 * @Author xuzhenshan
 * @Date 2024/1/20 14:57:43
 * @Version 1.0
 */

@Slf4j
@Component
public class CreateColumnConfig {

    private final JdbcTemplate jdbcTemplate;

    public CreateColumnConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // @PostConstruct
    void init() {
        try {
            // 查询数据库表是否存在
            Object index = jdbcTemplate.queryForObject("SHOW TABLES LIKE 'table_name';", Object.class);
            if (NumberUtils.INTEGER_ONE.equals(index)) {
                return;
            }
            // 判断字段是否存在
            Integer i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = 'tb_test' and column_name = 'ERROR_CODE';", Integer.class);
            if (NumberUtils.INTEGER_ZERO.equals(i)) {
                // 新增字段
                jdbcTemplate.execute("alter table tb_test ADD error_code integer(10);");
            }
        } catch (InvalidResultSetAccessException e) {
            log.error("项目启动新增字段失败：{}", e.getMessage());
        } catch (DataAccessException e) {
            log.error("项目启动查询数据库异常：{}", e.getMessage());
        }
    }
}
