package com.onezero.config;

import com.onezero.model.BusinessException;
import com.onezero.model.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
@RestControllerAdvice(basePackages = "com.onezero.controller")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result<?>) {
            return body;
        }
        return Result.ok(body);
    }


    public Result<?> handleBusinessException(BusinessException be) {
        return null;
    }

    @ExceptionHandler({Exception.class})
    public Result<?> handleException(Exception e) {
        e.printStackTrace();
        return Result.error(e.getMessage());
    }
}
