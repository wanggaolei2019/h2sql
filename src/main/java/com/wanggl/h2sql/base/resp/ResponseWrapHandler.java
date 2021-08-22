package com.wanggl.h2sql.base.resp;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * 使用 @ControllerAdvice & ResponseBodyAdvice
 * 拦截Controller方法默认返回参数，统一处理返回值/响应体
 */
@RestControllerAdvice
public class ResponseWrapHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 检查注解是否存在
        if (methodParameter.getDeclaringClass().isAnnotationPresent(ResponseWrap.class)) {
            return true;
        }
        return Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(ResponseWrap.class);
    }

    /**
     * 对返回值做包装处理，如果属于异常结果，则需要再包装
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter arg1, MediaType arg2,
                                  Class<? extends HttpMessageConverter<?>> arg3, ServerHttpRequest arg4, ServerHttpResponse arg5) {
        if (null == body) {
            return ApiResp.ofSuccess();
        }
        if (body instanceof ResponseEntity) {
            return body;
        } else if (body instanceof ApiResp) {
            return body;
        }
        return ApiResp.ofSuccess(body);
    }
}
