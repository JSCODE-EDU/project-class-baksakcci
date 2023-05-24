package com.example.spring_jscode.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionAdvice {
    // Spring Request Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return ErrorResponse.toResponseEntity(ErrorCode.INVALID_TITLE_CONTENT);
    }

    // Hibernate Exception

    // Not Found Exception
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> NoSuchElementExceptionHandler(NoSuchElementException e) {
        return ErrorResponse.toResponseEntity(ErrorCode.BOARD_NOT_FOUND);
    }
}
