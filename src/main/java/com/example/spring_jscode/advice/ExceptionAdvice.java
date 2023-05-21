package com.example.spring_jscode.advice;

import com.example.spring_jscode.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        ErrorResponseDto errorResponse = ErrorResponseDto.of("404", e.getLocalizedMessage());
        return ResponseEntity.status(404)
                .body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity NoSuchElementExceptionHandler(NoSuchElementException e) {
        ErrorResponseDto errorResponse = ErrorResponseDto.of("404", e.getLocalizedMessage());
        return ResponseEntity.status(404)
                .body(errorResponse);
    }
}
