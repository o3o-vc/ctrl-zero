package com.onezero.security;

import com.onezero.domain.system.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collections;

public class Jwt2UserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {
    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
        User user = new User();
        user.setId(source.getClaim("userId"));
        user.setUsername(source.getSubject());
        return new UsernamePasswordAuthenticationToken(new SecurityUser(user), source, Collections.EMPTY_LIST);
    }
}
