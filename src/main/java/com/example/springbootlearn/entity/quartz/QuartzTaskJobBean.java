package com.example.springbootlearn.entity.quartz;

import com.example.springbootlearn.entity.userDetail.UserInfoBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/27 18:47:14
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("Quartz 定时任务")
public class QuartzTaskJobBean {

    /**
     * 任务id
     */
    private int id;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 任务执行类
     */
    private String jobClass;

    /**
     * 任务状态, 任务状态：1：启动，0：暂停，-1 新任务,-2 异常
     */
    private Integer status;

    /**
     * 任务运行时间表达式
     */
    private String cronExpression;

    /**
     * 执行次数
     */
    @Transient
    private int execute;

    /**
     * 每周
     * 1代表周日 7代表周六 可选值：1、2、3、4、5、6、7、1-5、1-7
     */
    @Transient
    private String week;

    /**
     * 提醒配置项 （天）
     */
    private String alert;

    @Transient
    private UserInfoBean userInfo;

    /**
     * 创建时间
     */
    private String tmCreate;

    /**
     * 修改时间
     */
    private String tmModify;

    /**
     * 定时任务执行记录
     */
    @Transient
    private List<QuartzJobRecordBean> quartzJobRecordList;
}
