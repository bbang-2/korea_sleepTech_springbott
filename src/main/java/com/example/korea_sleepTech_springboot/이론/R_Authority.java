package com.example.korea_sleepTech_springboot.이론;

// 권한
/*
    Spring Security에서 권한 관리 방법

    ********** 1. hasRole("USER") *********
    : Role(역할)을 검사할 때 사용
    >> "ROLE_" prefix(접두사)가 필요
    >> Spring 권장 방식

    2. hasAuthority("ROLE_USER")
    : Authority(권한)를 검사 할 때 사용
    >> "ROLE_" prefix가 필요 x
 */

import org.springframework.security.access.prepost.PreAuthorize;

public class R_Authority {
    @PreAuthorize("hasRole('USER')")
    public String userProfile() {
        return "User Profile";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String commonData() {
        return "Accessible by USER or ADMIN";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminDashboard() {
        return "Admin Dashboard";
    }
}
