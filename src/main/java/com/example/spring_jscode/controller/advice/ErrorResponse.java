package com.example.spring_jscode.controller.advice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private String status;
    private String message;

    public static ErrorResponse of(String status, String message) {
        return new ErrorResponse(status, message);
    }
}
