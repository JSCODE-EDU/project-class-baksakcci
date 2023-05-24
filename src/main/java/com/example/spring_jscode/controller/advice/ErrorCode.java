package com.example.spring_jscode.controller.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400
    INVALID_TITLE_CONTENT(HttpStatus.BAD_REQUEST, "제목 혹은 내용이 유효하지 않습니다."),
    // 404
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "게시판을 찾을 수 없습니다."),
    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에 오류가 발생하였습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
