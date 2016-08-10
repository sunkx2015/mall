package com.sun.dao;

import org.apache.ibatis.annotations.Param;

import com.sun.model.user;

public interface userMapper {
    int insert(user record);

    int insertSelective(user record);
    
    user selectByPrimaryKey(@Param("id") String id);
}