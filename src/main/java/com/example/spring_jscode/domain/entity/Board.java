package com.example.spring_jscode.domain.entity;

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

    public Board(Builder builder) {
        title = builder.title;
        content = builder.content;
    }

    public static class Builder {
        private final String title;
        private final String content;

        public Builder(String title, String content) {
            this.title = title;
            this.content = content;
        }
        // 현재 선택 매개변수가 없어서 구현 x.
        public Board build() {
            return new Board(this);
        }
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
