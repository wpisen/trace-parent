package com.wpisen.trace.server.dao;

import org.apache.ibatis.annotations.Param;

import com.wpisen.trace.server.dao.entity.ProjectConfig;

import java.util.List;

public interface ProjectConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(ProjectConfig record);

    int insertSelective(ProjectConfig record);

    ProjectConfig selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(ProjectConfig record);

    int updateByPrimaryKey(ProjectConfig record);

    /**
     * 根据项目ID查找 对应配置信息
     *
     * @param proId   项目ID(必填)
     * @param appid appid
     * @param dynamic true 表示动态的,false 表示静态
     * @return
     */
    List<ProjectConfig> selectByProjectId(@Param("proId") Integer proId,
                                          @Param("appId") Integer appid,
                                          @Param("dynamic") Boolean dynamic);

}