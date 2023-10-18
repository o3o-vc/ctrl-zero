package com.onezero.config;

import com.beetl.sql.pref.PerformanceConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.beetl.sql.core.ExecuteContext;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLExecutor;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.core.extend.ParaExtend;
import org.beetl.sql.starter.SQLManagerCustomize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class BeetlsqlConfig {
    @Primary
    @Bean
    public DataSource datasource(Environment env) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        return ds;
    }
    @Bean
    public SQLManagerCustomize mySQLManagerCustomize(){
        return (sqlMangerName, manager) -> {
            /*List<Interceptor> interceptors = new ArrayList<>(Arrays.asList(manager.getInters()));
            FillInterceptor fillInterceptor = new FillInterceptor();
            interceptors.add(fillInterceptor);
            manager.setInters(interceptors.toArray(new Interceptor[0]));*/
            PerformanceConfig performanceConfig = new PerformanceConfig();
            performanceConfig.config(manager);
        };
    }



}
