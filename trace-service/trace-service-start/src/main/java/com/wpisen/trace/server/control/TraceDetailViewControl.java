package com.wpisen.trace.server.control;

import com.alibaba.druid.sql.SQLUtils;
import com.wpisen.trace.agent.trace.StackNodeVo;
import com.wpisen.trace.agent.trace.TraceNode;
import com.wpisen.trace.server.common.TraceException;
import com.wpisen.trace.server.common.TraceUtils;
import com.wpisen.trace.server.common.ForMatJSONStr;
import com.wpisen.trace.server.common.UtilJson;
import com.wpisen.trace.server.control.entity.JsTreeNode;
import com.wpisen.trace.server.control.entity.TraceNodeVo;
import com.wpisen.trace.server.dao.entity.Project;
import com.wpisen.trace.server.service.ClientService;
import com.wpisen.trace.server.service.NodeQueryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description: 即查看跟踪请求详情 br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/1/18 11:40
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/trace")
public class TraceDetailViewControl {

    static final Logger logger = LoggerFactory.getLogger(TraceDetailViewControl.class);

    @Autowired
    private NodeQueryService searchService;
    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/nodeListView")
    public ModelAndView openTraceListView(HttpServletRequest httpRequest, HttpServletResponse response,
    		String projectKey, String traceId, String rpcId) {
        Assert.notNull("参数 traceId 不能为空", traceId);
        ModelAndView view = new ModelAndView("/trace/traceListView");
        view.addObject("traceId", traceId);
        view.addObject("projectKey", projectKey);
        if (rpcId != null)
            view.addObject("rpcId", rpcId);
        
        view.addObject("request",httpRequest);
        return view;
    }

    @RequestMapping(value = "/traceNodeData.json")
    @ResponseBody()
    public Map<String, Serializable> getTraceNodes(HttpServletRequest request, String projectKey, String traceId) {
        Map<String, Serializable> result = new HashMap<>();
        Project project = clientService.getProject(projectKey);
        List<TraceNode> nodes = searchService.getNodesByTraceId(project.getProId(), traceId);
        //List<TraceNode> listFilters = new ArrayList<>();
        // 节点排序
        Collections.sort(nodes, new Comparator<TraceNode>() {
            @Override
            public int compare(TraceNode o1, TraceNode o2) {
                String rid1 = o1.getRpcId();
                String rid2 = o2.getRpcId();
                return new java.math.BigDecimal(rid1.replace(".", "")).compareTo(new java.math.BigDecimal(rid2.replace(".", "")));
            }
        });

        List<TraceNodeVo> nodeVos = new ArrayList<>(nodes.size());

        for (TraceNode node : nodes.toArray(new TraceNode[nodes.size()])) {
            if ("dubbo-provider".equals(node.getNodeType())) {
                // nodes.remove(node);
                continue;
            }

            TraceNodeVo vo = new TraceNodeVo();
            BeanUtils.copyProperties(node, vo);


            String rpcid = node.getRpcId();
            vo.setInvokerName(node.getAppId() + "-->" + node.getNodeType());
            if (rpcid.lastIndexOf(".") > -1) {
                String parentid = rpcid.substring(0, rpcid.lastIndexOf("."));
                vo.set_parentId(parentid);
            }
            if (node.getBeginTime() != null && node.getEndTime() != null) {
                vo.setUseTime(String.valueOf((node.getEndTime() - node.getBeginTime())));
            } else {
                vo.setUseTime(String.format("data error! end=%s,begin=%s", node.getEndTime(), node.getBeginTime()));
            }
            nodeVos.add(vo);
        }


        result.put("total", nodes.size());
        result.put("rows", nodeVos.toArray());
        return result;
    }

    @RequestMapping(value = "/openDetailView")
    public ModelAndView openDetailView(String projectKey, String traceId, String rpcId, HttpServletRequest request) {
        ModelAndView result = new ModelAndView();
        result.addObject("errorMsg", String.format("数据不存在 traceId=%s,rpcId=%s", traceId, rpcId));

        Project project = clientService.getProject(projectKey);
        TraceNode node = searchService.getNodeById(project.getProId(), traceId, rpcId);
        result.addObject("node", node);
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        result.addObject("invoke_time", format.format(new Date(node.getBeginTime())));

        if (StringUtils.hasText(node.getStackNodes())) {
            List<StackNodeVo> stackNodeVos = UtilJson.parseArray(node.getStackNodes(), StackNodeVo.class);
            List<JsTreeNode> treeNodes = convertJsTreeNodes(traceId, rpcId, stackNodeVos);
            String jsTreeNodesData = UtilJson.toJsonString(treeNodes);
            result.addObject("stackNodesData", jsTreeNodesData);
        }


        if ("Data Base".equals(node.getNodeType())) {
            if (node.getInParam() != null) {
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
            if (node.getOutParam() != null) {
                String[][] resultSet = UtilJson.parse(node.getOutParam(),
                        String[][].class);
                result.addObject("resultSet", resultSet);
            }
            result.setViewName("/trace/SqlDetailsView");
        } else {
            if (node.getInParam() != null) {
                if (node.getInParam().trim().startsWith("{") || node.getInParam().trim().startsWith("["))
                    result.addObject("in_param", ForMatJSONStr.format(node.getInParam()));
                else
                    result.addObject("in_param", node.getInParam());
            }
            if (node.getOutParam() != null) {
                if (node.getOutParam().trim().startsWith("{") || node.getInParam().trim().startsWith("[")){
                	result.addObject("out_param", ForMatJSONStr.format(node.getOutParam()));                	
                }else{
                	if(!StringUtils.isEmpty(node.getOutParam())){
                		result.addObject("out_param", node.getOutParam());
                	}else{
                		result.addObject("out_param", "{}");
                	}
                }
            }
            result.setViewName("/trace/NormalDetailsView");
        }
        // node.getErrorStack();
        result.addObject("request",request);
        return result;
    }

    private List<JsTreeNode> convertJsTreeNodes(String traceId, String nodeId, List<StackNodeVo> stackNodeVos) {
        List<JsTreeNode> result = new ArrayList<>();
        Map<String, JsTreeNode> map = new HashMap<>();
        StackNodeVo rootNode = null;
        for (StackNodeVo stackNodeVo : stackNodeVos) {
            if ("0".equals(stackNodeVo.getId())) {
                rootNode = stackNodeVo;
                break;
            }
        }
        
        // 节点排序
        Collections.sort(stackNodeVos, new Comparator<StackNodeVo>() {
            @Override
            public int compare(StackNodeVo o1, StackNodeVo o2) {
                String rid1 = o1.getId();
                String rid2 = o2.getId();

                return new java.math.BigDecimal(rid1.replace(".", "")).compareTo(new java.math.BigDecimal(rid2.replace(".", "")));
            }
        });

        // 构建所有Tree节点
        for (StackNodeVo stackNodeVo : stackNodeVos) {
            JsTreeNode treeNode = new JsTreeNode();
            // 计算用时占比
            String useTimePercentum = "";

            if (stackNodeVo.getUseTime() != null && rootNode.getUseTime() != null) {
                useTimePercentum = String.valueOf(stackNodeVo.getUseTime()* 100 / rootNode.getUseTime() ) + "%";
            }
            treeNode.setText(stackNodeVo.getClassName() + "." + stackNodeVo.getMethodName() + " " + useTimePercentum);

            treeNode.setId(stackNodeVo.getId());
            treeNode.setCodeLines(Arrays.toString(stackNodeVo.getLines().toArray()));
            // 默认展开2级及以上根节点 如:id=0.1
            if (stackNodeVo.getId().length() <= 3) {
                treeNode.setState(new JsTreeNode.State(false, false, true));
            }

            map.put(treeNode.getId(), treeNode);
            if (stackNodeVo.getId().equals("0")) {
                result.add(treeNode);
            }
        }

        // 构建节点父子关系
        String stackNodeId, parentId;
        for (StackNodeVo stackNodeVo : stackNodeVos) {
            if (!stackNodeVo.getId().equals("0")) {
                stackNodeId = stackNodeVo.getId();
                parentId = stackNodeId.substring(0, stackNodeId.lastIndexOf("."));
                if (!map.containsKey(parentId)) {
                    logger.error("数据异常",
                            new TraceException(TraceUtils.error_data,
                                    String.format("堆栈父节点丢失traceId=%s,stackNodeId=%s,stackNodeId=%s", traceId, nodeId, stackNodeId)));
                } else {
                    map.get(parentId).getChildren().add(map.get(stackNodeId));
                }
            }
        }

        return result;
    }

    private static String countPercentum(Long numerator, Long denominator) {
        numerator=numerator*100;
        BigDecimal numeratorBig = new BigDecimal(numerator);
        BigDecimal denominatorBig = new BigDecimal(denominator);
        return numeratorBig.divide(denominatorBig, 0, RoundingMode.HALF_UP).toString();
    }

    public static void main(String[] args) {
        System.out.println(countPercentum(3l, 7l));
    }
}
