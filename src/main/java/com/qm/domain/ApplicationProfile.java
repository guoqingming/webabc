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
public class ApplicationProfile implements Serializable {
    /**
     */
    @ApiModelProperty("")
    private Integer id;

    /**
     * 应用英文名
     */
    @ApiModelProperty("应用英文名")
    private String appName;

    /**
     * 应用中文名
     */
    @ApiModelProperty("应用中文名")
    private String appDesc;

    /**
     * 环境
     */
    @ApiModelProperty("环境")
    private String profile;

    /**
     * 删除标识，1：删除，0：未删除
     */
    @ApiModelProperty("删除标识，1：删除，0：未删除")
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
     * 更新时间
     */
    @ApiModelProperty("更新时间")
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