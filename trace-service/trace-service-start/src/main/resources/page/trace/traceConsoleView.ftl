<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>内部调用 - 调用链控制台</title>

    <script type="text/javascript" src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script type="text/javascript" src="//cdn.bootcss.com/layer/3.0.1/layer.js"></script>
    <script type="text/javascript" src="${request.contextPath}/static/gojs/go.js"></script>
    <style>
        body {
            font-size: 14px;
        }

        /* Custom Styles */
        ul.nav-tabs{
            width: 140px;
            margin-top: 20px;
            border-radius: 4px;
            border: 1px solid #ddd;
            box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
        }
        ul.nav-tabs li{
            margin: 0;
            border-top: 1px solid #ddd;
        }
        ul.nav-tabs li:first-child{
            border-top: none;
        }
        ul.nav-tabs li a{
            margin: 0;
            padding: 8px 16px;
            border-radius: 0;
        }
        ul.nav-tabs li.active a, ul.nav-tabs li.active a:hover{
            color: #fff;
            background: #0088cc;
            border: 1px solid #0088cc;
        }
        ul.nav-tabs li:first-child a{
            border-radius: 4px 4px 0 0;
        }
        ul.nav-tabs li:last-child a{
            border-radius: 0 0 4px 4px;
        }
        ul.nav-tabs.affix{
            top: 30px; /* Set the top position of pinned element */
        }

    </style>



</head>
<body data-spy="scroll" data-target="#myScrollspy">
<h3>调用跟踪控制台</h3>

<div class="container-fluid">
   <!-- 顶部流程图组件-->

    <div class="row" style="width: 100%">
        <div class="col-md-12">
            <h2 id="section-1">调用流程图</h2>
            <div id="myDiagramDiv" style="border: solid 1px black; height: 500px;width: 100%"></div>
        </div>
    </div>

 <!-- 底部基本信息 -->
    <div id="bottom_details" class="row">
        <div class="col-xs-3" id="myScrollspy">
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

        </div>
    </div>


</div>


<script>
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
        load();
        layout();
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
        $("#bottom_details").load("/trace/getDetailInfo?traceId=${traceId}&nodeId="+key);
    }

</script>

</body>
</html>

 
