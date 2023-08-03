package com.onezero;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.onezero.repository")
public class AuthorizationServerApp {
    public static void main( String[] args ) {
        SpringApplication.run(AuthorizationServerApp.class, args);
    }
}
