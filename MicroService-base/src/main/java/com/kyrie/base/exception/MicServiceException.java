package com.kyrie.base.exception;

/**
 * @auther: jijin
 * @date: 2023/7/3 23:55 周一
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description 本项目自定义异常类型，继承RuntimeException
 */
public class MicServiceException extends RuntimeException{
    private String errMessage;

}
