package com.aix.swifttransit.common.core.util;

import com.aix.swifttransit.common.core.constant.CommonConstants;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 全局通用返回
 *
 * @param <T> 任意类型
 */

@ToString
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;


    private int code;

    private String message;

    private T data;

    public static <T> CommonResult<T> ok() {
        return restResult(CommonConstants.SUCCESS, null, null);
    }

    public static <T> CommonResult<T> ok(T data) {
        return restResult(CommonConstants.SUCCESS, null, data);
    }

    public static <T> CommonResult<T> ok(String message, T data) {
        return restResult(CommonConstants.SUCCESS, null, data);
    }

    public static <T> CommonResult<T> failed() {
        return restResult(CommonConstants.FAIL, null, null);
    }

    public static <T> CommonResult<T> failed(String msg) {
        return restResult(CommonConstants.FAIL, msg, null);
    }

    public static <T> CommonResult<T> failed(T data) {
        return restResult(CommonConstants.FAIL, null, data);
    }

    public static <T> CommonResult<T> failed(String msg, T data) {
        return restResult(CommonConstants.FAIL, msg, data);
    }

    public static <T> CommonResult<T> restResult(int code, String msg, T data) {
        CommonResult<T> apiResult = new CommonResult<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMessage(msg);
        return apiResult;
    }
}
