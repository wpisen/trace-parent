package com.wpisen.trace.server.dao;

import com.wpisen.trace.server.dao.entity.ClientUpdateLog;

public interface ClientUpdateLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(ClientUpdateLog record);

    int insertSelective(ClientUpdateLog record);

    ClientUpdateLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(ClientUpdateLog record);

    int updateByPrimaryKey(ClientUpdateLog record);
}