package com.example.springbootlearn.config;

import com.example.springbootlearn.config.custom.MailConfig;
import com.example.springbootlearn.config.custom.UploadConfig;
import com.example.springbootlearn.config.custom.UserDefined;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 10:37:03
 * @Version 1.0
 */

@Component
public class SystemObject {

    /**
     * 自定义 配置
     */
    public static UserDefined userDefined;

    @Autowired
    void setMyConfig(UserDefined userDefined) {
        SystemObject.userDefined = userDefined;
    }

    /**
     * 邮件配置
     */
    public static MailConfig mailConfig;

    @Autowired
    void setMailConfig(MailConfig mailConfig) {
        SystemObject.mailConfig = mailConfig;
    }

    /**
     * 上传地址配置
     */
    public static UploadConfig uploadConfig;

    @Autowired
    void setUploadConfig(UploadConfig uploadConfig) {
        SystemObject.uploadConfig = uploadConfig;
    }


}
