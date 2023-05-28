package com.example.spring_jscode.controller.advice;

import com.example.spring_jscode.exception.CustomException;
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

    // Custom Exception
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> CustomExceptionHandler(CustomException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
