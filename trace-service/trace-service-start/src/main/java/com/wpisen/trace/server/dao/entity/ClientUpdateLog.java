package com.wpisen.trace.server.dao.entity;

import java.util.Date;

public class ClientUpdateLog {
    private Integer logId;

    private Integer proId;

    private String appName;

    private Integer clientId;

    private String previousVersionName;

    private Integer versionId;

    private String versionName;

    private String versionMd5;

    private Integer versionSize;

    private String gatewayIp;

    private String clientIp;

    private String clientMacAddress;

    private String clientPlatform;

    private String surroundings;

    private Date beginTime;

    private Integer consumeTime;

    private String state;

    private String failMessage;

    private String updateUrl;

    private Date createTime;

    private Date lastUpdateTime;

    private Boolean disable;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
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
        this.previousVersionName = previousVersionName == null ? null : previousVersionName.trim();
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    public String getVersionMd5() {
        return versionMd5;
    }

    public void setVersionMd5(String versionMd5) {
        this.versionMd5 = versionMd5 == null ? null : versionMd5.trim();
    }

    public Integer getVersionSize() {
        return versionSize;
    }

    public void setVersionSize(Integer versionSize) {
        this.versionSize = versionSize;
    }

    public String getGatewayIp() {
        return gatewayIp;
    }

    public void setGatewayIp(String gatewayIp) {
        this.gatewayIp = gatewayIp == null ? null : gatewayIp.trim();
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
    }

    public String getClientMacAddress() {
        return clientMacAddress;
    }

    public void setClientMacAddress(String clientMacAddress) {
        this.clientMacAddress = clientMacAddress == null ? null : clientMacAddress.trim();
    }

    public String getClientPlatform() {
        return clientPlatform;
    }

    public void setClientPlatform(String clientPlatform) {
        this.clientPlatform = clientPlatform == null ? null : clientPlatform.trim();
    }

    public String getSurroundings() {
        return surroundings;
    }

    public void setSurroundings(String surroundings) {
        this.surroundings = surroundings == null ? null : surroundings.trim();
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
        this.state = state == null ? null : state.trim();
    }

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage == null ? null : failMessage.trim();
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl == null ? null : updateUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }
}