package com.wpisen.trace.server.dao.entity;

public class ProjectAppWithBLOBs extends ProjectApp {
    private String insidePackages;

    private String relysLib;

    private String configsJson;

    public String getInsidePackages() {
        return insidePackages;
    }

    public void setInsidePackages(String insidePackages) {
        this.insidePackages = insidePackages == null ? null : insidePackages.trim();
    }

    public String getRelysLib() {
        return relysLib;
    }

    public void setRelysLib(String relysLib) {
        this.relysLib = relysLib == null ? null : relysLib.trim();
    }

    public String getConfigsJson() {
        return configsJson;
    }

    public void setConfigsJson(String configsJson) {
        this.configsJson = configsJson == null ? null : configsJson.trim();
    }
}