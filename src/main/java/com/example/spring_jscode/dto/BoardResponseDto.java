package com.example.spring_jscode.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;

    // 매개변수 Board -> from, of => 변수명 수정
    public static BoardResponseDto fromEntity(Long id, String title, String content) {
        return new BoardResponseDto(id, title, content);
    }
}
