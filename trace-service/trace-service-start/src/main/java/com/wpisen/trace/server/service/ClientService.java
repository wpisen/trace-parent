package com.wpisen.trace.server.service;

import com.wpisen.trace.server.common.TraceException;
import com.wpisen.trace.server.dao.entity.ClientSession;
import com.wpisen.trace.server.dao.entity.Project;
import com.wpisen.trace.server.model.ClientLoginParam;
import com.wpisen.trace.server.model.ClientSessionVo;
import com.wpisen.trace.server.model.UpdateLog;

/**
 * Description:agent 客户端服务 <br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2016年11月16日 下午3:23:51
 * @since JDK 1.7
 */
public interface ClientService {
    /**
     * 指定客户端登录
     * @param login
     * @param sign
     * @return
     */
    public ClientSessionVo login(ClientLoginParam login, String sign);

    /**
     * 根据版本签名查找指定版本内容
     * @param md5
     * @return
     */
    public byte[] download(String md5);

    // TODO 记录更新日志
    public void addUpdateLog(UpdateLog log);

    /**
     * 根据Session ID 获取客户端会话
     *
     * @param sessionId
     * @return
     * @throws TraceException 当指定session 不存在的时候抛出此异常
     */
    public ClientSession getSession(Integer sessionId) throws TraceException;

    /**
     * 根据 项目key 获取项目信息
     * @param proKey
     * @return
     * @throws TraceException 当指定项目不存在时抛出此异常
     */
    public Project getProject(String proKey)throws TraceException;

    /***
     * 更新指定会话的心跳
     * @param sessionId
     */
    public void sendSessionEcho(Integer sessionId);

}
