package com.example.spring_jscode.service;

import com.example.spring_jscode.controller.advice.ErrorCode;
import com.example.spring_jscode.domain.entity.User;
import com.example.spring_jscode.domain.repository.UserRepository;
import com.example.spring_jscode.dto.UserRequestDto;
import com.example.spring_jscode.exception.CustomException;
import com.example.spring_jscode.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    public void create(UserRequestDto userRequestDto) {
        userRepository.findByEmail(userRequestDto.getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.EMAIL_ALREADY_EXIST));
        userRepository.save(userRequestDto.toEntity(userRequestDto));
    }

    public String login(UserRequestDto userRequestDto) {
        return TokenUtils.createToken(userRequestDto.getEmail(),
                secretKey,
                1000 * 60 * 60l);
    }
}
