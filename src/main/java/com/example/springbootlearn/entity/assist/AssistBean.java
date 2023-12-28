package com.example.springbootlearn.entity.assist;

import com.example.springbootlearn.utils.enumType.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/27 18:49:05
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@ApiModel("实体协助详情")
public class AssistBean {

    /**
     * 创建者
     */
    @ApiModelProperty("创建者Id")
    private int creator;

    /**
     * 修改者
     */
    @ApiModelProperty("修改者Id")
    private int mender;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private long createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private long modifyTime;

    /**
     * 当前页数
     */
    @ApiModelProperty("当前页数")
    @Transient
    private int page;

    /**
     * 每页数量
     */
    @ApiModelProperty("每页数量")
    @Transient
    private int pageSize;

    /**
     * 总数
     */
    @ApiModelProperty("查询数据总数")
    @Transient
    private int total;

    /**
     * 状态，是否删除 1：正常，-1:删除
     */
    @ApiModelProperty("状态，是否删除 1：正常，-1:删除")
    private int isDelete;

    @ApiModelProperty("设置数据状态默认值")
    public int getIsDelete() {
        return isDelete = isDelete == 0 ? Constant.INITIAL_STATUS : isDelete;
    }


}
