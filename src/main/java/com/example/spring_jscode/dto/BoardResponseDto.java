package com.example.spring_jscode.dto;

import com.example.spring_jscode.domain.entity.Board;
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

    public static BoardResponseDto fromEntity(Board board) {
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContent(), board.getCreateAt());
    }
}
