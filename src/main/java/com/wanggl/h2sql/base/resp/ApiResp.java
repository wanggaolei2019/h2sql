package com.wanggl.h2sql.base.resp;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * api响应
 *
 * @author wanggl
 */
@ApiModel("api响应")
public class ApiResp<T> {
    @ApiModelProperty("状态码")
    private int code;

    @ApiModelProperty("消息")
    private String message;

    @ApiModelProperty("业务数据")
    private T data;

    private ApiResp(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResp<T> ofSuccess() {
        return new ApiResp<>(200, "ok", null);
    }

    public static <T> ApiResp<T> ofSuccess(T data) {
        return new ApiResp<>(200, "ok", data);
    }

    public static <T> ApiResp<T> ofSuccess(String message) {
        return new ApiResp<>(200, message, null);
    }

    public static <T> ApiResp<T> ofSuccess(T data, String message) {
        return new ApiResp<>(200, message, data);
    }

    public static <T> ApiResp<T> ofFailed(String message) {
        return new ApiResp<>(500, message, null);
    }

    public static <T> ApiResp<T> ofFailed(T data, String message) {
        return new ApiResp<>(500, message, data);
    }

    public static <T> ApiResp<T> ofMessage(int code, String message) {
        return new ApiResp<>(code, message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
