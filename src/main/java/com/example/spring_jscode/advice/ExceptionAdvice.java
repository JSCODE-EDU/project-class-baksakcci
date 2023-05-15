package com.example.spring_jscode.advice;

import com.example.spring_jscode.dto.ErrorResponseDto;
import com.example.spring_jscode.exception.ValidateLengthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        ErrorResponseDto errorResponse = ErrorResponseDto.of("404", e.getMessage());
        return ResponseEntity.status(404)
                .body(errorResponse);
    }
}
