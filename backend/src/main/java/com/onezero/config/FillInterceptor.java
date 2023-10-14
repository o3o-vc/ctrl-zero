package com.onezero.config;

import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.InterceptorContext;

public class FillInterceptor implements Interceptor {
    @Override
    public void before(InterceptorContext ctx) {

    }

    @Override
    public void after(InterceptorContext ctx) {
        System.out.println(ctx);
    }

    @Override
    public void exception(InterceptorContext ctx, Exception ex) {

    }
}
