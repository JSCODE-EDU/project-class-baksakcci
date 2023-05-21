package com.example.spring_jscode.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String content;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
    }

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Board create(String title, String content) {
        return new Board(title, content);
    }

    public void update(String title, String content) {
        isEmptyTitleAndContent(title, content);
        isBlankTitle(title);
        validateTitleAndContentLength(title, content);
        this.title = title;
        this.content = content;
    }

    private void isEmptyTitleAndContent(String title, String content) {
        if(title == null || title.length() == 0) {
            throw new IllegalArgumentException("제목을 무조껀 입력해야 합니다.");
        } else if(content == null || content.length() == 0) {
            throw new IllegalArgumentException("내용을 입력하세요");
        }
    }

    private void validateTitleAndContentLength(String title, String content) {
        if(title.length() > 15) {
            throw new IllegalArgumentException("제목은 1글자 이상 15글자 이하로 작성해주세요");
        } else if(content.length() > 1000) {
            throw new IllegalArgumentException("내용은 1글자 이상 1000글자 이하로 작성해주세요");
        }
    }

    private void isBlankTitle(String title) {
        if(title == null || title.trim().length() == 0) {
            throw new IllegalArgumentException("제목에 공백만 올 수 없습니다.");
        }
    }
}
