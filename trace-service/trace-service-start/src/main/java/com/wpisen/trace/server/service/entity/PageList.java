package com.wpisen.trace.server.service.entity;

import java.util.List;

/**
 * Created by wpisen on 16/8/12.
 */
public class PageList<T> implements java.io.Serializable{
	private static final long serialVersionUID = 1758785764522386638L;
	private int pageSize;        // 每页大小
    private int pageIndex;       // 当前页码
    private long totalElements; // 总数
    private int totalPage;      // 总页数
    private List<T> elements;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }
}
