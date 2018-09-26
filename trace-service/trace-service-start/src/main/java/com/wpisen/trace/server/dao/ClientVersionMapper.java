package com.wpisen.trace.server.dao;

import org.apache.ibatis.annotations.Param;

import com.wpisen.trace.server.dao.entity.ClientVersion;

public interface ClientVersionMapper {
    int deleteByPrimaryKey(Integer versionId);

    int insert(ClientVersion record);

    int insertSelective(ClientVersion record);

    ClientVersion selectByPrimaryKey(Integer versionId);

    int updateByPrimaryKeySelective(ClientVersion record);

    int updateByPrimaryKeyWithBLOBs(ClientVersion record);

    int updateByPrimaryKey(ClientVersion record);

    /**
     * 获取指定客户端最高有效版本
     * 
     * @param clientId
     * @return 返回版本信息， 但不返回data_bytes
     */
    ClientVersion selectClientMaxVersion(@Param("clientId") Integer clientId);

    /**
     * 根据md5签名查找版本内容
     * 
     * @param clientId
     * @return 返回版本信息和data_bytes
     */
    ClientVersion selectByMd5(@Param("md5") String md5);
}