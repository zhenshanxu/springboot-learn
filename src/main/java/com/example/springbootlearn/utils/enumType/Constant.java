package com.example.springbootlearn.utils.enumType;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Project springboot-learn
 * @Description 常数, 常量
 * @Author xuzhenshan
 * @Date 2023/12/28 09:26:34
 * @Version 1.0
 */

@Slf4j
@Component
public class Constant {

    /**
     * 验证码有效时间
     */
    public static final long VERIFY_CODE_VALID_TIME = 60;

    /**
     * token有效时间(24小时)
     */
    public static final long TOKEN_EXPIRE_TIME = 60 * 60 * 1000L;

    /**
     * 用户登录token存放文件夹
     */
    public static final String LOGIN_TOKEN = "loginToken";

    /**
     * JWT_ID
     */
    public static final String JWT_ID = UUID.randomUUID().toString();

    /**
     * 私钥
     */
    public static final String TOKEN_SECRET = "encryption encoding";

    /**
     * 邮件格式规则
     */
    public static final String MAIL_RULES = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";

    /**
     * 电话格式规则
     */
    public static final String PHONE_RULES = "^1[3456789]\\d{9}$|^(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{7,14}$";

    /**
     * 错误值-错误状态
     */
    public static final String ERROR_VALUE = "error";

    /**
     * 删除状态
     */
    public static final int DELETE_STATUS = -1;

    /**
     * 默认初始状态
     */
    public static final int INITIAL_STATUS = 1;

    /**
     * 令牌 - 标识
     */
    public static final String TOKEN_SYMBOL = "token";

    /**
     * 账号 - 标识
     */
    public static final String ACCOUNT = "account";

    /**
     * 账号密码 - 标识
     */
    public static final String PASSWORD = "password";

    /**
     * 设置默认的账号密码
     */
    public static final String ACQUIESCENT_PASSWORD = "123456";

    /**
     * 标识 - 验证码
     */
    public static final String VERIFY_CODE = "verifyCode";

    /**
     * 标识 - 内容
     */
    public static final String CONTENT = "content";

    /**
     * 颜色 - 黑
     */
    public static final int BLACK = 0xFF000000;

    /**
     * 颜色 - 白
     */
    public static final int WHITE = 0xFFFFFFFF;

    /**
     * 状态 - 启用
     */
    public static final int ENABLE_STATUS = 1;

    /**
     * 状态 - 关闭
     */
    public static final int CLOSE_STATUS = 0;

}
