package com.example.springbootlearn.service.user.impl;

import com.example.springbootlearn.entity.user.UserInfoBean;
import com.example.springbootlearn.service.user.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 09:57:04
 * @Version 1.0
 */
@Slf4j
@Service
public class IUserInfoServiceImpl implements IUserInfoService {

    @Override
    public Map<String, Object> insertUserInfo(UserInfoBean userInfoBean) {
        return null;
    }

    @Override
    public Map<String, Object> updateUserInfo(UserInfoBean userInfoBean) {
        return null;
    }

    @Override
    public void deleteUserInfo(UserInfoBean userInfoBean) {

    }

    @Override
    public List<UserInfoBean> queryUserInfoList(UserInfoBean userInfoBean) {
        return null;
    }
}
