package com.wpisen.trace.server.service.entity;

/**
 * Description: 请求地址查询<br/>
 *
 * @author wpisen@wpisen.com
 * @version 1.0
 * @date: 2016年8月4日 下午4:52:51
 * @since JDK 1.7
 */
public class SearchRequestParam {

        private String clientIp; // 客户端IP
        private String addressIp; // 目标IP 
        private String queryWord; // 请求关键字
        private Long timeBegin; // 开始时间
        private Long timeEnd; // 截止时间
        private Integer pageSize; // 每页大小
        private Integer pageIndex; // 页码
        private String nodeType; // 节点类型
        private String servicePath;// 服务路径

        public String getClientIp() {
                return clientIp;
        }

        public void setClientIp(String clientIp) {
                this.clientIp = clientIp;
        }

        public String getQueryWord() {
                return queryWord;
        }

        public void setQueryWord(String queryWord) {
                this.queryWord = queryWord;
        }

        public Long getTimeBegin() {
                return timeBegin;
        }

        public void setTimeBegin(Long timeBegin) {
                this.timeBegin = timeBegin;
        }

        public Long getTimeEnd() {
                return timeEnd;
        }

        public void setTimeEnd(Long timeEnd) {
                this.timeEnd = timeEnd;
        }

        public Integer getPageSize() {
                return pageSize;
        }

        public void setPageSize(Integer pageSize) {
                this.pageSize = pageSize;
        }

        public Integer getPageIndex() {
                return pageIndex;
        }

        public void setPageIndex(Integer pageIndex) {
                this.pageIndex = pageIndex;
        }

        public String getNodeType() {
                return nodeType;
        }

        public void setNodeType(String nodeType) {
                this.nodeType = nodeType;
        }

		public String getAddressIp() {
			return addressIp;
		}

		public void setAddressIp(String addressIp) {
			this.addressIp = addressIp;
		}

		public String getServicePath() {
			return servicePath;
		}

		public void setServicePath(String servicePath) {
			this.servicePath = servicePath;
		}
}
