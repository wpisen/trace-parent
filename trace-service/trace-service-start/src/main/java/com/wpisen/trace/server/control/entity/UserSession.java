package com.wpisen.trace.server.control.entity;

import java.io.Serializable;

/**
 * Description: TODO 一句话描述类是干什么的<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/2/4 17:10
 * @since JDK 1.7
 */
public class UserSession implements Serializable {
	private static final long serialVersionUID = -6564453566736353569L;
	private String currentProjectKey; //  用户当前选中项目
    private Integer currentProjectId; //  当前选中项目ID
    private Integer userId; // 用户ID
    private String userName;// 用户名称

    public String getCurrentProjectKey() {
        return currentProjectKey;
    }

    public void setCurrentProjectKey(String currentProjectKey) {
        this.currentProjectKey = currentProjectKey;
    }

    public Integer getCurrentProjectId() {
        return currentProjectId;
    }

    public void setCurrentProjectId(Integer currentProjectId) {
        this.currentProjectId = currentProjectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
