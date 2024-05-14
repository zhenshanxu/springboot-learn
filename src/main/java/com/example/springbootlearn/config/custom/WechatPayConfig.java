package com.example.springbootlearn.config.custom;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project springboot-learn
 * @Description 微信支付
 * @Author xuzhenshan
 * @Date 2024/5/14 14:09:37
 * @Version 1.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "spring.payment.wechat")
public class WechatPayConfig {

    @ApiModelProperty("微信商户号")
    private String mchId;

    @ApiModelProperty("微信公众平台appId")
    private String appId;

    @ApiModelProperty("微信公众平台appSecret")
    private String appSecret;

    @ApiModelProperty("v3 接口ca证书 apiclient_key.pem")
    private String privateKey;

    @ApiModelProperty("微信支付证书")
    private String wechatPayKey;

    @ApiModelProperty("商户证书序列号CA证书编号")
    private String mchSerialNo;

    @ApiModelProperty("# apiV3 密钥")
    private String apiV3Key;
}
