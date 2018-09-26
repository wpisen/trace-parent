<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>调用链系统</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  </head>
  <body>
      <div class="panel panel-primary" style="margin:1%">
          <div class="panel-heading">
              <h3 class="panel-title">欢迎访问调用链系统！</h3>
          </div>
          <div class="panel-body">
              <p><h4>这是一个调用链跟踪与监控系统，目前能支持页面(Java/PHP/H5/APP)、PHP-API以及Global-API的请求跟踪。例：点击"开启本机调试"后，则本机发起的所有页面请求都将上报到调用链系统监控中心，并最终在监控页面展示。<h4></p>
              <p><h5><i>后续将继续完善日志跟踪这块的功能，欢迎反馈问题和建议。</i><h5></p>
          </div>
      </div>
      <div class="panel panel-default" style="margin:1%">
        <div class="panel-body">
            <div style="margin:16px">
            <a id="startDebug" href="trace/startDebug" class="btn btn-success" role="button">开启本机调试</a>
            <a id="stopDebug" href="trace/stopDebug" class="btn btn-warning" role="button">关闭本机调试</a>
            <a href="trace/openConsole.html" target="_blank" class="btn btn-success" role="button">打开控制台</a>
            <a href="log" target="_blank" class="btn btn-success" role="button">实时日志</a>
            <a href="http://log.bubugao-inc.com/#/dashboard/elasticsearch/java" target="_blank" class="btn btn-success" role="button">日志检索</a>
            </div>
            
            <div class="col-xs-8">
            <textarea class="form-control" id="trackUrl" name="trackUrl" placeholder="请输入Global-API或PHP-API的完整URL" rows="3"></textarea>
            </div>
            <button id="trackUrlButton" type="button" class="btn btn-primary">追踪</button>
            
            <div id="debugInfoDiv" class="panel panel-default" style="margin-top:80px">
                
            </div>
        </div>
      </div>
  </body>
  
<script>
    var DEBUG_ID_NAME = "trace-debug-id";
    var DEBUG_UPLOAD_PATH = "trace.upload.path";
    //获取cookie
    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i=0; i<ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0)==' ') c = c.substring(1);
            if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
        }
        return "";
    }
    function openUrl(urlArray){
        for(var index in urlArray){
            window.open(urlArray[index]);
        }
    }
    $("#trackUrlButton").click(function(){
        $.get("trace/generateDebugId", function(data){
            var trackUrl = $("#trackUrl").val();
            if(trackUrl.indexOf("?") != -1){
                trackUrl = trackUrl + "&trace-debug-id=" + data;
            }else{
                trackUrl = trackUrl + "?trace-debug-id=" + data;
            }
            if(trackUrl.indexOf("http://") == -1){
                trackUrl = "http://" + trackUrl
            }
            window.open(trackUrl);
        })
    })
      
    function reloadDebugInfo(){
        $.get("debugInfo", function(html){
            $("#debugInfoDiv").html(html);
        })
    }
    
    $().ready(
        function(){
            reloadDebugInfo();
            if(getCookie(DEBUG_ID_NAME) != "" && getCookie(DEBUG_UPLOAD_PATH) != ""){
                $("#startDebug").hide();
                $("#stopDebug").show();
            }else{
                $("#stopDebug").hide();
                $("#startDebug").show();
            }
        }
    )
    setInterval(reloadDebugInfo, 10000);
</script>
</html>