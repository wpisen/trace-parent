package com.wpisen.trace.server.dao;

import org.apache.ibatis.annotations.Param;

import com.wpisen.trace.server.dao.entity.Project;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer proId);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer proId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    /**
     * @param proKey
     * @return
     */
    Project selectByProjectKey(@Param("proKey") String proKey);
}