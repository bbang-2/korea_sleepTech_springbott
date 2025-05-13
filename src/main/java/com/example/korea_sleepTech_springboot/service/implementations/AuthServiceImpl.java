package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignUpRequestDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignUpResponseDto;
import com.example.korea_sleepTech_springboot.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseDto<UserSignUpResponseDto> signup(UserSignUpRequestDto dto) {
        return null;
    }

    @Override
    public ResponseDto<UserSignUpResponseDto> login(UserSignUpRequestDto dto) {
        return null;
    }
}
