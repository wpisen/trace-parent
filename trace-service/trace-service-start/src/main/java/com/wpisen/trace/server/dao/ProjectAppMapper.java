package com.wpisen.trace.server.dao;

import org.apache.ibatis.annotations.Param;

import com.wpisen.trace.server.dao.entity.ProjectApp;
import com.wpisen.trace.server.dao.entity.ProjectAppWithBLOBs;

import java.util.List;

public interface ProjectAppMapper {
    int deleteByPrimaryKey(Integer appId);

    int insert(ProjectAppWithBLOBs record);

    int insertSelective(ProjectAppWithBLOBs record);

    ProjectAppWithBLOBs selectByPrimaryKey(Integer appId);

    int updateByPrimaryKeySelective(ProjectAppWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProjectAppWithBLOBs record);

    int updateByPrimaryKey(ProjectApp record);

    List<ProjectApp> selectByProjectId(@Param("proId") Integer proId);
}