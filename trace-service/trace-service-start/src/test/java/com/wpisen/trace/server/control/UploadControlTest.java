package com.wpisen.trace.server.control;

import com.wpisen.trace.agent.trace.TraceNode;
import com.wpisen.trace.server.common.UtilJson;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Description: TODO 一句话描述类是干什么的<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/1/19 11:44
 * @since JDK 1.7
 */
public class UploadControlTest {
	
    @Before
    public void setUp() throws Exception {
    }

    private ArrayList<TraceNode> mockNodes() {
        ArrayList<TraceNode> nodes = new ArrayList<>();
        String traceId = UUID.randomUUID().toString().replaceAll("-", "");
        {
            TraceNode traceNode = new TraceNode();
            traceNode.setTraceId(traceId);
            traceNode.setResultState("ok");
            traceNode.setNodeType("http");
            traceNode.setBeginTime(System.currentTimeMillis());
            traceNode.setRpcId("0");
            traceNode.setAppId("66");
            traceNode.setServicePath("zookper://10.200.51.93:2181");
            traceNode.setServiceName("com.bubugao.umpBizServie");
            traceNode.setBeginTime(System.currentTimeMillis());
            traceNode.setEndTime(System.currentTimeMillis() + 200);
            traceNode.setAppDetail("empty");
            nodes.add(traceNode);
        }
        for (int i = 1; i < 5; i++) {
            TraceNode traceNode = new TraceNode();
            traceNode.setTraceId(traceId);
            traceNode.setResultState("ok");
            traceNode.setNodeType("dubbo rpc");
            traceNode.setBeginTime(System.currentTimeMillis());
            traceNode.setRpcId("0." + i);
            traceNode.setAppId("66");
            traceNode.setServiceName("com.bubugao.umpBizServie");
            traceNode.setBeginTime(System.currentTimeMillis());
            traceNode.setEndTime(System.currentTimeMillis() + 200);
            traceNode.setServicePath("zookper://10.200.51.93:2181");
            nodes.add(traceNode);
        }
        {
            TraceNode traceNode = new TraceNode();
            traceNode.setTraceId(traceId);
            traceNode.setNodeType("dubbo rpc");
            traceNode.setResultState("ok");
            traceNode.setBeginTime(System.currentTimeMillis());
            traceNode.setRpcId("0.2");
            traceNode.setAppId("66");
            traceNode.setServiceName("com.bubugao.umpBizServie");
            traceNode.setBeginTime(System.currentTimeMillis());
            traceNode.setServicePath("zookper://10.200.51.93:2181");
            traceNode.setEndTime(System.currentTimeMillis() + 200);
            nodes.add(traceNode);
        }
        {
            TraceNode traceNode = new TraceNode();
            traceNode.setTraceId(traceId);
            traceNode.setNodeType("dubbo rpc");
            traceNode.setResultState("ok");
            traceNode.setBeginTime(System.currentTimeMillis());
            traceNode.setRpcId("0.2.1");
            traceNode.setAppId("65");
            traceNode.setServicePath("zookper://10.200.51.93:2181");
            traceNode.setServiceName("com.bubugao.umpBizServie");
            traceNode.setBeginTime(System.currentTimeMillis());
            traceNode.setEndTime(System.currentTimeMillis() + 200);
            nodes.add(traceNode);
        }
        return nodes;
    }

    @Ignore
    @Test
    public void uploadNode() throws Exception {
        String urlText = "http://localhost:8080/upload?type=TraceNode&sessionId=1";
        ArrayList<TraceNode> nodes = mockNodes();
        for (TraceNode node : nodes) {
            urlText += "&nodeJson=" + URLEncoder.encode(UtilJson.toJsonString(node), "UTF-8");
        }
        URL url = new URL(urlText);
        InputStream stream = url.openStream();
        System.out.println(IOUtils.toString(stream));
        ;
    }
    @Ignore
    @Test
    public void execHttp() throws UnsupportedEncodingException {
        String url = "http://localhost:8080/upload";
        ArrayList<TraceNode> nodes = mockNodes();

        String param = "type=TraceNode&sessionId=1";
        for (TraceNode node : nodes) {
            param += "&nodeJson=" + URLEncoder.encode(UtilJson.toJsonString(node), "UTF-8");
        }

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setUseCaches(false);

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException("上传失败", e);
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }


}