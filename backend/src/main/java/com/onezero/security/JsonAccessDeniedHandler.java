package com.onezero.security;


import com.onezero.model.Result;
import com.onezero.util.JacksonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;


import java.io.IOException;

@Slf4j
public class JsonAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        log.error(String.format("%s:没有权限", request.getRequestURI()),accessDeniedException);
        response.getWriter().write(JacksonUtil.asString(Result.get(Result.SYS_FORBIDDEN, "权限验证失败", request.getRequestURI())));
    }
}
