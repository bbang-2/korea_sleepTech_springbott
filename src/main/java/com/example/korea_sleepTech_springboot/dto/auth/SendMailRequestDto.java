package com.example.korea_sleepTech_springboot.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SendMailRequestDto {
    @NotBlank
    @Email
    private String email; // email 형식 유효성 검사
}
