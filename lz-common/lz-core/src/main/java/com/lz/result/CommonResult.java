package com.lz.result;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import com.lz.constant.ResultConstant;

/**
 * @author :Lictory
 * @date : 2024/03/04
 */
@Data
@ToString
@Builder
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult() {
    }

    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;

    }


    public static <T> CommonResult<T> autoResult(boolean isSuccess) {
        if (isSuccess) {
            return CommonResult.success();
        } else {
            return CommonResult.failed();
        }
    }

    public static <T> CommonResult<T> autoResult(boolean isSuccess, T data) {
        if (isSuccess) {
            return CommonResult.success(data);
        } else {
            return CommonResult.failed(data);
        }
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultConstant.SUCCESS_CODE, ResultConstant.OPERATE_SUCCESS_MESSAGE, data);
    }

    public static <T> CommonResult<T> failed(T data) {
        return new CommonResult<>(ResultConstant.FAIL_CODE, ResultConstant.OPERATE_FAIL_MESSAGE, data);
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult<>(ResultConstant.SUCCESS_CODE, ResultConstant.OPERATE_SUCCESS_MESSAGE);
    }

    public static <T> CommonResult<T> failed() {
        return new CommonResult<>(ResultConstant.FAIL_CODE, ResultConstant.OPERATE_FAIL_MESSAGE);
    }

    public static <T> CommonResult<T> successWithMsg(String message, T data) {
        return new CommonResult<>(ResultConstant.SUCCESS_CODE, message, data);
    }

    public static <T> CommonResult<T> failedWithMsg(String message, T data) {
        return new CommonResult<>(ResultConstant.FAIL_CODE, message, data);
    }

    public static <T> CommonResult<T> successWithMsg(String message) {
        return new CommonResult<>(ResultConstant.SUCCESS_CODE, message);
    }

    public static <T> CommonResult<T> failedWithMsg(String message) {
        return new CommonResult<>(ResultConstant.FAIL_CODE, message);
    }

    public static <T> CommonResult<T> failedWithCodeAndMsg(int code, String message) {
        return new CommonResult<>(code, message);
    }
}
