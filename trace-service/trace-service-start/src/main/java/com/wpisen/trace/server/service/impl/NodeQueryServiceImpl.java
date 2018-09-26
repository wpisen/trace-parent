package com.wpisen.trace.server.service.impl;

import com.wpisen.trace.agent.trace.TraceNode;
import com.wpisen.trace.server.common.TraceUtils;
import com.wpisen.trace.server.service.NodeQueryService;
import com.wpisen.trace.server.service.entity.PageList;
import com.wpisen.trace.server.service.entity.SearchRequestParam;

import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: TODO 一句话描述类是干什么的<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2017/1/19 15:36
 * @since JDK 1.7
 */
@SuppressWarnings("deprecation")
@Service
public class NodeQueryServiceImpl implements NodeQueryService {

    private static final String[] nodeBaseFiles = {"traceId", "rpcId", "appId", "appDetail", "nodeType", "resultState", "resultSize",
            "servicePath", "serviceName", "beginTime", "endTime", "addressIp", "fromIp", "inParam", "errorMessage"};

    @Autowired
    ElasticsearchTemplate esTemplate;

    /**
     * 根据复合参数搜索节点信息
     *
     * @param proId 项目ID
     * @param param 复合参数
     * @return 分页返回结果
     */
	@Override
    public PageList<TraceNode> searchNodePage(Integer proId, SearchRequestParam param) {
        Assert.notNull(param.getTimeBegin());
        Assert.notNull(param.getPageIndex());
        Assert.notNull(param.getPageSize());

        AndFilterBuilder filterBuilder = FilterBuilders.andFilter();
        // 创建时间过滤
        RangeFilterBuilder timeFilte = FilterBuilders.rangeFilter("beginTime").from(param.getTimeBegin());
        if (param.getTimeEnd() != null) {
            timeFilte.to(param.getTimeEnd());
        }
        filterBuilder.add(timeFilte);
        // ip过滤 TODO 后期考虑通过traceId 中付加ip属性
        if (StringUtils.hasText(param.getClientIp())) {
            filterBuilder.add(FilterBuilders.termFilter("fromIp", param.getClientIp()));
        }
        if (StringUtils.hasText(param.getAddressIp())) {
            filterBuilder.add(FilterBuilders.termFilter("addressIp", param.getAddressIp()));
        }
        // 类别过滤
        if (StringUtils.hasText(param.getNodeType())) {
            filterBuilder.add(FilterBuilders.termFilter("nodeType", param.getNodeType()));
        }
        QueryBuilder queryBuilder = null;
        if (StringUtils.hasText(param.getQueryWord())) {
            queryBuilder = QueryBuilders.queryStringQuery(param.getQueryWord());
        }
        SearchQuery query = new NativeSearchQuery(queryBuilder, filterBuilder);
        query.addIndices(TraceUtils.es_index_prefix + proId);
        query.addTypes("TraceNode");
        query.setPageable(new PageRequest(param.getPageIndex() - 1, param.getPageSize()));
        query.addSort(new Sort(Sort.Direction.DESC, "beginTime"));
        query.addFields(nodeBaseFiles);
        FacetedPage<TraceNode> facetedPage = esTemplate.queryForPage(query, TraceNode.class);
        PageList<TraceNode> result = new PageList<>();
        result.setPageIndex(facetedPage.getNumber() + 1);
        result.setPageSize(facetedPage.getSize());
        result.setTotalElements(facetedPage.getTotalElements());
        result.setTotalPage(facetedPage.getTotalPages());
        result.setElements(facetedPage.getContent());
        return result;
    }

    /**
     * 返回指定Trace ID所有的节点
     *
     * @param proId
     * @param traceId
     * @return
     */
    @Override
    public List<TraceNode> getNodesByTraceId(Integer proId, String traceId) {
        Assert.notNull(traceId);
        FilterBuilder filterBuilder = FilterBuilders.termFilter("traceId", traceId);
        SearchQuery query = new NativeSearchQuery(null, filterBuilder);
        query.addFields(nodeBaseFiles);
        query.addIndices(TraceUtils.es_index_prefix + proId);
        query.addTypes("TraceNode");
        query.setPageable(new PageRequest(0, 1000));
        List<TraceNode> list = esTemplate.queryForList(query, TraceNode.class);
        ArrayList<TraceNode> result = new ArrayList<>(list.size());
        result.addAll(list);
        return result;
    }

    /**
     * 获取指定跟踪节点信息
     *
     * @param proId   项目ID
     * @param traceId 跟踪ID
     * @param nodeId  节点ID
     * @return 跟踪节点
     */
    @Override
    public TraceNode getNodeById(Integer proId, String traceId, String nodeId) {
        Assert.notNull(traceId);
        Assert.notNull(nodeId);

        SearchQuery query = new NativeSearchQuery(QueryBuilders.idsQuery().ids(traceId + "_" + nodeId));
        query.addIndices(TraceUtils.es_index_prefix + proId);
        query.addTypes("TraceNode");

        List<TraceNode> list = esTemplate.queryForList(query, TraceNode.class);
        if (list.isEmpty()) {
            throw new IllegalArgumentException(String.format("traceNode not found traceID=%s,rcpId=%s", traceId, nodeId));
        } else if (list.size() > 1) {
            throw new IllegalArgumentException(String.format("result  Too much 1 traceID=%s,rcpId=%s", traceId, nodeId));
        }
        return list.get(0);
    }
}
