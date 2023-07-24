package com.onezero.security;


import com.onezero.model.Result;
import com.onezero.util.JacksonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


import java.io.IOException;

@Slf4j
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        log.error("登录session失效:{}", authException.getMessage());
        response.getWriter().write(JacksonUtil.asString(Result.get(Result.SYS_UNAUTHORIZED, "认证信息失效", authException.getMessage())));
    }
}
