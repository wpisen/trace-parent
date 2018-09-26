<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>调用链节点列表</title>
    <script type="text/javascript" src="${request.contextPath}/static/jquery/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${request.contextPath}/static/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${request.contextPath}/static/layer/layer.js"></script>
    <script type="text/javascript" src="${request.contextPath}/static/gojs/go.js"></script>

    <link rel="stylesheet" type="text/css" href="${request.contextPath}/static/jquery/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/static/jquery/themes/default/easyui.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

	<style>
	body{
        font-size: 14px;
        margin: 10px;
    }

    .panel-body {
         padding: 0px;
     }
	</style>

	</head>
	<body>

    <div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
                <ul id="bodyTab" class="nav nav-pills">
                    <li  class="active"><a href="#nodeList" data-toggle="tab">节点列表</a></li>
                    <li><a href="#flowChart" data-toggle="tab">流程图</a></li>
                    <!--<li><a href="#nodeLog" data-toggle="tab">原文日志</a></li>-->
                </ul>
			</div>
		</div>

		<div class="row" style="margin-top: 5px;">
			<div class="col-md-12">


                <div id="tabContent" class="tab-content" >


                    <div class="tab-pane in active " id="nodeList">
                        <table id="trace_node_table"  <#--title="调用链节点"--> class="easyui-treegrid" style="width:100%; padding: 0px;"
                               data-options="
									border:true,
									url: './traceNodeData.json?traceId=${traceId}&projectKey=${projectKey}',
									method: 'get',
									rownumbers: true,
									fit: false,   //自适应大小
									fitColumns:true, //自动使列适应表格宽度以防止出现水平滚动。
									idField: 'rpcId',
									treeField: 'rpcId'
									">
                            <thead>
                            <tr>
                                <th data-options="field:'rpcId'" width="80">序号</th>
                                <!--<th data-options="field:'invokerName'" width="150" align="left">应用名称</th>-->
                                <th data-options="field:'appDetail',formatter:showTitle" width="150" align="left">应用包名</th>
                                <th data-options="field:'nodeType'" width="60" align="center">类型</th>
                                <th data-options="field:'resultState',formatter:formatState" width="60" align="center">状态</th>
                                <th data-options="field:'servicePath',formatter:showTitle" width="350">服务路径</th>
                                <th data-options="field:'serviceName',formatter:showTitle" width="100">服务/方法</th>
                                <th data-options="field:'useTime'" width="50" align="center">用时</th>
                            </tr>
                            </thead>
                        </table>
                    </div>

                    <div class="tab-pane  " id="flowChart">
                        <div id="myDiagramDiv" style="border: solid 1px black;height: 800px;width: 100%; padding-top:51px;"></div>
                    </div>

                    <div class="tab-pane " id="nodeLog">
                        <p>原文日志</p>
                    </div>

                </div>
			</div>
		</div>
	</div>


<script>
var isLoadFlowChart=false;
var isLoadNodeList=false;
    $(function(){
        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            // 获取已激活的标签页的名称
            var activeTab = $(e.target).text();
//            // 获取前一个激活的标签页的名称
//            var previousTab = $(e.relatedTarget).text();
			if("流程图"==activeTab&&!isLoadFlowChart){
                //layout();
                load();
                isLoadFlowChart=true;
			}
            if("节点列表"==activeTab&&!isLoadNodeList){
                $('#trace_node_table').treegrid('reload');
                isLoadNodeList=true;
            }
            if("原文日志"==activeTab){

            }

        });
    });


</script>

<#--节点列表初始js-->
<script type="text/javascript">

	$(function(){
		$('#trace_node_table').treegrid({
		   onLoadSuccess:function(data){
			    //setHeight();
			    <#if rpcId??&& rpcId!="0">
			    	openDetails('${traceId}','${rpcId}');
			    </#if>
               isLoadNodeList=true;
			},
			onClickRow:function(row){
		    	if(row=='null') return;
		    	openDetails(row.traceId,row.rpcId);
		    }
		});
	});

	$("body").click(function(e){
        e = window.event || e;
        obj = $(e.srcElement || e.target);
        if(obj.parents(".datagrid-row").size() == 0 && obj.parents('.layui-layer').size() == 0){
            //$('#layui-layer').hide();
           // layer.closeAll();
        }
    })

	function setHeight(){
		var c = $('#cc');
		var p = c.layout('panel','center');	// get the center panel
		var oldHeight = p.panel('panel').outerHeight();
		p.panel('resize', {height:'auto'});
		var newHeight = p.panel('panel').outerHeight();
		newHeight= c.height() + newHeight - oldHeight + 50;
		if(newHeight < 750){
		  newHeight = 750;
		}
		c.layout('resize',{
			height: (newHeight)
		});
	}

	function showTitle(val,row){
		if(val==null) return;
		return "<span title='" + val.replace(/</g, "&lt;").replace(/>/g, "&gt;") + "' class='easyui-tooltip'>" + val.replace(/</g, "&lt;").replace(/>/g, "&gt;") + "</span>";
	}
	function formatDetails(val,row){
		if(val==null) return;
	    var h="./openLogView.html?traceId="+row.traceId+"&rpcId="+row.rpcId;
		return "<a href='"+h+"' target='_blank'>查看</a>";
	}

	function formatState(val,row){
		if(val==null) return;
		if(val=="fail"){
		   var t="异常信息："+row.errorMessage;
		   var h="./openLogView.html?traceId="+row.traceId+"&rpcId="+row.rpcId;
		   return "<a href='"+h+"' title='"+t.replace(/'/g, "")+"' style='color:red'  class='easyui-tooltip'>"+val+"</a>";
		}
		return val;
	}

	var detailViewIndex=-1;

	function openDetails(traceId,rpcId){
		var href="./openDetailView?projectKey=${projectKey}&traceId="+traceId+"&rpcId="+rpcId;

			if(detailViewIndex!=-1){
				layer.iframeSrc(detailViewIndex, href);
			}else{
				detailViewIndex=layer.open({
					  id:'detailView',
					  type: 2,
					  title: '节点详情',
					  shadeClose: true,
					  offset: 'rb',
					  shift :5,
					  shade: 0,
					  scrollbar:true,
					  area: ['30%', '100%'],
					  content: href ,
					  maxmin: true,
					  move: false,
					  shadeClose:true,
					  end:function(){
					  	detailViewIndex=-1;
					  }
				});
			}
	}
</script>

<#--流程图表js-->
<script type="text/javascript">
        init();
        function init() {
            if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
            var $go = go.GraphObject.make;  // for conciseness in defining templates

            var yellowgrad = $go(go.Brush, "Linear", {0: "rgb(254, 201, 0)", 1: "rgb(254, 162, 0)"});
            var greengrad = $go(go.Brush, "Linear", {0: "#98FB98", 1: "#9ACD32"});
            var bluegrad = $go(go.Brush, "Linear", {0: "#B0E0E6", 1: "#87CEEB"});
            var redgrad = $go(go.Brush, "Linear", {0: "#C45245", 1: "#871E1B"});
            var whitegrad = $go(go.Brush, "Linear", {0: "#F0F8FF", 1: "#E6E6FA"});

            var bigfont = "bold 13pt Helvetica, Arial, sans-serif";
            var smallfont = "bold 11pt Helvetica, Arial, sans-serif";

            // Common text styling
            function textStyle() {
                return {
                    margin: 6,
                    wrap: go.TextBlock.WrapFit,
                    textAlign: "center",
                    editable: true,
                    font: bigfont
                }
            }

            myDiagram =
                    $go(go.Diagram, "myDiagramDiv",
                            {
                                // have mouse wheel events zoom in and out instead of scroll up and down
                                "toolManager.mouseWheelBehavior": go.ToolManager.WheelZoom,
                                allowDrop: true,  // support drag-and-drop from the Palette
                                initialAutoScale: go.Diagram.Uniform,
                                "linkingTool.direction": go.LinkingTool.ForwardsOnly,
                                initialContentAlignment: go.Spot.Center,
                                layout: $go(go.LayeredDigraphLayout, {
                                    isInitial: false,
                                    isOngoing: false,
                                    layerSpacing: 50
                                }),
                                "undoManager.isEnabled": true
                            });

            // when the document is modified, add a "*" to the title and enable the "Save" button
            // 添加修改事件
            myDiagram.addDiagramListener("Modified", function (e) {
                /*var button = document.getElementById("SaveButton");
                if (button) button.disabled = !myDiagram.isModified;
                var idx = document.title.indexOf("*");
                if (myDiagram.isModified) {
                    if (idx < 0) document.title += "*";
                } else {
                    if (idx >= 0) document.title = document.title.substr(0, idx);
                }*/
            });
            // 添加点击事件
            myDiagram.addDiagramListener("ObjectSingleClicked", function (e) {
                var part = e.subject.part;
                if (!(part instanceof go.Link)) {
                    //alert("Clicked on " + part.data.key);
                    showDetails(part.data.key);
                }
            });

            var defaultAdornment =
                    $go(go.Adornment, "Spot",
                            $go(go.Panel, "Auto",
                                    $go(go.Shape, {fill: null, stroke: "dodgerblue", strokeWidth: 4}),
                                    $go(go.Placeholder)),
                            // the button to create a "next" node, at the top-right corner
                            $go("Button",
                                    {
                                        alignment: go.Spot.TopRight,
                                        click: addNodeAndLink
                                    },  // this function is defined below
                                    new go.Binding("visible", "", function (a) {
                                        return !a.diagram.isReadOnly;
                                    }).ofObject(),
                                    $go(go.Shape, "PlusLine", {desiredSize: new go.Size(6, 6)})
                            )
                    );

            // define the Node template
            myDiagram.nodeTemplate =
                    $go(go.Node, "Auto",
                            {selectionAdornmentTemplate: defaultAdornment},
                            new go.Binding("location", "loc", go.Point.parse).makeTwoWay(go.Point.stringify),
                            // define the node's outer shape, which will surround the TextBlock
                            $go(go.Shape, "Rectangle",
                                    {
                                        fill: yellowgrad, stroke: "black",
                                        portId: "", fromLinkable: true, toLinkable: true, cursor: "pointer",
                                        toEndSegmentLength: 50, fromEndSegmentLength: 40
                                    }),
                            $go(go.TextBlock, "Page",
                                    {
                                        margin: 6,
                                        font: bigfont,
                                        editable: true
                                    },
                                    new go.Binding("text", "text").makeTwoWay()));

            myDiagram.nodeTemplateMap.add("Source",
                    $go(go.Node, "Auto",
                            new go.Binding("location", "loc", go.Point.parse).makeTwoWay(go.Point.stringify),
                            $go(go.Shape, "RoundedRectangle",
                                    {
                                        fill: bluegrad,
                                        portId: "", fromLinkable: true, cursor: "pointer", fromEndSegmentLength: 40
                                    }),
                            $go(go.TextBlock, "Source", textStyle(),
                                    new go.Binding("text", "text").makeTwoWay())
                    ));

            myDiagram.nodeTemplateMap.add("DesiredEvent",
                    $go(go.Node, "Auto",
                            new go.Binding("location", "loc", go.Point.parse).makeTwoWay(go.Point.stringify),
                            $go(go.Shape, "RoundedRectangle",
                                    {fill: greengrad, portId: "", toLinkable: true, toEndSegmentLength: 50}),
                            $go(go.TextBlock, "Success!", textStyle(),
                                    new go.Binding("text", "text").makeTwoWay())
                    ));

            // Undesired events have a special adornment that allows adding additional "reasons"
            var UndesiredEventAdornment =
                    $go(go.Adornment, "Spot",
                            $go(go.Panel, "Auto",
                                    $go(go.Shape, {fill: null, stroke: "dodgerblue", strokeWidth: 4}),
                                    $go(go.Placeholder)),
                            // the button to create a "next" node, at the top-right corner
                            $go("Button",
                                    {
                                        alignment: go.Spot.BottomRight,
                                        click: addReason
                                    },  // this function is defined below
                                    new go.Binding("visible", "", function (a) {
                                        return !a.diagram.isReadOnly;
                                    }).ofObject(),
                                    $go(go.Shape, "TriangleDown", {desiredSize: new go.Size(10, 10)})
                            )
                    );

            var reasonTemplate = $go(go.Panel, "Horizontal",
                    $go(go.TextBlock, "Reason",
                            {
                                margin: new go.Margin(4, 0, 0, 0),
                                maxSize: new go.Size(200, NaN),
                                wrap: go.TextBlock.WrapFit,
                                stroke: "whitesmoke",
                                editable: true,
                                font: smallfont
                            },
                            new go.Binding("text", "text").makeTwoWay())
            );


            myDiagram.nodeTemplateMap.add("UndesiredEvent",
                    $go(go.Node, "Auto",
                            new go.Binding("location", "loc", go.Point.parse).makeTwoWay(go.Point.stringify),
                            {selectionAdornmentTemplate: UndesiredEventAdornment},
                            $go(go.Shape, "RoundedRectangle",
                                    {fill: redgrad, portId: "", toLinkable: true, toEndSegmentLength: 50}),
                            $go(go.Panel, "Vertical", {defaultAlignment: go.Spot.TopLeft},

                                    $go(go.TextBlock, "Drop", textStyle(),
                                            {
                                                stroke: "whitesmoke",
                                                minSize: new go.Size(80, NaN)
                                            },
                                            new go.Binding("text", "text").makeTwoWay()),

                                    $go(go.Panel, "Vertical",
                                            {
                                                defaultAlignment: go.Spot.TopLeft,
                                                itemTemplate: reasonTemplate
                                            },
                                            new go.Binding("itemArray", "reasonsList").makeTwoWay()
                                    )
                            )
                    ));

            myDiagram.nodeTemplateMap.add("Comment",
                    $go(go.Node, "Auto",
                            new go.Binding("location", "loc", go.Point.parse).makeTwoWay(go.Point.stringify),
                            $go(go.Shape, "Rectangle",
                                    {portId: "", fill: whitegrad, fromLinkable: true}),
                            $go(go.TextBlock, "A comment",
                                    {
                                        margin: 9,
                                        maxSize: new go.Size(200, NaN),
                                        wrap: go.TextBlock.WrapFit,
                                        editable: true,
                                        font: smallfont
                                    },
                                    new go.Binding("text", "text").makeTwoWay())
                            // no ports, because no links are allowed to connect with a comment
                    ));

            // clicking the button on an UndesiredEvent node inserts a new text object into the panel
            function addReason(e, obj) {
                var adorn = obj.part;
                if (adorn === null) return;
                e.handled = true;
                var arr = adorn.adornedPart.data.reasonsList;
                myDiagram.startTransaction("add reason");
                myDiagram.model.addArrayItem(arr, {});
                myDiagram.commitTransaction("add reason");
            }

            // clicking the button of a default node inserts a new node to the right of the selected node,
            // and adds a link to that new node
            function addNodeAndLink(e, obj) {
                var adorn = obj.part;
                if (adorn === null) return;
                e.handled = true;
                var diagram = adorn.diagram;
                diagram.startTransaction("Add State");
                // get the node data for which the user clicked the button
                var fromNode = adorn.adornedPart;
                var fromData = fromNode.data;
                // create a new "State" data object, positioned off to the right of the adorned Node
                var toData = {text: "new"};
                var p = fromNode.location;
                toData.loc = p.x + 200 + " " + p.y;  // the "loc" property is a string, not a Point object
                // add the new node data to the model
                var model = diagram.model;
                model.addNodeData(toData);
                // create a link data from the old node data to the new node data
                var linkdata = {};
                linkdata[model.linkFromKeyProperty] = model.getKeyForNodeData(fromData);
                linkdata[model.linkToKeyProperty] = model.getKeyForNodeData(toData);
                // and add the link data to the model
                model.addLinkData(linkdata);
                // select the new Node
                var newnode = diagram.findNodeForData(toData);
                diagram.select(newnode);
                diagram.commitTransaction("Add State");
            }

            // replace the default Link template in the linkTemplateMap
            myDiagram.linkTemplate =
                    $go(go.Link,  // the whole link panel
                            new go.Binding("points").makeTwoWay(),
                            {curve: go.Link.Bezier, toShortLength: 15},
                            new go.Binding("curviness", "curviness"),
                            $go(go.Shape,  // the link shape
                                    {stroke: "#2F4F4F", strokeWidth: 2.5}),
                            $go(go.Shape,  // the arrowhead
                                    {toArrow: "kite", fill: "#2F4F4F", stroke: null, scale: 2})
                    );

            myDiagram.linkTemplateMap.add("Comment",
                    $go(go.Link, {selectable: false},
                            $go(go.Shape, {strokeWidth: 2, stroke: "darkgreen"})));


            // read in the JSON-format data from the "mySavedModel" element
           // load();
            //layout();
        }

        function layout() {
            myDiagram.layoutDiagram(true);
        }
        function load() {

            /*myDiagram.model = $.ajax({
                url: "some.php"
            }).responseText;*/

            $.ajax({url:"/trace/getFlowNodeData?traceId=${traceId}",success:function(result,status,xhr){
                myDiagram.model=go.Model.fromJson(xhr.responseText);
                myDiagram.layoutDiagram(true);
            }});


            /* myDiagram.model = go.Model.fromJson(document.getElementById("mySavedModel").value);
             myDiagram.layoutDiagram(true);*/
        }
        /**
         * 展示节点详情
         * @param key
         */
        function showDetails(key) {

            // 加载详情展示区内容
           // $("#bottom_details").load("/trace/getDetailInfo?traceId=${traceId}&nodeId="+key);

            openDetails('${traceId}',key);
        }

    </script>

	</body>
</html>

 
