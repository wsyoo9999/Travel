package com.multi.travel.vo;

import java.util.List;
import com.multi.travel.support.Pagination;

public class PageResponseVO<T> {
    private List<T> list;
    private Pagination pagination;

    public PageResponseVO(List<T> list, Pagination pagination) {
        this.list = list;
        this.pagination = pagination;
    }

    public List<T> getList() { return list; }
    public Pagination getPagination() { return pagination; }
}