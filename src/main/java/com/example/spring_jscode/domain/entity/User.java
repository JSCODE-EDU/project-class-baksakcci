package com.example.spring_jscode.domain.entity;

import com.example.spring_jscode.exception.CustomException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
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

    private User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static User create(String email, String password) {
        return new User(email, password);
    }
}
