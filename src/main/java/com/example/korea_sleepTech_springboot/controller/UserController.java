package com.example.korea_sleepTech_springboot.controller;

import org.springframework.web.bind.annotation.RestController;

/*
    === UserController Vs AuthController ===

    1. UserController
        : 사용자 정보 및 사용자 관련 로직만 처리
        - 회원 정보 조회(READ)
        - 회원 정보 수정(UPDATE)
        - 비밀번호 변경(UPDATE)
        - 회원 탈퇴(DELETE)

    2. AuthController
        : 인증(Authorization)과 인가(Authentication) 관련 로직만 처리
        - 회원 가입(Sign Up)
        - 로그인(Sign In)
        - 로그아웃
 */

@RestController
public class UserController {
}
