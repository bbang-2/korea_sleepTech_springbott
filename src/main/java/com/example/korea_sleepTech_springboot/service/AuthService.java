package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignUpRequestDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignUpResponseDto;
import jakarta.validation.Valid;

public interface AuthService {
    ResponseDto<UserSignUpResponseDto> signup(@Valid UserSignUpRequestDto dto);

    ResponseDto<UserSignUpResponseDto> login(@Valid UserSignUpRequestDto dto);
}
