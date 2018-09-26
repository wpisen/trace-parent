package com.wpisen.trace.server.dao;

import org.apache.ibatis.annotations.Param;

import com.wpisen.trace.server.dao.entity.ClientSession;

import java.util.Date;
import java.util.List;

public interface ClientSessionMapper {
    int deleteByPrimaryKey(Integer sessionId);

    int insert(ClientSession record);

    int insertSelective(ClientSession record);

    int updateByPrimaryKeySelective(ClientSession record);

    ClientSession selectByPrimaryKey(Integer sessionId);

    int updateByPrimaryKey(ClientSession record);

    /**
     * 根据条件进行逻辑删除
     * @param proId 项目ID
     * @param macAddress 客户端MAC地址
     * @param appPath 客户端路径
     * @return
     */
    int updateDisable(@Param("proId") Integer proId , @Param("macAddress") String macAddress, @Param("appPath") String appPath);

    /**
     * 根据sessionID 更新客户端心跳
     * @param sessionId
     * @return
     */
    int clientEcho(@Param("sessionId") Integer sessionId);

    /**
     * 查询指定时间内活跃会话
     */
    List<ClientSession> getActiveSession(@Param("lastModfy")Date lastModfy,@Param("proId")Integer proId) ;

}