package com.example.springbootlearn.entity.userDetail;

import com.example.springbootlearn.entity.assist.AssistBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @Project springboot-learn
 * @Description 用户详情
 * @Author xuzhenshan
 * @Date 2023/12/28 09:35:52
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户详情")
@Accessors(chain = true)
public class UserInfoBean extends AssistBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String name;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String userAvatar;


    /**
     * 邮件
     */
    @ApiModelProperty("邮件")
    private String email;

    /**
     * 联系方式
     */
    @ApiModelProperty("联系方式")
    private String phone;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;
}
