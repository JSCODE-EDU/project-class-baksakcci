package com.example.spring_jscode.dto;

import com.example.spring_jscode.domain.entity.User;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class UserRequestDto {
    @Email(message = "이메일 형식으로 입력하세요")
    @NotEmpty(message = "이메일은 공백이 올 수 없습니다.")
    private String email;

    @NotEmpty(message = "패스워드는 공백이 올 수 없습니다.")
    @Size(min = 8, max = 15, message = "8자 이상 15자 이하여야 합니다.")
    private String password;

    public User toEntity(UserRequestDto userRequestDto) {
        return User.create(userRequestDto.getEmail(),
                userRequestDto.getPassword());
    }
}
