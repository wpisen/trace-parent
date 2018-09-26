package com.wpisen.trace.server.common;

// 业务异常
public class TraceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int code;
    private String notice;

    public TraceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public TraceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
