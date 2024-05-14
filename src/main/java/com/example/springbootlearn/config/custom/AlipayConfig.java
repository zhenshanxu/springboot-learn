package com.example.springbootlearn.config.custom;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project springboot-learn
 * @Description 支付宝支付配置
 * @Author xuzhenshan
 * @Date 2024/5/14 09:44:12
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.payment.alipay")
public class AlipayConfig {

    @ApiModelProperty("支付宝appid")
    private String appId;

    @ApiModelProperty("支付宝公钥")
    private String publicKey;

    @ApiModelProperty("支付宝网关")
    private String gatewayUrl;

    @ApiModelProperty("支付宝私钥")
    private String merchantPrivateKey;
}
