package com.example.springbootlearn.api.user;

import com.example.springbootlearn.entity.assist.ResponseResult;
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
    public ResponseResult<Object> addUserInfo(@RequestBody UserInfoBean userInfo) {
        ResponseResult<Object> response = new ResponseResult();
        try {
            Map<String, Object> flag = userInfoService.insertUserInfo(userInfo);
            if (flag.containsKey(Constant.ERROR_VALUE)) {
                response.err().setErrorMessage(flag.get(Constant.ERROR_VALUE).toString());
            } else {
                response.ok().setResult("新用户添加成功!");
            }
        } catch (Exception e) {
            response.err().setErrorMessage(e.getMessage());
        }
        return response;
    }

    @ApiOperation("修改更新用户信息")
    @PostMapping("/update")
    public ResponseResult<Object> updateUserInfo(@RequestBody UserInfoBean userInfo) {
        ResponseResult<Object> response = new ResponseResult<>();
        try {
            Map<String, Object> flag = userInfoService.updateUserInfo(userInfo);
            if (flag.containsKey(Constant.ERROR_VALUE)) {
                response.err().setErrorMessage(flag.get(Constant.ERROR_VALUE).toString());
            } else {
                response.ok().setResult("新用户添加成功!");
            }
        } catch (Exception e) {
            response.err().setErrorMessage(e.getMessage());
        }
        return response;
    }

    @ApiOperation("用户信息删除")
    @PostMapping("/delete")
    public ResponseResult<Object> deleteUserInfo(@RequestBody UserInfoBean userInfo) {
        ResponseResult<Object> response = new ResponseResult<>();
        try {
            userInfoService.deleteUserInfo(userInfo);
            response.ok().setResult("用户信息删除成功!");
        } catch (Exception e) {
            response.err().setErrorMessage(e.getMessage());
        }
        return response;
    }

    @ApiOperation("查询用户信息")
    @PostMapping("/query")
    public ResponseResult<List<UserInfoBean>> queryUserInfoList(@RequestBody UserInfoBean userInfo) {
        ResponseResult<List<UserInfoBean>> response = new ResponseResult<>();
        try {
            List<UserInfoBean> userInfoList = userInfoService.queryUserInfoList(userInfo);
            response.ok().setResult(userInfoList);
        } catch (Exception e) {
            response.err().setErrorMessage(e.getMessage());
        }
        return response;
    }

}
