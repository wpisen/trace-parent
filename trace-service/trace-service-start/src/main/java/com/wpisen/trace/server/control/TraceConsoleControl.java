package com.wpisen.trace.server.control;

import com.wpisen.trace.agent.trace.TraceNode;
import com.wpisen.trace.server.common.TraceUtils;
import com.wpisen.trace.server.common.ForMatJSONStr;
import com.wpisen.trace.server.control.entity.D3GraphData;
import com.wpisen.trace.server.control.entity.GoNode;
import com.wpisen.trace.server.control.entity.UserSession;
import com.wpisen.trace.server.control.entity.GoNode.*;
import com.wpisen.trace.server.service.NodeQueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description: 跟踪控制台<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/2/4 10:45
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/trace")
public class TraceConsoleControl extends BaseControl {

    @Autowired
    NodeQueryService nodeQueryService;

    @RequestMapping("console")
    public ModelAndView openConsolePage(HttpServletRequest request, String traceId) {
        TraceUtils.hasText(traceId, "参数'traceId'不能为空");
        ModelAndView modelAndView = new ModelAndView("/trace/traceConsoleView");
        modelAndView.addObject("traceId", traceId);
        modelAndView.addObject("request",request);
        return modelAndView;
    }

    @RequestMapping("getFlowNodeData")
    @ResponseBody
    public GoNode getflowChartNodeData(HttpServletRequest request, String traceId) {
        TraceUtils.hasText(traceId, "参数 'traceId' 不能为空");
//        GoNode result = new ArrayList<>();
        UserSession session = getSessionInfo(request);
        List<TraceNode> traceNodes = nodeQueryService.getNodesByTraceId(session.getCurrentProjectId(), traceId);
        return convertGoNode(traceNodes);
    }

    @RequestMapping("getD3GraphData")
    @ResponseBody
    public D3GraphData getD3GraphData(HttpServletRequest request, String traceId) {
        TraceUtils.hasText(traceId, "参数 'traceId' 不能为空");
        UserSession session = getSessionInfo(request);
        List<TraceNode> traceNodes = nodeQueryService.getNodesByTraceId(session.getCurrentProjectId(), traceId);
        return convertGoD3GraphData(traceNodes);
    }

    @RequestMapping("getDetailInfo")
    public ModelAndView getDetailInfo(HttpServletRequest request, String traceId, String nodeId) {
        ModelAndView result = new ModelAndView("/trace/traceConsoleDetailContent");
        UserSession session = getSessionInfo(request);
        TraceNode node = nodeQueryService.getNodeById(session.getCurrentProjectId(), traceId, nodeId);
        result.addObject("node", node);

        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        result.addObject("invoke_time", format.format(new Date(node.getBeginTime())));
        if ("Data Base".equals(node.getNodeType())) {
           /* if (node.getInParam() != null) {
                String[] params = UtilJson.parse(node.getInParam(), String[].class);
                String sql = null;
                for (int i = 0; i < params.length; i++) {
                    if (i == 0)
                        sql = params[0];
                    else if (sql != null && sql.indexOf("?") > -1)
                        sql = sql.replaceFirst("\\?", "'" + params[i] + "'");
                }
                if (sql != null) {
                    // 格式化sql 语句
                    result.addObject("sql_text", SQLUtils.formatMySql(sql));
                } else {
                    result.addObject("sql_text", "error sql is empty");
                }
            }
            if(node.getOutParam()!=null){
                String[][] resultSet = UtilJson.parse(node.getOutParam(),
                        String[][].class);
                result.addObject("resultSet", resultSet);
            }
            result.setViewName("/trace/SqlDetailsView");*/
        } else {
            if (node.getInParam() != null) {
                if (node.getInParam().trim().startsWith("{") || node.getInParam().trim().startsWith("["))
                    result.addObject("in_param", ForMatJSONStr.format(node.getInParam()));
                else
                    result.addObject("in_param", node.getInParam());
            }
            if (node.getOutParam() != null) {
                if (node.getOutParam().trim().startsWith("{") || node.getInParam().trim().startsWith("["))
                    result.addObject("out_param", ForMatJSONStr.format(node.getOutParam()));
                else
                    result.addObject("out_param", node.getOutParam());
            }
            //result.setViewName("/trace/NormalDetailsView");
        }
        result.addObject("request",request);
        return result;
    }

    // TODO: 2017/2/4 转换TraceNode至goNode
    private GoNode convertGoNode(List<TraceNode> traceNodes) {
        GoNode goNode = new GoNode();
        goNode.setCopiesArrayObjects(true);
        goNode.setCopiesArrays(true);
        List<GoLinkData> linkDataArray = new ArrayList<>(traceNodes.size());
        List<GoNodeData> nodeDataArray = new ArrayList<>(traceNodes.size());
        GoLinkData linkData;
        GoNodeData nodeData;
        String parentId;
        String nodeId;

        for (TraceNode node : traceNodes) {
            nodeData = new GoNodeData(node.getRpcId(), "yellowgrad", node.getAppDetail());
            nodeDataArray.add(nodeData);

            // 0 表示为web服务根节点
            if (!node.getRpcId().equals("0")) {
                nodeId = node.getRpcId();
                parentId = nodeId.substring(0, nodeId.lastIndexOf("."));
                linkData = new GoLinkData(parentId, node.getRpcId(), node.getNodeType());
                linkDataArray.add(linkData);
            }


        }
        goNode.setNodeDataArray(nodeDataArray.toArray(new GoNodeData[nodeDataArray.size()]));
        goNode.setLinkDataArray(linkDataArray.toArray(new GoLinkData[linkDataArray.size()]));

        return goNode;
    }

    private D3GraphData convertGoD3GraphData(List<TraceNode> traceNodes) {
        D3GraphData data = new D3GraphData();
        Map<String, Object[]> idMap = new HashMap<>();
        List<D3GraphData.Node> nodeList = new ArrayList<>();
        List<D3GraphData.Relationship> relationshipList = new ArrayList<>();
        String parentId;
        String nodeId;

        for (TraceNode traceNode : traceNodes) {
            idMap.put(traceNode.getRpcId(), new Object[]{idMap.size(), traceNode});
        }

        int relationId = 0;
        int start, end;
        for (TraceNode traceNode : traceNodes) {
            nodeId = traceNode.getRpcId();

            D3GraphData.Node node = new D3GraphData.Node();
            node.setId((Integer) idMap.get(nodeId)[0]);

            node.setLabels(new String[]{traceNode.getNodeType()});
            Map<String, String> properties = new HashMap<>();
            properties.put("应用名称", traceNode.getAppDetail());
            properties.put("服务名称", traceNode.getServicePath());
            properties.put("服务方法", traceNode.getServiceName());
            properties.put("IP地址", traceNode.getAddressIp());
            node.setProperties(properties);
            nodeList.add(node);
            // 不等于0
            if (!nodeId.equals("0")) {
                parentId = nodeId.substring(0, nodeId.lastIndexOf("."));
                D3GraphData.Relationship relationship = new D3GraphData.Relationship();
                start = (Integer) idMap.get(parentId)[0];
                end = (Integer) idMap.get(nodeId)[0];
                relationship.setStartNode(start);
                relationship.setEndNode(end);
                relationship.setId(relationId++);
                relationship.setLinknum(relationId);
                relationship.setSource(start);
                relationship.setTarget(end);
                relationship.setType(traceNode.getNodeType() + "_invoker");
                Map<String, String> relationshipProperties = new HashMap<>();
                if (traceNode.getEndTime() != null && traceNode.getBeginTime() != null) {
                    relationshipProperties.put("耗时", String.valueOf(traceNode.getEndTime() - traceNode.getBeginTime()));
                    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
                    relationshipProperties.put("时间", format.format(new Date(traceNode.getBeginTime())));
                }
                relationship.setProperties(relationshipProperties);
                relationshipList.add(relationship);
            }
        }

        data.setNodes(nodeList.toArray(new D3GraphData.Node[nodeList.size()]));
        data.setRelationships(relationshipList.toArray(new D3GraphData.Relationship[relationshipList.size()]));
        return data;
    }

}
