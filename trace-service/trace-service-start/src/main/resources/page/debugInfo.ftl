<#setting datetime_format="yyyy-MM-dd HH:mm:ss"/>
<button class="btn btn-default" type="button">
                当前处于调试状态的设备： <span class="badge">${deviceInfoList?size}</span>
</button>

<table class="table table-striped">
  <thead>
    <tr>
      <th>#</th>
      <th>调试ID</th>
      <th>机型</th>
      <th>设备编号</th>
      <th>客户端IP</th>
      <th>首次调试时间</th>
      <th>最近调试时间</th>
      <th width='20%'>最近请求路径</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
  <#if deviceInfoList?size != 0>
      <#list deviceInfoList as deviceInfo>
        <tr>
          <th scope="row">${deviceInfo_index + 1}</th>
          <td><a href="trace/openConsole.html?trace-debug-id=yunhoudebug_${deviceInfo.debugId}" target="_blank">${deviceInfo.debugId}</a></td>
          <td>${deviceInfo.phoneModel!'N/A'}</td>
          <td>${deviceInfo.ecode!'N/A'}</td>
          <td>${deviceInfo.firstTraceFrom.clientIp}</td>
          <td>${deviceInfo.firstTraceFrom.createTime?number_to_datetime}</td>
          <td>${deviceInfo.lastTraceFrom.createTime?number_to_datetime}</td>
          <td style="word-break:break-all;">${deviceInfo.lastTraceFrom.accessPath}</td>
          <td>
              <a href="trace/openConsole.html?trace-debug-id=yunhoudebug_${deviceInfo.debugId}" target="_blank"><button type="submit" class="btn btn-primary">追踪</button></a>
              <a href="log?trace-debug-id=yunhoudebug_${deviceInfo.debugId}" target="_blank"><button type="submit" class="btn btn-primary">日志</button></a>
          </td>
        </tr>
      </#list>
  <#else>
      <tr><td colspan="9" align="center">当前没有处于调试状态的设备</td></tr>
  </#if>               
  </tbody>
</table>