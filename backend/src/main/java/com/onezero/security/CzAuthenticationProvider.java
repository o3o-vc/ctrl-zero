package com.onezero.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.Locale;

public class CzAuthenticationProvider extends DaoAuthenticationProvider {
    public CzAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Maybe have some logics


        return super.authenticate(authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    protected void doAfterPropertiesSet() {
        /*
         * messageSource 被aware接口设置为 AnnotationConfigServletWebServerApplicationContext(实现了messageSource接口 默认locale 为us)
         * 此处重设locale
         */
        SpringSecurityMessageSource messageSource = new SpringSecurityMessageSource();
        messageSource.setDefaultLocale(Locale.CHINESE);
        this.setMessageSource(messageSource);
        Assert.notNull(this.messages, "messages must be set!");
    }
}
