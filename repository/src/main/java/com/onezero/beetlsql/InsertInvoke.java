package com.onezero.beetlsql;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SqlId;
import org.beetl.sql.mapper.MapperInvoke;
import org.beetl.sql.mapper.annotation.SqlResource;

import java.lang.reflect.Method;

public class InsertInvoke extends MapperInvoke {
    @Override
    public Object call(SQLManager sm, Class entityClass, Method m, Object[] args) {
        String resource = m.getDeclaringClass().getAnnotation(SqlResource.class).value();
        SqlId insertId = SqlId.of(resource, m.getName());
        return sm.insert(insertId, args[0]);
    }
}
