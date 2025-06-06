package com.example.korea_sleepTech_springboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentCreateRequestDto {
    @NotBlank (message = "내용은 필수 입력 값이니다.")
    private String content;

    @NotBlank (message = "작성자은 필수 입력 값이니다.")
    private String commenter;
}
