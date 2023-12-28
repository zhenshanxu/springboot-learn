package com.example.springbootlearn.service.quartz.impl;

import com.example.springbootlearn.dao.quartz.QuartzTaskJobDao;
import com.example.springbootlearn.dao.user.UserInfoDao;
import com.example.springbootlearn.entity.quartz.QuartzTaskJobBean;
import com.example.springbootlearn.entity.user.UserInfoBean;
import com.example.springbootlearn.service.quartz.QuartzJobRecordService;
import com.example.springbootlearn.service.quartz.QuartzTaskJobService;
import com.example.springbootlearn.utils.enumType.Constant;
import com.example.springbootlearn.utils.quartz.CronUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 15:06:04
 * @Version 1.0
 */

@Service
public class QuartzTaskJobServiceImpl implements QuartzTaskJobService {

    private final QuartzTaskJobDao quartzTaskJobDao;

    private final UserInfoDao userDao;

    private final QuartzJobRecordService quartzJobRecordService;

    public QuartzTaskJobServiceImpl(QuartzTaskJobDao quartzTaskJobDao, UserInfoDao userDao, QuartzJobRecordService quartzJobRecordService) {
        this.quartzTaskJobDao = quartzTaskJobDao;
        this.userDao = userDao;
        this.quartzJobRecordService = quartzJobRecordService;
    }

    /**
     * 添加定时任务
     *
     * @param quartzBean
     * @return
     */
    @Override
    public Map<String, Object> addJob(QuartzTaskJobBean quartzBean) {
        // 查询定时任务名称是否存在
        List<QuartzTaskJobBean> quartzTaskJobList = quartzTaskJobDao.queryJob(new QuartzTaskJobBean().setJobName(quartzBean.getJobName()));
        if (!CollectionUtils.isEmpty(quartzTaskJobList)) {
            return new HashMap<String, Object>() {{
                put(Constant.ERROR_VALUE, "定时任务名称已存在不可重复添加！");
            }};
        }
        int weekExecute = 2; // 定时任务每周执行标识
        if (quartzBean.getExecute() == weekExecute && quartzBean.getWeek() == null) {
            return new HashMap<String, Object>() {{
                put(Constant.ERROR_VALUE, "定时任务每周执行时间缺少每周执行时间!");
            }};
        }
        //  校验运行时间表达式
        String cronExpression = CronUtils.getCronExpression(quartzBean.getCronExpression(), quartzBean.getExecute(), quartzBean.getWeek());
        // Assert.isTrue(CronSequenceGenerator.isValidExpression(cronExpression), "定时任务执行时间表达式有误!");
        String createTime = String.valueOf(System.currentTimeMillis());
        quartzTaskJobDao.addJob(quartzBean.setCronExpression(cronExpression).setTmCreate(createTime).setTmModify(createTime));
        return new HashMap<>();
    }

    /**
     * 修改定时任务
     */
    @Override
    public Map<String, Object> updateJob(QuartzTaskJobBean quartzBean) {
        List<QuartzTaskJobBean> quartzTaskJobList = quartzTaskJobDao.queryJob(new QuartzTaskJobBean().setJobName(quartzBean.getJobName()));
        if (!CollectionUtils.isEmpty(quartzTaskJobList)) {
            QuartzTaskJobBean quartzTaskJobBean = quartzTaskJobList.get(0);
            if (quartzTaskJobBean.getId() != quartzBean.getId()) {
                return new HashMap<String, Object>() {{
                    put(Constant.ERROR_VALUE, "定时任务名称已存在不可修改！");
                }};
            }
        }
        if (quartzBean.getCronExpression() != null) {
            int weekExecute = 2; // 定时任务每周执行标识
            if (quartzBean.getExecute() == weekExecute && quartzBean.getWeek() == null) {
                return new HashMap<String, Object>() {{
                    put(Constant.ERROR_VALUE, "定时任务每周执行时间缺少每周执行时间!");
                }};
            }
            String cronExpression = CronUtils.getCronExpression(quartzBean.getCronExpression(), quartzBean.getExecute(), quartzBean.getWeek());
            //Assert.isTrue(CronSequenceGenerator.isValidExpression(cronExpression), "定时任务时间表达式有误!");
            quartzBean.setCronExpression(cronExpression);
        }
        quartzBean.setStatus(0);// 定时任务修状态默认改为暂停，需手动启动项目
        String createTime = String.valueOf(System.currentTimeMillis());
        quartzTaskJobDao.updateJob(quartzBean.setTmModify(createTime));
        return new HashMap<>();
    }

    /**
     * 删除定时任务
     *
     * @param quartzBean
     * @return
     */
    @Override
    public Map<String, Object> deleteJob(QuartzTaskJobBean quartzBean) {
        quartzBean.setIsDelete(Constant.DELETE_STATUS);
        quartzTaskJobDao.updateJob(quartzBean);
        // 停止定时任务
        quartzJobRecordService.deleteScheduleJob(new QuartzTaskJobBean().setId(quartzBean.getId()));
        return new HashMap<>();
    }

    /**
     * 查询定时任务
     *
     * @param quartzBean
     * @return
     */
    @Override
    public List<QuartzTaskJobBean> queryJob(QuartzTaskJobBean quartzBean) {
        if (quartzBean.getPageSize() != 0) {
            quartzBean.setPage(quartzBean.getPageSize() * (quartzBean.getPage() - 1));
        }
        List<QuartzTaskJobBean> quartzJobList = quartzTaskJobDao.queryJob(quartzBean);
        // 关联查询任务执行记录
        if (CollectionUtils.isEmpty(quartzJobList)) {
            return quartzJobList;
        }
        List<Integer> ids = quartzJobList.stream().map(QuartzTaskJobBean::getCreator).collect(Collectors.toList());
        List<UserInfoBean> userList = userDao.queryUserByIds(ids);
        if (!CollectionUtils.isEmpty(userList)) {
            Map<Integer, UserInfoBean> userMap = userList.stream().collect(Collectors.toMap(UserInfoBean::getId, o -> o));
            quartzJobList.stream().filter(o -> userMap.containsKey(o.getCreator())).forEach(q -> q.setUserInfo(userMap.get(q.getCreator())));
        }
        return quartzJobList;
    }
}
