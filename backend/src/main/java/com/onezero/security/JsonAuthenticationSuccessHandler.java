package com.onezero.security;


import com.onezero.model.Result;
import com.onezero.util.JacksonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


import java.io.IOException;
/**
 * @author: FenCho
 * @description:  登录成功处理器
 * @date: 2022/11/26 15:19
 */
@Slf4j
public class JsonAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        //默认页面跳转
        //super.onAuthenticationSuccess(request, response, authentication);
        response.setContentType("application/json;charset=utf-8");
        log.info("登录成功", request.getSession().getId());
        response.getWriter().write(JacksonUtil.asString(Result.ok("登录成功",
                map -> map.put("token", request.getSession().getId())
        )));
    }
}
