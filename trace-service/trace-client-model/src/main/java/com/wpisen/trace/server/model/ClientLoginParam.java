package com.wpisen.trace.server.model;


public class ClientLoginParam implements java.io.Serializable {
	private static final long serialVersionUID = -1875215344313180798L;
	private String proKey;
    private String platform;
    private Long loginTime;
    private String appId;
    private String appPath;

    private String clientIp;
    private String clientMacAddress;

    public String getProKey() {
        return proKey;
    }

    public void setProKey(String proKey) {
        this.proKey = proKey;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (proKey != null) {
            builder.append("proKey=");
            builder.append(proKey);
            builder.append(", ");
        }
        if (platform != null) {
            builder.append("platform=");
            builder.append(platform);
            builder.append(", ");
        }
        if (loginTime != null) {
            builder.append("loginTime=");
            builder.append(loginTime);
            builder.append(", ");
        }
        if (appId != null) {
            builder.append("appId=");
            builder.append(appId);
            builder.append(", ");
        }
        if (appPath != null) {
            builder.append("appPath=");
            builder.append(appPath);
            builder.append(", ");
        }
        if (clientIp != null) {
            builder.append("clientIp=");
            builder.append(clientIp);
            builder.append(", ");
        }
        if (clientMacAddress != null) {
            builder.append("clientMacAddress=");
            builder.append(clientMacAddress);
        }
        return builder.toString();
    }

}
