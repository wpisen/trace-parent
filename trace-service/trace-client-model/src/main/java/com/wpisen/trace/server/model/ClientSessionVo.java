package com.wpisen.trace.server.model;

import java.util.Arrays;
import java.util.Properties;

public class ClientSessionVo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String sessionId; // 会话ID,唯一性

    private String proKey; // 项目KEY

    private String appId; // 应用ID

    private String clientVersion; // 客户端版本

    private String clientMd5; // 客户端MD5验证码

    private String[] clientUploadUrls; // 客户端更新地址

    private Long loginTime; // 登陆时间

    private Properties configs; // 属性配置
    

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getProKey() {
        return proKey;
    }

    public void setProKey(String proKey) {
        this.proKey = proKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getClientMd5() {
        return clientMd5;
    }

    public void setClientMd5(String clientMd5) {
        this.clientMd5 = clientMd5;
    }

    public String[] getClientUploadUrls() {
        return clientUploadUrls;
    }

    public void setClientUploadUrls(String[] clientUploadUrls) {
        this.clientUploadUrls = clientUploadUrls;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Properties getConfigs() {
        return configs;
    }

    public void setConfigs(Properties configs) {
        this.configs = configs;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (sessionId != null) {
            builder.append("sessionId=");
            builder.append(sessionId);
            builder.append("& ");
        }
        if (proKey != null) {
            builder.append("proKey=");
            builder.append(proKey);
            builder.append("& ");
        }
        if (appId != null) {
            builder.append("appId=");
            builder.append(appId);
            builder.append("& ");
        }
        if (clientVersion != null) {
            builder.append("clientVersion=");
            builder.append(clientVersion);
            builder.append("& ");
        }
        if (clientMd5 != null) {
            builder.append("clientMd5=");
            builder.append(clientMd5);
            builder.append("& ");
        }
        if (clientUploadUrls != null) {
            builder.append("clientUploadUrls=");
            builder.append(Arrays.toString(clientUploadUrls));
            builder.append("& ");
        }
        if (loginTime != null) {
            builder.append("loginTime=");
            builder.append(loginTime);
            builder.append("& ");
        }
        if (configs != null) {
            builder.append("configs=");
            builder.append(configs);
        }
        return builder.toString();
    }
}
