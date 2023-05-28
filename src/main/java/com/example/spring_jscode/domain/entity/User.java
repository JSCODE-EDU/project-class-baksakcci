package com.example.spring_jscode.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;

    private String password;

    @Column(name = "signup_at")
    private LocalDateTime signupAt;
    @PrePersist
    protected void onCreate() {
       signupAt = LocalDateTime.now();
    }
}
