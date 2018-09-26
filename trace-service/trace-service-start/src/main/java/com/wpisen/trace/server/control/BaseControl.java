package com.wpisen.trace.server.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpisen.trace.server.common.TraceException;
import com.wpisen.trace.server.common.TraceUtils;
import com.wpisen.trace.server.control.entity.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 控制层基类<br/>
 * 主要用于处理异常
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/1/18 16:46
 * @since JDK 1.7
 */
public class BaseControl {
    static final Logger logger = LoggerFactory.getLogger(BaseControl.class);

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public String handleException(HttpServletRequest request, HttpServletResponse response, Throwable ex) {
        String code = null;
        String message = null;
        if (ex instanceof TraceException) {
            TraceException traceEx = (TraceException) ex;
            if (TraceUtils.isBusinessException(traceEx.getCode())) { // 业务异常
                code = String.valueOf(traceEx.getCode());
                message = traceEx.getMessage();
                logger.error("业务异常", ex);
            } else if (TraceUtils.isServerException(traceEx.getCode())) { // 内部服务异常
                code = String.valueOf(TraceUtils.error_server);
                message = "服务出错了";
                logger.error("服务异常", ex);
            }
        }
        if (code == null) {
            code = String.valueOf(TraceUtils.error_server);
            message = "服务出错了";
            logger.error("其它异常", ex);
        }

        response.addHeader("cbt-error-code", code);
        return message;
    }

    // TODO: 2017/2/4 用户登陆实现
    public UserSession getSessionInfo(HttpServletRequest request){
        UserSession mock = new UserSession();
        mock.setCurrentProjectId(1);
        mock.setCurrentProjectKey("b71e2da2f7b74cba94ad008403ba594f");
        mock.setUserId(1);
        mock.setUserName("wpisen");
        return mock;
    }
}
