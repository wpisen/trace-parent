package com.wpisen.trace.server.control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpisen.trace.server.common.TraceException;
import com.wpisen.trace.server.common.TraceUtils;
import com.wpisen.trace.server.common.UtilEncryption;
import com.wpisen.trace.server.model.ClientLoginParam;
import com.wpisen.trace.server.model.ClientSessionVo;
import com.wpisen.trace.server.service.ClientService;

import org.springframework.web.bind.annotation.RestController;

/**
 *客户端更新与登陆控制器
 *
 */
@RestController
@RequestMapping("/api")
public class ClientControl {
    static final Logger logger = LoggerFactory.getLogger(ClientControl.class);
    @Autowired
    ClientService clientService;

    // TODO 异常流程处理
    @RequestMapping(params = "method=client.login")
    @ResponseBody
    public String login(ClientLoginParam loginParam, String sign, HttpServletRequest request, HttpServletResponse response) {
        request.getRemoteHost();
        ClientSessionVo session = clientService.login(loginParam, sign);
        String str = converHttpData(session);
        /*
         * try { response.setCharacterEncoding("UTF-8");
         * response.getWriter().write(str); } catch (IOException e) {
         * logger.error("登陆会话信息返回失败", e); }
         */
        return str;

    }

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

        /*
         * try { response.setCharacterEncoding("UTF-8");
         * response.setContentType("text/html;charset=UTF-8");
         * response.addHeader("cbt-error-code", code);
         * response.getWriter().print(message); } catch (IOException e) {
         * logger.error("结果返回失败", e); }
         */
        // response.addHeader("cbt-error-code", code);
        // return message;
        /*
         * Map<String, String> map = new HashMap<String, String>();
         * map.put("code", code); map.put("message", message);
         */
        // response.setCharacterEncoding("UTF-8");
        response.addHeader("cbt-error-code", code);
        return message;
    }

    // TODO 异常流程处理
    @RequestMapping(params = "method=client.download")
    public void downloads(String key, HttpServletResponse response) {
        byte[] s = clientService.download(key);
        if (s == null) {
            response.setStatus(404);
        }
        try {
            ServletOutputStream out = response.getOutputStream();
            out.write(s);
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("客户端上传失败", e);
        }
    }
    @RequestMapping(params = "method=client.sendEcho")
    public void sendEcho(Integer sessionId){
        // 更新心跳
        clientService.sendSessionEcho(sessionId);
        logger.debug("接收到客户端的心跳 sessionId=" + sessionId);
    }
    private String converHttpData(ClientSessionVo s) {

        StringBuilder builder = new StringBuilder();
        if (s.getSessionId() != null) {
            builder.append("sessionId=");
            builder.append(s.getSessionId());
            builder.append("&");
        }
        if (s.getProKey() != null) {
            builder.append("proKey=");
            builder.append(s.getProKey());
            builder.append("&");
        }
        if (s.getAppId() != null) {
            builder.append("appId=");
            builder.append(s.getAppId());
            builder.append("&");
        }
        if (s.getClientVersion() != null) {
            builder.append("clientVersion=");
            builder.append(s.getClientVersion());
            builder.append("&");
        }
        if (s.getClientMd5() != null) {
            builder.append("clientMd5=");
            builder.append(s.getClientMd5());
            builder.append("&");
        }
        // 转base64处理
        if (s.getClientUploadUrls() != null) {
            builder.append("clientUploadUrls=");
            String arrs = Arrays.toString(s.getClientUploadUrls());
            arrs = arrs.substring(1, arrs.length() - 1);
            builder.append(toBase64(arrs));
            builder.append("&");
        }
        if (s.getLoginTime() != null) {
            builder.append("loginTime=");
            builder.append(s.getLoginTime());
            builder.append("&");
        }
        // 转base64处理
        if (s.getConfigs() != null) {
            builder.append("configs=");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                s.getConfigs().store(out, "");
            } catch (IOException e) {
                logger.error("", e);
            }
            builder.append(UtilEncryption.encodeBase64(out.toByteArray()));
        }
        if (builder.toString().endsWith("&"))
            builder = builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    private String toBase64(String str) {
        return UtilEncryption.encodeBase64(str.getBytes(Charset.forName("UTF-8")));
    }

    public static void main(String[] args) {
        Arrays.deepToString(new Object[] {});
        Arrays.deepToString(args);
    }
}
