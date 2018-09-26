<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <title>实时日志</title>
        <style>
        body{font-family: monospace;white-space:pre} 
        </style>
        <script type="text/javascript" src="${request.contextPath}/static/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="${request.contextPath}/static/sockjs/sockjs-0.3.4.min.js"></script>
        <script>
            var websocket;
            var websocketUrl = "ws://localhost:9090/yunhou-hawkeye-web/webSocketServer?trace-debug-id=${page_debugId}";
            var sockJsUrl = "${request.contextPath}/static/sockjs/webSocketServer?trace-debug-id=${page_debugId}";
            if ('WebSocket' in window) {
                try{
                    websocket = new WebSocket(websocketUrl);
                }catch(e){
                    websocket = new SockJS(sockJsUrl);
                }
            } else if ('MozWebSocket' in window) {
                websocket = new MozWebSocket(websocketUrl);
            } else {
                websocket = new SockJS(sockJsUrl);
            }
            websocket.onmessage = function (e) {
                var $new_data = $("<div>" + e.data + "</div>").hide();
                $("#realtimeLogDiv").append($new_data);
                $new_data.show(200, function(){
                    if($("#realtimeLogDiv").height() - $(window).height() > 0){
                        $("body").scrollTop($(document).height());
                    }
                });
            };
            websocket.onerror = function (e) {
                console.error(e);
            };
            websocket.onclose = function (e) {
                console.debug(e);
            }

        </script>
    </head>
    <body>
        <div id="realtimeLogDiv" style="margin-top: -4%;">
        
        </div>
    </body>
</html>

 
