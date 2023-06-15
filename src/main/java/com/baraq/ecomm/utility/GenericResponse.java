package com.baraq.ecomm.utility;

import lombok.Data;

@Data
public class GenericResponse<T> {
    private String message;
    private ResponseStatus status;
    private T data;

    public GenericResponse(String message, ResponseStatus status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
    public GenericResponse(ResponseStatus status, String message) {
        this.message = message;
        this.status = status;
    }
}
