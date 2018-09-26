<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>内部调用 - 调用链控制台</title>
	<link href="${request.contextPath}/static/jsoneditor/dist/jsoneditor.min.css" rel="stylesheet" type="text/css">
	<script src="${request.contextPath}/static/jsoneditor/dist/jsoneditor.min.js"></script>
	 <style type="text/css">
		    body {
		      color: #4d4d4d;
		      overflow: hidden;
		    }
		    		
		    code {
		      background-color: #f5f5f5;
		    }
		    
		   #jsoneditor{
		   	height: 92%;
		   }
		    html, body{
		      height: 100%;
		    }
		    .title {
		    	height: 6%;
		    }
	  </style>
	</head>
	<body>
		<div class="title">调用链日志</div>
		<div id="jsoneditor">
		
		</div>
		<script>
			  var container = document.getElementById('jsoneditor');
			  var options = {
			    mode: 'view',
			    search:'false',
			    modes: [ 'view','code'], // allowed modes
			    onError: function (err) {
			      alert(err.toString());
			    },
			    onModeChange: function (newMode, oldMode) {
			      console.log('Mode switched from', oldMode, 'to', newMode);
			    }
			  };
			
			  var editor = new JSONEditor(container, options, ${logs_json});
			  editor.expandAll();
		</script>
		</div>
	</body>
</html>

 
