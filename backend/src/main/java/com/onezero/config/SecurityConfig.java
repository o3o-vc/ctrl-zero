package com.onezero.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.onezero.security.*;
import com.onezero.security.access.annotation.CmdScan;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.Locale;

@Configuration
@EnableWebSecurity
@CmdScan({"com.bmht.adv.controller"})
@RequiredArgsConstructor
public class SecurityConfig {

    private final KeyComponent keyComponent;

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
//            AuthorizationManager<RequestAuthorizationContext> authorizationManager,
            AuthenticationProvider authenticationProvider,
//            AccessDeniedHandler accessDeniedHandler,
//            AuthenticationEntryPoint authenticationEntryPoint,
            LogoutSuccessHandler logoutSuccessHandler
    ) throws Exception {
        LocaleContextHolder.setLocale(Locale.CHINA);
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(
                        resourceServerConfigurer ->
                                resourceServerConfigurer.jwt(
                                        jwtConfigurer -> jwtConfigurer
                                                .jwtAuthenticationConverter(jwt2UserConverter())
                                )
                )
                .sessionManagement(
                        session -> {
                            // jwt
                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                        }
                )
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/login",  "/token", "/captcha/**").permitAll();
                            //只验证登录不验证权限
                            auth.requestMatchers("/getUserInfo", "/getUserRoutes", "/base/**").authenticated();
                            auth.anyRequest().authenticated();
                            //permission handle
//                            auth.anyRequest().access(authorizationManager);
                        }
                )
                .authenticationProvider(authenticationProvider)
                /*.exceptionHandling((ex -> {
                    //认证异常处理
                    ex.authenticationEntryPoint(authenticationEntryPoint);
                    //授权异常处理
                    ex.accessDeniedHandler(accessDeniedHandler);
                }
                ))*/
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                )
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

    @Bean
    @Primary
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey
                .Builder(keyComponent.getAccessTokenPublicKey())
                .privateKey(keyComponent.getAccessTokenPrivateKey())
                .build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    @Primary
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(keyComponent.getAccessTokenPublicKey()).build();
    }

    @Bean
    @Qualifier("jwtRefreshTokenDecoder")
    JwtDecoder jwtRefreshTokenDecoder() {
        return NimbusJwtDecoder.withPublicKey(keyComponent.getRefreshTokenPublicKey()).build();
    }

    @Bean
    @Qualifier("jwtRefreshTokenEncoder")
    JwtEncoder jwtRefreshTokenEncoder() {
        JWK jwk = new RSAKey
                .Builder(keyComponent.getRefreshTokenPublicKey())
                .privateKey(keyComponent.getRefreshTokenPrivateKey())
                .build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    @Qualifier("jwtRefreshTokenAuthProvider")
    JwtAuthenticationProvider jwtRefreshTokenAuthProvider() {
        JwtAuthenticationProvider provider = new JwtAuthenticationProvider(jwtRefreshTokenDecoder());
        provider.setJwtAuthenticationConverter(jwt2UserConverter());
        return provider;
    }

    @Bean
    Converter jwt2UserConverter() {
        return new Jwt2UserConverter();
    }

}
