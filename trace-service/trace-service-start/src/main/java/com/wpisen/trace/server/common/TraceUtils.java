package com.wpisen.trace.server.common;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public final class TraceUtils {
    public enum platform {
        java, php
    }

    public enum ErrorType {
        parameter, config, process, environment
    }
    public static final String es_index_prefix="trace_nodes_";
    /**
     * 100至499 为业务异常
     */
    public static final int error_business = 100;
    /**
     * 业务参数类错误 parameter
     */
    public static final int error_param = 101;

    /**
     * 参数设置不达要求 parameter
     */
    public static final int error_config = 201;

    /**
     * 执行流程出错 process
     */
    public static final int error_process = 301;

    /**
     * 业务环境不满足 environment
     */
    public static final int error_environment = 401;

    /**
     * 500至999 以后为内部服务异常
     */
    public static final int error_server = 500;

    /**
     * 应用逻辑出错
     */
    public static final int error_resource = 501;

    /**
     * 数据不完整，或脏数据导致
     */
    public static final int error_data = 601;

    /**
     * 资源连接错误
     */
    public static final int error_logic = 701;

    public static void error(int code, Throwable cause, String message) {
        throw new TraceException(code, message, cause);
    }

    public static boolean isBusinessException(int code) {
        return code > 100 && code < 500;
    }

    public static boolean isServerException(int code) {
        return code > 500 && code < 1000;
    }

    public static void hasText(String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new TraceException(error_param, message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new TraceException(error_param, message);
        }
    }

    public static void notNull(Object project, String message) {
        if (project == null) {
            throw new TraceException(error_param, message);
        }
    }

    public static void notEmpty(Object[] array, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new TraceException(error_param, message);
        }
    }
}
