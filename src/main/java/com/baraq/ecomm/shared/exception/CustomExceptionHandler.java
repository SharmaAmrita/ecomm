package com.baraq.ecomm.shared.exception;

import com.baraq.ecomm.utility.GenericResponse;
import com.baraq.ecomm.utility.ResponseStatus;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GenericResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        GenericResponse<Object> errorResponse = new GenericResponse<>(ResponseStatus.FAILURE, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericResponse<Object>> handleRuntimeException(RuntimeException ex, WebRequest request) {
        GenericResponse<Object> errorResponse = new GenericResponse<>(ResponseStatus.FAILURE, "something went wrong");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<GenericResponse<Object>> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        GenericResponse<Object> errorResponse = new GenericResponse<>(ResponseStatus.FAILURE, "Unauthorized User");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
