package com.example.spring_jscode.dto;

import com.example.spring_jscode.domain.entity.Board;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class BoardRequestDto {
    @NotNull(message = "제목을 무조껀 입력해야 합니다.")
    @NotBlank(message = "제목에 공백이 올 수 없습니다.")
    @Size(min = 1, max = 15, message = "제목은 1글자 이상 15글자 이하로 구성해야 합니다.")
    private String title;

    @NotNull(message = "내용을 무조껀 입력해야 합니다.")
    @Size(min = 1, max = 1000, message = "내용은 1글자 이상 1000글자 이하로 구성해야 합니다.")
    private String content;

    public Board toEntity(BoardRequestDto boardRequestDto) {
        return new Board.Builder(boardRequestDto.getTitle(), boardRequestDto.getContent())
                .build();
    }
}
