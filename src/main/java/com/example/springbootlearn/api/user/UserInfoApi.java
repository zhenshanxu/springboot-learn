package com.example.springbootlearn.api.user;

import com.example.springbootlearn.entity.assist.ResponseBean;
import com.example.springbootlearn.entity.user.UserInfoBean;
import com.example.springbootlearn.service.user.IUserInfoService;
import com.example.springbootlearn.utils.enumType.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 14:49:56
 * @Version 1.0
 */

@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping("/userInf/api")
public class UserInfoApi {

    private final IUserInfoService userInfoService;

    public UserInfoApi(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @ApiOperation("添加用户信息")
    @PostMapping("/add")
    public ResponseBean addUserInfo(@RequestBody UserInfoBean userInfo) {
        ResponseBean response = new ResponseBean();
        try {
            Map<String, Object> flag = userInfoService.insertUserInfo(userInfo);
            if (flag.containsKey(Constant.ERROR_VALUE)) {
                response.setSuccess(false);
                response.setErrorMessage(flag.get(Constant.ERROR_VALUE).toString());
            } else {
                response.setSuccess(true);
                response.setResult("新用户添加成功!");
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    @ApiOperation("修改更新用户信息")
    @PostMapping("/update")
    public ResponseBean updateUserInfo(@RequestBody UserInfoBean userInfo) {
        ResponseBean response = new ResponseBean();
        try {
            Map<String, Object> flag = userInfoService.updateUserInfo(userInfo);
            if (flag.containsKey(Constant.ERROR_VALUE)) {
                response.setSuccess(false);
                response.setErrorMessage(flag.get(Constant.ERROR_VALUE).toString());
            } else {
                response.setSuccess(true);
                response.setResult("新用户添加成功!");
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    @ApiOperation("用户信息删除")
    @PostMapping("/delete")
    public ResponseBean deleteUserInfo(@RequestBody UserInfoBean userInfo) {
        ResponseBean response = new ResponseBean();
        try {
            userInfoService.deleteUserInfo(userInfo);
            response.setSuccess(true);
            response.setResult("用户信息删除成功!");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    @ApiOperation("查询用户信息")
    @PostMapping("/query")
    public ResponseBean queryUserInfoList(@RequestBody UserInfoBean userInfo) {
        ResponseBean response = new ResponseBean();
        try {
            List<UserInfoBean> userInfoList = userInfoService.queryUserInfoList(userInfo);
            response.setSuccess(true);
            response.setResult(userInfoList);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

}
