package com.example.spring_jscode.dto;

import com.example.spring_jscode.domain.entity.Board;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createAt;

    private BoardResponseDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.content = builder.content;
        this.createAt = builder.createAt;
    }

    public static class Builder {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime createAt;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder createAt(LocalDateTime val) {
            createAt = val;
            return this;
        }

        public BoardResponseDto build() {
            return new BoardResponseDto(this);
        }
    }

    public static BoardResponseDto fromEntity(Board board) {
        return new Builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createAt(board.getCreateAt())
                .build();
    }
}
