package com.jcy.tradingstrategies.common.base;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Objects;
import java.util.Optional;

public class PageQuery<T> {

    /**
     * 第几页
     */
    private Integer page = 1;

    /**
     * 每页大小
     */
    private Integer size = 10;

    /**
     * 排序
     */
    private String sort;

    public PageQuery() {
    }

    public Page<T> getIpage() {
        //分页参数
        int curPage = Optional.ofNullable(this.getPage()).orElse(1);
        int limit = Optional.ofNullable(this.getSize()).orElse(10);
        //分页对象
        return new Page<>(curPage, limit);
    }

    public Integer getPage() {
        return this.page;
    }

    public Integer getSize() {
        return this.size;
    }

    public String getSort() {
        return this.sort;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PageQuery<?> pageQuery = (PageQuery<?>) o;
        return Objects.equals(page, pageQuery.page) &&
                Objects.equals(size, pageQuery.size) &&
                Objects.equals(sort, pageQuery.sort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, size, sort);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PageQuery;
    }

    @Override
    public String toString() {
        return "PageQuery(page=" + this.getPage() + ", size=" + this.getSize() + ", sort=" + this.getSort() + ")";
    }
}
