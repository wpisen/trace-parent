package com.wpisen.trace.server.control.entity;

/**
 * Description:请求参数<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017年1月20日 下午14:45:43
 * @since JDK 1.7
 */
public class RequestTableParam {
    Integer pageSize = 15; // 指定每页大小时
    Integer pageIndex = 1; // 指定页
    String clientIp; // 客户端IP
    String addressIp; // 目标IP
    String queryWord; // 关键词查询
    Long afterSeconds = 3600L; // 节点创建时间在指定秒数之内.默认在1小时之内
    String startTime;
    String endTime;
    String nodeType;
    String servicePath;
    private String projectKey;// 项目key

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public long getAfterSeconds() {
        return afterSeconds;
    }

    public void setAfterSeconds(long afterSeconds) {
        this.afterSeconds = afterSeconds;
    }

    public String getQueryWord() {
        return queryWord;
    }

    public void setQueryWord(String queryWord) {
        this.queryWord = queryWord;
    }

    public void setAfterSeconds(Long afterSeconds) {
        this.afterSeconds = afterSeconds;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String toHttpParams() {
        StringBuilder builder = new StringBuilder();
        if (pageSize != null) {
            builder.append("&pageSize=");
            builder.append(pageSize);
        }
        if (pageIndex != null) {
            builder.append("&pageIndex=");
            builder.append(pageIndex);
        }
        if (clientIp != null) {
            builder.append("&clientIp=");
            builder.append(clientIp);
        }
        if (addressIp != null) {
            builder.append("&addressIp=");
            builder.append(addressIp);
        }
        if (queryWord != null) {
            builder.append("&queryWord=");
            builder.append(queryWord);
        }
        if (afterSeconds != null) {
            builder.append("&afterSeconds=");
            builder.append(afterSeconds);
        }
        if (startTime != null) {
            builder.append("&startTime=");
            builder.append(startTime);
        }
        if (endTime != null) {
            builder.append("&endTime=");
            builder.append(endTime);
        }
        if (nodeType != null) {
            builder.append("&nodeType=");
            builder.append(nodeType);
        }
        if (servicePath != null) {
            builder.append("&servicePath=");
            builder.append(servicePath);
        }
        if (projectKey != null) {
            builder.append("&projectKey=");
            builder.append(projectKey);
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(0);
        }
        return builder.toString();

    }

	public String getAddressIp() {
		return addressIp;
	}

	public void setAddressIp(String addressIp) {
		this.addressIp = addressIp;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getServicePath() {
		return servicePath;
	}

	public void setServicePath(String servicePath) {
		this.servicePath = servicePath;
	}
}