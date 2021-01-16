package com.fwwb.back_end.utils;

import lombok.Data;
import java.io.Serializable;

/**
 * @program: demo
 * @description: response返回结果格式类
 * @author: CodingLiOOT
 * @create: 2020-12-25 15:34
 * @version: 1.0
 **/
@Data
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data){
        Result m =new Result();
        m.setCode(0);
        m.setData(data);
        m.setMsg("操作成功");
        return m;
    }
    public static Result success(Object data,String msg){
        Result m =new Result();
        m.setCode(0);
        m.setData(data);
        m.setMsg(msg);
        return m;
    }
    public static Result fail(String msg){
        Result m =new Result();
        m.setCode(1);
        m.setData(null);
        m.setMsg(msg);
        return m;
    }
    public static Result fail(Object data,String msg){
        Result m =new Result();
        m.setCode(1);
        m.setData(data);
        m.setMsg(msg);
        return m;
    }
}
