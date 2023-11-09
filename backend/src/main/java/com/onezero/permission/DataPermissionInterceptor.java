package com.onezero.permission;

import com.onezero.util.Context;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import org.beetl.sql.core.ExecuteContext;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.InterceptorContext;
import com.onezero.domain.system.DataPermission;

import java.util.Objects;

public class DataPermissionInterceptor implements Interceptor {
    @Override
    public void before(InterceptorContext ctx) {
        ExecuteContext executeContext = ctx.getExecuteContext();
        if (executeContext.isUpdate) {
            return;
        }
        DataPermission dataPermission = Context.get("data-permission", DataPermission.class);
        if (Objects.nonNull(dataPermission)) {
            try {
                Statement statement = CCJSqlParserUtil.parse(executeContext.sqlResult.jdbcSql);
                if (statement instanceof PlainSelect select) {
                    System.out.println(select.getFromItem());
                }
            } catch (JSQLParserException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void after(InterceptorContext ctx) {

    }

    @Override
    public void exception(InterceptorContext ctx, Exception ex) {

    }

    public static void main(String[] args) throws JSQLParserException {
        String sql = "select \n" +
                "    * \n" +
                "from test \n" +
                "where 1 = 1\n" +
                "    and name = ?\n" +
                "\n" +
                "\n" +
                "   \n" +
                "limit ?  , ? ";
        System.out.println(sql.replace("\n\n", "\n"));
        Statement parse = CCJSqlParserUtil.parse(sql.replace("\n\n", "\n"));
        System.out.println(parse);
    }
}
