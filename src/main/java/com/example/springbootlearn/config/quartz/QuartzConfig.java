package com.example.springbootlearn.config.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @Project springboot-learn
 * @Description Quartz 配置
 * @Author xuzhenshan
 * @Date 2023/12/27 18:32:01
 * @Version 1.0
 */
@Slf4j
@Configurable
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        // 指定任务描述具体的实现类
        return JobBuilder.newJob(QuartzTask.class)
                // 指定任务的名称
                .withIdentity("myTask")
                // 任务描述
                .withDescription("任务描述：用于输出任务信息")
                // 每次任务执行后进行存储
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger() {
        // 创建触发器
        return TriggerBuilder.newTrigger()
                // 绑定工作任务
                .forJob(jobDetail())
                // 间隔执行时间
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                // 立即执行一次任务
                .startNow()
                .build();
    }

}
