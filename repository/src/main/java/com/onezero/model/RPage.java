package com.onezero.model;

import com.github.pagehelper.Page;
import com.onezero.domain.PageInfo;

public class RPage<T> extends Page<T> {
    public PageInfo<T> as() {
        PageInfo<T> localPage = new PageInfo<>();
        localPage.setPageSize(this.getPageSize());
        localPage.setPageNumber(this.getPageNum());
        localPage.setTotalPage(this.getPages());
        localPage.setTotalRow(this.getTotal());
        localPage.setRecords(this);
        return localPage;
    }
}
