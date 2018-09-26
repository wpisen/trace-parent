<link rel="stylesheet" href="${request.contextPath}/static/highlight/styles/docco.css">
<script src="${request.contextPath}/static/highlight/highlight.pack.js"></script>
<script>hljs.initHighlightingOnLoad();</script>

<div id="myScrollspy" class="col-xs-3" >
    <ul class="nav nav-tabs nav-stacked" id="myNav" data-spy="affix" data-offset-top="125">
        <li class="active"><a href="#section-1">返回顶部</a></li>
        <li><a href="#section-2">基本信息</a></li>
        <li><a href="#section-3">输入参数</a></li>
        <li><a href="#section-4">返回结果</a></li>
        <li><a href="#section-5">代码堆栈</a></li>
        <li><a href="#section-6">数据库信息</a></li>
        <li><a href="#section-7">异常信息</a></li>
    </ul>
</div>

<div id="nodeDetailsContent" class="col-md-9" >
<h4 id="section-2">基本信息:</h4>
<table class="table table-bordered table-condensed">
    <tbody style="word-break: break-all;">
    <tr>
        <td style="width:80px;"> 类别</td>
        <td> ${node.nodeType}</td>
    </tr>
    <tr>
        <td> 服务路径</td>
        <td>${node.servicePath}</td>
    </tr>
    <tr>
        <td> 服务方法</td>
        <td>${node.serviceName}</td>
    </tr>
    <tr>
        <td> 状 态</td>
    <#if node.resultState=="success">
        <td class="text-success">成功</td>
    <#elseif node.resultState=="fail">
        <td class="text-danger">失败</td>
    <#else>
        <td>${node.resultState}</td>
    </#if>
    </tr>
    <tr>
        <td> 执行时间</td>
        <td>${invoke_time}</td>
    </tr>
    <tr>
        <td> 来源IP</td>
        <td>${node.fromIp!}</td>
    </tr>
    <tr>
        <td> 目标IP</td>
        <td>${node.addressIp!}</td>
    </tr>
    <tr>
        <td> 应用信息</td>
        <td>${node.appDetail!}</td>
    </tr>
    <tr>
        <td> 跟踪ID</td>
        <td>${node.traceId}</td>
    </tr>
    </tbody>
</table>
<hr/>

<#if in_param ??>
<h4 id="section-3"> 输入参数：</h4>
<pre style="padding:0px" class="json"><code>${in_param?html}</code></pre>
<#else>
<h4 id="section-3"> 输入参数：</h4>无
</#if>
<hr/>

<#if out_param ??>
<h4 id="section-4"> 返回结果：</h4>
<pre style="padding:0px" class="json"><code>${out_param?html}</code></pre>
<#else>
<h4 id="section-4"> 返回结果：</h4>无
</#if>
<hr/>

<!-- 代码堆栈-->

<!-- 数据库信息-->


<#if node.errorMessage ??>
<h4 id="section-5"> 异常信息：</h4>
<p class="text-danger">${node.errorMessage}</p>
<!-- 异常堆栈-->
    <#if node.errorStack ??>
    <pre style="padding:0px" class="basice"><code>${node.errorStack}</code></pre>
    </#if>
<hr/>
</#if>

</div>