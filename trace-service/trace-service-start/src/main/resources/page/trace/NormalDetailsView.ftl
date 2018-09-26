<!DOCTYPE html>
<html>
<!-- 普通标准详情页 -->
<head>
    <meta charset="UTF-8">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->

    <script src="http://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/jstree/3.3.3/jstree.min.js"></script>
    <script src="${request.contextPath}/static/highlight/highlight.pack.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <script src="http://cdn.bootcss.com/jquery-jsonview/1.2.3/jquery.jsonview.min.js"></script>

    <link rel="stylesheet" href="${request.contextPath}/static/highlight/styles/docco.css">
    <link href="http://cdn.bootcss.com/jstree/3.3.3/themes/default/style.min.css" rel="stylesheet">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!--json 查看视图 -->
    <link href="http://cdn.bootcss.com/jquery-jsonview/1.2.3/jquery.jsonview.min.css" rel="stylesheet">

    <title>详情参数视图</title>
    <style type="text/css">
        body {
            color: #4d4d4d;
            font-size: 14px;
        }

        code {
            background-color: #f5f5f5;
        }

        html, body {
            height: 100%;
        }

        .title {
            height: 6%;
        }
    </style>
</head>
<body>
<h4>基本信息:</h4>
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
<#if in_param ??>
<h4> 输入参数：</h4>
<#--
<pre style="padding:0px" class="json"><code>${in_param?html}</code></pre>
-->
<div class="well well-sm" id="inParamJsonView"></div>

<script>
    var inParamJson=${in_param};
    $(function() {
        $("#inParamJsonView").JSONView(inParamJson, { collapsed: false, nl2br: true, recursive_collapser: true });

        /*$('#collapse-btn').on('click', function() {
            $('#json').JSONView('collapse');
        });

        $('#expand-btn').on('click', function() {
            $('#json').JSONView('expand');
        });

        $('#toggle-btn').on('click', function() {
            $('#json').JSONView('toggle');
        });

        $('#toggle-level1-btn').on('click', function() {
            $('#json').JSONView('toggle', 1);
        });

        $('#toggle-level2-btn').on('click', function() {
            $('#json').JSONView('toggle', 2);
        });*/
    });

</script>


<#else>
<h4> 输入参数：</h4>无
</#if>
<#if out_param ??>
<h4> 返回结果：</h4>
<#--
<pre style="padding:0px" class="json"><code>${out_param?html}</code></pre>
-->
<div class="well well-sm" id="outResultJsonView"></div>

<script>
    var outResultJson=${out_param};
    if(outResultJson != null && outResultJson != ""){
		$(function() {
        	$("#outResultJsonView").JSONView(outResultJson, { collapsed: false, nl2br: true, recursive_collapser: true });
    	});
    }
</script>

<#else>
<h4> 返回结果：</h4>无
</#if>

<#if stackNodesData??>
<h4>代码堆栈</h4>

<div class="btn-group" role="group" aria-label="...">
    <button type="button" class="btn btn-default btn-sm" onclick="openStackTreeNodeAll()">
        <span class="glyphicon glyphicon-resize-full"></span>展开全部
    </button>
    <button type="button" class="btn btn-default btn-sm" onclick="closeStackTreeNodeAll()">
        <span class="glyphicon glyphicon-resize-small"></span>合闭全部
    </button>
</div>
<div class="well well-sm" id="stackJsTree">
</div>
<#--
<pre style="padding:0px"></pre>
-->
<!-- 初始化jsTree-->
<script>

    $('#stackJsTree').on("loaded.jstree", function (e, data) {
        data.instance.open_node("0"); // 展开根节点
    }).on("select_node.jstree", function (node, selected,event) {
        var tree=selected.instance;
        var tNode=selected.node;
        var newText=selected.node.original.text+"  "+selected.node.original.codeLines;
        tree.set_text(tNode,newText);
    }).jstree({
        'core': {
            'themes': {
                "name": false,
                "icons": false, // 默认关闭图标
                "stripes":true // 打开背景条纹
            },
            'animation': false, // 关闭动画
            'data': ${stackNodesData}
        },
        "dnd":false

    });


    function openStackTreeNodeAll(){
        var instance = $('#stackJsTree').jstree(true);
        // 展开全部节点
        instance.open_all("0");
		//instance.refresh(true);
    }
    function closeStackTreeNodeAll(){
        var instance = $('#stackJsTree').jstree(true);
        // 展开全部节点
        instance.close_all("0");
    }

    function showLineNumber() {

    }
    var stackJson=${stackNodesData}
    $('#stackJsTree').JSONView(outResultJson, { collapsed: false, nl2br: true, recursive_collapser: true });

</script>
</#if>

<#if node.errorMessage ??>
<h4> 异常信息：</h4>
<p class="text-danger">${node.errorMessage}</p>
</#if>
<#if node.errorStack ??>
<h4> 异常堆栈：</h4>
<pre style="padding:0px" class="basice"><code>${node.errorStack}</code></pre>
</#if>

</body>
</html>

 
