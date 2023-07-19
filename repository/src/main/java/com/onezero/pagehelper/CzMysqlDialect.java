package com.onezero.pagehelper;

import com.github.pagehelper.Page;
import com.github.pagehelper.dialect.helper.MySqlDialect;
import com.onezero.model.RPage;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * {@code @author} voncho
 * {@code @desc} pagehelper conver page to custom page (must extends Page)
 */
public class CzMysqlDialect extends MySqlDialect {
    @Override
    public Object afterPage(List pageList, Object parameterObject, RowBounds rowBounds) {
        Object obj = super.afterPage(pageList, parameterObject, rowBounds);
        RPage<Object> rPage = new RPage<>();
        rPage.addAll(pageList);
        if (obj instanceof Page<?> page) {
            rPage.setTotal(page.getTotal());
            rPage.setPageNum(page.getPageNum());
            rPage.setPageSize(page.getPageSize());
        }
        return rPage;
    }
}
