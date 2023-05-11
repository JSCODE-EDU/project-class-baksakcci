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

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
    }

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Board createBoard(String title, String content) {
        return new Board(title, content);
    }

    public void updateTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
