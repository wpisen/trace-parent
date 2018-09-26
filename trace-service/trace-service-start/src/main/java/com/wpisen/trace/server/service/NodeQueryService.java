package com.wpisen.trace.server.service;

import com.wpisen.trace.agent.trace.TraceNode;
import com.wpisen.trace.server.service.entity.PageList;
import com.wpisen.trace.server.service.entity.SearchRequestParam;

import java.util.List;

/**
 * Description: 节点查询服务<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/1/18 11:44
 * @since JDK 1.7
 */
public interface NodeQueryService {
        /**
         * 根据复合参数搜索节点信息
         *
         * @param proId 项目ID
         * @param param 复合参数
         * @return 分页返回结果
         */
        public PageList<TraceNode> searchNodePage(Integer proId, SearchRequestParam param);

        /**
         * 返回指定Trace ID所有的节点
         *
         * @param proId
         * @param traceId
         * @return
         */
        List<TraceNode> getNodesByTraceId(Integer proId, String traceId);

        /**
         * 获取指定跟踪节点信息
         *
         * @param proId   项目ID
         * @param traceId 跟踪ID
         * @param nodeId  节点ID
         * @return 跟踪节点
         */
        TraceNode getNodeById(Integer proId, String traceId, String nodeId);
}
