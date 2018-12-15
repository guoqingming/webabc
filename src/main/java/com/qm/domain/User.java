package com.qm.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *  
 */
@Data
public class User implements Serializable {
    /**
     */
    private Integer uid;

    /**
     */
    private String username;

    /**
     */
    private String password;

    /**
     */
    private static final long serialVersionUID = 1L;
}