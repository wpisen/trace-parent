package com.wpisen.trace.server.service.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Description: TODO 一句话描述类是干什么的<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/2/14 17:14
 * @since JDK 1.7
 */
public class StackNodeVo implements java.io.Serializable {
	private static final long serialVersionUID = -5657846441089641262L;
	private String id;
    private long classId;//
    private String className;
    private String methodName;
    private final List<Integer> lines = new LinkedList<Integer>();
    private boolean done;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<Integer> getLines() {
        return lines;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
