package com.onezero.pagehelper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onezero.domain.PageInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;

import java.util.Map;

/**
 * {@code @author} voncho
 *
 * {@code @desc} pagehelper args for Page or PageInfo auto do page
 */
public class CzPageHelper extends PageHelper {
    @Override
    public boolean skip(MappedStatement ms, Object parameterObject, RowBounds rowBounds) {
        Page<?> page = getPage(parameterObject);
        return super.skip(ms, page, rowBounds);
    }
    private Page<?> getPage(Object o) {
        if (o instanceof Page<?> page) {
            return PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }
        if (o instanceof Map<?,?> map) {
            for (Object arg : map.values()) {
                if (arg instanceof Page<?> page) {
                    return PageHelper.startPage(page.getPageNum(), page.getPageSize());
                }
            }
        }
        if (o instanceof PageInfo<?> page) {
            return PageHelper.startPage((int) page.getPageNumber(), (int) page.getPageSize());
        }
        if (o instanceof Map<?,?> map) {
            for (Object arg : map.values()) {
                if (arg instanceof PageInfo<?> page) {
                    return PageHelper.startPage((int) page.getPageNumber(), (int) page.getPageSize());
                }
            }
        }
        return null;
    }
}
