package com.wpisen.trace.server.model;

import java.util.Date;

public class UpdateLog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String appName;

    private Integer clientId;

    private String previousVersionName;

    private String versionName;

    private String versionMd5;

    private Integer versionSize;

    private String clientIp;

    private String clientMacAddress;

    private String clientPlatform;

    private String surroundings;

    private Date beginTime;

    private Integer consumeTime;

    private String state;

    private String failMessage;

    private String updateUrl;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getPreviousVersionName() {
        return previousVersionName;
    }

    public void setPreviousVersionName(String previousVersionName) {
        this.previousVersionName = previousVersionName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionMd5() {
        return versionMd5;
    }

    public void setVersionMd5(String versionMd5) {
        this.versionMd5 = versionMd5;
    }

    public Integer getVersionSize() {
        return versionSize;
    }

    public void setVersionSize(Integer versionSize) {
        this.versionSize = versionSize;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientMacAddress() {
        return clientMacAddress;
    }

    public void setClientMacAddress(String clientMacAddress) {
        this.clientMacAddress = clientMacAddress;
    }

    public String getClientPlatform() {
        return clientPlatform;
    }

    public void setClientPlatform(String clientPlatform) {
        this.clientPlatform = clientPlatform;
    }

    public String getSurroundings() {
        return surroundings;
    }

    public void setSurroundings(String surroundings) {
        this.surroundings = surroundings;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Integer consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

}
