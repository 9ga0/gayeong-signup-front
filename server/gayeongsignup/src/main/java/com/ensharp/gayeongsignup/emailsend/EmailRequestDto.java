package com.ensharp.gayeongsignup.emailsend;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequestDto(
        @Schema(description = "이메일", example = "koo050803@naver.com")
        @Email @NotBlank(message = "이메일을 입력해 주세요")
        String email
) {
    public EmailRequestDto(String email) {
        this.email = email;
    } ///들여쓰기 가독성 신경쓰기..

}