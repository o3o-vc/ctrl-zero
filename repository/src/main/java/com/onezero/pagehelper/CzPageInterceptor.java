package com.onezero.pagehelper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.onezero.domain.PageInfo;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Map;
@Deprecated
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
public class CzPageInterceptor extends PageInterceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Object parameter = args[1];
        if (parameter != null) {
            if (isPageable(parameter)) {
                return super.intercept(invocation);
            }
        }
        return invocation.proceed();
    }

    private boolean isPageable(Object o) {
        if (o instanceof Page<?> page) {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            return true;
        }
        if (o instanceof Map<?,?> map) {
            for (Object arg : map.values()) {
                if (arg instanceof Page<?> page) {
                    PageHelper.startPage(page.getPageNum(), page.getPageSize());
                    return true;
                }
            }
        }
        if (o instanceof PageInfo<?> page) {
            PageHelper.startPage((int) page.getPageNumber(), (int) page.getPageSize());
            return true;
        }
        if (o instanceof Map<?,?> map) {
            for (Object arg : map.values()) {
                if (arg instanceof PageInfo<?> page) {
                    PageHelper.startPage((int) page.getPageNumber(), (int) page.getPageSize());
                    return true;
                }
            }
        }
        return false;
    }
}
