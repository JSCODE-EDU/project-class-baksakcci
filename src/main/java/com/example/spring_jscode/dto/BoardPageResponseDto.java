package com.example.spring_jscode.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardPageResponseDto {
    private List<BoardResponseDto> boardResponseDtos;
    private Integer totalPages;
    private Integer offset;

    public static BoardPageResponseDto toDtoFromBoardResponseDto(List<BoardResponseDto> boardResponseDtos, Integer totalPages, Integer offset) {
        return new BoardPageResponseDto(boardResponseDtos, totalPages, offset);
    }
}
