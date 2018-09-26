package com.wpisen.trace.server.dao.entity;

import java.util.Date;

public class StaticProperties {
    private Integer staticId;

    private String staticKey;

    private String staticValue;

    private Integer history;

    private Date lastUpdatTime;

    private String remarks;

    private Integer type;

    private Boolean disable;

    private String staticText;

    public Integer getStaticId() {
        return staticId;
    }

    public void setStaticId(Integer staticId) {
        this.staticId = staticId;
    }

    public String getStaticKey() {
        return staticKey;
    }

    public void setStaticKey(String staticKey) {
        this.staticKey = staticKey == null ? null : staticKey.trim();
    }

    public String getStaticValue() {
        return staticValue;
    }

    public void setStaticValue(String staticValue) {
        this.staticValue = staticValue == null ? null : staticValue.trim();
    }

    public Integer getHistory() {
        return history;
    }

    public void setHistory(Integer history) {
        this.history = history;
    }

    public Date getLastUpdatTime() {
        return lastUpdatTime;
    }

    public void setLastUpdatTime(Date lastUpdatTime) {
        this.lastUpdatTime = lastUpdatTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public String getStaticText() {
        return staticText;
    }

    public void setStaticText(String staticText) {
        this.staticText = staticText == null ? null : staticText.trim();
    }
}