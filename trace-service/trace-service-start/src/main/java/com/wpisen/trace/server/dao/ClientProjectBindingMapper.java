package com.wpisen.trace.server.dao;

import org.apache.ibatis.annotations.Param;

import com.wpisen.trace.server.dao.entity.ClientProjectBinding;

public interface ClientProjectBindingMapper {
    int deleteByPrimaryKey(Integer bindId);

    int insert(ClientProjectBinding record);

    int insertSelective(ClientProjectBinding record);

    ClientProjectBinding selectByPrimaryKey(Integer bindId);

    int updateByPrimaryKeySelective(ClientProjectBinding record);

    int updateByPrimaryKey(ClientProjectBinding record);

    ClientProjectBinding selectByProIdAndPlatform(@Param("proId") Integer proId, @Param("platform") String platform);
}