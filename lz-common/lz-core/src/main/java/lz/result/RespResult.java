package lz.result;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lz.constant.ResultConstant;

/**
 * @author :Lictory
 * @date : 2024/03/04
 */
@Data
@ToString
@Builder
public class RespResult<T> {
    private Integer code;
    private String message;
    private T data;

    public RespResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RespResult(Integer code, String message) {
        this.code = code;
        this.message = message;

    }

    public static <T> RespResult<T> success(T data) {
        return new RespResult<>(ResultConstant.SUCCESS_CODE, ResultConstant.OPERATE_SUCCESS_MESSAGE, data);
    }

    public static <T> RespResult<T> failed(T data) {
        return new RespResult<>(ResultConstant.FAIL_CODE, ResultConstant.OPERATE_FAIL_MESSAGE, data);
    }

    public static <T> RespResult<T> success() {
        return new RespResult<>(ResultConstant.SUCCESS_CODE, ResultConstant.OPERATE_SUCCESS_MESSAGE);
    }

    public static <T> RespResult<T> failed() {
        return new RespResult<>(ResultConstant.FAIL_CODE, ResultConstant.OPERATE_FAIL_MESSAGE);
    }

    public static <T> RespResult<T> successWithMsg(String message, T data) {
        return new RespResult<>(ResultConstant.SUCCESS_CODE, message, data);
    }

    public static <T> RespResult<T> failedWithMsg(String message, T data) {
        return new RespResult<>(ResultConstant.FAIL_CODE, message, data);
    }

    public static <T> RespResult<T> successWithMsg(String message) {
        return new RespResult<>(ResultConstant.SUCCESS_CODE, message);
    }

    public static <T> RespResult<T> failedWithMsg(String message) {
        return new RespResult<>(ResultConstant.FAIL_CODE, message);
    }

    public static <T> RespResult<T> failedWithCodeAndMsg(int code, String message) {
        return new RespResult<>(code, message);
    }
}
