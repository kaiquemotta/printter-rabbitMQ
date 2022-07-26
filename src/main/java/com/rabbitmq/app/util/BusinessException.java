package com.rabbitmq.app.util;

public class BusinessException extends Exception{
    public BusinessException(String message) {
        super(message);
    }
}
