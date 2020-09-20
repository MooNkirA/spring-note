package com.moon.springmvc.exception;

/**
 * 自定义异常（处理业务异常）
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-19 23:31
 * @description
 */
public class CustomException extends RuntimeException {

    private String message;

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
