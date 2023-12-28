package com.example.springbootlearn.dao.user;

import com.example.springbootlearn.entity.user.UserInfoBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 10:03:23
 * @Version 1.0
 */

@Mapper
public interface UserInfoDao {

    /**
     * 插入用户信息
     *
     * @param userInfoBean 用户
     */
    void insertUserInfo(UserInfoBean userInfoBean);

    /**
     * 更新用户信息
     *
     * @param userInfoBean 用户
     */
    void updateUserInfo(UserInfoBean userInfoBean);


    /**
     * 获取用户
     *
     * @param userInfoBean 用户
     * @return userList
     */
    List<UserInfoBean> queryUserInfoList(UserInfoBean userInfoBean);

    /**
     * 根据 ids 查询用户列表
     *
     * @param ids 用户ids
     * @return userList
     */
    List<UserInfoBean> queryUserByIds(List<Integer> ids);

}
