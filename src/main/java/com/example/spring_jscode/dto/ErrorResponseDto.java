package com.example.spring_jscode.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponseDto {
    private String status;
    private String message;

    public static ErrorResponseDto of(String status, String message) {
        return new ErrorResponseDto(status, message);
    }
}
