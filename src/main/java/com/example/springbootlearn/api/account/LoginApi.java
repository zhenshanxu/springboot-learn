package com.example.springbootlearn.api.account;

import com.example.springbootlearn.config.aop.SysLog;
import com.example.springbootlearn.entity.assist.ResponseResult;
import com.example.springbootlearn.service.account.LoginService;
import com.example.springbootlearn.utils.enumType.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 14:52:25
 * @Version 1.0
 */

@Slf4j
@Api(tags = "登录管理")
@RestController
@RequestMapping("/login/api")
public class LoginApi {

    private final LoginService loginService;

    public LoginApi(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 获取验证码
     *
     * @param accountObject
     * @return
     */
    @SysLog(value = "获取验证码")
    @ApiOperation("获取验证码")
    @GetMapping("/getVerifyCode")
    ResponseResult<Object> getVerifyCode(@RequestParam Map<String, Object> accountObject) {
        ResponseResult<Object> response = new ResponseResult<>();
        try {
            Map<String, Object> flag = loginService.getVerifyCode(accountObject.get(Constant.ACCOUNT).toString());
            if (flag.containsKey(Constant.ERROR_VALUE)) {
                response.err().setErrorMessage(flag.get(Constant.ERROR_VALUE).toString());
            } else {
                response.ok().setResult("验证码发送成功!");
            }
        } catch (Exception e) {
            response.err().setErrorMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 使用验证码登录
     *
     * @param codeToLogin
     * @return
     */
    @SysLog(value = "验证码登录")
    @ApiOperation("验证码登录")
    @PostMapping("/codeToLogin")
    public ResponseResult<Map<String, Object>> codeToLogin(@RequestBody Map<String, Object> codeToLogin) {
        ResponseResult<Map<String, Object>> response = new ResponseResult<>();
        try {
            Map<String, Object> flag = loginService.codeToLogin(codeToLogin);
            if (flag.containsKey(Constant.ERROR_VALUE)) {
                response.err().setErrorMessage(flag.get(Constant.ERROR_VALUE).toString());
            } else {
                response.ok().setResult(flag);
            }
        } catch (Exception e) {
            response.err().setErrorMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 使用密码登录
     *
     * @param accountToLogin
     * @return
     */
    @SysLog(value = "密码登录")
    @ApiOperation("密码登录")
    @PostMapping("/accountToLogin")
    public ResponseResult<Map<String, Object>> accountToLogin(@RequestBody Map<String, Object> accountToLogin) {
        ResponseResult<Map<String, Object>> response = new ResponseResult<>();
        try {
            Map<String, Object> flag = loginService.accountToLogin(accountToLogin);
            if (flag.containsKey(Constant.ERROR_VALUE)) {
                response.err().setErrorMessage(flag.get(Constant.ERROR_VALUE).toString());
            } else {
                response.ok().setResult(flag);
            }
        } catch (Exception e) {
            response.err().setErrorMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 注册新用户
     *
     * @param signUpParam 注册用户信息
     * @return 结果
     */
    @SysLog(value = "新用户注册")
    @ApiOperation("新用户注册")
    @PostMapping("/signUp")
    public ResponseResult<Map<String, Object>> signUp(@RequestBody Map<String, Object> signUpParam) {
        ResponseResult<Map<String, Object>> response = new ResponseResult<>();
        try {
            Map<String, Object> flag = loginService.signUp(signUpParam);
            if (flag.containsKey(Constant.ERROR_VALUE)) {
                response.err().setErrorMessage(flag.get(Constant.ERROR_VALUE).toString());
            } else {
                response.ok().setResult(flag);
            }
        } catch (Exception e) {
            response.err().setErrorMessage(e.getMessage());
        }
        return response;
    }

}
