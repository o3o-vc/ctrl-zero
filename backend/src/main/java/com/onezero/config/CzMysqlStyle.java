package com.onezero.config;

import com.onezero.model.UserInfo;
import com.onezero.security.SecurityUtil;
import org.beetl.sql.clazz.SQLType;
import org.beetl.sql.core.ExecuteContext;
import org.beetl.sql.core.SQLExecutor;
import org.beetl.sql.core.db.MySqlStyle;

import java.time.LocalDateTime;
import java.util.Objects;

public class CzMysqlStyle extends MySqlStyle {
    @Override
    public SQLExecutor buildExecutor(ExecuteContext executeContext) {
        UserInfo userInfo = null;
        long userId = 0;
        String name = "系统";
        try {
            userInfo = SecurityUtil.getUserInfo();
        } catch (Exception ignored) {}

        if (Objects.nonNull(userInfo)) {
            userId = Long.parseLong(userInfo.getUserId());
            name = userInfo.getUserName();
        }
        SQLType sqlType = executeContext.sqlSource.sqlType;
        if (sqlType.equals(SQLType.INSERT)) {
            executeContext.setContextPara("created", LocalDateTime.now());
            executeContext.setContextPara("creator", userId);
            executeContext.setContextPara("creatorName", name);
        } else if (sqlType.equals(SQLType.UPDATE)) {
            executeContext.setContextPara("modified", LocalDateTime.now());
            executeContext.setContextPara("modifier", userId);
            executeContext.setContextPara("modifierName", name);
        }
        return super.buildExecutor(executeContext);
    }
}
