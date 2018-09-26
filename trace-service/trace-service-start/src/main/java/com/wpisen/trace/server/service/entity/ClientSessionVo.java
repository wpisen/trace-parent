package com.wpisen.trace.server.service.entity;

import com.wpisen.trace.server.dao.entity.ClientSession;

/**
 * Created by wpisen on 17/6/26.
 */
public class ClientSessionVo extends ClientSession implements  java.io.Serializable {
	private static final long serialVersionUID = -6143508024593958639L;
	private String statusText;  // 应用当前状态
    private String lastActiveTimeText; // 最后活跃时间

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getLastActiveTimeText() {
        return lastActiveTimeText;
    }

    public void setLastActiveTimeText(String lastActiveTimeText) {
        this.lastActiveTimeText = lastActiveTimeText;
    }
}
