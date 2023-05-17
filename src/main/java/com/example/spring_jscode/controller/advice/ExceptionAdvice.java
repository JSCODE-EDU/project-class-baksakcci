package com.example.spring_jscode.controller.advice;

import com.example.spring_jscode.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ErrorResponseDto errorResponse = ErrorResponseDto.of("400"
                ,e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(400)
                .body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity NoSuchElementExceptionHandler(NoSuchElementException e) {
        ErrorResponseDto errorResponse = ErrorResponseDto.of("404", e.getLocalizedMessage());
        return ResponseEntity.status(404)
                .body(errorResponse);
    }
}
