package com.wpisen.trace.server.dao;

import org.apache.ibatis.annotations.Param;

import com.wpisen.trace.server.dao.entity.StaticProperties;

public interface StaticPropertiesMapper {
    int deleteByPrimaryKey(Integer staticId);

    int insert(StaticProperties record);

    int insertSelective(StaticProperties record);

    StaticProperties selectByPrimaryKey(Integer staticId);

    int updateByPrimaryKeySelective(StaticProperties record);

    int updateByPrimaryKeyWithBLOBs(StaticProperties record);

    int updateByPrimaryKey(StaticProperties record);
    StaticProperties selectByKey(@Param("key") String key);
}