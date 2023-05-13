package com.example.spring_jscode.advice;

import com.example.spring_jscode.dto.ErrorResponseDto;
import com.example.spring_jscode.exception.ValidateLengthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(ValidateLengthException.class)
    public ResponseEntity validateLengthExceptionAdvice(ValidateLengthException e) {
        ErrorResponseDto errorResponse = ErrorResponseDto.of("404", e.getMessage());
        return ResponseEntity.status(404)
                .body(errorResponse);
    }
}
