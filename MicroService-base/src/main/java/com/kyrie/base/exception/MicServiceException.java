package com.kyrie.base.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther: jijin
 * @date: 2023/7/3 23:55 周一
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description 本项目自定义异常类型，继承RuntimeException
 */
@NoArgsConstructor
@Data
public class MicServiceException extends RuntimeException{
    private String errMessage;

    public MicServiceException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }
    public static void cast(String message){
        throw new MicServiceException(message);
    }
    public static void cast(CommonError error){
        throw new MicServiceException(error.getErrMessage());
    }
}
