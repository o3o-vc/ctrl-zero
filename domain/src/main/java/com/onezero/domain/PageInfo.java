package com.onezero.domain;

import com.mybatisflex.core.paginate.Page;

public class PageInfo<T> extends Page<T> {

    public PageInfo() {}

    public PageInfo(Integer pageNum, Integer pageSize) {
        this.setPageNumber(pageNum);
        this.setPageSize(pageSize);
    }

}
