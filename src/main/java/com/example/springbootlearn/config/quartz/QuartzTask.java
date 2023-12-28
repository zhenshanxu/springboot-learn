package com.example.springbootlearn.config.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/27 18:33:08
 * @Version 1.0
 */

@Slf4j
public class QuartzTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // TODO 定时任务执行逻辑
        log.info("定时任务指定时间：" + System.currentTimeMillis());
    }
}
