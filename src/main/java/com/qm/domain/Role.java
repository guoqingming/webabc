package com.qm.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *  
 */
@Data
public class Role implements Serializable {
    /**
     */
//    @ApiModelProperty("")
    private Integer rid;

    /**
     */
    private String rname;

    /**
     */
    private static final long serialVersionUID = 1L;

}