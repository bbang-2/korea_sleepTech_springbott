package com.example.korea_sleepTech_springboot.dto.response;

import com.example.korea_sleepTech_springboot.entity.C_Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private String writer;
    private String title;
    private C_Category category;
}
