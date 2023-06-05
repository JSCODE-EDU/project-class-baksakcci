package com.example.spring_jscode.controller;

import com.example.spring_jscode.dto.UserRequestDto;
import com.example.spring_jscode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.ok()
                .body("성공적으로 생성되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.ok()
                .body(userService.login(userRequestDto));
    }
}
