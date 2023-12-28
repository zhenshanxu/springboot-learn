package com.example.springbootlearn.service.user;

import com.example.springbootlearn.entity.user.UserInfoBean;

import java.util.List;
import java.util.Map;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 09:55:38
 * @Version 1.0
 */
public interface IUserInfoService {

    /**
     * 插入用户信息
     *
     * @param userInfoBean
     * @return
     */
    Map<String, Object> insertUserInfo(UserInfoBean userInfoBean);

    /**
     * 更新用户信息
     *
     * @param userInfoBean
     * @return
     */
    Map<String, Object> updateUserInfo(UserInfoBean userInfoBean);

    /**
     * 删除用户
     *
     * @param userInfoBean
     */
    void deleteUserInfo(UserInfoBean userInfoBean);

    /**
     * 获取用户
     *
     * @param userInfoBean
     * @return
     */
    List<UserInfoBean> queryUserInfoList(UserInfoBean userInfoBean);
}
