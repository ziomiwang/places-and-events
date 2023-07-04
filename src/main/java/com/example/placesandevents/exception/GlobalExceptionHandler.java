package com.example.placesandevents.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<CustomErrorResponse> handleException(ServerException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(CustomErrorResponse.map(ex));
    }
}
