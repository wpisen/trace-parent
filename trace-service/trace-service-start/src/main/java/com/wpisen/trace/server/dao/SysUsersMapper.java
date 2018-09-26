package com.wpisen.trace.server.dao;

import com.wpisen.trace.server.dao.entity.SysUsers;

public interface SysUsersMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(SysUsers record);

    int insertSelective(SysUsers record);

    SysUsers selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SysUsers record);

    int updateByPrimaryKey(SysUsers record);
}