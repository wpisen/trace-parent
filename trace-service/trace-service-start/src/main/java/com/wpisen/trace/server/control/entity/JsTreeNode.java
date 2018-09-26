package com.wpisen.trace.server.control.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: jsTree树节点<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/2/8 15:33
 * @since JDK 1.7
 */
public class JsTreeNode implements java.io.Serializable {
	private static final long serialVersionUID = -4390882609308536672L;
	private String id; // id
    private String text;// 文本
    private String icon;// 图标
    private List<JsTreeNode> children = new ArrayList<>(); // 子节点
    private State state;
    private String codeLines;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<JsTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<JsTreeNode> children) {
        this.children = children;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getCodeLines() {
        return codeLines;
    }

    public void setCodeLines(String codeLines) {
        this.codeLines = codeLines;
    }

    /**
     * 节点状态
     */
    public static class State {
        boolean selected = false; // 是否选中
        boolean disabled = false;//  是否禁用，不能点击
        boolean opened = false;// 是否展开子节点

        public State() {
        }

        public State(boolean selected, boolean disabled, boolean opened) {
            this.selected = selected;
            this.disabled = disabled;
            this.opened = opened;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public boolean isOpened() {
            return opened;
        }

        public void setOpened(boolean opened) {
            this.opened = opened;
        }
    }
}
