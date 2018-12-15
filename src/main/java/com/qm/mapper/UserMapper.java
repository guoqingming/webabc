package com.qm.mapper;


import com.qm.domain.User;

public interface UserMapper {
    int deleteById(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User getById(Integer uid);

    int updateByIdSelective(User record);

    int updateById(User record);
}