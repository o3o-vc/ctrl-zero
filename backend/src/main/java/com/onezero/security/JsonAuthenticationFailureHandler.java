package com.onezero.security;

import com.onezero.model.Result;
import com.onezero.util.JacksonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


import java.io.IOException;
/**
 * @author: FenCho
 * @description:  登录失败处理器
 * @date: 2022/11/26 15:19
 */
@Slf4j
public class JsonAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        //默认跳转url
        //super.onAuthenticationFailure(request, response, exception);
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=utf-8");
        log.error("登录失败", exception);
        response.getWriter().write(JacksonUtil.asString(Result.message(Result.SYS_UNAUTHORIZED, exception.getMessage())));
    }
}
