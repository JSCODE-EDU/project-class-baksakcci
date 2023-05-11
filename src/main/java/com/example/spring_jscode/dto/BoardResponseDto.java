package com.example.spring_jscode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class BoardResponseDto {
    private String title;
    private String content;

    public static BoardResponseDto fromEntity(String title, String content) {
        return new BoardResponseDto(title, content);
    }
}
