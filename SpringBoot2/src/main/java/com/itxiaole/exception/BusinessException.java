package com.itxiaole.exception;

//处理业务异常 抛出信息 自定义异常
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
