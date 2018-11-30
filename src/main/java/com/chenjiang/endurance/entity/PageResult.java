package com.chenjiang.endurance.entity;

import java.util.List;

public class PageResult<E> {
    private List<E> items;
    private int offset; // 起始查询位置
    private int limit;  // 每页数据量
    private int total;  // 数据总数
    private int page;   // 页码

    public PageResult(List<E> items, int offset, int limit, int total) {
        this.items = items;
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.page = limit == 0 ? 1 : (offset / limit) + 1;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<E> getItems() {
        return items;
    }

    public void setItems(List<E> list) {
        this.items = list;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
