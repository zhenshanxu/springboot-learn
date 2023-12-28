package com.example.springbootlearn.dao.quartz;

import com.example.springbootlearn.entity.quartz.QuartzJobRecordBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 15:08:19
 * @Version 1.0
 */
@Mapper
public interface QuartzJobRecordDao {

    /**
     * 添加定时任务执行记录
     *
     * @param quartzJobRecordBean
     */
    void addExecuteRecord(QuartzJobRecordBean quartzJobRecordBean);

    /**
     * 修改定时任务执行记录
     * @param quartzJobRecordBean
     */
    void updateExecuteRecord(QuartzJobRecordBean quartzJobRecordBean);

    /**
     * 查询定时任务执行记录
     *
     * @param quartzJobRecordBean
     */
    List<QuartzJobRecordBean> queryExecuteRecord(QuartzJobRecordBean quartzJobRecordBean);

    /**
     * 根据定时任务id查询定时任务执行记录
     *
     * @param jobIds
     * @return
     */
    List<QuartzJobRecordBean> queryExecuteRecordByJobIs(@Param("jobIds") List<Integer> jobIds);


}
