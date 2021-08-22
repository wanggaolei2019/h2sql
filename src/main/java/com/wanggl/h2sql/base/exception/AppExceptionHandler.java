package com.wanggl.h2sql.base.exception;


import cn.hutool.core.util.ArrayUtil;
import com.wanggl.h2sql.base.resp.ApiResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;


/**
 * 全局异常处理
 *
 * @author wanggl
 */
@RestControllerAdvice
public class AppExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ApiResp<Object> handleException(Exception e) {
        logger.warn(e.getMessage());
        logger.info(e.getMessage(), e);
        return ApiResp.ofFailed(e.getMessage());
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ApiResp<Object> handleNullPointerException(NullPointerException e) {
        logger.warn(e.getMessage());
        logger.info(e.getMessage(), e);
        StackTraceElement[] stackTrace = e.getStackTrace();
        if (ArrayUtil.isNotEmpty(stackTrace)) {
            StackTraceElement stackTraceElement = stackTrace[0];
            if (stackTraceElement != null) {
                return ApiResp.ofFailed("空指针异常：" + stackTraceElement.getMethodName());
            }
        }
        return ApiResp.ofFailed("空指针异常：");
    }

    @ExceptionHandler(value = BusinessException.class)
    public ApiResp<Object> handleBusinessException(BusinessException e) {
        logger.info(e.getMessage(), e);
        return ApiResp.ofFailed("处理失败：" + e.getMessage());
    }

    @ExceptionHandler(value = BaseException.class)
    public ApiResp<Object> handleBaseException(BaseException e) {
        logger.info(e.getMessage(), e);
        return ApiResp.ofFailed(e.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public ApiResp<Object> handleMethodArgumentNotValid(BindException e) {
        BindingResult result = e.getBindingResult();
        String msg = result.getFieldErrors().stream()
                .map(f -> String.format("[%s]：%s", f.getField(), f.getDefaultMessage()))
                .collect(Collectors.joining(";"));
        return ApiResp.ofFailed(msg);
    }

}
