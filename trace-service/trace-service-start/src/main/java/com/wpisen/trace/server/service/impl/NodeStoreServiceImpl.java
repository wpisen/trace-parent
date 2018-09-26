package com.wpisen.trace.server.service.impl;

import com.wpisen.trace.agent.trace.TraceNode;
import com.wpisen.trace.server.common.TraceUtils;
import com.wpisen.trace.server.common.UtilJson;
import com.wpisen.trace.server.service.NodeStoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Description: TODO 一句话描述类是干什么的<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/1/18 16:18
 * @since JDK 1.7
 */
@SuppressWarnings("deprecation")
@Service
public class NodeStoreServiceImpl implements NodeStoreService {
    @Autowired
    private ElasticsearchTemplate template;


    @Override
    public void saveTraceNode(Integer proId, String[] nodeJsons) {
        Assert.notNull(proId);
        Assert.notNull(nodeJsons);
        Assert.notEmpty(nodeJsons);
        for (String nodeJson : nodeJsons) {
            TraceNode node = UtilJson.parse(nodeJson, TraceNode.class);
            storeToEs(proId, node, UtilJson.writeValueAsString(node));
            //storeToEs(proId, node,nodeJson);
        }
    }

    private void storeToEs(Integer proId, TraceNode node, String nodeJson) {
        if (node.getNodeType().equals("dubbo-provider"))
            return;
        IndexQuery query = new IndexQueryBuilder().
                withIndexName(TraceUtils.es_index_prefix + proId)
                .withType("TraceNode")
                .withId(node.getTraceId() + "_" + node.getRpcId())
                .withSource(nodeJson)
                .build();
        template.index(query);
    }
}
