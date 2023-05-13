package com.example.spring_jscode.exception;

public class ValidateLengthException extends RuntimeException{
    public ValidateLengthException(String message){
        super(message);
    }
}
