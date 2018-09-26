package com.wpisen.trace.server.control.entity;

import java.io.Serializable;

/**
 * Description:gojs 定制节点模型<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/2/4 15:30
 * @since JDK 1.7
 */
public class GoNode implements java.io.Serializable {
	private static final long serialVersionUID = 7439477865907142042L;
	private boolean copiesArrays;
    private boolean copiesArrayObjects;
    private GoLinkData[] linkDataArray;
    private GoNodeData[] nodeDataArray;

    public boolean isCopiesArrays() {
        return copiesArrays;
    }

    public void setCopiesArrays(boolean copiesArrays) {
        this.copiesArrays = copiesArrays;
    }

    public boolean isCopiesArrayObjects() {
        return copiesArrayObjects;
    }

    public void setCopiesArrayObjects(boolean copiesArrayObjects) {
        this.copiesArrayObjects = copiesArrayObjects;
    }

    public GoLinkData[] getLinkDataArray() {
        return linkDataArray;
    }

    public void setLinkDataArray(GoLinkData[] linkDataArray) {
        this.linkDataArray = linkDataArray;
    }

    public GoNodeData[] getNodeDataArray() {
        return nodeDataArray;
    }

    public void setNodeDataArray(GoNodeData[] nodeDataArray) {
        this.nodeDataArray = nodeDataArray;
    }

    /**
     * Description: TODO 一句话描述类是干什么的<br/>
     *
     * @author wpisen@wpisen.com
     * @version 1.0
     * @date: 2017/2/4 16:36
     * @since JDK 1.7
     */
    public static class GoLinkData implements Serializable {
		private static final long serialVersionUID = 1622917623594172377L;
		String from;
        String to;
        String text;

        public GoLinkData() {
        }

        public GoLinkData(String from, String to) {
            this.from = from;
            this.to = to;
        }

        public GoLinkData(String from, String to, String text) {
            this.from = from;
            this.to = to;
            this.text = text;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    /**
     * Description: TODO 一句话描述类是干什么的<br/>
     *
     * @author wpisen@wpisen.com
     * @version 1.0
     * @date: 2017/2/4 16:34
     * @since JDK 1.7
     */
    public static class GoNodeData implements Serializable {
		private static final long serialVersionUID = 8093974227327377517L;
		String key;
        String category;
        String text;

        public GoNodeData() {
        }

        public GoNodeData(String key, String category, String text) {
            this.key = key;
            this.category = category;
            this.text = text;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }


}
