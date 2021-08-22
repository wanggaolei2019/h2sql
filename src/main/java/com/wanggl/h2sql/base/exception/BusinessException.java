package com.wanggl.h2sql.base.exception;

/**
 * 业务异常
 *
 * @author wanggl
 */
public class BusinessException extends Exception {
    private static final long serialVersionUID = 1435081871849475077L;

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public static BusinessException of(String message) {
        return new BusinessException(message);
    }

    public static BusinessException notFound() {
        return new BusinessException("数据不存在");
    }

    public static BusinessException entityNotFound() {
        return new BusinessException("表单配置不存在");
    }
}
