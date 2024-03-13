package com.example.springbootlearn.service.user.impl;

import com.example.springbootlearn.dao.user.UserInfoDao;
import com.example.springbootlearn.entity.user.UserInfoBean;
import com.example.springbootlearn.service.user.IUserInfoService;
import com.example.springbootlearn.utils.enumType.Common;
import com.example.springbootlearn.utils.enumType.Constant;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
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

    private final UserInfoDao userInfoDao;

    public IUserInfoServiceImpl(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    /**
     * 插入数据
     *
     * @param userInfoBean
     * @return
     */
    @Override
    public Map<String, Object> insertUserInfo(UserInfoBean userInfoBean) {
        String userName = userInfoBean.getUserName() == null ? generateDefaultNickname("用户-") : userInfoBean.getUserName();
        String password = userInfoBean.getPassword();
        userInfoBean.setUserName(userName).setPassword(null);
        userInfoBean.setCreateTime(System.currentTimeMillis());
        userInfoBean.setModifyTime(System.currentTimeMillis());
        userInfoDao.insertUserInfo(userInfoBean);
        // 设置用户密码
        String salt = Common.getShortRandomCode();
        password = Common.getPasswordMd5(userInfoBean.getId(), password,salt);
        userInfoDao.updateUserInfo(new UserInfoBean().setId(userInfoBean.getId()).setPassword(password).setSalt(salt));
        return new HashMap<>();
    }

    @Override
    public Map<String, Object> updateUserInfo(UserInfoBean userInfoBean) {
        userInfoDao.updateUserInfo(userInfoBean);
        return new HashMap<>();
    }

    @Override
    public void deleteUserInfo(UserInfoBean userInfoBean) {
        userInfoBean.setIsDelete(Constant.DELETE_STATUS);
        userInfoDao.updateUserInfo(userInfoBean);
    }

    @Override
    public List<UserInfoBean> queryUserInfoList(UserInfoBean userInfoBean) {
        if (userInfoBean.getPage() > 0) {
            userInfoBean.setPage((userInfoBean.getPage() - 1) * userInfoBean.getPageSize());
        }
        return userInfoDao.queryUserInfoList(userInfoBean);
    }


    /**
     * 生成默认昵称名
     *
     * @return
     */
    public String generateDefaultNickname(@NonNull String namePrefix) {
        String defaultNickName;
        List<UserInfoBean> userInfoList;
        long queryCount = 0;
        do {
            // 昵称校验重名
            defaultNickName = namePrefix + Common.getShortRandomCode();
            userInfoList = queryUserInfoList(new UserInfoBean().setUserName(defaultNickName.trim()));
            queryCount++;
        } while (!CollectionUtils.isEmpty(userInfoList));
        log.info("昵称生成并校验重名的操作次数: {}", queryCount);
        return defaultNickName;
    }

}
