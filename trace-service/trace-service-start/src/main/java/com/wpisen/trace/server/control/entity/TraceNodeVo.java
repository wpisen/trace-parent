package com.wpisen.trace.server.control.entity;

import com.wpisen.trace.agent.trace.TraceNode;

/**
 * Description: TraceNode 展示实体<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/1/19 17:34
 * @since JDK 1.7
 */
public class TraceNodeVo extends TraceNode implements java.io.Serializable{
	private static final long serialVersionUID = -7453969794674334512L;
	private String invokerName;//
    private String _parentId;
    private String useTime; // 总耗时

    public String getInvokerName() {
        return invokerName;
    }

    public void setInvokerName(String invokerName) {
        this.invokerName = invokerName;
    }

    public String get_parentId() {
        return _parentId;
    }

    public void set_parentId(String _parentId) {
        this._parentId = _parentId;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }
}
