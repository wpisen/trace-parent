package com.wpisen.trace.server.service;

import com.wpisen.trace.agent.trace.TraceNode;
import com.wpisen.trace.server.BasiceTest;
import com.wpisen.trace.server.common.UtilJson;
import com.wpisen.trace.server.service.NodeStoreService;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Description: TODO 一句话描述类是干什么的<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/1/19 9:48
 * @since JDK 1.7
 */
public class NodeStoreServiceTest extends BasiceTest {

    private NodeStoreService storeService;
    @Ignore
    @Before
    public void setUp() throws Exception {
        storeService = getBean(NodeStoreService.class);
        TraceNode node = new TraceNode();
        node.setRpcId("0.1");
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
    public void saveTest() throws Exception {
        ArrayList<TraceNode> nodes = mockNodes();
        String[] nodesJson = new String[nodes.size()];
        int i = 0;
        for (TraceNode node : nodes) {
            nodesJson[i++] = UtilJson.toJsonString(node);
        }
        storeService.saveTraceNode(1, nodesJson);
    }
}
