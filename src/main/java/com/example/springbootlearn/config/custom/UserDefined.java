package com.example.springbootlearn.config.custom;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 10:31:18
 * @Version 1.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "spring.user-defined")
public class UserDefined {

    /**
     * swagger Enable 状态
     */
    private Boolean swaggerEnable;

    /**
     * Salt值
     */
    private String md5Salt;
}
