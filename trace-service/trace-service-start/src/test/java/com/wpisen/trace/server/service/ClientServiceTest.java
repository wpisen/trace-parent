package com.wpisen.trace.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.Assert;

import com.wpisen.trace.server.common.TraceUtils;
import com.wpisen.trace.server.BasiceTest;
import com.wpisen.trace.server.common.NetUtils;
import com.wpisen.trace.server.common.UtilEncryption;
import com.wpisen.trace.server.model.ClientLoginParam;
import com.wpisen.trace.server.model.ClientSessionVo;
import com.wpisen.trace.server.service.ClientService;

@SuppressWarnings("deprecation")
public class ClientServiceTest extends BasiceTest {

	@Ignore
    @Test
    public void logindescribesTest() {
        String proKey = "b71e2da2f7b74cba94ad008403ba594f";
        String proSecret = "79e7b7950b5940cf8fdc8ab40c434fe8";
        ClientService clientService = getBean(ClientService.class);
        ClientLoginParam login = new ClientLoginParam();
        login.setAppId("member-center");
        login.setAppPath("F:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp3/wtpwebapps/bubugao-member-center-web");
        login.setClientIp(NetUtils.getLocalHost());
        login.setClientMacAddress(NetUtils.getLocalMac());
//        login.setGatewayIp("10.201.9.1");
        login.setLoginTime(System.currentTimeMillis());
        login.setPlatform(TraceUtils.platform.java.toString());
        login.setProKey(proKey);
        String sign = UtilEncryption.md5(proSecret + login.toString() + proSecret);

        ClientSessionVo session = clientService.login(login, sign);
        Assert.notNull(session);
    }
	@Ignore
    @Test
    public void httpLoginTest() throws IOException {
        URL url = new URL(
                "http://localhost:8080/api?method=client.login&sign=2adb203ef1069915f78d2219074d9bcb&appPath=F:\\git\\trace-agent\\agent-bootstrap&platform=java&loginTime=1479883519276&appId=test_team&proKey=b71e2da2f7b74cba94ad008403ba594f&clientIp=10.201.9.121&clientMacAddress=44-37-e6-cc-67-10");

        List<?> list = IOUtils.readLines(url.openStream(), "UTF-8");
        System.out.println(Arrays.toString(list.toArray()));
    }
	@Ignore
    @Test
    public void postTEst() throws IOException {
        URL realUrl = new URL(
                "http://localhost:8080/api?method=client.login&sign=2adb203ef1069915f78d2219074d9bcb&appPath=F:\\git\\trace-agent\\agent-bootstrap&platform=java&loginTime=1479883519276&appId=test_team&proKey=1b71e2da2f7b74cba94ad008403ba594f&clientIp=10.201.9.121&clientMacAddress=44-37-e6-cc-67-10");
        // 打开和URL之间的连接
        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
        // 设置通用的请求属性
        // System.out.println(conn.getContentEncoding());
        conn.setRequestProperty("accept", "text/html;charset=UTF-8");
        // conn.setRequestProperty("connection", "Keep-Alive");
        // 发送POST请求必须设置如下两行
        // conn.setDoOutput(true);
        // conn.setDoInput(true);
        conn.setConnectTimeout(2000);
        conn.setReadTimeout(3000);
        // 获取URLConnection对象对应的输出流
        // PrintWriter out = new PrintWriter(conn.getOutputStream());
        // 发送请求参数
        // out.print("");
        // flush输出流的缓冲
        // out.flush();
        // 定义BufferedReader输入流来读取URL的响应

        String errorCode = conn.getHeaderField("cbt-error-code");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        System.out.println(errorCode);
        String line;
        String result = "";
        while ((line = in.readLine()) != null) {
            result += line;
        }
        System.out.println(result);
    }
	@Ignore
    @Test
    public void downloadTest(){
        ClientService clientService = getBean(ClientService.class);
        String md5="8e21ff60e4036571b2053167156be18e";
        byte[] result = clientService.download(md5);
        Assert.isTrue(result.length>0);
        Assert.isTrue(UtilEncryption.md5(result).equals(md5));
    }
	@Ignore
    @Test
    public void createProjectKey() {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        // System.out.println(NetUtils.getLocalMac());
    }

}
