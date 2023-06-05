package com.example.spring_jscode.controller.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400
    INVALID_TITLE_CONTENT(HttpStatus.BAD_REQUEST, "제목 혹은 내용이 유효하지 않습니다."),
    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "동일한 이메일이 이미 존재합니다."),
    // 401
    UNAUTHENTICATED_EMPTY_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 헤더에 존재하지 않습니다."),
    UNAUTHENTICATED_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다."),
    UNAUTHENTICATED_EMPTY_USERID(HttpStatus.UNAUTHORIZED, "헤더 안의 user id값이 존재하지 않습니다."),
    // 404
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "게시판을 찾을 수 없습니다."),
    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에 오류가 발생하였습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
