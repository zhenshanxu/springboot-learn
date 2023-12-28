package com.example.springbootlearn.service.account;

import java.util.Map;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 14:53:11
 * @Version 1.0
 */
public interface LoginService {

    /**
     * 账号登录
     *
     * @param accountToLogin  账号（手机号/邮箱）密码登录
     * @return 返回结果
     */
    Map<String, Object> accountToLogin(Map<String,Object>accountToLogin);

    /**
     * 验证码登录
     *
     * @param codeToLogin 账号（手机号/邮箱）验证码登录
     * @return 返回结果
     */
    Map<String, Object> codeToLogin(Map<String,Object>codeToLogin);

    /**
     * 用户注册
     *
     * @param signUpParam
     * @return
     */
    Map<String, Object> signUp(Map<String, Object> signUpParam);

    /**
     * 获取验证码
     *
     * @param account 账号（手机号/邮箱）
     * @return 返回值
     */
    Map<String, Object> getVerifyCode(String account);

}
