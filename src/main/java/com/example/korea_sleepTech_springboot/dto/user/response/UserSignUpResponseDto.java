package com.example.korea_sleepTech_springboot.dto.user.response;

import com.example.korea_sleepTech_springboot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignUpResponseDto {
    User user;

}
