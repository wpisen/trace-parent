 <!-- 主体内容 -->
<dl class="text-overflow">
    <#if totals=0>
        <span >什么都没有搜索到...</span>
    <#else >
        <#list requestDatas as requestData>
            <dt><a href="${request.contextPath}/trace/nodeListView?projectKey=${projectKey}&traceId=${requestData.traceId}&rpcId=${requestData.rpcId}" style="font-size: 18px;" target="_blank">
                <span style="font-size: 15px;">${requestData.createTime}</span>
            ${requestData.title}  </a></dt>
            <dd style="margin-bottom: 10px;"><span style="color: #4cae4c">${requestData.clipentIp!""} </span>
            ${requestData.describe!""}
            <#if requestData.errorMessage??>
            <br/>
                <span style="color: red ">${requestData.errorMessage}</span>
            </#if>

            </dd>
        </#list>
    </#if>
</dl>

<!-- 分页组件 -->
<#if pageTotals gt 1>
    <nav>
        <ul class="pagination">
            <!-- 上一页-->
            	<#if pageIndex = 1>
            	<li>
            		<a  href="javascript:void(0);" aria-label="上一页">
                    	<span aria-hidden="true">&laquo;</span>
                	</a>
            	</li>
            	<#else>
                <li><!-- pageIndex 从1开始，pageUrls 从0开始，所以pageIndex减去2才是上一页 -->
            	 	<a name="page_button" href="javascript:void(0);" param="${queryParam}&pageIndex=${pageIndex-1}" aria-label="上一页">
                    	<span aria-hidden="true">&laquo;</span>
                	</a>
           		 </li>
            	</#if>
                  
            
         	 <#list pageBegin..pageEnd as index>
                <#if index=pageIndex>
                    <li class="active">
                 <#else>
                    <li>
                 </#if>
                       <a name="page_button" href="javascript:void(0);" param="${queryParam}&pageIndex=${index}"> ${index} </a>
                    </li>
            </#list>
            <!-- 下一页-->
           <#if pageIndex=pageTotals>
            <li>
                <a href="javascript:void(0);" aria-label="下一页">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <#else>
            <li> <!-- pageIndex 从1开始，pageUrls 从0开始，所以pageIndex即为下一页 -->
                <a name="page_button" href="javascript:void(0);"  param="${queryParam}&pageIndex=${pageIndex+1}" aria-label="下一页">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            </#if>
            
        </ul>
    </nav>
</#if>
    
  <script type="text/javascript">
	   $(function(){
	       $("a[name='page_button']").click(function(){
			       refreshDataByParam($(this).attr("param"));
			});
	   });
  </script>
