package com.wpisen.trace.server.dao.entity;

import java.util.Date;

public class Project {
    private Integer proId;

    private String name;

    private String proKey;

    private String proSecret;

    private String describes;

    private String belongsWay;

    private Integer belongsId;

    private Date createTime;

    private Date lastUpdateTime;

    private Boolean disable;

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getProKey() {
        return proKey;
    }

    public void setProKey(String proKey) {
        this.proKey = proKey == null ? null : proKey.trim();
    }

    public String getProSecret() {
        return proSecret;
    }

    public void setProSecret(String proSecret) {
        this.proSecret = proSecret == null ? null : proSecret.trim();
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
    }

    public String getBelongsWay() {
        return belongsWay;
    }

    public void setBelongsWay(String belongsWay) {
        this.belongsWay = belongsWay == null ? null : belongsWay.trim();
    }

    public Integer getBelongsId() {
        return belongsId;
    }

    public void setBelongsId(Integer belongsId) {
        this.belongsId = belongsId;
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