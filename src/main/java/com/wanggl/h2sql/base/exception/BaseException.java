package com.wanggl.h2sql.base.exception;

/**
 * 基础运行时异常
 *
 * @author wanggl
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1435081823849475077L;

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public static BaseException of(String message) {
        return new BaseException(message);
    }
}
