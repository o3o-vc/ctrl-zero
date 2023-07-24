package com.onezero.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;

    @PostMapping("login")
    public Object login(String userName, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, password, new ArrayList<>());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        /**
         * 用户名或密码错误，貌似不会进入被注释的代码，会直接抛出异常进入catch
         * 自定义authenticate逻辑时
         */
        // if (Objects.isNull(authenticate)) {
        //     return Result.message(Result.SYS_UNAUTHORIZED, "用户名或密码错误");
        // }
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticate);
        SecurityContextHolder.setContext(context);
        return "success";
    }
}
