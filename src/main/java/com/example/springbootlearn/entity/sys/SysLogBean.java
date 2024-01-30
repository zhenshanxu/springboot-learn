package com.example.springbootlearn.entity.sys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @Project springboot-learn
 * @Description 系统日志
 * @Author xuzhenshan
 * @Date 2024/1/30 14:57:19
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_log")
@ApiModel("系统日志")
public class SysLogBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 操作
     */
    private String operation;

    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String params;

    /**
     * 执行时长（毫秒）
     */
    private Long time;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * 创建时间
     */
    private Long createDate;
}
