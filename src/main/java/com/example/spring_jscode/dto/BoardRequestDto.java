package com.example.spring_jscode.dto;

import com.example.spring_jscode.domain.entity.Board;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class BoardRequestDto {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 15)
    private String title;

    @NotNull
    @Size(min = 1, max = 1000)
    private String content;

    public Board toEntity(BoardRequestDto boardRequestDto) {
        return new Board.Builder(boardRequestDto.getTitle(), boardRequestDto.getContent())
                .build();
    }
}
