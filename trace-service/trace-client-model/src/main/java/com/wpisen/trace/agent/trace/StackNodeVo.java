package com.wpisen.trace.agent.trace;

import java.util.LinkedList;
import java.util.List;

/**
 * Description: 代码堆栈节点对象<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/2/14 17:14
 * @since JDK 1.7
 */
public class StackNodeVo implements java.io.Serializable {
	private static final long serialVersionUID = -9006234111219336959L;
	private String id;
    private long classId;//
    private String className;
    private String methodName;
    private Long useTime; // nanotime
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

    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
