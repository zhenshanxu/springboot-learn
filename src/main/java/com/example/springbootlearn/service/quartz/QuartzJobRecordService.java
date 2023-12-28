package com.example.springbootlearn.service.quartz;

import com.example.springbootlearn.entity.quartz.QuartzJobRecordBean;
import com.example.springbootlearn.entity.quartz.QuartzTaskJobBean;

import java.util.List;
import java.util.Map;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 15:09:05
 * @Version 1.0
 */
public interface QuartzJobRecordService {

    /**
     * 添加定时任务执行记录
     *
     * @param quartzJobRecordBean
     * @return
     */
    Map<String, Object> addExecuteRecord(QuartzJobRecordBean quartzJobRecordBean);

    /**
     * 修改定时任务执行记录
     *
     * @param quartzJobRecordBean
     * @return
     */
    Map<String, Object> updateExecuteRecord(QuartzJobRecordBean quartzJobRecordBean);

    /**
     * 查询定时任务执行记录
     *
     * @param quartzJobRecordBean
     * @return
     */
    List<QuartzJobRecordBean> queryExecuteRecord(QuartzJobRecordBean quartzJobRecordBean);

    /**
     * 创建并执行定时任务
     *
     * @param QuartzTaskJob
     * @return
     */
    Map<String, Object> createJob(QuartzTaskJobBean QuartzTaskJob);

    /**
     * 暂停定时任务
     *
     * @param quartzTaskJob
     * @return
     */
    Map<String, Object> pauseJob(QuartzTaskJobBean quartzTaskJob);

    /**
     * 继续定时任务
     * @param quartzTaskJob
     * @return
     */
    Map<String, Object> resume(QuartzTaskJobBean quartzTaskJob);

    /**
     * 执行一次任务
     * @param quartzTaskJob
     * @return
     */
    Map<String, Object> runOnce(QuartzTaskJobBean quartzTaskJob);

    /**
     * 修改定时任务并执行
     * @param quartzTaskJob
     * @return
     */
    Map<String, Object> updateJob(QuartzTaskJobBean quartzTaskJob);

    /**
     * 根据定时任务名称从调度器中删除定时任务
     *
     * @param quartzTaskJob
     * @return
     */
    Map<String, Object> deleteScheduleJob(QuartzTaskJobBean quartzTaskJob);


}
