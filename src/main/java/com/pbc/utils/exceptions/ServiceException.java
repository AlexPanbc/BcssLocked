package com.pbc.utils.exceptions;

/**
 * 功能：Service层抛出的异常类
 * Created by LiuHuiChao on 2016/10/7.
 */
public class ServiceException extends RuntimeException {


    private String info;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message, String info) {
        super(message);
        this.info = info;
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
}

