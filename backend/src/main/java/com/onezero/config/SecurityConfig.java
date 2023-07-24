package com.onezero.config;

import com.onezero.security.*;
import com.onezero.security.access.annotation.CmdScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.Locale;

@Configuration
@EnableWebSecurity
@CmdScan({"com.bmht.adv.controller"})
public class SecurityConfig {

    // 自定义login controller 会用到
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public AuthorizationManager<RequestAuthorizationContext> authorizationManager() {
        return new CzAuthorizationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        return new CzAuthenticationProvider(userDetailsService, passwordEncoder);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new JsonAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new JsonAuthenticationEntryPoint();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new JsonLogoutSuccessHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthorizationManager<RequestAuthorizationContext> authorizationManager,
            AuthenticationProvider authenticationProvider,
            AccessDeniedHandler accessDeniedHandler,
            AuthenticationEntryPoint authenticationEntryPoint,
            LogoutSuccessHandler logoutSuccessHandler
    ) throws Exception {
        LocaleContextHolder.setLocale(Locale.CHINA);
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(login -> {
//                    login.usernameParameter("userName");
//                    login.successHandler(authenticationSuccessHandler);
//                    login.failureHandler(authenticationFailureHandler);
//                })
                .sessionManagement(
                        session -> {
                            session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
                            session.sessionFixation().migrateSession();
                        }
                )
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/login", "/logout", "/captcha/**", "/test").permitAll();
                            //只验证登录不验证权限
                            auth.requestMatchers("/getUserInfo", "/getUserRoutes", "/base/**").authenticated();
                            //permission handle
                            auth.anyRequest().access(authorizationManager);
                        }
                )
                .authenticationProvider(authenticationProvider)
                .exceptionHandling((ex -> {
                    //认证异常处理
                    ex.authenticationEntryPoint(authenticationEntryPoint);
                    //授权异常处理
                    ex.accessDeniedHandler(accessDeniedHandler);
                }
                ))
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutSuccessHandler(logoutSuccessHandler))
                .anonymous(AbstractHttpConfigurer::disable);
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
