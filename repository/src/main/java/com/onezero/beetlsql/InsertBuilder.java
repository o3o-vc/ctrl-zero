package com.onezero.beetlsql;

import org.beetl.sql.mapper.MapperInvoke;
import org.beetl.sql.mapper.builder.MapperExtBuilder;

import java.lang.reflect.Method;

public class InsertBuilder implements MapperExtBuilder {
    @Override
    public MapperInvoke parse(Class entity, Method m) {
        return new InsertInvoke();
    }
}
