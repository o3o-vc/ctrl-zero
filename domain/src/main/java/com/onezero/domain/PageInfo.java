package com.onezero.domain;


import lombok.Data;
import org.beetl.sql.core.page.DefaultPageRequest;
import org.beetl.sql.core.page.PageRequest;
import org.beetl.sql.core.page.PageResult;

import java.util.List;

@Data
public class PageInfo<T> implements PageRequest<T>, PageResult<T> {

    long pageNumber;
    int pageSize;
    String orderBy;
    boolean totalRequired = true;
    boolean listRequired = true;

    List<T> records;
    long pageCount;
    long itemCount;

    public PageInfo() {}

    public static <T> PageInfo<T> of(long page, int pageSize, boolean totalRequired, boolean listRequired) {
        PageInfo<T> request = new PageInfo<>();
        request.pageNumber = page;
        request.pageSize = pageSize;
        request.totalRequired = totalRequired;
        request.listRequired = listRequired;
        return request;
    }
    public static <T> PageInfo<T> of(int pageNumber, int pageSize) {
        return of(pageNumber, pageSize, true, true);
    }


    @Override
    public PageInfo<T> of(List<T> result) {
        return of(result, 0L);
    }

    @Override
    public PageInfo<T> of(List<T> result, Long total) {
        PageInfo<T> pageResult = new PageInfo<>();
        pageResult.setPageNumber(this.pageNumber);
        pageResult.setPageSize(this.pageSize);
        pageResult.setItemCount(total);
        pageResult.setRecords(result);
        pageResult.calcTotalPage();
        return pageResult;
    }

    @Override
    public long getTotalRow() {
        return this.itemCount;
    }

    @Override
    public List<T> getList() {
        return this.records;
    }

    @Override
    public long getTotalPage() {
        return this.pageCount;
    }

    @Override
    public void setList(List<T> list) {
        this.setRecords(list);
    }

    protected void calcTotalPage() {
        if (this.itemCount == 0L) {
            this.pageCount = 1L;
        } else if (this.itemCount % this.pageSize == 0L) {
            this.pageCount = this.itemCount / this.pageSize;
        } else {
            this.pageCount = this.itemCount / this.pageSize + 1L;
        }

    }
}
