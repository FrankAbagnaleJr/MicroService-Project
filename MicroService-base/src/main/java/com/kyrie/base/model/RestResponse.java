package com.kyrie.base.model;

import lombok.Data;
import lombok.ToString;

/**
 * @auther: jijin
 * @date: 2023/7/2 18:15 周日
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description TODO
 */
@Data
@ToString
public class RestResponse<T> {
    /**
     * 响应编码,0为正常,-1错误
     */
    private boolean flag;

    /**
     * 响应提示信息
     */
    private String msg;

    /**
     * 响应内容
     */
    private T result;


    public RestResponse() {
        this(true, "success");
    }

    public RestResponse(boolean flag) {
        this.flag = flag;
    }

    public RestResponse(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public RestResponse(boolean flag, String msg,T result) {
        this.flag = flag;
        this.msg = msg;
        this.result = result;
    }

    /**
     * 错误信息的封装
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> RestResponse<T> validfail(String msg) {
        RestResponse<T> response = new RestResponse<T>();
        response.setFlag(false);
        response.setMsg(msg);
        return response;
    }
    public static <T> RestResponse<T> validfail(T result,String msg) {
        RestResponse<T> response = new RestResponse<T>();
        response.setFlag(false);
        response.setResult(result);
        response.setMsg(msg);
        return response;
    }



    /**
     * 添加正常响应数据（包含响应内容）
     *
     * @return RestResponse Rest服务封装相应数据
     */
    public static <T> RestResponse<T> success(T result) {
        RestResponse<T> response = new RestResponse<T>();
        response.setResult(result);
        return response;
    }
    public static <T> RestResponse<T> success(T result,String msg) {
        RestResponse<T> response = new RestResponse<T>();
        response.setResult(result);
        response.setMsg(msg);
        return response;
    }

    /**
     * 添加正常响应数据（不包含响应内容）
     *
     * @return RestResponse Rest服务封装相应数据
     */
    public static <T> RestResponse<T> success() {
        return new RestResponse<T>();
    }
}
