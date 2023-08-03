package com.onezero.config;

import com.github.pagehelper.PageInterceptor;
import com.github.pagehelper.page.PageAutoDialect;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import com.onezero.base.Creatable;
import com.onezero.base.Modifiable;
import com.onezero.mybatisflex.CzInsertListener;
import com.onezero.mybatisflex.CzUpdateListener;
import com.onezero.pagehelper.CzMysqlDialect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


@Configuration
@MapperScan("com.onezero.mapper")
public class MybatisFlexConfig implements MyBatisFlexCustomizer {
    @Override
    public void customize(FlexGlobalConfig flexGlobalConfig) {

        flexGlobalConfig.registerInsertListener(new CzInsertListener(), Creatable.class);
        flexGlobalConfig.registerUpdateListener(new CzUpdateListener(), Modifiable.class);

    }

    @Bean
    public PageInterceptor pageInterceptor() {
        PageAutoDialect.registerDialectAlias("mysql", CzMysqlDialect.class);
        var interceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("dialect", "com.onezero.pagehelper.CzPageHelper");
        interceptor.setProperties(properties);
        return interceptor;
    }

}
