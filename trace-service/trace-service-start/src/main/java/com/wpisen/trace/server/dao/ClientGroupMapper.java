package com.wpisen.trace.server.dao;

import org.apache.ibatis.annotations.Param;

import com.wpisen.trace.server.dao.entity.ClientGroup;

public interface ClientGroupMapper {
    int deleteByPrimaryKey(Integer clientId);

    int insert(ClientGroup record);

    int insertSelective(ClientGroup record);

    ClientGroup selectByPrimaryKey(Integer clientId);

    int updateByPrimaryKeySelective(ClientGroup record);

    int updateByPrimaryKey(ClientGroup record);

    ClientGroup selectMaster(@Param("platform") String platform);
}