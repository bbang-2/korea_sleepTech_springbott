package com.example.korea_sleepTech_springboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PostWithCommentCountResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private int commentCount;
}
