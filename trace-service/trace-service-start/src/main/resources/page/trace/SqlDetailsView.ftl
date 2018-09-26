<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
     <script type="text/javascript" src="${request.contextPath}/static/jquery/jquery.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<!-- 语法高亮 -->
    <link rel="stylesheet" href="${request.contextPath}/static/highlight/styles/docco.css">
    <script src="${request.contextPath}/static/highlight/highlight.pack.js"></script> 
    <script>hljs.initHighlightingOnLoad();</script>
 	<script type="text/javascript" src="${request.contextPath}/static/layer/layer.js"></script>
 	
 	<title>Sql 参数视图</title>
	 <style type="text/css">
		    body {
		      color: #4d4d4d;
		      <!-- overflow: hidden; -->
		    }
		    		
		    code {
		      background-color: #f5f5f5;
		    }
		    
		   <!--#sqlView{
		   	height: 92%;
		   } -->
		    html, body{
		      height: 100%;
		    }
		    .title {
		    	height: 6%;
		    }
	  </style>
	</head>
	
<body >
	
<h4>基本信息</h4>	
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
        <td> 服务方法</td> <td>${node.serviceName}</td>
    </tr>
    <tr>
        <td> 状 态</td>
        <#if node.resultState=="success">
        	<td class="text-success">${node.resultState}</td>
        <#elseif node.resultState=="fail">
         <td class="text-danger">${node.resultState}</td>
        <#else>
         <td >${node.resultState}</td>
        </#if>
    </tr>
    <tr>
        <td> 执行时间</td><td>${invoke_time}</td>
    </tr>
    <tr>
        <td> 来源IP</td><td>${node.fromIp!}</td>
    </tr>
    <tr>
        <td> 目标IP</td><td>${node.addressIp!}</td>
    </tr>
    <tr>
        <td> 应用信息</td><td>${node.appDetail!}</td>
    </tr>
    <tr>
        <td> 跟踪ID</td><td>${node.traceId}</td>
    </tr> 
    </tbody> 
</table>

<h4>SQl语句 <small>(参数已拼装于'?'之上)</small></h4>
	<pre class="sql" style="padding:0px" ><code>${sql_text}</code></pre>

<#if node.errorMessage ??>
<h4>异常信息</h4>
  <p class="text-danger">${node.errorMessage}</p>		
</#if>

<#if node.errorStack ??>
<h4>异常堆栈</h4>
	<pre class="basice" style="padding:0px"><code>${node.errorStack}</code></pre>
</#if>

<!-- 返回结果：resultSet 为字符串二维数组-->
<#if resultSet ??>
<h4>返回结果</h4>
	<table class="table table-bordered table-condensed table-responsive" >
		<#list resultSet as data >
			<tr>
			<#list data as row>
				<#if data_index == 0>
					<th style="width:10%;white-space:nowrap; overflow:hidden;">${row!''}</th>
					<#else>
					<#if row?? && row?length gt 20>
						<td style="white-space:nowrap; ">
						 	${(row?substring(0,20))?html}
							<input id="${data_index?c + row_index?c}" type="hidden" value="${row?html}" ></input>
							<button type="button" title="查看详情" class="btn btn-info btn-xs" onClick='openDetails(${data_index?c + row_index?c});'>查看</button>
						</td>
					<#else>
						<td style="white-space:nowrap; ">${row!''}</td>
					</#if>
					
					
				</#if>
			</#list>
			</tr>
		</#list>
	</table>
</#if>
</body>

<script>
	function openDetails(text){
		layer.alert(htmlEncode($("#"+text).val()));
	}
	
		//Html编码获取Html转义实体
	function htmlEncode(value){
	  return $('<div/>').text(value).html();
	}
	//Html解码获取Html实体
	function htmlDecode(value){
	  return $('<div/>').html(value).text();
	}
	
</script>
</html>

 
