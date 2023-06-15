package com.baraq.ecomm.shared.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException{
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public BaseException(String message, Throwable throwable) {
        super(message, throwable);
    }
    public BaseException(String message) {
        super(message);
    }
}

