package com.wpisen.trace.server.dao.entity;

import java.util.Date;

public class ClientVersion {
    private Integer versionId;

    private Integer clientId;

    private String versionName;

    private Integer versionNumber;

    private String dataMd5;

    private String describes;

    private Date createTime;

    private Date lastUpdateTime;

    private Boolean disable;

    private byte[] dataByte;

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getDataMd5() {
        return dataMd5;
    }

    public void setDataMd5(String dataMd5) {
        this.dataMd5 = dataMd5 == null ? null : dataMd5.trim();
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
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

    public byte[] getDataByte() {
        return dataByte;
    }

    public void setDataByte(byte[] dataByte) {
        this.dataByte = dataByte;
    }
}