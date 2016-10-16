package com.pbc.utils.exceptions;

/**
 * 功能：controller抛出的异常，例如，字段校验。。。
 * Created by LiuHuiChao on 2016/10/16.
 */
public class CustomException extends Exception {
    /**
     * 异常信息
     */
    public String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
