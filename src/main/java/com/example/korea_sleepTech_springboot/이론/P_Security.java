package com.example.korea_sleepTech_springboot.이론;

public class P_Security {
}

/*
     ===== 스프링 시큐리티(Spring Security, 보안) =====
     : Spring Framework 기반 애플리케이션에서 보안(인증, 인가, 권한)을 담당하는 보안 프레임워크
     >> 다양한 애너테이션으로 CSRF 공격, 세션 고정 공격을 방어
     >> 요청 헤더에 포함된 보안 처리도 가능

     cf) CSRF(Cross-Site Request Forgery)
       : 자신의 의지와는 상관없이 특정 웹 사이트에
           , 공격자가 원하는 요청을 보내도록 유도하는 웹 보안 취약점
    1. 인증(Authentication)
    : 사용자가 누구인지 확인하는 과정, 신원 입증 과정
    EX) 사용자가 로그인 폼에 ID와 비밀번호를 입력하면
        , 이를 기반으로 사용자가 주장하는 인물인지 확인!

    2. 인가(Authorization)
    : 인증된 사용자가 특정 리소스에 접근할 수 있는 권한인지 확인하는 과정
     EX) 관리자만 특정 페이지에 접근할 수 있도록 설정!
 */

// === Spring Security '인증(로그인)' 처리 절차 === //
// 1. HTTP 요청
//      : 사용자가 폼에 ID와 PW를 입력하고 전송
// 2. Authentication Filter 동작
//      : 전달된 ID와 비밀번호의 유효성 검사를 진행

