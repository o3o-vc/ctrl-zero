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
        SQLType sqlType = executeContext.sqlSource.sqlType;
        if (sqlType.isUpdate() && !sqlType.equals(SQLType.DELETE)) {
            OperatorInfo result = getOperatorInfo();
            if (sqlType.equals(SQLType.INSERT)) {
                executeContext.setContextPara("created", LocalDateTime.now());
                executeContext.setContextPara("creator", result.userId());
                executeContext.setContextPara("creatorName", result.name());
            } else if (sqlType.equals(SQLType.UPDATE)) {
                executeContext.setContextPara("modified", LocalDateTime.now());
                executeContext.setContextPara("modifier", result.userId());
                executeContext.setContextPara("modifierName", result.name());
            }
        }
        return super.buildExecutor(executeContext);
    }

    private static OperatorInfo getOperatorInfo() {
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
        OperatorInfo result = new OperatorInfo(userId, name);
        return result;
    }

    private record OperatorInfo(long userId, String name) {
    }
}
