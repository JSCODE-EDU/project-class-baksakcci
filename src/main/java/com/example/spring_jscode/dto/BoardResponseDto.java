package com.example.spring_jscode.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createAt;

    // 매개변수 Board -> from, of => 변수명 수정
    public static BoardResponseDto fromEntity(Long id, String title, String content, LocalDateTime createAt) {
        return new BoardResponseDto(id, title, content, createAt);
    }
}
