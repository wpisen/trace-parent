package com.wpisen.trace.server.dao.entity;

import java.util.Date;

public class ClientProjectBinding {
    private Integer bindId;

    private Integer clientId;

    private Integer proId;

    private String platform;

    private Date createTime;

    private Date lastUpdateTime;

    private Boolean disable;

    public Integer getBindId() {
        return bindId;
    }

    public void setBindId(Integer bindId) {
        this.bindId = bindId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
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