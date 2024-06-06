package com.example.springbootlearn.entity.assist;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/27 18:50:37
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@ApiModel("统一响应结果")
@Accessors(chain = true)
public class ResponseResult<T> implements Serializable {

    /**
     * 请求状态 true：成功 false：失败
     */
    @ApiModelProperty("请求状态 true：成功 false：失败")
    private Boolean success;

    /**
     * 请求结果
     */
    @ApiModelProperty("请求结果")
    private T result;

    /**
     * 错误信息
     */
    @ApiModelProperty("错误信息")
    private String errorMessage;

    /**
     * 额外信息
     */
    @ApiModelProperty("额外信息")
    private Object extraMessage;

    @ApiModelProperty("成功状态")
    public ResponseResult<T> ok() {
        return new ResponseResult<T>().setSuccess(Boolean.TRUE);
    }

    @ApiModelProperty("失败状态")
    public ResponseResult<T> err() {
        return new ResponseResult<T>().setSuccess(Boolean.FALSE);
    }
}
