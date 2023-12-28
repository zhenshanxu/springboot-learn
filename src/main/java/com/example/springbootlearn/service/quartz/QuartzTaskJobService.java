package com.example.springbootlearn.service.quartz;

import com.example.springbootlearn.entity.quartz.QuartzTaskJobBean;

import java.util.List;
import java.util.Map;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 15:02:03
 * @Version 1.0
 */
public interface QuartzTaskJobService {
    /**
     * 添加定时任务
     *
     * @param quartzBean
     * @return
     */
    Map<String, Object> addJob(QuartzTaskJobBean quartzBean);

    /**
     * 修改定时任务
     *
     * @param quartzBean
     * @return
     */
    Map<String, Object> updateJob(QuartzTaskJobBean quartzBean);

    /**
     * 删除定时任务
     * @param quartzBean
     * @return
     */
    Map<String, Object> deleteJob(QuartzTaskJobBean quartzBean);

    /**
     * 查询定时任务
     *
     * @param quartzBean
     * @return
     */
    List<QuartzTaskJobBean> queryJob(QuartzTaskJobBean quartzBean);
}
