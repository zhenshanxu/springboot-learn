package com.example.springbootlearn.dao.quartz;

import com.example.springbootlearn.entity.quartz.QuartzTaskJobBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 15:08:03
 * @Version 1.0
 */
@Mapper
public interface QuartzTaskJobDao {

    /**
     * 添加定时任务
     *
     * @param quartzBean
     */
    void addJob(QuartzTaskJobBean quartzBean);

    /**
     * 修改定时任务
     *
     * @param quartzBean
     */
    void updateJob(QuartzTaskJobBean quartzBean);

    /**
     * 查询定时任务
     *
     * @param quartzBean
     * @return
     */
    List<QuartzTaskJobBean> queryJob(QuartzTaskJobBean quartzBean);
}
