package com.wpisen.trace.server.service;

import java.util.List;

import com.wpisen.trace.server.service.entity.ClientSessionVo;

/**
 *
 * 项目系统管理
 * Created by wpisen on 17/6/26.
 */
public interface ProjectSystemManage {
    /**
     * 获取当前在线
     * @param proId
     * @return
     */
    public List<ClientSessionVo> getActiveSessions(Integer proId);
}
