package com.qm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: result-demo
 * @description: 配置项
 * @author: guoqingming
 * @create: 2018-12-15 11:18
 **/
@Data
@ApiModel
public class ConfigItem {

    /**
     * 英文应用名
     */
    @ApiModelProperty(value = "英文应用名")
    private String appName;

    /**
     * 环境 dev/test/prod
     */
    @ApiModelProperty(value = "环境")
    private String profile;

    /**
     *
     */
    @ApiModelProperty(value = "键")
    private String key;

    /**
     *
     */
    @ApiModelProperty(value = "值")
    private String value;

    @ApiModelProperty(value = "加密，1：密文，0：明文")
    private Integer cipher;

    @ApiModelProperty(value = "敏感项，1：敏感，0：不敏感")
    private Integer sensitive;
}
