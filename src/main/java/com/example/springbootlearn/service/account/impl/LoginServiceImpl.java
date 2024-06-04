package com.example.springbootlearn.service.account.impl;

import com.example.springbootlearn.config.jwt.JwtUtil;
import com.example.springbootlearn.config.redis.RedisService;
import com.example.springbootlearn.entity.user.UserInfoBean;
import com.example.springbootlearn.service.account.LoginService;
import com.example.springbootlearn.service.user.IUserInfoService;
import com.example.springbootlearn.utils.enumType.Common;
import com.example.springbootlearn.utils.enumType.Constant;
import com.example.springbootlearn.utils.send.MailSendUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 14:53:53
 * @Version 1.0
 */

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {


    private final IUserInfoService userInfoService;

    private final MailSendUtil mailSendUtil;
    private final RedisService redisService;

    public LoginServiceImpl(IUserInfoService userInfoService, MailSendUtil mailSendUtil, RedisService redisService) {
        this.userInfoService = userInfoService;
        this.mailSendUtil = mailSendUtil;
        this.redisService = redisService;
    }


    /**
     * 账号登录
     *
     * @param accountToLogin 账号（手机号/邮箱）密码登录
     * @return 返回值
     */
    @Override
    public Map<String, Object> accountToLogin(Map<String, Object> accountToLogin) {
        Map<String, Object> result = new HashMap<>();
        UserInfoBean userInfo = new UserInfoBean();
        String account = String.valueOf(accountToLogin.get(Constant.ACCOUNT));
        String password = String.valueOf(accountToLogin.get(Constant.PASSWORD));
        // 验证用户信息
        Map<String, Object> objectMap = checkUserInfo(account, userInfo);
        if (objectMap.containsKey(Constant.ERROR_VALUE)) {
            return objectMap;
        }
        UserInfoBean user = (UserInfoBean) objectMap.get("userInfo");
        if (!user.getPassword().equals(Common.getPasswordMd5(user.getId(), password,user.getSalt()))) {
            result.put(Constant.ERROR_VALUE, "密码输入错误,请重新输入!");
            return result;
        }
        // 将用户 token 放到 Redis 中
        String token = JwtUtil.createJwt(user.getId(), account);
        redisService.set(Constant.LOGIN_TOKEN + ":" + token, user, Constant.TOKEN_EXPIRE_TIME);
        result.put(Constant.TOKEN_SYMBOL, token);
        return result;
    }

    /**
     * 验证码登录
     *
     * @param codeToLogin 账号（手机号/邮箱）验证码登录
     * @return 返回值
     */
    @Override
    public Map<String, Object> codeToLogin(Map<String, Object> codeToLogin) {
        Map<String, Object> result = new HashMap<>();
        UserInfoBean userInfo = new UserInfoBean();
        String account = String.valueOf(codeToLogin.get(Constant.ACCOUNT));
        Map<String, Object> object = checkVerifyCode(account, codeToLogin);
        if (object.containsKey(Constant.ERROR_VALUE)) {
            return object;
        }
        // 验证用户信息
        Map<String, Object> objectMap = checkUserInfo(account, userInfo);
        if (objectMap.containsKey(Constant.ERROR_VALUE)) {
            return objectMap;
        }
        UserInfoBean user = (UserInfoBean) objectMap.get("userInfo");
        // 将用户 token 放到 Redis 中
        String token = JwtUtil.createJwt(user.getId(), account);
        redisService.set(Constant.LOGIN_TOKEN + ":" + token, user, Constant.TOKEN_EXPIRE_TIME);
        result.put(Constant.TOKEN_SYMBOL, token);
        return result;
    }

    /**
     * 账号注册
     *
     * @param signUpParam 账号（手机号/邮箱）注册
     * @return 返回值
     */
    @Override
    @Transactional
    public Map<String, Object> signUp(Map<String, Object> signUpParam) {
        Map<String, Object> result = new HashMap<>();
        UserInfoBean userInfo = new UserInfoBean();
        String account = String.valueOf(signUpParam.get(Constant.ACCOUNT));
        String password = null;
        if (signUpParam.containsKey(Constant.VERIFY_CODE)) {
            Map<String, Object> objectMap = checkVerifyCode(account, signUpParam);
            if (objectMap.containsKey(Constant.ERROR_VALUE)) {
                return objectMap;
            }
            password = Constant.ACQUIESCENT_PASSWORD;
            result.put(Constant.ACCOUNT, account);
            result.put(Constant.CONTENT, "临时密码为:" + password + ",请登录后修改密码!");
        } else if (signUpParam.containsKey(Constant.PASSWORD)) {
            password = String.valueOf(signUpParam.get(Constant.PASSWORD));
            result.put(Constant.ACCOUNT, account);
            result.put(Constant.CONTENT, "账号注册成功!");
        }
        boolean isMail = account.matches(Constant.MAIL_RULES);
        if (isMail) {
            userInfo.setEmail(account);
        } else {
            userInfo.setPhone(account);
        }
        List<UserInfoBean> userInfoList = userInfoService.queryUserInfoList(userInfo);
        if (!CollectionUtils.isEmpty(userInfoList)) {
            result.put(Constant.ERROR_VALUE, "该账号已注册，请登录!");
            return result;
        }
        userInfo.setPassword(password);
        userInfoService.insertUserInfo(userInfo);
        return result;
    }

    /**
     * 获取验证码
     *
     * @param account 账号（手机号/邮箱）
     * @return 返回值
     */
    @Override
    public Map<String, Object> getVerifyCode(String account) {
        Map<String, Object> result = new HashMap<>();
        long time = Constant.VERIFY_CODE_VALID_TIME;
        boolean isMail = account.matches(Constant.MAIL_RULES);
        int verifyCode = Common.getRandomCode();
        if (isMail) {
            String content = Common.getVerifyCodeHtml();
            content = content.replace("${name}", account)
                    .replace("${item}", "学习项目")
                    .replace("${verifyCode}", String.valueOf(verifyCode))
                    .replace("${time}", String.valueOf(time / 60));
            mailSendUtil.sendWithHtml(account, "验证码", content);
        } else {
            // TODO 添加短信
        }
//        redisService.set(account, verifyCode, time);
        return result;
    }

    /**
     * 校验验证码有效状态
     *
     * @param account   账号（手机号/邮箱）
     * @param objectMap 请求参数
     * @return 返回值
     */
    private Map<String, Object> checkVerifyCode(String account, Map<String, Object> objectMap) {
        String verifyCode = String.valueOf(objectMap.get(Constant.VERIFY_CODE));
        Object code = redisService.get(account);
        Map<String, Object> objectObj = new HashMap<>();
        if (code == null) {
            objectObj.put(Constant.ERROR_VALUE, "验证码已过期,请重新获取!");
        }
        if (verifyCode == code) {
            objectObj.put(Constant.ERROR_VALUE, "验证码不正确,请重新输入!");
        }
        return objectObj;
    }

    /**
     * @param account  账号（手机号/邮箱）
     * @param userInfo 用户详情
     * @return 返回值
     */
    private Map<String, Object> checkUserInfo(String account, UserInfoBean userInfo) {
        boolean isMail = account.matches(Constant.MAIL_RULES);
        if (isMail) {
            userInfo.setEmail(account);
        } else {
            userInfo.setPhone(account);
        }
        List<UserInfoBean> userInfoList = userInfoService.queryUserInfoList(userInfo);
        Map<String, Object> objectMap = new HashMap<>();
        if (CollectionUtils.isEmpty(userInfoList)) {
            objectMap.put(Constant.ERROR_VALUE, "未检测到账号,请注册后使用!");
        } else {
            objectMap.put("userInfo", userInfoList.get(0));
        }
        return objectMap;
    }


}
