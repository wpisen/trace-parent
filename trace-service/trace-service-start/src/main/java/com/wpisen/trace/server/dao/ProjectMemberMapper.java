package com.wpisen.trace.server.dao;

import com.wpisen.trace.server.dao.entity.ProjectMember;

public interface ProjectMemberMapper {
    int deleteByPrimaryKey(Integer memberId);

    int insert(ProjectMember record);

    int insertSelective(ProjectMember record);

    ProjectMember selectByPrimaryKey(Integer memberId);

    int updateByPrimaryKeySelective(ProjectMember record);

    int updateByPrimaryKey(ProjectMember record);
}