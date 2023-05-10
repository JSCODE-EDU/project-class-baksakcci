package com.example.spring_jscode.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponseDto {
    private String title;
    private String content;

    public void changeBoardToDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
