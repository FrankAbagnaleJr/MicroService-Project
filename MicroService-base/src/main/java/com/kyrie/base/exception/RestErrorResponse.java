package com.kyrie.base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @auther: jijin
 * @date: 2023/7/3 23:52 周一
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description 和前端约定返回的返回异常信息模型
 */
@Data
@AllArgsConstructor
public class RestErrorResponse {
    private String errMessage;
}
