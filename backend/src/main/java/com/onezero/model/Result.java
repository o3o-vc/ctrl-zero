package com.onezero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author: FenCho
 * @description: 前端结果包装类
 * @date: 2022/11/29 11:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    public static final int OK = 200;

    public static final int BIZ_SUCCESS = 219;
    public static final int BIZ_INFO = 220;
    public static final int BIZ_WARN = 221;
    public static final int BIZ_ERROR = 222;

    public static final int SYS_UNAUTHORIZED = 401;
    public static final int SYS_FORBIDDEN = 403;

    private Integer code;
    private String message;
    private T data;

    public static Result<Void> message(Integer code, String message) {
        return get(code, message, null);
    }

    public static Result<Void> success(String message) {
        return message(BIZ_SUCCESS, message);
    }

    public static Result<Void> info(String message) {
        return message(BIZ_INFO, message);
    }

    public static Result<Void> warn(String message) {
        return message(BIZ_WARN, message);
    }

    public static <T> Result<T> get(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static Result<Map<String, Object>> map(Integer code, String message, Consumer<Map<String, Object>> consumer) {
        Map<String, Object> map = new HashMap<>();
        if (!Objects.isNull(consumer)) {
            consumer.accept(map);
        }
        return get(code, message, map);
    }

    public static <T> Result<T> ok(String message, T data) {
        return get(OK, message, data);
    }

    public static Result<Map<String, Object>> ok(String message, Consumer<Map<String, Object>> consumer) {
        return map(OK, message, consumer);
    }

    public static <T> Result<T> ok(T data) {
        return ok("ok", data);
    }

    public static Result<Void> ok(String message) {
        return get(OK, message, null);
    }

    public static <T> Result<T> error(String message, T data) {
        return get(BIZ_ERROR, message, data);
    }

    public static Result<Map<String, Object>> error(String message, Consumer<Map<String, Object>> consumer) {
        return map(BIZ_ERROR, message, consumer);
    }

    public static <T> Result<T> error(String message) {
        return get(BIZ_ERROR, message, null);
    }

    public static Result<Void> conditional(boolean condition, String trueMsg, String falseMsg) {
        if (condition) {
            return success(trueMsg);
        } else {
            return warn(falseMsg);
        }
    }

    /**
     *
     * @param affected 影响行数，大于0时返回成功，其他情况返回失败
     * @param operationName 操作名称
     * @return 结果
     */
    public static Result<Void> sf(int affected, String operationName) {
        return conditional(affected > 0, operationName + "成功", operationName + "失败");
    }
    /**
     *
     * @param affected 影响行数，大于0时返回成功，其他情况返回失败
     * @param operationName 操作名称
     * @return 结果
     */
    public static Result<Void> sf(Integer affected, String operationName) {
        if (affected == null) {
            affected = 0;
        }
        return conditional(affected > 0, operationName + "成功", operationName + "失败");
    }

    /**
     *
     * @param succeed 是否成功
     * @param operationName 操作名称
     * @return 结果
     */
    public static Result<Void> sf(boolean succeed, String operationName) {
        return conditional(succeed, operationName + "成功", operationName + "失败");
    }
}
