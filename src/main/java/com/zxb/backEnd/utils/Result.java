package com.zxb.backEnd.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zxb.backEnd.pojos.User;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result() {}

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 静态方法简化创建结果对象
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null);
    }

    public static Result<User> error(int code, String message) {
        return new Result<>(code, message, null);
    }
}
