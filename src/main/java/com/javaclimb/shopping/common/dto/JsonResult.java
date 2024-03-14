package com.javaclimb.shopping.common.dto;

import lombok.Data;

/*
前后端传输的JSON格式的数据
 */
@Data
public class JsonResult {
    /*
    返回的状态码0 失败 1成功
     */
    private Integer code;
    /*
    返回的信息
     */
    private String msg;
    /*
    返回的数据
     */
    private Object result;
    /*
    返回数据的构造方法
     */
    public JsonResult(Integer code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
    /*
    不返回数据的构造方法
     */
    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    /*
    只返回数据和状态码
     */
    public JsonResult(Integer code, Object result) {
        this.code = code;
        this.result = result;
    }

    /*
    成功的返沪
     */
    public static JsonResult success(){
        return new JsonResult(1,"操作成功");
    }
    /*
    成功的返沪
     */
    public static JsonResult success(String msg){
        return new JsonResult(1,msg);
    }
    /*
    成功的返沪
     */
    public static JsonResult success(String msg,Object result){
        return new JsonResult(1,msg,result);
    }
    /*
    失败的返回
     */
    public static JsonResult error(String msg){
        return  new JsonResult(0,msg);
    }
    /*
    失败的返回
     */
    public static JsonResult error(String msg,Object result){
        return  new JsonResult(0,msg,result);
    }
}
