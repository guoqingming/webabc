package com.qm.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *  
 */
@ApiModel
@Data
public class ConfigItem implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 应用ID
     */
    @ApiModelProperty("应用ID")
    private Integer appId;

    @ApiModelProperty("应用英文名")
    private String appName;

    @ApiModelProperty("环境")
    private String profile;

    /**
     */
    @ApiModelProperty("")
    private String key;

    /**
     */
    @ApiModelProperty("")
    private String value;

    /**
     * 是否加密，1：密文，0：明文
     */
    @ApiModelProperty("是否加密，1：密文，0：明文")
    private Integer cipher;

    /**
     * 是否为敏感项，1：敏感，0：不敏感
     */
    @ApiModelProperty("是否为敏感项，1：敏感，0：不敏感")
    private Integer sensitive;

    /**
     * 是否删除，1：删除，0：未删除
     */
    @ApiModelProperty("是否删除，1：删除，0：未删除")
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createAt;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createBy;

    /**
     */
    @ApiModelProperty("")
    private Date updateAt;

    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    private String updateBy;

    /**
     */
    private static final long serialVersionUID = 1L;


}