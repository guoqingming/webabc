package com.qm.vo;

import com.qm.domain.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: zkui1
 * @description: 登录用户
 * @author: guoqingming
 * @create: 2018-08-06 10:16
 **/
@Data
public class UserInfo implements Serializable {

    private List<Role> roleList;
}
