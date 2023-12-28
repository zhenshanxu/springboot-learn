package com.example.springbootlearn.config.custom;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 10:28:07
 * @Version 1.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "spring.upload-config")
public class UploadConfig {

    /**
     * 文件上传根目录
     */
    private String rootFolder;
}
