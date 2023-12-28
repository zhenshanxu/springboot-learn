package com.example.springbootlearn.entity.quartz;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/27 18:47:41
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@Accessors(chain = true)
// @ApiModel("定时任务执行记录")
public class QuartzJobRecordBean {
    /**
     * 任务执行记录id
     */
    @Id
    private int id;

    /**
     * 定时任务id
     */
    @ApiModelProperty("定时任务id")
    private int jobId;

    /**
     * 定时任务执行时间
     */
    @ApiModelProperty("定时任务执行时间")
    private String jonExecute;

    /**
     * 定时任务执行状态 1：执行完成，-1 执行失败
     */
    @ApiModelProperty("定时任务执行状态")
    private int executeStatus;

    /**
     * 定时任务异常记录
     */
    @ApiModelProperty("定时任务异常记录")
    private String executeAbnormal;
}
