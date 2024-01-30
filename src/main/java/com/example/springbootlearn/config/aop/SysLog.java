package com.example.springbootlearn.config.aop;

import java.lang.annotation.*;

/**
 * @Project springboot-learn
 * @Description 系统日志aop 配置
 * @Author xuzhenshan
 * @Date 2024/1/25 11:30:02
 * @Version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "暂无内容";

    String title() default "暂时标题";

    String describe() default "暂无介绍";

}
