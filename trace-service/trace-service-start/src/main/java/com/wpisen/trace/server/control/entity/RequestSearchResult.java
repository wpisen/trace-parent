package com.wpisen.trace.server.control.entity;

/**
 * Description:请求返回结果<br/>
 *
 * @author zengguangwei@bubugao.com
 * @version 1.0
 * @date: 2016年8月18日 下午7:39:43
 * @since JDK 1.7
 */
public class RequestSearchResult {
    private String clipentIp;
    private String createTime;
    private String title;
    private String describe;
    private String traceId;
    private String errorMessage;
    private String rpcId;

    public String getClipentIp() {
        return clipentIp;
    }

    public void setRpcId(String rpcId) {
        this.rpcId = rpcId;
    }

    public String getRpcId() {
        return rpcId;
    }

    public void setClipentIp(String clipentIp) {
        this.clipentIp = clipentIp;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}