package com.wpisen.trace.server.service;

/**
 * Description: 跟踪节点存储服务<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/1/18 11:43
 * @since JDK 1.7
 */
public interface NodeStoreService {
	/**
	 * 保存TraceNode
	 *
	 * @param proId
	 *            项目ID
	 * @param nodeJsons
	 *            待存储的node 的json 字符串
	 */
	public void saveTraceNode(Integer proId, String[] nodeJsons);
}
