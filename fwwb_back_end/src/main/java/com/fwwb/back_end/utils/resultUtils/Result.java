package com.fwwb.back_end.utils.resultUtils;

import com.fwwb.back_end.utils.exceptionHandler.exception.BaseErrorInfoInterface;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: demo
 * @description: response返回结果格式类
 * @author: CodingLiOOT
 * @create: 2020-12-25 15:34
 * @version: 1.0
 **/
@Data
@Accessors(fluent = true)
public class Result implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return new Result()
                .code(0)
                .msg("操作成功")
                .data(data);
    }

    public static Result success(Object data, String msg) {
        return new Result()
                .code(0)
                .msg(msg)
                .data(data);
    }

    public static Result success(String msg) {
        return new Result()
                .code(0)
                .msg(msg);
    }

    public static Result fail(BaseErrorInfoInterface error) {
        return new Result()
                .code(1)
                .msg(error.getResultMsg())
                .data(error.getResultData());
    }

    public static Result fail(String msg) {
        return new Result()
                .code(1)
                .msg(msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
