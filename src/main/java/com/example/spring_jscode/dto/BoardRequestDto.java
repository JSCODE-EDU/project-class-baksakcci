package com.example.spring_jscode.dto;

import com.example.spring_jscode.domain.entity.Board;
import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String content;

    public Board toEntity(BoardRequestDto boardRequestDto) {
        return new Board(boardRequestDto.getTitle(), boardRequestDto.getContent());
    }
}
