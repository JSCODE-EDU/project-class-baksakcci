package com.example.spring_jscode.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = ErrorResponse.of("400"
                ,e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(400)
                .body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity NoSuchElementExceptionHandler(NoSuchElementException e) {
        ErrorResponse errorResponse = ErrorResponse.of("404", e.getLocalizedMessage());
        return ResponseEntity.status(404)
                .body(errorResponse);
    }
}
