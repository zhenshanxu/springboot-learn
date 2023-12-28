package com.example.springbootlearn.config.custom;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project springboot-learn
 * @Description 邮箱配置
 * @Author xuzhenshan
 * @Date 2023/12/28 10:27:18
 * @Version 1.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class MailConfig {

    /**
     * 邮件配置名
     */
    private String userName;

    /**
     * 邮件授权
     */
    private String password;

    /**
     * 邮箱host
     */
    private String host;

}
